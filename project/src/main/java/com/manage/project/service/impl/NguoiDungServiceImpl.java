package com.manage.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manage.project.model.NguoiDung;
import com.manage.project.model.NhiemVu;
import com.manage.project.repo.NguoiDungRepo;
import com.manage.project.repo.NhiemVuRepo;
import com.manage.project.service.NguoiDungService;

@Service
public class NguoiDungServiceImpl implements NguoiDungService, UserDetailsService {

    @Autowired
    private NguoiDungRepo nguoiDungRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String tenDangNhap) throws UsernameNotFoundException {
        NguoiDung nguoiDung = nguoiDungRepo.findByTenDangNhap(tenDangNhap);
        if (nguoiDung == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(nguoiDung.getTenDangNhap())
                .password(nguoiDung.getMatKhau())
                .authorities("ROLE_" + nguoiDung.getVaiTro())
                .build();
    }

    @Override
    public void createUser(String username, String password, String role) {
        if (nguoiDungRepo.findByTenDangNhap(username) != null) {
            throw new IllegalArgumentException("Tài khoản đã tồn tại.");
        }
        NguoiDung user = new NguoiDung(username, passwordEncoder.encode(password), role);
        nguoiDungRepo.save(user);
    }

    @Override
    public NguoiDung getNguoiDung(String username) {
        return nguoiDungRepo.findByTenDangNhap(username);
    }

    @Override
    public List<NguoiDung> getAllUsers() {
        return nguoiDungRepo.findAll();
    }

    @Override
    public NguoiDung getUserById(Long id) {
        return nguoiDungRepo.findById(id).orElse(null);
    }

    @Override
    public List<NguoiDung> getNguoiDungByRole(String role) {
        return nguoiDungRepo.findByVaiTro(role);
    }

    // THÊM VÀO - Xóa người dùng
    @Override
    public void deleteNguoiDungById(Long id) {
        nguoiDungRepo.deleteById(id);
    }

    // THÊM VÀO - Cập nhật người dùng
    @Override
    public void updateNguoiDung(NguoiDung nguoiDung) {
        nguoiDungRepo.save(nguoiDung);
    }

    // THÊM VÀO - Lấy người dùng theo ID
    @Override
    public NguoiDung getNguoiDungById(Long id) {
        return nguoiDungRepo.findById(id).orElse(null);
    }
}
