package mini_supermarket.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.io.Serializable;

@Entity
@Table(name = __.DISCOUNT_DETAIL.DISCOUNT_DETAIL)
public class DiscountDetail extends RelationshipDTO implements Serializable {
    @EmbeddedId
    private DiscountDetailId id;
    @Column(name = __.DISCOUNT_DETAIL.STATUS)
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
