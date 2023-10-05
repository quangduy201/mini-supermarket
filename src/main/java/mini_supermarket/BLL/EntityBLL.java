package mini_supermarket.BLL;

import mini_supermarket.DAL.BaseDAL;
import mini_supermarket.DTO.EntityDTO;

public class EntityBLL<DTO extends EntityDTO> extends BaseBLL<DTO, Long> {
    public EntityBLL(BaseDAL<DTO, Long> DAL) {
        super(DAL);
    }
}
