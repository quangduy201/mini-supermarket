package mini_supermarket.GUI.dialog;

import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;

import javax.swing.*;

public class SmallDialog {
    public static void showResult(Pair<Boolean, String> result, Runnable runIfSuccess, Runnable runIfFailed) {
        if (result.getFirst()) {
            String title = I18n.get("dialog", "title.info");
            JOptionPane.showMessageDialog(MiniSupermarket.main, result.getSecond(), title, JOptionPane.INFORMATION_MESSAGE);
            if (runIfSuccess != null)
                runIfSuccess.run();
        } else {
            String title = I18n.get("dialog", "title.error");
            JOptionPane.showMessageDialog(MiniSupermarket.main, result.getSecond(), title, JOptionPane.ERROR_MESSAGE);
            if (runIfFailed != null)
                runIfFailed.run();
        }
    }
    public static int showErrorWhileImporting(String message) {
        String title = I18n.get("dialog", "title.error");
        Object[] options = new Object[]{
            I18n.get("dialog", "skip"),
            I18n.get("dialog", "stop")
        };
        return JOptionPane.showOptionDialog(MiniSupermarket.main, message, title,
            JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
    }
}
