package mini_supermarket.DAL;

import mini_supermarket.DTO.Shipment;

import java.util.List;

public class ShipmentDAL extends EntityDAL<Shipment> {
    public ShipmentDAL() {
        super(Shipment.class, List.of());
    }
}
