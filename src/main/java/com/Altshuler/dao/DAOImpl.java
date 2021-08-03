package com.Altshuler.dao;

import com.Altshuler.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.Entity;
import java.sql.SQLException;
import java.util.List;


public class DAOImpl<T> implements DAO<T> {

    @Override
    public T save(T t) {
        try (Session session = SessionUtil.getSession()) {
            session.getTransaction().begin();
            session.saveOrUpdate(t);
            session.getTransaction().commit();
        }
        return t;
    }

    @Override
    public T get(int id, Class<T> generic) throws SQLException {
        Session session = SessionUtil.getSession();
        session.getTransaction().begin();
        T t = session.get(generic, id);
        session.getTransaction().commit();
        session.close();
        return t;
    }

    @Override
    public void deleteAll(Class<T> generic) {
        String annotationName = generic.getAnnotation(Entity.class).name();
        Session session = SessionUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("delete from " + annotationName);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();

    }


    @Override
    public void saveAll(List<T> t) throws SQLException {
        for (T obj : t) {
            save(obj);
        }
    }

    @Override
    public List<T> getAll(Class<T> generic) throws SQLException {
        Session session = SessionUtil.getSession();
        session.getTransaction().begin();
        String annotationName = generic.getAnnotation(Entity.class).name();
        List<T> resultList = session.createQuery("from " + annotationName).getResultList();
        session.getTransaction().commit();
        session.close();
        return resultList;
    }

    @Override
    public void delete(int id, Class<T> generic) throws SQLException {
        Session session = SessionUtil.getSession();
        session.getTransaction().begin();
        String annotationName = generic.getAnnotation(Entity.class).name();
        String annotationHQL = " " + annotationName + " ";
        Query query = session.createQuery("delete from" + annotationHQL + "where id= :id");
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
