package com.oopl.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserHasVehicletypePK implements Serializable {
    private String userNrp;
    private int vehicleTypeIdType;
    private String registrationNo;

    @Column(name = "User_NRP", nullable = false, length = 45)
    @Id
    public String getUserNrp() {
        return userNrp;
    }

    public void setUserNrp(String userNrp) {
        this.userNrp = userNrp;
    }

    @Column(name = "VehicleType_idType", nullable = false)
    @Id
    public int getVehicleTypeIdType() {
        return vehicleTypeIdType;
    }

    public void setVehicleTypeIdType(int vehicleTypeIdType) {
        this.vehicleTypeIdType = vehicleTypeIdType;
    }

    @Column(name = "registration_no", nullable = false, length = 11)
    @Id
    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHasVehicletypePK that = (UserHasVehicletypePK) o;
        return vehicleTypeIdType == that.vehicleTypeIdType &&
                Objects.equals(userNrp, that.userNrp) &&
                Objects.equals(registrationNo, that.registrationNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNrp, vehicleTypeIdType, registrationNo);
    }
}
