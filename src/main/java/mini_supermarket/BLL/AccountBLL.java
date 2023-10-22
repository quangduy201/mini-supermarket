package mini_supermarket.BLL;

import mini_supermarket.DAL.AccountDAL;
import mini_supermarket.DTO.Account;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
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

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.ACCOUNT.USERNAME))
            return validateUsername((String) value);
        if (attribute.equals(__.ACCOUNT.PASSWORD))
            return validatePassword((String) value);
        return new Pair<>(false, I18n.get("messages", "account.attribute.not_found", attribute));
    }

    public static Pair<Boolean, String> validateUsername(String username) {
        if (username.isBlank())
            return new Pair<>(false, I18n.get("messages", "account.validate.username.no_empty"));
        if (VNString.containsUnicode(username))
            return new Pair<>(false, I18n.get("messages", "account.validate.username.no_unicode"));
        if (VNString.containsSpecial(username))
            return new Pair<>(false, I18n.get("messages", "account.validate.username.no_special"));
        return new Pair<>(true, I18n.get("messages", "account.validate.username"));
    }

    public static Pair<Boolean, String> validatePassword(String password) {
        if (password.isBlank())
            return new Pair<>(false, I18n.get("messages", "account.validate.password.no_empty"));
        if (VNString.containsUnicode(password))
            return new Pair<>(false, I18n.get("messages", "account.validate.password.no_unicode"));
        if (!VNString.containsUpperCase(password))
            return new Pair<>(false, I18n.get("messages", "account.validate.password.upper"));
        if (!VNString.containsLowerCase(password))
            return new Pair<>(false, I18n.get("messages", "account.validate.password.lower"));
        if (!VNString.containsNumber(password))
            return new Pair<>(false, I18n.get("messages", "account.validate.password.number"));
        if (!VNString.containsSpecial(password))
            return new Pair<>(false, I18n.get("messages", "account.validate.password.special"));
        return new Pair<>(true, I18n.get("messages", "account.validate.password"));
    }
}
