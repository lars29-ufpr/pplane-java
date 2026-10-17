package com.phaseplane.ui;

import com.phaseplane.math.EquilibriumPoint;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Locale;
import java.util.function.Consumer;

/**
 * Janela modal analítica para inspeção minuciosa de pontos de equilíbrio.
 * Apresenta coordenadas, classificação qualitativa, matriz Jacobiana formatada,
 * invariantes (Traço, Det, Discriminante) e autovalores/autovetores.
 */
public class EquilibriumDetailDialog extends Stage {

    public EquilibriumDetailDialog(Window owner, EquilibriumPoint eq, Consumer<EquilibriumPoint> onTraceSeparatrices) {
        initOwner(owner);
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Análise de Ponto Crítico");
        setResizable(false);

        VBox root = new VBox(14);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #0d1117;");
        root.setPrefWidth(540);

        // ================= 1. CABEÇALHO COM BADGE =================
        HBox header = new HBox(12);
        header.setAlignment(Pos.CENTER_LEFT);

        Label titleCoord = new Label(String.format(Locale.US, "P* = (%.5f, %.5f)", eq.x(), eq.y()));
        titleCoord.setFont(Font.font("Monospaced", FontWeight.BOLD, 17));
        titleCoord.setTextFill(Color.web("#f0f6fc"));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label badge = createBadge(eq);

        header.getChildren().addAll(titleCoord, spacer, badge);

        // ================= 2. CARTÃO: JACOBIANA =================
        VBox jacobianCard = createCard("MATRIZ JACOBIANA  J(P*)");
        double[][] J = eq.jacobian();

        HBox matrixBox = new HBox(8);
        matrixBox.setAlignment(Pos.CENTER);

        Label leftBracket = new Label("[");
        leftBracket.setFont(Font.font("Serif", FontWeight.LIGHT, 38));
        leftBracket.setTextFill(Color.web("#8b949e"));

        VBox rows = new VBox(4);
        rows.setAlignment(Pos.CENTER);
        Label row1 = new Label(String.format(Locale.US, "%+10.5f   %+10.5f", J[0][0], J[0][1]));
        Label row2 = new Label(String.format(Locale.US, "%+10.5f   %+10.5f", J[1][0], J[1][1]));
        row1.setFont(Font.font("Monospaced", FontWeight.NORMAL, 13));
        row2.setFont(Font.font("Monospaced", FontWeight.NORMAL, 13));
        row1.setTextFill(Color.web("#58a6ff"));
        row2.setTextFill(Color.web("#58a6ff"));
        rows.getChildren().addAll(row1, row2);

        Label rightBracket = new Label("]");
        rightBracket.setFont(Font.font("Serif", FontWeight.LIGHT, 38));
        rightBracket.setTextFill(Color.web("#8b949e"));

        matrixBox.getChildren().addAll(leftBracket, rows, rightBracket);
        jacobianCard.getChildren().add(matrixBox);

        // ================= 3. CARTÃO: INVARIANTES =================
        VBox invariantsCard = createCard("INVARIANTES DA MATRIZ");
        double trace = J[0][0] + J[1][1];
        double det = J[0][0] * J[1][1] - J[0][1] * J[1][0];
        double delta = trace * trace - 4.0 * det;

        GridPane gridInv = new GridPane();
        gridInv.setHgap(16);
        gridInv.setVgap(6);
        gridInv.setAlignment(Pos.CENTER);

        addGridRow(gridInv, 0, "Traço (τ):", String.format(Locale.US, "%.5f", trace));
        addGridRow(gridInv, 1, "Determinante (Δ):", String.format(Locale.US, "%.5f", det));
        addGridRow(gridInv, 2, "Discriminante (δ):", String.format(Locale.US, "%.5f (%s)", delta, delta >= 0 ? "δ ≥ 0" : "δ < 0"));

        invariantsCard.getChildren().add(gridInv);

        // ================= 4. CARTÃO: ESPECTRO =================
        VBox spectralCard = createCard("ESPECTRO E AUTOVETORES");
        VBox spectralContent = new VBox(6);
        spectralContent.setAlignment(Pos.CENTER_LEFT);

        Label eigValLabel = new Label("Autovalores:  " + eq.autovaloresFormatados());
        eigValLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 12));
        eigValLabel.setTextFill(Color.web("#7ee787"));
        spectralContent.getChildren().add(eigValLabel);

        double[][] evec = eq.realEigenvectors();
        if (delta >= 0 && !Double.isNaN(evec[0][0])) {
            Label v1Label = new Label(String.format(Locale.US, "v₁ (λ₁) = [%+.4f, %+.4f]ᵀ", evec[0][0], evec[1][0]));
            Label v2Label = new Label(String.format(Locale.US, "v₂ (λ₂) = [%+.4f, %+.4f]ᵀ", evec[0][1], evec[1][1]));
            v1Label.setFont(Font.font("Monospaced", 11));
            v2Label.setFont(Font.font("Monospaced", 11));
            v1Label.setTextFill(Color.web("#c9d1d9"));
            v2Label.setTextFill(Color.web("#c9d1d9"));
            spectralContent.getChildren().addAll(v1Label, v2Label);
        } else {
            Label complexNote = new Label("Autovetores complexos conjugados (órbita em rotação).");
            complexNote.setFont(Font.font(11));
            complexNote.setTextFill(Color.web("#8b949e"));
            spectralContent.getChildren().add(complexNote);
        }

        spectralCard.getChildren().add(spectralContent);

        // ================= 5. RODAPÉ COM AÇÕES =================
        HBox actions = new HBox(10);
        actions.setAlignment(Pos.CENTER_RIGHT);

        if (eq.type() == EquilibriumPoint.Type.SADDLE && onTraceSeparatrices != null) {
            Button btnSep = new Button("⤹ Traçar Separatrizes");
            btnSep.setStyle("-fx-background-color: #238636; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 6; -fx-padding: 6 12;");
            btnSep.setOnAction(e -> {
                close();
                onTraceSeparatrices.accept(eq);
            });
            actions.getChildren().add(btnSep);
        }

        Button btnClose = new Button("Fechar");
        btnClose.setStyle("-fx-background-color: #21262d; -fx-text-fill: #c9d1d9; -fx-cursor: hand; -fx-background-radius: 6; -fx-padding: 6 14;");
        btnClose.setOnAction(e -> close());
        actions.getChildren().add(btnClose);

        root.getChildren().addAll(header, new Separator(), jacobianCard, invariantsCard, spectralCard, actions);

        Scene scene = new Scene(root);
        setScene(scene);
    }

    private VBox createCard(String title) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(10, 14, 10, 14));
        card.setStyle("-fx-background-color: #161b22; -fx-border-color: #30363d; -fx-border-radius: 6; -fx-background-radius: 6;");

        Label cardTitle = new Label(title);
        cardTitle.setFont(Font.font("System", FontWeight.BOLD, 10));
        cardTitle.setTextFill(Color.web("#8b949e"));

        card.getChildren().add(cardTitle);
        return card;
    }

    private void addGridRow(GridPane grid, int row, String name, String val) {
        Label nameLbl = new Label(name);
        nameLbl.setFont(Font.font("System", FontWeight.NORMAL, 12));
        nameLbl.setTextFill(Color.web("#8b949e"));

        Label valLbl = new Label(val);
        valLbl.setFont(Font.font("Monospaced", FontWeight.BOLD, 12));
        valLbl.setTextFill(Color.web("#f0f6fc"));

        grid.add(nameLbl, 0, row);
        grid.add(valLbl, 1, row);
    }

    private Label createBadge(EquilibriumPoint eq) {
        Label badge = new Label(eq.classificacaoFormatada());
        badge.setFont(Font.font("System", FontWeight.BOLD, 11));
        badge.setPadding(new Insets(4, 10, 4, 10));

        String bg;
        String fg;

        switch (eq.type()) {
            case SADDLE -> { bg = "rgba(248, 81, 73, 0.15)"; fg = "#ff7b72"; }
            case NODE -> {
                if (eq.stability() == EquilibriumPoint.Stability.STABLE) {
                    bg = "rgba(46, 160, 67, 0.15)"; fg = "#3fb950";
                } else {
                    bg = "rgba(210, 153, 34, 0.15)"; fg = "#d29922";
                }
            }
            case SPIRAL -> {
                if (eq.stability() == EquilibriumPoint.Stability.STABLE) {
                    bg = "rgba(56, 189, 248, 0.15)"; fg = "#38bdf8";
                } else {
                    bg = "rgba(219, 97, 162, 0.15)"; fg = "#f472b6";
                }
            }
            case CENTER -> { bg = "rgba(88, 166, 255, 0.15)"; fg = "#58a6ff"; }
            default -> { bg = "rgba(139, 148, 158, 0.15)"; fg = "#8b949e"; }
        }

        badge.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s; -fx-background-radius: 12; -fx-border-color: %s; -fx-border-radius: 12;", bg, fg, fg));
        return badge;
    }
}
