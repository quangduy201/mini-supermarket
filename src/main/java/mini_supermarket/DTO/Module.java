package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.MODULE.MODULE)
public class Module extends EntityDTO implements Serializable {
    @Column(name = __.MODULE.NAME)
    private String name;
    @OneToMany(mappedBy = __.DECENTRALIZATION.MODULE, cascade = CascadeType.ALL)
    private Set<Decentralization> decentralizations;

    public Module() {
    }

    public Module(Long id, String name) {
        super(id);
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
