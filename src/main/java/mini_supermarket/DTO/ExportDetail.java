package mini_supermarket.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.io.Serializable;

@Entity
@Table(name = __.EXPORT_DETAIL.EXPORT_DETAIL)
public class ExportDetail extends RelationshipDTO implements Serializable {
    @EmbeddedId
    private ExportDetailId id;
    @Column(name = __.EXPORT_DETAIL.QUANTITY)
    private Double quantity;
    @Column(name = __.EXPORT_DETAIL.TOTAL)
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
        return id.getExportNote() + VNString.NULL +
            id.getShipment() + VNString.NULL +
            quantity + VNString.NULL +
            total;
    }
}
