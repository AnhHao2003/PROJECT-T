package com.manage.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.manage.project.service.DuAnService;
import com.manage.project.model.DuAn;
import com.manage.project.model.NguoiDung;
import com.manage.project.model.NhacNho;
import com.manage.project.service.NguoiDungService;
import com.manage.project.service.NhacNhoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
	
	   @Autowired
	    private NguoiDungService nguoiDungService;
	   
	   @Autowired
	   private NhacNhoService nhacNhoService;

	
	@GetMapping("/login")
	public String showLoginForm() {
	    return "login";
	}
	
	@GetMapping("/home")
	public String Home(Model model) {
		return "home"; 
	}
	
//	@GetMapping("/logout")
//	public String Logout(HttpServletRequest request, HttpServletResponse response) {
//		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
//	    logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
//	    return "redirect:/login";
//	}
//	
	@PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout"; // Chuyển hướng về trang đăng nhập
    }
	
	 @GetMapping("/register")
	    public String showRegisterForm() {
	        return "register"; // Trả về trang đăng ký
	    }

	 @PostMapping("/register")
	    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
	        nguoiDungService.createUser(username, password, role); // Gọi phương thức tạo người dùng
	        return "redirect:/login"; // Chuyển hướng về trang đăng nhập
	    }
	 
	 @GetMapping("/user-profile")
	    public String viewUserProfile(Authentication authentication, Model model) {
	        // Lấy tên đăng nhập từ thông tin xác thực
	        String username = authentication.getName();
	        
	        // Tìm thông tin người dùng từ cơ sở dữ liệu
	        NguoiDung nguoiDung = nguoiDungService.getNguoiDung(username);
	        
	        // Truyền thông tin người dùng vào model
	        model.addAttribute("tenDangNhap", nguoiDung.getTenDangNhap());
	        model.addAttribute("vaiTro", nguoiDung.getVaiTro());
	        
	        // Chuyển đổi maNguoiDung từ long sang Integer
	        Integer userId = (int) nguoiDung.getMaNguoiDung(); // Chuyển đổi từ long sang Integer
	        
	        return "user-profile"; // Trả về trang hiển thị thông tin cá nhân
	    }
	 
	 
}
