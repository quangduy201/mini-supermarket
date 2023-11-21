package mini_supermarket.GUI.module;

import mini_supermarket.BLL.Criteria;
import mini_supermarket.BLL.SupplierBLL;
import mini_supermarket.DTO.Function;
import mini_supermarket.DTO.Supplier;
import mini_supermarket.GUI.component.CustomTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierGUI extends ControlLayout {
    private final SupplierBLL supplierBLL;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private CustomTable dataTable;
    private Long[] idsOfCurrentData;

    public SupplierGUI(List<Function> functions) {
        super(functions);
        supplierBLL = new SupplierBLL();
        panelFunction = getTopPanel();

        // TODO

        panelData = getBottomPanel();
        panelData.setLayout(new GridBagLayout());

        String[] attributes = I18n.get("components", "table_headers.supplier").split(", ");

        Pair<Long[], Object[][]> pair = SupplierBLL.getDataFrom(supplierBLL.findAll());
        idsOfCurrentData = pair.getFirst();
        dataTable = new CustomTable(
            pair.getSecond(),
            attributes,
            new Integer[]{1, 100, 50, 200, 100},
            this::detail, true
        );
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10 , 10 ,10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelData.add(dataTable, gbc);

        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBoxFilter.getModel();
        model.addElement(attributes[1]); // name
        model.addElement(attributes[2]); // phone
        model.addElement(attributes[3]); // address
        model.addElement(attributes[4]); // email
    }

    public List<Supplier> getSuppliersFromSelectedRows() {
        List<Supplier> suppliers = new ArrayList<>();
        for (int row : dataTable.getTable().getSelectedRows()) {
            Supplier supplier = supplierBLL.findBy(__.SUPPLIER.ID,idsOfCurrentData[row]).get(0);
            suppliers.add(supplier);
        }
        return suppliers;
    }

    public Supplier getSupplierFromSelectedRow() {
        List<Supplier> suppliers = getSuppliersFromSelectedRows();
        if (suppliers.isEmpty())
            return null;
        return suppliers.get(0);
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
    public void find() {
        int attributeIndex = comboBoxFilter.getSelectedIndex();
        String criteria = jTextFieldSearch.getText().trim();

        List<Supplier> suppliers;
        if (criteria.isBlank())
            suppliers = supplierBLL.findAll();
        else {
            Criteria<Supplier> c = new Criteria<>(supplierBLL);
            suppliers = switch (attributeIndex) {
                case 0 -> supplierBLL.findByCriteria(
                    c.like(__.SUPPLIER.NAME, "%" + criteria + "%"));
                case 1 -> supplierBLL.findByCriteria(
                    c.like(__.SUPPLIER.PHONE, "%" + criteria + "%"));
                case 2 -> supplierBLL.findByCriteria(
                    c.like(__.SUPPLIER.EMAIL, "%" + criteria + "%"));
                case 3 -> supplierBLL.findByCriteria(
                    c.like(__.SUPPLIER.ADDRESS, "%" + criteria + "%"));
                default -> supplierBLL.findAll();
            };
        }

        Pair<Long[], Object[][]> pair = SupplierBLL.getDataFrom(suppliers);
        idsOfCurrentData = pair.getFirst();
        Object[][] data = pair.getSecond();
        dataTable.setData(data);
    }

    @Override
    public void refresh() {
        jTextFieldSearch.setText("");
        if (comboBoxFilter.getSelectedIndex() == 0)
            find();
        else
            comboBoxFilter.setSelectedIndex(0);
//        dialogAdd.refresh();
//        dialogEdit.refresh();
//        dialogDetail.refresh();
        System.gc();
    }
}
