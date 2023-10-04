package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.VNString;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category extends EntityDTO implements Serializable {
    public static final String CATEGORY_ID = "category_id";
    public static final String NAME = "name";
    public static final String QUANTITY = "quantity";

    @Column(name = NAME)
    private String name;
    @Column(name = QUANTITY)
    private Integer quantity;
    @OneToMany(mappedBy = Product.CATEGORY, cascade = CascadeType.ALL)
    private Set<Product> products;

    public Category() {
    }

    public Category(String name, Integer quantity) {
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
