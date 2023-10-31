package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.DISCOUNT.DISCOUNT)
public class Discount extends EntityDTO implements Serializable {
    @Column(name = __.DISCOUNT.PERCENT)
    private Double percent;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = __.DISCOUNT.START_DATE)
    private Date startDate;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = __.DISCOUNT.END_DATE)
    private Date endDate;
    @Column(name = __.DISCOUNT.STATUS)
    private Boolean status;
    @OneToMany(mappedBy = __.DISCOUNT_DETAIL.DISCOUNT, cascade = CascadeType.ALL)
    private Set<DiscountDetail> discountDetails;

    public Discount() {
    }

    public Discount(Long id, Double percent, Date startDate, Date endDate, Boolean status) {
        super(id);
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
