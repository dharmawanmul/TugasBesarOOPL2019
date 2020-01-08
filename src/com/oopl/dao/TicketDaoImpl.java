package com.oopl.dao;

import com.oopl.entity.Ticket;
import com.oopl.util.DaoService;
import com.oopl.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements DaoService<Ticket> {
    @Override
    public List<Ticket> showAll() {
        List<Ticket> tickets = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(Ticket.class);
        tickets.addAll(criteria.list());
        return tickets;
    }

    @Override
    public int addData(Ticket object) {
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
    public int deleteData(Ticket object) {
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
    public int updateData(Ticket object) {
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

//    public Ticket calculatePrice(Ticket object) {
//        Ticket result = new Ticket();
//        try {
//            Connection connection = DBHelper.createMySQLConnection();
//            String query = "SELECT * from Ticket WHERE User_has_Vehicle_User_NRP = ? AND User_has_Vehicle_Vehicle_registrationNum = ?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, object.getUserVehicle().getUserNRP().getNrp());
//            ps.setString(2, object.getUserVehicle().getUserRegistrationNum().getRegistrationNum());
//
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()) {
//                emp.setIdEmployee(rs.getInt("idEmployee"));
//                emp.setName(rs.getString("name"));
//                emp.setUsername(rs.getString("username"));
//                emp.setPassword(rs.getString("password"));
//
//                Role role = new Role();
//                role.setIdRole(rs.getInt("Role_idRole"));
//                emp.setEmployeeRole(role);
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
}
