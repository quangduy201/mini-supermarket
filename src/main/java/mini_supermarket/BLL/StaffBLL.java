package mini_supermarket.BLL;

import mini_supermarket.DAL.StaffDAL;
import mini_supermarket.DTO.Staff;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
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
}
