package mini_supermarket.BLL;

import mini_supermarket.DAL.ReceiptDAL;
import mini_supermarket.DTO.Receipt;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class ReceiptBLL extends EntityBLL<Receipt> {
    public ReceiptBLL() {
        super(new ReceiptDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Receipt receipt) {
        List<Receipt> receipts;
        receipts = findBy(__.RECEIPT.ID, receipt.getId());
        if (!receipts.isEmpty()) {
            String message = I18n.get("messages", "receipt.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "receipt.exists.not");
        return new Pair<>(false, message);
    }
}
