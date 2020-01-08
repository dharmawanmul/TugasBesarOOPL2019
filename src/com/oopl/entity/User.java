package com.oopl.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {
    private String nrp;
    private String userName;
    private Userrole userroleByUserRoleIdUserRole;
    private Vehicle vehicleByVehicleRegistrationNum;

    @Id
    @Column(name = "NRP", nullable = false, length = 45)
    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    @Basic
    @Column(name = "userName", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nrp, user.nrp) &&
                Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrp, userName);
    }

    @ManyToOne
    @JoinColumn(name = "UserRole_idUserRole", referencedColumnName = "idUserRole", nullable = false)
    public Userrole getUserroleByUserRoleIdUserRole() {
        return userroleByUserRoleIdUserRole;
    }

    public void setUserroleByUserRoleIdUserRole(Userrole userroleByUserRoleIdUserRole) {
        this.userroleByUserRoleIdUserRole = userroleByUserRoleIdUserRole;
    }

    @ManyToOne
    @JoinColumn(name = "Vehicle_registrationNum", referencedColumnName = "registrationNum", nullable = false)
    public Vehicle getVehicleByVehicleRegistrationNum() {
        return vehicleByVehicleRegistrationNum;
    }

    public void setVehicleByVehicleRegistrationNum(Vehicle vehicleByVehicleRegistrationNum) {
        this.vehicleByVehicleRegistrationNum = vehicleByVehicleRegistrationNum;
    }
}
