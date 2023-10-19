package mini_supermarket.DAL;

import mini_supermarket.DTO.Staff;
import mini_supermarket.utils.__;

import java.util.List;

public class StaffDAL extends EntityDAL<Staff> {
    public StaffDAL() {
        super(Staff.class, List.of(
            __.STAFF.COLUMN.ID,
            __.STAFF.COLUMN.NAME,
            __.STAFF.COLUMN.GENDER,
            __.STAFF.COLUMN.BIRTHDATE,
            __.STAFF.COLUMN.PHONE,
            __.STAFF.COLUMN.ADDRESS,
            __.STAFF.COLUMN.EMAIL,
            __.STAFF.COLUMN.ENTRY_DATE
        ));
    }
}
