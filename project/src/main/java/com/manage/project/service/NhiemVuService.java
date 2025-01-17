package com.manage.project.service;

import com.manage.project.model.NhiemVu;
import com.manage.project.repo.NhiemVuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhiemVuService {

    private final NhiemVuRepo nhiemVuRepository;

    @Autowired
    public NhiemVuService(NhiemVuRepo nhiemVuRepository) {
        this.nhiemVuRepository = nhiemVuRepository;
    }

    public List<NhiemVu> getAllNhiemVu() {
        return nhiemVuRepository.findAll();
    }

    public void saveNhiemVu(NhiemVu nhiemVu) {
        nhiemVuRepository.save(nhiemVu);
    }
    
    
}
