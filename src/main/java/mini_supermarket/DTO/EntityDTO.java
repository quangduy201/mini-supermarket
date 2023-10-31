package mini_supermarket.DTO;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public class EntityDTO extends BaseDTO implements Serializable {
    public static final String ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    public EntityDTO() {
    }

    public EntityDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
