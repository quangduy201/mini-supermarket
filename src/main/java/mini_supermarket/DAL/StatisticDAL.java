package mini_supermarket.DAL;

import mini_supermarket.DTO.Statistic;
import mini_supermarket.utils.__;

import java.util.List;

public class StatisticDAL extends EntityDAL<Statistic> {
    public StatisticDAL() {
        super(Statistic.class, List.of(
            __.STATISTIC.ID,
            __.STATISTIC.DATE,
            __.STATISTIC.AMOUNT,
            __.STATISTIC.EXPENSES
        ));
    }
}
