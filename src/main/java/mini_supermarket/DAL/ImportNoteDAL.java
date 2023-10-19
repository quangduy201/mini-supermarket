package mini_supermarket.DAL;

import mini_supermarket.DTO.ImportNote;
import mini_supermarket.utils.__;

import java.util.List;

public class ImportNoteDAL extends EntityDAL<ImportNote> {
    public ImportNoteDAL() {
        super(ImportNote.class, List.of(
            __.IMPORT_NOTE.COLUMN.ID,
            __.STAFF.COLUMN.ID,
            __.STAFF.COLUMN.NAME,
            __.STAFF.COLUMN.GENDER,
            __.STAFF.COLUMN.BIRTHDATE,
            __.STAFF.COLUMN.PHONE,
            __.STAFF.COLUMN.ADDRESS,
            __.STAFF.COLUMN.EMAIL,
            __.STAFF.COLUMN.ENTRY_DATE,
            __.IMPORT_NOTE.COLUMN.RECEIVED_DATE,
            __.IMPORT_NOTE.COLUMN.TOTAL,
            __.SUPPLIER.COLUMN.ID,
            __.SUPPLIER.COLUMN.NAME,
            __.SUPPLIER.COLUMN.PHONE,
            __.SUPPLIER.COLUMN.ADDRESS,
            __.SUPPLIER.COLUMN.EMAIL
        ));
    }
}
