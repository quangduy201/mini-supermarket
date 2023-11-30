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

    public Pair<Boolean, String> addStaff(Staff staff) {
        Pair<Boolean, String> result;
        result = validateStaff(staff);
        if (!result.getFirst())
            return result;

        result = exists(null, staff);
        if (result.getFirst())
            return new Pair<>(false, result.getSecond());

        if (!add(staff))
            return new Pair<>(false, I18n.get("message", "staff.add.failed"));
        return new Pair<>(true, I18n.get("messages", "staff.add.success"));
    }

    public Pair<Boolean, String> editStaff(Staff currentStaff, Staff staff) {
        Pair<Boolean, String> result;
        result = validateStaff(staff);
        if (!result.getFirst())
            return result;

        staff.setId(currentStaff.getId());
        result = exists(currentStaff, staff);
        if (result.getFirst())
            return new Pair<>(false, result.getSecond());

        if (staff.getId() == 1 || !update(staff))
            return new Pair<>(false, I18n.get("messages", "staff.edit.failed"));
        return new Pair<>(true, I18n.get("messages", "staff.edit.success"));
    }


    public Pair<Boolean , String> removeStaff(Staff staff){
        if (staff.getId() == 1 || !delete(staff))
            return new Pair<>(false, I18n.get("messages", "staff.remove.failed"));
        return new Pair<>(true, I18n.get("messages", "staff.remove.success"));
    }

    @Override
    public Pair<Boolean, String> exists(Staff oldStaff, Staff newStaff) {
        boolean hasChanges = false;
        List<Staff> staffs;
        if (oldStaff == null || newStaff.getId() == null || !newStaff.getId().equals(oldStaff.getId())) {
            hasChanges = true;
            staffs = findBy(__.STAFF.ID, newStaff.getId());
            if (!staffs.isEmpty())
                return new Pair<>(true, I18n.get("messages", "staff.exists"));
        }

        if (oldStaff == null || !newStaff.getPhone().equals(oldStaff.getPhone())) {
            hasChanges = true;
            staffs = findBy(__.STAFF.PHONE, newStaff.getPhone());
            if (!staffs.isEmpty())
                return new Pair<>(true, I18n.get("messages", "staff.exists.phone", newStaff.getPhone()));
        }

        if (oldStaff == null ||
            !newStaff.getName().equals(oldStaff.getName()) ||
            !newStaff.getGender().equals(oldStaff.getGender()) ||
            !newStaff.getBirthdate().isEqual(oldStaff.getBirthdate()) ||
            !newStaff.getAddress().equals(oldStaff.getAddress()) ||
            !newStaff.getEmail().equals(oldStaff.getEmail()) ||
            !newStaff.getEntryDate().isEqual(oldStaff.getEntryDate())) {
            hasChanges = true;
        }

        if (!hasChanges)
            return new Pair<>(true, I18n.get("messages", "staff.edit.unchanged"));
        return new Pair<>(false, I18n.get("messages", "staff.exists.not"));
    }

    public static Pair<Boolean, String> validateStaff(Staff staff) {
        Pair<Boolean, String> result;
        result = validate(__.STAFF.NAME, staff.getName());
        if (!result.getFirst())
            return new Pair<>(false, result.getSecond());

        result = validate(__.STAFF.BIRTHDATE, staff.getBirthdate());
        if (!result.getFirst())
            return new Pair<>(false, result.getSecond());

        result = validate(__.STAFF.PHONE, staff.getPhone());
        if (!result.getFirst())
            return new Pair<>(false, result.getSecond());

        result = validate(__.STAFF.ADDRESS, staff.getAddress());
        if (!result.getFirst())
            return new Pair<>(false, result.getSecond());

        result = validate(__.STAFF.EMAIL, staff.getEmail());
        if (!result.getFirst())
            return new Pair<>(false, result.getSecond());

        result = validate(__.STAFF.ENTRY_DATE, staff.getEntryDate());
        if (!result.getFirst())
            return new Pair<>(false , result.getSecond());

        return new Pair<>(true, I18n.get("messages", "staff.validate"));
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.STAFF.NAME))
            return validateName((String) value);
        if (attribute.equals(__.STAFF.BIRTHDATE))
            return validateBirthdate((Date) value);
        if (attribute.equals(__.STAFF.PHONE))
            return validatePhone((String) value);
        if (attribute.equals(__.STAFF.ADDRESS))
            return validateAddress((String) value);
        if (attribute.equals(__.STAFF.EMAIL))
            return validateEmail((String) value);
        if (attribute.equals(__.STAFF.ENTRY_DATE))
            return validateEntryDate((Date) value);
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

    private static Pair<Boolean, String> validateBirthdate(Date birthdate) {
        if(birthdate == null)
            return new Pair<>(false, I18n.get("messages", "staff.validate.birthdate.no_empty"));


        if (!Date.isValidDate(birthdate.date))
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

    private static Pair<Boolean, String> validateAddress(String address) {
        if (address.isBlank())
            return new Pair<>(false, I18n.get("messages", "staff.validate.address.no_empty"));
        return new Pair<>(true, I18n.get("messages", "staff.validate.address"));
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

    private static Pair<Boolean, String> validateEntryDate(Date entryDate) {
        if(entryDate == null)
            return new Pair<>(false, I18n.get("messages", "staff.validate.entry_date.no_empty"));

        if (!Date.isValidDate(entryDate.date))
            return new Pair<>(false, I18n.get("messages", "staff.validate.entry_date.format.not"));
        return new Pair<>(true, I18n.get("messages", "staff.validate.entry_date"));
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
