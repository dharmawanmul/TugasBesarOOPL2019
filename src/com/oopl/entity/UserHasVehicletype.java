package com.oopl.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_has_vehicletype", schema = "dboopl", catalog = "")
@IdClass(UserHasVehicletypePK.class)
public class UserHasVehicletype {
    private String userNrp;
    private int vehicleTypeIdType;
    private String registrationNo;
    private User userByUserNrp;

    @Id
    @Column(name = "User_NRP", nullable = false, length = 45)
    public String getUserNrp() {
        return userNrp;
    }

    public void setUserNrp(String userNrp) {
        this.userNrp = userNrp;
    }

    @Id
    @Column(name = "VehicleType_idType", nullable = false)
    public int getVehicleTypeIdType() {
        return vehicleTypeIdType;
    }

    public void setVehicleTypeIdType(int vehicleTypeIdType) {
        this.vehicleTypeIdType = vehicleTypeIdType;
    }

    @Id
    @Column(name = "registration_no", nullable = false, length = 11)
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
        UserHasVehicletype that = (UserHasVehicletype) o;
        return vehicleTypeIdType == that.vehicleTypeIdType &&
                Objects.equals(userNrp, that.userNrp) &&
                Objects.equals(registrationNo, that.registrationNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNrp, vehicleTypeIdType, registrationNo);
    }

    @ManyToOne
    @JoinColumn(name = "User_NRP", referencedColumnName = "NRP", nullable = false)
    public User getUserByUserNrp() {
        return userByUserNrp;
    }

    public void setUserByUserNrp(User userByUserNrp) {
        this.userByUserNrp = userByUserNrp;
    }
}
