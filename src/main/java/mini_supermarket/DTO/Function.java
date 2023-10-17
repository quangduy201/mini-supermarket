package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.FUNCTION.FUNCTION)
public class Function extends EntityDTO implements Serializable {
    @Column(name = __.FUNCTION.NAME)
    private String name;
    @OneToMany(mappedBy = __.DECENTRALIZATION.FUNCTION, cascade = CascadeType.ALL)
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
