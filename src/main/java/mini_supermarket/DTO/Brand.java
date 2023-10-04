package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.VNString;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "brand")
public class Brand extends EntityDTO implements Serializable {
    public static final String BRAND_ID = "brand_id";
    public static final String NAME = "name";
    public static final String SUPPLIER = "supplier";

    @Column(name = NAME)
    private String name;
    @ManyToOne
    @JoinColumn(name = Supplier.SUPPLIER_ID)
    private Supplier supplier;
    @OneToMany(mappedBy = Product.BRAND, cascade = CascadeType.ALL)
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
