package com.phaseplane.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;
import java.util.function.Consumer;

/**
 * Janela dedicada (Parameter Studio) para ajuste interativo e exploração 
 * de parâmetros de sistemas dinâmicos via sliders e campos numéricos.
 */
public class ParameterStudioStage extends Stage {

    private final Map<String, Double> currentParams = new LinkedHashMap<>();
    private final Consumer<Map<String, Double>> onParametersChanged;
    private final VBox slidersContainer;
    private final CheckBox liveUpdateCheck;

    public ParameterStudioStage(Map<String, Double> initialParams, Consumer<Map<String, Double>> onParametersChanged) {
        this.onParametersChanged = onParametersChanged;
        if (initialParams != null) {
            this.currentParams.putAll(initialParams);
        }

        setTitle(I18n.get("studio.window_title"));

        VBox root = new VBox(14);
        root.setPadding(new Insets(16));
        root.setStyle("-fx-background-color: #0d1117;");

        Label headerTitle = new Label(I18n.get("studio.header_title"));
        headerTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #58a6ff; -fx-font-size: 14px;");

        Label desc = new Label(I18n.get("studio.header_desc"));
        desc.setStyle("-fx-text-fill: #8b949e; -fx-font-size: 11px;");

        liveUpdateCheck = new CheckBox(I18n.get("studio.live_update"));
        liveUpdateCheck.setSelected(true);
        liveUpdateCheck.setStyle("-fx-text-fill: #c9d1d9;");

        slidersContainer = new VBox(12);
        ScrollPane scrollPane = new ScrollPane(slidersContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #0d1117; -fx-background-color: transparent; -fx-border-color: #30363d; -fx-border-radius: 4;");
        scrollPane.setPrefHeight(300);

        Button btnApplyManual = new Button(I18n.get("studio.btn_apply"));
        btnApplyManual.setMaxWidth(Double.MAX_VALUE);
        btnApplyManual.setStyle("-fx-background-color: #238636; -fx-text-fill: white; -fx-font-weight: bold;");
        btnApplyManual.setOnAction(e -> notifyChange());

        root.getChildren().addAll(headerTitle, desc, liveUpdateCheck, scrollPane, btnApplyManual);

        rebuildControls();

        Scene scene = new Scene(root, 460, 440);
        setScene(scene);
    }

    public void updateParameters(Map<String, Double> newParams) {
        currentParams.clear();
        if (newParams != null) {
            currentParams.putAll(newParams);
        }
        rebuildControls();
    }

    private void rebuildControls() {
        slidersContainer.getChildren().clear();

        if (currentParams.isEmpty()) {
            Label emptyLbl = new Label(I18n.get("studio.empty_params"));
            emptyLbl.setStyle("-fx-text-fill: #8b949e; -fx-padding: 16;");
            slidersContainer.getChildren().add(emptyLbl);
            return;
        }

        for (Map.Entry<String, Double> entry : currentParams.entrySet()) {
            String name = entry.getKey();
            double val = entry.getValue();

            VBox card = new VBox(6);
            card.setPadding(new Insets(10));
            card.setStyle("-fx-background-color: #161b22; -fx-border-color: #30363d; -fx-border-radius: 6;");

            double min = (val > 0) ? 0.0 : (val < 0 ? val * 2.5 : -5.0);
            double max = (val > 0) ? Math.max(val * 2.5, 5.0) : (val < 0 ? 0.0 : 5.0);
            if (min == max) { min -= 1.0; max += 1.0; }

            TextField minField = new TextField(String.format(Locale.US, "%.2f", min));
            TextField maxField = new TextField(String.format(Locale.US, "%.2f", max));
            minField.setPrefWidth(55);
            maxField.setPrefWidth(55);
            styleNumericField(minField);
            styleNumericField(maxField);

            Label nameLbl = new Label(name + " =");
            nameLbl.setStyle("-fx-text-fill: #58a6ff; -fx-font-weight: bold; -fx-font-size: 13px;");

            TextField valField = new TextField(String.format(Locale.US, "%.3f", val));
            valField.setPrefWidth(70);
            styleNumericField(valField);

            Slider slider = new Slider(min, max, val);
            slider.setShowTickMarks(false);
            slider.setShowTickLabels(false);
            slider.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(slider, Priority.ALWAYS);

            minField.setOnAction(e -> updateSliderBounds(slider, minField, maxField));
            maxField.setOnAction(e -> updateSliderBounds(slider, minField, maxField));

            slider.valueProperty().addListener((obs, oldVal, newVal) -> {
                double rounded = Math.round(newVal.doubleValue() * 1000.0) / 1000.0;
                valField.setText(String.format(Locale.US, "%.3f", rounded));
                currentParams.put(name, rounded);
                if (liveUpdateCheck.isSelected()) {
                    notifyChange();
                }
            });

            slider.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
                if (!isChanging && !liveUpdateCheck.isSelected()) {
                    notifyChange();
                }
            });

            valField.setOnAction(e -> {
                try {
                    double typed = Double.parseDouble(valField.getText().trim().replace(',', '.'));
                    currentParams.put(name, typed);
                    if (typed < slider.getMin()) {
                        slider.setMin(typed);
                        minField.setText(String.format(Locale.US, "%.2f", typed));
                    }
                    if (typed > slider.getMax()) {
                        slider.setMax(typed);
                        maxField.setText(String.format(Locale.US, "%.2f", typed));
                    }
                    slider.setValue(typed);
                    notifyChange();
                } catch (NumberFormatException ignored) {}
            });

            HBox headerBox = new HBox(8, nameLbl, valField);
            headerBox.setAlignment(Pos.CENTER_LEFT);

            HBox controlsBox = new HBox(8, new Label("min:"), minField, slider, maxField, new Label(":max"));
            controlsBox.setAlignment(Pos.CENTER_LEFT);
            for (var node : controlsBox.getChildren()) {
                if (node instanceof Label l) l.setStyle("-fx-text-fill: #8b949e; -fx-font-size: 10px;");
            }

            card.getChildren().addAll(headerBox, controlsBox);
            slidersContainer.getChildren().add(card);
        }
    }

    private void updateSliderBounds(Slider slider, TextField minF, TextField maxF) {
        try {
            double mi = Double.parseDouble(minF.getText().trim().replace(',', '.'));
            double ma = Double.parseDouble(maxF.getText().trim().replace(',', '.'));
            if (mi < ma) {
                slider.setMin(mi);
                slider.setMax(ma);
            }
        } catch (NumberFormatException ignored) {}
    }

    private void styleNumericField(TextField tf) {
        tf.setStyle("-fx-background-color: #0d1117; -fx-text-fill: #e6edf3; -fx-border-color: #30363d; -fx-border-radius: 3; -fx-font-size: 11px;");
    }

    private void notifyChange() {
        if (onParametersChanged != null) {
            onParametersChanged.accept(Collections.unmodifiableMap(currentParams));
        }
    }
}
