package com.oopl.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {
    private String nrp;
    private Userrole userRole;
    private String userName;

    @Id
    @Column(name = "NRP", nullable = false, length = 45)
    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nrp, user.nrp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrp);
    }

    @ManyToOne
    @JoinColumn(name = "UserRole_idUserRole", referencedColumnName = "idUserRole", nullable = false)
    public Userrole getUserRole() {
        return userRole;
    }

    public void setUserRole(Userrole userRole) {
        this.userRole = userRole;
    }

    @Basic
    @Column(name = "userName", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
