package mini_supermarket.BLL;

import mini_supermarket.DAL.ImportNoteDAL;
import mini_supermarket.DTO.ImportNote;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.util.List;

public class ImportNoteBLL extends EntityBLL<ImportNote> {
    public ImportNoteBLL() {
        super(new ImportNoteDAL());
    }

    @Override
    public Pair<Boolean, String> exists(ImportNote oldImportNote, ImportNote newImportNote) {
        List<ImportNote> importNotes;
        importNotes = findBy(__.IMPORT_NOTE.IMPORT_NOTE, newImportNote.getId());
        if (!importNotes.isEmpty()) {
            String message = I18n.get("messages", "import_note.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "import_note.exists.not");
        return new Pair<>(false, message);
    }

    public static Pair<Boolean, String> validate(String attribute, Object value) {
        if (attribute.equals(__.IMPORT_NOTE.RECEIVED_DATE))
            return validateReceivedDate((String) value);
        return new Pair<>(false, I18n.get("messages", "import_note.attribute.not_found", attribute));
    }

    private static Pair<Boolean, String> validateReceivedDate(String receivedDate) {
        if (receivedDate.isBlank())
            return new Pair<>(false, I18n.get("messages", "import_note.validate.receivedDate.no_empty"));
        if (!VNString.checkFormatDateTime(receivedDate))
            return new Pair<>(false, I18n.get("messages", "import_note.validate.receivedDate.format.not"));
        return new Pair<>(true, I18n.get("messages", "import_note.validate.receivedDate"));
    }
}
