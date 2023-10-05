package mini_supermarket.BLL;

import mini_supermarket.DAL.CustomerDAL;
import mini_supermarket.DTO.Customer;

public class CustomerBLL extends EntityBLL<Customer>{
    public CustomerBLL() {
        super(new CustomerDAL());
    }
}
