package com.oopl.dao;

import com.oopl.entity.UserHasVehicle;
import com.oopl.util.DaoService;
import com.oopl.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserHasVehicleDaoImpl implements DaoService<UserHasVehicle> {
    @Override
    public List<UserHasVehicle> showAll() {
//        List<UserHasVehicle> userHasVehicle = new ArrayList<>();
//        Session session = HibernateUtil.getSession();
//        Criteria criteria = session.createCriteria(UserHasVehicle.class);
//        userHasVehicle.addAll(criteria.list());
//        return userHasVehicle;

        Session session = HibernateUtil.getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UserHasVehicle> criteriaQuery = criteriaBuilder.createQuery(UserHasVehicle.class);
        Root<UserHasVehicle> root = criteriaQuery.from(UserHasVehicle.class);
        criteriaQuery.select(root);
        Query<UserHasVehicle> query = session.createQuery(criteriaQuery);
        List<UserHasVehicle> userHasVehicle = query.getResultList();
        return userHasVehicle;
    }

    @Override
    public int addData(UserHasVehicle object) {
        int result = 0;
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(object);
            transaction.commit();
            result = 1;
        } catch (HibernateException exception) {
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public int deleteData(UserHasVehicle object) {
        int result = 0;
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(object);
            transaction.commit();
            result = 1;
        } catch (HibernateException exception) {
            transaction.rollback();
        }
        session.close();
        return result;
    }

    @Override
    public int updateData(UserHasVehicle object) {
        int result = 0;
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(object);
            transaction.commit();
            result = 1;
        } catch (HibernateException exception) {
            transaction.rollback();
        }
        session.close();
        return result;
    }
}
