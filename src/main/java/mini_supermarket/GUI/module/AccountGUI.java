package mini_supermarket.GUI.module;

import mini_supermarket.BLL.AccountBLL;
import mini_supermarket.DTO.Account;
import mini_supermarket.DTO.Function;
import mini_supermarket.GUI.component.DataTable;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.ControlLayout;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.utils.Log;
import mini_supermarket.utils.__;

import java.util.List;

public class AccountGUI extends ControlLayout {
    private final RoundPanel panelFunction;
    private final RoundPanel panelData;
    private LeftRightLayout layoutFormAndData;

    public AccountGUI(List<Function> functions) {
        super(functions);
        panelFunction = getTopPanel();

        panelData = getBottomPanel();
        
        AccountBLL accountBLL = new AccountBLL();
        List<Account> accounts = accountBLL.findAll();
        panelData.add(new DataTable(
            accountBLL.getData(accounts,
                __.ACCOUNT.COLUMN.USERNAME,
                __.ROLE.COLUMN.NAME,
                __.STAFF.COLUMN.NAME,
                __.STAFF.COLUMN.GENDER
            ), new Object[]{
                "Tên tài khoản",
                "Tên chức vụ",
                "Tên nhân viên",
                "Giới tính"
            }, e -> Log.debug(AccountGUI.class, "Test")
        ));
    }
}
