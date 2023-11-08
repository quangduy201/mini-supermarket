package mini_supermarket.BLL;

import mini_supermarket.DAL.ReceiptDetailDAL;
import mini_supermarket.DTO.ReceiptDetail;
import mini_supermarket.DTO.ReceiptDetailId;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class ReceiptDetailBLL extends RelationshipBLL<ReceiptDetail, ReceiptDetailId> {
    public ReceiptDetailBLL() {
        super(new ReceiptDetailDAL());
    }

    @Override
    public Pair<Boolean, String> exists(ReceiptDetail receiptDetail) {
        List<ReceiptDetail> receiptDetails;
        receiptDetails = findBy(
            __.RECEIPT_DETAIL.RECEIPT, receiptDetail.getId().getReceipt(),
            __.RECEIPT_DETAIL.PRODUCT, receiptDetail.getId().getProduct());
        if (!receiptDetails.isEmpty()) {
            String message = I18n.get("messages", "receipt_detail.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "receipt_detail.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.RECEIPT_DETAIL.QUANTITY))
            return validateQuantity((String) value);
        if (attribute.equals(__.RECEIPT_DETAIL.PERCENT))
            return validatePercent((String) value);
        return new Pair<>(false, I18n.get("messages", "receipt_detail.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateQuantity(String quantity) {
        if (quantity.isBlank())
            return new Pair<>(false, I18n.get("messages", "receipt_detail.validate.quantity.no_empty"));
        if (!VNString.checkUnsignedNumber(quantity))
            return new Pair<>(false, I18n.get("messages", "receipt_detail.validate.quantity.unsignedNumber.not"));
        return new Pair<>(true, I18n.get("messages", "receipt_detail.validate.quantity"));
    }

    private static Pair<Boolean, String> validatePercent(String percent) {
        if (percent.isBlank())
            return new Pair<>(false, I18n.get("messages", "receipt_detail.validate.percent.no_empty"));
        if (!VNString.checkRangeOfPercent(percent))
            return new Pair<>(false, I18n.get("messages", "receipt_detail.validate.percent.out_of_range"));
        return new Pair<>(true, I18n.get("messages", "receipt_detail.validate.percent"));
    }
}
