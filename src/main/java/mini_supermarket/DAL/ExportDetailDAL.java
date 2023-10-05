package mini_supermarket.DAL;

import mini_supermarket.DTO.ExportDetail;
import mini_supermarket.DTO.ExportDetailId;

import java.util.List;

public class ExportDetailDAL extends RelationshipDAL<ExportDetail, ExportDetailId> {
    public ExportDetailDAL() {
        super(ExportDetail.class, List.of());
    }
}
