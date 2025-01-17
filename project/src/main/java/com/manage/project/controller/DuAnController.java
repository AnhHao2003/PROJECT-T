package com.manage.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.manage.project.service.DuAnService;

@Controller
public class DuAnController {

    @Autowired
    private DuAnService duAnService;

    @GetMapping("/project_progress")
    public String hienThiTienDoDuAn(Model model) {
        model.addAttribute("danhSachDuAn", duAnService.layTatCaDuAn());
        return "project_progress";
    }
}
