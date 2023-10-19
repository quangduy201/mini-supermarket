package mini_supermarket.GUI.module;

import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import javax.swing.*;
import java.awt.*;

public class AccountGUI extends JPanel {
    private final ControlLayout mainAccount;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private LeftRightLayout layoutFormAndData;

    public AccountGUI() {
        this.setLayout(new BorderLayout());
        mainAccount = new ControlLayout();
        this.add(mainAccount, BorderLayout.CENTER);

        panelFunction = mainAccount.getTopPanel();
        panelData = mainAccount.getBottomPanel();

    }
}
