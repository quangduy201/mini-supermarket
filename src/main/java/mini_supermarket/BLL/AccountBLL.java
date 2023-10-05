package mini_supermarket.BLL;

import mini_supermarket.DAL.AccountDAL;
import mini_supermarket.DTO.Account;

public class AccountBLL extends EntityBLL<Account> {
    public AccountBLL() {
        super(new AccountDAL());
    }
}
