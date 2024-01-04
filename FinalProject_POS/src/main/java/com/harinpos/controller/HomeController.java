package com.harinpos.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ComponentScan("com.harinpos.user")
public class HomeController {
	
	// 홈 페이지를 표시하는 메서드
	@GetMapping("/main")
	public String home() {
		return "main"; // "main" 뷰 반환
	}
	
}
