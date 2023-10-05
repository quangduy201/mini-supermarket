package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.VNString;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "supplier")
public class Supplier extends EntityDTO implements Serializable {
    public static final String SUPPLIER_ID = "supplier_id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String EMAIL = "email";

    @Column(name = NAME)
    private String name;
    @Column(name = PHONE)
    private String phone;
    @Column(name = ADDRESS)
    private String address;
    @Column(name = EMAIL)
    private String email;
    @OneToMany(mappedBy = Brand.SUPPLIER, cascade = CascadeType.ALL)
    private Set<Brand> brands;
    @OneToMany(mappedBy = ImportNote.SUPPLIER, cascade = CascadeType.ALL)
    private Set<ImportNote> importNotes;

    public Supplier() {
    }

    public Supplier(String name, String phone, String address, String email) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Brand> getBrands() {
        return brands;
    }

    public void setBrands(Set<Brand> brands) {
        this.brands = brands;
    }

    public Set<ImportNote> getImportNotes() {
        return importNotes;
    }

    public void setImportNotes(Set<ImportNote> importNotes) {
        this.importNotes = importNotes;
    }

    @Override
    public String toString() {
        return getId() + VNString.NULL +
            name + VNString.NULL +
            phone + VNString.NULL +
            address + VNString.NULL +
            email;
    }
}
