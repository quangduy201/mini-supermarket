package mini_supermarket.BLL;

import mini_supermarket.DAL.ProductDAL;
import mini_supermarket.DTO.Product;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class ProductBLL extends SafeEntityBLL<Product> {
    public ProductBLL() {
        super(new ProductDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Product oldProduct, Product newProduct) {
        List<Product> products;
        products = findBy(__.PRODUCT.ID, newProduct.getId());
        if (!products.isEmpty()) {
            String message = I18n.get("messages", "product.exists");
            return new Pair<>(true, message);
        }

        products = findBy(__.PRODUCT.NAME, newProduct.getName());
        if (!products.isEmpty()) {
            String message = I18n.get("messages", "product.exists.name", newProduct.getName());
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "product.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.PRODUCT.NAME))
            return validateName((String) value);
        if (attribute.equals(__.PRODUCT.COST))
            return validateCost((String) value);
        if (attribute.equals(__.PRODUCT.BARCODE))
            return validateBarcode((String) value);
        return new Pair<>(false, I18n.get("messages", "product.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateName(String name) {
        if (name.isBlank())
            return new Pair<>(false, I18n.get("messages", "product.validate.name.no_empty"));
        if (VNString.containsSpecial(name))
            return new Pair<>(false, I18n.get("messages", "product.validate.name.no_special"));
        return new Pair<>(true, I18n.get("messages", "product.validate.name"));
    }

    private static Pair<Boolean, String> validateCost(String cost) {
        if (cost.isBlank())
            return new Pair<>(false, I18n.get("messages", "product.validate.cost.no_empty"));
        if (!VNString.checkUnsignedNumber(cost))
            return new Pair<>(false, I18n.get("messages", "product.validate.cost.unsigned_number.not"));
        return new Pair<>(true, I18n.get("messages", "product.validate.cost"));
    }

    private static Pair<Boolean, String> validateBarcode(String barcode) {
        if (barcode.isBlank())
            return new Pair<>(false, I18n.get("messages", "product.validate.barcode.no_empty"));
        if (VNString.containsSpecial(barcode))
            return new Pair<>(false, I18n.get("messages", "product.validate.barcode.no_special"));
        return new Pair<>(true, I18n.get("messages", "product.validate.barcode"));
    }
}
