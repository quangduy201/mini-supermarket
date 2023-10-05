package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.DateTime;
import mini_supermarket.utils.VNString;
import org.hibernate.annotations.Type;

import java.io.Serializable;

@Entity
@Table(name = "account")
public class Account extends EntityDTO implements Serializable {
    public static final String ACCOUNT_ID = "account_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String STAFF = "staff";
    public static final String LAST_SIGNED_IN = "last_signed_in";

    @Column(name = USERNAME)
    private String username;
    @Column(name = PASSWORD)
    private String password;
    @ManyToOne
    @JoinColumn(name = Role.ROLE_ID)
    private Role role;
    @ManyToOne
    @JoinColumn(name = Staff.STAFF_ID)
    private Staff staff;
    @Type(mini_supermarket.utils.DateTimeUserType.class)
    @Column(name = LAST_SIGNED_IN)
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
