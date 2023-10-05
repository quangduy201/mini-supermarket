package mini_supermarket.BLL;

import mini_supermarket.DAL.ExportDetailDAL;
import mini_supermarket.DTO.ExportDetail;
import mini_supermarket.DTO.ExportDetailId;

public class ExportDetailBLL extends RelationshipBLL<ExportDetail, ExportDetailId> {
    public ExportDetailBLL() {
        super(new ExportDetailDAL());
    }
}
