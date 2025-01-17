package com.manage.project.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class testPass {
	public static void main(String[] args) {
		System.out.print(new BCryptPasswordEncoder().encode("1234"));
//		System.out.print(new BCryptPasswordEncoder().encode("manager123"));
	}
}
 