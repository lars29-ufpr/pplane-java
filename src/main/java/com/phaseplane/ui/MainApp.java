package com.phaseplane;
import com.phaseplane.ui.AboutDialog;

import javafx.scene.control.ScrollPane;
import com.phaseplane.ui.AboutDialog;
import com.phaseplane.ui.EquilibriumDetailDialog;
import com.phaseplane.ui.ExportEngine;
import com.phaseplane.ui.GuideViewerDialog;
import com.phaseplane.ui.ModelGalleryDialog;
import com.phaseplane.ui.TimeSeriesViewer;
import com.phaseplane.ui.ParameterStudioStage;
import com.phaseplane.math.BoundingBox;
import com.phaseplane.math.DormandPrinceIntegrator;
import com.phaseplane.math.DynamicalSystem2D;
import com.phaseplane.math.EquilibriumAnalyzer;
import com.phaseplane.math.EquilibriumPoint;
import com.phaseplane.math.PredefinedModel;
import com.phaseplane.math.Trajectory;
import com.phaseplane.ui.PhasePlaneCanvas;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class MainApp extends Application {
private ToggleButton btnInspectMode;
    private DynamicalSystem2D system;
    private PhasePlaneCanvas canvas;
    private final DormandPrinceIntegrator integrator = new DormandPrinceIntegrator();
    private  TimeSeriesViewer timeSeriesViewer;
private ParameterStudioStage parameterStudioStage; 
private final java.util.List<double[]> initialPoints = new java.util.ArrayList<>();
private boolean autoScanEquilibria = false;
   private Trajectory lastTrajectory;
private List<EquilibriumPoint> lastEquilibria = new ArrayList<>();
    // Controles de entrada
    private TextField fxField;
    private TextField fyField;
    private TextField varXField;
    private TextField varYField;
    private TextField paramsField;

    private TextField xMinField;
    private TextField xMaxField;
    private TextField yMinField;
    private TextField yMaxField;
    private TextField tMaxField;
    private TextField tMinField;

    private Label statusLabel;
    private Label coordLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
timeSeriesViewer = new TimeSeriesViewer();
        primaryStage.setTitle("pplaneX - Análise de Planos de Fase");

        BoundingBox initialBounds = new BoundingBox(-3.0, 3.0, -3.0, 3.0);
        canvas = new PhasePlaneCanvas(initialBounds);

        // Painel esquerdo (SideBar com controles)
        VBox sideBar = createSidebar();

        // Barra inferior de status
        HBox statusBar = createStatusBar();
BorderPane mainLayout = new BorderPane();

        ScrollPane scrollSidebar = new ScrollPane(sideBar);
        scrollSidebar.setFitToWidth(true);
        scrollSidebar.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollSidebar.setStyle("-fx-background: #161b22; -fx-background-color: #161b22; -fx-border-color: transparent;");

        mainLayout.setLeft(scrollSidebar);
        mainLayout.setCenter(canvas);
        mainLayout.setBottom(statusBar);
        // Dispara o cálculo de trajetória ao clicar no Canvas
        canvas.setOnPointClicked((x0, y0) -> {
            if (system == null) return;

            // Se o modo de inspeção estiver ativo, refinamos e exibimos o pop-up moderno
            if (btnInspectMode != null && btnInspectMode.isSelected()) {
                BoundingBox bounds = getCurrentBounds();
                java.util.Optional<com.phaseplane.math.EquilibriumPoint> eqOpt = 
                        com.phaseplane.math.EquilibriumAnalyzer.findEquilibrium(system, x0, y0, bounds);

                if (eqOpt.isPresent()) {
                    com.phaseplane.math.EquilibriumPoint eq = eqOpt.get();
                    statusLabel.setText(String.format("Ponto crítico em (%.5f, %.5f): %s", eq.x(), eq.y(), eq.classificacaoFormatada()));
                    
                    EquilibriumDetailDialog dialog = new EquilibriumDetailDialog(primaryStage, eq, saddle -> {
                        computeSeparatrices();
                    });
                    dialog.showAndWait();
                } else {
                    statusLabel.setText(String.format("Nenhum equilíbrio encontrado próximo a (%.3f, %.3f).", x0, y0));
                }
                return;
            }

            // Fluxo padrão: Integração de órbita bidirecional ao clicar no Canvas
            double tMin = parseDouble(tMinField.getText(), -10.0);
            double tMax = parseDouble(tMaxField.getText(), 20.0);

            initialPoints.add(new double[]{x0, y0});
            Trajectory trajectory = integrator.integrateBidirectional(
                    system, x0, y0, tMin, tMax, getCurrentBounds()
            );
            this.lastTrajectory = trajectory;
            canvas.addTrajectory(trajectory);
            statusLabel.setText(String.format("Órbita traçada a partir de (%.3f, %.3f) com %d passos.", x0, y0, trajectory.size()));
        });
        // Atualização contínua das coordenadas do cursor
        canvas.setOnPointerMoved((wx, wy) -> coordLabel.setText(String.format("x: %+.3f  |  y: %+.3f", wx, wy)));

      // Carrega o sistema inicial padrão (Oscilador de Van der Pol)
       applySystem();

       Scene scene = new Scene(mainLayout, 1200, 850);
       scene.getStylesheets().add(createDarkThemeStyle());
        
       primaryStage.setScene(scene);
       primaryStage.show();
   }
       // Carrega o sistema inicial padrão APÓS o palco estar visível
      

    private VBox createSidebar() {
        VBox sidebar = new VBox(8);
        sidebar.setPadding(new Insets(12));
        sidebar.setPrefWidth(380);
        sidebar.setStyle("-fx-background-color: #161b22; -fx-border-color: #30363d; -fx-border-width: 0 1 0 0;");

        Label title = new Label("SISTEMA DE EDOs 2D");
        title.setStyle("-fx-font-weight: bold; -fx-text-fill: #58a6ff; -fx-font-size: 14px;");

        // Presets rápidos
       

        // Campos do Sistema
        varXField = new TextField("x");
        varYField = new TextField("y");
Label lblVar = new Label("Var:");
lblVar.setMinWidth(Region.USE_PREF_SIZE);
varXField.setPrefWidth(60);
varYField.setPrefWidth(60);
HBox varBox = new HBox(8, lblVar, varXField, varYField);
        varBox.setAlignment(Pos.CENTER_LEFT);

fxField = new TextField("2 * x - y + 3 * (x^2 - y^2) + 2 * x * y");
        fyField = new TextField("x - 3 * y - 3 * (x^2 - y^2) + 3 * x * y");

        paramsField = new TextField("");
        paramsField.setPromptText("ex: a=1.5, b=0.5, mu=1.0");

        // Limites dos Eixos
        xMinField = new TextField("-2.0");
        xMaxField = new TextField("2.0");
        yMinField = new TextField("-2.0");
        yMaxField = new TextField("2.0");
xMinField.setPrefWidth(70);
xMaxField.setPrefWidth(70);
yMinField.setPrefWidth(70);
yMaxField.setPrefWidth(70);

        GridPane boundsGrid = new GridPane();
        boundsGrid.setHgap(8);
        boundsGrid.setVgap(6);
        boundsGrid.addRow(0, new Label("xmin:"), xMinField, new Label("xmax:"), xMaxField);
        boundsGrid.addRow(1, new Label("ymin:"), yMinField, new Label("ymax:"), yMaxField);

        // Parâmetros de Tempo
        tMinField = new TextField("-10.0");
        tMaxField = new TextField("25.0");
tMinField.setPrefWidth(70);
        tMaxField.setPrefWidth(70);
        GridPane timeGrid = new GridPane();
        timeGrid.setHgap(8);
        timeGrid.setVgap(6);
        timeGrid.addRow(0, new Label("t min:"), tMinField, new Label("t max:"), tMaxField);

        // Botões de Ação
Button btnGallery = new Button("📚 Galeria");
        btnGallery.setMaxWidth(Double.MAX_VALUE);
        btnGallery.setStyle("-fx-background-color: #8957e5; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        btnGallery.setOnAction(e -> {
            PredefinedModel snapshot = captureCurrentModelSnapshot();
            ModelGalleryDialog dialog = new ModelGalleryDialog(btnGallery.getScene() != null ? btnGallery.getScene().getWindow() : null, snapshot, this::loadPredefinedModel);
            dialog.show();
        });

Button btnGuide = new Button("📖 Guia Didático");
        btnGuide.setMaxWidth(Double.MAX_VALUE);
        btnGuide.setStyle("-fx-background-color: #21262d; -fx-text-fill: #58a6ff; -fx-border-color: #30363d; -fx-font-weight: bold;");
        btnGuide.setOnAction(e -> GuideViewerDialog.show((Stage) btnGuide.getScene().getWindow()));
Button btnAbout = new Button("Sobre o PPLANEX");
        btnAbout.setMaxWidth(Double.MAX_VALUE);
        btnAbout.setStyle("-fx-background-color: #21262d; -fx-text-fill: #8b949e; -fx-border-color: #30363d; -fx-border-radius: 6; -fx-background-radius: 6; -fx-font-weight: bold; -fx-cursor: hand;");
        btnAbout.setOnAction(e -> AboutDialog.show((Stage) btnAbout.getScene().getWindow()));

        HBox rowSupport = new HBox(8);
        if (btnGallery != null) {
            btnGallery.setMaxWidth(Double.MAX_VALUE);
            rowSupport.getChildren().add(btnGallery);
            HBox.setHgrow(btnGallery, Priority.ALWAYS);
        }
        if (btnGuide != null) {
            btnGuide.setMaxWidth(Double.MAX_VALUE);
            rowSupport.getChildren().add(btnGuide);
            HBox.setHgrow(btnGuide, Priority.ALWAYS);
        }

        VBox boxTopModels = new VBox(6, rowSupport, btnAbout);

        Button btnUpdate = new Button("Atualizar Campo");
        btnUpdate.setMaxWidth(Double.MAX_VALUE);
        btnUpdate.setStyle("-fx-background-color: #238636; -fx-text-fill: white; -fx-font-weight: bold;");
        btnUpdate.setOnAction(e -> applySystem());

        Button btnEquilibria = new Button("Localizar Equilíbrios");
        btnEquilibria.setMaxWidth(Double.MAX_VALUE);
        btnEquilibria.setStyle("-fx-background-color: #1f6feb; -fx-text-fill: white;");
        btnEquilibria.setOnAction(e -> scanEquilibria());
Button btnSeparatrices = new Button("Traçar Separatrizes");
        btnSeparatrices.setMaxWidth(Double.MAX_VALUE);
        btnSeparatrices.setStyle("-fx-background-color: #8957e5; -fx-text-fill: white; -fx-font-weight: bold;");
        btnSeparatrices.setOnAction(e -> computeSeparatrices());

Button btnStudio = new Button("Parameter Studio (Sliders)");
        btnStudio.setMaxWidth(Double.MAX_VALUE);
        btnStudio.setStyle("-fx-background-color: #d29922; -fx-text-fill: #0d1117; -fx-font-weight: bold;");
        btnStudio.setOnAction(e -> openParameterStudio());

// Entrada manual de coordenadas iniciais (x0, y0)
        TextField x0Field = new TextField("1.0");
        x0Field.setPrefWidth(55);
        x0Field.setStyle("-fx-background-color: #0d1117; -fx-text-fill: #e6edf3; -fx-border-color: #30363d; -fx-border-radius: 4;");

        TextField y0Field = new TextField("1.0");
        y0Field.setPrefWidth(55);
        y0Field.setStyle("-fx-background-color: #0d1117; -fx-text-fill: #e6edf3; -fx-border-color: #30363d; -fx-border-radius: 4;");

        Button btnPlotManual = new Button("Traçar");
        btnPlotManual.setStyle("-fx-background-color: #238636; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 4;");
        btnPlotManual.setOnAction(e -> {
            try {
                double x0 = Double.parseDouble(x0Field.getText().trim().replace(',', '.'));
                double y0 = Double.parseDouble(y0Field.getText().trim().replace(',', '.'));
                computeTrajectory(x0, y0);
            } catch (NumberFormatException ex) {
                statusLabel.setText("Coordenadas iniciais inválidas.");
            }
        });

        HBox manualBox = new HBox(6, new Label("x₀:"), x0Field, new Label("y₀:"), y0Field, btnPlotManual);
        manualBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

CheckBox chkNullclines = new CheckBox("Exibir Isóclinas Nulas (Nullclines)");
        chkNullclines.setSelected(true);
        chkNullclines.setStyle("-fx-text-fill: #c9d1d9; -fx-font-size: 11px;");
        chkNullclines.setOnAction(e -> canvas.setShowNullclines(chkNullclines.isSelected()));

        Button btnClear = new Button("Limpar Trajetórias");
        btnClear.setMaxWidth(Double.MAX_VALUE);
        btnClear.setStyle("-fx-background-color: #21262d; -fx-text-fill: #c9d1d9; -fx-border-color: #30363d;");
btnClear.setOnAction(e -> {
            initialPoints.clear();
autoScanEquilibria = false;
            canvas.clearTrajectories();
canvas.clearEquilibria();
            canvas.clearSeparatrices();
        });
// Junta Inspecionar e Limpar lado a lado
btnInspectMode = new ToggleButton("⌕ Inspecionar Equilíbrio (Clique)");
        btnInspectMode.setMaxWidth(Double.MAX_VALUE);
        btnInspectMode.setStyle("-fx-background-color: #21262d; -fx-text-fill: #58a6ff; -fx-border-color: #388bfd; -fx-border-radius: 6;");
        btnInspectMode.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                btnInspectMode.setStyle("-fx-background-color: #1f6feb; -fx-text-fill: white; -fx-font-weight: bold;");
                statusLabel.setText("Modo Inspeção ativo: clique próximo a um ponto crítico no plano.");
            } else {
                btnInspectMode.setStyle("-fx-background-color: #21262d; -fx-text-fill: #58a6ff; -fx-border-color: #388bfd;");
                statusLabel.setText("Modo Trajetória ativo: clique no plano para traçar órbitas.");
            }
        });
        HBox boxInspectClear = new HBox(8, btnInspectMode, btnClear);
        HBox.setHgrow(btnInspectMode, Priority.ALWAYS);
        HBox.setHgrow(btnClear, Priority.ALWAYS);
        btnInspectMode.setMaxWidth(Double.MAX_VALUE);
Button btnTimeSeries = new Button("Soluções vs Tempo: x(t), y(t)");
        btnTimeSeries.setMaxWidth(Double.MAX_VALUE);
        btnTimeSeries.setStyle("-fx-background-color: #6e40c9; -fx-text-fill: white; -fx-font-weight: bold;");
        btnTimeSeries.setOnAction(e -> {
            if (lastTrajectory != null) {
                timeSeriesViewer.updateTrajectory(lastTrajectory);
            }
        });
Button btnExportPNG = new Button("Exportar PNG");
        btnExportPNG.setMaxWidth(Double.MAX_VALUE);
        btnExportPNG.setStyle("-fx-background-color: #0f766e; -fx-text-fill: white; -fx-font-weight: bold;");
        btnExportPNG.setOnAction(e -> {
            ExportEngine.exportPNG(canvas, canvas.getScene().getWindow());
        });

        Button btnExportSVG = new Button("Exportar SVG");
        btnExportSVG.setMaxWidth(Double.MAX_VALUE);
        btnExportSVG.setStyle("-fx-background-color: #0369a1; -fx-text-fill: white; -fx-font-weight: bold;");
btnExportSVG.setOnAction(e -> {
            ExportEngine.exportSVG(canvas, canvas.getScene().getWindow());
        });
// Agrupamento horizontal inteligente de botões (Opção B)
        HBox boxUpdateEquilibria = new HBox(6, btnUpdate, btnEquilibria);
        HBox.setHgrow(btnUpdate, Priority.ALWAYS);
        HBox.setHgrow(btnEquilibria, Priority.ALWAYS);

        HBox boxAnalysis = new HBox(6, btnSeparatrices, btnStudio);
        HBox.setHgrow(btnSeparatrices, Priority.ALWAYS);
        HBox.setHgrow(btnStudio, Priority.ALWAYS);

       HBox boxExport = new HBox(6, btnExportPNG, btnExportSVG);
        HBox.setHgrow(btnExportPNG, Priority.ALWAYS);
        HBox.setHgrow(btnExportSVG, Priority.ALWAYS);

        sidebar.getChildren().addAll(
                title,
                boxTopModels,
                varBox,
                new Label("dx/dt ="),
                fxField,
                new Label("dy/dt ="),
                fyField,
                new Label("Parâmetros (chave=valor):"),
                paramsField,
                new Label("Limites do Plano de Fase:"),
                boundsGrid,
                new Label("Janela de Integração:"),
                timeGrid,
                new Label("Ponto Inicial (x₀, y₀):"),
manualBox,
                chkNullclines,
                boxUpdateEquilibria,
                boxInspectClear,
                boxAnalysis,
                btnTimeSeries,
                boxExport
        );
        return sidebar;
    }

    private HBox createStatusBar() {
        HBox status = new HBox(20);
        status.setPadding(new Insets(6, 16, 6, 16));
        status.setStyle("-fx-background-color: #0d1117; -fx-border-color: #30363d; -fx-border-width: 1 0 0 0;");
        status.setAlignment(Pos.CENTER_LEFT);

        statusLabel = new Label("Pronto. Clique em qualquer local do gráfico para calcular a órbita.");
        statusLabel.setStyle("-fx-text-fill: #8b949e;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        coordLabel = new Label("x: 0.000  |  y: 0.000");
        coordLabel.setStyle("-fx-text-fill: #58a6ff; -fx-font-family: 'Menlo', 'Consolas', monospace;");

        status.getChildren().addAll(statusLabel, spacer, coordLabel);
        return status;
    }

    private void applySystem() {
        try {
            Map<String, Double> params = parseParameters(paramsField.getText());
            BoundingBox bounds = getCurrentBounds();

            system = new DynamicalSystem2D(
                    varXField.getText(),
                    varYField.getText(),
                    fxField.getText(),
                    fyField.getText(),
                    params
            );

canvas.setBounds(bounds);
        canvas.setOnBoundsChanged(newBounds -> {
            setBoundsFields(newBounds.xMin(), newBounds.xMax(), newBounds.yMin(), newBounds.yMax());
        });
            canvas.clearTrajectories();
            canvas.clearSeparatrices();
            canvas.clearEquilibria();
            canvas.setSystem(system);

            // Recalcula dinamicamente todas as trajetórias ativas com os novos parâmetros
            if (!initialPoints.isEmpty()) {
// Atualiza o visualizador temporal ao vivo se a janela estiver aberta
            if (timeSeriesViewer != null && timeSeriesViewer.isShowing() && lastTrajectory != null) {
                timeSeriesViewer.updateTrajectory(lastTrajectory);
            }
                double tMin = parseDouble(tMinField.getText(), -10.0);
                double tMax = parseDouble(tMaxField.getText(), 20.0);
                for (double[] pt : initialPoints) {
                    Trajectory tr = integrator.integrateBidirectional(system, pt[0], pt[1], tMin, tMax, bounds);
                    this.lastTrajectory = tr;
                    canvas.addTrajectory(tr);
                }
            }
// Recalcula equilíbrios automaticamente se o modo estiver ativo
            if (autoScanEquilibria) {
                scanEquilibria();
            }
            statusLabel.setText("Campo de vetores compilado e atualizado.");
            statusLabel.setText("Campo de vetores compilado e atualizado.");
        } catch (Exception ex) {
            showError("Erro na expressão matemática", ex.getMessage());
        }
    }

    private void scanEquilibria() {
        if (system == null) return;
this.autoScanEquilibria = true;    
    BoundingBox bounds = getCurrentBounds();

        List<EquilibriumPoint> found = new ArrayList<>();
        int gridScan = 12;

        double dx = (bounds.xMax() - bounds.xMin()) / gridScan;
        double dy = (bounds.yMax() - bounds.yMin()) / gridScan;

        for (int i = 0; i <= gridScan; i++) {
            double gx = bounds.xMin() + i * dx;
            for (int j = 0; j <= gridScan; j++) {
                double gy = bounds.yMin() + j * dy;

                EquilibriumAnalyzer.findEquilibrium(system, gx, gy, bounds).ifPresent(eq -> {
                    // Evita duplicatas próximas
                    boolean alreadyExists = found.stream().anyMatch(existing ->
                            Math.hypot(existing.x() - eq.x(), existing.y() - eq.y()) < 1e-3);
                    if (!alreadyExists) {
                        found.add(eq);
                    }
                });
            }
        }
this.lastEquilibria = found;
        canvas.setEquilibria(found);
        statusLabel.setText(String.format("Busca concluída: %d ponto(s) de equilíbrio localizado(s).", found.size()));
    }

    private void applyPreset(String name) {
        if (name == null) return;
        switch (name) {
            case "Van der Pol" -> {
                fxField.setText("y");
                fyField.setText("mu * (1 - x^2) * y - x");
                paramsField.setText("mu=1.0");
                setBoundsFields(-3.0, 3.0, -3.0, 3.0);
            }
            case "Predador-Presa (Lotka-Volterra)" -> {
                fxField.setText("alpha * x - beta * x * y");
                fyField.setText("delta * x * y - gamma * y");
                paramsField.setText("alpha=1.0, beta=0.5, delta=0.5, gamma=1.0");
                setBoundsFields(0.0, 6.0, 0.0, 6.0);
            }
            case "Pêndulo Não-Linear" -> {
                fxField.setText("y");
                fyField.setText("-sin(x) - d * y");
                paramsField.setText("d=0.25");
                setBoundsFields(-7.0, 7.0, -4.0, 4.0);
            }
            case "Oscilador de Duffing" -> {
                fxField.setText("y");
                fyField.setText("x - x^3 - delta * y");
                paramsField.setText("delta=0.2");
                setBoundsFields(-2.5, 2.5, -2.5, 2.5);
            }
            case "Sistema Linear (Sela/Foco)" -> {
                fxField.setText("a * x + b * y");
                fyField.setText("c * x + d * y");
                paramsField.setText("a=0.5, b=-1.0, c=1.0, d=-0.5");
                setBoundsFields(-4.0, 4.0, -4.0, 4.0);
            }
case "Pplane Clássico (Polking)" -> {
                fxField.setText("2 * x - y + 3 * (x^2 - y^2) + 2 * x * y");
                fyField.setText("x - 3 * y - 3 * (x^2 - y^2) + 3 * x * y");
                paramsField.setText("");
                setBoundsFields(-2.5, 2.5, -2.5, 2.5);
            }
            case "FitzHugh-Nagumo (Neuronal)" -> {
                fxField.setText("c * (x - (x^3)/3 + y + z)");
                fyField.setText("-(x - a + b * y) / c");
                paramsField.setText("a=0.7, b=0.8, c=3.0, z=-0.4");
                setBoundsFields(-3.0, 3.0, -2.5, 2.5);
            }
            case "Epidemiológico SIR" -> {
                fxField.setText("mu - beta * x * y - mu * x");
                fyField.setText("beta * x * y - (gamma + mu) * y");
                paramsField.setText("beta=2.0, gamma=0.5, mu=0.1");
                setBoundsFields(0.0, 1.2, 0.0, 1.0);
            }
        }
        applySystem();
    }

    private void setBoundsFields(double xMin, double xMax, double yMin, double yMax) {
        xMinField.setText(String.valueOf(xMin));
        xMaxField.setText(String.valueOf(xMax));
        yMinField.setText(String.valueOf(yMin));
        yMaxField.setText(String.valueOf(yMax));
    }

    private BoundingBox getCurrentBounds() {
        return new BoundingBox(
                parseDouble(xMinField.getText(), -3.0),
                parseDouble(xMaxField.getText(), 3.0),
                parseDouble(yMinField.getText(), -3.0),
                parseDouble(yMaxField.getText(), 3.0)
        );
    }

    private Map<String, Double> parseParameters(String raw) {
        Map<String, Double> map = new HashMap<>();
        if (raw == null || raw.isBlank()) return map;

        String[] tokens = raw.split("[,;]");
        for (String token : tokens) {
            String[] pair = token.split("=");
            if (pair.length == 2) {
                map.put(pair[0].trim(), Double.parseDouble(pair[1].trim()));
            }
        }
        return map;
    }

    private double parseDouble(String str, double def) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return def;
        }
    }

    private void showError(String header, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }

    private String createDarkThemeStyle() {
        return "data:text/css," +
                ".root { -fx-base: #0d1117; -fx-background-color: #0d1117; }" +
                ".label { -fx-text-fill: #c9d1d9; -fx-font-family: 'Segoe UI', sans-serif; }" +
                ".text-field { -fx-background-color: #0d1117; -fx-text-fill: #c9d1d9; -fx-border-color: #30363d; -fx-border-radius: 4; -fx-font-family: 'Menlo', 'Consolas', monospace; }" +
                ".text-field:focused { -fx-border-color: #58a6ff; }" +
                ".combo-box { -fx-background-color: #21262d; -fx-border-color: #30363d; -fx-border-radius: 4; }" +
                ".combo-box .list-cell { -fx-text-fill: #c9d1d9; }" +
                ".separator *.line { -fx-border-color: #30363d; -fx-border-width: 0.5; }";
    }
private void computeTrajectory(double x0, double y0) {
        if (system == null) return;
initialPoints.add(new double[]{x0, y0});
        double tMin = parseDouble(tMinField.getText(), -10.0);
        double tMax = parseDouble(tMaxField.getText(), 20.0);

        Trajectory trajectory = integrator.integrateBidirectional(
                system, x0, y0, tMin, tMax, getCurrentBounds()
        );
        this.lastTrajectory = trajectory;
        canvas.addTrajectory(trajectory);
        statusLabel.setText(String.format("Órbita traçada a partir de (%.3f, %.3f) com %d passos.", x0, y0, trajectory.size()));
    }

private void computeSeparatrices() {
        if (system == null) {
            applySystem();
        }
        if (system == null) {
            statusLabel.setText("Defina um sistema válido antes de calcular separatrizes.");
            return;
        }

        if (this.lastEquilibria == null || this.lastEquilibria.isEmpty()) {
            scanEquilibria();
        }

        if (this.lastEquilibria == null || this.lastEquilibria.isEmpty()) {
            statusLabel.setText("Nenhum ponto de equilíbrio localizado para calcular separatrizes.");
            return;
        }

        List<EquilibriumPoint> equilibria = this.lastEquilibria;
        double tSpan = Math.abs(parseDouble(tMaxField.getText(), 20.0));
        var bounds = getCurrentBounds();
        double eps = 1e-3;
        int count = 0;

        for (var eq : equilibria) {
            if (eq.type() != EquilibriumPoint.Type.SADDLE) continue;

            double xs = eq.x();
            double ys = eq.y();
            double[] rVals = eq.realEigenvalues();
            double[][] rVecs = eq.realEigenvectors();

            if (rVals == null || rVecs == null) continue;

            for (int k = 0; k < 2; k++) {
                double lambda = rVals[k];
                double vx = rVecs[0][k];
                double vy = rVecs[1][k];
                double norm = Math.hypot(vx, vy);
                if (norm < 1e-10) continue;

                vx /= norm;
                vy /= norm;

                boolean isUnstable = (lambda > 0);
                double tTarget = isUnstable ? tSpan : -tSpan;
                boolean backward = !isUnstable;

                for (double sign : new double[]{-1.0, 1.0}) {
                    double x0 = xs + sign * eps * vx;
                    double y0 = ys + sign * eps * vy;
                    Trajectory traj = integrator.integrateOneDirection(system, x0, y0, tTarget, bounds, backward);
                    if (isUnstable) {
                        canvas.addUnstableSeparatrix(traj); // Wu -> Laranja
                    } else {
                        canvas.addStableSeparatrix(traj);   // Ws -> Verde
                    }
                    count++;
                }
            }
        }

        if (count > 0) {
            statusLabel.setText(String.format("Calculadas %d separatrizes estáveis e instáveis nos pontos de sela.", count));
        } else {
            statusLabel.setText("Nenhum ponto de sela identificado nesta janela.");
        }
    }
private void openParameterStudio() {
        Map<String, Double> params = parseParameters(paramsField.getText());
        if (parameterStudioStage == null) {
            parameterStudioStage = new ParameterStudioStage(params, updatedMap -> {
                StringBuilder sb = new StringBuilder();
                updatedMap.forEach((k, v) -> {
                    if (!sb.isEmpty()) sb.append(", ");
                    sb.append(k).append("=").append(v);
                });
                paramsField.setText(sb.toString());
                applySystem();
            });
        } else {
            parameterStudioStage.updateParameters(params);
        }
        parameterStudioStage.show();
        parameterStudioStage.toFront();
    }
private void loadPredefinedModel(PredefinedModel model) {
        if (model == null) return;

        varXField.setText(model.getVarX());
        varYField.setText(model.getVarY());
        fxField.setText(model.getExprDx());
        fyField.setText(model.getExprDy());

        xMinField.setText(String.valueOf(model.getXMin()));
        xMaxField.setText(String.valueOf(model.getXMax()));
        yMinField.setText(String.valueOf(model.getYMin()));
        yMaxField.setText(String.valueOf(model.getYMax()));
        tMaxField.setText(String.valueOf(model.getTMax()));

        if (model.getParameters().isEmpty()) {
            paramsField.setText("");
        } else {
            StringBuilder sb = new StringBuilder();
            model.getParameters().forEach((k, v) -> {
                if (sb.length() > 0) sb.append(", ");
                sb.append(k).append("=").append(v);
            });
            paramsField.setText(sb.toString());
        }

        applySystem();
    }

    private PredefinedModel captureCurrentModelSnapshot() {
        Map<String, Double> currentParams = new LinkedHashMap<>();
        String raw = paramsField.getText();
        if (raw != null && !raw.isBlank()) {
            for (String pair : raw.split("[,;]")) {
                String[] kv = pair.split("=");
                if (kv.length == 2) {
                    try {
                        currentParams.put(kv[0].trim(), Double.parseDouble(kv[1].trim()));
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        double xmin = parseDoubleSafe(xMinField.getText(), -3.0);
        double xmax = parseDoubleSafe(xMaxField.getText(), 3.0);
        double ymin = parseDoubleSafe(yMinField.getText(), -3.0);
        double ymax = parseDoubleSafe(yMaxField.getText(), 3.0);
        double tmax = parseDoubleSafe(tMaxField.getText(), 25.0);

        return new PredefinedModel(
                "Modelos do Usuário", "Modelo Atual",
                varXField.getText().trim(), varYField.getText().trim(),
                fxField.getText().trim(), fyField.getText().trim(),
                xmin, xmax, ymin, ymax, tmax,
                currentParams,
                "Capturado do plano de fase ativo."
        );
    }

    private double parseDoubleSafe(String txt, double fallback) {
        try {
            return Double.parseDouble(txt.trim());
        } catch (Exception e) {
            return fallback;
        }
    }
}
