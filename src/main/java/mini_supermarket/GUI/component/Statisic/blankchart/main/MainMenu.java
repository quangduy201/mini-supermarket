package mini_supermarket.GUI.component.Statisic.blankchart.main;

import mini_supermarket.BLL.DecentralizationBLL;
import mini_supermarket.DTO.Module;
import mini_supermarket.DTO.*;
import mini_supermarket.GUI.component.Button;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.BottomTopLayout;
import mini_supermarket.GUI.module.*;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends BottomTopLayout {
    public static final int WIDTH = 210;
    public static final int ITEM_WIDTH = 235;
    public static final int ITEM_HEIGHT = 45;
    public static final int VERTICAL_GAP = 5;
    private final MainUserInfo mainUserInfo;
    private final BottomTopLayout menuLayout;
    private final JScrollPane scrollPane;
    private final RoundPanel panelModules;
    private final Button[] btnModules;
    private final Button btnLogout;

    public MainMenu(Account account) {
        super(120, true, 20, 5);
        getBottomPanel().setLayout(new GridBagLayout());

        Pair<List<Module>, List<List<Function>>> result = getModulesAndFunctionsFromRole(account.getRole());
        List<Module> modules = result.getFirst();
        List<List<Function>> function2D = result.getSecond();

        mainUserInfo = new MainUserInfo(account);
        menuLayout = new BottomTopLayout(50, false, 20, 5);
        scrollPane = new JScrollPane();
        panelModules = new RoundPanel(20);
        btnModules = new Button[modules.size()];
        btnLogout = new Button();

        getTopPanel().add(mainUserInfo);
        getTopPanel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
//        menuLayout.layoutBackground(Color.BLUE, new Color(255, 0, 0));
        menuLayout.getBottomPanel().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        menuLayout.getTopPanel().setLayout(new GridBagLayout());
        getBottomPanel().add(menuLayout, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        scrollPane.setBorder(null);
        scrollPane.setBackground(null);
        scrollPane.getViewport().setBackground(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        menuLayout.getTopPanel().add(scrollPane, gbc);

        int height = ((ITEM_HEIGHT + VERTICAL_GAP) * (modules.size())) + 2 * VERTICAL_GAP;
        panelModules.setPreferredSize(new Dimension(WIDTH, height));
        panelModules.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelModules.setBackground(null);
        scrollPane.getViewport().add(panelModules);

        for (int i = 0; i < modules.size(); i++) {
            btnModules[i] = new Button();
            btnModules[i].setColor(new Color(243, 240, 240));
            btnModules[i].setColorOver(new Color(255, 255, 255));
            btnModules[i].setColorClick(new Color(176, 173, 173));
            btnModules[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnModules[i].setRadius(20);
            btnModules[i].setPreferredSize(new Dimension(ITEM_WIDTH, ITEM_HEIGHT));
            Module module = modules.get(i);
            List<Function> functions = function2D.get(i);
            btnModules[i].setText(I18n.get("frame", "main.module." + module.getName()));
            JPanel panelModule = getPanelModule(module.getId(), functions);
            btnModules[i].addActionListener(e -> openModule(panelModule));
            panelModules.add(btnModules[i]);
        }

        btnLogout.setText(I18n.get("frame", "main.logout"));
        btnLogout.setColor(new Color(240, 240, 240));
        btnLogout.setColorOver(new Color(150, 150, 150));
        btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogout.setRadius(20);
        btnLogout.setPreferredSize(new Dimension(230, ITEM_HEIGHT));
        btnLogout.addActionListener(e -> MiniSupermarket.main.logout());
        menuLayout.getBottomPanel().add(btnLogout);
    }

    public Pair<List<Module>, List<List<Function>>> getModulesAndFunctionsFromRole(Role role) {
        DecentralizationBLL decentralizationBLL = new DecentralizationBLL();
        List<Decentralization> decentralizations = decentralizationBLL.findBy(__.DECENTRALIZATION.ROLE, role);
        List<Module> modules = new ArrayList<>();
        List<List<Function>> function2D = new ArrayList<>();
        for (int i = 0; i < decentralizations.size(); i++) {
            Module module = decentralizations.get(i).getId().getModule();
            modules.add(module);
            List<Function> functions = new ArrayList<>();
            do {
                functions.add(decentralizations.get(i).getId().getFunction());
            } while (++i < decentralizations.size() && decentralizations.get(i).getId().getModule() == module);
            function2D.add(functions);
            i--;
        }
        return new Pair<>(modules, function2D);
    }

    public JPanel getPanelModule(long id, List<Function> functions) {
        return switch ((int) id) {
            case 1 -> new HomeGUI(functions);
            case 2 -> new SaleGUI(functions);
            case 3 -> new WarehouseGUI(functions);
            case 4 -> new StatisticGUI(functions);
            case 5 -> new DiscountGUI(functions);
            case 6 -> new PromotionGUI(functions);
            case 7 -> new ReceiptGUI(functions);
            case 8 -> new ExportNoteGUI(functions);
            case 9 -> new ImportNoteGUI(functions);
            case 10 -> new ProductGUI(functions);
            case 11 -> new SupplierGUI(functions);
            case 12 -> new CustomerGUI(functions);
            case 13 -> new StaffGUI(functions);
            case 14 -> new AccountGUI(functions);
            case 15 -> new DecentralizationGUI(functions);
            default -> null;
        };
    }

    public void openModule(JPanel module) {
        RoundPanel mainRightPanel = MiniSupermarket.main.getMainLayout().getRightPanel();
        mainRightPanel.removeAll();
        mainRightPanel.add(module);
        mainRightPanel.repaint();
        mainRightPanel.revalidate();
        System.gc();
    }
}
