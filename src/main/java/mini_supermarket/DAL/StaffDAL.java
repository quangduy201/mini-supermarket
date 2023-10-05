package mini_supermarket.DAL;

import mini_supermarket.DTO.Staff;

import java.util.List;

public class StaffDAL extends EntityDAL<Staff> {
    public StaffDAL() {
        super(Staff.class, List.of());
    }
}
