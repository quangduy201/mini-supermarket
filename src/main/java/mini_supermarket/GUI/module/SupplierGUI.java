package mini_supermarket.GUI.module;

import mini_supermarket.BLL.Criteria;
import mini_supermarket.BLL.SupplierBLL;
import mini_supermarket.DTO.Function;
import mini_supermarket.DTO.Supplier;
import mini_supermarket.GUI.component.CustomTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.dialog.CustomerDialog;
import mini_supermarket.GUI.dialog.SmallDialog;
import mini_supermarket.GUI.dialog.SupplierDialog;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.main.MiniSupermarket;
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
    private final SupplierDialog dialogAdd;
    private final SupplierDialog dialogEdit;
    private final SupplierDialog dialogDetail;
    private final RoundPanel panelData;
    private CustomTable dataTable;
    private Long[] idsOfCurrentData;

    public SupplierGUI(List<Function> functions) {
        super(functions);
        supplierBLL = new SupplierBLL();
        panelFunction = getTopPanel();

        dialogAdd = new SupplierDialog(I18n.get("dialog", "supplier.add"),
            false, supplier -> supplierBLL.addSupplier(supplier.getSecond()));

        dialogEdit = new SupplierDialog(I18n.get("dialog", "supplier.edit"),
            false, supplier -> supplierBLL.editSupplier(supplier.getFirst(), supplier.getSecond()));

        dialogDetail = new SupplierDialog(I18n.get("dialog", "supplier.detail"),
            true, null);
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
        dialogAdd.setVisible(true);
        if(!dialogAdd.isCancel())
            refresh();
    }

    @Override
    public void edit() {
        Supplier supplier = getSupplierFromSelectedRow();
        if(supplier == null){
            SmallDialog.showErrorWhenDataTableIsNotSelected(MiniSupermarket.main);
            return;
        }
        dialogEdit.setData(supplier);
        dialogEdit.setVisible(true);
        if(!dialogEdit.isCancel())
            refresh();
    }

    @Override
    public void remove() {
        Supplier supplier = getSupplierFromSelectedRow();
        if(supplier == null){
            SmallDialog.showErrorWhenDataTableIsNotSelected(MiniSupermarket.main);
            return;
        }
        int choice = SmallDialog.showOptionDialogWhenDeleting(MiniSupermarket.main,
            I18n.get("messages", "supplier.remove.success"));
        if (choice != 0)
            return;

        Pair<Boolean, String> result = supplierBLL.removeSupplier(supplier);
        SmallDialog.showResult(MiniSupermarket.main, result, this::refresh, null);

    }

    @Override
    public void detail() {
        Supplier supplier = getSupplierFromSelectedRow();
        if(supplier == null){
            SmallDialog.showErrorWhenDataTableIsNotSelected(MiniSupermarket.main);
            return;
        }
        dialogDetail.setData(supplier);
        dialogDetail.setVisible(true);
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
        dialogAdd.refresh();
        dialogEdit.refresh();
        dialogDetail.refresh();
        System.gc();
    }
}
