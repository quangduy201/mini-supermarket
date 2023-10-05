package mini_supermarket.BLL;

import mini_supermarket.DAL.ReceiptDetailDAL;
import mini_supermarket.DTO.ReceiptDetail;
import mini_supermarket.DTO.ReceiptDetailId;

public class ReceiptDetailBLL extends RelationshipBLL<ReceiptDetail, ReceiptDetailId> {
    public ReceiptDetailBLL() {
        super(new ReceiptDetailDAL());
    }
}
