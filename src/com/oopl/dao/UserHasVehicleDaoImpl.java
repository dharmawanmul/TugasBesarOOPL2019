//package com.oopl.dao;
//
//import com.oopl.util.DBHelper;
//import com.oopl.util.DaoService;
//import com.oopl.util.HibernateUtil;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.List;
//
//public class UserHasVehicleDaoImpl implements DaoService<UserHasVehicle> {
//    @Override
//    public List<UserHasVehicle> showAll() {
////        List<UserHasVehicle> userHasVehicle = new ArrayList<>();
////        Session session = HibernateUtil.getSession();
////        Criteria criteria = session.createCriteria(UserHasVehicle.class);
////        userHasVehicle.addAll(criteria.list());
////        return userHasVehicle;
//
//        Session session = HibernateUtil.getSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<UserHasVehicle> criteriaQuery = criteriaBuilder.createQuery(UserHasVehicle.class);
//        Root<UserHasVehicle> root = criteriaQuery.from(UserHasVehicle.class);
//        criteriaQuery.select(root);
//        Query<UserHasVehicle> query = session.createQuery(criteriaQuery);
//        List<UserHasVehicle> userHasVehicle = query.getResultList();
//        return userHasVehicle;
//    }
//
//    @Override
//    public int addData(UserHasVehicle object) {
//        int result = 0;
//        Session session = HibernateUtil.getSession();
//        Transaction transaction = session.beginTransaction();
//        try {
//            session.save(object);
//            transaction.commit();
//            result = 1;
//        } catch (HibernateException exception) {
//            transaction.rollback();
//        }
//        session.close();
//        return result;
//    }
//
//    @Override
//    public int deleteData(UserHasVehicle object) {
//        int result = 0;
//        Session session = HibernateUtil.getSession();
//        Transaction transaction = session.beginTransaction();
//        try {
//            session.delete(object);
//            transaction.commit();
//            result = 1;
//        } catch (HibernateException exception) {
//            transaction.rollback();
//        }
//        session.close();
//        return result;
//    }
//
//    @Override
//    public int updateData(UserHasVehicle object) {
//        int result = 0;
////        Session session = HibernateUtil.getSession();
////        Transaction transaction = session.beginTransaction();
////        try {
////            session.update(object);
////            transaction.commit();
////            result = 1;
////        } catch (HibernateException exception) {
////            transaction.rollback();
////        }
////        session.close();
//        try {
//            Connection connection = DBHelper.createMySQLConnection();
//            String query = "UPDATE User_has_Vehicle u JOIN Vehicle v ON u.Vehicle_registrationNum = v.registrationNum SET u.Vehicle_registrationNum=?, u.VehicleType_idType=?, v.registrationNum=? WHERE u.User_NRP =? AND u.Vehicle_registrationNum=?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, object.getVehicleByVehicleRegistrationNum().getRegistrationNum());
//            ps.setInt(2, object.getVehicletypeByVehicleTypeIdType().getIdType());
//            ps.setString(3, object.getVehicleByVehicleRegistrationNum().getRegistrationNum());
//            ps.setString(4, object.getUserByUserNrp().getNrp());
//            ps.setString(5, object.getVehicleByVehicleRegistrationNum().getRegistrationNum());
//            if (ps.executeUpdate() != 0) {
//                connection.commit();
//                result = 1;
//            } else {
//                connection.rollback();
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
////    public int updateVehicle(Vehicle object){
////        int result = 0;
////        try {
////            Connection connection = DBHelper.createMySQLConnection();
////            String query = "UPDATE vehicle SET registrationNum = ?";
////            PreparedStatement ps = connection.prepareStatement(query);
////            ps.setString(1, object.getRegistrationNum());
////            if (ps.executeUpdate()!=0) {
////                connection.commit();
////                result = 1;
////            } else {
////                connection.rollback();
////            }
////        } catch (SQLException | ClassNotFoundException e) {
////            e.printStackTrace();
////        }
////        return result;
////    }
////
////    public int updateUserVehicle(UserHasVehicle object){
////        int result = 0;
////        try {
////            Connection connection = DBHelper.createMySQLConnection();
////            String query = "UPDATE User_has_Vehicle SET Vehicle_registrationNum = ? WHERE User_NRP = ?";
////            PreparedStatement ps = connection.prepareStatement(query);
////            ps.setString(1, object.getVehicleByVehicleRegistrationNum().getRegistrationNum());
////            ps.setString(2, object.getUserByUserNrp().getNrp());
////            if (ps.executeUpdate()!=0) {
////                connection.commit();
////                result = 1;
////            } else {
////                connection.rollback();
////            }
////        } catch (SQLException | ClassNotFoundException e) {
////            e.printStackTrace();
////        }
////        return result;
////    }
//
//}
