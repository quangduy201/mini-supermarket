package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.BRAND.BRAND)
public class Brand extends EntityDTO implements Serializable {
    @Column(name = __.BRAND.NAME)
    private String name;
    @ManyToOne
    @JoinColumn(name = __.SUPPLIER.COLUMN.ID)
    private Supplier supplier;
    @OneToMany(mappedBy = __.PRODUCT.BRAND, cascade = CascadeType.ALL)
    private Set<Product> products;

    public Brand() {
    }

    public Brand(String name, Supplier supplier) {
        this.name = name;
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return getId() + VNString.NULL +
            name + VNString.NULL +
            supplier;
    }
}
