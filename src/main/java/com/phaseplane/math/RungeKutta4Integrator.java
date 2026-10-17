package com.phaseplane.math;

import java.util.ArrayList;
import java.util.List;

public final class RungeKutta4Integrator {
    private final double stepSize;
    private final long maxWallClockMillis;

    public RungeKutta4Integrator(double stepSize, long maxWallClockMillis) {
        this.stepSize = Math.abs(stepSize);
        this.maxWallClockMillis = maxWallClockMillis;
    }

    public Trajectory integrate(DynamicalSystem2D system,
                                double x0, double y0,
                                double tSpan,
                                boolean backward,
                                BoundingBox bounds) {
        List<Double> tList = new ArrayList<>();
        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();

        double t = 0.0;
        double x = x0;
        double y = y0;
        double h = backward ? -stepSize : stepSize;

        tList.add(t);
        xList.add(x);
        yList.add(y);

        long startTime = System.currentTimeMillis();
        Trajectory.TerminationReason reason = Trajectory.TerminationReason.TIME_EXHAUSTED;

        double[] k1 = new double[2];
        double[] k2 = new double[2];
        double[] k3 = new double[2];
        double[] k4 = new double[2];

        int totalSteps = (int) Math.ceil(Math.abs(tSpan) / stepSize);

        for (int i = 0; i < totalSteps; i++) {
            if (System.currentTimeMillis() - startTime > maxWallClockMillis) {
                reason = Trajectory.TerminationReason.TIMEOUT;
                break;
            }

            system.evaluate(x, y, k1);
            system.evaluate(x + 0.5 * h * k1[0], y + 0.5 * h * k1[1], k2);
            system.evaluate(x + 0.5 * h * k2[0], y + 0.5 * h * k2[1], k3);
            system.evaluate(x + h * k3[0], y + h * k3[1], k4);

            x += (h / 6.0) * (k1[0] + 2.0 * k2[0] + 2.0 * k3[0] + k4[0]);
            y += (h / 6.0) * (k1[1] + 2.0 * k2[1] + 2.0 * k3[1] + k4[1]);
            t += h;

            tList.add(t);
            xList.add(x);
            yList.add(y);

            if (bounds != null && !bounds.contains(x, y)) {
                reason = Trajectory.TerminationReason.OUT_OF_BOUNDS;
                break;
            }
        }

        double[] tArr = tList.stream().mapToDouble(Double::doubleValue).toArray();
        double[] xArr = xList.stream().mapToDouble(Double::doubleValue).toArray();
        double[] yArr = yList.stream().mapToDouble(Double::doubleValue).toArray();

        return new Trajectory(tArr, xArr, yArr, reason);
    }
}
