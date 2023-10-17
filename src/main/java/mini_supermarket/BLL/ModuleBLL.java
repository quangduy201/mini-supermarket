package mini_supermarket.BLL;

import mini_supermarket.DAL.ModuleDAL;
import mini_supermarket.DTO.Module;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
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
            String message = I18n.getString("module.exists");
            return new Pair<>(true, message);
        }

        modules = findBy(
            __.MODULE.NAME, module.getName(),
            __.MODULE.DELETED, false);
        if (!modules.isEmpty()) {
            String message = I18n.getString("module.exists.name", module.getName());
            return new Pair<>(true, message);
        }

        String message = I18n.getString("module.exists.not");
        return new Pair<>(false, message);
    }
}
