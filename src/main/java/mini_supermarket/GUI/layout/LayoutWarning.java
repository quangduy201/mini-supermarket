package mini_supermarket.GUI.layout;

import javax.swing.*;
import java.awt.*;

public class LayoutWarning extends JDialog {
    public LayoutWarning() {
        super();
        this.setSize(new Dimension(1300,600));
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(240,240,240));
        init();
    }

    private void init() {

    }
}
