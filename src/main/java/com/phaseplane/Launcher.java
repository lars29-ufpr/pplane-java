package com.phaseplane;

import com.phaseplane.ui.I18n;
import java.util.Locale;

/**
 * Ponto de entrada universal do pplaneX.
 * Permite contornar a validação de módulos do JavaFX em Fat JARs
 * e configura o Locale de forma transparente e cumulativa.
 */
public final class Launcher {

    public static void main(String[] args) {
        // O Java já inicia o Locale.getDefault() lendo o sistema operacional 
        // ou a flag -Duser.language injetada no terminal.
        Locale chosenLocale = Locale.getDefault();

        // 1. Permite sobrescrever via argumento CLI (--lang=es, --lang=fr, -pt, -en)
        for (String arg : args) {
            if (arg.toLowerCase().startsWith("--lang=")) {
                chosenLocale = new Locale(arg.substring(7));
                break;
            } else if (arg.equalsIgnoreCase("-pt")) {
                chosenLocale = new Locale("pt", "BR");
                break;
            } else if (arg.equalsIgnoreCase("-en")) {
                chosenLocale = Locale.ENGLISH;
                break;
            } else if (arg.equalsIgnoreCase("-es")) {
                chosenLocale = new Locale("es");
                break;
            }
        }

        // 2. Define o idioma. Se o arquivo correspondente não existir, 
        // o próprio Java fará o fallback (recuo automático) para o Inglês.
        Locale.setDefault(chosenLocale);
        I18n.setLocale(chosenLocale);

        MainApp.main(args);
    }
}
