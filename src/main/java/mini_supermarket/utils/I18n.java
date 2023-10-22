package mini_supermarket.utils;

import java.util.*;
import java.util.regex.Matcher;

public class I18n {
    private static final int CACHE_SIZE_LIMIT = 50;
    private static Map<String, ResourceBundle> resourceBundleCache = new LinkedHashMap<>(CACHE_SIZE_LIMIT + 1, 0.75F, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, ResourceBundle> eldest) {
            return size() > CACHE_SIZE_LIMIT;
        }
    };
    private static Language language;

    public static void initialize() {
        Properties properties = Resource.loadProperties(Settings.CONFIG_FILE, false);
        if (properties == null) {
            setCurrentLanguage(Language.VIETNAMESE);
            return;
        }
        String language = properties.getProperty("language");
        if (language == null || language.equalsIgnoreCase("vi"))
            setCurrentLanguage(Language.VIETNAMESE);
        else
            setCurrentLanguage(Language.ENGLISH);
    }

    public static Language getCurrentLanguage() {
        return language;
    }

    public static void setCurrentLanguage(Language language) {
        I18n.language = language;
    }

    public static String get(String baseName, String key, Object... args) {
        ResourceBundle bundle = getResourceBundle("i18n." + baseName);
        return getString(bundle, key, args);
    }

    private static ResourceBundle getResourceBundle(String baseName) {
        if (!resourceBundleCache.containsKey(baseName)) {
            ResourceBundle bundle = ResourceBundle.getBundle(baseName, language.locale);
            resourceBundleCache.put(baseName, bundle);
        }
        return resourceBundleCache.get(baseName);
    }

    private static String getString(ResourceBundle bundle, String key, Object... args) {
        try {
            String s = bundle.getString(key);
            s = replaceArgs(s, args);
            return s;
        } catch (Exception e) {
            Log.error(I18n.class, "Error while getting the string value of the key '" + key + "': " + e);
            return key;
        }
    }

    private static String replaceArgs(String message, Object... args) {
        // Replace placeholders like {0}, {1}, {2}, ... with provided arguments
        for (int i = 0; i < args.length; i++) {
            String placeholder = "\\{%d\\}".formatted(i);
            message = message.replaceAll(placeholder, Matcher.quoteReplacement(String.valueOf(args[i])));
        }
        return message;
    }

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

        @Override
        public String toString() {
            return locale.getLanguage();
        }
    }
}
