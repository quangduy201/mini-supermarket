package mini_supermarket.BLL;

import mini_supermarket.DAL.ExportNoteDAL;
import mini_supermarket.DTO.ExportNote;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class ExportNoteBLL extends EntityBLL<ExportNote> {
    public ExportNoteBLL() {
        super(new ExportNoteDAL());
    }

    @Override
    public Pair<Boolean, String> exists(ExportNote exportNote) {
        List<ExportNote> exportNotes;
        exportNotes = findBy(__.EXPORT_NOTE.ID, exportNote.getInvoiceDate());
        if (!exportNotes.isEmpty()) {
            String message = I18n.getString("export_note.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.getString("export_note.exists.not");
        return new Pair<>(false, message);
    }
}
