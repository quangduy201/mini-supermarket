package mini_supermarket.BLL;

import mini_supermarket.DAL.ProductDAL;
import mini_supermarket.DTO.Product;

public class ProductBLL extends EntityBLL<Product> {
    public ProductBLL() {
        super(new ProductDAL());
    }
}
