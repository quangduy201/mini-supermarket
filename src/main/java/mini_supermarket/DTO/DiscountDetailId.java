package mini_supermarket.DTO;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import mini_supermarket.utils.__;

import java.io.Serializable;

@Embeddable
public class DiscountDetailId implements Serializable {
    @ManyToOne
    @JoinColumn(name = __.DISCOUNT.COLUMN.ID)
    private Discount discount;
    @ManyToOne
    @JoinColumn(name = __.PRODUCT.COLUMN.ID)
    private Product product;

    public DiscountDetailId() {
    }

    public DiscountDetailId(Discount discount, Product product) {
        this.discount = discount;
        this.product = product;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
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
        if (!(object instanceof DiscountDetailId that)) return false;

        if (!getDiscount().equals(that.getDiscount())) return false;
        return getProduct().equals(that.getProduct());
    }

    @Override
    public int hashCode() {
        int result = getDiscount().hashCode();
        result = 31 * result + getProduct().hashCode();
        return result;
    }
}
