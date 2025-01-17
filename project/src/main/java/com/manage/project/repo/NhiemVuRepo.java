package com.manage.project.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.project.model.NhiemVu;

@Repository
public interface NhiemVuRepo extends JpaRepository<NhiemVu, Integer> {
    List<NhiemVu> findByNguoiDung_MaNguoiDung(Integer maNguoiDung);
}

