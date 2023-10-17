package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.PRODUCT.PRODUCT)
public class Product extends EntityDTO implements Serializable {
    @Column(name = __.PRODUCT.NAME)
    private String name;
    @ManyToOne
    @JoinColumn(name = __.BRAND.COLUMN.ID)
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = __.CATEGORY.COLUMN.ID)
    private Category category;
    @Column(name = __.PRODUCT.UNIT)
    private String unit;
    @Column(name = __.PRODUCT.COST)
    private Double cost;
    @Column(name = __.PRODUCT.QUANTITY)
    private Double quantity;
    @Column(name = __.PRODUCT.IMAGE)
    private String image;
    @Column(name = __.PRODUCT.BARCODE)
    private String barcode;
    @OneToMany(mappedBy = __.DISCOUNT_DETAIL.PRODUCT, cascade = CascadeType.ALL)
    private Set<DiscountDetail> discountDetails;
    @OneToMany(mappedBy = __.RECEIPT_DETAIL.PRODUCT, cascade = CascadeType.ALL)
    private Set<ReceiptDetail> receiptDetails;
    @OneToMany(mappedBy = __.SHIPMENT.PRODUCT, cascade = CascadeType.ALL)
    private Set<Shipment> shipments;
    @OneToMany(mappedBy = __.PROMOTION_ITEM.PRODUCT, cascade = CascadeType.ALL)
    private Set<PromotionItem> promotionItems;
    @OneToMany(mappedBy = __.PROMOTION_GIFT.PRODUCT, cascade = CascadeType.ALL)
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

    public Set<DiscountDetail> getDiscountDetails() {
        return discountDetails;
    }

    public void setDiscountDetails(Set<DiscountDetail> discountDetails) {
        this.discountDetails = discountDetails;
    }

    public Set<ReceiptDetail> getReceiptDetails() {
        return receiptDetails;
    }

    public void setReceiptDetails(Set<ReceiptDetail> receiptDetails) {
        this.receiptDetails = receiptDetails;
    }

    public Set<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(Set<Shipment> shipments) {
        this.shipments = shipments;
    }

    public Set<PromotionItem> getPromotionItems() {
        return promotionItems;
    }

    public void setPromotionItems(Set<PromotionItem> promotionItems) {
        this.promotionItems = promotionItems;
    }

    public Set<PromotionGift> getPromotionGifts() {
        return promotionGifts;
    }

    public void setPromotionGifts(Set<PromotionGift> promotionGifts) {
        this.promotionGifts = promotionGifts;
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
