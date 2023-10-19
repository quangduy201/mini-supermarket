package mini_supermarket.BLL;

import mini_supermarket.DAL.ShipmentDAL;
import mini_supermarket.DTO.Shipment;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class ShipmentBLL extends EntityBLL<Shipment> {
    public ShipmentBLL() {
        super(new ShipmentDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Shipment shipment) {
        List<Shipment> shipments;
        shipments = findBy(__.SHIPMENT.ID, shipment.getId());
        if (!shipments.isEmpty()) {
            String message = I18n.get("messages", "shipment.exists");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "shipment.exists.not");
        return new Pair<>(false, message);
    }
}
