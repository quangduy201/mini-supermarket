package mini_supermarket.BLL;

import mini_supermarket.DAL.ExportDetailDAL;
import mini_supermarket.DTO.ExportDetail;
import mini_supermarket.DTO.ExportDetailId;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class ExportDetailBLL extends RelationshipBLL<ExportDetail, ExportDetailId> {
    public ExportDetailBLL() {
        super(new ExportDetailDAL());
    }

    @Override
    public Pair<Boolean, String> exists(ExportDetail oldExportDetail, ExportDetail newExportDetail) {
        List<ExportDetail> exportDetails;
        exportDetails = findBy(
            __.EXPORT_DETAIL.EXPORT_NOTE, newExportDetail.getId().getExportNote(),
            __.EXPORT_DETAIL.SHIPMENT, newExportDetail.getId().getShipment());
        if (!exportDetails.isEmpty()) {
            String message = I18n.get("messages", "export_detail.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "export_detail.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.EXPORT_DETAIL.QUANTITY))
            return validateQuantity((String) value);
        return new Pair<>(false, I18n.get("messages", "export_detail.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateQuantity(String quantity) {
        if (quantity.isBlank())
            return new Pair<>(false, I18n.get("messages", "export_detail.validate.quantity.no_empty"));
        if (!VNString.checkUnsignedNumber(quantity))
            return new Pair<>(false, I18n.get("messages", "export_detail.validate.quantity.unsignedNumber.not"));
        return new Pair<>(true, I18n.get("messages", "export_detail.validate.quantity"));
    }
}
