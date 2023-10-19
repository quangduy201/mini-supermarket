package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.SUPPLIER.SUPPLIER)
public class Supplier extends EntityDTO implements Serializable {
    @Column(name = __.SUPPLIER.NAME)
    private String name;
    @Column(name = __.SUPPLIER.PHONE)
    private String phone;
    @Column(name = __.SUPPLIER.ADDRESS)
    private String address;
    @Column(name = __.SUPPLIER.EMAIL)
    private String email;
    @OneToMany(mappedBy = __.BRAND.SUPPLIER, cascade = CascadeType.ALL)
    private Set<Brand> brands;
    @OneToMany(mappedBy = __.IMPORT_NOTE.SUPPLIER, cascade = CascadeType.ALL)
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
