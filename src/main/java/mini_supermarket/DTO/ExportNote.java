package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.DateTime;
import mini_supermarket.utils.VNString;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "export_note")
public class ExportNote extends EntityDTO implements Serializable {
    public static final String EXPORT_NOTE_ID = "export_note_id";
    public static final String STAFF = "staff";
    public static final String INVOICE_DATE = "invoice_date";
    public static final String TOTAL = "total";
    public static final String REASON = "reason";

    @ManyToOne
    @JoinColumn(name = Staff.STAFF_ID)
    private Staff staff;
    @Type(mini_supermarket.utils.DateTimeUserType.class)
    @Column(name = INVOICE_DATE)
    private DateTime invoiceDate;
    @Column(name = TOTAL)
    private Double total;
    @Column(name = REASON)
    private String reason;
    @OneToMany(mappedBy = ExportDetail.EXPORT_NOTE_ID, cascade = CascadeType.ALL)
    private Set<ExportDetail> exportDetails;

    public ExportNote() {
    }

    public ExportNote(Staff staff, DateTime invoiceDate, Double total, String reason) {
        this.staff = staff;
        this.invoiceDate = invoiceDate;
        this.total = total;
        this.reason = reason;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public DateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(DateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
            staff + VNString.NULL +
            invoiceDate + VNString.NULL +
            total + VNString.NULL +
            reason;
    }
}
