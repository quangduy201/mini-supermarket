package mini_supermarket.BLL;

import mini_supermarket.DAL.ExportDetailDAL;
import mini_supermarket.DTO.ExportDetail;
import mini_supermarket.DTO.ExportDetailId;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class ExportDetailBLL extends RelationshipBLL<ExportDetail, ExportDetailId> {
    public ExportDetailBLL() {
        super(new ExportDetailDAL());
    }

    @Override
    public Pair<Boolean, String> exists(ExportDetail exportDetail) {
        List<ExportDetail> exportDetails;
        exportDetails = findBy(
            __.EXPORT_DETAIL.EXPORT_NOTE, exportDetail.getId().getExportNote(),
            __.EXPORT_DETAIL.SHIPMENT, exportDetail.getId().getShipment());
        if (!exportDetails.isEmpty()) {
            String message = I18n.getString("export_detail.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.getString("export_detail.exists.not");
        return new Pair<>(false, message);
    }
}
