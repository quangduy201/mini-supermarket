package mini_supermarket.BLL;

import mini_supermarket.DAL.ImportNoteDAL;
import mini_supermarket.DTO.ImportNote;

public class ImportNoteBLL extends EntityBLL<ImportNote> {
    public ImportNoteBLL() {
        super(new ImportNoteDAL());
    }
}
