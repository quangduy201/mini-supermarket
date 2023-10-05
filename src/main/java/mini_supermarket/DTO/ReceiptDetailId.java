package mini_supermarket.DTO;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ReceiptDetailId implements Serializable {
    public static final String RECEIPT = "receipt";
    public static final String PRODUCT = "product";

    @ManyToOne
    @JoinColumn(name = Receipt.RECEIPT_ID)
    private Receipt receipt;
    @ManyToOne
    @JoinColumn(name = Product.PRODUCT_ID)
    private Product product;

    public ReceiptDetailId() {
    }

    public ReceiptDetailId(Receipt receipt, Product product) {
        this.receipt = receipt;
        this.product = product;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
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
        if (!(object instanceof ReceiptDetailId that)) return false;

        if (!getReceipt().equals(that.getReceipt())) return false;
        return getProduct().equals(that.getProduct());
    }

    @Override
    public int hashCode() {
        int result = getReceipt().hashCode();
        result = 31 * result + getProduct().hashCode();
        return result;
    }
}
