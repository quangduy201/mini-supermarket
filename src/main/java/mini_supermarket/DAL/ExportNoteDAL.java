package mini_supermarket.DAL;

import mini_supermarket.DTO.ExportNote;
import mini_supermarket.utils.__;

import java.util.List;

public class ExportNoteDAL extends EntityDAL<ExportNote> {
    public ExportNoteDAL() {
        super(ExportNote.class, List.of(
            __.EXPORT_NOTE.COLUMN.ID,
            __.STAFF.COLUMN.ID,
            __.STAFF.COLUMN.NAME,
            __.STAFF.COLUMN.GENDER,
            __.STAFF.COLUMN.BIRTHDATE,
            __.STAFF.COLUMN.PHONE,
            __.STAFF.COLUMN.ADDRESS,
            __.STAFF.COLUMN.EMAIL,
            __.STAFF.COLUMN.ENTRY_DATE,
            __.EXPORT_NOTE.COLUMN.INVOICE_DATE,
            __.EXPORT_NOTE.COLUMN.TOTAL,
            __.EXPORT_NOTE.COLUMN.REASON
        ));
    }
}
