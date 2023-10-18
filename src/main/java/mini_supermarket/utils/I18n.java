package mini_supermarket.utils;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;

public class I18n {
    private static ResourceBundle resourceBundle;
    private static Language language;

    public enum Language {
        ENGLISH(Locale.ENGLISH),
        VIETNAMESE(new Locale("vi"));

        private final Locale locale;

        Language(Locale locale) {
            this.locale = locale;
        }

        public Locale getLocale() {
            return locale;
        }
    }

    public static void initialize() {
        Properties properties = Resource.loadProperties(Settings.CONFIG_FILE);
        String language = properties.getProperty("language");
        if (language.equalsIgnoreCase("vi"))
            setCurrentLanguage(Language.VIETNAMESE);
        else
            setCurrentLanguage(Language.ENGLISH);
    }

    public static Language getCurrentLanguage() {
        return language;
    }

    public static void setCurrentLanguage(Language language) {
        I18n.language = language;
        resourceBundle = ResourceBundle.getBundle("i18n.messages", I18n.language.locale);
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
