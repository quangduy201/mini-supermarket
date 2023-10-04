package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.VNString;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "staff")
public class Staff extends EntityDTO implements Serializable {
    public static final String STAFF_ID = "staff_id";
    public static final String NAME = "name";
    public static final String GENDER = "gender";
    public static final String BIRTHDATE = "birthdate";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String EMAIL = "email";
    public static final String ENTRY_DATE = "entry_date";

    @Column(name = NAME)
    private String name;
    @Column(name = GENDER)
    private Boolean gender;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = BIRTHDATE)
    private Date birthdate;
    @Column(name = PHONE)
    private String phone;
    @Column(name = ADDRESS)
    private String address;
    @Column(name = EMAIL)
    private String email;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = ENTRY_DATE)
    private Date entryDate;
    @OneToMany(mappedBy = Account.STAFF, cascade = CascadeType.ALL)
    private Set<Account> accounts;
    @OneToMany(mappedBy = Receipt.STAFF, cascade = CascadeType.ALL)
    private Set<Receipt> receipts;
    @OneToMany(mappedBy = ImportNote.STAFF, cascade = CascadeType.ALL)
    private Set<ImportNote> imports;
    @OneToMany(mappedBy = ExportNote.STAFF, cascade = CascadeType.ALL)
    private Set<ExportNote> exportNotes;

    public Staff() {
    }

    public Staff(String name, Boolean gender, Date birthdate, String phone, String address, String email, Date entryDate) {
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

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<Receipt> receipts) {
        this.receipts = receipts;
    }

    public Set<ImportNote> getImports() {
        return imports;
    }

    public void setImports(Set<ImportNote> imports) {
        this.imports = imports;
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
