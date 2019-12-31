package com.oopl.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Voucher {
    private String idVoucher;
    private String voucherType;

    @Id
    @Column(name = "idVoucher", nullable = false, length = 50)
    public String getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(String idVoucher) {
        this.idVoucher = idVoucher;
    }

    @Basic
    @Column(name = "voucherType", nullable = false, length = 45)
    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voucher voucher = (Voucher) o;
        return Objects.equals(idVoucher, voucher.idVoucher) &&
                Objects.equals(voucherType, voucher.voucherType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVoucher, voucherType);
    }
}
