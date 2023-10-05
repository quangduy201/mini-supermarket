package mini_supermarket.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mini_supermarket.utils.VNString;

import java.io.Serializable;

@Entity
@Table(name = "promotion_gift")
public class PromotionGift extends RelationshipDTO implements Serializable {
    public static final String PROMOTION_ID = ID + "." + PromotionDetailId.PROMOTION;
    public static final String PRODUCT_ID = ID + "." + PromotionDetailId.PRODUCT;
    public static final String QUANTITY = "quantity";

    @EmbeddedId
    private PromotionDetailId id;
    @Column(name = QUANTITY)
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
