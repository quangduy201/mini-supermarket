package mini_supermarket.DAL;

import mini_supermarket.DTO.Supplier;

import java.util.List;

public class SupplierDAL extends EntityDAL<Supplier> {
    public SupplierDAL() {
        super(Supplier.class, List.of());
    }
}
