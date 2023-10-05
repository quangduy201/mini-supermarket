package mini_supermarket.BLL;

import mini_supermarket.DAL.ModuleDAL;
import mini_supermarket.DTO.Module;

public class ModuleBLL extends EntityBLL<Module> {
    public ModuleBLL() {
        super(new ModuleDAL());
    }
}
