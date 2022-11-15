package com.Altshuler.servlce;

import com.Altshuler.model.Stats;

import java.util.List;

public interface StatsService {
    Stats add(Stats stats);
    List<Stats> getAll();
    void deleteAll();
}
