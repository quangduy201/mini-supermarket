package mini_supermarket.DAL;

import mini_supermarket.DTO.Statistic;

import java.util.List;

public class StatisticDAL extends EntityDAL<Statistic> {
    public StatisticDAL() {
        super(Statistic.class, List.of());
    }
}
