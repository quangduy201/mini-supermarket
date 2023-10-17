package mini_supermarket.BLL;

import mini_supermarket.DAL.DecentralizationDAL;
import mini_supermarket.DTO.Decentralization;
import mini_supermarket.DTO.DecentralizationId;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class DecentralizationBLL extends RelationshipBLL<Decentralization, DecentralizationId> {
    public DecentralizationBLL() {
        super(new DecentralizationDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Decentralization decentralization) {
        List<Decentralization> decentralizations;
        decentralizations = findBy(
            __.DECENTRALIZATION.ROLE, decentralization.getId().getRole(),
            __.DECENTRALIZATION.MODULE, decentralization.getId().getModule(),
            __.DECENTRALIZATION.FUNCTION, decentralization.getId().getFunction());
        if (!decentralizations.isEmpty()) {
            String message = I18n.getString("decentralization.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.getString("decentralization.exists.not");
        return new Pair<>(false, message);
    }
}
