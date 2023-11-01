package mini_supermarket.GUI.module;

import mini_supermarket.BLL.AccountBLL;
import mini_supermarket.DTO.Account;
import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.DataTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.Log;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountGUI extends ControlLayout {
    private final AccountBLL accountBLL;
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private LeftRightLayout layoutFormAndData;
    private JScrollPane scrollPane;
    private DataTable dataTable;
    private Long[] idsOfCurrentData;

    public AccountGUI(List<Function> functions) {
        super(functions);
        accountBLL = new AccountBLL();
        panelFunction = getTopPanel();

        panelData = getBottomPanel();
        panelData.setLayout(new GridBagLayout());

        dataTable = getDataTable(accountBLL.findAll());
        dataTable.setBackground(null);

        scrollPane = new JScrollPane(dataTable);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(null);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelData.add(scrollPane, gbc);
    }

    public DataTable getDataTable(List<Account> accounts) {
        Object[][] ids = accountBLL.getData(accounts, false, List.of(
            new Pair<>(__.ACCOUNT.COLUMN.ID, Long::parseLong)
        ));
        idsOfCurrentData = Arrays.stream(ids)
            .map(row -> (Long) row[0])
            .toArray(Long[]::new);
        Object[][] data = accountBLL.getData(accounts, true, List.of(
            new Pair<>(__.ACCOUNT.COLUMN.USERNAME, String::toString),
            new Pair<>(__.STAFF.COLUMN.NAME, String::toString),
            new Pair<>(__.STAFF.COLUMN.GENDER, s -> Boolean.parseBoolean(s) ? "Nam" : "Nữ"),
            new Pair<>(__.STAFF.COLUMN.BIRTHDATE, Date::parse),
            new Pair<>(__.ROLE.COLUMN.NAME, String::toString),
            new Pair<>(__.STAFF.COLUMN.EMAIL, String::toString)
        ));
        return new DataTable(data,
            new Object[]{"STT", "Tài khoản", "Nhân viên", "Giới tính", "Ngày sinh", "Chức vụ", "Email"},
            new Integer[]{1, 1, 100, 1, 1, 0, 0},
            this::detail, true,
            new Pair<>(Date.class, 3)
        );
    }

    public List<Account> getAccountFromSelectedRows() {
        List<Account> accounts = new ArrayList<>();
        for (int row : dataTable.getSelectedRows()) {
            Account account = accountBLL.findBy(__.ACCOUNT.ID, idsOfCurrentData[row]).get(0);
            accounts.add(account);
        }
        return accounts;
    }

    public Account getAccountFromSelectedRow() {
        return getAccountFromSelectedRows().get(0);
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
        Account account = getAccountFromSelectedRow();
        Log.info(dataTable.getDataFromSelectedRow(), account);
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
