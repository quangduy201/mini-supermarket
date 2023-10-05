package mini_supermarket.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mini_supermarket.utils.VNString;

import java.io.Serializable;

@Entity
@Table(name = "export_detail")
public class ExportDetail extends RelationshipDTO implements Serializable {
    public static final String EXPORT_NOTE_ID = ID + "." + ExportDetailId.EXPORT_NOTE;
    public static final String SHIPMENT_ID = ID + "." + ExportDetailId.SHIPMENT;
    public static final String QUANTITY = "quantity";
    public static final String TOTAL = "total";

    @EmbeddedId
    private ExportDetailId id;
    @Column(name = QUANTITY)
    private Double quantity;
    @Column(name = TOTAL)
    private Double total;

    public ExportDetail() {
    }

    public ExportDetail(ExportDetailId id, Double quantity, Double total) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
    }

    public ExportDetailId getId() {
        return id;
    }

    public void setId(ExportDetailId id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return id.getExport() + VNString.NULL +
            id.getShipment() + VNString.NULL +
            quantity + VNString.NULL +
            total;
    }
}
