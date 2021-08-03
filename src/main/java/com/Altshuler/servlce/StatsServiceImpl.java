package com.Altshuler.servlce;

import com.Altshuler.dao.DAOStats;
import com.Altshuler.dao.DAOStatsImpl;
import com.Altshuler.model.Stats;

import java.sql.SQLException;
import java.util.List;

public class StatsServiceImpl implements StatsService{
    private final DAOStats daoStats = new DAOStatsImpl();

    public Stats add(Stats stats) {
        try {
            return daoStats.save(stats);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Stats> getAll() {
        List<Stats> statsList = null;
        try {
            statsList = daoStats.getAll(Stats.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statsList;
    }
    public void deleteAll(){
        daoStats.deleteAll(Stats.class);
    }
}
