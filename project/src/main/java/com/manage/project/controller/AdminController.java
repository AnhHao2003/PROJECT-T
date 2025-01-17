package com.manage.project.controller;

import com.manage.project.model.DuAn;
import com.manage.project.model.NguoiDung;
import com.manage.project.model.NhacNho;
import com.manage.project.model.NhiemVu;
import com.manage.project.service.AdminService;
import com.manage.project.service.DuAnService;
import com.manage.project.service.NguoiDungService;
import com.manage.project.service.NhacNhoService;
import com.manage.project.service.NhiemVuService;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DuAnService duAnService;

    @Autowired
    private NguoiDungService nguoiDungService;
    
    @Autowired
    private AdminService adminService;
    

    @Autowired
    private NhacNhoService nhacNhoService; 
    
    @Autowired
    private NhiemVuService nhiemVuService;


    // Trang Dashboard của Admin
    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin_dashboard";
    }

    @GetMapping("/duan")
    public String manageDuAn(Model model) {
        model.addAttribute("duans", duAnService.layTatCaDuAn());
        return "admin_duan";  // This should be a valid view file name
    }

 // Thêm dự án
    @GetMapping("/duan/add")
    public String addDuAnForm() {
        return "add_duan";  // This should map to the add_duan.html view
    }
    
    

    @PostMapping("/duan/add")
    public String addDuAn(@ModelAttribute DuAn duAn) {
        // Call the service to save the project
        duAnService.themDuAn(duAn);
        return "redirect:/admin/duan"; // Redirect to the project management page after saving
    }
    


    // Cập nhật dự án
    @GetMapping("/duan/edit/{maDuAn}")
    public String editDuAnForm(@PathVariable Integer maDuAn, Model model) {
        DuAn duAn = duAnService.layTatCaDuAn().stream()
                .filter(d -> d.getMaDuAn().equals(maDuAn))
                .findFirst().orElse(null);
        model.addAttribute("duAn", duAn);
        return "edit_duan";
    }

    @PostMapping("/duan/edit")
    public String updateDuAn(@ModelAttribute DuAn duAn) {
        // Kiểm tra và gán giá trị mặc định nếu tiến độ không có giá trị
        if (duAn.getTienDo() == null || duAn.getTienDo().isEmpty()) {
            duAn.setTienDo("Chưa bắt đầu");
        }
        duAnService.capNhatDuAn(duAn); // Cập nhật dự án
        return "redirect:/admin/duan";  // Quay lại danh sách dự án
    }



    // Xóa dự án
    @GetMapping("/duan/delete/{maDuAn}")
    public String deleteDuAn(@PathVariable Integer maDuAn) {
        duAnService.xoaDuAn(maDuAn);
        return "redirect:/admin/duan";
    }

    // Quản lý người dùng
    @GetMapping("/users")
    public String manageUsers(Model model) {
        // Fetch users with the role "user"
        model.addAttribute("users", nguoiDungService.getNguoiDungByRole("user"));
        return "manage_users";  // Ensure the path matches the location of the template
    }
    
 // Đường dẫn đến trang hồ sơ admin
    @GetMapping("/profile")
    public String adminProfile(Model model) {
        // Lấy thông tin người dùng hiện tại từ SecurityContextHolder
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // Dùng username để lấy thông tin admin
        NguoiDung admin = adminService.getAdminByUsername(currentUsername);
        model.addAttribute("admin", admin);

        return "admin_profile";  // Trả về view admin_profile.html
    }
    
    @GetMapping("/duan/{maDuAn}")
    public String viewDuAnDetail(@PathVariable Integer maDuAn, Model model) {
        DuAn duAn = duAnService.layDuAnTheoMa(maDuAn);
        if (duAn == null) {
            return "redirect:/admin/duan";  // Nếu không tìm thấy, quay lại danh sách
        }
        model.addAttribute("duAn", duAn);
        return "duan_detail";  // Tên view phải khớp với file tại src/main/resources/templates/
    }
    
 // Hiển thị form sửa thông tin người dùng
    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id); // Lấy thông tin người dùng qua ID
        if (nguoiDung == null) {
            return "redirect:/admin/users"; // Nếu không tìm thấy, quay lại danh sách
        }
        model.addAttribute("user", nguoiDung);
        return "edit_user"; // View hiển thị form sửa thông tin người dùng
    }

    // Xử lý chỉnh sửa người dùng
    @PostMapping("/users/edit")
    public String updateUser(@ModelAttribute NguoiDung nguoiDung) {
        nguoiDungService.updateNguoiDung(nguoiDung); // Gọi service để cập nhật người dùng
        return "redirect:/admin/users"; // Quay lại danh sách người dùng sau khi sửa
    }

    // Xóa người dùng
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        nguoiDungService.deleteNguoiDungById(id); // Gọi service để xóa người dùng
        return "redirect:/admin/users"; // Quay lại danh sách người dùng sau khi xóa
    }
    
    @PostMapping("/assign-reminder/{userId}")
    public String assignReminderToUser(@PathVariable Integer userId, @RequestParam String noiDung, @RequestParam LocalDate ngayNhac) {
        // Tạo nhắc nhở mới
        NhacNho nhacNho = new NhacNho();
        nhacNho.setMaNguoiDung(userId);
        nhacNho.setNoiDung(noiDung);
        nhacNho.setNgayNhac(ngayNhac);

        // Lưu nhắc nhở
        nhacNhoService.saveNhacNho(nhacNho);
        
        return "redirect:/admin/users";  // Sau khi giao nhắc nhở, quay lại danh sách người dùng
    }
	
  
}
