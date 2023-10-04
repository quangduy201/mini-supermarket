package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.VNString;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "function")
public class Function extends EntityDTO implements Serializable {
    public static final String FUNCTION_ID = "function_id";
    public static final String NAME = "name";

    @Column(name = NAME)
    private String name;
    @OneToMany(mappedBy = Decentralization.FUNCTION_ID, cascade = CascadeType.ALL)
    private Set<Decentralization> decentralizations;

    public Function() {
    }

    public Function(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Decentralization> getDecentralizations() {
        return decentralizations;
    }

    public void setDecentralizations(Set<Decentralization> decentralizations) {
        this.decentralizations = decentralizations;
    }

    @Override
    public String toString() {
        return getId() + VNString.NULL +
            name;
    }
}
