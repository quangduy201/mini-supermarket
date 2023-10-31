package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.DateTime;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.IMPORT_NOTE.IMPORT_NOTE)
public class ImportNote extends EntityDTO implements Serializable {
    @ManyToOne
    @JoinColumn(name = __.STAFF.COLUMN.ID)
    private Staff staff;
    @Type(mini_supermarket.utils.DateTimeUserType.class)
    @Column(name = __.IMPORT_NOTE.RECEIVED_DATE)
    private DateTime receivedDate;
    @Column(name = __.IMPORT_NOTE.TOTAL)
    private Double total;
    @ManyToOne
    @JoinColumn(name = __.SUPPLIER.COLUMN.ID)
    private Supplier supplier;
    @OneToMany(mappedBy = __.SHIPMENT.IMPORT_NOTE, cascade = CascadeType.ALL)
    private Set<Shipment> shipments;

    public ImportNote() {
    }

    public ImportNote(Long id, Staff staff, DateTime receivedDate, Double total, Supplier supplier) {
        super(id);
        this.staff = staff;
        this.receivedDate = receivedDate;
        this.total = total;
        this.supplier = supplier;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public DateTime getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(DateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(Set<Shipment> shipments) {
        this.shipments = shipments;
    }

    @Override
    public String toString() {
        return getId() + VNString.NULL +
            staff + VNString.NULL +
            receivedDate + VNString.NULL +
            total + VNString.NULL +
            supplier;
    }
}
