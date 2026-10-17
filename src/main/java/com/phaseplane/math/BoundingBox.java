package com.phaseplane.math;

public record BoundingBox(double xMin, double xMax, double yMin, double yMax) {
    public BoundingBox {
        if (xMin >= xMax || yMin >= yMax) {
            throw new IllegalArgumentException("Limites inválidos: xMin < xMax e yMin < yMax são obrigatórios.");
        }
    }

    public boolean contains(double x, double y) {
        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }
}
