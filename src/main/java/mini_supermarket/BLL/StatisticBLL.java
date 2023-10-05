package mini_supermarket.BLL;

import mini_supermarket.DAL.StatisticDAL;
import mini_supermarket.DTO.Statistic;

public class StatisticBLL extends EntityBLL<Statistic> {
    public StatisticBLL() {
        super(new StatisticDAL());
    }
}
