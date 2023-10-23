package mini_supermarket.DAL;

import mini_supermarket.DTO.Role;
import mini_supermarket.utils.__;

import java.util.List;

public class RoleDAL extends SafeEntityDAL<Role> {
    public RoleDAL() {
        super(Role.class, List.of(
            __.ROLE.COLUMN.ID,
            __.ROLE.COLUMN.NAME
        ));
    }
}
