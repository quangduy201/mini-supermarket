package mini_supermarket.BLL;

import mini_supermarket.DAL.BaseDAL;
import mini_supermarket.DTO.SafeEntityDTO;

import java.util.Arrays;
import java.util.List;

public abstract class SafeEntityBLL<DTO extends SafeEntityDTO> extends EntityBLL<DTO> {
    public SafeEntityBLL(BaseDAL<DTO, Long> DAL) {
        super(DAL);
    }

    @Override
    public List<DTO> findIn(int pageNumber, int pageSize, Object... attributes) {
        List<DTO> dtoList = super.findIn(pageNumber, pageSize, attributes);
        dtoList.removeIf(SafeEntityDTO::isDeleted);
        return dtoList;
    }

    @Override
    public List<DTO> findByCriteriaIn(int pageNumber, int pageSize, Object... criteria) {
        List<DTO> dtoList = super.findByCriteriaIn(pageNumber, pageSize, criteria);
        dtoList.removeIf(SafeEntityDTO::isDeleted);
        return dtoList;
    }

    @Override
    public List<DTO> findByPage(int pageNumber, int pageSize) {
        return findIn(pageNumber, pageSize);
    }

    @Override
    public List<DTO> findAll() {
        return findByPage(0, 0);
    }
}
