package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.VNString;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product extends EntityDTO implements Serializable {
    public static final String PRODUCT_ID = "product_id";
    public static final String NAME = "name";
    public static final String BRAND = "brand";
    public static final String CATEGORY = "category";
    public static final String UNIT = "unit";
    public static final String COST = "cost";
    public static final String QUANTITY = "quantity";
    public static final String IMAGE = "image";
    public static final String BARCODE = "barcode";

    @Column(name = NAME)
    private String name;
    @ManyToOne
    @JoinColumn(name = Brand.BRAND_ID)
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = Category.CATEGORY_ID)
    private Category category;
    @Column(name = UNIT)
    private String unit;
    @Column(name = COST)
    private Double cost;
    @Column(name = QUANTITY)
    private Double quantity;
    @Column(name = IMAGE)
    private String image;
    @Column(name = BARCODE)
    private String barcode;
    @OneToMany(mappedBy = DiscountDetail.PRODUCT_ID, cascade = CascadeType.ALL)
    private Set<DiscountDetail> discountDetails;
    @OneToMany(mappedBy = ReceiptDetail.PRODUCT_ID, cascade = CascadeType.ALL)
    private Set<ReceiptDetail> receiptDetails;
    @OneToMany(mappedBy = Shipment.PRODUCT, cascade = CascadeType.ALL)
    private Set<Shipment> shipments;
    @OneToMany(mappedBy = PromotionItem.PRODUCT_ID, cascade = CascadeType.ALL)
    private Set<PromotionItem> promotionItems;
    @OneToMany(mappedBy = PromotionGift.PRODUCT_ID, cascade = CascadeType.ALL)
    private Set<PromotionGift> promotionGifts;

    public Product() {
    }

    public Product(String name, Brand brand, Category category, String unit, Double cost, Double quantity, String image, String barcode) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.unit = unit;
        this.cost = cost;
        this.quantity = quantity;
        this.image = image;
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return getId() + VNString.NULL +
            name + VNString.NULL +
            brand + VNString.NULL +
            category + VNString.NULL +
            unit + VNString.NULL +
            cost + VNString.NULL +
            quantity + VNString.NULL +
            image + VNString.NULL +
            barcode;
    }
}
