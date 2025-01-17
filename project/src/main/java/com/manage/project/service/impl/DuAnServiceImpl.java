package com.manage.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manage.project.model.DuAn;
import com.manage.project.repo.DuAnRepo;
import com.manage.project.service.DuAnService;

import java.util.List;

@Service
public class DuAnServiceImpl implements DuAnService {

    @Autowired
    private DuAnRepo duAnRepo;

    @Override
    public List<DuAn> layTatCaDuAn() {
        return duAnRepo.findAll(); // Lấy tất cả các dự án từ database
    }

    @Override
    public DuAn themDuAn(DuAn duAn) {
        return duAnRepo.save(duAn); // Save the project to the database
    }


    @Override
    public DuAn capNhatDuAn(DuAn duAn) {
        // Đảm bảo tiến độ không null hoặc rỗng
        if (duAn.getTienDo() == null || duAn.getTienDo().isEmpty()) {
            duAn.setTienDo("Chưa bắt đầu");
        }
        return duAnRepo.save(duAn); // Lưu hoặc cập nhật thông tin dự án
    }
    
    @Override
    public List<DuAn> getAllDuAn() {
        return duAnRepo.findAll();  // Lấy toàn bộ danh sách dự án
    }

    @Override
    public void xoaDuAn(Integer maDuAn) {
        duAnRepo.deleteById(maDuAn); // Xóa dự án theo mã dự án
    }
    
    @Override
    public DuAn layDuAnTheoMa(Integer maDuAn) {
        return duAnRepo.findById(maDuAn).orElse(null);
    }
    
}
