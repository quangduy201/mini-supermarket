package mini_supermarket.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@MappedSuperclass
public class SafeEntityDTO extends EntityDTO implements Serializable {
    public static final String DELETED = "deleted";

    @Column(name = DELETED)
    private Boolean deleted;

    public SafeEntityDTO() {
        deleted = Boolean.FALSE;
    }

    public SafeEntityDTO(Long id) {
        super(id);
        deleted = Boolean.FALSE;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
