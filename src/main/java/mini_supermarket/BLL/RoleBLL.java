package mini_supermarket.BLL;

import mini_supermarket.DAL.RoleDAL;
import mini_supermarket.DTO.Role;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class RoleBLL extends EntityBLL<Role> {
    public RoleBLL() {
        super(new RoleDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Role oldRole, Role newRole) {
        List<Role> roles;
        roles = findBy(__.ROLE.ID, newRole.getId());
        if (!roles.isEmpty()) {
            String message = I18n.get("messages", "role.exists");
            return new Pair<>(true, message);
        }

        roles = findBy(
            __.ROLE.NAME, newRole.getName(),
            __.ROLE.DELETED, false);
        if (!roles.isEmpty()) {
            String message = I18n.get("messages", "role.exists.name");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "role.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.ROLE.NAME))
            return validateName((String) value);
        return new Pair<>(false, I18n.get("messages", "role.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateName(String name) {
        if (name.isBlank())
            return new Pair<>(false, I18n.get("messages", "role.validate.name.no_empty"));
        if (VNString.containsNumber(name))
            return new Pair<>(false, I18n.get("messages", "role.validate.name.no_number"));
        if (VNString.containsSpecial(name))
            return new Pair<>(false, I18n.get("messages", "role.validate.name.no_special"));
        return new Pair<>(true, I18n.get("messages", "role.validate.name"));
    }
}
