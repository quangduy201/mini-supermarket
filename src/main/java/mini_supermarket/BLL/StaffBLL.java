package mini_supermarket.BLL;

import mini_supermarket.DAL.StaffDAL;
import mini_supermarket.DTO.Staff;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class StaffBLL extends EntityBLL<Staff> {
    public StaffBLL() {
        super(new StaffDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Staff staff) {
        List<Staff> staffs;
        staffs = findBy(__.STAFF.ID, staff.getId());
        if (!staffs.isEmpty()) {
            String message = I18n.get("messages", "staff.exists");
            return new Pair<>(true, message);
        }

        staffs = findBy(
            __.STAFF.PHONE, staff.getPhone(),
            __.STAFF.DELETED, false);
        if (!staffs.isEmpty()) {
            String message = I18n.get("messages", "staff.exists.phone", staff.getPhone());
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "staff.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.STAFF.NAME))
            return validateName((String) value);
        if (attribute.equals(__.STAFF.BIRTHDATE))
            return validateBirthdate((String) value);
        if (attribute.equals(__.STAFF.PHONE))
            return validatePhone((String) value);
        if (attribute.equals(__.STAFF.ENTRY_DATE))
            return validateEntryDate((String) value);
        if (attribute.equals(__.STAFF.EMAIL))
            return validateEmail((String) value);
        return new Pair<>(false, I18n.get("messages", "staff.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateName(String name) {
        if (name.isBlank())
            return new Pair<>(false, I18n.get("messages", "staff.validate.name.no_empty"));
        if (VNString.containsSpecial(name))
            return new Pair<>(false, I18n.get("messages", "staff.validate.name.no_special"));
        if (VNString.containsNumber(name))
            return new Pair<>(false, I18n.get("messages", "staff.validate.name.no_number"));
        return new Pair<>(true, I18n.get("messages", "staff.validate.name"));
    }

    private static Pair<Boolean, String> validateBirthdate(String birthdate) {
        if (birthdate.isBlank())
            return new Pair<>(false, I18n.get("messages", "staff.validate.birthdate.no_empty"));
        if (!VNString.checkFormatDate(birthdate))
            return new Pair<>(false, I18n.get("messages", "staff.validate.birthdate.format.not"));
        return new Pair<>(true, I18n.get("messages", "staff.validate.birthdate"));
    }

    private static Pair<Boolean, String> validatePhone(String phone) {
        if (phone.isBlank())
            return new Pair<>(false, I18n.get("messages", "staff.validate.phone.no_empty"));
        if (!VNString.checkFormatPhone(phone))
            return new Pair<>(false, I18n.get("messages", "staff.validate.phone.format.not"));
        return new Pair<>(true, I18n.get("messages", "staff.validate.phone"));
    }

    private static Pair<Boolean, String> validateEntryDate(String entryDate) {
        if (entryDate.isBlank())
            return new Pair<>(false, I18n.get("messages", "staff.validate.entryDate.no_empty"));
        if (!VNString.checkFormatDate(entryDate))
            return new Pair<>(false, I18n.get("messages", "staff.validate.entryDate.format.not"));
        return new Pair<>(true, I18n.get("messages", "staff.validate.entryDate"));
    }

    private static Pair<Boolean, String> validateEmail(String email) {
        if (email.isBlank())
            return new Pair<>(false, I18n.get("messages", "staff.validate.email.no_empty"));
        if (VNString.containsUnicode(email))
            return new Pair<>(false, I18n.get("messages", "staff.validate.email.no_unicode"));
        if (!VNString.checkFormatOfEmail(email))
            return new Pair<>(false, I18n.get("messages", "staff.validate.email.format.not"));
        return new Pair<>(true, I18n.get("messages", "staff.validate.email"));
    }
}
