package mini_supermarket.DAL;

import mini_supermarket.DTO.SafeEntityDTO;

import java.util.List;

public abstract class SafeEntityDAL<DTO extends SafeEntityDTO> extends EntityDAL<DTO> {
    public SafeEntityDAL(Class<DTO> entityType, List<String> columnNames) {
        super(entityType, columnNames);
    }

    public boolean remove(DTO entity) {
        entity.setDeleted(true);
        return merge(entity);
    }
}
