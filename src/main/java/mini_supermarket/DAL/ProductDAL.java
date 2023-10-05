package mini_supermarket.DAL;

import mini_supermarket.DTO.Product;

import java.util.List;

public class ProductDAL extends EntityDAL<Product> {
    public ProductDAL() {
        super(Product.class, List.of());
    }
}
