package mini_supermarket.main;

import mini_supermarket.DTO.Account;
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
            Settings.initialize();
            UI.initialize();
            I18n.initialize();

            Thread initSettings = new Thread(HibernateUtil::initialize);
            initSettings.start();
            MiniSupermarket.initialize();
            initSettings.join();
        } catch (Exception e) {
            Log.error("Cannot initialize the application.");
        }
        splashScreen.dispose();
        if (login != null)
            login.setVisible(true);
        else
            main.setVisible(true);
    }

    public static void initialize() {
        splashScreen = new SplashScreen();
        Account account = Settings.getLastAccount();
        if (account == null)
            login = new Login();
        else {
            main = new Main(account);
        }
        threadTime = new Thread(() -> {
            while (true) {
                if (main == null)
                    continue;
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
