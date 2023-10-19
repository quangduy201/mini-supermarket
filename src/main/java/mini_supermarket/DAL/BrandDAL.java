package mini_supermarket.DAL;

import mini_supermarket.DTO.Brand;
import mini_supermarket.utils.__;

import java.util.List;

public class BrandDAL extends EntityDAL<Brand> {
    public BrandDAL() {
        super(Brand.class, List.of(
            __.BRAND.COLUMN.ID,
            __.BRAND.COLUMN.NAME,
            __.SUPPLIER.COLUMN.ID,
            __.SUPPLIER.COLUMN.NAME,
            __.SUPPLIER.COLUMN.PHONE,
            __.SUPPLIER.COLUMN.ADDRESS,
            __.SUPPLIER.COLUMN.EMAIL
        ));
    }
}
