package com.phaseplane.math;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class DynamicalSystem2D {
    private final String varXName;
    private final String varYName;
    private final String rawFx;
    private final String rawFy;
    private final Map<String, Double> parameters;
    
    // Expressões thread-confined (instâncias exp4j)
    private final Expression exprFx;
    private final Expression exprFy;

    public DynamicalSystem2D(String varXName, String varYName,
                             String rawFx, String rawFy,
                             Map<String, Double> parameters) {
        this.varXName = varXName.trim();
        this.varYName = varYName.trim();
        this.rawFx = rawFx.trim();
        this.rawFy = rawFy.trim();
        this.parameters = new HashMap<>(parameters);

        Set<String> allVars = new java.util.HashSet<>(parameters.keySet());
        allVars.add(this.varXName);
        allVars.add(this.varYName);

        this.exprFx = new ExpressionBuilder(this.rawFx)
                .variables(allVars)
                .build();
        this.exprFy = new ExpressionBuilder(this.rawFy)
                .variables(allVars)
                .build();

        // Inicializa constantes de parâmetros
        for (var entry : this.parameters.entrySet()) {
            this.exprFx.setVariable(entry.getKey(), entry.getValue());
            this.exprFy.setVariable(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Avalia o campo vetorial f(x, y) e g(x, y).
     */
    public synchronized void evaluate(double x, double y, double[] outDerivatives) {
        exprFx.setVariable(varXName, x);
        exprFx.setVariable(varYName, y);
        exprFy.setVariable(varXName, x);
        exprFy.setVariable(varYName, y);

        outDerivatives[0] = exprFx.evaluate();
        outDerivatives[1] = exprFy.evaluate();
    }

    public double[] evaluate(double x, double y) {
        double[] out = new double[2];
        evaluate(x, y, out);
        return out;
    }

    public Map<String, Double> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }
}
