package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.Date;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "shipment")
public class Shipment extends EntityDTO implements Serializable {
    public static final String SHIPMENT_ID = "shipment_id";
    public static final String PRODUCT = "product";
    public static final String UNIT_PRICE = "unit_price";
    public static final String QUANTITY = "quantity";
    public static final String REMAIN = "remain";
    public static final String MFG = "mfg";
    public static final String EXP = "exp";
    public static final String SKU = "sku";
    public static final String IMPORT_NOTE = "importNote";

    @ManyToOne
    @JoinColumn(name = Product.PRODUCT_ID)
    private Product product;
    @Column(name = UNIT_PRICE)
    private Double unitPrice;
    @Column(name = QUANTITY)
    private Double quantity;
    @Column(name = REMAIN)
    private Double remain;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = MFG)
    private Date mfg;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = EXP)
    private Date exp;
    @Column(name = SKU)
    private String sku;
    @ManyToOne
    @JoinColumn(name = ImportNote.IMPORT_NOTE_ID)
    private ImportNote importNote;
    @OneToMany(mappedBy = ExportDetail.SHIPMENT_ID, cascade = CascadeType.ALL)
    private Set<ExportDetail> exportDetails;

    public Shipment() {
    }

    public Shipment(Product product, Double unitPrice, Double quantity, Double remain, Date mfg, Date exp, String sku, ImportNote importNote) {
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.remain = remain;
        this.mfg = mfg;
        this.exp = exp;
        this.sku = sku;
        this.importNote = importNote;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getRemain() {
        return remain;
    }

    public void setRemain(Double remain) {
        this.remain = remain;
    }

    public Date getMfg() {
        return mfg;
    }

    public void setMfg(Date mfg) {
        this.mfg = mfg;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public ImportNote getImportNote() {
        return importNote;
    }

    public void setImportNote(ImportNote importNote) {
        this.importNote = importNote;
    }

    public Set<ExportDetail> getExportDetails() {
        return exportDetails;
    }

    public void setExportDetails(Set<ExportDetail> exportDetails) {
        this.exportDetails = exportDetails;
    }
}
