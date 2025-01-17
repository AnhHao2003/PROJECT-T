package com.manage.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.manage.project.model.NhacNho;
import com.manage.project.service.NhacNhoService;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/admin/nhacnho")
public class NhacNhoController {

    private final NhacNhoService nhacNhoService;  // Declare service as a final field

    @Autowired
    public NhacNhoController(NhacNhoService nhacNhoService) {
        this.nhacNhoService = nhacNhoService;
    }

    // Show the form to create a new reminder
    @GetMapping("/create")
    public String showCreateForm() {
        return "create_reminder";  // Đường dẫn đến file view chính xác
    }

    // Xử lý lưu nhắc nhở vào DB
    @PostMapping("/add")
    public String saveReminder(@ModelAttribute NhacNho nhacNho) {
        nhacNhoService.saveNhacNho(nhacNho);  // Gọi dịch vụ để lưu nhắc nhở
        return "redirect:/admin/nhacnho";  // Quay lại danh sách nhắc nhở
    }

    // Hành động hiển thị danh sách nhắc nhở
    @GetMapping
    public String showAllReminders(Model model) {
        List<NhacNho> allNhacNho = nhacNhoService.getAllNhacNho();  // Lấy danh sách nhắc nhở từ dịch vụ
        model.addAttribute("allNhacNho", allNhacNho);  // Thêm thuộc tính vào model
        return "admin_nhacnho";  // Trả về view hiển thị danh sách nhắc nhở
    }
}
