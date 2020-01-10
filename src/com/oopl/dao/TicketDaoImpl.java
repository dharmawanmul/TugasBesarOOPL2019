package com.oopl.dao;

import com.oopl.entity.*;
import com.oopl.util.DBHelper;
import com.oopl.util.DaoService;
import com.oopl.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
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

    public int getAIId(String front) {
        int result = 1 ;
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT MAX(SUBSTRING(idTicket, 6,5)) FROM Ticket WHERE SUBSTRING(idTicket,1,4) = ? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, front);
            ResultSet rs = ps.executeQuery();
            while (rs.next() && rs.getString("MAX(SUBSTRING(idTicket, 6,5))") != null) {
                result = Integer.parseInt(rs.getString("MAX(SUBSTRING(idTicket, 6,5))")) + 1;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getVehicleNum(String monthYear, int vehicle) {
        int Num = 0;
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT COUNT(t.idTicket) FROM Ticket t JOIN Vehicle v ON t.Vehicle_registrationNum = v.registrationNum WHERE SUBSTRING(t.idTicket,1,4) = ? AND v.VehicleType_idType=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, monthYear);
            ps.setInt(2, vehicle);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Num = Integer.parseInt(rs.getString("COUNT(t.idTicket)"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Num;
    }

    public int getProfit(String monthYear, int vehicle) {
        int Num = 0;
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT SUM(t.total) FROM Ticket t JOIN Vehicle v ON t.Vehicle_registrationNum = v.registrationNum WHERE SUBSTRING(t.idTicket,1,4) = ? AND v.VehicleType_idType=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, monthYear);
            ps.setInt(2, vehicle);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Num = rs.getInt("SUM(t.total)");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Num;
    }

    public List<String> getAllYear(){
        List<String> years = new ArrayList<>();
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT DISTINCT YEAR(date_out) FROM Ticket";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                years.add(rs.getString("YEAR(date_out)"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return years;
    }

//    public Ticket getTicketByNRPAndRegisNum(String NRP, String plateNum) {
//        Ticket ticket = new Ticket();
//        try {
//            Connection connection = DBHelper.createMySQLConnection();
//            String query = "SELECT * FROM Ticket WHERE User_NRP = ? AND Vehicle_registrationNum = ?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, NRP);
//            ps.setString(2, plateNum);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ticket.setIdTicket(rs.getString("idTicket"));
//                ticket.setDateIn(rs.getTimestamp("date_in"));
//                ticket.setStatus(rs.getInt("status"));
//                ticket.setTotal(rs.getDouble("total"));
//
//                User u = new User();
//                u.setNrp(rs.getString("User_NRP"));
//
//                ticket.setUserByUserNrp(u);
//
//                Vehicle v = new Vehicle();
//                v.setRegistrationNum(rs.getString("Vehicle_registrationNum"));
//
//                ticket.setVehicleByVehicleRegistrationNum(v);
//
//                Station s = new Station();
//                s.setIdStation(rs.getInt("Station_idStation"));
//
//                ticket.setStationByStationIdStation(s);
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return ticket;
//    }

    public Ticket getTicketByNRPAndRegisNum(String NRP, String plateNum) {
        Ticket ticket = new Ticket();
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT t.*, v.*, ve.*, u.NRP FROM Ticket t JOIN User u ON t.User_NRP = u.NRP JOIN Vehicle v ON u.Vehicle_registrationNum = v.registrationNum JOIN Vehicletype ve ON v.VehicleType_idType = ve.idType WHERE t.User_NRP = ? AND t.Vehicle_registrationNum = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, NRP);
            ps.setString(2, plateNum);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ticket.setIdTicket(rs.getString("idTicket"));
                ticket.setDateIn(rs.getTimestamp("date_in"));
                ticket.setStatus(rs.getInt("status"));
                ticket.setTotal(rs.getDouble("total"));

                User u = new User();
                u.setNrp(rs.getString("User_NRP"));

                ticket.setUserByUserNrp(u);

                Vehicletype vehicletype = new Vehicletype();
                vehicletype.setIdType(rs.getInt("idType"));
                vehicletype.setVehicleType(rs.getString("vehicleType"));
                vehicletype.setRate(rs.getDouble("rate"));

                Vehicle v = new Vehicle();
                v.setRegistrationNum(rs.getString("Vehicle_registrationNum"));
                v.setVehicletypeByVehicleTypeIdType(vehicletype);

                ticket.setVehicleByVehicleRegistrationNum(v);

                Station s = new Station();
                s.setIdStation(rs.getInt("Station_idStation"));

                ticket.setStationByStationIdStation(s);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public Ticket getTicketByIdTicketAndRegisNum(String id, String plateNum) {
        Ticket ticket = new Ticket();
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT t.*, v.*, ve.*, u.NRP FROM Ticket t JOIN User u ON t.User_NRP = u.NRP JOIN Vehicle v ON u.Vehicle_registrationNum = v.registrationNum JOIN Vehicletype ve ON v.VehicleType_idType = ve.idType WHERE t.idTicket = ? AND t.Vehicle_registrationNum = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, plateNum);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ticket.setIdTicket(rs.getString("idTicket"));
                ticket.setDateIn(rs.getTimestamp("date_in"));
                ticket.setStatus(rs.getInt("status"));
                ticket.setTotal(rs.getDouble("total"));

                User u = new User();
                u.setNrp(rs.getString("User_NRP"));

                ticket.setUserByUserNrp(u);

                Vehicletype vehicletype = new Vehicletype();
                vehicletype.setIdType(rs.getInt("idType"));
                vehicletype.setVehicleType(rs.getString("vehicleType"));
                vehicletype.setRate(rs.getDouble("rate"));

                Vehicle v = new Vehicle();
                v.setRegistrationNum(rs.getString("Vehicle_registrationNum"));
                v.setVehicletypeByVehicleTypeIdType(vehicletype);

                ticket.setVehicleByVehicleRegistrationNum(v);

                Station s = new Station();
                s.setIdStation(rs.getInt("Station_idStation"));

                ticket.setStationByStationIdStation(s);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ticket;
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
