package com.manage.project.service;

import com.manage.project.model.NguoiDung;

public interface AdminService {
    NguoiDung getAdminByUsername(String username);
}
