package com.zerob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String auth() {
		return "auth/auth";
	}
	
	@GetMapping("/main")
	public String main() {
		return "main/main";
	}
}
