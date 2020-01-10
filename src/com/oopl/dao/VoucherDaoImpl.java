package com.oopl.dao;

import com.oopl.entity.Permissions;
import com.oopl.entity.Voucher;
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

public class VoucherDaoImpl implements DaoService<Voucher> {
    @Override
    public List<Voucher> showAll() {
        List<Voucher> vouchers = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(Voucher.class);
        vouchers.addAll(criteria.list());
        return vouchers;
    }

    @Override
    public int addData(Voucher object) {
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
    public int deleteData(Voucher object) {
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
    public int updateData(Voucher object) {
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

    public Voucher getVoucherById(String id){
        Voucher v = new Voucher();
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT * FROM Voucher WHERE idVoucher = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                v.setIdVoucher(rs.getString("idVoucher"));
                v.setVoucherType(rs.getString("voucherType"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return v;
    }
}
