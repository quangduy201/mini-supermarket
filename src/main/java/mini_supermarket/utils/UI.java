package mini_supermarket.utils;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class UI {
    private static Theme theme;

    public static void initialize() {
        UIManager.put("TitlePane.iconSize", new Dimension(25, 25));
        UIManager.put("TitlePane.iconMargins", new Insets(3, 5, 0, 20));
        UIManager.put("TitlePane.titleMargins", new Insets(3, 0, 0, 0));
        UIManager.put("TitlePane.buttonSize", new Dimension(45, 35));
        UIManager.put("MenuBar.selectionEmbeddedInsets", new Insets(20, 0, 0, 0));
        UIManager.put("Button.textIconGap", 10);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("ScrollPane.smoothScrolling", true);
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("ProgressBar.cycleTime", 6000);
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("TextComponent.arc", 5);
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        Properties properties = Resource.loadProperties(Settings.CONFIG_FILE, false);
        if (properties == null) {
            setCurrentTheme(Theme.LIGHT);
            return;
        }
        String theme = properties.getProperty("theme");
        if (theme == null || theme.equalsIgnoreCase("light"))
            setCurrentTheme(Theme.LIGHT);
        else
            setCurrentTheme(Theme.DARK);
    }

    public static Theme getCurrentTheme() {
        return theme;
    }

    public static void setCurrentTheme(Theme theme) {
        UI.theme = theme;
        FlatLaf.setup(theme.theme);
        if (theme.theme.isDark()) {
            UIManager.put("Panel.background", new Color(60, 63, 65));
        } else {
            UIManager.put("Panel.background", new Color(215, 215, 215));
        }
    }

    public enum Theme {
        LIGHT(new FlatIntelliJLaf(), "light"),
        DARK(new FlatDarculaLaf(), "dark");

        private final FlatLaf theme;
        private final String name;

        Theme(FlatLaf theme, String name) {
            this.theme = theme;
            this.name = name;
        }

        public FlatLaf getTheme() {
            return theme;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) {
        UIDefaults defaults = UIManager.getDefaults();
        Enumeration<Object> keys = defaults.keys();
        List<Object> keyList = new ArrayList<>();

        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            keyList.add(key);
        }
        keyList.sort(Comparator.comparing(Object::toString));
        for (Object key : keyList) {
            System.out.println(key);
        }
        System.out.println(keyList.size() + " keys.");
    }
}
