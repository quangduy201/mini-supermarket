package mini_supermarket.BLL;

import mini_supermarket.DAL.RoleDAL;
import mini_supermarket.DTO.Role;

public class RoleBLL extends EntityBLL<Role> {
    public RoleBLL() {
        super(new RoleDAL());
    }
}
