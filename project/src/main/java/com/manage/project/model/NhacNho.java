package com.manage.project.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class NhacNho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maNhacNho;

    private Integer maNguoiDung;
    private String noiDung;

    @Column(nullable = true)
    private Integer maNhiemVu;

    @Column(nullable = false)
    private LocalDate ngayNhac;

    public Integer getMaNhacNho() {
        return maNhacNho;
    }

    public void setMaNhacNho(Integer maNhacNho) {
        this.maNhacNho = maNhacNho;
    }

    public Integer getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(Integer maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public LocalDate getNgayNhac() {
        return ngayNhac;
    }

    public void setNgayNhac(LocalDate ngayNhac) {
        this.ngayNhac = ngayNhac;
    }

    public Integer getMaNhiemVu() {
        return maNhiemVu;
    }

    public void setMaNhiemVu(Integer maNhiemVu) {
        this.maNhiemVu = maNhiemVu;
    }
}
