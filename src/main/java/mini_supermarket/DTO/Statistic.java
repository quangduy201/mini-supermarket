package mini_supermarket.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.VNString;
import org.hibernate.annotations.Type;

import java.io.Serializable;

@Entity
@Table(name = "statistic")
public class Statistic extends EntityDTO implements Serializable {
    public static final String STATISTIC_ID = "statistic_id";
    public static final String DATE = "date";
    public static final String AMOUNT = "amount";
    public static final String EXPENSES = "expenses";

    @Type(mini_supermarket.utils.DateUserType.class)
    @Column(name = DATE)
    private Date date;
    @Column(name = AMOUNT)
    private Double amount;
    @Column(name = EXPENSES)
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
