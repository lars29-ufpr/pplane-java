package com.phaseplane.math;

import com.phaseplane.ui.I18n;
import java.util.Locale;

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
        SADDLE("eq.type.saddle"),
        NODE("eq.type.node"),
        SPIRAL("eq.type.spiral"),
        CENTER("eq.type.center"),
        DEGENERATE("eq.type.degenerate");

        private final String i18nKey;

        Type(String i18nKey) {
            this.i18nKey = i18nKey;
        }

        public String getDescricao() {
            return I18n.get(i18nKey);
        }
    }

    public enum Stability {
        STABLE("eq.stability.stable"),
        UNSTABLE("eq.stability.unstable"),
        NEUTRALLY_STABLE("eq.stability.neutrally_stable"),
        UNKNOWN("eq.stability.unknown");

        private final String i18nKey;

        Stability(String i18nKey) {
            this.i18nKey = i18nKey;
        }

        public String getDescricao() {
            return I18n.get(i18nKey);
        }
    }

    /**
     * Retorna a classificacao qualitativa combinada no idioma ativo.
     */
    public String classificacaoFormatada() {
        if (type == Type.SADDLE) {
            return Type.SADDLE.getDescricao();
        }
        if (type == Type.CENTER) {
            return Type.CENTER.getDescricao();
        }
        if (type == Type.DEGENERATE) {
            return Type.DEGENERATE.getDescricao();
        }
        return type.getDescricao() + " " + stability.getDescricao();
    }

    /**
     * Formata os autovalores de forma legivel para exibicao.
     */
    public String autovaloresFormatados() {
        if (imagEigenvalues[0] == 0.0 && imagEigenvalues[1] == 0.0) {
            return String.format(Locale.US, "λ₁ = %.4f,  λ₂ = %.4f", realEigenvalues[0], realEigenvalues[1]);
        } else {
            return String.format(Locale.US, "λ = %.4f ± %.4fi", realEigenvalues[0], Math.abs(imagEigenvalues[0]));
        }
    }
}
