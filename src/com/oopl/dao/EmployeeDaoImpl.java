package com.oopl.dao;

import com.oopl.entity.Employee;
import com.oopl.entity.Role;
import com.oopl.entity.User;
import com.oopl.util.DBHelper;
import com.oopl.util.DaoService;
import com.oopl.util.HibernateUtil;
import org.hibernate.*;
import org.jboss.jandex.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements DaoService<Employee> {
    @Override
    public List<Employee> showAll() {
        List<Employee> employees = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(Employee.class);
        employees.addAll(criteria.list());
        return employees;
    }

    @Override
    public int addData(Employee object) {
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
    public int deleteData(Employee object) {
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
    public int updateData(Employee object) {
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

    public Employee loginEmployee(Employee object) {
        Employee emp = new Employee();
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT * from Employee WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, object.getUsername());
            ps.setString(2, object.getPassword());

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                emp.setUsername(rs.getString("username"));
                emp.setName(rs.getString("name"));

                Role role = new Role();
                role.setIdRole(rs.getInt("Role_idRole"));
                emp.setRole(role);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return emp;
    }
//public Employee userLogin(String username, String password) {
//    Session session = null;
//    Employee employee = null;
//    try {
//        session = HibernateUtil.getSession();
//        employee = (Employee)session.get(Employee.class, username);
//        Hibernate.initialize(employee);
//    } catch (Exception e) {
//        e.printStackTrace();
//    } finally {
//        if (session != null && session.isOpen()) {
//            session.close();
//        }
//    }
//    return employee;
//}

    public int validateEmployee(Employee object) {
        int result = 0;
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT * from employee WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, object.getUsername());
            ps.setString(2, object.getPassword());
            if (ps.executeUpdate() != 0) {
                connection.commit();
                if (object.getRole().getIdRole() == 1) {
                    result = 1;
                }
                else if (object.getRole().getIdRole() == 2) {
                    result = 2;
                }
                else if (object.getRole().getIdRole() == 3) {
                    result = 3;
                }
            } else {
                connection.rollback();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
