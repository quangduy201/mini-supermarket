package mini_supermarket.GUI.module;

import mini_supermarket.BLL.StaffBLL;
import mini_supermarket.DTO.Account;
import mini_supermarket.DTO.Function;
import mini_supermarket.DTO.Staff;
import mini_supermarket.GUI.component.DataTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaffGUI extends ControlLayout {
    private final StaffBLL staffBLL;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;

    private DataTable dataTable;
    private JScrollPane scrollPane;
    private Long[] idsOfCurrentData;
    private LeftRightLayout layoutFormAndData;

    public StaffGUI(List<Function> functions) {
        super(functions);
        staffBLL = new StaffBLL();
        panelFunction = getTopPanel();
        panelData = getBottomPanel();
        panelData.setLayout(new GridBagLayout());

        dataTable = getDataTable(staffBLL.findAll());
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


    public DataTable getDataTable(List<Staff> staffs) {
        Object[][] ids = staffBLL.getData(staffs, false, List.of(
            new Pair<>(__.STAFF.COLUMN.ID, Long::parseLong)
        ));

        idsOfCurrentData = Arrays.stream(ids)
            .map(row -> (long) row[0])
            .toArray(Long[]::new);


        Object[][] data = staffBLL.getData(staffs, true, List.of(
            new Pair<>(__.STAFF.COLUMN.NAME, String::toString),
            new Pair<>(__.STAFF.COLUMN.GENDER, s -> Boolean.parseBoolean(s) ? "Nam" : "Nữ"),
            new Pair<>(__.STAFF.COLUMN.BIRTHDATE, Date::parse),
            new Pair<>(__.STAFF.COLUMN.PHONE, String::toString),
            new Pair<>(__.STAFF.COLUMN.EMAIL, String::toString),
            new Pair<>(__.STAFF.COLUMN.ENTRY_DATE, Date::parse)
        ));
        return new DataTable(data,
            new Object[]{"STT", "Nhân viên", "Giới tính", "Ngày sinh", "SĐT", "Email", "Ngày làm"},
            new Integer[]{1, 100, 1, 2, 0, 0, 2},
            this::detail, true,
            new Pair<>(Date.class, 3)
        );
    }

    public List<Staff> getStaffFromSelectedRows() {
        List<Staff> staff = new ArrayList<>();
        for (int row : dataTable.getSelectedRows()) {
            Staff staffItem = staffBLL.findBy(__.STAFF.ID, idsOfCurrentData[row]).get(0);
            staff.add(staffItem);
        }
        return staff;
    }

    //public Staff getStaffFromSelectedRow() {
//        return getStaffFromSelectedRows().get(0);
//    }

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
