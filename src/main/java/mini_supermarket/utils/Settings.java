package mini_supermarket.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Settings {
    public static final String CONFIG_FILE = "settings/config.properties";
    public static final String DATABASE_FILE = "settings/db.properties";
    public static final String EMAIL_FILE = "settings/email.properties";

    public static void initialize() {
        try {
            Files.createDirectories(Paths.get(Resource.getResourcePath("settings", false)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        Resource.copyResource(DATABASE_FILE, DATABASE_FILE);
        Resource.copyResource(EMAIL_FILE, EMAIL_FILE);
        Resource.copyResource(CONFIG_FILE, CONFIG_FILE);
    }
}
