package com.oopl.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Employee {
    private Integer idEmployee;
    private String name;
    private String username;
    private String password;
    private String photos;
    private Role roleByRoleIdRole;

    @Id
    @GenericGenerator(strategy = "increment", name = "incrementId")
    @GeneratedValue(generator = "incrementId")
    @Column(name = "idEmployee", nullable = false)
    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "photos", nullable = false, length = 100)
    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(idEmployee, employee.idEmployee) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(username, employee.username) &&
                Objects.equals(password, employee.password) &&
                Objects.equals(photos, employee.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployee, name, username, password, photos);
    }

    @ManyToOne
    @JoinColumn(name = "Role_idRole", referencedColumnName = "idRole", nullable = false)
    public Role getRoleByRoleIdRole() {
        return roleByRoleIdRole;
    }

    public void setRoleByRoleIdRole(Role roleByRoleIdRole) {
        this.roleByRoleIdRole = roleByRoleIdRole;
    }
}
