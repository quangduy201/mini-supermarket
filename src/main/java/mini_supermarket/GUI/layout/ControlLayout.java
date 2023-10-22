package mini_supermarket.GUI.layout;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.FunctionButton;
import mini_supermarket.GUI.component.FunctionButtonList;
import mini_supermarket.GUI.component.RoundPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlLayout extends BottomTopLayout {
    private final RoundPanel topPanel;
    private final RoundPanel bottomPanel;
    private RoundPanel mainFunction;
    private List<Function> functions;
    private RoundPanel searchFunction;
    private JComboBox comboBoxFilter;
    private JTextField jTextFieldSearch;
    private RoundPanel resetPanel;
    private FunctionButton btnRefresh;

    public ControlLayout(List<Function> functions) {
        super(100, true, 20, 5);
        setBackground(new Color(240, 240, 240));
        this.topPanel = this.getTopPanel();
        this.bottomPanel = this.getBottomPanel();
        this.functions = functions;
        init();
    }

    public void init() {
        List<String> functionNames = new ArrayList<>();
        for (Function function : functions)
            functionNames.add(function.getName());
        FunctionButtonList listFunction = new FunctionButtonList(functionNames);
        LeftRightLayout functionLayout = new LeftRightLayout(500, false, 20, 0);
//        functionLayout.layoutBackground(new Color(215,215,215));
        topPanel.setLayout(new BorderLayout());
        topPanel.add(functionLayout, BorderLayout.CENTER);

        mainFunction = functionLayout.getLeftPanel();
        searchFunction = functionLayout.getRightPanel();

        searchFunction.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        comboBoxFilter = new JComboBox<>();
        comboBoxFilter.setPreferredSize(new Dimension(150, 40));
        searchFunction.add(comboBoxFilter);

        jTextFieldSearch = new JTextField();
        jTextFieldSearch.setPreferredSize(new Dimension(230, 40));
        searchFunction.add(jTextFieldSearch);

        resetPanel = new RoundPanel();
        resetPanel.setPreferredSize(new Dimension(80, 90));
        searchFunction.add(resetPanel);

        mainFunction.setLayout(new BorderLayout());
        mainFunction.add(listFunction, BorderLayout.CENTER);

        btnRefresh = new FunctionButton("refresh");
        resetPanel.setBackground(new Color(215, 215, 215));
        resetPanel.add(btnRefresh, BorderLayout.CENTER);
    }
}
