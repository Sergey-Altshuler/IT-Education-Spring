package com.Altshuler.servlce;

import com.Altshuler.dao.DAOCoach;
import com.Altshuler.dao.DAOCoachImpl;
import com.Altshuler.info.ProjectInfo;
import com.Altshuler.model.Coach;
import com.Altshuler.util.HQLUtil;

import java.sql.SQLException;

public class CoachServiceImpl implements CoachService {
    private final DAOCoach daoCoach = new DAOCoachImpl();
    private final HQLUtil hqlUtil = new HQLUtil();

    public Coach add(Coach coach) {
        try {
            return daoCoach.save(coach);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deleteAll(){
        daoCoach.deleteAll(Coach.class);
    }

    public boolean validate(String login, String password) {
        return hqlUtil.checkCoach(login, password) != null;
    }

    public void logIn(String login, String password) {
        ProjectInfo.setCoach(hqlUtil.checkCoach(login, password));
    }
    public Coach getById(int id){
        Coach coach = null;
        try {
            coach = daoCoach.get(id, Coach.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coach;
    }
}
