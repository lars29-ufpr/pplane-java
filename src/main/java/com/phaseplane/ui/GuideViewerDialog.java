package com.phaseplane.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class GuideViewerDialog {

    public static void show(Window owner) {
        Stage stage = new Stage();
        stage.initOwner(owner);
        stage.initModality(Modality.NONE);
        stage.setTitle(I18n.get("guide.window_title"));

        WebView webView = new WebView();
        VBox.setVgrow(webView, Priority.ALWAYS);

        String rawMarkdown = loadGuideContent();
        String htmlContent = buildHtmlWithMathJax(rawMarkdown);
        webView.getEngine().loadContent(htmlContent);

        Button btnClose = new Button(I18n.get("btn.close"));
        btnClose.setStyle("-fx-background-color: #21262d; -fx-text-fill: #c9d1d9; -fx-border-color: #30363d; -fx-padding: 6 18; -fx-cursor: hand;");
        btnClose.setOnAction(e -> stage.close());

        VBox root = new VBox(10, webView, btnClose);
        root.setAlignment(Pos.CENTER_RIGHT);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #0d1117;");

        Scene scene = new Scene(root, 820, 640);
        stage.setScene(scene);
        stage.show();
    }

    private static String buildHtmlWithMathJax(String markdown) {
        StringBuilder html = new StringBuilder();
        String[] lines = markdown.split("\r?\n");

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.startsWith("### ")) {
                html.append("<h3>").append(trimmed.substring(4)).append("</h3>\n");
            } else if (trimmed.startsWith("#### ")) {
                html.append("<h4>").append(trimmed.substring(5)).append("</h4>\n");
            } else if (trimmed.startsWith("# ")) {
                html.append("<h2>").append(trimmed.substring(2)).append("</h2>\n");
            } else if (trimmed.equals("---")) {
                html.append("<hr/>\n");
            } else if (trimmed.startsWith("* ")) {
                String itemContent = trimmed.substring(2).replaceAll("\\*\\*(.*?)\\*\\*", "<strong>$1</strong>");
                html.append("<p class='bullet'>• ").append(itemContent).append("</p>\n");
            } else if (trimmed.startsWith("$$") && trimmed.endsWith("$$")) {
                html.append("<div class='eq-block'>").append(trimmed).append("</div>\n");
            } else if (!trimmed.isEmpty()) {
                String formatted = trimmed.replaceAll("\\*\\*(.*?)\\*\\*", "<strong>$1</strong>");
                html.append("<p>").append(formatted).append("</p>\n");
            }
        }

        return "<!DOCTYPE html><html><head><meta charset='UTF-8'>" +
               "<script>window.MathJax = { tex: { inlineMath: [['$', '$']], displayMath: [['$$', '$$']] }, svg: { fontCache: 'global' } };</script>" +
               "<script src='https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-svg.js'></script>" +
               "<style>" +
               "body { background-color: #0d1117; color: #c9d1d9; font-family: system-ui, -apple-system, sans-serif; padding: 22px; line-height: 1.7; font-size: 14px; }" +
               "h2 { color: #58a6ff; border-bottom: 1px solid #30363d; padding-bottom: 6px; margin-top: 24px; font-size: 18px; }" +
               "h3 { color: #79c0ff; margin-top: 18px; margin-bottom: 8px; font-size: 16px; }" +
               "h4 { color: #d2a8ff; margin-top: 14px; margin-bottom: 6px; font-size: 14px; }" +
               "hr { border: 0; border-top: 1px solid #30363d; margin: 18px 0; }" +
               "strong { color: #f0f6fc; }" +
               "p { margin: 6px 0; }" +
               ".bullet { margin: 4px 0 4px 18px; color: #c9d1d9; }" +
               ".eq-block { margin: 14px 0; text-align: center; }" +
               "svg { overflow: visible !important; vertical-align: middle; }" +
               "mjx-container { color: #e6edf3 !important; font-size: 115% !important; margin: 6px 0 !important; }" +
               "</style></head><body>" + html + "</body></html>";
    }

    private static String loadGuideContent() {
        String lang = I18n.getLocale().getLanguage();
        // Para português, mantém o nome legado. Para outros, usa o sufixo do idioma.
        String fileName = lang.equals("pt") ? "GUIA_MODELOS.md" : "GUIA_MODELOS_" + lang + ".md";
        
        // 1ª Tentativa: Carregar o guia no idioma atual do sistema (ex: _de, _it, ou .md para pt)
        try (var is = GuideViewerDialog.class.getResourceAsStream("/docs/" + fileName)) {
            if (is != null) return new String(is.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
        } catch (Exception ignored) {}
        
        // 2ª Tentativa (Fallback Segura): Se falhar, tenta carregar o guia universal em inglês (_en)
        try (var is = GuideViewerDialog.class.getResourceAsStream("/docs/GUIA_MODELOS_en.md")) {
            if (is != null) return new String(is.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
        } catch (Exception ignored) {}
        
        // 3ª Tentativa: Verificação de arquivos externos (caso o usuário esteja rodando fora do .jar)
        java.io.File file = new java.io.File(fileName);
        if (!file.exists()) file = new java.io.File("GUIA_MODELOS_en.md");
        if (!file.exists()) file = new java.io.File("GUIA_MODELOS.md");
        
        if (file.exists()) {
            try { return java.nio.file.Files.readString(file.toPath()); }  
            catch (java.io.IOException e) { return I18n.get("guide.read_error") + e.getMessage(); }
        }
        
        return I18n.get("guide.not_found");
    }
}
