package com.phaseplane;

/**
 * Ponto de entrada intermediário para contornar a verificação de módulos
 * em tempo de execução ao rodar uma aplicação JavaFX a partir de um Fat JAR comum.
 */
public final class Launcher {
    public static void main(String[] args) {
        MainApp.main(args);
    }
}
