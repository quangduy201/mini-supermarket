package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.DateTime;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;
import org.hibernate.annotations.Type;

import java.io.Serializable;

@Entity
@Table(name = __.ACCOUNT.ACCOUNT)
public class Account extends EntityDTO implements Serializable {
    @Column(name = __.ACCOUNT.USERNAME)
    private String username;
    @Column(name = __.ACCOUNT.PASSWORD)
    private String password;
    @ManyToOne
    @JoinColumn(name = __.ROLE.COLUMN.ID)
    private Role role;
    @OneToOne
    @JoinColumn(name = __.STAFF.COLUMN.ID)
    private Staff staff;
    @Type(mini_supermarket.utils.DateTimeUserType.class)
    @Column(name = __.ACCOUNT.LAST_SIGNED_IN)
    private DateTime lastSignedIn;

    public Account() {
    }

    public Account(String username, String password, Role role, Staff staff, DateTime lastSignedIn) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.staff = staff;
        this.lastSignedIn = lastSignedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public DateTime getLastSignedIn() {
        return lastSignedIn;
    }

    public void setLastSignedIn(DateTime lastSignedIn) {
        this.lastSignedIn = lastSignedIn;
    }

    @Override
    public String toString() {
        return getId() + VNString.NULL +
            username + VNString.NULL +
            password + VNString.NULL +
            role + VNString.NULL +
            staff + VNString.NULL +
            lastSignedIn;
    }
}
