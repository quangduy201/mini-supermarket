package mini_supermarket.BLL;

import mini_supermarket.DAL.SupplierDAL;
import mini_supermarket.DTO.Supplier;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class SupplierBLL extends EntityBLL<Supplier> {
    public SupplierBLL() {
        super(new SupplierDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Supplier supplier) {
        List<Supplier> suppliers;
        suppliers = findBy(__.SUPPLIER.ID, supplier.getId());
        if (!suppliers.isEmpty()) {
            String message = I18n.getString("supplier.exists");
            return new Pair<>(true, message);
        }

        suppliers = findBy(
            __.SUPPLIER.NAME, supplier.getName(),
            __.SUPPLIER.DELETED, false);
        if (!suppliers.isEmpty()) {
            String message = I18n.getString("supplier.exists.name");
            return new Pair<>(true, message);
        }

        suppliers = findBy(
            __.SUPPLIER.PHONE, supplier.getPhone(),
            __.SUPPLIER.DELETED, false);
        if (!suppliers.isEmpty()) {
            String message = I18n.getString("supplier.exists.phone");
            return new Pair<>(true, message);
        }

        String message = I18n.getString("supplier.exists.not");
        return new Pair<>(false, message);
    }
}
