package mini_supermarket.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.io.Serializable;

@Entity
@Table(name = __.RECEIPT_DETAIL.RECEIPT_DETAIL)
public class ReceiptDetail extends RelationshipDTO implements Serializable {
    @EmbeddedId
    private ReceiptDetailId id;
    @Column(name = __.RECEIPT_DETAIL.QUANTITY)
    private Double quantity;
    @Column(name = __.RECEIPT_DETAIL.TOTAL)
    private Double total;
    @Column(name = __.RECEIPT_DETAIL.PERCENT)
    private Double percent;

    public ReceiptDetail() {
    }

    public ReceiptDetail(ReceiptDetailId id, Double quantity, Double total, Double percent) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
        this.percent = percent;
    }

    public ReceiptDetailId getId() {
        return id;
    }

    public void setId(ReceiptDetailId id) {
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

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return id.getReceipt() + VNString.NULL +
            id.getProduct() + VNString.NULL +
            quantity + VNString.NULL +
            total + VNString.NULL +
            percent;
    }
}
