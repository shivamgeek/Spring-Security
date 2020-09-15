package com.shivam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/myLoginPage")
	public String showLoginPage() {
		return "myLoginPage";
	}
	
	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders-meeting";
	}
	
	@GetMapping("/system")
	public String showSystem() {
		return "system-info";
	}
	
	@GetMapping("/my-access-denied")
	public String accessDeniedPage() {
		return "access-denied";
	}
	
	
}
