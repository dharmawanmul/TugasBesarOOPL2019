package com.oopl.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Vehicle {
    private String registrationNum;
    private Vehicletype vehicletypeByVehicleTypeIdType;

    @Id
    @Column(name = "registrationNum", nullable = false, length = 20)
    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(registrationNum, vehicle.registrationNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNum);
    }

    @ManyToOne
    @JoinColumn(name = "VehicleType_idType", referencedColumnName = "idType", nullable = false)
    public Vehicletype getVehicletypeByVehicleTypeIdType() {
        return vehicletypeByVehicleTypeIdType;
    }

    public void setVehicletypeByVehicleTypeIdType(Vehicletype vehicletypeByVehicleTypeIdType) {
        this.vehicletypeByVehicleTypeIdType = vehicletypeByVehicleTypeIdType;
    }
}
