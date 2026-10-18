package com.phaseplane.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AboutDialog {

    public static void show(Window owner) {
        Stage dialog = new Stage();
        dialog.initOwner(owner);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Sobre o PPLANEX");
        dialog.setResizable(false);

        ImageView iconView = new ImageView();
        try {
            Image appIcon = new Image(AboutDialog.class.getResourceAsStream("/icons/pplanex-icon.png"));
            iconView.setImage(appIcon);
            iconView.setFitWidth(76);
            iconView.setFitHeight(76);
            iconView.setPreserveRatio(true);
        } catch (Exception ignored) {}

        Label lblTitle = new Label("PPLANEX");
        lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #e6edf3;");

        Label lblVersion = new Label("Versão 1.0 (2026)");
        lblVersion.setStyle("-fx-font-size: 12px; -fx-text-fill: #8b949e;");

        Label lblAuthor = new Label(
            "Desenvolvido por:\n" +
            "Prof. Dr. Luiz Antonio Ribeiro de Santana\n" +
            "Departamento de Matemática (DMAT) — UFPR"
        );
        lblAuthor.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #58a6ff; -fx-text-alignment: center;");

        Label lblDesc = new Label(
            "Análise e Simulação de Planos de Fase 2D\n\n" +
            "• Inspirado na ferramenta clássica pplane de John C. Polking.\n" +
            "• Integração numérica adaptativa de EDOs via Runge-Kutta.\n" +
            "• Análise local de equilíbrios, autovalores e matriz Jacobiana.\n" +
            "• Traçado interativo de separatrizes, órbitas e isóclinas nulas.\n" +
            "• Exportação vetorial pura (SVG) e raster de alta resolução com canal alfa (PNG RGBA)."
        );
        lblDesc.setStyle("-fx-font-size: 12px; -fx-text-fill: #c9d1d9; -fx-line-spacing: 3px; -fx-text-alignment: left;");

        Button btnClose = new Button("Fechar");
        btnClose.setStyle(
            "-fx-background-color: #238636; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: bold; " +
            "-fx-padding: 7 24; " +
            "-fx-background-radius: 6; " +
            "-fx-cursor: hand;"
        );
        btnClose.setOnAction(e -> dialog.close());

        VBox layout = new VBox(12);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 24, 20, 24));
        layout.setStyle("-fx-background-color: #0d1117; -fx-border-color: #30363d; -fx-border-width: 1px;");
        layout.getChildren().addAll(
            iconView,
            lblTitle,
            lblVersion,
            lblAuthor,
            new Separator(),
            lblDesc,
            new Separator(),
            btnClose
        );

        Scene scene = new Scene(layout);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
