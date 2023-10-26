package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.CATEGORY.CATEGORY)
public class Category extends SafeEntityDTO implements Serializable {
    @Column(name = __.CATEGORY.NAME)
    private String name;
    @Column(name = __.CATEGORY.QUANTITY)
    private Integer quantity;
    @OneToMany(mappedBy = __.PRODUCT.CATEGORY, cascade = CascadeType.ALL)
    private Set<Product> products;

    public Category() {
    }

    public Category(Long id, String name, Integer quantity) {
        super(id);
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
            quantity;
    }
}
