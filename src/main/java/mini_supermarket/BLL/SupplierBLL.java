package mini_supermarket.BLL;

import mini_supermarket.DAL.SupplierDAL;
import mini_supermarket.DTO.Customer;
import mini_supermarket.DTO.Supplier;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.Arrays;
import java.util.List;

public class SupplierBLL extends SafeEntityBLL<Supplier> {
    public SupplierBLL() {
        super(new SupplierDAL());
    }

    public Pair<Boolean,String> addSupplier(Supplier supplier){
        Pair<Boolean, String> result;
        result = validateSuppleier(supplier);
        if(!result.getFirst()){
            return result;
        }

        result = exists(null, supplier);
        if(result.getFirst()){
            return new Pair<>(false, result.getSecond());
        }
        if (!add(supplier))
            return new Pair<>(false, I18n.get("message", "supplier.add.failed"));
        return new Pair<>(true, I18n.get("messages", "supplier.add.success"));
    }

    public Pair<Boolean, String> editSupplier(Supplier currentSupplier, Supplier supplier){
        Pair<Boolean, String> result;
        result = validateSuppleier(supplier);
        if (!result.getFirst())
            return result;

        supplier.setId(currentSupplier.getId());
        result = exists(currentSupplier, supplier);
        if (result.getFirst())
            return new Pair<>(false, result.getSecond());
        if (supplier.getId() == 1 || !update(supplier))
            return new Pair<>(false, I18n.get("messages", "supplier.edit.failed"));
        return new Pair<>(true, I18n.get("messages", "supplier.edit.success"));
    }

    public Pair<Boolean, String> removeSupplier(Supplier Supplier){
        if(Supplier.getId() == 1 || !delete(Supplier))
            return new Pair<>(false, I18n.get("messages", "supplier.remove.failed"));
        return new Pair<>(true, I18n.get("messages", "supplier.remove.success"));
    }
    @Override
    public Pair<Boolean, String> exists(Supplier oldSupplier, Supplier newSupplier) {
        boolean hasChanges = false;
        List<Supplier> suppliers;

       if(oldSupplier == null || newSupplier.getId()== null || !newSupplier.getId().equals(oldSupplier.getId())){
           hasChanges = true;
           suppliers = findBy(__.SUPPLIER.ID, newSupplier.getId());
           if(!suppliers.isEmpty())
               return  new Pair<>(true, I18n.get("messages", "supplier.exists"));
       }
       if(oldSupplier == null || !newSupplier.getPhone().equals(oldSupplier.getPhone())){
           hasChanges = true;
           suppliers = findBy(__.SUPPLIER.PHONE, newSupplier.getPhone());
           if (!suppliers.isEmpty())
               return new Pair<>(true, I18n.get("messages","supplier.exists.phone",newSupplier.getPhone()));
       }
       if(oldSupplier == null ||
           !newSupplier.getName().equals(oldSupplier.getName())||
           !newSupplier.getAddress().equals(oldSupplier.getAddress())||
           !newSupplier.getEmail().equals(oldSupplier.getEmail())){
           hasChanges = true;
       }
       if(!hasChanges)
           return new Pair<>(true, I18n.get("messages", "supplier.edit.unchanged"));
        return new Pair<>(false, I18n.get("messages", "supplier.exists.not"));

    }


    public static Pair<Boolean, String> validateSuppleier (Supplier supplier){
        Pair<Boolean, String> result;
        result = validate(__.SUPPLIER.NAME,supplier.getName());
        if(!result.getFirst())
            return new Pair<>(false, result.getSecond());

        result = validate(__.SUPPLIER.PHONE, supplier.getPhone());
        if(!result.getFirst())
            return new Pair<>(false, result.getSecond());

        result = validate(__.SUPPLIER.EMAIL, supplier.getEmail());
        if(!result.getFirst())
            return new Pair<>(false, result.getSecond());

        return new Pair<>(true,I18n.get("messages" , "supplier.validate"));



    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.SUPPLIER.NAME))
            return validateName((String) value);
        if (attribute.equals(__.SUPPLIER.PHONE))
            return validatePhone((String) value);
        if (attribute.equals(__.SUPPLIER.EMAIL))
            return validateEmail((String) value);
        return new Pair<>(false, I18n.get("messages", "supplier.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateName(String name) {
        if (name.isBlank())
            return new Pair<>(false, I18n.get("messages", "supplier.validate.name.no_empty"));
        if (VNString.containsSpecial(name))
            return new Pair<>(false, I18n.get("messages", "supplier.validate.name.no_special"));
        if (VNString.containsNumber(name))
            return new Pair<>(false, I18n.get("messages", "supplier.validate.name.no_number"));
        return new Pair<>(true, I18n.get("messages", "supplier.validate.name"));
    }

    private static Pair<Boolean, String> validatePhone(String phone) {
        if (phone.isBlank())
            return new Pair<>(false, I18n.get("messages", "supplier.validate.phone.no_empty"));
        if (!VNString.checkFormatPhone(phone))
            return new Pair<>(false, I18n.get("messages", "supplier.validate.phone.format.not"));
        return new Pair<>(true, I18n.get("messages", "supplier.validate.phone"));
    }

    private static Pair<Boolean, String> validateEmail(String email) {
        if (email.isBlank())
            return new Pair<>(false, I18n.get("messages", "supplier.validate.email.no_empty"));
        if (VNString.containsUnicode(email))
            return new Pair<>(false, I18n.get("messages", "supplier.validate.email.no_unicode"));
        if (!VNString.checkFormatOfEmail(email))
            return new Pair<>(false, I18n.get("messages", "supplier.validate.email.format.not"));
        return new Pair<>(true, I18n.get("messages", "supplier.validate.email"));
    }

    public static Pair<Long[], Object[][]> getDataFrom(List<Supplier> suppliers) {
        SupplierBLL supplierBLL = new SupplierBLL();
        Object[][] ids = supplierBLL.getData(suppliers, false, List.of(
            new Pair<>(__.SUPPLIER.COLUMN.ID, Long::parseLong)
        ));
        Long[] idsOfData = Arrays.stream(ids)
            .map(row -> (long) row[0])
            .toArray(Long[]::new);
        Object[][] data = supplierBLL.getData(suppliers, true, List.of(
            new Pair<>(__.SUPPLIER.COLUMN.NAME, String::toString),
            new Pair<>(__.SUPPLIER.COLUMN.PHONE, String::toString),
            new Pair<>(__.SUPPLIER.COLUMN.ADDRESS, String::toString),
            new Pair<>(__.SUPPLIER.COLUMN.EMAIL, String::toString)
        ));
        return new Pair<>(idsOfData, data);
    }
}
