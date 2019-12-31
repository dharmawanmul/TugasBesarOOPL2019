package com.oopl.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_has_vehicle", schema = "dboopl", catalog = "")
public class UserHasVehicle implements Serializable {
    private User userNRP;
    private Vehicle userRegistrationNum;

    @Id
    @ManyToOne
    @JoinColumn(name = "User_NRP", referencedColumnName = "NRP", nullable = false)
    public User getUserNRP() {
        return userNRP;
    }

    public void setUserNRP(User userNRP) {
        this.userNRP = userNRP;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "Vehicle_registrationNum", referencedColumnName = "registrationNum", nullable = false)
    public Vehicle getUserRegistrationNum() {
        return userRegistrationNum;
    }

    public void setUserRegistrationNum(Vehicle userRegistrationNum) {
        this.userRegistrationNum = userRegistrationNum;
    }
}
