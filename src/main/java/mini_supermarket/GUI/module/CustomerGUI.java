package mini_supermarket.GUI.module;

import mini_supermarket.BLL.Criteria;
import mini_supermarket.BLL.CustomerBLL;
import mini_supermarket.DTO.Customer;
import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.CustomTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerGUI extends ControlLayout {
    private final CustomerBLL customerBLL;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private CustomTable dataTable;
    private  Long[] idsOfCurrentData;
    private LeftRightLayout layoutFormAndData;

    public CustomerGUI(List<Function> functions) {
        super(functions);
        customerBLL = new CustomerBLL();
        panelFunction = getTopPanel();
        panelData = getBottomPanel();
        panelData.setLayout(new GridBagLayout());

        String[] attributes = I18n.get("components", "table_headers.customer").split(", ");

        Pair<Long[], Object[][]> pair = CustomerBLL.getDataFrom(customerBLL.findAll());
        idsOfCurrentData = pair.getFirst();
        dataTable = new CustomTable(
            pair.getSecond(),
            attributes,
            new Integer[]{1, 100, 1, 0, 1, 1, 0},
            this::detail, true,
            new Pair<>(Date.class, 6)
        );
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10,10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelData.add(dataTable, gbc);

        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBoxFilter.getModel();
        model.addElement(attributes[1]); // name
        model.addElement(attributes[3]); // phone
        model.addElement(attributes[5]); // point
    }

    public List<Customer> getCustomerFromSelectedRows() {
        List<Customer> customers = new ArrayList<>();
        for (int row : dataTable.getTable().getSelectedRows()) {
            Customer customer = customerBLL.findBy(__.CUSTOMER.ID, idsOfCurrentData[row]).get(0);
            customers.add(customer);
        }
        return customers;
    }

    public Customer getCustomerFromSelectedRow() {
        List<Customer> customers = getCustomerFromSelectedRows();
        if (customers.isEmpty())
            return null;
        return customers.get(0);
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

        List<Customer> customers;
        if (criteria.isBlank())
            customers = customerBLL.findAll();
        else {
            Criteria<Customer> c = new Criteria<>(customerBLL);
            customers = switch (attributeIndex) {
                case 0 -> customerBLL.findByCriteria(
                    c.like(__.CUSTOMER.NAME, "%" + criteria + "%"));
                case 1 -> customerBLL.findByCriteria(
                    c.like(__.CUSTOMER.PHONE, "%" + criteria + "%"));
                case 2 -> customerBLL.findByCriteria(
                    c.equal(__.CUSTOMER.POINT, criteria));
                default -> customerBLL.findAll();
            };
        }

        Pair<Long[], Object[][]> pair = CustomerBLL.getDataFrom(customers);
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
