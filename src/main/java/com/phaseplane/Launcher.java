package com.phaseplane;

import com.phaseplane.ui.I18n;
import java.util.Locale;

/**
 * Ponto de entrada universal do pplaneX.
 * Permite contornar a validação de módulos do JavaFX em Fat JARs
 * e configura o Locale de forma transparente (multiplataforma).
 */
public final class Launcher {

    public static void main(String[] args) {
        Locale chosenLocale = null;

        // 1. Permite forçar idioma via argumento CLI (--lang=pt, --lang=en)
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--lang=pt") || arg.equalsIgnoreCase("-pt")) {
                chosenLocale = new Locale("pt", "BR");
                break;
            } else if (arg.equalsIgnoreCase("--lang=en") || arg.equalsIgnoreCase("-en")) {
                chosenLocale = Locale.ENGLISH;
                break;
            }
        }

        // 2. Se não foi forçado, detecta o idioma do sistema operacional
        if (chosenLocale == null) {
            Locale sys = Locale.getDefault();
            if (sys.getLanguage().equalsIgnoreCase("pt")) {
                chosenLocale = new Locale("pt", "BR");
            } else {
                chosenLocale = Locale.ENGLISH;
            }
        }

        Locale.setDefault(chosenLocale);
        I18n.setLocale(chosenLocale);

        MainApp.main(args);
    }
}
