package com.phaseplane.ui;

import com.phaseplane.math.PredefinedModel;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ModelGalleryDialog extends Dialog<PredefinedModel> {

    private final ComboBox<String> cmbCategories = new ComboBox<>();
    private final ListView<PredefinedModel> listView = new ListView<>();

    // Detalhes do Modelo
    private final Label lblDetailName = new Label();
    private final Label lblDetailCategory = new Label();
    private final TextArea txtDetailDescription = new TextArea();
    private final Label lblDetailEquations = new Label();
    private final Label lblDetailBounds = new Label();
    private final Label lblDetailParameters = new Label();

    private final ButtonType btnLoadType;
    private final PredefinedModel currentSystemSnapshot;
    private final List<PredefinedModel> userModels = new ArrayList<>();
    private final Path userDir = Paths.get(System.getProperty("user.home"), ".pplane", "models");

    private Button btnExport;

    public ModelGalleryDialog(Stage owner, PredefinedModel currentSystemSnapshot) {
        this.currentSystemSnapshot = currentSystemSnapshot;

        initOwner(owner);
        initModality(Modality.APPLICATION_MODAL);
        setTitle(I18n.get("gallery.window_title"));
        setResizable(true);

        // Herda o ícone da aplicação para evitar o ícone riscado/quebrado
        if (owner != null && !owner.getIcons().isEmpty()) {
            Stage dialogStage = (Stage) getDialogPane().getScene().getWindow();
            dialogStage.getIcons().addAll(owner.getIcons());
        }

        loadUserModelsFromDisk();

        DialogPane dialogPane = getDialogPane();
        dialogPane.setPrefSize(960, 600);
        dialogPane.setStyle("-fx-background-color: #0d1117;");

        // Carrega estilos CSS se existirem
        URL cssResource = getClass().getResource("/css/style.css");
        if (cssResource != null) {
            dialogPane.getStylesheets().add(cssResource.toExternalForm());
        } else if (owner != null && owner.getScene() != null) {
            dialogPane.getStylesheets().addAll(owner.getScene().getStylesheets());
        }

        // Botões nativos do rodapé
        this.btnLoadType = new ButtonType(I18n.get("gallery.btn_load"), ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().addAll(btnLoadType, ButtonType.CANCEL);

        initLayout(dialogPane);
        initEvents();

        setResultConverter(buttonType -> {
            if (buttonType == btnLoadType) {
                return listView.getSelectionModel().getSelectedItem();
            }
            return null;
        });
    }

    private void initLayout(DialogPane dialogPane) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(16));
        root.setStyle("-fx-background-color: #0d1117;");

        // --- TOPO: Título, Filtro e Ações ---
        VBox topBox = new VBox(12);
        topBox.setPadding(new Insets(0, 0, 14, 0));

        Label lblTitle = new Label(I18n.get("gallery.window_title"));
        lblTitle.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-text-fill: #f0f6fc;");

        HBox filterBox = new HBox(10);
        filterBox.setAlignment(Pos.CENTER_LEFT);

        String filterText = I18n.get("gallery.filter");
        if (filterText == null || filterText.startsWith("!")) {
            filterText = I18n.get("gallery.lbl_category");
        }
        Label lblFilter = new Label(filterText);
        lblFilter.setStyle("-fx-text-fill: #8b949e; -fx-font-weight: bold;");

        List<String> categories = new ArrayList<>();
        categories.add(I18n.get("gallery.cat_physics"));
        categories.add(I18n.get("gallery.cat_biomath"));
        categories.add(I18n.get("gallery.cat_climate"));
        categories.add(I18n.get("gallery.cat_finance"));
        categories.add(I18n.get("gallery.tab_user_models"));

        cmbCategories.setItems(FXCollections.observableArrayList(categories));
        cmbCategories.setValue(I18n.get("gallery.cat_biomath"));
        cmbCategories.setPrefWidth(260);
        cmbCategories.setStyle("-fx-background-color: #21262d; -fx-text-fill: #c9d1d9; -fx-border-color: #30363d; -fx-border-radius: 4;");

        Region topSpacer = new Region();
        HBox.setHgrow(topSpacer, Priority.ALWAYS);

        String darkBtnStyle = "-fx-background-color: #21262d; -fx-text-fill: #c9d1d9; -fx-border-color: #30363d; -fx-border-radius: 6; -fx-background-radius: 6; -fx-cursor: hand; -fx-padding: 6 12;";

        Button btnSaveCurrent = new Button(I18n.get("gallery.btn_save_current"));
        btnSaveCurrent.setStyle(darkBtnStyle);
        btnSaveCurrent.setOnAction(e -> handleSaveCurrent());

        Button btnImport = new Button(I18n.get("gallery.btn_import"));
        btnImport.setStyle(darkBtnStyle);
        btnImport.setOnAction(e -> handleImport());

        btnExport = new Button(I18n.get("gallery.btn_export"));
        btnExport.setStyle(darkBtnStyle);
        btnExport.setDisable(true);
        btnExport.setOnAction(e -> handleExport());

        filterBox.getChildren().addAll(lblFilter, cmbCategories, topSpacer, btnSaveCurrent, btnImport, btnExport);
        topBox.getChildren().addAll(lblTitle, filterBox);
        root.setTop(topBox);

        // --- CENTRO: SplitPane com Lista Escura e Painel de Detalhes ---
        SplitPane splitPane = new SplitPane();
        splitPane.setStyle("-fx-background-color: transparent; -fx-box-border: transparent;");

        // Lista de Modelos (Esquerda)
        listView.setStyle("-fx-background-color: #161b22; -fx-control-inner-background: #161b22; -fx-border-color: #30363d; -fx-border-radius: 6;");
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(PredefinedModel item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("-fx-background-color: transparent;");
                } else {
                    setText(getModelDisplayName(item.getName()));
                    if (isSelected()) {
                        setStyle("-fx-background-color: #1f6feb; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-padding: 6 10;");
                    } else {
                        setStyle("-fx-background-color: transparent; -fx-text-fill: #c9d1d9; -fx-padding: 6 10;");
                    }
                }
            }
        });

        VBox leftBox = new VBox(listView);
        VBox.setVgrow(listView, Priority.ALWAYS);

        // Painel de Detalhes (Direita)
        VBox rightBox = buildDetailPanel();
        rightBox.setPadding(new Insets(16));
        rightBox.setStyle("-fx-background-color: #161b22; -fx-border-color: #30363d; -fx-border-radius: 6; -fx-background-radius: 6;");

        splitPane.getItems().addAll(leftBox, rightBox);
        splitPane.setDividerPositions(0.36);

        root.setCenter(splitPane);
        dialogPane.setContent(root);

        // Rodapé de Ações do DialogPane
        Button btnLoad = (Button) dialogPane.lookupButton(btnLoadType);
        btnLoad.setStyle("-fx-background-color: #238636; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 6 16;");
        btnLoad.setDisable(true);

        Button btnCancel = (Button) dialogPane.lookupButton(ButtonType.CANCEL);
        String closeText = I18n.get("btn.close");
        if (closeText == null || closeText.startsWith("!")) closeText = "Close";
        btnCancel.setText(closeText);
        btnCancel.setStyle("-fx-background-color: #21262d; -fx-text-fill: #c9d1d9; -fx-border-color: #30363d; -fx-border-radius: 6; -fx-padding: 6 16;");

        // Barra de botões do diálogo com fundo escuro
        ButtonBar buttonBar = (ButtonBar) dialogPane.lookup(".button-bar");
        if (buttonBar != null) {
            buttonBar.setStyle("-fx-background-color: #0d1117;");
        }

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, selected) -> {
            btnLoad.setDisable(selected == null);
            btnExport.setDisable(selected == null);
            updateDetails(selected);
        });
    }

    private VBox buildDetailPanel() {
        VBox box = new VBox(12);

        lblDetailName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #58a6ff;");
        lblDetailCategory.setStyle("-fx-font-size: 12px; -fx-text-fill: #8b949e;");

        txtDetailDescription.setEditable(false);
        txtDetailDescription.setWrapText(true);
        txtDetailDescription.setPrefRowCount(4);
        txtDetailDescription.setStyle("-fx-control-inner-background: #0d1117; -fx-text-fill: #c9d1d9; -fx-border-color: #30363d; -fx-border-radius: 4; -fx-font-size: 12px;");

        lblDetailEquations.setStyle("-fx-font-family: 'JetBrains Mono', 'Consolas', monospace; -fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #7ee787;");
        lblDetailBounds.setStyle("-fx-font-size: 12px; -fx-text-fill: #e6edf3;");
        lblDetailParameters.setStyle("-fx-font-size: 12px; -fx-text-fill: #e6edf3;");

        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(10);

        Label lblEqHeader = new Label(I18n.get("gallery.lbl_equations"));
        lblEqHeader.setStyle("-fx-text-fill: #8b949e; -fx-font-weight: bold;");
        Label lblParamHeader = new Label(I18n.get("gallery.lbl_parameters"));
        lblParamHeader.setStyle("-fx-text-fill: #8b949e; -fx-font-weight: bold;");
        Label lblDomainHeader = new Label(I18n.get("gallery.lbl_domain"));
        lblDomainHeader.setStyle("-fx-text-fill: #8b949e; -fx-font-weight: bold;");

        grid.addRow(0, lblEqHeader, lblDetailEquations);
        grid.addRow(1, lblParamHeader, lblDetailParameters);
        grid.addRow(2, lblDomainHeader, lblDetailBounds);

        Separator sep1 = new Separator();
        sep1.setStyle("-fx-background-color: #30363d;");
        Separator sep2 = new Separator();
        sep2.setStyle("-fx-background-color: #30363d;");

        box.getChildren().addAll(
                lblDetailName,
                lblDetailCategory,
                sep1,
                txtDetailDescription,
                sep2,
                grid
        );

        return box;
    }

    private void initEvents() {
        cmbCategories.valueProperty().addListener((obs, oldVal, newCategory) -> refreshList(newCategory));

        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && listView.getSelectionModel().getSelectedItem() != null) {
                Button btnLoad = (Button) getDialogPane().lookupButton(btnLoadType);
                btnLoad.fire();
            }
        });

        refreshList(cmbCategories.getValue());
    }

    private void refreshList(String categoryDisplay) {
        List<PredefinedModel> list = new ArrayList<>();
        String userCat = I18n.get("gallery.tab_user_models");

        if (Objects.equals(categoryDisplay, userCat)) {
            list.addAll(userModels);
        } else {
            for (PredefinedModel m : PredefinedModel.getDefaultModels()) {
                if (getCategoryDisplayName(m.getCategory()).equals(categoryDisplay)) {
                    list.add(m);
                }
            }
        }

        listView.setItems(FXCollections.observableArrayList(list));
        if (!list.isEmpty()) {
            listView.getSelectionModel().selectFirst();
        } else {
            updateDetails(null);
        }
    }

    private void updateDetails(PredefinedModel m) {
        if (m == null) {
            lblDetailName.setText(I18n.get("gallery.lbl_select_model"));
            lblDetailCategory.setText("");
            txtDetailDescription.setText("");
            lblDetailEquations.setText("-");
            lblDetailBounds.setText("-");
            lblDetailParameters.setText("-");
            return;
        }

        lblDetailName.setText(getModelDisplayName(m.getName()));
        lblDetailCategory.setText(getCategoryDisplayName(m.getCategory()));
        txtDetailDescription.setText(getModelDisplayDescription(m.getName(), m.getDescription()));

        lblDetailEquations.setText(String.format("d%s/dt = %s\nd%s/dt = %s",
                m.getVarX(), m.getExprDx(), m.getVarY(), m.getExprDy()));

        lblDetailBounds.setText(String.format(Locale.US, "[%s: %.2f to %.2f] × [%s: %.2f to %.2f] (tMax: %.1f)",
                m.getVarX(), m.getXMin(), m.getXMax(), m.getVarY(), m.getYMin(), m.getYMax(), m.getTMax()));

        StringBuilder sb = new StringBuilder();
        if (m.getParameters() == null || m.getParameters().isEmpty()) {
            sb.append(I18n.get("gallery.lbl_none"));
        } else {
            m.getParameters().forEach((k, v) -> sb.append(k).append(" = ").append(v).append(";  "));
        }
        lblDetailParameters.setText(sb.toString());
    }

    private void handleSaveCurrent() {
        if (currentSystemSnapshot == null) return;
        TextInputDialog dialog = new TextInputDialog(currentSystemSnapshot.getName());
        dialog.initOwner(getOwner());
        dialog.setTitle(I18n.get("gallery.save_dialog_title"));
        dialog.setHeaderText(I18n.get("gallery.save_dialog_header"));
        dialog.setContentText(I18n.get("gallery.save_dialog_content"));

        dialog.showAndWait().ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                PredefinedModel userM = new PredefinedModel(
                        I18n.get("gallery.user_models_category"), name.trim(),
                        currentSystemSnapshot.getVarX(), currentSystemSnapshot.getVarY(),
                        currentSystemSnapshot.getExprDx(), currentSystemSnapshot.getExprDy(),
                        currentSystemSnapshot.getXMin(), currentSystemSnapshot.getXMax(),
                        currentSystemSnapshot.getYMin(), currentSystemSnapshot.getYMax(),
                        currentSystemSnapshot.getTMax(),
                        currentSystemSnapshot.getParameters(),
                        I18n.get("gallery.user_model_desc")
                );

                saveModelToDisk(userM);
                userModels.add(userM);

                String userCat = I18n.get("gallery.tab_user_models");
                cmbCategories.setValue(userCat);
                refreshList(userCat);
                listView.getSelectionModel().select(userM);
            }
        });
    }

    private void handleImport() {
        FileChooser fc = new FileChooser();
        fc.setTitle(I18n.get("gallery.import_title"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(I18n.get("gallery.file_filter"), "*.pplane"));
        File file = fc.showOpenDialog(getOwner());
        if (file != null) {
            try {
                PredefinedModel imported = PredefinedModel.loadFromFile(file);
                saveModelToDisk(imported);
                userModels.add(imported);
                String userCat = I18n.get("gallery.tab_user_models");
                cmbCategories.setValue(userCat);
                refreshList(userCat);
                listView.getSelectionModel().select(imported);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void handleExport() {
        PredefinedModel selected = listView.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        FileChooser fc = new FileChooser();
        fc.setTitle(I18n.get("gallery.export_title"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(I18n.get("gallery.file_filter"), "*.pplane"));
        fc.setInitialFileName(selected.getName().toLowerCase().replace(" ", "_") + ".pplane");

        File file = fc.showSaveDialog(getOwner());
        if (file != null) {
            try {
                selected.saveToFile(file);
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

    private String getCategoryDisplayName(String rawCategory) {
        if (rawCategory == null) return "";
        return switch (rawCategory) {
            case "Física & Mecânica" -> I18n.get("gallery.cat_physics");
            case "Biomatemática & Ecologia" -> I18n.get("gallery.cat_biomath");
            case "Clima & Oceanografia" -> I18n.get("gallery.cat_climate");
            case "Finanças & Dinâmica Não Linear" -> I18n.get("gallery.cat_finance");
            case "Sistemas Não Lineares & Separatrizes" -> I18n.get("gallery.cat_nonlinear");
            case "Sistemas Lineares & Teoria Qualitativa" -> I18n.get("gallery.cat_linear");
            case "Química & Dinâmica Não Linear" -> I18n.get("gallery.cat_chemistry");
            default -> rawCategory;
        };
    }

    private String getModelDisplayName(String rawName) {
        if (rawName == null) return "";
        return switch (rawName) {
            case "Oscilador de Van der Pol" -> I18n.get("model.vanderpol.name");
            case "Pêndulo Não Linear com Atrito" -> I18n.get("model.pendulum.name");
            case "Oscilador de Duffing (Autônomo)" -> I18n.get("model.duffing.name");
            case "Predador-Presa (Lotka-Volterra)" -> I18n.get("model.lotka.name");
            case "Competição de Duas Espécies (Murray)" -> I18n.get("model.murray.name");
            case "Neurônio de FitzHugh-Nagumo" -> I18n.get("model.fitzhugh.name");
            case "Circulação Termoalina de Stommel" -> I18n.get("model.stommel.name");
            case "Ciclo Econômico Não Linear de Kaldor" -> I18n.get("model.kaldor.name");
            case "Epidemia SIR (Kermack-McKendrick)" -> I18n.get("model.sir.name");
            case "Pplane Clássico (Polking)" -> I18n.get("model.polking_classic.name");
            case "10. Polking: Múltiplos Equilíbrios" -> I18n.get("model.polking_multiple.name");
            case "Sistemas Lineares 2D (Classificação & O Amor de Strogatz)" -> I18n.get("model.strogatz_love.name");
            case "Oscilador Químico Brusselator" -> I18n.get("model.brusselator.name");
            default -> rawName;
        };
    }

    private String getModelDisplayDescription(String rawName, String rawDesc) {
        if (rawName == null) return rawDesc != null ? rawDesc : "";
        return switch (rawName) {
            case "Oscilador de Van der Pol" -> I18n.get("model.vanderpol.desc");
            case "Pêndulo Não Linear com Atrito" -> I18n.get("model.pendulum.desc");
            case "Oscilador de Duffing (Autônomo)" -> I18n.get("model.duffing.desc");
            case "Predador-Presa (Lotka-Volterra)" -> I18n.get("model.lotka.desc");
            case "Competição de Duas Espécies (Murray)" -> I18n.get("model.murray.desc");
            case "Neurônio de FitzHugh-Nagumo" -> I18n.get("model.fitzhugh.desc");
            case "Circulação Termoalina de Stommel" -> I18n.get("model.stommel.desc");
            case "Ciclo Econômico Não Linear de Kaldor" -> I18n.get("model.kaldor.desc");
            case "Epidemia SIR (Kermack-McKendrick)" -> I18n.get("model.sir.desc");
            case "Pplane Clássico (Polking)" -> I18n.get("model.polking_classic.desc");
            case "10. Polking: Múltiplos Equilíbrios" -> I18n.get("model.polking_multiple.desc");
            case "Sistemas Lineares 2D (Classificação & O Amor de Strogatz)" -> I18n.get("model.strogatz_love.desc");
            case "Oscilador Químico Brusselator" -> I18n.get("model.brusselator.desc");
            default -> rawDesc;
        };
    }

    public static Optional<PredefinedModel> showGallery(Stage owner, PredefinedModel currentSystemSnapshot) {
        ModelGalleryDialog dialog = new ModelGalleryDialog(owner, currentSystemSnapshot);
        return dialog.showAndWait();
    }
}
