package mini_supermarket.DTO;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public class EntityDTO extends BaseDTO implements Serializable {
    public static final String ID = "id";
    public static final String DELETED = "deleted";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;
    @Column(name = DELETED)
    private Boolean deleted;

    public EntityDTO() {
        deleted = Boolean.FALSE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
