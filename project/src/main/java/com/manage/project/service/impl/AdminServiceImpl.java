package com.manage.project.service.impl;


import com.manage.project.model.NguoiDung;
import com.manage.project.repo.NguoiDungRepo;
import com.manage.project.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private NguoiDungRepo nguoiDungRepo;

    @Override
    public NguoiDung getAdminByUsername(String username) {
        return nguoiDungRepo.findByTenDangNhap(username);
    }
}
