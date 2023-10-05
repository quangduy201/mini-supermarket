package mini_supermarket.BLL;

import mini_supermarket.DAL.DecentralizationDAL;
import mini_supermarket.DTO.Decentralization;
import mini_supermarket.DTO.DecentralizationId;

public class DecentralizationBLL extends RelationshipBLL<Decentralization, DecentralizationId> {
    public DecentralizationBLL() {
        super(new DecentralizationDAL());
    }
}
