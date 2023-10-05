package mini_supermarket.DAL;

import mini_supermarket.DTO.Customer;

import java.util.List;

public class CustomerDAL extends EntityDAL<Customer> {
    public CustomerDAL() {
        super(Customer.class, List.of());
    }
}
