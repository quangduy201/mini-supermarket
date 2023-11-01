package mini_supermarket.BLL;

import mini_supermarket.DAL.ModuleDAL;
import mini_supermarket.DTO.Module;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class ModuleBLL extends EntityBLL<Module> {
    public ModuleBLL() {
        super(new ModuleDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Module module) {
        List<Module> modules;
        modules = findBy(__.MODULE.ID, module.getId());
        if (!modules.isEmpty()) {
            String message = I18n.get("messages", "module.exists");
            return new Pair<>(true, message);
        }

        modules = findBy(__.MODULE.NAME, module.getName(), false);
        if (!modules.isEmpty()) {
            String message = I18n.get("messages", "module.exists.name", module.getName());
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "module.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.MODULE.NAME))
            return validateName((String) value);
        return new Pair<>(false, I18n.get("messages", "module.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateName(String name) {
        if (name.isBlank())
            return new Pair<>(false, I18n.get("messages", "module.validate.name.no_empty"));
        if (VNString.containsNumber(name))
            return new Pair<>(false, I18n.get("messages", "module.validate.name.no_number"));
        if (VNString.containsSpecial(name))
            return new Pair<>(false, I18n.get("messages", "module.validate.name.no_special"));
        return new Pair<>(true, I18n.get("messages", "module.validate.name"));
    }
}
