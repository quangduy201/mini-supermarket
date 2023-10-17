package mini_supermarket.DAL;

import mini_supermarket.DTO.Module;
import mini_supermarket.utils.__;

import java.util.List;

public class ModuleDAL extends EntityDAL<Module> {
    public ModuleDAL() {
        super(Module.class, List.of(
            __.MODULE.ID,
            __.MODULE.NAME
        ));
    }
}
