package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminsController {
	@GetMapping("/admin")
	public String adminPage() {
		return "admin";
	}

	@GetMapping("/user")
	public String userPage() {
		return "user";
	}

	@GetMapping("/")
	public String loginPage(ModelMap model) {
		return "login";
	}
}
