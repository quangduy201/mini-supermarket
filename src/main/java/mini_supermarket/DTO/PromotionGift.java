package mini_supermarket.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.io.Serializable;

@Entity
@Table(name = __.PROMOTION_GIFT.PROMOTION_GIFT)
public class PromotionGift extends RelationshipDTO implements Serializable {
    @EmbeddedId
    private PromotionDetailId id;
    @Column(name = __.PROMOTION_GIFT.QUANTITY)
    private Double quantity;

    public PromotionGift() {
    }

    public PromotionGift(PromotionDetailId id, Double quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public PromotionDetailId getId() {
        return id;
    }

    public void setId(PromotionDetailId id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return id.getPromotion() + VNString.NULL +
            id.getProduct() + VNString.NULL +
            quantity;
    }
}
