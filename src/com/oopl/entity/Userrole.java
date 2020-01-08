package com.oopl.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Userrole {
    private Integer idUserRole;
    private String userRole;

    @Id
    @Column(name = "idUserRole", nullable = false)
    public Integer getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(Integer idUserRole) {
        this.idUserRole = idUserRole;
    }

    @Basic
    @Column(name = "userRole", nullable = false, length = 15)
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Userrole userrole = (Userrole) o;
        return Objects.equals(idUserRole, userrole.idUserRole) &&
                Objects.equals(userRole, userrole.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserRole, userRole);
    }
}
