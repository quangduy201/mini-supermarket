package mini_supermarket.GUI.layout;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.FunctionButton;
import mini_supermarket.GUI.component.FunctionButtonList;
import mini_supermarket.GUI.component.RoundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ControlLayout extends BottomTopLayout {
    private RoundPanel mainFunction;
    private List<Function> functions;
    private FunctionButtonList listFunction;
    private RoundPanel searchFunction;
    private JComboBox comboBoxFilter;
    private JTextField jTextFieldSearch;
    private RoundPanel resetPanel;
    private FunctionButton btnRefresh;

    public ControlLayout(List<Function> functions) {
        super(100, true, 20, 5);
        this.functions = functions;
        functions.removeIf(f -> f.getName().equals("view"));
        init();
    }

    public void init() {
        getTopPanel().setLayout(new BorderLayout());

        LeftRightLayout functionLayout = new LeftRightLayout(500, false, 20, 0);
        getTopPanel().add(functionLayout, BorderLayout.CENTER);

        mainFunction = functionLayout.getLeftPanel();
        mainFunction.setBackground(null);
        mainFunction.setLayout(new BorderLayout());

        List<String> functionNames = new ArrayList<>();
        for (Function function : functions)
            functionNames.add(function.getName());
        listFunction = new FunctionButtonList(functionNames);
        mainFunction.add(listFunction, BorderLayout.CENTER);

        searchFunction = functionLayout.getRightPanel();
        searchFunction.setBackground(null);
        searchFunction.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        comboBoxFilter = new JComboBox<>();
        comboBoxFilter.setPreferredSize(new Dimension(150, 40));
        searchFunction.add(comboBoxFilter);

        jTextFieldSearch = new JTextField();
        jTextFieldSearch.setPreferredSize(new Dimension(230, 40));
        searchFunction.add(jTextFieldSearch);

        resetPanel = new RoundPanel();
        resetPanel.setBackground(null);
        resetPanel.setPreferredSize(new Dimension(80, 90));
        searchFunction.add(resetPanel);

        btnRefresh = new FunctionButton("refresh");
        resetPanel.add(btnRefresh, BorderLayout.CENTER);
        addActionListenerToFunctionButtons();
    }

    public void addActionListenerToFunctionButtons() {
        List<FunctionButton> functionButtons = listFunction.getButtons();
        for (int i = 0; i < functionButtons.size(); i++) {
            final int index = i;
            functionButtons.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String functionName = functions.get(index).getName();
                    doFunction(functionName);
                }
            });
        }
        btnRefresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                doFunction("refresh");
            }
        });
    }

    public void doFunction(String functionName) {
        switch (functionName) {
            case "add" -> add();
            case "edit" -> edit();
            case "remove" -> remove();
            case "detail" -> detail();
            case "excel" -> excel();
            case "pdf" -> pdf();
            case "refresh" -> refresh();
            default -> throw new UnsupportedOperationException("Unsupported operation: " + functionName);
        };
    }

    public void add() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public void edit() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public void detail() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public void excel() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public void pdf() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public void refresh() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
