package mini_supermarket.GUI.dialog;

import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;

import javax.swing.*;
import java.awt.*;

public class SmallDialog {
    public static void showResult(Component parent, Pair<Boolean, String> result, Runnable runIfSuccess, Runnable runIfFailed) {
        if (result.getFirst()) {
            String title = I18n.get("dialog", "title.info");
            JOptionPane.showMessageDialog(null, result.getSecond(), title, JOptionPane.INFORMATION_MESSAGE);
            if (runIfSuccess != null)
                runIfSuccess.run();
        } else {
            String title = I18n.get("dialog", "title.error");
            JOptionPane.showMessageDialog(parent, result.getSecond(), title, JOptionPane.ERROR_MESSAGE);
            if (runIfFailed != null)
                runIfFailed.run();
        }
    }

    public static int showOptionDialogWhenDeleting(Component parent, String message) {
        String[] options = new String[]{
            I18n.get("dialog", "yes"),
            I18n.get("dialog", "no")
        };
        return JOptionPane.showOptionDialog(parent, message,
            I18n.get("dialog", "title.question"),
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    public static int showErrorWhileImporting(Component parent, String message) {
        String title = I18n.get("dialog", "title.error");
        Object[] options = new Object[]{
            I18n.get("dialog", "skip"),
            I18n.get("dialog", "stop")
        };
        return JOptionPane.showOptionDialog(parent, message, title,
            JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
    }

    public static void showErrorWhenDataTableIsNotSelected(Component parent) {
        JOptionPane.showMessageDialog(parent,
            I18n.get("messages", "data_table.selected.not"),
            I18n.get("dialog", "title.info"), JOptionPane.INFORMATION_MESSAGE);
    }
}
