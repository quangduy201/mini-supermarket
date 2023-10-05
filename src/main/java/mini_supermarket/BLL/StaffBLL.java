package mini_supermarket.BLL;

import mini_supermarket.DAL.StaffDAL;
import mini_supermarket.DTO.Staff;

public class StaffBLL extends EntityBLL<Staff> {
    public StaffBLL() {
        super(new StaffDAL());
    }
}
