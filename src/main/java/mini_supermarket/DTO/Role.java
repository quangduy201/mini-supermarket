package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.ROLE.ROLE)
public class Role extends SafeEntityDTO implements Serializable {
    @Column(name = __.ROLE.NAME)
    private String name;
    @OneToMany(mappedBy = __.ACCOUNT.ROLE, cascade = CascadeType.ALL)
    private Set<Account> accounts;
    @OneToMany(mappedBy = __.DECENTRALIZATION.ROLE, cascade = CascadeType.ALL)
    private Set<Decentralization> decentralizations;

    public Role() {
    }

    public Role(Long id, String name) {
        super(id);
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
