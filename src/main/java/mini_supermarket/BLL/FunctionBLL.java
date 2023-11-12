package mini_supermarket.BLL;

import mini_supermarket.DAL.FunctionDAL;
import mini_supermarket.DTO.Function;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class FunctionBLL extends EntityBLL<Function> {
    public FunctionBLL() {
        super(new FunctionDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Function oldFunction, Function newFunction) {
        List<Function> functions;
        functions = findBy(__.FUNCTION.ID, newFunction.getId());
        if (!functions.isEmpty()) {
            String message = I18n.get("messages", "function.exists");
            return new Pair<>(true, message);
        }

        functions = findBy(__.FUNCTION.NAME, newFunction.getName(), false);
        if (!functions.isEmpty()) {
            String message = I18n.get("messages", "function.exists.name", newFunction.getName());
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "function.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.FUNCTION.NAME))
            return validateName((String) value);
        return new Pair<>(false, I18n.get("messages", "function.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateName(String name) {
        if (name.isBlank())
            return new Pair<>(false, I18n.get("messages", "function.validate.name.no_empty"));
        if (VNString.containsNumber(name))
            return new Pair<>(false, I18n.get("messages", "function.validate.name.no_number"));
        if (VNString.containsSpecial(name))
            return new Pair<>(false, I18n.get("messages", "function.validate.name.no_special"));
        return new Pair<>(true, I18n.get("messages", "function.validate.name"));
    }
}
