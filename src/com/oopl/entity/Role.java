package com.oopl.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Role {
    private Integer idRole;
    private String role;

    @Id
    @Column(name = "idRole", nullable = false)
    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 20)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(idRole, role1.idRole) &&
                Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, role);
    }

    @Override
    public String toString() {
        return this.role;
    }
}
