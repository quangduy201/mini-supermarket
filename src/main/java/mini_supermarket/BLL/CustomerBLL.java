package mini_supermarket.BLL;

import mini_supermarket.DAL.CustomerDAL;
import mini_supermarket.DTO.Customer;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class CustomerBLL extends EntityBLL<Customer> {
    public CustomerBLL() {
        super(new CustomerDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Customer customer) {
        List<Customer> customers;
        customers = findBy(__.CUSTOMER.ID, customer.getId());
        if (!customers.isEmpty()) {
            String message = I18n.get("messages", "customer.exists");
            return new Pair<>(true, message);
        }

        customers = findBy(
            __.CUSTOMER.PHONE, customer.getPhone(),
            __.CUSTOMER.DELETED, false);
        if (!customers.isEmpty()) {
            String message = I18n.get("messages", "customer.exists.phone", customer.getPhone());
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
}
