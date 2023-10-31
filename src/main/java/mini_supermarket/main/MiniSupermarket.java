package mini_supermarket.main;

import mini_supermarket.DTO.Account;
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
            Log.initialize();
            Settings.initialize();
            UI.initialize();
            I18n.initialize();
            splashScreen = new SplashScreen();
            HibernateUtil.initialize();
            MiniSupermarket.initialize();
        } catch (Exception e) {
            Log.fatal("Cannot initialize the application.");
        }
    }

    public static void initialize() {
        Account account = Settings.getLastAccount();
        if (account == null) {
            login = new Login();
            splashScreen.dispose();
            login.setVisible(true);
        } else {
            main = new Main(account);
            splashScreen.dispose();
            main.setVisible(true);
        }
    }

    public static void exit(int status) {
        HibernateUtil.shutdown();
        System.exit(status);
    }
}
