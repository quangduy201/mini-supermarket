package mini_supermarket.DTO;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.io.Serializable;

@Entity
@Table(name = __.DECENTRALIZATION.DECENTRALIZATION)
public class Decentralization extends RelationshipDTO implements Serializable {
    @EmbeddedId
    private DecentralizationId id;

    public Decentralization() {
    }

    public Decentralization(DecentralizationId id) {
        this.id = id;
    }

    public DecentralizationId getId() {
        return id;
    }

    public void setId(DecentralizationId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.getRole() + VNString.NULL +
            id.getModule() + VNString.NULL +
            id.getFunction();
    }
}
