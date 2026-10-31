package com.phaseplane.ui;

import com.phaseplane.math.Trajectory;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TimeSeriesViewer {

    private final Stage stage = new Stage();
    private final VBox chartsBox = new VBox(15);
    private final BorderPane root = new BorderPane(chartsBox);

    public TimeSeriesViewer() {
        stage.setTitle(I18n.get("timeseries.window_title"));
try {
            var iconStream = TimeSeriesViewer.class.getResourceAsStream("/icons/pplaneX_32.png");
            if (iconStream == null) iconStream = TimeSeriesViewer.class.getResourceAsStream("/icons/pplaneX_icon.png");
            if (iconStream != null) stage.getIcons().add(new javafx.scene.image.Image(iconStream));
        } catch (Exception e) {
            // Ignora silenciosamente
        }
        chartsBox.setPadding(new Insets(12));
        root.setStyle("-fx-background-color: #0b0f14;");

        Scene scene = new Scene(root, 760, 680);

        // Estilo escuro consistente e elegante com o pplaneX
        String css = """
            .chart { -fx-padding: 5px; }
            .chart-title { -fx-text-fill: #e6edf3; -fx-font-size: 13px; -fx-font-weight: bold; }
            .axis-label { -fx-text-fill: #8b949e; -fx-font-size: 11px; }
            .axis { -fx-tick-label-fill: #8b949e; }
            .chart-plot-background { -fx-background-color: #161b22; }
            .chart-vertical-grid-lines { -fx-stroke: #21262d; }
            .chart-horizontal-grid-lines { -fx-stroke: #21262d; }
            .chart-legend { -fx-background-color: transparent; }
            .chart-legend-item { -fx-text-fill: #c9d1d9; }
        """;
        scene.getStylesheets().add("data:text/css," + css.replace("\n", " ").replace(" ", "%20"));

        stage.setScene(scene);
    }

    public void updateTrajectory(Trajectory traj) {
        if (traj == null || traj.size() == 0) {
            return;
        }

        Platform.runLater(() -> {
            double[] t = traj.t();
            double[] x = traj.x();
            double[] y = traj.y();
            int total = traj.size();

           // 1. Filtra apenas valores que explodem para o infinito (Blow-up)
            List<Integer> validIndices = new ArrayList<>();
            for (int i = 0; i < total; i++) {
                if (Double.isFinite(t[i]) && Double.isFinite(x[i]) && Double.isFinite(y[i])) {
                    if (Math.abs(x[i]) < 1e5 && Math.abs(y[i]) < 1e5) {
                        validIndices.add(i);
                    }
                }
            }
            if (validIndices.isEmpty()) {
                return;
            }

            // 2. Downsampling regular
            int maxPoints = 500;
            int step = Math.max(1, validIndices.size() / maxPoints);

            List<Integer> sampledIndices = new ArrayList<>();
            for (int i = 0; i < validIndices.size(); i += step) {
                sampledIndices.add(validIndices.get(i));
            }
            int lastValid = validIndices.get(validIndices.size() - 1);
            if (!sampledIndices.contains(lastValid)) {
                sampledIndices.add(lastValid);
            }
            sampledIndices.sort(Comparator.comparingDouble(idx -> t[idx]));

            double minT = Double.POSITIVE_INFINITY, maxT = Double.NEGATIVE_INFINITY;
            double minX = Double.POSITIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY;
            double minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY;

            XYChart.Series<Number, Number> seriesX = new XYChart.Series<>();
            seriesX.setName("x(t)");

            XYChart.Series<Number, Number> seriesY = new XYChart.Series<>();
            seriesY.setName("y(t)");

            for (int idx : sampledIndices) {
                double curT = t[idx];
                double curX = x[idx];
                double curY = y[idx];

                if (curT < minT) minT = curT;
                if (curT > maxT) maxT = curT;
                if (curX < minX) minX = curX;
                if (curX > maxX) maxX = curX;
                if (curY < minY) minY = curY;
                if (curY > maxY) maxY = curY;

                seriesX.getData().add(new XYChart.Data<>(curT, curX));
                seriesY.getData().add(new XYChart.Data<>(curT, curY));
            }

            // Margens confortáveis de 8% nos eixos
            double spanT = (maxT - minT) > 1e-4 ? (maxT - minT) * 0.08 : 1.0;
            double spanX = (maxX - minX) > 1e-4 ? (maxX - minX) * 0.08 : 0.2;
            double spanY = (maxY - minY) > 1e-4 ? (maxY - minY) * 0.08 : 0.2;

            NumberAxis tAxisX = new NumberAxis(minT - spanT, maxT + spanT, Math.max(0.1, (maxT - minT) / 8));
            tAxisX.setLabel(I18n.get("timeseries.axis_time"));
NumberAxis valAxisX = new NumberAxis(minX - spanX, maxX + spanX, Math.max(0.05, (maxX - minX) / 6));
            valAxisX.setLabel("x(t)");

            LineChart<Number, Number> chartX = new LineChart<>(tAxisX, valAxisX);
            chartX.setTitle(I18n.get("timeseries.chart_x_title"));
            chartX.setCreateSymbols(false);
            chartX.setAnimated(false);
            chartX.getData().add(seriesX);

            NumberAxis tAxisY = new NumberAxis(minT - spanT, maxT + spanT, Math.max(0.1, (maxT - minT) / 8));
            tAxisY.setLabel(I18n.get("timeseries.axis_time"));

            NumberAxis valAxisY = new NumberAxis(minY - spanY, maxY + spanY, Math.max(0.05, (maxY - minY) / 6));
            valAxisY.setLabel("y(t)");
           
            LineChart<Number, Number> chartY = new LineChart<>(tAxisY, valAxisY);
            chartY.setTitle(I18n.get("timeseries.chart_y_title"));
            chartY.setCreateSymbols(false);
            chartY.setAnimated(false);
            chartY.getData().add(seriesY);

            chartsBox.getChildren().setAll(chartX, chartY);

            // Traços das curvas
            if (seriesX.getNode() != null) {
                seriesX.getNode().setStyle("-fx-stroke: #00e5ff; -fx-stroke-width: 2.2px;");
            }
            if (seriesY.getNode() != null) {
                seriesY.getNode().setStyle("-fx-stroke: #ff9100; -fx-stroke-width: 2.2px;");
            }

            if (!stage.isShowing()) {
                stage.show();
            } else {
                stage.toFront();
            }
        });
    }

    public boolean isShowing() {
        return stage.isShowing();
    }
}
