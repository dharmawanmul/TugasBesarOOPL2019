package com.oopl.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Permissions {
    private String idPermissions;
    private Timestamp dateStart;
    private Timestamp dateEnd;

    @Id
    @Column(name = "idPermissions", nullable = false, length = 10)
    public String getIdPermissions() {
        return idPermissions;
    }

    public void setIdPermissions(String idPermissions) {
        this.idPermissions = idPermissions;
    }

    @Basic
    @Column(name = "date_start", nullable = false)
    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "date_end", nullable = false)
    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissions that = (Permissions) o;
        return Objects.equals(idPermissions, that.idPermissions) &&
                Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(dateEnd, that.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPermissions, dateStart, dateEnd);
    }
}
