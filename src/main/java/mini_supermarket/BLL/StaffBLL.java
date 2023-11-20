package mini_supermarket.BLL;

import mini_supermarket.DAL.StaffDAL;
import mini_supermarket.DTO.Staff;
import mini_supermarket.utils.*;

import java.util.Arrays;
import java.util.List;

public class StaffBLL extends SafeEntityBLL<Staff> {
    public StaffBLL() {
        super(new StaffDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Staff oldStaff, Staff newStaff) {
        List<Staff> staffs;
        staffs = findBy(__.STAFF.ID, newStaff.getId());
        if (!staffs.isEmpty()) {
            String message = I18n.get("messages", "staff.exists");
            return new Pair<>(true, message);
        }

        staffs = findBy(__.STAFF.PHONE, newStaff.getPhone());
        if (!staffs.isEmpty()) {
            String message = I18n.get("messages", "staff.exists.phone", newStaff.getPhone());
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

    public static Pair<Long[], Object[][]> getDataFrom(List<Staff> staffs) {
        StaffBLL staffBLL = new StaffBLL();
        Object[][] ids = staffBLL.getData(staffs, false, List.of(
            new Pair<>(__.STAFF.COLUMN.ID, Long::parseLong)
        ));
        Long[] idsOfData = Arrays.stream(ids)
            .map(row -> (long) row[0])
            .toArray(Long[]::new);
        Object[][] data = staffBLL.getData(staffs, true, List.of(
            new Pair<>(__.STAFF.COLUMN.NAME, String::toString),
            new Pair<>(__.STAFF.COLUMN.GENDER, s -> Boolean.parseBoolean(s) ? "Nam" : "Nữ"),
            new Pair<>(__.STAFF.COLUMN.BIRTHDATE, Date::parse),
            new Pair<>(__.STAFF.COLUMN.PHONE, String::toString),
            new Pair<>(__.STAFF.COLUMN.EMAIL, String::toString),
            new Pair<>(__.STAFF.COLUMN.ENTRY_DATE, Date::parse)
        ));
        return new Pair<>(idsOfData, data);
    }
}
