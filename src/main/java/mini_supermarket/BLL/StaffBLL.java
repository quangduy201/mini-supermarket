package mini_supermarket.BLL;

import mini_supermarket.DAL.StaffDAL;
import mini_supermarket.DTO.Staff;
import mini_supermarket.utils.*;


import java.util.List;

public class StaffBLL extends EntityBLL<Staff> {
    public StaffBLL() {
        super(new StaffDAL());
    }

    public Pair<Boolean, String> addStaff(Staff staff){
        Pair<Boolean, String> Username;
        Username = validate(__.STAFF.NAME, staff.getName());
        if(!Username.getFirst())
            return new Pair<>(false, Username.getSecond());


        Pair<Boolean,String> Birthday;
        Birthday = validate(__.STAFF.BIRTHDATE ,staff.getBirthdate());
        if(!Birthday.getFirst())
            return new Pair<>(false,Birthday.getSecond());


        Pair<Boolean, String> Phone;
        Phone = validate(__.STAFF.PHONE,staff.getPhone());
        if(!Phone.getFirst())
            return new Pair<>(false, Phone.getSecond());


        Phone = exists(null, staff);
        if(!Phone.getFirst())
            return new Pair<>(false, Phone.getSecond());


        Pair<Boolean, String> Entrydate;
        Entrydate = validate(__.STAFF.ENTRY_DATE, staff.getEntryDate());
        if(!Entrydate.getFirst())
            return new Pair<>(false , Entrydate.getSecond());


        Pair<Boolean, String> Email;
        Email = validate(__.STAFF.EMAIL, staff.getEmail());
        if(!Email.getFirst())
            return new Pair<>(false, Email.getSecond());





        if(!add(staff)) {
            return new Pair<>(false, I18n.get("message", "staff.add.failed"));
        }

        return new Pair<>(true, I18n.get("messages", "staff.add.success"));
    }


    public Pair<Boolean, String> editStaff(Staff currentStaff, Staff staff) {
        Pair<Boolean, String> Username;
        Username = validate(__.STAFF.NAME, staff.getName());
        if(!Username.getFirst())
            return new Pair<>(false, Username.getSecond());

        Pair<Boolean, String> Gender;
        Gender = validate(__.STAFF.GENDER, staff.getGender());
        if(!Gender.getFirst())
            return new Pair<>(false, Gender.getSecond());


        Pair<Boolean, String> Birthday;
        Birthday = validate(__.STAFF.BIRTHDATE, staff.getBirthdate());
        if(!Birthday.getFirst())
            return new Pair<>(false, Birthday.getSecond());

        Pair<Boolean, String> Phone;
        Phone = validate(__.STAFF.PHONE, staff.getPhone());
        if(!Phone.getFirst())
            return new Pair<>(false, Phone.getSecond());


        Pair<Boolean, String> Address;
        Address = validate(__.STAFF.ADDRESS, staff.getAddress());
        if(!Address.getFirst())
            return new Pair<>(false, Address.getSecond());


        Pair<Boolean, String> Email;
        Email = validate(__.STAFF.EMAIL, staff.getEmail());
        if(!Email.getFirst())
            return new Pair<>(false, Email.getSecond());


        Pair<Boolean, String> Entrydate;
        Entrydate = validate(__.STAFF.ENTRY_DATE, staff.getEntryDate());
        if(!Entrydate.getFirst())
            return new Pair<>(false, Entrydate.getSecond());




        staff.setId(currentStaff.getId());

        return new Pair<>(true, I18n.get("messages", "staff.edit.success"));
    }


    public Pair<Boolean , String> removeStaff(Staff staff){
        if(!delete(staff))
            return new Pair<>(false, I18n.get("messages", "staff.remove.failed"));
        return new Pair<>(true, I18n.get("messages", "staff.remove.success"));
    }



    @Override
    public Pair<Boolean, String> exists(Staff oldStaff, Staff newStaff) {
        List<Staff> staffs;
        staffs = findBy(__.STAFF.ID, newStaff.getId());
        if (!staffs.isEmpty()) {
            String message = I18n.get("messages", "staff.exists");
            return new Pair<>(true, message);
        }

        staffs = findBy(
            __.STAFF.PHONE, newStaff.getPhone(),
            __.STAFF.DELETED, false);
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
        if(attribute.equals(__.STAFF.ADDRESS))
            return validateAddress((String) value);
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

//    private static Pair<Boolean, String> validateGender(String gender) {
//        if (gender == null) {
//            return new Pair<>(false, I18n.get("messages", "staff.validate.gender.no_empty"));
//        }
//        if (gender) {
//            return new Pair<>(true, I18n.get("messages", "staff.validate.gender.male"));
//        } else {
//            return new Pair<>(true, I18n.get("messages", "staff.validate.gender.female"));
//        }
//    }

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

    private static Pair<Boolean, String> validateAddress(String address) {
        if (address.isBlank())
            return new Pair<>(false, I18n.get("messages", "staff.validate.name.no_empty"));

        return new Pair<>(true, I18n.get("messages", "staff.validate.address"));
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
