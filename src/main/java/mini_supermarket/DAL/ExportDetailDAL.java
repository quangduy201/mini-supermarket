package mini_supermarket.DAL;

import mini_supermarket.DTO.ExportDetail;
import mini_supermarket.DTO.ExportDetailId;
import mini_supermarket.utils.__;

import java.util.List;

public class ExportDetailDAL extends RelationshipDAL<ExportDetail, ExportDetailId> {
    public ExportDetailDAL() {
        super(ExportDetail.class, List.of(
            __.EXPORT_NOTE.ID,
            __.STAFF.ID,
            __.STAFF.NAME,
            __.STAFF.GENDER,
            __.STAFF.BIRTHDATE,
            __.STAFF.PHONE,
            __.STAFF.ADDRESS,
            __.STAFF.EMAIL,
            __.STAFF.ENTRY_DATE,
            __.EXPORT_NOTE.INVOICE_DATE,
            __.EXPORT_NOTE.TOTAL,
            __.EXPORT_NOTE.REASON
            //THIEU SHIPMENT
        ));
    }
}
