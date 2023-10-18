package mini_supermarket.main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import mini_supermarket.GUI.Login;
import mini_supermarket.GUI.Main;
import mini_supermarket.GUI.SplashScreen;
import mini_supermarket.utils.HibernateUtil;
import mini_supermarket.utils.Settings;

import javax.swing.*;
import java.awt.*;

public class MiniSupermarket {
    public static SplashScreen splashScreen;
    public static Login login;
    public static Main main;

    public static void main(String[] args) {

        start();
    }

    public static void start() {
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatIntelliJLaf.setup();
        splashScreen = new SplashScreen();
        login = new Login();
        main = new Main();
    }

    public static void init() {
        Settings.initialize();
        HibernateUtil.initialize();
        splashScreen.dispose();
        main.setVisible(true);
    }

    public static void exit(int status) {
        HibernateUtil.shutdown();
        System.exit(status);
    }
}
