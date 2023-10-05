package mini_supermarket.DAL;

import mini_supermarket.DTO.Role;

import java.util.List;

public class RoleDAL extends EntityDAL<Role> {
    public RoleDAL() {
        super(Role.class, List.of());
    }
}
