package mini_supermarket.DTO;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class PromotionDetailId implements Serializable {
    public static final String PROMOTION = "promotion";
    public static final String PRODUCT = "product";

    @ManyToOne
    @JoinColumn(name = Promotion.PROMOTION_ID)
    private Promotion promotion;
    @ManyToOne
    @JoinColumn(name = Product.PRODUCT_ID)
    private Product product;

    public PromotionDetailId() {
    }

    public PromotionDetailId(Promotion promotion, Product product) {
        this.promotion = promotion;
        this.product = product;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof PromotionDetailId that)) return false;

        if (!getPromotion().equals(that.getPromotion())) return false;
        return getProduct().equals(that.getProduct());
    }

    @Override
    public int hashCode() {
        int result = getPromotion().hashCode();
        result = 31 * result + getProduct().hashCode();
        return result;
    }
}
