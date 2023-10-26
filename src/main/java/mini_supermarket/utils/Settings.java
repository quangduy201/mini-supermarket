package mini_supermarket.utils;

import mini_supermarket.BLL.AccountBLL;
import mini_supermarket.DTO.Account;
import mini_supermarket.GUI.Main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

public class Settings {
    private static Properties configurations;
    public static final String CONFIG_FILE = "settings/config.properties";
    public static final String DATABASE_FILE = "settings/db.properties";
    public static final String EMAIL_FILE = "settings/email.properties";

    public static void initialize() {
        try {
            Files.createDirectories(Resource.getPathFromResource("settings", false));
        } catch (IOException e) {
            Log.error(Settings.class, e.toString());
            return;
        }
        Resource.copyResource(DATABASE_FILE, DATABASE_FILE);
        Resource.copyResource(EMAIL_FILE, EMAIL_FILE);
        Resource.copyResource(CONFIG_FILE, CONFIG_FILE);
        configurations = Resource.loadProperties(CONFIG_FILE, false);
    }

    public static void saveConfigurations(Properties properties) {
        if (properties == null)
            return;
        Path path = Resource.getPathFromResource(CONFIG_FILE, false);
        Resource.setReadOnly(path, false);
        try (OutputStream outputStream = new FileOutputStream(path.toFile())) {
            properties.store(outputStream, "Configurations");
            Resource.setReadOnly(path, true);
        } catch (IOException e) {
            Log.error(Settings.class, e.toString());
        }
    }

    public static void setConfiguration(String key, String value) {
        configurations.setProperty(key, value);
        saveConfigurations(configurations);
    }

    public static void setTheme(UI.Theme theme) {
        UI.setCurrentTheme(theme);
        setConfiguration("theme", theme.toString());
    }

    public static void setFrameSize(int width, int height) {
        Main.setFrameWidth(width);
        Main.setFrameHeight(height);
        setConfiguration("size", width + "," + height);
    }

    public static void setLanguage(I18n.Language language) {
        I18n.setCurrentLanguage(language);
        setConfiguration("language", language.toString());
    }

    public static Account getLastAccount() {
        String accountUsername = configurations.getProperty("account");
        List<Account> accounts = new AccountBLL().findBy(__.ACCOUNT.USERNAME, accountUsername);
        if (!accounts.isEmpty())
            return accounts.get(0);
        return null;
    }

    public static void setLastAccount(Account account) {
        if (account == null)
            setConfiguration("account", "");
        else
            setConfiguration("account", account.getUsername());
    }
}
