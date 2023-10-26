package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.STAFF.STAFF)
public class Staff extends SafeEntityDTO implements Serializable {
    @Column(name = __.STAFF.NAME)
    private String name;
    @Column(name = __.STAFF.GENDER)
    private Boolean gender;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = __.STAFF.BIRTHDATE)
    private Date birthdate;
    @Column(name = __.STAFF.PHONE)
    private String phone;
    @Column(name = __.STAFF.ADDRESS)
    private String address;
    @Column(name = __.STAFF.EMAIL)
    private String email;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = __.STAFF.ENTRY_DATE)
    private Date entryDate;
    @OneToOne(mappedBy = __.ACCOUNT.STAFF, cascade = CascadeType.ALL)
    private Account account;
    @OneToMany(mappedBy = __.RECEIPT.STAFF, cascade = CascadeType.ALL)
    private Set<Receipt> receipts;
    @OneToMany(mappedBy = __.IMPORT_NOTE.STAFF, cascade = CascadeType.ALL)
    private Set<ImportNote> importNotes;
    @OneToMany(mappedBy = __.EXPORT_NOTE.STAFF, cascade = CascadeType.ALL)
    private Set<ExportNote> exportNotes;

    public Staff() {
    }

    public Staff(Long id, String name, Boolean gender, Date birthdate, String phone, String address, String email, Date entryDate) {
        super(id);
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.entryDate = entryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<Receipt> receipts) {
        this.receipts = receipts;
    }

    public Set<ImportNote> getImportNotes() {
        return importNotes;
    }

    public void setImportNotes(Set<ImportNote> importNotes) {
        this.importNotes = importNotes;
    }

    public Set<ExportNote> getExports() {
        return exportNotes;
    }

    public void setExports(Set<ExportNote> exportNotes) {
        this.exportNotes = exportNotes;
    }

    @Override
    public String toString() {
        return getId() + VNString.NULL +
            name + VNString.NULL +
            gender + VNString.NULL +
            birthdate + VNString.NULL +
            phone + VNString.NULL +
            address + VNString.NULL +
            email + VNString.NULL +
            entryDate;
    }
}
