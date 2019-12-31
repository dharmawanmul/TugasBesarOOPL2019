package com.oopl.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Ticket {
    private String idTicket;
    private Timestamp dateIn;
    private Timestamp dateOut;
    private double price;
    private UserHasVehicletype userHasVehicletype;

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
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticket.price, price) == 0 &&
                Objects.equals(idTicket, ticket.idTicket) &&
                Objects.equals(dateIn, ticket.dateIn) &&
                Objects.equals(dateOut, ticket.dateOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTicket, dateIn, dateOut, price);
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "User_has_VehicleType_User_NRP", referencedColumnName = "User_NRP", nullable = false), @JoinColumn(name = "User_has_VehicleType_VehicleType_idType", referencedColumnName = "VehicleType_idType", nullable = false), @JoinColumn(name = "User_has_VehicleType_registration_no", referencedColumnName = "registration_no", nullable = false)})
    public UserHasVehicletype getUserHasVehicletype() {
        return userHasVehicletype;
    }

    public void setUserHasVehicletype(UserHasVehicletype userHasVehicletype) {
        this.userHasVehicletype = userHasVehicletype;
    }
}
