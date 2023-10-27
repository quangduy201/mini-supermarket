package mini_supermarket.GUI.component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class FunctionButtonList extends RoundPanel {
    private final Button btnLeftArrow;
    private final Button btnRightArrow;
    private final RoundPanel panelCenter;
    private final JScrollPane scrollListFunc;
    private final RoundPanel panelFunctions;
    private final FunctionButton[] buttons;

    public FunctionButtonList(List<String> functionNames) {
        super(20);
        this.setBackground(null);
        this.setLayout(new BorderLayout());
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                int width = FunctionButtonList.super.getWidth();
                int minWidth = (FunctionButton.BUTTON_WIDTH + 5) * (functionNames.size() - 1) + 5;
                if (width < minWidth) {
                    btnLeftArrow.setVisible(true);
                    btnRightArrow.setVisible(true);
                    scrollListFunc.setBorder(null);
                } else {
                    btnLeftArrow.setVisible(false);
                    btnRightArrow.setVisible(false);
                    scrollListFunc.setBorder(new EmptyBorder(0, 10, 0, 0));
                }
            }
        });

        btnLeftArrow = new Button();
        btnRightArrow = new Button();
        panelCenter = new RoundPanel(20);
        scrollListFunc = new JScrollPane();
        panelFunctions = new RoundPanel(20);
        buttons = new FunctionButton[functionNames.size()];

        btnLeftArrow.setRadius(20);
        btnLeftArrow.setColor(new Color(0x737070));
        btnLeftArrow.setColorOver(new Color(0x737070));
        btnLeftArrow.setColorClick(new Color(0xB0ADAD));
        btnLeftArrow.setPreferredSize(new Dimension(20, 100));
        btnLeftArrow.setVisible(false);
        btnLeftArrow.addActionListener(e -> {
            JScrollBar horizontalScrollBar = scrollListFunc.getHorizontalScrollBar();
            int currentValue = horizontalScrollBar.getValue();
            horizontalScrollBar.setValue(currentValue - FunctionButton.BUTTON_WIDTH + 5);
        });
        this.add(btnLeftArrow, BorderLayout.WEST);

        btnRightArrow.setRadius(20);
        btnRightArrow.setColor(new Color(0x737070));
        btnRightArrow.setColorOver(new Color(0x737070));
        btnRightArrow.setColorClick(new Color(0xB0ADAD));
        btnRightArrow.setPreferredSize(new Dimension(20, 100));
        btnRightArrow.setVisible(false);
        btnRightArrow.addActionListener(e -> {
            JScrollBar horizontalScrollBar = scrollListFunc.getHorizontalScrollBar();
            int currentValue = horizontalScrollBar.getValue();
            horizontalScrollBar.setValue(currentValue + FunctionButton.BUTTON_WIDTH + 5);
        });
        this.add(btnRightArrow, BorderLayout.EAST);

        panelCenter.setBackground(null);
        panelCenter.setLayout(new GridBagLayout());
        this.add(panelCenter, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.insets = new Insets(3, 3, 2, 2);

        scrollListFunc.setBorder(new EmptyBorder(0, 10, 0, 0));
        scrollListFunc.setBackground(null);
        scrollListFunc.getViewport().setBackground(null);
        scrollListFunc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollListFunc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelCenter.add(scrollListFunc, gbc);

        panelFunctions.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelFunctions.setBackground(null);
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
