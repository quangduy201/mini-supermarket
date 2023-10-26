package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.SHIPMENT.SHIPMENT)
public class Shipment extends EntityDTO implements Serializable {
    @ManyToOne
    @JoinColumn(name = __.PRODUCT.COLUMN.ID)
    private Product product;
    @Column(name = __.SHIPMENT.UNIT_PRICE)
    private Double unitPrice;
    @Column(name = __.SHIPMENT.QUANTITY)
    private Double quantity;
    @Column(name = __.SHIPMENT.REMAIN)
    private Double remain;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = __.SHIPMENT.MFG)
    private Date mfg;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = __.SHIPMENT.EXP)
    private Date exp;
    @Column(name = __.SHIPMENT.SKU)
    private String sku;
    @ManyToOne
    @JoinColumn(name = __.IMPORT_NOTE.COLUMN.ID)
    private ImportNote importNote;
    @OneToMany(mappedBy = __.EXPORT_DETAIL.SHIPMENT, cascade = CascadeType.ALL)
    private Set<ExportDetail> exportDetails;

    public Shipment() {
    }

    public Shipment(Long id, Product product, Double unitPrice, Double quantity, Double remain, Date mfg, Date exp, String sku, ImportNote importNote) {
        super(id);
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

    @Override
    public String toString() {
        return getId() + VNString.NULL +
            product + VNString.NULL +
            unitPrice + VNString.NULL +
            quantity + VNString.NULL +
            remain + VNString.NULL +
            mfg + VNString.NULL +
            exp + VNString.NULL +
            sku + VNString.NULL +
            importNote;
    }
}


