package mini_supermarket.BLL;

import mini_supermarket.DAL.ReceiptDAL;
import mini_supermarket.DTO.Receipt;

public class ReceiptBLL extends EntityBLL<Receipt> {
    public ReceiptBLL() {
        super(new ReceiptDAL());
    }
}
