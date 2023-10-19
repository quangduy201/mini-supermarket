package mini_supermarket.utils;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

public class UI {
    public static void initialize() {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatIntelliJLaf.setup();
        UIManager.put("Button.textIconGap", 10);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("ProgressBar.cycleTime", 6000);
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("TextComponent.arc", 5);
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
