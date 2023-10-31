package mini_supermarket.GUI.component;

import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Resource;

import javax.swing.*;
import java.awt.*;

public class FunctionButton extends RoundPanel {
    public static final int BUTTON_WIDTH = 80;
    public static final int BUTTON_HEIGHT = 80;
    private final JLabel icon;
    private final JLabel functionName;

    public FunctionButton(String name) {
        super(20);
        setBackground(null);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        icon = new JLabel();
        functionName = new JLabel();

        icon.setPreferredSize(new Dimension(50, 50));
        icon.setIcon(Resource.loadSVGIcon("img/icon/" + name + ".svg", 50));
        this.add(icon);

        functionName.setPreferredSize(new Dimension(80, 20));
        functionName.setText(I18n.get("frame", "function." + name));
        functionName.setForeground(new Color(0x000000));
        functionName.setFont(new Font("Time new Romans", Font.BOLD, 18));
        functionName.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(functionName);
    }

    public JLabel getIcon() {
        return icon;
    }

    public JLabel getFunctionName() {
        return functionName;
    }
}
