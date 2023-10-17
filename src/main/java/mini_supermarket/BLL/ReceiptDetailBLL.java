package mini_supermarket.BLL;

import mini_supermarket.DAL.ReceiptDetailDAL;
import mini_supermarket.DTO.ReceiptDetail;
import mini_supermarket.DTO.ReceiptDetailId;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
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
            String message = I18n.getString("receipt_detail.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.getString("receipt_detail.exists.not");
        return new Pair<>(false, message);
    }
}
