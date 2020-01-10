package com.oopl.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Ticket {
    private String idTicket;
    private Timestamp dateIn;
    private Timestamp dateOut;
    private Integer status;
    private Double total;
    private User userByUserNrp;
    private Vehicle vehicleByVehicleRegistrationNum;
    private Station stationByStationIdStation;

    @Id
    @Column(name = "idTicket", nullable = false, length = 100)
    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    @Basic
    @Column(name = "date_in", nullable = false)
    public Timestamp getDateIn() {
        return dateIn;
    }

    public void setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
    }

    @Basic
    @Column(name = "date_out", nullable = true)
    public Timestamp getDateOut() {
        return dateOut;
    }

    public void setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "total", nullable = false, precision = 0)
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(idTicket, ticket.idTicket) &&
                Objects.equals(dateIn, ticket.dateIn) &&
                Objects.equals(dateOut, ticket.dateOut) &&
                Objects.equals(status, ticket.status) &&
                Objects.equals(total, ticket.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTicket, dateIn, dateOut, status, total);
    }

    @ManyToOne
    @JoinColumn(name = "User_NRP", referencedColumnName = "NRP", nullable = false)
    public User getUserByUserNrp() {
        return userByUserNrp;
    }

    public void setUserByUserNrp(User userByUserNrp) {
        this.userByUserNrp = userByUserNrp;
    }

    @ManyToOne
    @JoinColumn(name = "Vehicle_registrationNum", referencedColumnName = "registrationNum", nullable = false)
    public Vehicle getVehicleByVehicleRegistrationNum() {
        return vehicleByVehicleRegistrationNum;
    }

    public void setVehicleByVehicleRegistrationNum(Vehicle vehicleByVehicleRegistrationNum) {
        this.vehicleByVehicleRegistrationNum = vehicleByVehicleRegistrationNum;
    }

    @ManyToOne
    @JoinColumn(name = "Station_idStation", referencedColumnName = "idStation", nullable = false)
    public Station getStationByStationIdStation() {
        return stationByStationIdStation;
    }

    public void setStationByStationIdStation(Station stationByStationIdStation) {
        this.stationByStationIdStation = stationByStationIdStation;
    }
}
