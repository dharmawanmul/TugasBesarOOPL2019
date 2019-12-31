package com.oopl.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Vehicle {
    private String registrationNum;
    private String vehicleType;

    @Id
    @Column(name = "registrationNum", nullable = false, length = 20)
    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }

    @Basic
    @Column(name = "vehicleType", nullable = false, length = 20)
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(registrationNum, vehicle.registrationNum) &&
                Objects.equals(vehicleType, vehicle.vehicleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNum, vehicleType);
    }
}
