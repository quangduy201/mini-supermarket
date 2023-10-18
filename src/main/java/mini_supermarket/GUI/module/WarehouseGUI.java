package mini_supermarket.GUI.module;

import mini_supermarket.GUI.layout.ControlLayout;

import javax.swing.*;
import java.awt.*;

public class WarehouseGUI extends JPanel {
    private ControlLayout mainWarehouse;
    public WarehouseGUI() {
        this.setLayout(new BorderLayout());
        mainWarehouse = new ControlLayout();
        this.add(mainWarehouse, BorderLayout.CENTER);
    }
}
