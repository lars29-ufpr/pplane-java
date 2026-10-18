package com.phaseplane.math;

import java.io.*;
import java.util.*;

public class PredefinedModel {
    private String category;
    private String name;
    private String varX;
    private String varY;
    private String exprDx;
    private String exprDy;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private double tMax;
    private Map<String, Double> parameters;
    private String description;

    public PredefinedModel(String category, String name, String varX, String varY,
                           String exprDx, String exprDy,
                           double xMin, double xMax, double yMin, double yMax, double tMax,
                           Map<String, Double> parameters, String description) {
        this.category = category;
        this.name = name;
        this.varX = varX;
        this.varY = varY;
        this.exprDx = exprDx;
        this.exprDy = exprDy;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.tMax = tMax;
        this.parameters = parameters != null ? new LinkedHashMap<>(parameters) : new LinkedHashMap<>();
        this.description = description;
    }


    public String getCategory() { return category; }
    public String getName() { return name; }
    public String getVarX() { return varX; }
    public String getVarY() { return varY; }
    public String getExprDx() { return exprDx; }
    public String getExprDy() { return exprDy; }
    public double getXMin() { return xMin; }
    public double getXMax() { return xMax; }
    public double getYMin() { return yMin; }
    public double getYMax() { return yMax; }
    public double getTMax() { return tMax; }
    public Map<String, Double> getParameters() { return parameters; }
    public String getDescription() { return description; }

    /**
     * Exporta as definições do modelo para um arquivo .pplane (formato chave-valor legível).
     */
    public void saveToFile(File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("# Phase Plane Model Definition (.pplane)");
            writer.println("version=1.0");
            writer.println("category=" + category);
            writer.println("name=" + name);
            writer.println("varX=" + varX);
             writer.println("varY=" + varY);
            writer.println("exprDx=" + exprDx);
            writer.println("exprDy=" + exprDy);
            writer.println(String.format(Locale.US, "xMin=%.4f", xMin));
            writer.println(String.format(Locale.US, "xMax=%.4f", xMax));
            writer.println(String.format(Locale.US, "yMin=%.4f", yMin));
            writer.println(String.format(Locale.US, "yMax=%.4f", yMax));
            writer.println(String.format(Locale.US, "tMax=%.4f", tMax));
            
            StringBuilder sbParams = new StringBuilder();
            for (Map.Entry<String, Double> e : parameters.entrySet()) {
                if (sbParams.length() > 0) sbParams.append(";");
                sbParams.append(e.getKey()).append("=").append(e.getValue());
            }
            writer.println("parameters=" + sbParams.toString());
            
            // Descrição em uma única linha escapada ou delimitada
            String cleanDesc = description != null ? description.replace("\n", "\\n") : "";
            writer.println("description=" + cleanDesc);
        }
    }

    /**
     * Importa um modelo a partir de um arquivo .pplane.
     */
    public static PredefinedModel loadFromFile(File file) throws IOException {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                int idx = line.indexOf('=');
                if (idx != -1) {
                    String k = line.substring(0, idx).trim();
                    String v = line.substring(idx + 1).trim();
                    map.put(k, v);
                }
            }
        }

        String cat = map.getOrDefault("category", "Modelos do Usuário");
        String name = map.getOrDefault("name", file.getName().replace(".pplane", ""));
        String vx = map.getOrDefault("varX", "x");
        String vy = map.getOrDefault("varY", "y");
        String dx = map.getOrDefault("exprDx", "0");
        String dy = map.getOrDefault("exprDy", "0");
        double xmin = Double.parseDouble(map.getOrDefault("xMin", "-5.0"));
        double xmax = Double.parseDouble(map.getOrDefault("xMax", "5.0"));
        double ymin = Double.parseDouble(map.getOrDefault("yMin", "-5.0"));
        double ymax = Double.parseDouble(map.getOrDefault("yMax", "5.0"));
        double tmax = Double.parseDouble(map.getOrDefault("tMax", "20.0"));
        
        Map<String, Double> params = new LinkedHashMap<>();
        String rawParams = map.getOrDefault("parameters", "");
        if (!rawParams.isEmpty()) {
            for (String pair : rawParams.split(";")) {
                String[] kv = pair.split("=");
                if (kv.length == 2) {
                    try {
                        params.put(kv[0].trim(), Double.parseDouble(kv[1].trim()));
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        String desc = map.getOrDefault("description", "");
        desc = desc.replace("\\n", "\n");

        return new PredefinedModel(cat, name, vx, vy, dx, dy, xmin, xmax, ymin, ymax, tmax, params, desc);
    }

    /**
     * Retorna o catálogo dos 8 modelos canônicos integrados no software.
     */
    public static List<PredefinedModel> getDefaultModels() {
        List<PredefinedModel> list = new ArrayList<>();

        // 1. Van der Pol (Física)
        Map<String, Double> p1 = new LinkedHashMap<>();
        p1.put("mu", 1.0);
        list.add(new PredefinedModel("Física & Mecânica", "Oscilador de Van der Pol", "x", "y",
                "y", "mu * (1 - x^2) * y - x",
                -4.0, 4.0, -4.0, 4.0, 30.0, p1,
                "Oscilador autoexcitado com amortecimento não linear que exibe um ciclo limite estável relaxado."));

        // 2. Pêndulo Não Linear (Física)
        Map<String, Double> p2 = new LinkedHashMap<>();
        p2.put("b", 0.2);
        p2.put("w", 1.0);
        list.add(new PredefinedModel("Física & Mecânica", "Pêndulo Não Linear com Atrito", "theta", "omega",
                "omega", "-w^2 * sin(theta) - b * omega",
                -7.0, 7.0, -5.0, 5.0, 40.0, p2,
                "Movimento pendular com atrito viscoso. Apresenta focos atratores e selas nas separatrizes homoclínicas/heteroclínicas."));

        // 3. Oscilador de Duffing (Física)
        Map<String, Double> p3 = new LinkedHashMap<>();
        p3.put("alpha", 1.0);
        p3.put("beta", -1.0);
        p3.put("delta", 0.2);
        list.add(new PredefinedModel("Física & Mecânica", "Oscilador de Duffing (Autônomo)", "x", "y",
                "y", "-delta * y - beta * x - alpha * x^3",
                -3.0, 3.0, -3.0, 3.0, 40.0, p3,
                "Sistema biestável com potencial de poço duplo. Ilustra a competição entre duas bacias de atração."));

        // 4. Lotka-Volterra Clássico (Biomatemática)
        Map<String, Double> p4 = new LinkedHashMap<>();
        p4.put("a", 1.0);
        p4.put("b", 0.1);
        p4.put("c", 1.5);
        p4.put("d", 0.05);
        list.add(new PredefinedModel("Biomatemática & Ecologia", "Predador-Presa (Lotka-Volterra)", "N", "P",
                "a * N - b * N * P", "-c * P + d * N * P",
                0.0, 80.0, 0.0, 30.0, 25.0, p4,
                "Modelo canônico conservativo presa-predador. As trajetórias formam órbitas periódicas fechadas no primeiro quadrante."));

        // 5. Competição Interespecífica de Murray (Biomatemática)
        Map<String, Double> p5 = new LinkedHashMap<>();
        p5.put("a1", 1.0);
        p5.put("b1", 1.0);
        p5.put("c1", 1.0);
        p5.put("a2", 0.7);
        p5.put("b2", 1.0);
        p5.put("c2", 0.5);
list.add(new PredefinedModel("Biomatemática & Ecologia", "Competição de Duas Espécies (Murray)", "x", "y",
                "x * (a1 - b1 * x - c1 * y)", "y * (a2 - b2 * y - c2 * x)",
                0.0, 1.5, 0.0, 1.5, 30.0, p5,
                "Modelo logístico com termos competitivos cruzados conforme James Murray. Dependendo dos parâmetros, ilustra coexistência estável ou exclusão competitiva."));
// 6. FitzHugh-Nagumo (Biomatemática)
        Map<String, Double> p6 = new LinkedHashMap<>();
        p6.put("a", 0.7);
        p6.put("b", 0.8);
        p6.put("tau", 12.5);
        p6.put("I", 0.5);
        list.add(new PredefinedModel("Biomatemática & Ecologia", "Neurônio de FitzHugh-Nagumo", "v", "w",
                "v - (v^3)/3 - w + I", "(v + a - b * w) / tau",
                -2.5, 2.5, -1.0, 2.0, 100.0, p6,
                "Simplificação bidimensional de Hodgkin-Huxley para a dinâmica de potenciais de ação neuronais e excitabilidade."));

        // 7. CTH de Stommel (Clima & Geofísica)
        Map<String, Double> p7 = new LinkedHashMap<>();
        p7.put("T0", 1.0);
        p7.put("S0", 0.8);
        p7.put("R", 0.5);
        p7.put("mu", 0.2);
        list.add(new PredefinedModel("Clima & Oceanografia", "Circulação Termoalina de Stommel", "T", "S",
                "T0 - T - T * (1 + mu * abs(T - S))", "S0 - S - S * (1 + mu * abs(T - S)) * R",
                -1.5, 2.0, -1.5, 2.0, 50.0, p7,
                "Modelo de duas caixas de Stommel para a convecção oceânica profunda (AMOC). Mostra biestabilidade e pontos de inflexão climáticos (tipping points)."));

        // 8. Ciclos de Kaldor (Finanças & Economia)
        Map<String, Double> p8 = new LinkedHashMap<>();
        p8.put("alpha", 1.2);
        p8.put("gamma", 0.8);
        p8.put("s", 0.3);
        p8.put("delta", 0.1);
        list.add(new PredefinedModel("Finanças & Dinâmica Não Linear", "Ciclo Econômico Não Linear de Kaldor", "Y", "K",
                "alpha * (tanh(Y) - s * Y - gamma * K)", "s * Y - delta * K",
                -3.0, 3.0, -3.0, 3.0, 50.0, p8,
                "Dinâmica macroeconômica de renda (Y) e estoque de capital (K) com função de investimento não linear sigmoide gerando ciclos endógenos."));
// 9. Epidemiologia SIR (Kermack-McKendrick)
        Map<String, Double> pSIR = new LinkedHashMap<>();
        pSIR.put("beta", 0.5);
        pSIR.put("gamma", 0.2);
        list.add(new PredefinedModel(
                "Biomatemática & Ecologia",
                "Epidemia SIR (Kermack-McKendrick)",
                "S", "I",
                "-beta * S * I",
                "beta * S * I - gamma * I",
                0.0, 1.0, 0.0, 0.6, 60.0,
                pSIR,
                "Modelo epidemiológico clássico de Kermack-McKendrick (1927). Descreve o espalhamento de patógeno com imunidade permanente em frações populacionais adimensionais (S + I + R = 1). O limiar epidêmico básico é R0 = beta/gamma = 2.5, com a nulclina crítica vertical em S = gamma/beta = 0.4 definindo o pico infeccioso."
        ));
// 10. Polking: Múltiplos Equilíbrios
        Map<String, Double> p10 = new LinkedHashMap<>();
        list.add(new PredefinedModel("Finanças & Dinâmica Não Linear", "10. Polking: Múltiplos Equilíbrios", "x", "y",
                "x^2 - y^2", "x * (2 - y)",
                -4.0, 4.0, -2.0, 5.0, 30.0,
                p10,
                "Exemplo clássico de John Polking (PPLANE/Rice). Apresenta 4 pontos de equilíbrio simultâneos, ilustrando separatrizes e fronteiras de bacias de atração."));

        // 11. Dinâmica do Amor (Romeu & Julieta)
        Map<String, Double> p11 = new LinkedHashMap<>();
        p11.put("a", -0.1);
        p11.put("b", 1.0);
        p11.put("c", -1.0);
        p11.put("d", -0.1);
list.add(new PredefinedModel("Sistemas Lineares & Teoria Qualitativa", "Sistemas Lineares 2D (Classificação & O Amor de Strogatz)", "R", "J",
                "a * R + b * J", "c * R + d * J",
                -3.0, 3.0, -3.0, 3.0, 40.0,
                p11,
                "Modelo linear de Steven Strogatz para o afeto recíproco. Mostra órbitas em espiral rumo à apatia emocional ou ciclos de desencontros amorosos matematicamente inevitáveis."));
// 12. Oscilador Químico Brusselator (Prigogine & Lefever)
        Map<String, Double> p12 = new LinkedHashMap<>();
        p12.put("a", 1.0);
        p12.put("b", 3.0);
        list.add(new PredefinedModel("Química & Dinâmica Não Linear", "Oscilador Químico Brusselator", "x", "y",
                "a - (b + 1) * x + x^2 * y", "b * x - x^2 * y",
                0.0, 5.0, 0.0, 5.0, 40.0,
                p12,
                "Reação autocatalítica de Prigogine e Lefever. Exibe bifurcação de Hopf supercrítica em b = 1 + a^2 com surgimento de ciclo-limite."));       
 return list;
    }
}
