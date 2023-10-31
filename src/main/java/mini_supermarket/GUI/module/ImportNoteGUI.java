package mini_supermarket.GUI.module;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;

import java.awt.*;
import java.util.List;

public class ImportNoteGUI extends ControlLayout {
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private final LeftRightLayout layoutFormAndData;
    private RoundPanel panelForm;
    private RoundPanel panelDetailData;

    public ImportNoteGUI(List<Function> functions) {
        super(functions);
        panelFunction = getTopPanel();

        panelData = getBottomPanel();
        panelData.setBackground(null);
        panelData.setLayout(new BorderLayout());

        layoutFormAndData = new LeftRightLayout(1, 3, 20, 5, 0);
        panelData.add(layoutFormAndData, BorderLayout.CENTER);
    }

    @Override
    public void add() {
        // TODO
    }

    @Override
    public void detail() {
        // TODO
    }

    @Override
    public void excel() {
        // TODO
    }

    @Override
    public void pdf() {
        // TODO
    }

    @Override
    public void refresh() {
        // TODO
    }
}
