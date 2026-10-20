package com.phaseplane.math;

import java.util.ArrayList;
import java.util.List;

public final class DormandPrinceIntegrator {

    // Coeficientes do tableau de Dormand-Prince (DOPRI5)
    private static final double C2 = 1.0 / 5.0;
    private static final double C3 = 3.0 / 10.0;
    private static final double C4 = 4.0 / 5.0;
    private static final double C5 = 8.0 / 9.0;

    private static final double A21 = 1.0 / 5.0;
    private static final double A31 = 3.0 / 40.0;
    private static final double A32 = 9.0 / 40.0;
    private static final double A41 = 44.0 / 45.0;
    private static final double A42 = -56.0 / 15.0;
    private static final double A43 = 32.0 / 9.0;
    private static final double A51 = 19372.0 / 6561.0;
    private static final double A52 = -25360.0 / 2187.0;
    private static final double A53 = 64448.0 / 6561.0;
    private static final double A54 = -212.0 / 729.0;
    private static final double A61 = 9017.0 / 3168.0;
    private static final double A62 = -355.0 / 33.0;
    private static final double A63 = 46732.0 / 5247.0;
    private static final double A64 = 49.0 / 176.0;
    private static final double A65 = -5103.0 / 18656.0;
    private static final double A71 = 35.0 / 384.0;
    private static final double A73 = 500.0 / 1113.0;
    private static final double A74 = 125.0 / 192.0;
    private static final double A75 = -2187.0 / 6784.0;
    private static final double A76 = 11.0 / 84.0;

    // Coeficientes para estimativa do erro E = y5 - y4
    private static final double E1 = 71.0 / 57600.0;
    private static final double E3 = -71.0 / 16695.0;
    private static final double E4 = 71.0 / 1920.0;
    private static final double E5 = -17253.0 / 339200.0;
    private static final double E6 = 22.0 / 525.0;
    private static final double E7 = -1.0 / 40.0;

    private final double relTol;
    private final double absTol;
    private final double maxStep;
    private final double minStep;
    private final long maxWallClockMillis;

    public DormandPrinceIntegrator(double relTol, double absTol, double maxStep, double minStep, long maxWallClockMillis) {
        this.relTol = relTol;
        this.absTol = absTol;
        this.maxStep = maxStep;
        this.minStep = minStep;
        this.maxWallClockMillis = maxWallClockMillis;
    }

    public DormandPrinceIntegrator() {
        this(1e-6, 1e-6, 0.05, 1e-8, 2000);
    }

    /**
     * Integração bidirecional contínua:
     * Calcula o ramo reverso tMin -> 0 e concatena com o ramo direto 0 -> tMax.
     */
    public Trajectory integrateBidirectional(DynamicalSystem2D system,
                                             double x0, double y0,
                                             double tMin, double tMax,
                                             BoundingBox bounds) {
        Trajectory backward = integrateOneDirection(system, x0, y0, tMin, bounds, true);
        Trajectory forward = integrateOneDirection(system, x0, y0, tMax, bounds, false);

        int bSize = backward.size();
        int fSize = forward.size();
        int totalSize = (bSize > 0 ? bSize - 1 : 0) + fSize;

        double[] tMerged = new double[totalSize];
        double[] xMerged = new double[totalSize];
        double[] yMerged = new double[totalSize];

        int idx = 0;
        // Inverte os pontos da trajetória reversa para ordem temporal estritamente crescente
        for (int i = bSize - 1; i >= 1; i--) {
            tMerged[idx] = backward.t()[i];
            xMerged[idx] = backward.x()[i];
            yMerged[idx] = backward.y()[i];
            idx++;
        }

        // Concatena ramo frontal
        for (int i = 0; i < fSize; i++) {
            tMerged[idx] = forward.t()[i];
            xMerged[idx] = forward.x()[i];
            yMerged[idx] = forward.y()[i];
            idx++;
        }

        return new Trajectory(tMerged, xMerged, yMerged, forward.reason());
    }

    public Trajectory integrateOneDirection(DynamicalSystem2D system,
                                            double x0, double y0,
                                            double tTarget,
                                            BoundingBox rawBounds,
                                            boolean backward) {
        BoundingBox bounds = (rawBounds != null) ? rawBounds.expanded(0.35) : null;
        List<Double> tList = new ArrayList<>();
        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();

        double t = 0.0;
        double x = x0;
        double y = y0;

        tList.add(t);
        xList.add(x);
        yList.add(y);

        double direction = backward ? -1.0 : 1.0;
        double h = direction * Math.min(0.01, Math.abs(tTarget) * 0.1);
        long startTime = System.currentTimeMillis();

        Trajectory.TerminationReason reason = Trajectory.TerminationReason.TIME_EXHAUSTED;

        double[] k1 = new double[2];
        double[] k2 = new double[2];
        double[] k3 = new double[2];
        double[] k4 = new double[2];
        double[] k5 = new double[2];
        double[] k6 = new double[2];
        double[] k7 = new double[2];

        system.evaluate(x, y, k1);

        while ((backward && t > tTarget) || (!backward && t < tTarget)) {
            // Checagem de Timeout
            if (System.currentTimeMillis() - startTime > maxWallClockMillis) {
                reason = Trajectory.TerminationReason.TIMEOUT;
                break;
            }

            // Ajuste do último passo
            if ((backward && t + h < tTarget) || (!backward && t + h > tTarget)) {
                h = tTarget - t;
            }

            // Estágios de Runge-Kutta
            double x2 = x + h * (A21 * k1[0]);
            double y2 = y + h * (A21 * k1[1]);
            system.evaluate(x2, y2, k2);

            double x3 = x + h * (A31 * k1[0] + A32 * k2[0]);
            double y3 = y + h * (A31 * k1[1] + A32 * k2[1]);
            system.evaluate(x3, y3, k3);

            double x4 = x + h * (A41 * k1[0] + A42 * k2[0] + A43 * k3[0]);
            double y4 = y + h * (A41 * k1[1] + A42 * k2[1] + A43 * k3[1]);
            system.evaluate(x4, y4, k4);

            double x5 = x + h * (A51 * k1[0] + A52 * k2[0] + A53 * k3[0] + A54 * k4[0]);
            double y5 = y + h * (A51 * k1[1] + A52 * k2[1] + A53 * k3[1] + A54 * k4[1]);
            system.evaluate(x5, y5, k5);

            double x6 = x + h * (A61 * k1[0] + A62 * k2[0] + A63 * k3[0] + A64 * k4[0] + A65 * k5[0]);
            double y6 = y + h * (A61 * k1[1] + A62 * k2[1] + A63 * k3[1] + A64 * k4[1] + A65 * k5[1]);
            system.evaluate(x6, y6, k6);

            // Nova solução de ordem 5
            double xNext = x + h * (A71 * k1[0] + A73 * k3[0] + A74 * k4[0] + A75 * k5[0] + A76 * k6[0]);
            double yNext = y + h * (A71 * k1[1] + A73 * k3[1] + A74 * k4[1] + A75 * k5[1] + A76 * k6[1]);

            // FSAL (First Same As Last)
            system.evaluate(xNext, yNext, k7);

            // Estimativa de erro local
            double errX = h * (E1 * k1[0] + E3 * k3[0] + E4 * k4[0] + E5 * k5[0] + E6 * k6[0] + E7 * k7[0]);
            double errY = h * (E1 * k1[1] + E3 * k3[1] + E4 * k4[1] + E5 * k5[1] + E6 * k6[1] + E7 * k7[1]);

            double scaleX = absTol + Math.max(Math.abs(x), Math.abs(xNext)) * relTol;
            double scaleY = absTol + Math.max(Math.abs(y), Math.abs(yNext)) * relTol;

            double ratioX = errX / scaleX;
            double ratioY = errY / scaleY;
            double errorNorm = Math.sqrt(0.5 * (ratioX * ratioX + ratioY * ratioY));
// ========================================================
            // Controle Geométrico: impede retas longas no plano de fase
            // ========================================================
            double dist = Math.hypot(xNext - x, yNext - y);
            double maxDist = (bounds != null)
                    ? Math.hypot(bounds.xMax() - bounds.xMin(), bounds.yMax() - bounds.yMin()) * 0.02
                    : 0.1;

            if (dist > maxDist && Math.abs(h) > minStep * 2.0) {
                h *= 0.5;
                continue; // Rejeita e recalcula o mesmo ponto com h menor
            }
            // ========================================================
            if (errorNorm <= 1.0 || Math.abs(h) <= minStep) {
                // Passo aceito
                t += h;
                x = xNext;
                y = yNext;

                tList.add(t);


                xList.add(x);
                yList.add(y);

                // Checagem de limites de domínio
                if (bounds != null && !bounds.contains(x, y)) {
                    reason = Trajectory.TerminationReason.OUT_OF_BOUNDS;
                    break;
                }

                // Prepara k1 para o próximo passo através da propriedade FSAL
                k1[0] = k7[0];
                k1[1] = k7[1];
            }

            // Fator de escala adaptativo de passo
            double factor = (errorNorm == 0) ? 2.0 : 0.9 * Math.pow(errorNorm, -0.2);
            factor = Math.max(0.2, Math.min(5.0, factor));
            h *= factor;

            if (Math.abs(h) > maxStep) {
                h = direction * maxStep;
            }
        }

        double[] tArr = tList.stream().mapToDouble(Double::doubleValue).toArray();
        double[] xArr = xList.stream().mapToDouble(Double::doubleValue).toArray();
        double[] yArr = yList.stream().mapToDouble(Double::doubleValue).toArray();

        return new Trajectory(tArr, xArr, yArr, reason);
    }
}
