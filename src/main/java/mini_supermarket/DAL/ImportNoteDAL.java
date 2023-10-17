package mini_supermarket.DAL;

import mini_supermarket.DTO.ImportNote;
import mini_supermarket.utils.__;

import java.util.List;

public class ImportNoteDAL extends EntityDAL<ImportNote> {
    public ImportNoteDAL() {
        super(ImportNote.class, List.of(
            __.IMPORT_NOTE.ID,
            __.STAFF.ID,
            __.STAFF.NAME,
            __.STAFF.GENDER,
            __.STAFF.BIRTHDATE,
            __.STAFF.PHONE,
            __.STAFF.ADDRESS,
            __.STAFF.EMAIL,
            __.STAFF.ENTRY_DATE,
            __.IMPORT_NOTE.RECEIVED_DATE,
            __.IMPORT_NOTE.TOTAL,
            __.SUPPLIER.ID,
            __.SUPPLIER.NAME,
            __.SUPPLIER.ADDRESS,
            __.SUPPLIER.PHONE,
            __.SUPPLIER.EMAIL
        ));
    }
}
