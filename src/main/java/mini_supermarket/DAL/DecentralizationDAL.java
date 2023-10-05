package mini_supermarket.DAL;

import mini_supermarket.DTO.Decentralization;
import mini_supermarket.DTO.DecentralizationId;

import java.util.List;

public class DecentralizationDAL extends RelationshipDAL<Decentralization, DecentralizationId> {
    public DecentralizationDAL() {
        super(Decentralization.class, List.of());
    }
}
