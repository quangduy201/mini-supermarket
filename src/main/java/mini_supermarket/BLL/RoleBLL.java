package mini_supermarket.BLL;

import mini_supermarket.DAL.RoleDAL;
import mini_supermarket.DTO.Role;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class RoleBLL extends EntityBLL<Role> {
    public RoleBLL() {
        super(new RoleDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Role role) {
        List<Role> roles;
        roles = findBy(__.ROLE.ID, role.getId());
        if (!roles.isEmpty()) {
            String message = I18n.get("messages", "role.exists");
            return new Pair<>(true, message);
        }

        roles = findBy(
            __.ROLE.NAME, role.getName(),
            __.ROLE.DELETED, false);
        if (!roles.isEmpty()) {
            String message = I18n.get("messages", "role.exists.name");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "role.exists.not");
        return new Pair<>(false, message);
    }
}
