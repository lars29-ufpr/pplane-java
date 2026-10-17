package com.phaseplane.math;

public record EquilibriumPoint(
        double x,
        double y,
        double[][] jacobian,
        double[] realEigenvalues,
        double[] imagEigenvalues,
        double[][] realEigenvectors,
        Type type,
        Stability stability
) {
    public enum Type {
        SADDLE("Ponto de Sela"),
        NODE("Nó"),
        SPIRAL("Foco (Espiral)"),
        CENTER("Centro Linear"),
        DEGENERATE("Equilíbrio Degenerado");

        private final String descricao;

        Type(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    public enum Stability {
        STABLE("Estável (Atrator)"),
        UNSTABLE("Instável (Repulsor)"),
        NEUTRALLY_STABLE("Neutramente Estável"),
        UNKNOWN("Indeterminada");

        private final String descricao;

        Stability(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    /**
     * Retorna a classificação qualitativa combinada em português.
     * Exemplo: "Ponto de Sela", "Nó Estável (Atrator)", "Foco Instável (Espiral Repulsiva)".
     */
    public String classificacaoFormatada() {
        if (type == Type.SADDLE) {
            return "Ponto de Sela";
        }
        if (type == Type.CENTER) {
            return "Centro Linear";
        }
        if (type == Type.DEGENERATE) {
            return "Equilíbrio Degenerado";
        }
        return type.getDescricao() + " " + stability.getDescricao();
    }

    /**
     * Retorna a representação formatada dos autovalores em texto.
     */
    public String autovaloresFormatados() {
        if (imagEigenvalues == null || (imagEigenvalues[0] == 0.0 && imagEigenvalues[1] == 0.0)) {
            return String.format("λ₁ = %.4f, λ₂ = %.4f", realEigenvalues[0], realEigenvalues[1]);
        } else {
            return String.format("λ₁,₂ = %.4f ± %.4fi", realEigenvalues[0], Math.abs(imagEigenvalues[0]));
        }
    }
}
