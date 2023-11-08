package mini_supermarket.BLL;

import mini_supermarket.DAL.SupplierDAL;
import mini_supermarket.DTO.Supplier;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class SupplierBLL extends EntityBLL<Supplier> {
    public SupplierBLL() {
        super(new SupplierDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Supplier supplier) {
        List<Supplier> suppliers;
        suppliers = findBy(__.SUPPLIER.ID, supplier.getId());
        if (!suppliers.isEmpty()) {
            String message = I18n.get("messages", "supplier.exists");
            return new Pair<>(true, message);
        }

        suppliers = findBy(
            __.SUPPLIER.NAME, supplier.getName(),
            __.SUPPLIER.DELETED, false);
        if (!suppliers.isEmpty()) {
            String message = I18n.get("messages", "supplier.exists.name");
            return new Pair<>(true, message);
        }

        suppliers = findBy(
            __.SUPPLIER.PHONE, supplier.getPhone(),
            __.SUPPLIER.DELETED, false);
        if (!suppliers.isEmpty()) {
            String message = I18n.get("messages", "supplier.exists.phone");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "supplier.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.SUPPLIER.NAME))
            return validateName((String) value);
        if (attribute.equals(__.SUPPLIER.PHONE))
            return validatePhone((String) value);
        if (attribute.equals(__.SUPPLIER.EMAIL))
            return validateEmail((String) value);
        return new Pair<>(false, I18n.get("messages", "supplier.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateName(String name) {
        if (name.isBlank())
            return new Pair<>(false, I18n.get("messages", "supplier.validate.name.no_empty"));
        if (VNString.containsSpecial(name))
            return new Pair<>(false, I18n.get("messages", "supplier.validate.name.no_special"));
        if (VNString.containsNumber(name))
            return new Pair<>(false, I18n.get("messages", "supplier.validate.name.no_number"));
        return new Pair<>(true, I18n.get("messages", "supplier.validate.name"));
    }

    private static Pair<Boolean, String> validatePhone(String phone) {
        if (phone.isBlank())
            return new Pair<>(false, I18n.get("messages", "supplier.validate.phone.no_empty"));
        if (!VNString.checkFormatPhone(phone))
            return new Pair<>(false, I18n.get("messages", "supplier.validate.phone.format.not"));
        return new Pair<>(true, I18n.get("messages", "supplier.validate.phone"));
    }

    private static Pair<Boolean, String> validateEmail(String email) {
        if (email.isBlank())
            return new Pair<>(false, I18n.get("messages", "supplier.validate.email.no_empty"));
        if (VNString.containsUnicode(email))
            return new Pair<>(false, I18n.get("messages", "supplier.validate.email.no_unicode"));
        if (!VNString.checkFormatOfEmail(email))
            return new Pair<>(false, I18n.get("messages", "supplier.validate.email.format.not"));
        return new Pair<>(true, I18n.get("messages", "supplier.validate.email"));
    }
}
