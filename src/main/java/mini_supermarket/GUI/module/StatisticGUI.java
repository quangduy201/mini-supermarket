package mini_supermarket.GUI.module;

import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.EmptyLayout;

import java.util.List;

public class StatisticGUI extends EmptyLayout {
    private final RoundPanel panelStatistic;

    public StatisticGUI(List<Function> functions) {
        panelStatistic = getPanel();
//        this.setLayout(new GridBagLayout());
//        mainStatistic = new RoundPanel(20);
//        mainStatistic.setBackground(new Color(215, 215, 215));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.weightx = 1.0;
//        gbc.weighty = 1.0;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(5, 5, 5, 5);
//        this.add(mainStatistic, gbc);

    }
}
