package mini_supermarket.utils;

import mini_supermarket.GUI.DatabaseConfig;
import mini_supermarket.main.MiniSupermarket;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            buildSessionFactory();
        return sessionFactory;
    }

    public static void initialize() {
        buildSessionFactory();
    }

    public static void shutdown() {
        if (sessionFactory != null)
            sessionFactory.close();
    }

    public static List<String> getDatabaseConfiguration() {
        List<String> result = new ArrayList<>();
        Properties properties = Resource.loadProperties(Settings.DATABASE_FILE, false);
        if (properties == null) {
            Log.debug(HibernateUtil.class, "Can't load the properties file: " + Settings.DATABASE_FILE);
            return result;
        }
        String dbPort = properties.getProperty("db.port");
        String dbDatabase = properties.getProperty("db.database");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        if (dbDatabase == null) {
            Log.debug(HibernateUtil.class, "Can't read the database property: " + Settings.DATABASE_FILE);
            return result;
        }
        dbPassword = Password.decrypt(dbPassword, dbDatabase);
        String dbUrl = String.format("jdbc:mysql://localhost:%s/%s", dbPort, dbDatabase);
        result.add(dbUrl);
        result.add(dbUsername);
        result.add(dbPassword);
        return result;
    }

    public static boolean testConnection() {
        List<String> properties = getDatabaseConfiguration();
        if (properties.isEmpty()) {
            return false;
        }
        try (Connection ignored = DriverManager.getConnection(properties.get(0), properties.get(1), properties.get(2))) {
            return true;
        } catch (SQLException e) {
            Log.debug(HibernateUtil.class, "Can't get the connection to: " + properties.get(0));
            return false;
        }
    }

    public static void buildSessionFactory() {
        while (!testConnection()) {
            initializeDatabase();
        }
        List<String> properties = getDatabaseConfiguration();
        Configuration configuration = new Configuration().configure("settings/hibernate.cfg.xml");
        configuration.setProperty("hibernate.connection.url", properties.get(0));
        configuration.setProperty("hibernate.connection.username", properties.get(1));
        configuration.setProperty("hibernate.connection.password", properties.get(2));

        sessionFactory = configuration.buildSessionFactory();
        Log.debug(HibernateUtil.class, "Successfully connected to the database: " + properties.get(0));
    }

    public static void initializeDatabase() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {

        }
        if (SwingUtilities.isEventDispatchThread()) {
            JOptionPane.showMessageDialog(MiniSupermarket.login,
                I18n.get("frame", "error.database.restart"),
                I18n.get("dialog", "title.error"), JOptionPane.ERROR_MESSAGE);
            MiniSupermarket.exit(1);
        }
        JOptionPane.showMessageDialog(MiniSupermarket.splashScreen,
            I18n.get("frame", "error.database"),
            I18n.get("dialog", "title.error"), JOptionPane.ERROR_MESSAGE);
        Properties properties = Resource.loadProperties(Settings.DATABASE_FILE, false);
        if (properties == null) {
            Resource.copyResource(Settings.DATABASE_FILE, Settings.DATABASE_FILE, true);
            properties = Resource.loadProperties(Settings.DATABASE_FILE, false);
        }
        new DatabaseConfig(properties);
        do {
            properties = Resource.loadProperties(Settings.DATABASE_FILE, false);
        } while (properties == null || properties.size() != 4 || !properties.containsKey("db.password") || properties.getProperty("db.password").isEmpty());
    }
}
