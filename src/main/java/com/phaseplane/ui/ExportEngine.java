package com.phaseplane.ui;

import com.phaseplane.math.BoundingBox;
import com.phaseplane.math.DynamicalSystem2D;
import com.phaseplane.math.EquilibriumPoint;
import com.phaseplane.math.Trajectory;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ExportEngine {

    public enum ExportTheme {
        LIGHT("Claro / LaTeX (Fundo Branco)"),
        TRANSPARENT("Transparente (Sem fundo / Slides)"),
        DARK("Escuro (Como na Tela)");

        private final String description;
        ExportTheme(String description) { this.description = description; }
        @Override public String toString() { return description; }
    }

    private static ExportTheme askTheme(Window window) {
        List<ExportTheme> choices = Arrays.asList(ExportTheme.LIGHT, ExportTheme.TRANSPARENT, ExportTheme.DARK);
        ChoiceDialog<ExportTheme> dialog = new ChoiceDialog<>(ExportTheme.LIGHT, choices);
        dialog.setTitle(I18n.get("export.theme_dialog_title"));
        dialog.setHeaderText(I18n.get("export.theme_dialog_header"));
        dialog.setContentText(I18n.get("export.theme_dialog_content"));
        dialog.initOwner(window);
        return dialog.showAndWait().orElse(null);
    }

    public static void exportPNG(PhasePlaneCanvas canvas, Window window) {
        ExportTheme theme = askTheme(window);
        if (theme == null) return;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(I18n.get("export.png_title"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(I18n.get("export.png_filter"), "*.png"));
        fileChooser.setInitialFileName(I18n.get("export.png_filename"));

        File file = fileChooser.showSaveDialog(window);
        if (file == null) return;

        try {
            // 1. Configura o canvas para o tema escolhido e redesenha
            if (theme == ExportTheme.TRANSPARENT) {
                canvas.setExportBackground(null, true);
            } else if (theme == ExportTheme.LIGHT) {
                canvas.setExportBackground(Color.WHITE, false);
            } else {
                canvas.setExportBackground(null, false);
            }
            canvas.redraw();

            // 2. Captura a imagem em alta definição com suporte nativo a canal alfa
            double scale = 2.5;
            SnapshotParameters params = new SnapshotParameters();
            params.setTransform(Transform.scale(scale, scale));
            params.setFill(theme == ExportTheme.TRANSPARENT ? Color.TRANSPARENT : (theme == ExportTheme.LIGHT ? Color.WHITE : Color.web("#0d1117")));

            WritableImage fxImage = canvas.snapshot(params, null);

            // 3. Restaura o Canvas para a interface de trabalho escura
            canvas.setExportBackground(null, false);
            canvas.redraw();

            // 4. Grava diretamente via AWT BufferedImage sem manipulação destrutiva de pixels
            int imgW = (int) fxImage.getWidth();
            int imgH = (int) fxImage.getHeight();
            BufferedImage bufferedImage = new BufferedImage(
                    imgW, imgH,
                    theme == ExportTheme.TRANSPARENT ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB
            );

            PixelReader reader = fxImage.getPixelReader();
            int[] buffer = new int[imgW];
            for (int y = 0; y < imgH; y++) {
                reader.getPixels(0, y, imgW, 1, javafx.scene.image.PixelFormat.getIntArgbInstance(), buffer, 0, imgW);
                bufferedImage.setRGB(0, y, imgW, 1, buffer, 0, imgW);
            }

            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void exportSVG(PhasePlaneCanvas canvas, Window window) {
        ExportTheme theme = askTheme(window);
        if (theme == null) return;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(I18n.get("export.svg_title"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(I18n.get("export.svg_filter"), "*.svg"));
        fileChooser.setInitialFileName(I18n.get("export.svg_filename"));

        File file = fileChooser.showSaveDialog(window);
        if (file == null) return;

        BoundingBox bounds = canvas.getBoundsBox();
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        DynamicalSystem2D system = canvas.getSystem();

        // Configuração de Paleta
        boolean isDark = (theme == ExportTheme.DARK);
        String gridColor = isDark ? "#161b22" : "#e2e8f0";
        String textColor = isDark ? "#8b949e" : "#475569";
        String axisColor = isDark ? "#38bdf8" : "#2563eb";
        String arrowColor = isDark ? "#8b949e" : "#94a3b8";
        String trColor = isDark ? "#38bdf8" : "#0284c7";
        String stabSepColor = isDark ? "#10b981" : "#059669";
        String unstabSepColor = isDark ? "#f97316" : "#ea580c";

        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            out.printf(Locale.US, "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%.1f\" height=\"%.1f\" viewBox=\"0 0 %.1f %.1f\">\n",
                    w, h, w, h);

            // Fundo
            if (theme == ExportTheme.DARK) {
                out.printf("  <rect width=\"100%%\" height=\"100%%\" fill=\"#0d1117\" />\n");
            } else if (theme == ExportTheme.LIGHT) {
                out.printf("  <rect width=\"100%%\" height=\"100%%\" fill=\"#ffffff\" />\n");
            }
            // TRANSPARENT simplesmente não imprime a tag <rect>

            // 1. Grade e Rótulos Numéricos
            int div = 10;
            for (int i = 0; i <= div; i++) {
                double wx = bounds.xMin() + i * (bounds.xMax() - bounds.xMin()) / div;
                double sx = mapX(wx, bounds, w);
                out.printf(Locale.US, "  <line x1=\"%.2f\" y1=\"0\" x2=\"%.2f\" y2=\"%.2f\" stroke=\"%s\" stroke-width=\"1.0\" />\n",
                        sx, sx, h, gridColor);

                double wy = bounds.yMin() + i * (bounds.yMax() - bounds.yMin()) / div;
                double sy = mapY(wy, bounds, h);
                out.printf(Locale.US, "  <line x1=\"0\" y1=\"%.2f\" x2=\"%.2f\" y2=\"%.2f\" stroke=\"%s\" stroke-width=\"1.0\" />\n",
                        sy, w, sy, gridColor);

                out.printf(Locale.US, "  <text x=\"%.2f\" y=\"%.2f\" fill=\"%s\" font-family=\"monospace\" font-size=\"10\">%.2f</text>\n",
                        sx + 3, h - 5, textColor, wx);
                out.printf(Locale.US, "  <text x=\"5\" y=\"%.2f\" fill=\"%s\" font-family=\"monospace\" font-size=\"10\">%.2f</text>\n",
                        sy - 3, textColor, wy);
            }

            // Eixos cartesianos principais x = 0 e y = 0
            if (bounds.xMin() <= 0 && bounds.xMax() >= 0) {
                double zx = mapX(0.0, bounds, w);
                out.printf(Locale.US, "  <line x1=\"%.2f\" y1=\"0\" x2=\"%.2f\" y2=\"%.2f\" stroke=\"%s\" stroke-width=\"1.5\" stroke-opacity=\"0.8\" />\n",
                        zx, zx, h, axisColor);
            }
            if (bounds.yMin() <= 0 && bounds.yMax() >= 0) {
                double zy = mapY(0.0, bounds, h);
                out.printf(Locale.US, "  <line x1=\"0\" y1=\"%.2f\" x2=\"%.2f\" y2=\"%.2f\" stroke=\"%s\" stroke-width=\"1.5\" stroke-opacity=\"0.8\" />\n",
                        zy, w, zy, axisColor);
            }

            // 2. Campo Vetorial (Setas)
            if (system != null) {
                int density = canvas.getFieldGridDensity();
                double dx = (bounds.xMax() - bounds.xMin()) / (density - 1);
                double dy = (bounds.yMax() - bounds.yMin()) / (density - 1);
                double cellWidth = w / density;
                double arrowLen = cellWidth * 0.42;
                double[] der = new double[2];

                for (int i = 0; i < density; i++) {
                    double wx = bounds.xMin() + i * dx;
                    for (int j = 0; j < density; j++) {
                        double wy = bounds.yMin() + j * dy;
                        system.evaluate(wx, wy, der);
                        double mag = Math.hypot(der[0], der[1]);
                        if (mag < 1e-12) continue;

                        double u = der[0] / mag;
                        double v = der[1] / mag;
                        double sx = mapX(wx, bounds, w);
                        double sy = mapY(wy, bounds, h);
                        double ex = sx + u * arrowLen;
                        double ey = sy - v * arrowLen;

                        out.printf(Locale.US, "  <line x1=\"%.2f\" y1=\"%.2f\" x2=\"%.2f\" y2=\"%.2f\" stroke=\"%s\" stroke-width=\"1.2\" stroke-opacity=\"0.6\" stroke-linecap=\"round\" />\n",
                                sx, sy, ex, ey, arrowColor);

                        double angle = Math.atan2(ey - sy, ex - sx);
                        double headSize = 4.0;
                        double x1 = ex - headSize * Math.cos(angle - Math.PI / 6);
                        double y1 = ey - headSize * Math.sin(angle - Math.PI / 6);
                        double x2 = ex - headSize * Math.cos(angle + Math.PI / 6);
                        double y2 = ey - headSize * Math.sin(angle + Math.PI / 6);

                        out.printf(Locale.US, "  <line x1=\"%.2f\" y1=\"%.2f\" x2=\"%.2f\" y2=\"%.2f\" stroke=\"%s\" stroke-width=\"1.2\" stroke-opacity=\"0.6\" />\n",
                                ex, ey, x1, y1, arrowColor);
                        out.printf(Locale.US, "  <line x1=\"%.2f\" y1=\"%.2f\" x2=\"%.2f\" y2=\"%.2f\" stroke=\"%s\" stroke-width=\"1.2\" stroke-opacity=\"0.6\" />\n",
                                ex, ey, x2, y2, arrowColor);
                    }
                }
            }

            // 3. Isóclinas Nulas
            if (canvas.isShowNullclines() && system != null) {
                int cols = 150;
                int rows = 150;
                double[][] fGrid = new double[cols + 1][rows + 1];
                double[][] gGrid = new double[cols + 1][rows + 1];
                double dxi = (bounds.xMax() - bounds.xMin()) / cols;
                double dyi = (bounds.yMax() - bounds.yMin()) / rows;

                for (int i = 0; i <= cols; i++) {
                    double x = bounds.xMin() + i * dxi;
                    for (int j = 0; j <= rows; j++) {
                        double y = bounds.yMin() + j * dyi;
                        try {
                            double[] d = system.evaluate(x, y);
                            fGrid[i][j] = (d[0] == 0.0) ? 1e-12 : d[0];
                            gGrid[i][j] = (d[1] == 0.0) ? 1e-12 : d[1];
                        } catch (Exception e) {
                            fGrid[i][j] = Double.NaN;
                            gGrid[i][j] = Double.NaN;
                        }
                    }
                }

                String nullX = isDark ? "#ff5252" : "#dc2626";
                String nullY = isDark ? "#69f0ae" : "#16a34a";
                writeContour(out, fGrid, bounds, w, h, cols, rows, nullX, 1.4);
                writeContour(out, gGrid, bounds, w, h, cols, rows, nullY, 1.4);
            }

            // 4. Trajetórias Regulares
            for (Trajectory tr : canvas.getTrajectories()) {
                writePath(out, tr, bounds, w, h, trColor, 1.8, 0.9);
            }

            // 5. Separatrizes
            for (Trajectory tr : canvas.getStableSeparatrices()) {
                writePath(out, tr, bounds, w, h, stabSepColor, 2.2, 1.0);
            }
            for (Trajectory tr : canvas.getUnstableSeparatrices()) {
                writePath(out, tr, bounds, w, h, unstabSepColor, 2.2, 1.0);
            }

            // 6. Equilíbrios
            for (EquilibriumPoint eq : canvas.getEquilibria()) {
                double sx = mapX(eq.x(), bounds, w);
                double sy = mapY(eq.y(), bounds, h);
                String eqFill = isDark ? "#f59e0b" : "#d97706";
                String eqBorder = isDark ? "#ffffff" : "#0f172a";
                String labelFill = isDark ? "#f1f5f9" : "#0f172a";

                out.printf(Locale.US, "  <circle cx=\"%.2f\" cy=\"%.2f\" r=\"5.5\" fill=\"%s\" stroke=\"%s\" stroke-width=\"1.5\" />\n",
                        sx, sy, eqFill, eqBorder);
                String label = eq.type() != null ? eq.type().name() : "Eq";
                out.printf(Locale.US, "  <text x=\"%.2f\" y=\"%.2f\" fill=\"%s\" font-family=\"monospace\" font-size=\"11\">%s</text>\n",
                        sx + 8, sy - 8, labelFill, label);
            }

            out.println("</svg>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeContour(PrintWriter out, double[][] grid, BoundingBox b, double w, double h,
                                     int cols, int rows, String color, double strokeWidth) {
        double dxi = (b.xMax() - b.xMin()) / cols;
        double dyi = (b.yMax() - b.yMin()) / rows;

        for (int i = 0; i < cols; i++) {
            double x0 = b.xMin() + i * dxi;
            double x1 = x0 + dxi;
            for (int j = 0; j < rows; j++) {
                double y0 = b.yMin() + j * dyi;
                double y1 = y0 + dyi;

                double v00 = grid[i][j];
                double v10 = grid[i + 1][j];
                double v11 = grid[i + 1][j + 1];
                double v01 = grid[i][j + 1];

                if (Double.isNaN(v00) || Double.isNaN(v10) || Double.isNaN(v11) || Double.isNaN(v01)) continue;

                int mask = 0;
                if (v00 > 0) mask |= 1;
                if (v10 > 0) mask |= 2;
                if (v11 > 0) mask |= 4;
                if (v01 > 0) mask |= 8;
                if (mask == 0 || mask == 15) continue;

                double xBottom = x0 + (-v00) / (v10 - v00) * dxi;
                double xTop    = x0 + (-v01) / (v11 - v01) * dxi;
                double yLeft   = y0 + (-v00) / (v01 - v00) * dyi;
                double yRight  = y0 + (-v10) / (v11 - v10) * dyi;

                switch (mask) {
                    case 1, 14 -> outSegment(out, b, w, h, color, strokeWidth, x0, yLeft, xBottom, y0);
                    case 2, 13 -> outSegment(out, b, w, h, color, strokeWidth, xBottom, y0, x1, yRight);
                    case 4, 11 -> outSegment(out, b, w, h, color, strokeWidth, xTop, y1, x1, yRight);
                    case 8, 7  -> outSegment(out, b, w, h, color, strokeWidth, x0, yLeft, xTop, y1);
                    case 3, 12 -> outSegment(out, b, w, h, color, strokeWidth, x0, yLeft, x1, yRight);
                    case 6, 9  -> outSegment(out, b, w, h, color, strokeWidth, xBottom, y0, xTop, y1);
                    case 5 -> {
                        outSegment(out, b, w, h, color, strokeWidth, x0, yLeft, xTop, y1);
                        outSegment(out, b, w, h, color, strokeWidth, xBottom, y0, x1, yRight);
                    }
                    case 10 -> {
                        outSegment(out, b, w, h, color, strokeWidth, x0, yLeft, xBottom, y0);
                        outSegment(out, b, w, h, color, strokeWidth, xTop, y1, x1, yRight);
                    }
                }
            }
        }
    }

    private static void outSegment(PrintWriter out, BoundingBox b, double w, double h,
                                   String color, double strokeWidth, double x1, double y1, double x2, double y2) {
        out.printf(Locale.US, "  <line x1=\"%.2f\" y1=\"%.2f\" x2=\"%.2f\" y2=\"%.2f\" stroke=\"%s\" stroke-width=\"%.1f\" stroke-linecap=\"round\" />\n",
                mapX(x1, b, w), mapY(y1, b, h), mapX(x2, b, w), mapY(y2, b, h), color, strokeWidth);
    }

    private static void writePath(PrintWriter out, Trajectory tr, BoundingBox b, double w, double h,
                                  String color, double strokeWidth, double opacity) {
        if (tr == null || tr.size() < 2) return;
        double[] x = tr.x();
        double[] y = tr.y();

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Locale.US, "M %.2f %.2f", mapX(x[0], b, w), mapY(y[0], b, h)));
        for (int i = 1; i < tr.size(); i++) {
            sb.append(String.format(Locale.US, " L %.2f %.2f", mapX(x[i], b, w), mapY(y[i], b, h)));
        }

        out.printf(Locale.US, "  <path d=\"%s\" fill=\"none\" stroke=\"%s\" stroke-width=\"%.1f\" stroke-opacity=\"%.2f\" stroke-linecap=\"round\" stroke-linejoin=\"round\" />\n",
                sb.toString(), color, strokeWidth, opacity);
    }

    private static double mapX(double wx, BoundingBox b, double width) {
        return (wx - b.xMin()) / (b.xMax() - b.xMin()) * width;
    }

    private static double mapY(double wy, BoundingBox b, double height) {
        return (b.yMax() - wy) / (b.yMax() - b.yMin()) * height;
    }
}
