package mini_supermarket.DAL;

import mini_supermarket.DTO.ImportNote;

import java.util.List;

public class ImportNoteDAL extends EntityDAL<ImportNote> {
    public ImportNoteDAL() {
        super(ImportNote.class, List.of());
    }
}
