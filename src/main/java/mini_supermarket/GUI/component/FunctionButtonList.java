package mini_supermarket.GUI.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class FunctionButtonList extends RoundPanel {
    private final RoundPanel mainFunction;
    private final Button btnLeftArrow;
    private final Button btnRightArrow;
    private final RoundPanel panelCenter;
    private final JScrollPane scrollListFunc;
    private final RoundPanel panelFunctions;
    private final FunctionButton[] buttons;

    public FunctionButtonList(List<String> functionNames) {
        super(20);
        setLayout(new BorderLayout());

        mainFunction = new RoundPanel(20);
        btnLeftArrow = new Button();
        btnRightArrow = new Button();
        panelCenter = new RoundPanel(20);
        scrollListFunc = new JScrollPane();
        panelFunctions = new RoundPanel(20);
        buttons = new FunctionButton[functionNames.size()];

        mainFunction.setBackground(new Color(215, 215, 215));
        mainFunction.setLayout(new BorderLayout());
        mainFunction.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                int width = FunctionButtonList.super.getWidth();
                if (width < (80 + 5) * 6 + 5) {
                    btnLeftArrow.setVisible(true);
                    btnRightArrow.setVisible(true);
                } else {
                    btnLeftArrow.setVisible(false);
                    btnRightArrow.setVisible(false);
                }
            }
        });
        this.add(mainFunction);

        btnLeftArrow.setRadius(20);
        btnLeftArrow.setColor(new Color(0x737070));
        btnLeftArrow.setColorOver(new Color(0x737070));
        btnLeftArrow.setColorClick(new Color(0xB0ADAD));
        btnLeftArrow.setPreferredSize(new Dimension(20, 100));
        btnLeftArrow.addActionListener(e -> {
            JScrollBar horizontalScrollBar = scrollListFunc.getHorizontalScrollBar();
            int currentValue = horizontalScrollBar.getValue();
            horizontalScrollBar.setValue(currentValue - 85);
        });
        mainFunction.add(btnLeftArrow, BorderLayout.WEST);

        btnRightArrow.setRadius(20);
        btnRightArrow.setColor(new Color(0x737070));
        btnRightArrow.setColorOver(new Color(0x737070));
        btnRightArrow.setColorClick(new Color(0xB0ADAD));
        btnRightArrow.setPreferredSize(new Dimension(20, 100));
        btnRightArrow.addActionListener(e -> {
            JScrollBar horizontalScrollBar = scrollListFunc.getHorizontalScrollBar();
            int currentValue = horizontalScrollBar.getValue();
            horizontalScrollBar.setValue(currentValue + 85);
        });
        mainFunction.add(btnRightArrow, BorderLayout.EAST);

        panelCenter.setBackground(new Color(215, 215, 215));
        panelCenter.setLayout(new GridBagLayout());
        mainFunction.add(panelCenter, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.insets = new Insets(3, 3, 2, 2);

        scrollListFunc.setBorder(null);
//        scrollListFunc.getViewport().setBackground(new Color(215, 215, 215));
        scrollListFunc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollListFunc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelCenter.add(scrollListFunc, gbc);

        panelFunctions.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelFunctions.setBackground(new Color(215, 215, 215));
        scrollListFunc.getViewport().add(panelFunctions);

        for (int i = 0; i < functionNames.size(); i++) {
            String functionName = functionNames.get(i);
            if (functionName.equals("view"))
                continue;
            buttons[i] = new FunctionButton(functionName);
            panelFunctions.add(buttons[i]);
        }
    }

    public FunctionButton[] getButtons() {
        return buttons;
    }

    public int getSized() {
        return this.getWidth();
    }
}
