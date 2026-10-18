package com.phaseplane.ui;

import com.phaseplane.math.PredefinedModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

public class ModelGalleryDialog {

    private final Stage stage;
    private final Consumer<PredefinedModel> onModelSelected;
    private final PredefinedModel currentSystemSnapshot;
    private PredefinedModel selectedModel;

    private Label lblTitle;
    private Label lblEquations;
    private Label lblBounds;
    private Label lblParams;
    private Label lblDescription;
    private Button btnLoad;
    private Button btnExport;

    private ListView<PredefinedModel> userListView;
    private final List<PredefinedModel> userModels = new ArrayList<>();
    private final Path userDir = Paths.get(System.getProperty("user.home"), ".pplane", "models");

    public ModelGalleryDialog(Window owner, PredefinedModel currentSystemSnapshot, Consumer<PredefinedModel> onModelSelected) {
        this.currentSystemSnapshot = currentSystemSnapshot;
        this.onModelSelected = onModelSelected;

        stage = new Stage();
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Galeria de Sistemas Dinâmicos & Modelos");
        stage.setResizable(true);

        loadUserModelsFromDisk();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(12));
        root.setPrefSize(780, 500);

        // Centro: Abas à esquerda e painel de detalhes à direita
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(createTabsPanel(), createDetailPanel());
        splitPane.setDividerPositions(0.42);
        root.setCenter(splitPane);

        // Rodapé com botões de ação
        root.setBottom(createFooter());

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    private TabPane createTabsPanel() {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        List<PredefinedModel> defaults = PredefinedModel.getDefaultModels();

        Map<String, List<PredefinedModel>> byCategory = new LinkedHashMap<>();
        byCategory.put("Física & Mecânica", new ArrayList<>());
        byCategory.put("Biomatemática & Ecologia", new ArrayList<>());
        byCategory.put("Clima & Oceanografia", new ArrayList<>());
        byCategory.put("Finanças & Dinâmica Não Linear", new ArrayList<>());

        for (PredefinedModel m : defaults) {
            byCategory.computeIfAbsent(m.getCategory(), k -> new ArrayList<>()).add(m);
        }

        for (Map.Entry<String, List<PredefinedModel>> entry : byCategory.entrySet()) {
            Tab tab = new Tab(entry.getKey());
            ListView<PredefinedModel> lv = new ListView<>();
            lv.getItems().addAll(entry.getValue());
            configureListView(lv);
            tab.setContent(lv);
            tabPane.getTabs().add(tab);
        }

        // Aba: Meus Modelos
        Tab userTab = new Tab("Meus Modelos");
        userListView = new ListView<>();
        userListView.getItems().addAll(userModels);
        configureListView(userListView);
        userTab.setContent(userListView);
        tabPane.getTabs().add(userTab);

        return tabPane;
    }

    private void configureListView(ListView<PredefinedModel> lv) {
        lv.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(PredefinedModel item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        lv.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                updateDetailPanel(newVal);
            }
        });
    }

    private VBox createDetailPanel() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(12));

        lblTitle = new Label("Selecione um modelo à esquerda");
        lblTitle.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        lblEquations = new Label();
        lblEquations.setStyle("-fx-font-family: monospace; -fx-font-size: 13px;");

        lblBounds = new Label();
        lblBounds.setStyle("-fx-font-size: 12px;");

        lblParams = new Label();
        lblParams.setStyle("-fx-font-size: 12px;");

        lblDescription = new Label();
        lblDescription.setWrapText(true);
        lblDescription.setStyle("-fx-font-size: 12px; -fx-text-fill: #555555;");

        vbox.getChildren().addAll(lblTitle, new Separator(), lblEquations, lblBounds, lblParams, new Separator(), lblDescription);
        return vbox;
    }

    private void updateDetailPanel(PredefinedModel m) {
        this.selectedModel = m;
        lblTitle.setText(m.getName());

        lblEquations.setText(String.format("d%s/dt = %s\nd%s/dt = %s",
                m.getVarX(), m.getExprDx(), m.getVarY(), m.getExprDy()));

        lblBounds.setText(String.format(Locale.US, "Janela de Fase: [%.2f, %.2f] × [%.2f, %.2f] | tMax: %.1f",
                m.getXMin(), m.getXMax(), m.getYMin(), m.getYMax(), m.getTMax()));

        StringBuilder sb = new StringBuilder("Parâmetros: ");
        if (m.getParameters().isEmpty()) {
            sb.append("(nenhum)");
        } else {
            m.getParameters().forEach((k, v) -> sb.append(k).append(" = ").append(v).append(";  "));
        }
        lblParams.setText(sb.toString());

        lblDescription.setText(m.getDescription());
        btnLoad.setDisable(false);
        btnExport.setDisable(false);
    }

    private HBox createFooter() {
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(12, 0, 0, 0));
        hbox.setAlignment(Pos.CENTER_RIGHT);

        Button btnSaveCurrent = new Button("Salvar Configuração Atual...");
        btnSaveCurrent.setOnAction(e -> handleSaveCurrent());

        Button btnImport = new Button("Importar .pplane...");
        btnImport.setOnAction(e -> handleImport());

        btnExport = new Button("Exportar .pplane...");
        btnExport.setDisable(true);
        btnExport.setOnAction(e -> handleExport());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        btnLoad = new Button("Carregar no Plano");
        btnLoad.setStyle("-fx-font-weight: bold;");
        btnLoad.setDisable(true);
        btnLoad.setOnAction(e -> {
            if (selectedModel != null && onModelSelected != null) {
                onModelSelected.accept(selectedModel);
                stage.close();
            }
        });

        Button btnClose = new Button("Fechar");
        btnClose.setOnAction(e -> stage.close());

        hbox.getChildren().addAll(btnSaveCurrent, btnImport, btnExport, spacer, btnLoad, btnClose);
        return hbox;
    }

    private void handleSaveCurrent() {
        if (currentSystemSnapshot == null) return;
        TextInputDialog dialog = new TextInputDialog(currentSystemSnapshot.getName());
        dialog.setTitle("Salvar Modelo");
        dialog.setHeaderText("Salvar parâmetros e equações atuais em 'Meus Modelos':");
        dialog.setContentText("Nome do Modelo:");
        dialog.initOwner(stage);

        dialog.showAndWait().ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                PredefinedModel userM = new PredefinedModel(
                        "Modelos do Usuário", name.trim(),
                        currentSystemSnapshot.getVarX(), currentSystemSnapshot.getVarY(),
                        currentSystemSnapshot.getExprDx(), currentSystemSnapshot.getExprDy(),
                        currentSystemSnapshot.getXMin(), currentSystemSnapshot.getXMax(),
                        currentSystemSnapshot.getYMin(), currentSystemSnapshot.getYMax(),
                        currentSystemSnapshot.getTMax(),
                        currentSystemSnapshot.getParameters(),
                        "Criado pelo usuário."
                );

                saveModelToDisk(userM);
                userModels.add(userM);
                if (userListView != null) {
                    userListView.getItems().setAll(userModels);
                }
            }
        });
    }

    private void handleImport() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Importar Modelo (.pplane)");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Modelo Phase Plane (*.pplane)", "*.pplane"));
        File file = fc.showOpenDialog(stage);
        if (file != null) {
            try {
                PredefinedModel imported = PredefinedModel.loadFromFile(file);
                saveModelToDisk(imported);
                userModels.add(imported);
                if (userListView != null) {
                    userListView.getItems().setAll(userModels);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void handleExport() {
        if (selectedModel == null) return;
        FileChooser fc = new FileChooser();
        fc.setTitle("Exportar Modelo (.pplane)");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Modelo Phase Plane (*.pplane)", "*.pplane"));
        fc.setInitialFileName(selectedModel.getName().toLowerCase().replace(" ", "_") + ".pplane");

        File file = fc.showSaveDialog(stage);
        if (file != null) {
            try {
                selectedModel.saveToFile(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void loadUserModelsFromDisk() {
        try {
            if (Files.exists(userDir)) {
                File[] files = userDir.toFile().listFiles((d, n) -> n.endsWith(".pplane"));
                if (files != null) {
                    for (File f : files) {
                        try {
                            userModels.add(PredefinedModel.loadFromFile(f));
                        } catch (Exception ignored) {}
                    }
                }
            }
        } catch (Exception ignored) {}
    }

    private void saveModelToDisk(PredefinedModel model) {
        try {
            Files.createDirectories(userDir);
            String safeName = model.getName().replaceAll("[^a-zA-Z0-9_-]", "_") + ".pplane";
            File target = userDir.resolve(safeName).toFile();
            model.saveToFile(target);
        } catch (Exception ignored) {}
    }

    public void show() {
        stage.show();
    }
}
