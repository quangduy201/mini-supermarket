package mini_supermarket.utils;

import mini_supermarket.GUI.DatabaseConfig;
import mini_supermarket.main.MiniSupermarket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
    private static String password = null;
    private static SessionFactory sessionFactory;

    public static void buildSessionFactory() {
        do {
            try {
                Configuration configuration = new Configuration().configure("settings/hibernate.cfg.xml");
                Properties properties = Resource.loadProperties("settings/db.properties", false);
                assert properties != null;
                String dbPort = properties.getProperty("db.port");
                String dbDatabase = properties.getProperty("db.database");
                String dbUsername = properties.getProperty("db.username");
                String dbPassword = properties.getProperty("db.password");
                if (dbDatabase == null)
                    throw new RuntimeException();
                if (password == null)
                    password = Password.decrypt(dbPassword, dbDatabase);
                String dbUrl = String.format("jdbc:mysql://localhost:%s/%s", dbPort, dbDatabase);
                configuration.setProperty("hibernate.connection.url", dbUrl);
                configuration.setProperty("hibernate.connection.username", dbUsername);
                configuration.setProperty("hibernate.connection.password", password);

                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                password = null;
                initializeDatabase();
            }
        } while (password == null);
    }

    public static void initialize() {
        buildSessionFactory();
    }

    public static void initializeDatabase() {
        if (SwingUtilities.isEventDispatchThread()) {
            JOptionPane.showMessageDialog(MiniSupermarket.login, "Không thể kết nối đến cơ sở dữ liệu.\nVui lòng khởi động lại chương trình", "Lỗi", JOptionPane.ERROR_MESSAGE);
            MiniSupermarket.exit(1);
        }
        JOptionPane.showMessageDialog(MiniSupermarket.splashScreen, "Không thể kết nối đến cơ sở dữ liệu.\nVui lòng cấu hình lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        Resource.copyResource(Settings.DATABASE_FILE, Settings.DATABASE_FILE, true);
        Properties properties = Resource.loadProperties(Settings.DATABASE_FILE);
        try {
            new FileOutputStream(Resource.getResourcePath(Settings.DATABASE_FILE, false)).close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        new DatabaseConfig(properties);
        do {
            properties = Resource.loadProperties(Settings.DATABASE_FILE);
        } while (properties.size() != 4 || !properties.containsKey("db.password") || properties.getProperty("db.password").isEmpty());
    }

    public static Session getSession() {
        if (sessionFactory == null)
            buildSessionFactory();
        return sessionFactory.openSession();
    }

    public static void closeSession(Session session) {
        if (session != null)
            session.close();
    }

    public static void shutdown() {
        password = null;
        if (sessionFactory != null)
            sessionFactory.close();
    }
}
