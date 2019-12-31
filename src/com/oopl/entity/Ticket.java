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
    private double total;
    private Voucher ticketVoucher;
    private Permissions ticketPermission;
    private StationHasEmployee ticketStation;
    private UserHasVehicle userVehicle;

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

    @Basic
    @Column(name = "total", nullable = false, precision = 0)
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticket.price, price) == 0 &&
                Double.compare(ticket.total, total) == 0 &&
                Objects.equals(idTicket, ticket.idTicket) &&
                Objects.equals(dateIn, ticket.dateIn) &&
                Objects.equals(dateOut, ticket.dateOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTicket, dateIn, dateOut, price, total);
    }

    @ManyToOne
    @JoinColumn(name = "Voucher_idVoucher", referencedColumnName = "idVoucher", nullable = false)
    public Voucher getTicketVoucher() {
        return ticketVoucher;
    }

    public void setTicketVoucher(Voucher ticketVoucher) {
        this.ticketVoucher = ticketVoucher;
    }

    @ManyToOne
    @JoinColumn(name = "Permissions_idPermissions", referencedColumnName = "idPermissions", nullable = false)
    public Permissions getTicketPermission() {
        return ticketPermission;
    }

    public void setTicketPermission(Permissions ticketPermission) {
        this.ticketPermission = ticketPermission;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "Station_has_Employee_Station_idStation", referencedColumnName = "Station_idStation", nullable = false), @JoinColumn(name = "Station_has_Employee_Employee_idEmployee", referencedColumnName = "Employee_idEmployee", nullable = false)})
    public StationHasEmployee getTicketStation() {
        return ticketStation;
    }

    public void setTicketStation(StationHasEmployee ticketStation) {
        this.ticketStation = ticketStation;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "User_has_Vehicle_User_NRP", referencedColumnName = "User_NRP", nullable = false), @JoinColumn(name = "User_has_Vehicle_Vehicle_registrationNum", referencedColumnName = "Vehicle_registrationNum", nullable = false)})
    public UserHasVehicle getUserVehicle() {
        return userVehicle;
    }

    public void setUserVehicle(UserHasVehicle userVehicle) {
        this.userVehicle = userVehicle;
    }
}
