package mini_supermarket.main;

import mini_supermarket.GUI.Login;
import mini_supermarket.GUI.Main;
import mini_supermarket.GUI.SplashScreen;
import mini_supermarket.utils.*;

public class MiniSupermarket {
    public static SplashScreen splashScreen;
    public static Login login;
    public static Main main;

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        try {
            Thread initSettings = new Thread(() -> {
                Settings.initialize();
                HibernateUtil.initialize();
            });
            Log.initialize();
            I18n.initialize();
            UI.initialize();
            initSettings.start();
            MiniSupermarket.initialize();
            initSettings.join();
        } catch (Exception e) {
            Log.error("Cannot initialize the application.");
        }
        splashScreen.dispose();
        login.setVisible(true);
    }

    public static void initialize() {
        splashScreen = new SplashScreen();
        login = new Login();
        main = new Main();
    }

    public static void exit(int status) {
        HibernateUtil.shutdown();
        System.exit(status);
    }
}
