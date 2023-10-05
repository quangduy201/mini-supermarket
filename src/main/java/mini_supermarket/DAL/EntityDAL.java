package mini_supermarket.DAL;

import mini_supermarket.DTO.EntityDTO;

import java.util.List;

public abstract class EntityDAL<DTO extends EntityDTO> extends BaseDAL<DTO, Long> {
    public EntityDAL(Class<DTO> entityType, List<String> columnNames) {
        super(entityType, columnNames);
    }

    public void softRemove(DTO entity) {
        entity.setDeleted(true);
        merge(entity);
    }
}
