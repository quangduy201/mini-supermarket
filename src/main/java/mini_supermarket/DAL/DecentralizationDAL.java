package mini_supermarket.DAL;

import mini_supermarket.DTO.Decentralization;
import mini_supermarket.DTO.DecentralizationId;
import mini_supermarket.utils.__;

import java.util.List;

public class DecentralizationDAL extends RelationshipDAL<Decentralization, DecentralizationId> {
    public DecentralizationDAL() {
        super(Decentralization.class, List.of(
            __.ROLE.COLUMN.ID,
            __.ROLE.COLUMN.NAME,
            __.MODULE.COLUMN.ID,
            __.MODULE.COLUMN.NAME,
            __.FUNCTION.COLUMN.ID,
            __.FUNCTION.COLUMN.NAME
        ));
    }
}
