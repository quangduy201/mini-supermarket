package mini_supermarket.BLL;

import mini_supermarket.DAL.CustomerDAL;
import mini_supermarket.DTO.Customer;
import mini_supermarket.utils.*;

import java.util.Arrays;
import java.util.List;

public class CustomerBLL extends SafeEntityBLL<Customer> {
    public CustomerBLL() {
        super(new CustomerDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Customer oldCustomer, Customer newCustomer) {
        List<Customer> customers;
        customers = findBy(__.CUSTOMER.ID, newCustomer.getId());
        if (!customers.isEmpty()) {
            String message = I18n.get("messages", "customer.exists");
            return new Pair<>(true, message);
        }

        customers = findBy(__.CUSTOMER.PHONE, newCustomer.getPhone());
        if (!customers.isEmpty()) {
            String message = I18n.get("messages", "customer.exists.phone", newCustomer.getPhone());
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "customer.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.CUSTOMER.NAME))
            return validateName((String) value);
        if (attribute.equals(__.CUSTOMER.BIRTHDATE))
            return validateBirthdate((String) value);
        if (attribute.equals(__.CUSTOMER.PHONE))
            return validatePhone((String) value);
        if (attribute.equals(__.CUSTOMER.SIGNED_UP_DATE))
            return validateSignedUpDate((String) value);
        if (attribute.equals(__.CUSTOMER.POINT))
            return validatePoint((String) value);
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

    private static Pair<Boolean, String> validateBirthdate(String birthdate) {
        if (birthdate.isBlank())
            return new Pair<>(false, I18n.get("messages", "customer.validate.birthdate.no_empty"));
        if (!VNString.checkFormatDate(birthdate))
            return new Pair<>(false, I18n.get("messages", "customer.validate.birthdate.format.not"));
        return new Pair<>(true, I18n.get("messages", "customer.validate.birthdate"));
    }

    private static Pair<Boolean, String> validatePhone(String phone) {
        if (phone.isBlank())
            return new Pair<>(false, I18n.get("messages", "customer.validate.phone.no_empty"));
        if (!VNString.checkFormatPhone(phone))
            return new Pair<>(false, I18n.get("messages", "customer.validate.phone.format.not"));
        return new Pair<>(true, I18n.get("messages", "customer.validate.phone"));
    }

    private static Pair<Boolean, String> validateSignedUpDate(String signedUpDate) {
        if (signedUpDate.isBlank())
            return new Pair<>(false, I18n.get("messages", "customer.validate.signedUpDate.no_empty"));
        if (!VNString.checkFormatDate(signedUpDate))
            return new Pair<>(false, I18n.get("messages", "customer.validate.signedUpDate.format.not"));
        return new Pair<>(true, I18n.get("messages", "customer.validate.signedUpDate"));
    }

    private static Pair<Boolean, String> validatePoint(String point) {
        if (point.isBlank())
            return new Pair<>(false, I18n.get("messages", "customer.validate.point.no_empty"));
        if (!VNString.checkUnsignedNumber(point))
            return new Pair<>(false, I18n.get("messages", "customer.validate.point.unsignedNumber.not"));
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
