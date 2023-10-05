package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.DateTime;
import mini_supermarket.utils.VNString;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "receipt")
public class Receipt extends EntityDTO implements Serializable {
    public static final String RECEIPT_ID = "receipt_id";
    public static final String STAFF = "staff";
    public static final String CUSTOMER = "customer";
    public static final String INVOICE_DATE = "invoice_date";
    public static final String TOTAL = "total";
    public static final String RECEIVED = "received";
    public static final String EXCESS = "excess";

    @ManyToOne
    @JoinColumn(name = Staff.STAFF_ID)
    private Staff staff;
    @ManyToOne
    @JoinColumn(name = Customer.CUSTOMER_ID)
    private Customer customer;
    @Type(mini_supermarket.utils.DateTimeUserType.class)
    @Column(name = INVOICE_DATE)
    private DateTime invoiceDate;
    @Column(name = TOTAL)
    private Double total;
    @Column(name = RECEIVED)
    private Double received;
    @Column(name = EXCESS)
    private Double excess;
    @OneToMany(mappedBy = ReceiptDetail.RECEIPT_ID, cascade = CascadeType.ALL)
    private Set<ReceiptDetail> receiptDetails;

    public Receipt() {
    }

    public Receipt(Staff staff, Customer customer, DateTime invoiceDate, Double total, Double received, Double excess) {
        this.staff = staff;
        this.customer = customer;
        this.invoiceDate = invoiceDate;
        this.total = total;
        this.received = received;
        this.excess = excess;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Double getReceived() {
        return received;
    }

    public void setReceived(Double received) {
        this.received = received;
    }

    public Double getExcess() {
        return excess;
    }

    public void setExcess(Double excess) {
        this.excess = excess;
    }

    public Set<ReceiptDetail> getReceiptDetails() {
        return receiptDetails;
    }

    public void setReceiptDetails(Set<ReceiptDetail> receiptDetails) {
        this.receiptDetails = receiptDetails;
    }

    @Override
    public String toString() {
        return getId() + VNString.NULL +
            staff + VNString.NULL +
            customer + VNString.NULL +
            invoiceDate + VNString.NULL +
            total + VNString.NULL +
            received + VNString.NULL +
            excess;
    }
}
