package mini_supermarket.DAL;

import mini_supermarket.DTO.Product;
import mini_supermarket.utils.__;

import java.util.List;

public class ProductDAL extends EntityDAL<Product> {
    public ProductDAL() {
        super(Product.class, List.of(
            __.PRODUCT.ID,
            __.PRODUCT.NAME,
            __.BRAND.ID,
            __.BRAND.NAME,
            __.SUPPLIER.ID,
            __.SUPPLIER.NAME,
            __.SUPPLIER.ADDRESS,
            __.SUPPLIER.PHONE,
            __.SUPPLIER.EMAIL,
            __.CATEGORY.ID,
            __.CATEGORY.NAME,
            __.CATEGORY.QUANTITY,
            __.PRODUCT.UNIT,
            __.PRODUCT.COST,
            __.PRODUCT.QUANTITY,
            __.PRODUCT.IMAGE,
            __.PRODUCT.BARCODE
        ));
    }
}
