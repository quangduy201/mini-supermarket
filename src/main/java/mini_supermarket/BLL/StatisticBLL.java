package mini_supermarket.BLL;

import mini_supermarket.DAL.StatisticDAL;
import mini_supermarket.DTO.Statistic;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import java.util.List;

public class StatisticBLL extends EntityBLL<Statistic> {
    public StatisticBLL() {
        super(new StatisticDAL());
    }

    @Override
    public Pair<Boolean, String> exists(Statistic oldStatistic, Statistic newStatistic) {
        List<Statistic> statistics;
        statistics = findBy(__.STATISTIC.ID, newStatistic.getId());
        if (!statistics.isEmpty()) {
            String message = I18n.get("messages", "statistic.exists");
            return new Pair<>(true, message);
        }

        statistics = findBy(__.STATISTIC.DATE, newStatistic.getDate(), false);
        if (!statistics.isEmpty()) {
            String message = I18n.get("messages", "statistic.exists.date");
            return new Pair<>(true, message);
        }

        String message = I18n.get("messages", "statistic.exists.not");
        return new Pair<>(false, message);
    }
}
