package com.oopl.dao;

import com.oopl.entity.Employee;
import com.oopl.entity.Role;
import com.oopl.util.DBHelper;
import com.oopl.util.DaoService;
import com.oopl.util.HibernateUtil;
import org.hibernate.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

//        Session session = HibernateUtil.getSession();
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
//        Root<Employee> root = criteriaQuery.from(Employee.class);
//        criteriaQuery.select(root);
//        Query<Employee> query = session.createQuery(criteriaQuery);
//        List<Employee> employees = query.getResultList();
//        return employees;

//        CriteriaBuilder cb = HibernateUtil.getSession().getCriteriaBuilder();
//        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
//        Root<Employee> rootEntry = cq.from(Employee.class);
//        CriteriaQuery<Employee> all = cq.select(rootEntry);
//
//        TypedQuery<Employee> allQuery = HibernateUtil.getSession().createQuery(all);
//        return allQuery.getResultList();
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
                emp.setEmployeeRole(role);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return emp;
    }
}
