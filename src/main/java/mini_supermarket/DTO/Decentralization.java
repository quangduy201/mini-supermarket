package mini_supermarket.DTO;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mini_supermarket.utils.VNString;

import java.io.Serializable;

@Entity
@Table(name = "decentralization")
public class Decentralization extends RelationshipDTO implements Serializable {
    public static final String ROLE_ID = ID + "." + DecentralizationId.ROLE;
    public static final String MODULE_ID = ID + "." + DecentralizationId.MODULE;
    public static final String FUNCTION_ID = ID + "." + DecentralizationId.FUNCTION;

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
