package mini_supermarket.GUI.module;

import mini_supermarket.BLL.ProductBLL;
import mini_supermarket.DTO.Function;
import mini_supermarket.DTO.Product;
import mini_supermarket.GUI.component.Button;
import mini_supermarket.GUI.component.DataTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.dialog.LayoutWarning;
import mini_supermarket.GUI.layout.BottomTopLayout;
import mini_supermarket.GUI.dialog.LayoutForm;
import mini_supermarket.GUI.dialog.LayoutWarningTable;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class SaleGUI extends LeftRightLayout {
    private final BottomTopLayout layoutContent;
    private final LeftRightLayout layoutInformation;
    private final BottomTopLayout layoutSearchProduct;
    private final BottomTopLayout layoutFormAndButton;
    private final BottomTopLayout layoutFormAndButtonSale;
    private final RoundPanel panelLeftSale;
    private final RoundPanel panelRightSale;
    private final RoundPanel panelListProduct;
    private final RoundPanel panelDetailProduct;
    private RoundPanel mainSale;
    private RoundPanel panelSearch;
    private RoundPanel panelDetail;
    private RoundPanel panelProduct;

    private JTextField searchProduct;

    private RoundPanel listProduct;
    private JScrollPane scrollListProduct;
    private RoundPanel[] product;
    private RoundPanel[] panelDetailRightSale;
    private  RoundPanel[] panelDetails;

    private JLabel[] labelProduct;
    private JTextField[] textFieldProduct;
    private JLabel[] labelSale;
    private JTextField[] textFieldSale;

    private Button btnAdd;
    private Button btnRefresh;
    private Button btnCansel;
    private Button btnCompleted;
    private LayoutForm layoutForm;
    private LayoutForm layoutSale;
    private int totalHeightPanel;
    private Long[] idsOfCurrentProducts;


    public SaleGUI(List<Function> functions) {
        super(3, 1, 20, 5, 5);
        this.setBackground(null);

        labelProduct = new JLabel[10];
        textFieldProduct = new JTextField[10];
        labelSale = new JLabel[10];
        textFieldSale = new JTextField[10];
        btnAdd = new Button();
        btnRefresh = new Button();
        btnCompleted = new Button();
        btnCansel = new Button();
        btnAdd.setColor(new Color(243, 240, 240));
        btnRefresh.setColor(new Color(243, 240, 240));
        btnCompleted.setColor(new Color(243, 240, 240));
        btnCansel.setColor(new Color(243, 240, 240));


        panelLeftSale = this.getLeftPanel();
        panelRightSale = this.getRightPanel();

        panelLeftSale.setLayout(new BorderLayout());
        layoutContent = new BottomTopLayout(3, 1.5, 20, 5, 0);
        layoutContent.setBackground(new Color(255, 255, 255));
        panelLeftSale.add(layoutContent, BorderLayout.CENTER);

        panelDetailProduct = layoutContent.getTopPanel();
        panelListProduct = layoutContent.getBottomPanel();

        panelDetailProduct.setLayout(new BorderLayout());
        layoutInformation = new LeftRightLayout(1.5, 1, 20, 5, 0);
        layoutInformation.setBackground(new Color(255, 255, 255));
        panelDetailProduct.add(layoutInformation, BorderLayout.CENTER);

        layoutFormAndButton = new BottomTopLayout(40,false,20,  0);
        layoutFormAndButtonSale = new BottomTopLayout(40,false,20,  0);

        panelProduct = layoutInformation.getLeftPanel();
        panelProduct.setLayout(new BorderLayout());
        layoutSearchProduct = new BottomTopLayout(40, true, 20,0);
        layoutSearchProduct.setBackground(null);
        panelProduct.add(layoutSearchProduct, BorderLayout.CENTER);


        GridBagConstraints gbcd = new GridBagConstraints();
        gbcd.fill = GridBagConstraints.BOTH;
        gbcd.weightx = 1.0;
        gbcd.weighty = 1.0;
        gbcd.gridx = 0;
        gbcd.gridy = 0;
        gbcd.insets = new Insets(5, 10, 0, 10);
        searchProduct = new JTextField();
        panelSearch = layoutSearchProduct.getTopPanel();
        panelSearch.setLayout(new GridBagLayout());
        searchProduct.setPreferredSize(new Dimension(0, 40));
        panelSearch.add(searchProduct, gbcd);

        panelProduct = layoutSearchProduct.getBottomPanel();
        panelProduct.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;


        int y = 16; // số lượng sản phẩm'
        int size = 100;
        gbc.insets = new Insets(5, 5, 5, 5);
        product = new RoundPanel[y];
        listProduct = new RoundPanel(20);
        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel.weightx = 1.0;
        gbcPanel.weighty = 1.0;
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;
        gbcPanel.insets = new Insets(0, 0, 5, 5);
        gbcPanel.anchor = GridBagConstraints.PAGE_START;
        listProduct.setPreferredSize(new Dimension(0, (size + 5) * y));
        listProduct.setLayout(new GridBagLayout());
        listProduct.setBackground(new Color(215, 215, 215));
        scrollListProduct = new JScrollPane(listProduct);
        scrollListProduct.setBorder(null);
        scrollListProduct.setBackground(null);
        scrollListProduct.getViewport().setBackground(null);
        scrollListProduct.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollListProduct.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        for (int i = 0; i < y; i++) {
            product[i] = new RoundPanel(20);
            product[i].setPreferredSize(new Dimension(0, size));
            product[i].setBackground(new Color(255, 255, 255));
            listProduct.add(product[i], gbcPanel);
            gbcPanel.gridy++;
        }
        panelProduct.add(scrollListProduct, gbc);

//        GridBagConstraints panelgbc = new GridBagConstraints();
//        panelgbc.fill = GridBagConstraints.BOTH;
//        panelgbc.gridx = 0;
//        panelgbc.gridy = 0;
//        panelgbc.insets = new Insets(5, 5, 5, 5);

        layoutForm = new LayoutForm(8);
        JTextField jTextField = new JTextField();
        JTextField jTextField1 = new JTextField();
        JTextField jTextField2 = new JTextField();
        JTextField jTextField3 = new JTextField();
        JTextField jTextField4 = new JTextField();
        JTextField jTextField5 = new JTextField();
        JTextField jTextField6 = new JTextField();
        JTextField jTextField7 = new JTextField();
        JTextField jTextField8 = new JTextField();
        JTextField jTextField9 = new JTextField();
//        JTextArea jTextArea = new JTextArea();
//        jTextField.setLineWrap(true);
        jTextField.setEditable(false);
//        jTextField.setBackground(null);
//        jTextField.setBorder(null);
        jTextField.setForeground(new Color(1));
        JComboBox jComboBox = new JComboBox();
        layoutForm.addTwoText("Mã sản phẩm", "Tên sản phẩm", 1,2,0);
        layoutForm.addTwoComponent(jTextField, jTextField1, 1,4,5);
        layoutForm.addTextAbove("Thương hiệu:", jTextField2);
        layoutForm.addTextAbove("Thể loại :", jTextField3);
        layoutForm.addTextAbove("Giá tiền:", jTextField4);
        layoutForm.addTextAbove("Hàng tồn :", jTextField5);
        layoutForm.addTwoText("Đơn vị:", "Số lượng",1,2,20);
        layoutForm.addTwoComponent(jTextField6, jTextField7,1,2,20);
//        layoutForm.add("Combox:", jComboBox,20,0);
//        layoutForm.add("Con meo:", jTextField7);
//        layoutForm.add("Con meo:", jTextField8);
//        layoutForm.add("Con meo:", jTextField9);
        totalHeightPanel = (layoutForm.getNumberAttribute() * layoutForm.getHeightFuncAndText());
//        int sizeHeight = Main.getFrameHeight() - 165;
        GridBagConstraints panelgbc = new GridBagConstraints();
        panelgbc.fill = GridBagConstraints.BOTH;
        panelgbc.weighty = 1.0;
        panelgbc.weightx = 1.0;
        panelgbc.gridx = 0;
        panelgbc.gridy = 0;
        panelgbc.insets = new Insets(5, 5, 5, 5);
        panelgbc.anchor = GridBagConstraints.PAGE_START;
        panelDetail = layoutInformation.getRightPanel();
        panelDetail.setLayout(new GridBagLayout());
        panelDetail.add(layoutFormAndButton, panelgbc);

        layoutFormAndButton.getTopPanel().setLayout(new GridBagLayout());
        layoutFormAndButton.getTopPanel().add(layoutForm, panelgbc);
        layoutFormAndButton.getBottomPanel().setLayout(new GridBagLayout());
        btnAdd.setPreferredSize(new Dimension(120,0));
        btnRefresh.setPreferredSize(new Dimension(120,0));
        layoutFormAndButton.getBottomPanel().add(btnAdd,panelgbc);
        panelgbc.gridx++;
        layoutFormAndButton.getBottomPanel().add(btnRefresh, panelgbc);
        panelgbc.fill = GridBagConstraints.BOTH;
        panelgbc.gridx = 0;

        layoutSale = new LayoutForm(5, new Insets(10,0,0,0));
        JTextField jtextSale = new JTextField();
        JTextField jtextSale1 = new JTextField();
        JTextField jtextSale2 = new JTextField();
        JTextField jtextSale3 = new JTextField();
        RoundPanel panel = new RoundPanel(20);
        panel.setBackground(new Color(240,240,240));
        jTextField.setEditable(false);
        layoutSale.addTextLeft("Tên Nhân Viên", jtextSale);
        layoutSale.addPanel(panel, 500);
        layoutSale.addTextLeft("Tổng Tiền:", jtextSale1);
        layoutSale.addTextLeft("Tiền Nhận :", jtextSale2);
        layoutSale.addTextLeft("Tiền Thừa:", jtextSale3);
        totalHeightPanel = (layoutSale.getNumberAttribute() * layoutSale.getHeightFuncAndText());
        panelRightSale.setLayout(new GridBagLayout());
        panelRightSale.add(layoutFormAndButtonSale, panelgbc);
        layoutFormAndButtonSale.getTopPanel().setLayout(new GridBagLayout());
        layoutFormAndButtonSale.getTopPanel().add(layoutSale, panelgbc);
        btnCompleted.setPreferredSize(new Dimension(120,0));
        btnCansel.setPreferredSize(new Dimension(120,0));

        layoutFormAndButtonSale.getBottomPanel().setLayout(new GridBagLayout());
        layoutFormAndButtonSale.getBottomPanel().add(btnCompleted,panelgbc);
        panelgbc.gridx++;
        layoutFormAndButtonSale.getBottomPanel().add(btnCansel, panelgbc);


        DataTable dataTable = getDataTable(List.of());
        panelListProduct.setLayout(new GridBagLayout());
        panelListProduct.add(dataTable, gbc);

        //Test
        btnAdd.addActionListener((e) -> {
            LayoutWarningTable layoutwa = new LayoutWarningTable();
            layoutwa.setVisible(true);
        });
    }

    public DataTable getDataTable(List<Product> products) {
        ProductBLL productBLL = new ProductBLL();
        Object[][] ids = productBLL.getData(products, false, List.of(
            new Pair<>(__.PRODUCT.COLUMN.ID, Long::parseLong)
        ));
        idsOfCurrentProducts = Arrays.stream(ids)
            .map(row -> (Long) row[0])
            .toArray(Long[]::new);
        Object[][] data = productBLL.getData(products, true, List.of(
            new Pair<>(__.PRODUCT.COLUMN.NAME, String::toString),
            new Pair<>(__.PRODUCT.COLUMN.COST, Double::parseDouble),
            new Pair<>(__.PRODUCT.COLUMN.QUANTITY, Double::parseDouble),
            new Pair<>(__.PRODUCT.COLUMN.UNIT, String::toString),
            new Pair<>(__.BRAND.COLUMN.NAME, String::toString),
            new Pair<>(__.CATEGORY.COLUMN.NAME, String::toString)
        ));
        return new DataTable(data,
            new Object[]{"STT", "Tài khoản", "Nhân viên", "Giới tính", "Ngày sinh", "Chức vụ", "Email"},
            new Integer[]{1, 1, 100, 1, 1, 0, 0},
            this::detail, false
        );
    }

    public void detail() {

    }
}
