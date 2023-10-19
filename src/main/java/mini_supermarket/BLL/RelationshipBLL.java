package mini_supermarket.BLL;

import mini_supermarket.DAL.BaseDAL;
import mini_supermarket.DTO.RelationshipDTO;

import java.io.Serializable;

public abstract class RelationshipBLL<DTO extends RelationshipDTO, ID extends Serializable> extends BaseBLL<DTO, ID> {
    public RelationshipBLL(BaseDAL<DTO, ID> DAL) {
        super(DAL);
    }
}
