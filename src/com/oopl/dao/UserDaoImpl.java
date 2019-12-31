package com.oopl.dao;

import com.oopl.entity.User;
import com.oopl.util.DBHelper;
import com.oopl.util.DaoService;
import com.oopl.util.HibernateUtil;
import org.hibernate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements DaoService<User> {
    @Override
    public List<User> showAll() {
        List<User> menus = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(User.class);
        menus.addAll(criteria.list());
        return menus;
    }

    @Override
    public int addData(User object) {
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
    public int deleteData(User object) {
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
    public int updateData(User object) {
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

//    public User userLogin(String user_id) {
//        Session session = null;
//        User user = null;
//        try {
//            session = HibernateUtil.getSession();
//            user = (User)session.get(User.class, user_id);
//            Hibernate.initialize(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//        return user;
//    }
//    public User validateUser(User object) throws SQLException, ClassNotFoundException {
//        int result = 0;
//        Connection connection = DBHelper.createMySQLConnection();
//        String query = "SELECT * from user WHERE NRP = ? AND registration_no = ?";
//        PreparedStatement ps = connection.prepareStatement(query);
//        ps.setString(1, object.getNrp());
//        ps.setString(2, object);
//    }
}
