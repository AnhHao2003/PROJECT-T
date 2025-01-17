package com.manage.project.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "du_an")
public class DuAn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_du_an")
    private Integer maDuAn;

    @Column(name = "ten_du_an", nullable = false, length = 100)
    private String tenDuAn;

    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDate ngayKetThuc;

    @OneToMany(mappedBy = "duAn")
    private List<NhiemVu> danhSachNhiemVu;
    
    @Column(name = "tien_do", nullable = false)
    private String tienDo;  // Tiến độ của dự án

    @Transient
    public String getTrangThaiDuAn() {
        if (this.tienDo != null) {
            return this.tienDo;
        }

        if (danhSachNhiemVu == null || danhSachNhiemVu.isEmpty()) {
            return "Chưa có nhiệm vụ";
        }

        boolean isHoanThanh = danhSachNhiemVu.stream()
                .allMatch(nv -> "Hoàn thành".equalsIgnoreCase(nv.getTrangThai()));

        boolean isDangTienHanh = danhSachNhiemVu.stream()
                .anyMatch(nv -> "Đang thực hiện".equalsIgnoreCase(nv.getTrangThai()));

        if (isHoanThanh) {
            return "Hoàn thành";
        } else if (isDangTienHanh) {
            return "Đang thực hiện";
        } else {
            return "Chưa bắt đầu";
        }
    }

    public Integer getMaDuAn() {
        return maDuAn;
    }

    public void setMaDuAn(Integer maDuAn) {
        this.maDuAn = maDuAn;
    }

    public String getTenDuAn() {
        return tenDuAn;
    }

    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
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

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTienDo() {
        return tienDo;
    }

    public void setTienDo(String tienDo) {
        this.tienDo = tienDo;
    }
}
