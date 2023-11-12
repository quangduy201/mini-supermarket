package mini_supermarket.GUI.module;

import mini_supermarket.BLL.AccountBLL;
import mini_supermarket.DTO.Account;
import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.CustomTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.dialog.AccountDialog;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountGUI extends ControlLayout {
    private final AccountBLL accountBLL;
    private final RoundPanel panelFunction;
    private AccountDialog dialogAdd;
    private AccountDialog dialogEdit;
    private AccountDialog dialogDetail;
    private final RoundPanel panelData;
    private LeftRightLayout layoutFormAndData;
    private CustomTable dataTable;
    private Long[] idsOfCurrentData;

    public AccountGUI(List<Function> functions) {
        super(functions);
        accountBLL = new AccountBLL();
        panelFunction = getTopPanel();

        panelData = getBottomPanel();
        panelData.setLayout(new GridBagLayout());

        refresh();
    }

    public CustomTable getDataTable(List<Account> accounts) {
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
        return new CustomTable(data,
            new Object[]{"STT", "Tài khoản", "Nhân viên", "Giới tính", "Ngày sinh", "Chức vụ", "Email"},
            new Integer[]{1, 1, 100, 1, 1, 0, 0},
            this::detail, true,
            new Pair<>(Date.class, 3)
        );
    }

    public List<Account> getAccountsFromSelectedRows() {
        List<Account> accounts = new ArrayList<>();
        for (int row : dataTable.getTable().getSelectedRows()) {
            Account account = accountBLL.findBy(__.ACCOUNT.ID, idsOfCurrentData[row]).get(0);
            accounts.add(account);
        }
        return accounts;
    }

    public Account getAccountFromSelectedRow() {
        List<Account> accounts = getAccountsFromSelectedRows();
        if (accounts.isEmpty())
            return null;
        return accounts.get(0);
    }

    @Override
    public void add() {
        dialogAdd.setVisible(true);
        Account account = dialogAdd.getData();
        if (account == null)
            return;

        Pair<Boolean, String> result = accountBLL.addAccount(account);
        if (result.getFirst()) {
            String title = I18n.get("dialog", "title.info");
            JOptionPane.showMessageDialog(MiniSupermarket.main, result.getSecond(), title, JOptionPane.INFORMATION_MESSAGE);
            refresh();
        } else {
            String title = I18n.get("dialog", "title.error");
            JOptionPane.showMessageDialog(MiniSupermarket.main, result.getSecond(), title, JOptionPane.ERROR_MESSAGE);
            add();
        }
    }

    @Override
    public void edit() {
        Account currentAccount = getAccountFromSelectedRow();
        if (currentAccount == null) {
            JOptionPane.showMessageDialog(MiniSupermarket.main,
                I18n.get("messages", "data_table.selected.not"),
                I18n.get("dialog", "title.info"), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        dialogEdit.setData(currentAccount);
        dialogEdit.setVisible(true);
        Account account = dialogEdit.getData();
        if (account == null)
            return;

        Pair<Boolean, String> result = accountBLL.editAccount(currentAccount, account);
        if (result.getFirst()) {
            String title = I18n.get("dialog", "title.info");
            JOptionPane.showMessageDialog(MiniSupermarket.main, result.getSecond(), title, JOptionPane.INFORMATION_MESSAGE);
            refresh();
        } else {
            String title = I18n.get("dialog", "title.error");
            JOptionPane.showMessageDialog(MiniSupermarket.main, result.getSecond(), title, JOptionPane.ERROR_MESSAGE);
            edit();
        }
    }

    @Override
    public void remove() {
        Account account = getAccountFromSelectedRow();
        if (account == null) {
            JOptionPane.showMessageDialog(MiniSupermarket.main,
                I18n.get("messages", "data_table.selected.not"),
                I18n.get("dialog", "title.info"), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String[] options = new String[]{
            I18n.get("dialog", "yes"),
            I18n.get("dialog", "no")
        };
        int choice = JOptionPane.showOptionDialog(MiniSupermarket.main,
            I18n.get("messages", "account.remove.confirm"),
            I18n.get("dialog", "title.question"),
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice != 0)
            return;

        Pair<Boolean, String> result = accountBLL.removeAccount(account);
        if (result.getFirst()) {
            String title = I18n.get("dialog", "title.info");
            JOptionPane.showMessageDialog(MiniSupermarket.main, result.getSecond(), title, JOptionPane.INFORMATION_MESSAGE);
            refresh();
        } else {
            String title = I18n.get("dialog", "title.error");
            JOptionPane.showMessageDialog(MiniSupermarket.main, result.getSecond(), title, JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void detail() {
        Account account = getAccountFromSelectedRow();
        if (account == null) {
            JOptionPane.showMessageDialog(MiniSupermarket.main,
                I18n.get("messages", "data_table.selected.not"),
                I18n.get("dialog", "title.info"), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Log.info(dataTable.getDataFromSelectedRow(), account);
        dialogDetail.setData(account);
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
    public void refresh() {
        panelData.removeAll();
        panelData.revalidate();
        panelData.repaint();
        dataTable = getDataTable(accountBLL.findBy(__.ACCOUNT.DELETED, false));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelData.add(dataTable, gbc);
//        comboBoxFilter.setSelectedIndex(0);
        jTextFieldSearch.setText("");
        dialogAdd = new AccountDialog(I18n.get("dialog", "account.add"), false);
        dialogEdit = new AccountDialog(I18n.get("dialog", "account.edit"), false);
        dialogDetail = new AccountDialog(I18n.get("dialog", "account.detail"), true);
        System.gc();
    }
}
