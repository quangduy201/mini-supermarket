package mini_supermarket.BLL;

import mini_supermarket.DAL.RoleDAL;
import mini_supermarket.DTO.Role;
import mini_supermarket.utils.*;

import java.util.Arrays;
import java.util.List;

public class RoleBLL extends SafeEntityBLL<Role> {
    public RoleBLL() {
        super(new RoleDAL());
    }

    public Pair<Boolean, String> addRole(Role role) {
        Pair<Boolean, String> result;
        result = validate(__.ROLE.NAME, role.getName());
        if (!result.getFirst())
            return new Pair<>(false, result.getSecond());

        result = exists(null, role);
        if (result.getFirst())
            return new Pair<>(false, result.getSecond());

        if (!add(role))
            return new Pair<>(false, I18n.get("messages", "role.add.failed"));
        return new Pair<>(true, I18n.get("messages", "role.add.success"));
    }

    public Pair<Boolean, String> editRole(Role currentRole, Role role) {
        Pair<Boolean, String> result;
        result = validate(__.ROLE.NAME, role.getName());
        if (!result.getFirst())
            return new Pair<>(false, result.getSecond());

        role.setId(currentRole.getId());
        result = exists(currentRole, role);
        if (result.getFirst())
            return new Pair<>(false, result.getSecond());

        if (role.getId() == 1 || !update(role))
            return new Pair<>(false, I18n.get("messages", "role.edit.failed"));
        return new Pair<>(true, I18n.get("messages", "role.edit.success"));
    }

    public Pair<Boolean, String> removeRole(Role role) {
        // TODO: can't remove when there is still at least one account with this role
        if (role.getId() == 1 || !delete(role))
            return new Pair<>(false, I18n.get("messages", "role.remove.failed"));
        return new Pair<>(true, I18n.get("messages", "role.remove.success"));
    }

    @Override
    public Pair<Boolean, String> exists(Role oldRole, Role newRole) {
        boolean hasChanges = false;
        List<Role> roles;
        if (oldRole == null || newRole.getId() == null || !newRole.getId().equals(oldRole.getId())) {
            hasChanges = true;
            roles = findBy(__.ROLE.ID, newRole.getId());
            if (!roles.isEmpty())
                return new Pair<>(true, I18n.get("messages", "role.exists"));
        }

        if (oldRole == null || !newRole.getName().equals(oldRole.getName())) {
            hasChanges = true;
            roles = findBy(__.ROLE.NAME, newRole.getName());
            if (!roles.isEmpty())
                return new Pair<>(true, I18n.get("messages", "role.exists.name", newRole.getName()));
        }

        if (!hasChanges)
            return new Pair<>(true, I18n.get("messages", "role.edit.unchanged"));
        return new Pair<>(false, I18n.get("messages", "role.exists.not"));
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

    public static Pair<Long[], Object[][]> getDataFrom(List<Role> roles) {
        RoleBLL roleBLL = new RoleBLL();
        Object[][] ids = roleBLL.getData(roles, false, List.of(
            new Pair<>(__.ROLE.COLUMN.ID, Long::parseLong)
        ));
        Long[] idsOfData = Arrays.stream(ids)
            .map(row -> (Long) row[0])
            .toArray(Long[]::new);
        Object[][] data = roleBLL.getData(roles, true, List.of(
            new Pair<>(__.ROLE.COLUMN.NAME, String::toString)
        ));
        return new Pair<>(idsOfData, data);
    }
}
