package mini_supermarket.DAL;

import mini_supermarket.DTO.Staff;
import mini_supermarket.utils.__;

import java.util.List;

public class StaffDAL extends EntityDAL<Staff> {
    public StaffDAL() {
        super(Staff.class, List.of(
            __.STAFF.ID,
            __.STAFF.NAME,
            __.STAFF.GENDER,
            __.STAFF.BIRTHDATE,
            __.STAFF.PHONE,
            __.STAFF.ADDRESS,
            __.STAFF.EMAIL,
            __.STAFF.ENTRY_DATE
        ));
    }
}
