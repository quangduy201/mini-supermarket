package mini_supermarket.BLL;

import mini_supermarket.DAL.ShipmentDAL;
import mini_supermarket.DTO.Shipment;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class ShipmentBLL extends EntityBLL<Shipment> {
    public ShipmentBLL() {
        super(new ShipmentDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Shipment oldShipment, Shipment newShipment) {
        List<Shipment> shipments;
        shipments = findBy(__.SHIPMENT.ID, newShipment.getId());
        if (!shipments.isEmpty()) {
            String message = I18n.get("messages", "shipment.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "shipment.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.SHIPMENT.UNIT_PRICE))
            return validateUnitPrice((String) value);
        if (attribute.equals(__.SHIPMENT.QUANTITY))
            return validateQuantity((String) value);
        if (attribute.equals(__.SHIPMENT.EXP))
            return validateEXP((String) value);
        if (attribute.equals(__.SHIPMENT.MFG))
            return validateMFG((String) value);
        if (attribute.equals(__.SHIPMENT.SKU))
            return validateSKU((String) value);
        return new Pair<>(false, I18n.get("messages", "shipment.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateUnitPrice(String unitPrice) {
        if (unitPrice.isBlank())
            return new Pair<>(false, I18n.get("messages", "shipment.validate.unit_price.no_empty"));
        if (!VNString.checkUnsignedNumber(unitPrice))
            return new Pair<>(false, I18n.get("messages", "shipment.validate.unit_price.unsigned_number.not"));
        return new Pair<>(true, I18n.get("messages", "shipment.validate.unit_price"));
    }

    private static Pair<Boolean, String> validateQuantity(String quantity) {
        if (quantity.isBlank())
            return new Pair<>(false, I18n.get("messages", "shipment.validate.quantity.no_empty"));
        if (!VNString.checkUnsignedNumber(quantity))
            return new Pair<>(false, I18n.get("messages", "shipment.validate.quantity.unsigned_number.not"));
        return new Pair<>(true, I18n.get("messages", "shipment.validate.quantity"));
    }

    private static Pair<Boolean, String> validateEXP(String exp) {
        if (exp.isBlank())
            return new Pair<>(false, I18n.get("messages", "shipment.validate.exp.no_empty"));
        if (!VNString.checkFormatDate(exp))
            return new Pair<>(false, I18n.get("messages", "shipment.validate.exp.format.not"));
        return new Pair<>(true, I18n.get("messages", "shipment.validate.exp"));
    }

    private static Pair<Boolean, String> validateMFG(String mfg) {
        if (mfg.isBlank())
            return new Pair<>(false, I18n.get("messages", "shipment.validate.mfg.no_empty"));
        if (!VNString.checkFormatDate(mfg))
            return new Pair<>(false, I18n.get("messages", "shipment.validate.mfg.format.not"));
        return new Pair<>(true, I18n.get("messages", "shipment.validate.mfg"));
    }

    private static Pair<Boolean, String> validateSKU(String sku) {
        if (sku.isBlank())
            return new Pair<>(false, I18n.get("messages", "shipment.validate.sku.no_empty"));
        if (VNString.containsSpecial(sku))
            return new Pair<>(false, I18n.get("messages", "shipment.validate.sku.no_special"));
        if (VNString.containsAlphabet(sku))
            return new Pair<>(false, I18n.get("messages", "shipment.validate.sku.no_alphabet"));
        return new Pair<>(true, I18n.get("messages", "shipment.validate.sku"));
    }
}
