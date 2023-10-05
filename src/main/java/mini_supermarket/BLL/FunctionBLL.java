package mini_supermarket.BLL;

import mini_supermarket.DAL.FunctionDAL;
import mini_supermarket.DTO.Function;

public class FunctionBLL extends EntityBLL<Function> {
    public FunctionBLL() {
        super(new FunctionDAL());
    }
}
