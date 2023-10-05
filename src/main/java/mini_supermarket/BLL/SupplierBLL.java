package mini_supermarket.BLL;

import mini_supermarket.DAL.SupplierDAL;
import mini_supermarket.DTO.Supplier;

public class SupplierBLL extends EntityBLL<Supplier> {
    public SupplierBLL() {
        super(new SupplierDAL());
    }
}
