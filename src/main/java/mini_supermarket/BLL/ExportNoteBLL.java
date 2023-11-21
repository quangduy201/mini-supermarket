package mini_supermarket.BLL;

import mini_supermarket.DAL.ExportNoteDAL;
import mini_supermarket.DTO.ExportNote;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class ExportNoteBLL extends EntityBLL<ExportNote> {
    public ExportNoteBLL() {
        super(new ExportNoteDAL());
    }

    @Override
    public Pair<Boolean, String> exists(ExportNote oldExportNote, ExportNote newExportNote) {
        List<ExportNote> exportNotes;
        exportNotes = findBy(__.EXPORT_NOTE.ID, newExportNote.getInvoiceDate());
        if (!exportNotes.isEmpty()) {
            String message = I18n.get("messages", "export_note.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "export_note.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.EXPORT_NOTE.INVOICE_DATE))
            return validateInvoiceDate((String) value);
        return new Pair<>(false, I18n.get("messages", "export_note.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateInvoiceDate(String invoiceDate) {
        if (invoiceDate.isBlank())
            return new Pair<>(false, I18n.get("messages", "export_note.validate.invoice_date.no_empty"));
        if (!VNString.checkFormatDateTime(invoiceDate))
            return new Pair<>(false, I18n.get("messages", "export_note.validate.invoice_date.format.not"));
        return new Pair<>(true, I18n.get("messages", "export_note.validate.invoice_date"));
    }
}
