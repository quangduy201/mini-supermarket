package mini_supermarket.main;

import mini_supermarket.GUI.Login;
import mini_supermarket.GUI.Main;
import mini_supermarket.GUI.SplashScreen;
import mini_supermarket.utils.*;

import java.time.format.DateTimeFormatter;

public class MiniSupermarket {
    public static SplashScreen splashScreen;
    public static Login login;
    public static Main main;
    public static Thread threadTime;

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        try {
            Log.initialize();
            UI.initialize();

            Thread initSettings = new Thread(() -> {
                Settings.initialize();
                I18n.initialize();
                HibernateUtil.initialize();
            });
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
        threadTime = new Thread(() -> {
            while (true) {
                if (main == null)
                    return;
                DateTime now = DateTime.now();
                main.setTime(now.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss a")));
            }
        });
        threadTime.start();
    }

    public static void exit(int status) {
        HibernateUtil.shutdown();
        System.exit(status);
    }
}
