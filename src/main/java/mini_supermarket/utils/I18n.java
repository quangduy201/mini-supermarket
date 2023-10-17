package mini_supermarket.utils;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;

public class I18n {
    private static ResourceBundle resourceBundle;
    private static Locale currentLocale;

    public static void initialize() {
        setCurrentLocale(Locale.ENGLISH);
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void setCurrentLocale(Locale locale) {
        currentLocale = locale;
        resourceBundle = ResourceBundle.getBundle("i18n.messages", currentLocale);
    }

    public static String getString(String key, Object... args) {
        try {
            String s = resourceBundle.getString(key);
            s = replaceArgs(s, args);
            return s;
        } catch (Exception e) {
            Log.error(I18n.class, "Error while getting the string value of the key '" + key + "': " + e);
            return key;
        }
    }

    public static String replaceArgs(String message, Object... args) {
        // Replace placeholders like {0}, {1}, {2}, ... with provided arguments
        for (int i = 0; i < args.length; i++) {
            String placeholder = "\\{%d\\}".formatted(i);
            message = message.replaceAll(placeholder, Matcher.quoteReplacement(String.valueOf(args[i])));
        }
        return message;
    }
}
