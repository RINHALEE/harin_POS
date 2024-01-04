package com.harinpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harinpos.user.*;

@Controller
@ComponentScan("com.harinpos.user")
public class LoginController {
	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 로그인 페이지를 표시하는 메서드
	@GetMapping("/login")
	public String showLoginForm() {
		return "login"; // "login" 뷰 반환
	}

	// 로그인 처리를 수행하는 메서드
	@PostMapping("/main")
	public String processLogin(@RequestParam("userId") String userId, @RequestParam("password") String password,
			Model model) {
		// 사용자 정보를 DB에서 조회
		User user = userDao.selectById(userId);

		if (user != null && user.getPassword().equals(password)) {
			// 사용자 정보가 존재하고 비밀번호가 일치하면 로그인 성공 처리
			// "main" 뷰 반환하여 메인 페이지로 이동
			return "main";
		} else {
			// 사용자 정보가 존재하지 않거나 비밀번호가 일치하지 않으면 로그인 실패 처리
			model.addAttribute("message", "사용자 정보가 존재하지 않거나, 아이디 또는 비밀번호가 일치하지 않습니다.");

			// "login" 뷰 반환하여 로그인 페이지에 실패 메시지를 표시
			return "login";
		}
	}

	// 로그아웃 처리를 수행하는 메서드
	@GetMapping("/logout")
	public String logout() {
		// 로그아웃 후 로그인 페이지로 이동
		return "redirect:/login";
	}
}
