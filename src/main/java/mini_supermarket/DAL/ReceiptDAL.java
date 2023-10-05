package mini_supermarket.DAL;

import mini_supermarket.DTO.Receipt;

import java.util.List;

public class ReceiptDAL extends EntityDAL<Receipt> {
    public ReceiptDAL() {
        super(Receipt.class, List.of());
    }
}
