package mini_supermarket.GUI.module;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import java.util.List;

public class CustomerGUI extends ControlLayout {
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private LeftRightLayout layoutFormAndData;

    public CustomerGUI(List<Function> functions) {
        super(functions);
        panelFunction = getTopPanel();

        panelData = getBottomPanel();
    }
}
