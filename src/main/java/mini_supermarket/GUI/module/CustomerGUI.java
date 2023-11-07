package mini_supermarket.GUI.module;

import mini_supermarket.BLL.CustomerBLL;
import mini_supermarket.DTO.Customer;
import mini_supermarket.DTO.Function;
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

public class CustomerGUI extends ControlLayout {

    private final CustomerBLL customerBLL;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;

    private DataTable dataTable;
    private JScrollPane scrollPane;
    private  Long[] idsOfCurrentData;
    private LeftRightLayout layoutFormAndData;


    public CustomerGUI(List<Function> functions) {
        super(functions);
        customerBLL = new CustomerBLL();
        panelFunction = getTopPanel();
        panelData = getBottomPanel();
        panelData.setLayout(new GridBagLayout());

        dataTable = getDataTable(customerBLL.findAll());
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




    public  DataTable getDataTable(List<Customer> customers){
          Object[][] ids = customerBLL.getData(customers, false,List.of(
                new Pair<>(__.CUSTOMER.COLUMN.ID, Long :: parseLong)
          ));

          idsOfCurrentData = Arrays.stream(ids)
              .map(row -> (long) row[0])
              .toArray(Long[]::new);

          Object[][] data = customerBLL.getData(customers, true, List.of(
              new Pair<>(__.CUSTOMER.COLUMN.NAME, String::toString),
              new Pair<>(__.CUSTOMER.COLUMN.GENDER, s -> Boolean.parseBoolean(s) ? "Nam" : "Nữ"),
              new Pair<>(__.CUSTOMER.COLUMN.PHONE, String :: toString),
              new Pair<>(__.CUSTOMER.COLUMN.MEMBERSHIP, s -> Boolean.parseBoolean(s) ? "Member" : " " ),
              new Pair<>(__.CUSTOMER.COLUMN.POINT, Integer ::valueOf),
              new Pair<>(__.CUSTOMER.COLUMN.SIGNED_UP_DATE, Date::parse)
          ));

          return new DataTable(data,
              new Object[]{"STT","Khách hàng","Giới tính","SĐT","Member","Điểm","Ngày đăng ký"},
              new Integer[]{1,100,1,0,1,1,0},
              this::detail, true,
              new Pair<>(Date.class,3)
              );
    };


    public List<Customer> getCustomerFromSelectedRows(){
        List<Customer> customers = new ArrayList<>();
        for (int row : dataTable.getSelectedRows()){
            Customer customer = customerBLL.findBy(__.CUSTOMER.ID,idsOfCurrentData[row]).get(0);
            customers.add(customer);
        }
        return customers;
    }

//    public Customer getCustomerFromSelectedRows()

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
