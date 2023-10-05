package mini_supermarket.DAL;

import mini_supermarket.DTO.ExportNote;

import java.util.List;

public class ExportNoteDAL extends EntityDAL<ExportNote> {
    public ExportNoteDAL() {
        super(ExportNote.class, List.of());
    }
}
