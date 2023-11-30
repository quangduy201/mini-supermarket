package mini_supermarket.GUI.module;

import mini_supermarket.BLL.PromotionBLL;
import mini_supermarket.DTO.Function;
import mini_supermarket.DTO.Promotion;
import mini_supermarket.GUI.component.CustomTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.dialog.AccountDialog;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PromotionGUI extends ControlLayout {
    private final PromotionBLL promotionBLL;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
     private CustomTable dataTable;

    private Long[] idsOfCurrentData;

    public PromotionGUI(List<Function> functions) {
        super(functions);
        promotionBLL = new PromotionBLL();
        panelFunction = getTopPanel();

        panelData = getBottomPanel();
        panelData.setLayout(new GridBagLayout());

        refresh();
    }


    public CustomTable getDataTable(List<Promotion> promotions){
        Object[][] ids = promotionBLL.getData(promotions, false, List.of(
            new Pair<>(__.PROMOTION.COLUMN.ID, Long::parseLong)
        ));

        idsOfCurrentData = Arrays.stream(ids)
            .map(row -> (Long) row[0])
            .toArray(Long[]::new);

        Object[][] data = promotionBLL.getData(promotions, false, List.of(
            new Pair<>(__.PROMOTION.COLUMN.NAME, String ::toString),
           new Pair<>(__.PROMOTION.COLUMN.START_DATE, Date::parse),
            new Pair<>(__.PROMOTION.COLUMN.END_DATE, Date::parse),
            new Pair<>(__.PROMOTION.COLUMN.STATUS, s -> Boolean.parseBoolean(s) ? "on" : "off")

        ));
        return new CustomTable(data,
            new Object[]{"Promotion","Start","End", "Status"},
            new Integer[]{100, 1, 1, 1},
            this::detail, true,
            new Pair<>(Date.class, 3)
            );
    }





    @Override
    public void add() {
        // TODO
    }

    @Override
    public void edit() {
        // TODO
    }

    @Override
    public void refresh() {
        panelData.removeAll();
        panelData.revalidate();
        panelData.repaint();
        dataTable = getDataTable(promotionBLL.findBy(__.PROMOTION.ID, false));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelData.add(dataTable, gbc);
        jTextFieldSearch.setText("");
        System.gc();
    }
}
