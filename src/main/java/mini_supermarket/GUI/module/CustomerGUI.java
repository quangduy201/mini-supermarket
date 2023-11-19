package mini_supermarket.GUI.module;

import mini_supermarket.BLL.CustomerBLL;
import mini_supermarket.DTO.Customer;
import mini_supermarket.DTO.Function;
import mini_supermarket.DTO.Staff;
import mini_supermarket.GUI.component.CustomTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerGUI extends ControlLayout {
    private final CustomerBLL customerBLL;
    private final RoundPanel panelFunction;


    private final RoundPanel panelData;
    private CustomTable dataTable;
    private  Long[] idsOfCurrentData;

    public CustomerGUI(List<Function> functions) {
        super(functions);
        customerBLL = new CustomerBLL();
        panelFunction = getTopPanel();
        panelData = getBottomPanel();
        panelData.setLayout(new GridBagLayout());



        refresh();;
    }

    public  CustomTable getDataTable(List<Customer> customers){
          Object[][] ids = customerBLL.getData(customers, false, List.of(
                new Pair<>(__.CUSTOMER.COLUMN.ID, Long::parseLong)
          ));

          idsOfCurrentData = Arrays.stream(ids)
              .map(row -> (long) row[0])
              .toArray(Long[]::new);

          Object[][] data = customerBLL.getData(customers, true, List.of(
              new Pair<>(__.CUSTOMER.COLUMN.NAME, String::toString),
              new Pair<>(__.CUSTOMER.COLUMN.GENDER, s -> Boolean.parseBoolean(s) ? "Nam" : "Nữ"),
              new Pair<>(__.CUSTOMER.COLUMN.PHONE, String::toString),
              new Pair<>(__.CUSTOMER.COLUMN.MEMBERSHIP, s -> Boolean.parseBoolean(s) ? "Có" : "" ),
              new Pair<>(__.CUSTOMER.COLUMN.POINT, Integer::valueOf),
              new Pair<>(__.CUSTOMER.COLUMN.SIGNED_UP_DATE, Date::parse)
          ));

          return new CustomTable(data,
              new Object[]{"STT", "Khách hàng", "Giới tính", "SĐT", "Thành viên", "Điểm tích lũy", "Ngày đăng ký"},
              new Integer[]{1, 100, 1, 0, 1, 1, 0},
              this::detail, true,
              new Pair<>(Date.class,3)
              );
    };

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
        if(customers.isEmpty())
            return  null;
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
    public void refresh() {
        panelData.removeAll();;
        panelData.revalidate();
        panelData.repaint();
        dataTable = getDataTable(customerBLL.findBy(__.CUSTOMER.DELETED,false));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelData.add(dataTable,gbc);
        jTextFieldSearch.setText("");
        System.gc();
    }
}
