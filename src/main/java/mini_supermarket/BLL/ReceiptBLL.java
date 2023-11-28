package mini_supermarket.BLL;

import mini_supermarket.DAL.ReceiptDAL;
import mini_supermarket.DTO.Receipt;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class ReceiptBLL extends EntityBLL<Receipt> {
    public ReceiptBLL() {
        super(new ReceiptDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Receipt oldReceipt, Receipt newReceipt) {
        List<Receipt> receipts;
        receipts = findBy(__.RECEIPT.ID, newReceipt.getId());
        if (!receipts.isEmpty()) {
            String message = I18n.get("messages", "receipt.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "receipt.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.RECEIPT.INVOICE_DATE))
            return validateInvoiceDate((String) value);
        if (attribute.equals(__.RECEIPT.RECEIVED))
            return validateReceived((String) value);
        return new Pair<>(false, I18n.get("messages", "receipt.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateInvoiceDate(String invoiceDate) {
        if (invoiceDate.isBlank())
            return new Pair<>(false, I18n.get("messages", "receipt.validate.invoice_date.no_empty"));
        if (!VNString.checkFormatDateTime(invoiceDate))
            return new Pair<>(false, I18n.get("messages", "receipt.validate.invoice_date.format.not"));
        return new Pair<>(true, I18n.get("messages", "receipt.validate.invoice_date"));
    }

    private static Pair<Boolean, String> validateReceived(String received) {
        if (received.isBlank())
            return new Pair<>(false, I18n.get("messages", "receipt.validate.received.no_empty"));
        if (!VNString.checkUnsignedNumber(received))
            return new Pair<>(false, I18n.get("messages", "receipt.validate.received.unsigned_number.not"));
        return new Pair<>(true, I18n.get("messages", "receipt.validate.received"));
    }
}
