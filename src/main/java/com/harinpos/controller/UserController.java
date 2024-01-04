package com.harinpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.harinpos.user.*;

@Controller
@ComponentScan("com.harinpos.user")
public class UserController {
	@Autowired
	private UserDao userDao;

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 사용자 계정 페이지를 표시하는 메서드
	@GetMapping("/account")
	public String showAccountPage(Model model) {
		// 사용자 데이터를 조회하는 서비스 클래스의 메서드를 호출하여 사용자 데이터를 가져옴
		List<User> users = userDao.selectAll();

		// 모델에 사용자 데이터를 추가하여 뷰에서 사용할 수 있도록 함
		model.addAttribute("users", users);

		// 사용자 데이터를 표시하는 뷰(JSP 파일 등)로 이동
		return null;
	}

	// 사용자 등록 페이지를 표시하는 메서드
	@PostMapping("/registerUser")
	public String registerUser() {
		return "account";
	}

	// 회원 가입 폼을 표시하는 메서드
	@RequestMapping(value = "/join", method = { RequestMethod.POST, RequestMethod.GET })
	public String showJoinForm(Model model) {
		model.addAttribute("registerRequest", new RegisterRequest());
		return "join";
	}

	// 회원 가입 폼을 처리하는 메서드
	@PostMapping("/FinishJoin")
	public String processJoinForm(@ModelAttribute("registerRequest") RegisterRequest regReq, BindingResult result,
			Model model) {
		if (!regReq.getPassword().equals(regReq.getConfirmPassword())) {
			return "join";
		}

		try {
			userService.regist(regReq);
			return "FinishJoin";
		} catch (DuplicateMemberException ex) {
			return "FinishJoin";
		}
	}

	// 사용자 삭제 메서드
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("id") String id) {
		userDao.deleteUser(id);
		return "redirect:/account";
	}

	// 사용자 수정 페이지를 표시하는 메서드
	@GetMapping("/updateUserF")
	public String updateUserF(@RequestParam("id") String id, Model model) {
		model.addAttribute("id", id);
		return "updateUser";
	}

	// 사용자 정보를 수정하는 메서드
	@PostMapping("/updateUser")
	public String updateUser(@RequestParam("id") String id, @RequestParam("password") String password,
			@RequestParam("position") String position, @RequestParam("name") String name) {
		// 상품 수정 로직
		userDao.updateUser(id, password, position, name);

		// 수정 후 리다이렉트 또는 다른 처리 수행
		return "redirect:/account";
	}

}
