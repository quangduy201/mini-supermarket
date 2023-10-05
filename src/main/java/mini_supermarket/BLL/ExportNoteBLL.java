package mini_supermarket.BLL;

import mini_supermarket.DAL.ExportNoteDAL;
import mini_supermarket.DTO.ExportNote;

public class ExportNoteBLL extends EntityBLL<ExportNote> {
    public ExportNoteBLL() {
        super(new ExportNoteDAL());
    }
}
