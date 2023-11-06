package mini_supermarket.GUI.module;

import mini_supermarket.BLL.SupplierBLL;
import mini_supermarket.DTO.Customer;
import mini_supermarket.DTO.Function;
import mini_supermarket.DTO.Supplier;
import mini_supermarket.GUI.component.DataTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SupplierGUI extends ControlLayout {

    private final SupplierBLL supplierBLL;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private DataTable dataTable;
    private JScrollPane scrollPane;
    private  Long[] idsOfCurrentData;
    private LeftRightLayout layoutFormAndData;

    public SupplierGUI(List<Function> functions) {
        super(functions);
        supplierBLL = new SupplierBLL();
        panelFunction = getTopPanel();
        panelData = getBottomPanel();
        panelData.setLayout(new GridBagLayout());
        dataTable = getDataTable(supplierBLL.findAll());
        dataTable.setBackground(null);

        scrollPane = new JScrollPane(dataTable);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(null);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10 , 10 ,10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelData.add(scrollPane,gbc);
    }


    public  DataTable getDataTable(List<Supplier> suppliers){
        Object[][] ids = supplierBLL.getData(suppliers, false,List.of(
            new Pair<>(__.SUPPLIER.COLUMN.ID, Long :: parseLong)
        ));

        idsOfCurrentData = Arrays.stream(ids)
            .map(row -> (long) row[0])
            .toArray(Long[]::new);

        Object[][] data = supplierBLL.getData(suppliers, true, List.of(
            new Pair<>(__.SUPPLIER.COLUMN.NAME, String::toString),
            new Pair<>(__.SUPPLIER.COLUMN.PHONE, String :: toString),
            new Pair<>(__.SUPPLIER.COLUMN.ADDRESS, String::toString),
            new Pair<>(__.SUPPLIER.COLUMN.EMAIL, String::toString)
        ));

        return new DataTable(data,
            new Object[]{"STT","Nhà cung cấp","SĐT","Địa chỉ","Email"},
            new Integer[]{1,0,0,0,0},
            this::detail, true,
            new Pair<>(Date.class,3)
        );
    };


    public List<Supplier> getSupplierFromSelectedRows(){
        List<Supplier> suppliers = new ArrayList<>();
        for (int row : dataTable.getSelectedRows()){
            Supplier supplier = supplierBLL.findBy(__.SUPPLIER.ID,idsOfCurrentData[row]).get(0);
            suppliers.add(supplier);
        }
        return suppliers;
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
    public void remove() {
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
