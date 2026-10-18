package com.phaseplane.ui;

import com.phaseplane.math.BoundingBox;
import com.phaseplane.math.DynamicalSystem2D;
import com.phaseplane.math.EquilibriumPoint;
import com.phaseplane.math.Trajectory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class PhasePlaneCanvas extends Pane {

    // Paleta de cores escura (Dark Theme)
private Color exportBgColor = null;
    private boolean transparentExport = false;

    public void setExportBackground(Color color, boolean transparent) {
        this.exportBgColor = color;
        this.transparentExport = transparent;
    }    
private static final Color BG_COLOR = Color.web("#0d1117");
    private static final Color GRID_COLOR = Color.web("#1e293b");
    private static final Color AXIS_COLOR = Color.web("#38bdf8", 0.65);
    private static final Color TEXT_COLOR = Color.web("#94a3b8");
    private static final Color ARROW_COLOR = Color.web("#64748b", 0.35);

    private final Canvas canvas;
    private BoundingBox bounds;
    private DynamicalSystem2D system;

    private final List<Trajectory> trajectories = new ArrayList<>();
private final List<Trajectory> stableSeparatrices = new ArrayList<>();
    private final List<Trajectory> unstableSeparatrices = new ArrayList<>();

    private static final Color STABLE_COLOR = Color.web("#10b981");    // Verde esmeralda (Ws)
    private static final Color UNSTABLE_COLOR = Color.web("#f97316");  // Laranja vibrante (Wu)
    private final List<EquilibriumPoint> equilibria = new ArrayList<>();
    
    // Callback disparado ao clicar no plano (passa x0, y0)
    private BiConsumer<Double, Double> onPointClicked;
    private BiConsumer<Double, Double> onPointerMoved;

    private int fieldGridDensity = 26;
private boolean showNullclines = true;
    public PhasePlaneCanvas(BoundingBox initialBounds) {
        this.bounds = initialBounds;
        this.canvas = new Canvas();

        getChildren().add(canvas);

        // Canvas redimensionável adaptado ao container pai
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        widthProperty().addListener((obs, oldVal, newVal) -> redraw());
        heightProperty().addListener((obs, oldVal, newVal) -> redraw());

        canvas.setOnMouseClicked(event -> {
            if (onPointClicked != null) {
                double worldX = screenToWorldX(event.getX());
                double worldY = screenToWorldY(event.getY());
                onPointClicked.accept(worldX, worldY);
            }
        });

        canvas.setOnMouseMoved(event -> {
            if (onPointerMoved != null) {
                double worldX = screenToWorldX(event.getX());
                double worldY = screenToWorldY(event.getY());
                onPointerMoved.accept(worldX, worldY);
            }
        });
    }
public void setShowNullclines(boolean show) {
        this.showNullclines = show;
        redraw();
    }

    public boolean isShowNullclines() {
        return showNullclines;
    }
    public void setSystem(DynamicalSystem2D system) {
        this.system = system;
        redraw();
    }

    public void setBounds(BoundingBox bounds) {
        this.bounds = bounds;
        redraw();
    }

    public void setFieldGridDensity(int density) {
        this.fieldGridDensity = density;
        redraw();
    }

    public void setOnPointClicked(BiConsumer<Double, Double> callback) {
        this.onPointClicked = callback;
    }

    public void setOnPointerMoved(BiConsumer<Double, Double> callback) {
        this.onPointerMoved = callback;
    }

    public void addTrajectory(Trajectory traj) {
        this.trajectories.add(traj);
        redraw();
    }

    public void clearTrajectories() {
        this.trajectories.clear();
this.stableSeparatrices.clear();
        this.unstableSeparatrices.clear();
        redraw();
    }
public void addStableSeparatrix(Trajectory traj) {
        this.stableSeparatrices.add(traj);
        redraw();
    }

    public void addUnstableSeparatrix(Trajectory traj) {
        this.unstableSeparatrices.add(traj);
        redraw();
    }

    public void clearSeparatrices() {
        this.stableSeparatrices.clear();
        this.unstableSeparatrices.clear();
        redraw();
    }
    public void setEquilibria(List<EquilibriumPoint> eqList) {
        this.equilibria.clear();
        this.equilibria.addAll(eqList);
        redraw();
    }

    public void clearEquilibria() {
        this.equilibria.clear();
        redraw();
    }

    // ==========================================
    // RENDERIZAÇÃO
    // ==========================================
@Override
    protected void layoutChildren() {
        super.layoutChildren();
        redraw();
    }
    public void redraw() {
        double w = getWidth();
        double h = getHeight();
        if (w <= 0 || h <= 0) return;

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Fundo
     // Fundo
        if (transparentExport) {
            gc.clearRect(0, 0, w, h);
        } else {
            gc.setFill(exportBgColor != null ? exportBgColor : BG_COLOR);
            gc.fillRect(0, 0, w, h);
        }
        drawGridAndAxes(gc, w, h);

        if (system != null) {
            drawVectorField(gc, w, h);
if (showNullclines) {
               drawNullclines(gc, w, h);
           }
        }

        drawTrajectories(gc);
drawSeparatrices(gc);        
drawEquilibria(gc);
    }

    private void drawGridAndAxes(GraphicsContext gc, double w, double h) {
        gc.setLineWidth(1.0);
        gc.setFont(Font.font("Menlo, Consolas, sans-serif", 10));

        // Subdivisão da grade (10 divisões em cada eixo)
        int div = 10;
        gc.setStroke(GRID_COLOR);

        for (int i = 0; i <= div; i++) {
            double wx = bounds.xMin() + i * (bounds.xMax() - bounds.xMin()) / div;
            double sx = worldToScreenX(wx);
            gc.strokeLine(sx, 0, sx, h);

            double wy = bounds.yMin() + i * (bounds.yMax() - bounds.yMin()) / div;
            double sy = worldToScreenY(wy);
            gc.strokeLine(0, sy, w, sy);

            // Rótulos numéricos nos eixos
            gc.setFill(TEXT_COLOR);
            gc.fillText(String.format("%.2f", wx), sx + 3, h - 5);
            gc.fillText(String.format("%.2f", wy), 5, sy - 3);
        }

        // Eixos x = 0 e y = 0 destacados
        gc.setStroke(AXIS_COLOR);
        gc.setLineWidth(1.5);
        if (bounds.xMin() <= 0 && bounds.xMax() >= 0) {
            double zeroX = worldToScreenX(0);
            gc.strokeLine(zeroX, 0, zeroX, h);
        }
        if (bounds.yMin() <= 0 && bounds.yMax() >= 0) {
            double zeroY = worldToScreenY(0);
            gc.strokeLine(0, zeroY, w, zeroY);
        }
    }

    private void drawVectorField(GraphicsContext gc, double w, double h) {
        double dx = (bounds.xMax() - bounds.xMin()) / (fieldGridDensity - 1);
        double dy = (bounds.yMax() - bounds.yMin()) / (fieldGridDensity - 1);

        double cellWidth = w / fieldGridDensity;
        double arrowLen = cellWidth * 0.42;

        gc.setStroke(ARROW_COLOR);
        gc.setFill(ARROW_COLOR);
        gc.setLineWidth(1.2);
        gc.setLineCap(StrokeLineCap.ROUND);

        double[] der = new double[2];

        for (int i = 0; i < fieldGridDensity; i++) {
            double wx = bounds.xMin() + i * dx;
            for (int j = 0; j < fieldGridDensity; j++) {
                double wy = bounds.yMin() + j * dy;

                system.evaluate(wx, wy, der);
                double magnitude = Math.hypot(der[0], der[1]);
                if (magnitude < 1e-12) continue;

                // Vetor normalizado
                double u = der[0] / magnitude;
                double v = der[1] / magnitude;

                double sx = worldToScreenX(wx);
                double sy = worldToScreenY(wy);

                double ex = sx + u * arrowLen;
                double ey = sy - v * arrowLen; // v invertido para espaço de tela

                // Traço da haste
                gc.strokeLine(sx, sy, ex, ey);

                // Cabeça da seta
                double angle = Math.atan2(ey - sy, ex - sx);
                double headSize = 4.0;
                double x1 = ex - headSize * Math.cos(angle - Math.PI / 6);
                double y1 = ey - headSize * Math.sin(angle - Math.PI / 6);
                double x2 = ex - headSize * Math.cos(angle + Math.PI / 6);
                double y2 = ey - headSize * Math.sin(angle + Math.PI / 6);

                gc.strokeLine(ex, ey, x1, y1);
                gc.strokeLine(ex, ey, x2, y2);
            }
        }
    }

    private void drawTrajectories(GraphicsContext gc) {
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.ROUND);

        for (Trajectory traj : trajectories) {
            int n = traj.size();
            if (n < 2) continue;

            double sxStart = worldToScreenX(traj.x()[0]);
            double syStart = worldToScreenY(traj.y()[0]);
            double sxEnd = worldToScreenX(traj.x()[n - 1]);
            double syEnd = worldToScreenY(traj.y()[n - 1]);

            // Gradiente ciano/azul elétrico luminoso
            LinearGradient gradient = new LinearGradient(
                    sxStart, syStart, sxEnd, syEnd, false, CycleMethod.NO_CYCLE,
                    new Stop(0.0, Color.web("#00e5ff")),
                    new Stop(1.0, Color.web("#0077ff"))
            );

            // Efeito suave de rastro
            gc.setStroke(gradient);
            gc.setLineWidth(2.2);

            gc.beginPath();
            gc.moveTo(sxStart, syStart);
            for (int i = 1; i < n; i++) {
                gc.lineTo(worldToScreenX(traj.x()[i]), worldToScreenY(traj.y()[i]));
            }
            gc.stroke();

            // Ponto de partida (condição inicial)
            gc.setFill(Color.web("#ffffff"));
            gc.fillOval(sxStart - 3.5, syStart - 3.5, 7.0, 7.0);
            gc.setStroke(Color.web("#00e5ff"));
            gc.setLineWidth(1.5);
            gc.strokeOval(sxStart - 5.5, syStart - 5.5, 11.0, 11.0);
        }
    }

private void drawSeparatrices(GraphicsContext gc) {
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.ROUND);

        // 1. Variedades Estáveis (Ws)
        gc.setStroke(STABLE_COLOR);
        gc.setLineWidth(2.2);
        for (Trajectory traj : stableSeparatrices) {
            renderTrajectoryPath(gc, traj);
        }

        // 2. Variedades Instáveis (Wu)
        gc.setStroke(UNSTABLE_COLOR);
        gc.setLineWidth(2.2);
        for (Trajectory traj : unstableSeparatrices) {
            renderTrajectoryPath(gc, traj);
        }
        // Restaura espessura padrão para não afetar outros desenhos
        gc.setLineWidth(1.0);
    }
    private void renderTrajectoryPath(GraphicsContext gc, Trajectory traj) {
        int n = traj.size();
        if (n < 2) return;
        gc.beginPath();
        gc.moveTo(worldToScreenX(traj.x()[0]), worldToScreenY(traj.y()[0]));
        for (int i = 1; i < n; i++) {
            gc.lineTo(worldToScreenX(traj.x()[i]), worldToScreenY(traj.y()[i]));
        }
        gc.stroke();
    }

    private void drawEquilibria(GraphicsContext gc) {
        for (EquilibriumPoint eq : equilibria) {
            double sx = worldToScreenX(eq.x());
            double sy = worldToScreenY(eq.y());

            Color pointColor = switch (eq.stability()) {
                case STABLE -> Color.web("#00e676");           // Verde esmeralda brilhante
                case UNSTABLE -> (eq.type() == EquilibriumPoint.Type.SADDLE)
                        ? Color.web("#ff1744")                // Vermelho carmim para Sela
                        : Color.web("#ff9100");               // Âmbar/Laranja para nós/espirais instáveis
                case NEUTRALLY_STABLE -> Color.web("#d500f9"); // Magenta/Roxo para centros
                case UNKNOWN -> Color.web("#ffd600");
            };

            // Halo luminoso
            gc.setFill(pointColor.deriveColor(0, 1, 1, 0.25));
            gc.fillOval(sx - 11, sy - 11, 22, 22);

            // Núcleo
            gc.setFill(pointColor);
            gc.fillOval(sx - 5, sy - 5, 10, 10);
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(1.2);
            gc.strokeOval(sx - 5, sy - 5, 10, 10);
// Rótulo descritivo em Português
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font("sans-serif", 11));
            String desc = eq.classificacaoFormatada();
            gc.fillText(desc, sx + 9, sy - 8);
        }
    }

    // ==========================================
    // CONVERSÕES DE COORDENADAS
    // ==========================================

    public double worldToScreenX(double wx) {
        return (wx - bounds.xMin()) / (bounds.xMax() - bounds.xMin()) * getWidth();
    }

    public double worldToScreenY(double wy) {
        return getHeight() - (wy - bounds.yMin()) / (bounds.yMax() - bounds.yMin()) * getHeight();
    }

    public double screenToWorldX(double sx) {
        return bounds.xMin() + (sx / getWidth()) * (bounds.xMax() - bounds.xMin());
    }

    public double screenToWorldY(double sy) {
        return bounds.yMin() + ((getHeight() - sy) / getHeight()) * (bounds.yMax() - bounds.yMin());
    }

// ==========================================
    // ISÓCLINAS NULAS (MARCHING SQUARES)
    // ==========================================

    private void drawNullclines(GraphicsContext gc, double w, double h) {
        if (system == null) return;

        int cols = 150;
        int rows = 150;

        double[][] fGrid = new double[cols + 1][rows + 1];
        double[][] gGrid = new double[cols + 1][rows + 1];

        double dx = (bounds.xMax() - bounds.xMin()) / cols;
        double dy = (bounds.yMax() - bounds.yMin()) / rows;

        for (int i = 0; i <= cols; i++) {
            double x = bounds.xMin() + i * dx;
            for (int j = 0; j <= rows; j++) {
                double y = bounds.yMin() + j * dy;
                try {
double[] deriv = system.evaluate(x, y);
                    double f = deriv[0];
                    double g = deriv[1];
                    fGrid[i][j] = (f == 0.0) ? 1e-12 : f;
                    gGrid[i][j] = (g == 0.0) ? 1e-12 : g;
                } catch (Exception e) {
                    fGrid[i][j] = Double.NaN;
                    gGrid[i][j] = Double.NaN;
                }
            }
        }

        gc.save();

        // x-nullcline: dx/dt = 0 em vermelho (#ff5252)
        gc.setStroke(Color.web("#ff5252"));
        gc.setLineWidth(2.0);
        renderContour(gc, fGrid, cols, rows, dx, dy);

        // y-nullcline: dy/dt = 0 em verde (#69f0ae)
        gc.setStroke(Color.web("#69f0ae"));
        gc.setLineWidth(2.0);
        renderContour(gc, gGrid, cols, rows, dx, dy);

        gc.restore();
    }

    private void renderContour(GraphicsContext gc, double[][] grid, int cols, int rows, double dx, double dy) {
        for (int i = 0; i < cols; i++) {
            double x0 = bounds.xMin() + i * dx;
            double x1 = x0 + dx;

            for (int j = 0; j < rows; j++) {
                double y0 = bounds.yMin() + j * dy;
                double y1 = y0 + dy;

                double v00 = grid[i][j];
                double v10 = grid[i + 1][j];
                double v11 = grid[i + 1][j + 1];
                double v01 = grid[i][j + 1];

                if (Double.isNaN(v00) || Double.isNaN(v10) || Double.isNaN(v11) || Double.isNaN(v01)) {
                    continue;
                }

                int cellIndex = 0;
                if (v00 > 0) cellIndex |= 1;
                if (v10 > 0) cellIndex |= 2;
                if (v11 > 0) cellIndex |= 4;
                if (v01 > 0) cellIndex |= 8;

                if (cellIndex == 0 || cellIndex == 15) continue;

                double xBottom = x0 + (-v00 / (v10 - v00)) * dx;
                double yRight  = y0 + (-v10 / (v11 - v10)) * dy;
                double xTop    = x0 + (-v01 / (v11 - v01)) * dx;
                double yLeft   = y0 + (-v00 / (v01 - v00)) * dy;

                switch (cellIndex) {
                    case 1:
                    case 14:
                        strokeSegment(gc, x0, yLeft, xBottom, y0);
                        break;
                    case 2:
                    case 13:
                        strokeSegment(gc, xBottom, y0, x1, yRight);
                        break;
                    case 3:
                    case 12:
                        strokeSegment(gc, x0, yLeft, x1, yRight);
                        break;
                    case 4:
                    case 11:
                        strokeSegment(gc, xTop, y1, x1, yRight);
                        break;
                    case 5:
                        strokeSegment(gc, x0, yLeft, xTop, y1);
                        strokeSegment(gc, xBottom, y0, x1, yRight);
                        break;
                    case 6:
                    case 9:
                        strokeSegment(gc, xBottom, y0, xTop, y1);
                        break;
                    case 7:
                    case 8:
                        strokeSegment(gc, x0, yLeft, xTop, y1);
                        break;
                    case 10:
                        strokeSegment(gc, x0, yLeft, xBottom, y0);
                        strokeSegment(gc, xTop, y1, x1, yRight);
                        break;
                }
            }
        }
    }

    private void strokeSegment(GraphicsContext gc, double wx1, double wy1, double wx2, double wy2) {
        gc.strokeLine(worldToScreenX(wx1), worldToScreenY(wy1), worldToScreenX(wx2), worldToScreenY(wy2));
    }
    public BoundingBox getBoundsBox() {
        return bounds;
    }

    public List<Trajectory> getTrajectories() {
        return trajectories;
    }

    public List<Trajectory> getStableSeparatrices() {
        return stableSeparatrices;
    }

    public List<Trajectory> getUnstableSeparatrices() {
        return unstableSeparatrices;
    }

    public List<EquilibriumPoint> getEquilibria() {
        return equilibria;
    }
public DynamicalSystem2D getSystem() {
        return system;
    }

    public int getFieldGridDensity() {
        return fieldGridDensity;
    }

}
