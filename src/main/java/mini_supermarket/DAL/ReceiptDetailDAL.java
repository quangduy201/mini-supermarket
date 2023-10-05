package mini_supermarket.DAL;

import mini_supermarket.DTO.ReceiptDetail;
import mini_supermarket.DTO.ReceiptDetailId;

import java.util.List;

public class ReceiptDetailDAL extends RelationshipDAL<ReceiptDetail, ReceiptDetailId> {
    public ReceiptDetailDAL() {
        super(ReceiptDetail.class, List.of());
    }
}
