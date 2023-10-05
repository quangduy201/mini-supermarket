package mini_supermarket.DAL;

import mini_supermarket.DTO.RelationshipDTO;

import java.io.Serializable;
import java.util.List;

public abstract class RelationshipDAL<DTO extends RelationshipDTO, ID extends Serializable> extends BaseDAL<DTO, ID> {
    public RelationshipDAL(Class<DTO> relationshipType, List<String> columnNames) {
        super(relationshipType, columnNames);
    }
}
