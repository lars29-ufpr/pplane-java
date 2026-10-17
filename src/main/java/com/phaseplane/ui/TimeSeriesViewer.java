package com.phaseplane.ui;

import com.phaseplane.math.Trajectory;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TimeSeriesViewer {

    private final Stage stage = new Stage();
    private final LineChart<Number, Number> chartX;
    private final LineChart<Number, Number> chartY;

    public TimeSeriesViewer() {
        stage.setTitle("pplane - Soluções vs Tempo: x(t) e y(t)");

        // Configuração dos eixos de x(t)
        NumberAxis tAxisX = new NumberAxis();
        tAxisX.setLabel("Tempo (t)");
        NumberAxis valAxisX = new NumberAxis();
        valAxisX.setLabel("x(t)");

        chartX = new LineChart<>(tAxisX, valAxisX);
        chartX.setTitle("Evolução Temporal de x(t)");
        chartX.setCreateSymbols(false);
        chartX.setAnimated(false);

        // Configuração dos eixos de y(t)
        NumberAxis tAxisY = new NumberAxis();
        tAxisY.setLabel("Tempo (t)");
        NumberAxis valAxisY = new NumberAxis();
        valAxisY.setLabel("y(t)");

        chartY = new LineChart<>(tAxisY, valAxisY);
        chartY.setTitle("Evolução Temporal de y(t)");
        chartY.setCreateSymbols(false);
        chartY.setAnimated(false);

        VBox chartsBox = new VBox(15, chartX, chartY);
        chartsBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane(chartsBox);
        root.setStyle("-fx-background-color: #121820;");

        // CSS inline simples para tema escuro compatível
        String chartStyle = """
            .chart-title { -fx-text-fill: #e0e0e0; -fx-font-size: 13px; -fx-font-weight: bold; }
            .axis-label { -fx-text-fill: #90a4ae; -fx-font-size: 11px; }
            .axis { -fx-tick-label-fill: #b0bec5; }
            .chart-plot-background { -fx-background-color: #0b0f14; }
            .chart-vertical-grid-lines { -fx-stroke: #1c2633; }
            .chart-horizontal-grid-lines { -fx-stroke: #1c2633; }
            .default-color0.chart-series-line { -fx-stroke: #00e5ff; -fx-stroke-width: 2px; }
        """;

        Scene scene = new Scene(root, 720, 680);
        scene.getStylesheets().add("data:text/css," + chartStyle.replace("\n", " ").replace(" ", "%20"));

        stage.setScene(scene);
    }

    public void updateTrajectory(Trajectory traj) {
        chartX.getData().clear();
        chartY.getData().clear();

        if (traj == null || traj.size() == 0) {
            return;
        }

        XYChart.Series<Number, Number> seriesX = new XYChart.Series<>();
        seriesX.setName("x(t)");

        XYChart.Series<Number, Number> seriesY = new XYChart.Series<>();
        seriesY.setName("y(t)");

        double[] t = traj.t();
        double[] x = traj.x();
        double[] y = traj.y();

        for (int i = 0; i < traj.size(); i++) {
            seriesX.getData().add(new XYChart.Data<>(t[i], x[i]));
            seriesY.getData().add(new XYChart.Data<>(t[i], y[i]));
        }

        chartX.getData().add(seriesX);
        chartY.getData().add(seriesY);

if (!stage.isShowing()) {
            stage.show();
        } else {
            stage.toFront();
        }
    }

    public boolean isShowing() {
        return stage.isShowing();
    }
}
