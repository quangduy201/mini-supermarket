package mini_supermarket.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mini_supermarket.utils.VNString;

import java.io.Serializable;

@Entity
@Table(name = "receipt_detail")
public class ReceiptDetail extends RelationshipDTO implements Serializable {
    public static final String RECEIPT_ID = ID + "." + ReceiptDetailId.RECEIPT;
    public static final String PRODUCT_ID = ID + "." + ReceiptDetailId.PRODUCT;
    public static final String QUANTITY = "quantity";
    public static final String TOTAL = "total";
    public static final String PERCENT = "percent";

    @EmbeddedId
    private ReceiptDetailId id;
    @Column(name = QUANTITY)
    private Double quantity;
    @Column(name = TOTAL)
    private Double total;
    @Column(name = PERCENT)
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
