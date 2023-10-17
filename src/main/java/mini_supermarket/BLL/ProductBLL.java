package mini_supermarket.BLL;

import mini_supermarket.DAL.ProductDAL;
import mini_supermarket.DTO.Product;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class ProductBLL extends EntityBLL<Product> {
    public ProductBLL() {
        super(new ProductDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Product product) {
        List<Product> products;
        products = findBy(__.PRODUCT.ID, product.getId());
        if (!products.isEmpty()) {
            String message = I18n.getString("product.exists");
            return new Pair<>(true, message);
        }

        products = findBy(
            __.PRODUCT.NAME, product.getName(),
            __.PRODUCT.DELETED, false);
        if (!products.isEmpty()) {
            String message = I18n.getString("product.exists.name", product.getName());
            return new Pair<>(true, message);
        }

        String message = I18n.getString("product.exists.not");
        return new Pair<>(false, message);
    }
}
