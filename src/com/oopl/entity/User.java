package com.oopl.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {
    private String nrp;
    private String name;
    private Userrole userroleByUserRoleIdUserRole;

    @Id
    @Column(name = "NRP", nullable = false, length = 45)
    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nrp, user.nrp) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrp, name);
    }

    @ManyToOne
    @JoinColumn(name = "UserRole_idUserRole", referencedColumnName = "idUserRole", nullable = false)
    public Userrole getUserroleByUserRoleIdUserRole() {
        return userroleByUserRoleIdUserRole;
    }

    public void setUserroleByUserRoleIdUserRole(Userrole userroleByUserRoleIdUserRole) {
        this.userroleByUserRoleIdUserRole = userroleByUserRoleIdUserRole;
    }
}
