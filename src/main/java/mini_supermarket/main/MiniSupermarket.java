package mini_supermarket.main;

import mini_supermarket.DTO.Account;
import mini_supermarket.GUI.ForgottenPassword;
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
            Log.fatal(e.toString());
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
            if (account.getPassword().startsWith("first")) {
                ForgottenPassword forgottenPassword = new ForgottenPassword();
                forgottenPassword.setAccount(account);
                forgottenPassword.toStep(3);
                forgottenPassword.setVisible(true);
            }
        }
    }

    public static void restart() {
        HibernateUtil.shutdown();
        I18n.shutdown();
        Log.shutdown();
        if (splashScreen != null)
            splashScreen.dispose();
        if (login != null)
            login.dispose();
        if (main != null)
            main.dispose();
        splashScreen = null;
        login = null;
        main = null;
        System.gc();
        start();
    }

    public static void exit(int status) {
        HibernateUtil.shutdown();
        I18n.shutdown();
        Log.shutdown();
        System.exit(status);
    }
}
