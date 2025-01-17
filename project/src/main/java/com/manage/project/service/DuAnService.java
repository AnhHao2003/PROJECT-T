package com.manage.project.service;

import java.util.List;
import com.manage.project.model.DuAn;

public interface DuAnService {
    List<DuAn> layTatCaDuAn(); // Lấy tất cả các dự án
    DuAn themDuAn(DuAn duAn); // Thêm một dự án
    DuAn capNhatDuAn(DuAn duAn); // Cập nhật thông tin dự án
    void xoaDuAn(Integer maDuAn); // Xóa dự án theo mã
    DuAn layDuAnTheoMa(Integer maDuAn);
    List<DuAn> getAllDuAn();    
    
    
}