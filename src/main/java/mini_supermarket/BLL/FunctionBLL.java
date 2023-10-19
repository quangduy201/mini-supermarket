package mini_supermarket.BLL;

import mini_supermarket.DAL.FunctionDAL;
import mini_supermarket.DTO.Function;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class FunctionBLL extends EntityBLL<Function> {
    public FunctionBLL() {
        super(new FunctionDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Function function) {
        List<Function> functions;
        functions = findBy(__.FUNCTION.ID, function.getId());
        if (!functions.isEmpty()) {
            String message = I18n.get("messages", "function.exists");
            return new Pair<>(true, message);
        }

        functions = findBy(
            __.FUNCTION.NAME, function.getName(),
            __.FUNCTION.DELETED, false);
        if (!functions.isEmpty()) {
            String message = I18n.get("messages", "function.exists.name", function.getName());
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "function.exists.not");
        return new Pair<>(false, message);
    }
}
