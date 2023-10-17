package mini_supermarket.DAL;

import mini_supermarket.DTO.Function;
import mini_supermarket.utils.__;

import java.util.List;

public class FunctionDAL extends EntityDAL<Function> {
    public FunctionDAL() {
        super(Function.class, List.of(
            __.FUNCTION.ID,
            __.FUNCTION.NAME
        ));
    }
}
