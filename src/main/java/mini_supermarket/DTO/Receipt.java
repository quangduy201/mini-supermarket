package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.DateTime;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.RECEIPT.RECEIPT)
public class Receipt extends EntityDTO implements Serializable {
    @ManyToOne
    @JoinColumn(name = __.STAFF.COLUMN.ID)
    private Staff staff;
    @ManyToOne
    @JoinColumn(name = __.CUSTOMER.COLUMN.ID)
    private Customer customer;
    @Type(mini_supermarket.utils.DateTimeUserType.class)
    @Column(name = __.RECEIPT.INVOICE_DATE)
    private DateTime invoiceDate;
    @Column(name = __.RECEIPT.TOTAL)
    private Double total;
    @Column(name = __.RECEIPT.RECEIVED)
    private Double received;
    @Column(name = __.RECEIPT.EXCESS)
    private Double excess;
    @OneToMany(mappedBy = __.RECEIPT_DETAIL.RECEIPT, cascade = CascadeType.ALL)
    private Set<ReceiptDetail> receiptDetails;

    public Receipt() {
    }

    public Receipt(Long id, Staff staff, Customer customer, DateTime invoiceDate, Double total, Double received, Double excess) {
        super(id);
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
