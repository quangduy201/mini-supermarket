package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.VNString;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "discount")
public class Discount extends EntityDTO implements Serializable {
    public static final String DISCOUNT_ID = "discount_id";
    public static final String PERCENT = "percent";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String STATUS = "status";

    @Column(name = PERCENT)
    private Double percent;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = START_DATE)
    private Date startDate;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = END_DATE)
    private Date endDate;
    @Column(name = STATUS)
    private Boolean status;
    @OneToMany(mappedBy = DiscountDetail.DISCOUNT_ID, cascade = CascadeType.ALL)
    private Set<DiscountDetail> discountDetails;

    public Discount() {
    }

    public Discount(Double percent, Date startDate, Date endDate, Boolean status) {
        this.percent = percent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<DiscountDetail> getDiscountDetails() {
        return discountDetails;
    }

    public void setDiscountDetails(Set<DiscountDetail> discountDetails) {
        this.discountDetails = discountDetails;
    }

    @Override
    public String toString() {
        return getId() + VNString.NULL +
            percent + VNString.NULL +
            startDate + VNString.NULL +
            endDate + VNString.NULL +
            status;
    }
}
