package com.phaseplane.math;

import java.util.Optional;

public final class EquilibriumAnalyzer {

    private static final double DX = 1e-6;
    private static final int MAX_NEWTON_ITER = 100;
    private static final double TOL_F = 1e-9;
    private static final double TOL_X = 1e-9;

    /**
     * Calcula o Jacobiano por diferenças finitas centrais:
     * J = [ [df/dx, df/dy],
     *       [dg/dx, dg/dy] ]
     */
    public static double[][] approximateJacobian(DynamicalSystem2D system, double x, double y) {
        double[] fxPlus = system.evaluate(x + DX, y);
        double[] fxMinus = system.evaluate(x - DX, y);
        double[] fyPlus = system.evaluate(x, y + DX);
        double[] fyMinus = system.evaluate(x, y - DX);

        double dfdx = (fxPlus[0] - fxMinus[0]) / (2.0 * DX);
        double dfdy = (fyPlus[0] - fyMinus[0]) / (2.0 * DX);
        double dgdx = (fxPlus[1] - fxMinus[1]) / (2.0 * DX);
        double dgdy = (fyPlus[1] - fyMinus[1]) / (2.0 * DX);

        return new double[][]{{dfdx, dfdy}, {dgdx, dgdy}};
    }

    /**
     * Refinamento por Newton-Raphson bidimensional.
     */
    public static Optional<EquilibriumPoint> findEquilibrium(DynamicalSystem2D system,
                                                            double initialX, double initialY,
                                                            BoundingBox bounds) {
        double x = initialX;
        double y = initialY;

        for (int iter = 0; iter < MAX_NEWTON_ITER; iter++) {
            double[] fVal = system.evaluate(x, y);
            double residual = Math.hypot(fVal[0], fVal[1]);

            if (residual < TOL_F) {
                if (bounds != null && !bounds.contains(x, y)) {
                    return Optional.empty();
                }
                return Optional.of(classify(system, x, y));
            }

            double[][] J = approximateJacobian(system, x, y);
            double a = J[0][0];
            double b = J[0][1];
            double c = J[1][0];
            double d = J[1][1];

            double det = a * d - b * c;
            if (Math.abs(det) < 1e-14) {
                return Optional.empty(); // Matriz singular / falha de convergência
            }

            // Delta = -J^-1 * F
            double invDet = 1.0 / det;
            double deltaX = -invDet * (d * fVal[0] - b * fVal[1]);
            double deltaY = -invDet * (-c * fVal[0] + a * fVal[1]);

            x += deltaX;
            y += deltaY;

            if (Math.hypot(deltaX, deltaY) < TOL_X) {
                if (bounds != null && !bounds.contains(x, y)) {
                    return Optional.empty();
                }
                return Optional.of(classify(system, x, y));
            }
        }

        return Optional.empty();
    }

    /**
     * Decomposição espectral analítica da matriz 2x2 e classificação qualitativa.
     */
    private static EquilibriumPoint classify(DynamicalSystem2D system, double x, double y) {
        double[][] J = approximateJacobian(system, x, y);
        double a = J[0][0];
        double b = J[0][1];
        double c = J[1][0];
        double d = J[1][1];

        double trace = a + d;
        double det = a * d - b * c;
        double discriminant = trace * trace - 4.0 * det;

        double[] realEig = new double[2];
        double[] imagEig = new double[2];
        double[][] eigenvectors = new double[2][2];

        EquilibriumPoint.Type type;
        EquilibriumPoint.Stability stability;

        if (Math.abs(det) < 1e-8) {
            type = EquilibriumPoint.Type.DEGENERATE;
            stability = EquilibriumPoint.Stability.UNKNOWN;
        } else if (det < 0) {
            type = EquilibriumPoint.Type.SADDLE;
            stability = EquilibriumPoint.Stability.UNSTABLE;
        } else {
            if (discriminant >= 0) {
                type = EquilibriumPoint.Type.NODE;
            } else {
                type = (Math.abs(trace) < 1e-7) ? EquilibriumPoint.Type.CENTER : EquilibriumPoint.Type.SPIRAL;
            }

            if (trace < -1e-8) {
                stability = EquilibriumPoint.Stability.STABLE;
            } else if (trace > 1e-8) {
                stability = EquilibriumPoint.Stability.UNSTABLE;
            } else {
                stability = EquilibriumPoint.Stability.NEUTRALLY_STABLE;
            }
        }

        // Resolução dos autovalores e autovetores
        if (discriminant >= 0) {
            realEig[0] = 0.5 * (trace + Math.sqrt(discriminant));
            realEig[1] = 0.5 * (trace - Math.sqrt(discriminant));
            imagEig[0] = 0.0;
            imagEig[1] = 0.0;

            computeRealEigenvector(a, b, c, d, realEig[0], eigenvectors, 0);
            computeRealEigenvector(a, b, c, d, realEig[1], eigenvectors, 1);
        } else {
            realEig[0] = 0.5 * trace;
            realEig[1] = 0.5 * trace;
            imagEig[0] = 0.5 * Math.sqrt(-discriminant);
            imagEig[1] = -imagEig[0];
            // Autovalores complexos conjugados: autovetores não são reais
            eigenvectors = new double[][]{{Double.NaN, Double.NaN}, {Double.NaN, Double.NaN}};
        }

        return new EquilibriumPoint(x, y, J, realEig, imagEig, eigenvectors, type, stability);
    }

    private static void computeRealEigenvector(double a, double b, double c, double d,
                                              double lambda, double[][] outEig, int col) {
        double v1, v2;
        if (Math.abs(b) > 1e-12) {
            v1 = -b;
            v2 = a - lambda;
        } else if (Math.abs(c) > 1e-12) {
            v1 = d - lambda;
            v2 = -c;
        } else {
            v1 = (col == 0) ? 1.0 : 0.0;
            v2 = (col == 0) ? 0.0 : 1.0;
        }

        // Normalização Euclidiana
        double norm = Math.hypot(v1, v2);
        if (norm > 1e-14) {
            v1 /= norm;
            v2 /= norm;
        }

        outEig[0][col] = v1;
        outEig[1][col] = v2;
    }
}
