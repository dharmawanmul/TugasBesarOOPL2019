package com.oopl.dao;

import com.oopl.entity.Permissions;
import com.oopl.util.DBHelper;
import com.oopl.util.DaoService;
import com.oopl.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PermissionsDaoImpl implements DaoService<Permissions> {
    @Override
    public List<Permissions> showAll() {
        List<Permissions> permissions = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(Permissions.class);
        permissions.addAll(criteria.list());
        return permissions;
    }

    @Override
    public int addData(Permissions object) {
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
    public int deleteData(Permissions object) {
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
    public int updateData(Permissions object) {
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

    public Permissions getPermissionById(String id){
        Permissions p = new Permissions();
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT * FROM Permissions WHERE idPermissions = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setIdPermissions(rs.getString("idPermissions"));
                p.setDateStart(rs.getTimestamp("date_start"));
                p.setDateEnd(rs.getTimestamp("date_end"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return p;
    }
}
