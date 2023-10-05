package mini_supermarket.BLL;

import mini_supermarket.DAL.ShipmentDAL;
import mini_supermarket.DTO.Shipment;

public class ShipmentBLL extends EntityBLL<Shipment> {
    public ShipmentBLL() {
        super(new ShipmentDAL());
    }
}
