package com.oopl.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Vehicletype {
    private Integer idType;
    private String vehicleType;
    private Double rate;

    @Id
    @Column(name = "idType", nullable = false)
    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    @Basic
    @Column(name = "vehicleType", nullable = false, length = 20)
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Basic
    @Column(name = "rate", nullable = false, precision = 0)
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicletype that = (Vehicletype) o;
        return Objects.equals(idType, that.idType) &&
                Objects.equals(vehicleType, that.vehicleType) &&
                Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idType, vehicleType, rate);
    }

    @Override
    public String toString() {
        return vehicleType;
    }
}
