package mini_supermarket.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mini_supermarket.utils.VNString;

import java.io.Serializable;

@Entity
@Table(name = "discount_detail")
public class DiscountDetail extends RelationshipDTO implements Serializable {
    public static final String DISCOUNT_ID = ID + "." + DiscountDetailId.DISCOUNT;
    public static final String PRODUCT_ID = ID + "." + DiscountDetailId.PRODUCT;
    public static final String STATUS = "status";

    @EmbeddedId
    private DiscountDetailId id;
    @Column(name = STATUS)
    private Boolean status;

    public DiscountDetail() {
    }

    public DiscountDetail(DiscountDetailId id, Boolean status) {
        this.id = id;
        this.status = status;
    }

    public DiscountDetailId getId() {
        return id;
    }

    public void setId(DiscountDetailId id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id.getDiscount() + VNString.NULL +
            id.getProduct() + VNString.NULL +
            status;
    }
}
