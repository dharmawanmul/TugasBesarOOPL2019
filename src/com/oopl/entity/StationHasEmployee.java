package com.oopl.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "station_has_employee", schema = "dboopl", catalog = "")
public class StationHasEmployee implements Serializable {
    private Timestamp empDateIn;
    private Timestamp empDateOut;
    private Station stationByStationIdStation;
    private Employee employeeByEmployeeIdEmployee;

    @Basic
    @Column(name = "emp_date_in", nullable = false)
    public Timestamp getEmpDateIn() {
        return empDateIn;
    }

    public void setEmpDateIn(Timestamp empDateIn) {
        this.empDateIn = empDateIn;
    }

    @Basic
    @Column(name = "emp_date_out", nullable = true)
    public Timestamp getEmpDateOut() {
        return empDateOut;
    }

    public void setEmpDateOut(Timestamp empDateOut) {
        this.empDateOut = empDateOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationHasEmployee that = (StationHasEmployee) o;
        return Objects.equals(empDateIn, that.empDateIn) &&
                Objects.equals(empDateOut, that.empDateOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empDateIn, empDateOut);
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "Station_idStation", referencedColumnName = "idStation", nullable = false)
    public Station getStationByStationIdStation() {
        return stationByStationIdStation;
    }

    public void setStationByStationIdStation(Station stationByStationIdStation) {
        this.stationByStationIdStation = stationByStationIdStation;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "Employee_idEmployee", referencedColumnName = "idEmployee", nullable = false)
    public Employee getEmployeeByEmployeeIdEmployee() {
        return employeeByEmployeeIdEmployee;
    }

    public void setEmployeeByEmployeeIdEmployee(Employee employeeByEmployeeIdEmployee) {
        this.employeeByEmployeeIdEmployee = employeeByEmployeeIdEmployee;
    }
}
