package com.manage.project.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.project.model.NguoiDung;

@Repository

public interface NguoiDungRepo extends JpaRepository<NguoiDung, Long> {
    NguoiDung findByTenDangNhap(String tenDangNhap);
    List<NguoiDung> findByVaiTro(String vaiTro);  // Add this method to find users by role
}

