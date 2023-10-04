package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.VNString;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer extends EntityDTO implements Serializable {
    public static final String CUSTOMER_ID = "customer_id";
    public static final String NAME = "name";
    public static final String GENDER = "gender";
    public static final String BIRTHDATE = "birthdate";
    public static final String PHONE = "phone";
    public static final String MEMBERSHIP = "membership";
    public static final String SIGNED_UP_DATE = "signed_up_date";
    public static final String POINT = "point";

    @Column(name = NAME)
    private String name;
    @Column(name = GENDER)
    private Boolean gender;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = BIRTHDATE)
    private Date birthdate;
    @Column(name = PHONE)
    private String phone;
    @Column(name = MEMBERSHIP)
    private Boolean membership;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = SIGNED_UP_DATE)
    private Date signedUpDate;
    @Column(name = POINT)
    private Integer point;
    @OneToMany(mappedBy = Receipt.CUSTOMER, cascade = CascadeType.ALL)
    private Set<Receipt> receipts;

    public Customer() {
    }

    public Customer(String name, Boolean gender, Date birthdate, String phone, Boolean membership, Date signedUpDate, Integer point) {
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.phone = phone;
        this.membership = membership;
        this.signedUpDate = signedUpDate;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getMembership() {
        return membership;
    }

    public void setMembership(Boolean membership) {
        this.membership = membership;
    }

    public Date getSignedUpDate() {
        return signedUpDate;
    }

    public void setSignedUpDate(Date signedUpDate) {
        this.signedUpDate = signedUpDate;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Set<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<Receipt> receipts) {
        this.receipts = receipts;
    }

    @Override
    public String toString() {
        return getId() + VNString.NULL +
            name + VNString.NULL +
            gender + VNString.NULL +
            birthdate + VNString.NULL +
            phone + VNString.NULL +
            membership + VNString.NULL +
            signedUpDate + VNString.NULL +
            point;
    }
}
