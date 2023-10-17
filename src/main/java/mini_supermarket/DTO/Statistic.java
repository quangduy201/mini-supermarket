package mini_supermarket.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.VNString;
import mini_supermarket.utils.__;
import org.hibernate.annotations.Type;

import java.io.Serializable;

@Entity
@Table(name = __.STATISTIC.STATISTIC)
public class Statistic extends EntityDTO implements Serializable {
    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = __.STATISTIC.DATE)
    private Date date;
    @Column(name = __.STATISTIC.AMOUNT)
    private Double amount;
    @Column(name = __.STATISTIC.EXPENSES)
    private Double expenses;

    public Statistic() {
    }

    public Statistic(Date date, Double amount, Double expenses) {
        this.date = date;
        this.amount = amount;
        this.expenses = expenses;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return getId() + VNString.NULL +
            date + VNString.NULL +
            amount + VNString.NULL +
            expenses;
    }
}
