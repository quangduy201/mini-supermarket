package mini_supermarket.DAL;

import mini_supermarket.DTO.Supplier;
import mini_supermarket.utils.__;

import java.util.List;

public class SupplierDAL extends EntityDAL<Supplier> {
    public SupplierDAL() {
        super(Supplier.class, List.of(
            __.SUPPLIER.ID,
            __.SUPPLIER.NAME,
            __.SUPPLIER.ADDRESS,
            __.SUPPLIER.PHONE,
            __.SUPPLIER.EMAIL
        ));
    }
}
