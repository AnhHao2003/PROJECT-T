package com.manage.project.controller;

import com.manage.project.model.NhiemVu;
import com.manage.project.model.DuAn;
import com.manage.project.model.NguoiDung;
import com.manage.project.service.NhiemVuService;
import com.manage.project.service.DuAnService;
import com.manage.project.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/nhiemvu")
public class NhiemVuController {

    private final NhiemVuService nhiemVuService;
    private final NguoiDungService nguoiDungService;
    private final DuAnService duAnService;  // ✅ Thêm dòng này

    // Constructor Injection
    @Autowired
    public NhiemVuController(NhiemVuService nhiemVuService, 
                             NguoiDungService nguoiDungService, 
                             DuAnService duAnService) {
        this.nhiemVuService = nhiemVuService;
        this.nguoiDungService = nguoiDungService;
        this.duAnService = duAnService;  // ✅ Gán giá trị cho duAnService
    }

    // Hiển thị form giao nhiệm vụ
    @GetMapping("/assign")
    public String showAssignForm(Model model) {
        model.addAttribute("nhiemVu", new NhiemVu());
        model.addAttribute("nguoiDung", nguoiDungService.getAllUsers());  // Danh sách người dùng
        model.addAttribute("duans", duAnService.getAllDuAn());           // Danh sách dự án
        return "assign_task";  // Tên file HTML
    }


    // Xử lý giao nhiệm vụ
    @PostMapping("/assign")
    public String assignTask(@ModelAttribute NhiemVu nhiemVu, 
                             @RequestParam("nguoiDungId") Long nguoiDungId, 
                             @RequestParam("duAnId") Integer duAnId) {

        NguoiDung nguoiDung = nguoiDungService.getUserById(nguoiDungId);
        DuAn duAn = duAnService.layDuAnTheoMa(duAnId);

        if (nguoiDung == null || duAn == null) {
            return "redirect:/error";
        }

        nhiemVu.setNguoiDung(nguoiDung);
        nhiemVu.setDuAn(duAn);
        nhiemVuService.saveNhiemVu(nhiemVu);

        return "redirect:/admin/nhiemvu";
    }


    // Hiển thị danh sách nhiệm vụ
    @GetMapping
    public String showAllTasks(Model model) {
        List<NhiemVu> nhiemVuList = nhiemVuService.getAllNhiemVu();
        model.addAttribute("nhiemVuList", nhiemVuList);
        return "task_list";
    }
}
