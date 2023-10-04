package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.VNString;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "promotion")
public class Promotion extends EntityDTO implements Serializable {
    public static final String PROMOTION_ID = "promotion_id";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String STATUS = "status";

    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = START_DATE)
    private Date startDate;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = END_DATE)
    private Date endDate;
    @Column(name = STATUS)
    private Boolean status;
    @OneToMany(mappedBy = PromotionItem.PROMOTION_ID, cascade = CascadeType.ALL)
    private Set<PromotionItem> promotionItems;
    @OneToMany(mappedBy = PromotionGift.PROMOTION_ID, cascade = CascadeType.ALL)
    private Set<PromotionGift> promotionGifts;

    public Promotion() {
    }

    public Promotion(Date startDate, Date endDate, Boolean status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public Set<PromotionItem> getPromotionItems() {
        return promotionItems;
    }

    public void setPromotionItems(Set<PromotionItem> promotionItems) {
        this.promotionItems = promotionItems;
    }

    public Set<PromotionGift> getPromotionGifts() {
        return promotionGifts;
    }

    public void setPromotionGifts(Set<PromotionGift> promotionGifts) {
        this.promotionGifts = promotionGifts;
    }

    @Override
    public String toString() {
        return getId() + VNString.NULL +
            startDate + VNString.NULL +
            endDate + VNString.NULL +
            status;
    }
}
