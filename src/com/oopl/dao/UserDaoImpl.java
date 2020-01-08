package com.oopl.dao;

import com.oopl.entity.*;
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

//    public int updateVehicle

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

    public User loginUser(User object) {
        User u = new User();
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT * from User WHERE NRP = ? AND Vehicle_registrationNum = ? LIMIT 1";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, object.getNrp());
            ps.setString(2, object.getVehicleByVehicleRegistrationNum().getRegistrationNum());

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                u.setNrp(rs.getString("NRP"));
                u.setUserName(rs.getString("username"));

                Vehicle vehicle = new Vehicle();
                vehicle.setRegistrationNum(rs.getString("registrationNum"));

                Vehicletype vehicletype = new Vehicletype();
                vehicletype.setIdType(rs.getInt("idType"));
                vehicletype.setVehicleType(rs.getString("vehicleType"));
                vehicletype.setRate(rs.getDouble("rate"));

                vehicle.setVehicletypeByVehicleTypeIdType(vehicletype);
                u.setVehicleByVehicleRegistrationNum(vehicle);

                Userrole userrole = new Userrole();
                userrole.setIdUserRole(rs.getInt("idUserRole"));
                userrole.setUserRole(rs.getString("userRole"));

                u.setUserroleByUserRoleIdUserRole(userrole);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return u;
    }

    public int getGuest() {
        int result = 1;
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT MAX(SUBSTRING(NRP, 6,5)) FROM User WHERE NRP LIKE 'GUEST%'";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = Integer.parseInt(rs.getString("MAX(SUBSTRING(NRP, 6,5))")) + 1;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public User validateUser(User object) throws SQLException, ClassNotFoundException {
//        int result = 0;
//        Connection connection = DBHelper.createMySQLConnection();
//        String query = "SELECT * from user WHERE NRP = ? AND registration_no = ?";
//        PreparedStatement ps = connection.prepareStatement(query);
//        ps.setString(1, object.getNrp());
//        ps.setString(2, object);
//    }
}
