package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.DateTime;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.EXPORT_NOTE.EXPORT_NOTE)
public class ExportNote extends EntityDTO implements Serializable {
    @ManyToOne
    @JoinColumn(name = __.STAFF.COLUMN.ID)
    private Staff staff;
    @Type(mini_supermarket.utils.DateTimeUserType.class)
    @Column(name = __.EXPORT_NOTE.INVOICE_DATE)
    private DateTime invoiceDate;
    @Column(name = __.EXPORT_NOTE.TOTAL)
    private Double total;
    @Column(name = __.EXPORT_NOTE.REASON)
    private String reason;
    @OneToMany(mappedBy = __.EXPORT_DETAIL.EXPORT_NOTE, cascade = CascadeType.ALL)
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
