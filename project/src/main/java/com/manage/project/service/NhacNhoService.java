package com.manage.project.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.manage.project.model.NhacNho;
import com.manage.project.repo.NhacNhoRepo;

@Service
public class NhacNhoService {

    @Autowired
    private NhacNhoRepo nhacNhoRepository;
    

    // Phương thức để lấy tất cả nhắc nhở từ database
    public List<NhacNho> getAllNhacNho() {
        return (List<NhacNho>) nhacNhoRepository.findAll();  // Lấy tất cả các bản ghi từ repository
    }

    public void createUserReminder(Integer maNguoiDung, String noiDung, LocalDate ngayNhac) {
        NhacNho nhacNho = new NhacNho();
        nhacNho.setMaNguoiDung(maNguoiDung);
        nhacNho.setNoiDung(noiDung);
        nhacNho.setNgayNhac(ngayNhac); // Sử dụng LocalDate thay vì Date
        
        nhacNhoRepository.save(nhacNho);
    }

    // Define the themNhacNho method
    public void themNhacNho(NhacNho nhacNho, Integer maNhiemVu) {
        nhacNho.setMaNguoiDung(maNhiemVu); // Assuming you are setting the MaNguoiDung here
        nhacNhoRepository.save(nhacNho);
    }
    
 // Phương thức lưu nhắc nhở
    public void saveNhacNho(NhacNho nhacNho) {
        nhacNhoRepository.save(nhacNho);  // Lưu nhắc nhở vào DB thông qua repository
    }

}
