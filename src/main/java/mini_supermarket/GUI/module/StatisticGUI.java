package mini_supermarket.GUI.module;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.Button;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.component.Statisic.chart.Chart;
import mini_supermarket.GUI.component.Statisic.chart.ModelChart2;
import mini_supermarket.GUI.component.Statisic.chart.PanelBorderRadius;
import mini_supermarket.GUI.layout.BottomTopLayout;
import mini_supermarket.GUI.layout.EmptyLayout;
import mini_supermarket.GUI.component.Statisic.CurveLineChart;
import mini_supermarket.GUI.component.Statisic.ModelChart;
import mini_supermarket.GUI.component.Statisic.PanelShadow;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class StatisticGUI extends EmptyLayout {
    private final RoundPanel panelStatistic;
    private CurveLineChart curveLineChart;

    private RoundPanel panelDetail;
    private RoundPanel panelChart;
    private RoundPanel panelGeneralStatistic;
    private RoundPanel[] panelQuantity;
    private JLabel[] labelIcon;
    private RoundPanel[] panelTextAndNumber;
    private JLabel[] labelText;
    private JLabel[] labelNumber;

    private PanelBorderRadius pnlChart;
    private BottomTopLayout panelOverview;
    private BottomTopLayout layoutChartAndData;
    private RoundPanel panelRevenue;

    private PanelShadow panelShadow;
    private JTabbedPane tabbedPaneGeneral;
    private JTabbedPane tabbedPane;
    public StatisticGUI(List<Function> functions) {
        panelStatistic = getPanel();
        panelStatistic.setLayout(new GridBagLayout());
        init();
    }

    public void init() {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatIntelliJLaf.setup();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,5,5,0);
        tabbedPaneGeneral = new JTabbedPane();
        tabbedPane = new JTabbedPane();
//        tabbedPane.setBackground(null);
        UIManager.put("TabbedPane.selectedBackground", Color.white);
        UIManager.put("TabbedPane.tabAreaInsets", new Insets(0, 0, 0, 0));
        UIManager.put("TabbedPane.tabInsets", new Insets(20, 20, 20, 20));
        UIManager.put("TabbedPane.selected", Color.RED);
        UIManager.put("TabbedPane.contentAreaColor", Color.GRAY);

        UIManager.put("Button.textIconGap", 10);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("ProgressBar.cycleTime", 6000);
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("TextComponent.arc", 5);
        tabbedPaneGeneral.setOpaque(false);
        panelOverview = new BottomTopLayout(110,true,20,5);
        tabbedPaneGeneral.addTab("Tổng Quan", panelOverview);

        panelRevenue = new RoundPanel();
        tabbedPaneGeneral.addTab("Doanh Thu", panelRevenue);

        tabbedPaneGeneral.addChangeListener(e -> {
            switch (tabbedPaneGeneral.getSelectedIndex()) {
                case 0 -> {
                    curveLineChart.clear();
                    curveLineChart.addData(new ModelChart("Thu2", new double[]{1000, 800, 200}));
                    curveLineChart.addData(new ModelChart("Thu 3", new double[]{1000, 400, 200}));
                    curveLineChart.addData(new ModelChart("Thu 4", new double[]{900, 300, 200}));
                    curveLineChart.addData(new ModelChart("Thu 5", new double[]{500, 200, 400}));
                    curveLineChart.addData(new ModelChart("Thu 6", new double[]{1000, 800, 200}));
                    curveLineChart.addData(new ModelChart("Thu 7", new double[]{1000, 800, 200}));
                    curveLineChart.addData(new ModelChart("CN", new double[]{100, 800, 200}));
                    curveLineChart.start();
                }
                case 1 -> {
                    chart.clear();
                    chart.addData(new ModelChart2("January", new double[]{500, 200, 80}));
                    chart.addData(new ModelChart2("February", new double[]{600, 750, 90}));
                    chart.addData(new ModelChart2("March", new double[]{200, 350, 460}));
                    chart.addData(new ModelChart2("April", new double[]{480, 150, 750}));
                    chart.addData(new ModelChart2("May", new double[]{350, 540, 300}));
                    chart.addData(new ModelChart2("June", new double[]{190, 280, 81}));
                    chart.start();
                }
            }
        });

        panelStatistic.add(tabbedPaneGeneral, gbc);
        panelQuantity = new RoundPanel[10];
        labelIcon = new JLabel[10];
        labelText = new JLabel[10];
        labelNumber = new JLabel[10];
        textField = new JTextField[10];
        panelTextAndNumber = new RoundPanel[10];
        StatisticGeneral();
        Statistic();
    }

    public void StatisticGeneral() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,5,5,0);
        panelGeneralStatistic = new RoundPanel(20);
        panelChart = new RoundPanel(20);
        panelDetail = new RoundPanel(20);
        gbc.insets = new Insets(5,0,0,0);
        panelGeneralStatistic = panelOverview.getTopPanel();
        panelOverview.getBottomPanel().setLayout(new GridBagLayout());
        panelOverview.getBottomPanel().setBackground(null);
        gbc.weighty = 0.5;
        panelOverview.getBottomPanel().add(panelChart,gbc);
        gbc.gridy++;
        panelOverview.getBottomPanel().add(panelDetail,gbc);
        gbc.weighty = 1.0;

        panelGeneralStatistic.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
        panelGeneralStatistic.setBackground(null);
        for(int i = 0; i < 3; i++) {
            panelQuantity[i] = new RoundPanel(20);
            panelQuantity[i].setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
            panelQuantity[i].setPreferredSize(new Dimension(320,100));
            panelTextAndNumber[i] = new RoundPanel(20);
            panelTextAndNumber[i].setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
            panelTextAndNumber[i].setPreferredSize(new Dimension(200,80));
            labelIcon[i] = new JLabel();
            labelIcon[i].setPreferredSize(new Dimension(80,80));
            labelText[i] = new JLabel();
            labelText[i].setFont(new Font("Roboto",Font.PLAIN,20));
            labelText[i].setPreferredSize(new Dimension(200,40));
            labelNumber[i] = new JLabel();
            labelNumber[i].setFont(new Font("Roboto",Font.BOLD,25));
            labelNumber[i].setText("0");
            labelNumber[i].setPreferredSize(new Dimension(50,40));
            panelTextAndNumber[i].add(labelNumber[i]);
            panelTextAndNumber[i].add(labelText[i]);
            panelQuantity[i].add(labelIcon[i]);
            panelQuantity[i].add(panelTextAndNumber[i]);
            panelGeneralStatistic.add(panelQuantity[i]);
        }

        labelText[0].setText("Sản phẩm tồn kho");
        labelText[1].setText("Số lượng thành viên");
        labelText[2].setText("Số lượng nhân viên ");

        gbc.gridx = 0;
        gbc.insets = new Insets(0,0,0,0);
        panelChart.setLayout(new GridBagLayout());
        panelShadow = new PanelShadow();
        panelShadow.setLayout(new GridBagLayout());
        panelChart.add(panelShadow, gbc);

        curveLineChart = new CurveLineChart();
        panelShadow.setBackground(new Color(34, 59, 69));
        panelShadow.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelShadow.setColorGradient(new Color(17, 38, 47));
        panelShadow.add(curveLineChart,gbc);

        curveLineChart.setForeground(new Color(237, 237, 237));
        curveLineChart.setFillColor(true);


        curveLineChart.clear();
        curveLineChart.addData(new ModelChart("Thu2", new double[]{1000, 800, 200}));
        curveLineChart.addData(new ModelChart("Thu 3", new double[]{1000, 400, 200}));
        curveLineChart.addData(new ModelChart("Thu 4", new double[]{900, 300, 200}));
        curveLineChart.addData(new ModelChart("Thu 5", new double[]{500, 200, 400}));
        curveLineChart.addData(new ModelChart("Thu 6", new double[]{1000, 800, 200}));
        curveLineChart.addData(new ModelChart("Thu 7", new double[]{1000, 800, 200}));
        curveLineChart.addData(new ModelChart("CN", new double[]{100, 800, 200}));
//        curveLineChart.addData(new ModelChart("August", new double[]{1000, 800, 200}));
//        curveLineChart.addData(new ModelChart("September", new double[]{1000, 800, 200}));
//        curveLineChart.addData(new ModelChart("October", new double[]{400, 800, 400}));
//        curveLineChart.addData(new ModelChart("November", new double[]{500, 800, 200}));
//        curveLineChart.addData(new ModelChart("December", new double[]{1000, 800, 200}));

        curveLineChart.setTitle("Biểu đồ thống kê theo ngày trong năm " + 2023);
        curveLineChart.addLegend("Vốn ", Color.decode("#7b4397"), Color.decode("#dc2430"));
        curveLineChart.addLegend("Lợi Nhuân", Color.decode("#e65c00"), Color.decode("#F9D423"));
        curveLineChart.addLegend("Doanh Thu", Color.decode("#0099F7"), Color.decode("#F11712"));

        curveLineChart.start();
    }

    private BottomTopLayout statisticMonth;
    private BottomTopLayout statisticDay;
    private BottomTopLayout statisticYear;

    private JTextField[] textField;
    private JButton btnStatistic;
    private JButton btnRefresh;

    private Chart chart;

    public void Statistic() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,0);
        tabbedPane.setOpaque(false);
        statisticYear = new BottomTopLayout(40,true,20,5);
        statisticMonth = new BottomTopLayout(40,true,20,5);
        statisticDay = new BottomTopLayout(40,true,20,5);
        tabbedPane.addTab("Thống kê theo năm", statisticYear);
        tabbedPane.addTab("Thống kê theo tháng", statisticMonth);
        tabbedPane.addTab("Thống kê theo ngày", statisticDay);

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (tabbedPane.getSelectedIndex()) {
                    case 0 -> {
                        addChart(statisticYear);
                        chart.clear();
                        chart.addData(new ModelChart2("January", new double[]{500, 200, 80}));
                        chart.addData(new ModelChart2("February", new double[]{600, 750, 90}));
                        chart.addData(new ModelChart2("March", new double[]{200, 350, 460}));
                        chart.addData(new ModelChart2("April", new double[]{480, 150, 750}));
                        chart.addData(new ModelChart2("May", new double[]{350, 540, 300}));
                        chart.addData(new ModelChart2("June", new double[]{190, 280, 81}));
                        chart.start();
                    }
                    case 1 -> {
                        addChart(statisticMonth);
                        chart.clear();
                        chart.addData(new ModelChart2("January", new double[]{500, 200, 80}));
                        chart.addData(new ModelChart2("February", new double[]{600, 750, 90}));
                        chart.addData(new ModelChart2("March", new double[]{200, 350, 460}));
                        chart.addData(new ModelChart2("April", new double[]{480, 150, 750}));
                        chart.addData(new ModelChart2("May", new double[]{350, 540, 300}));
                        chart.addData(new ModelChart2("June", new double[]{190, 280, 81}));
                        chart.start();
                    }
                    case 2 -> {
                        addChart(statisticDay);
                        chart.clear();
                        chart.addData(new ModelChart2("January", new double[]{500, 200, 80}));
                        chart.addData(new ModelChart2("February", new double[]{600, 750, 90}));
                        chart.addData(new ModelChart2("March", new double[]{200, 350, 460}));
                        chart.addData(new ModelChart2("April", new double[]{480, 150, 750}));
                        chart.addData(new ModelChart2("May", new double[]{350, 540, 300}));
                        chart.addData(new ModelChart2("June", new double[]{190, 280, 81}));
                        chart.start();
                    }
                }
            }
        });

        panelRevenue.setLayout(new GridBagLayout());
        panelRevenue.add(tabbedPane, gbc);
        layoutChartAndData = new BottomTopLayout(200,false,20,5);
        chart = new Chart();
        chart.addLegend("Vốn", new Color(135, 189, 245));
        chart.addLegend("Lợi nhuận", new Color(189, 135, 245));
        chart.addLegend("Doanh thu", new Color(139, 229, 222));
        layoutChartAndData.setBackground(null);
        layoutChartAndData.getTopPanel().setLayout(new GridBagLayout());
        layoutChartAndData.getTopPanel().add(chart, gbc);
        statisticYear.getBottomPanel().setLayout(new GridBagLayout());
        statisticYear.getBottomPanel().add(layoutChartAndData,gbc);
        StatisticYear();
        StatisticMonth();
        StatisticDay();
    }

    public void StatisticYear() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,0);
        statisticYear.setBackground(null);
        statisticYear.getTopPanel().setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        labelText[3] = new JLabel();
        labelText[3].setFont(new Font("Roboto",Font.PLAIN,11));
        labelText[3].setText("Từ năm");
        labelText[3].setPreferredSize(new Dimension(80,30));
        labelText[3].setHorizontalAlignment(SwingConstants.CENTER);
        yearFromChooser = new JYearChooser();
        yearFromChooser.setFont(new Font("Roboto",Font.PLAIN,11));
        yearFromChooser.setPreferredSize(new Dimension(100,30));
        statisticYear.getTopPanel().add(labelText[3]);
        statisticYear.getTopPanel().add(yearFromChooser);
        labelText[4] = new JLabel();
        labelText[4].setFont(new Font("Roboto",Font.PLAIN,11));
        labelText[4].setText("Đến năm");
        labelText[4].setPreferredSize(new Dimension(80,30));
        labelText[4].setHorizontalAlignment(SwingConstants.CENTER);
        yearChooser = new JYearChooser();
        yearChooser.setFont(new Font("Roboto",Font.PLAIN,11));
        yearChooser.setPreferredSize(new Dimension(100,30));
        statisticYear.getTopPanel().add(labelText[4]);
        statisticYear.getTopPanel().add(yearChooser);
        btnStatistic = new JButton();
        btnStatistic.setFont(new Font("Roboto",Font.PLAIN,11));
        btnStatistic.setPreferredSize(new Dimension(100,30));
        btnStatistic.setText("Thống kê");
        btnRefresh = new JButton();
        btnRefresh.setFont(new Font("Roboto",Font.PLAIN,11));
        btnRefresh.setPreferredSize(new Dimension(100,30));
        btnRefresh.setText("Làm mới");
        statisticYear.getTopPanel().add(btnStatistic);
        statisticYear.getTopPanel().add(btnRefresh);

    }

    private JYearChooser yearChooser;
    private JYearChooser yearFromChooser;
    private JMonthChooser monthchooser;
    public void StatisticMonth() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,0);
        statisticMonth.setBackground(null);
        statisticMonth.getTopPanel().setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        labelText[4] = new JLabel();
        labelText[4].setFont(new Font("Roboto",Font.PLAIN,11));
        labelText[4].setText("Chọn năm thống kê");
        labelText[4].setPreferredSize(new Dimension(110,30));
        yearChooser = new JYearChooser();
        yearChooser.addPropertyChangeListener("year", (PropertyChangeEvent e) -> {
            int year = (Integer) e.getNewValue();
        });
        yearChooser.setFont(new Font("Roboto",Font.PLAIN,11));
        yearChooser.setPreferredSize(new Dimension(100,30));
        statisticMonth.getTopPanel().add(labelText[4]);
        statisticMonth.getTopPanel().add(yearChooser);

    }
    public void StatisticDay() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,0);
        statisticDay.setBackground(null);
        statisticDay.getTopPanel().setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        labelText[5] = new JLabel();
        labelText[5].setFont(new Font("Roboto",Font.PLAIN,11));
        labelText[5].setText("Chọn tháng");
        labelText[5].setPreferredSize(new Dimension(80,30));
        monthchooser = new JMonthChooser();
        monthchooser.setFont(new Font("Roboto",Font.PLAIN,11));
        monthchooser.setPreferredSize(new Dimension(150,30));
        statisticDay.getTopPanel().add(labelText[5]);
        statisticDay.getTopPanel().add(monthchooser);
        labelText[6] = new JLabel();
        labelText[6].setFont(new Font("Roboto",Font.PLAIN,11));
        labelText[6].setText("Chọn năm");
        labelText[6].setPreferredSize(new Dimension(80,30));
        yearChooser = new JYearChooser();
        yearChooser.setFont(new Font("Roboto",Font.PLAIN,11));
        yearChooser.setPreferredSize(new Dimension(100,30));
        statisticDay.getTopPanel().add(labelText[6]);
        statisticDay.getTopPanel().add(yearChooser);
        statisticDay.getTopPanel().add(btnStatistic);
    }

    public void addChart(BottomTopLayout statistic) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,0);
        statistic.getBottomPanel().removeAll();
        statistic.getBottomPanel().setLayout(new GridBagLayout());
        statistic.getBottomPanel().add(layoutChartAndData,gbc);
        statistic.getBottomPanel().repaint();
        statistic.getBottomPanel().revalidate();
    }
}
