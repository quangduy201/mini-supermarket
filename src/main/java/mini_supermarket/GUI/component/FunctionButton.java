package mini_supermarket.GUI.component;

import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Resource;

import javax.swing.*;
import java.awt.*;

public class FunctionButton extends RoundPanel {
    private final JLabel icon;
    private final JLabel name;

    public FunctionButton(String functionName) {
        super(20);
        setBackground(new Color(215, 215, 215));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(80, 80));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        icon = new JLabel();
        name = new JLabel();

        icon.setPreferredSize(new Dimension(50, 50));
        icon.setIcon(Resource.loadSVGIcon("img/icon/" + functionName + ".svg", 50));
        this.add(icon);

        name.setPreferredSize(new Dimension(80, 20));
        name.setText(I18n.get("frame", "function." + functionName));
        name.setForeground(new Color(0x000000));
        name.setFont(new Font("Time new Romans", Font.BOLD, 18));
        name.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(name);
    }
}
