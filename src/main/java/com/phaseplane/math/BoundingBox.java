package com.phaseplane.math;

public record BoundingBox(double xMin, double xMax, double yMin, double yMax) {
    public BoundingBox {
        if (xMin >= xMax || yMin >= yMax) {
            throw new IllegalArgumentException("Limites inválidos: xMin < xMax e yMin < yMax são obrigatórios.");
        }
    }

    public double width() {
        return xMax - xMin;
    }

    public double height() {
        return yMax - yMin;
    }

    public boolean contains(double x, double y) {
        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }

    /**
     * Retorna uma nova caixa expandida por uma fração percentual da sua largura e altura.
     * Exemplo: factor = 0.3 expande 30% para cada lado.
     */
    public BoundingBox expanded(double factor) {
        double dx = width() * factor;
        double dy = height() * factor;
        return new BoundingBox(xMin - dx, xMax + dx, yMin - dy, yMax + dy);
    }
}
