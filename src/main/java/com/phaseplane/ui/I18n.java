package com.phaseplane.ui;

import java.util.Locale;
import java.util.ResourceBundle;

public final class I18n {

    private static final String BUNDLE_BASE_NAME = "i18n.messages";
private static Locale currentLocale = Locale.getDefault();
    private static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME, currentLocale);

    private I18n() {
        // Classe utilitária, não instanciável
    }

    public static void setLocale(Locale locale) {
        currentLocale = locale;
        bundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME, currentLocale);
    }

    public static Locale getLocale() {
        return currentLocale;
    }

    public static String get(String key) {
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        }
        return "!" + key + "!";
    }
public static String format(String key, Object... args) {
        return String.format(currentLocale, get(key), args);
    }
}
