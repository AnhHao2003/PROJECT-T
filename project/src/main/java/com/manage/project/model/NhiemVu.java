package com.manage.project.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "nhiem_vu")
public class NhiemVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nhiem_vu")
    private int maNhiemVu;

    @ManyToOne
    @JoinColumn(name = "ma_du_an", nullable = false) // Liên kết đúng với bảng du_an
    private DuAn duAn;

    @Column(name = "ten_nhiem_vu", nullable = false, length = 100)
    private String tenNhiemVu;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "ngay_hoan_thanh")
    private LocalDate ngayHoanThanh;

    @Column(name = "trang_thai", length = 20)
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "ma_nguoi_dung", nullable = false)
    private NguoiDung nguoiDung;

    public NhiemVu() {
    }

    public int getMaNhiemVu() {
        return maNhiemVu;
    }

    public void setMaNhiemVu(int maNhiemVu) {
        this.maNhiemVu = maNhiemVu;
    }

    public DuAn getDuAn() {
        return duAn;
    }

    public void setDuAn(DuAn duAn) {
        this.duAn = duAn;
    }

    public String getTenNhiemVu() {
        return tenNhiemVu;
    }

    public void setTenNhiemVu(String tenNhiemVu) {
        this.tenNhiemVu = tenNhiemVu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayHoanThanh() {
        return ngayHoanThanh;
    }

    public void setNgayHoanThanh(LocalDate ngayHoanThanh) {
        this.ngayHoanThanh = ngayHoanThanh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }
}
