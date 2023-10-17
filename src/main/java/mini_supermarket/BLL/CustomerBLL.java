package mini_supermarket.BLL;

import mini_supermarket.DAL.CustomerDAL;
import mini_supermarket.DTO.Customer;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class CustomerBLL extends EntityBLL<Customer>{
    public CustomerBLL() {
        super(new CustomerDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Customer customer) {
        List<Customer> customers;
        customers = findBy(__.CUSTOMER.ID, customer.getId());
        if (!customers.isEmpty()) {
            String message = I18n.getString("customer.exists");
            return new Pair<>(true, message);
        }

        customers = findBy(
            __.CUSTOMER.PHONE, customer.getPhone(),
            __.CUSTOMER.DELETED, false);
        if (!customers.isEmpty()) {
            String message = I18n.getString("customer.exists.phone", customer.getPhone());
            return new Pair<>(true, message);
        }

        String message = I18n.getString("customer.exists.not");
        return new Pair<>(false, message);
    }
}
