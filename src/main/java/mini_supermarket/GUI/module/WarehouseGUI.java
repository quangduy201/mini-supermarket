package mini_supermarket.GUI.module;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.layout.ControlLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WarehouseGUI extends JPanel {
    private final ControlLayout mainWarehouse;

    public WarehouseGUI(List<Function> functions) {
        this.setLayout(new BorderLayout());
        mainWarehouse = new ControlLayout(functions);
        this.add(mainWarehouse, BorderLayout.CENTER);
    }
}
