package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.DateTime;
import mini_supermarket.utils.VNString;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "import_note")
public class ImportNote extends EntityDTO implements Serializable {
    public static final String IMPORT_NOTE_ID = "import_note_id";
    public static final String STAFF = "staff";
    public static final String RECEIVED_DATE = "received_date";
    public static final String TOTAL = "total";
    public static final String SUPPLIER = "supplier";

    @ManyToOne
    @JoinColumn(name = Staff.STAFF_ID)
    private Staff staff;
    @Type(mini_supermarket.utils.DateTimeUserType.class)
    @Column(name = RECEIVED_DATE)
    private DateTime receivedDate;
    @Column(name = TOTAL)
    private Double total;
    @ManyToOne
    @JoinColumn(name = Supplier.SUPPLIER_ID)
    private Supplier supplier;
    @OneToMany(mappedBy = Shipment.IMPORT_NOTE, cascade = CascadeType.ALL)
    private Set<Shipment> shipments;

    public ImportNote() {
    }

    public ImportNote(Staff staff, DateTime receivedDate, Double total, Supplier supplier) {
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
