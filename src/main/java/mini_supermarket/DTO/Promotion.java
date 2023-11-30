package mini_supermarket.DTO;

import jakarta.persistence.*;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = __.PROMOTION.PROMOTION)
public class Promotion extends EntityDTO implements Serializable {
    @Column(name = __.PROMOTION.NAME)
    private String name;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = __.PROMOTION.START_DATE)
    private Date startDate;
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = __.PROMOTION.END_DATE)
    private Date endDate;
    @Column(name = __.PROMOTION.STATUS)
    private Boolean status;
    @OneToMany(mappedBy = __.PROMOTION_ITEM.PROMOTION, cascade = CascadeType.ALL)
    private Set<PromotionItem> promotionItems;
    @OneToMany(mappedBy = __.PROMOTION_GIFT.PROMOTION, cascade = CascadeType.ALL)
    private Set<PromotionGift> promotionGifts;

    public Promotion() {
    }

    public Promotion(Long id, Date startDate, Date endDate, Boolean status) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
            name + VNString.NULL +
            startDate + VNString.NULL +
            endDate + VNString.NULL +
            status;
    }
}
