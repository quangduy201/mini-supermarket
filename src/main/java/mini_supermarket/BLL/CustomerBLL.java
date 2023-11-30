package mini_supermarket.BLL;

import mini_supermarket.DAL.CustomerDAL;
import mini_supermarket.DTO.Customer;
import mini_supermarket.DTO.Staff;
import mini_supermarket.utils.*;

import java.util.Arrays;
import java.util.List;

public class CustomerBLL extends SafeEntityBLL<Customer> {
    public CustomerBLL() {
        super(new CustomerDAL());
    }



    public Pair<Boolean,String> addCustomer(Customer customer){
        Pair<Boolean, String> result;
        result = validateCustomer(customer);
        if(!result.getFirst()){
            return result;
        }

        result = exists(null, customer);
        if(result.getFirst()){
            return new Pair<>(false, result.getSecond());
        }
        if (!add(customer))
            return new Pair<>(false, I18n.get("message", "customer.add.failed"));
        return new Pair<>(true, I18n.get("messages", "customer.add.success"));
    }


    public Pair<Boolean, String> editCustomer(Customer currentCustomer, Customer customer){
        Pair<Boolean, String> result;
        result = validateCustomer(customer);
        if (!result.getFirst())
            return result;

        customer.setId(currentCustomer.getId());
        result = exists(currentCustomer, customer);
        if (result.getFirst())
            return new Pair<>(false, result.getSecond());
        if (customer.getId() == 1 || !update(customer))
            return new Pair<>(false, I18n.get("messages", "customer.edit.failed"));
        return new Pair<>(true, I18n.get("messages", "customer.edit.success"));
    }


    public Pair<Boolean, String> removeCustomer(Customer customer){
        if(customer.getId() == 1 || !delete(customer))
            return new Pair<>(false, I18n.get("messages", "customer.remove.failed"));
        return new Pair<>(true, I18n.get("messages", "Customer.remove.success"));
    }


    @Override
    public Pair<Boolean, String> exists(Customer oldCustomer, Customer newCustomer) {
        boolean hasChanges = false;
        List<Customer> customers;
        if(oldCustomer == null || newCustomer.getId() == null || !newCustomer.getId().equals(oldCustomer.getId())){
            hasChanges = true;
            customers = findBy(__.CUSTOMER.ID, newCustomer.getId());
            if (!customers.isEmpty())
                return new Pair<>(true, I18n.get("messages", "customer.exists"));
        }
        if (oldCustomer == null || !newCustomer.getPhone().equals(oldCustomer.getPhone())){
            hasChanges = true;
            customers = findBy(__.CUSTOMER.PHONE, newCustomer.getPhone());
            if(!customers.isEmpty())
                return new Pair<>(true, I18n.get("messages", "customer.exists.phone",newCustomer.getPhone()));
        }
        if(oldCustomer == null ||
            !newCustomer.getName().equals(oldCustomer.getName())||
            !newCustomer.getGender().equals((oldCustomer.getGender()))||
            !newCustomer.getMembership().equals(oldCustomer.getMembership())||
            !newCustomer.getPoint().equals(oldCustomer.getPoint())||
            !newCustomer.getSignedUpDate().isEqual(oldCustomer.getSignedUpDate())){
            hasChanges = true;
        }
        if(!hasChanges)
            return new Pair<>(true, I18n.get("messages", "customer.edit.unchanged"));
        return new Pair<>(false, I18n.get("messages", "customer.exists.not"));
    }

    public static Pair<Boolean, String> validateCustomer(Customer customer){
        Pair<Boolean, String> result;
        result = validate(__.CUSTOMER.NAME,customer.getName());
        if(!result.getFirst())
            return new Pair<>(false,result.getSecond());

        result = validate(__.CUSTOMER.PHONE,customer.getPhone());
        if(!result.getFirst())
            return new Pair<>(false,result.getSecond());

        result = validate(__.CUSTOMER.SIGNED_UP_DATE,customer.getSignedUpDate());
        if(!result.getFirst())
            return new Pair<>(false,result.getSecond());

        result = validate(__.CUSTOMER.POINT,customer.getPoint());
        if(!result.getFirst())
            return new Pair<>(false,result.getSecond());
        return new Pair<>(true, I18n.get("messages", "staff.validate"));
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.CUSTOMER.NAME))
            return validateName((String) value);
        if (attribute.equals(__.CUSTOMER.PHONE))
            return validatePhone((String) value);
        if (attribute.equals(__.CUSTOMER.SIGNED_UP_DATE))
            return validateSignedUpDate((Date) value);
        if (attribute.equals(__.CUSTOMER.POINT))
            return validatePoint((Integer) value);
        return new Pair<>(false, I18n.get("messages", "customer.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateName(String name) {
        if (name.isBlank())
            return new Pair<>(false, I18n.get("messages", "customer.validate.name.no_empty"));
        if (VNString.containsSpecial(name))
            return new Pair<>(false, I18n.get("messages", "customer.validate.name.no_special"));
        if (VNString.containsNumber(name))
            return new Pair<>(false, I18n.get("messages", "customer.validate.name.no_number"));
        return new Pair<>(true, I18n.get("messages", "customer.validate.name"));
    }


    private static Pair<Boolean, String> validatePhone(String phone) {
        if (phone.isBlank())
            return new Pair<>(false, I18n.get("messages", "customer.validate.phone.no_empty"));
        if (!VNString.checkFormatPhone(phone))
            return new Pair<>(false, I18n.get("messages", "customer.validate.phone.format.not"));
        return new Pair<>(true, I18n.get("messages", "customer.validate.phone"));
    }

    private static Pair<Boolean, String> validateSignedUpDate(Date signedUpDate) {
        if(signedUpDate == null)
            return new Pair<>(false, I18n.get("messages", "customer.validate.signed_up_date.no_empty"));

        if(!Date.isValidDate(signedUpDate.date))
            return new Pair<>(false, I18n.get("messages", "customer.validate.signed_up_date.format.not"));
        return new Pair<>(true, I18n.get("messages", "customer.validate.signed_up_date"));
    }

    private static Pair<Boolean, String> validatePoint(Integer point) {
//        if(point.isBlank())
//            return new Pair<>(false, I18n.get("messages", "customer.validate.point.no_empty"));


            if (point < 0)
                return new Pair<>(false, I18n.get("messages", "customer.validate.point.unsigned_number.not"));


        return new Pair<>(true, I18n.get("messages", "customer.validate.point"));
    }

    public static Pair<Long[], Object[][]> getDataFrom(List<Customer> customers) {
        CustomerBLL customerBLL = new CustomerBLL();
        Object[][] ids = customerBLL.getData(customers, false, List.of(
            new Pair<>(__.CUSTOMER.COLUMN.ID, Long::parseLong)
        ));
        Long[] idsOfData = Arrays.stream(ids)
            .map(row -> (long) row[0])
            .toArray(Long[]::new);
        Object[][] data = customerBLL.getData(customers, true, List.of(
            new Pair<>(__.CUSTOMER.COLUMN.NAME, String::toString),
            new Pair<>(__.CUSTOMER.COLUMN.GENDER, s -> Boolean.parseBoolean(s) ? "Nam" : "Nữ"),
            new Pair<>(__.CUSTOMER.COLUMN.PHONE, String::toString),
            new Pair<>(__.CUSTOMER.COLUMN.MEMBERSHIP, s -> Boolean.parseBoolean(s) ? "Có" : "" ),
            new Pair<>(__.CUSTOMER.COLUMN.POINT, Integer::valueOf),
            new Pair<>(__.CUSTOMER.COLUMN.SIGNED_UP_DATE, Date::parse)
        ));
        return new Pair<>(idsOfData, data);
    }
}
