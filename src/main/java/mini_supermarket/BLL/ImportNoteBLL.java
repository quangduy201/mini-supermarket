package mini_supermarket.BLL;

import mini_supermarket.DAL.ImportNoteDAL;
import mini_supermarket.DTO.ImportNote;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class ImportNoteBLL extends EntityBLL<ImportNote> {
    public ImportNoteBLL() {
        super(new ImportNoteDAL());
    }

    @Override
    public Pair<Boolean, String> exists(ImportNote importNote) {
        List<ImportNote> importNotes;
        importNotes = findBy(__.IMPORT_NOTE.IMPORT_NOTE, importNote.getId());
        if (!importNotes.isEmpty()) {
            String message = I18n.get("messages", "import_note.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "import_note.exists.not");
        return new Pair<>(false, message);
    }
}
