package mini_supermarket.BLL;

import mini_supermarket.DAL.AccountDAL;
import mini_supermarket.DTO.Account;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class AccountBLL extends EntityBLL<Account> {
    public AccountBLL() {
        super(new AccountDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Account account) {
        List<Account> accounts;
        accounts = findBy(__.ACCOUNT.ID, account.getId());
        if (!accounts.isEmpty()) {
            String message = I18n.get("messages", "account.exists");
            return new Pair<>(true, message);
        }

        accounts = findBy(
            __.ACCOUNT.USERNAME, account.getUsername(),
            __.ACCOUNT.DELETED, false);
        if (!accounts.isEmpty()) {
            String message = I18n.get("messages", "account.exists.username", account.getUsername());
            return new Pair<>(true, message);
        }

        accounts = findBy(
            __.ACCOUNT.STAFF, account.getStaff(),
            __.ACCOUNT.DELETED, false);
        if (!accounts.isEmpty()) {
            String message = I18n.get("messages", "account.exists.staff", account.getStaff().getName());
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "account.exists.not");
        return new Pair<>(false, message);
    }
}
