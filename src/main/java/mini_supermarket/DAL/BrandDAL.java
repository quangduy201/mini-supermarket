package mini_supermarket.DAL;

import mini_supermarket.DTO.Brand;
import mini_supermarket.utils.__;

import java.util.List;

public class BrandDAL extends EntityDAL<Brand> {
    public BrandDAL() {
        super(Brand.class, List.of(
            __.BRAND.ID,
            __.BRAND.NAME,
            __.SUPPLIER.ID,
            __.SUPPLIER.NAME,
            __.SUPPLIER.ADDRESS,
            __.SUPPLIER.PHONE,
            __.SUPPLIER.EMAIL
        ));
    }
}
