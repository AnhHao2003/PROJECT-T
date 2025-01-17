package com.manage.project.service;

import com.manage.project.model.NguoiDung;
import java.util.List;

public interface NguoiDungService {
    List<NguoiDung> getAllUsers();
    NguoiDung getUserById(Long id);

    void createUser(String username, String password, String role);
    NguoiDung getNguoiDung(String username);
    List<NguoiDung> getNguoiDungByRole(String role);

    // THÊM VÀO
    void deleteNguoiDungById(Long id);

    // THÊM VÀO
    void updateNguoiDung(NguoiDung nguoiDung);

    // Đã có, hoặc thêm nếu thiếu
    NguoiDung getNguoiDungById(Long id);
}
