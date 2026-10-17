package com.phaseplane.math;

import java.util.List;

public record Trajectory(
        double[] t,
        double[] x,
        double[] y,
        TerminationReason reason
) {
    public enum TerminationReason {
        TIME_EXHAUSTED,
        OUT_OF_BOUNDS,
        TIMEOUT,
        SINGULARITY
    }

    public int size() {
        return t.length;
    }
}
