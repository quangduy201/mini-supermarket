package mini_supermarket.DAL;

import mini_supermarket.DTO.Supplier;
import mini_supermarket.utils.__;

import java.util.List;

public class SupplierDAL extends SafeEntityDAL<Supplier> {
    public SupplierDAL() {
        super(Supplier.class, List.of(
            __.SUPPLIER.COLUMN.ID,
            __.SUPPLIER.COLUMN.NAME,
            __.SUPPLIER.COLUMN.PHONE,
            __.SUPPLIER.COLUMN.ADDRESS,
            __.SUPPLIER.COLUMN.EMAIL
        ));
    }
}
