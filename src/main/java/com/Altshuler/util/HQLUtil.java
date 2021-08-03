package com.Altshuler.util;

import com.Altshuler.model.Coach;
import com.Altshuler.model.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HQLUtil {
    public Coach checkCoach(String login, String password) {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from coach c where c.login = :login AND c.password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        List<Coach> resultList = query.getResultList();
        session.close();
        if ((resultList == null) || (resultList.size() == 0)) {
            return null;
        } else return resultList.get(0);
    }

    public Student checkStudent(String login, String password) {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from student s where s.login = :login AND s.password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        List<Student> resultList = query.getResultList();
        session.close();
        if ((resultList == null) || (resultList.size() == 0)) {
            return null;
        } else return resultList.get(0);
    }
}
