package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.VNString;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role extends EntityDTO implements Serializable {
    public static final String ROLE_ID = "role_id";
    public static final String NAME = "name";

    @Column(name = NAME)
    private String name;
    @OneToMany(mappedBy = Account.ROLE, cascade = CascadeType.ALL)
    private Set<Account> accounts;
    @OneToMany(mappedBy = Decentralization.ROLE_ID, cascade = CascadeType.ALL)
    private Set<Decentralization> decentralizations;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
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
