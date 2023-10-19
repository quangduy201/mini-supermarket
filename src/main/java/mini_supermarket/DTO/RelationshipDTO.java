package mini_supermarket.DTO;

import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@MappedSuperclass
public class RelationshipDTO extends BaseDTO implements Serializable {
    public RelationshipDTO() {
    }
}
