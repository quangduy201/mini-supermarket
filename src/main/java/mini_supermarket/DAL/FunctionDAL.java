package mini_supermarket.DAL;

import mini_supermarket.DTO.Function;

import java.util.List;

public class FunctionDAL extends EntityDAL<Function> {
    public FunctionDAL() {
        super(Function.class, List.of());
    }
}
