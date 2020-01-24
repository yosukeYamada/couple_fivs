package com.example.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.LoginForm;
import com.example.form.UserForm;
import com.example.service.LoginService;


@Controller
@RequestMapping("")
public class LoginContoroller {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private HttpSession session;
	

	@RequestMapping("")
	public String index() {
		return "login";
	}
	
	@RequestMapping("/showRegisterUserForm")
	public String showRegisterUser() {
		return "register_user";
	}
	
	@RequestMapping("/registerUser")
	public String registerUser(UserForm userForm) {
		User user = new User();
		BeanUtils.copyProperties(userForm, user);
		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = localDate.atTime(0, 0);
		Timestamp timestamp = Timestamp.valueOf(localDateTime);
		user.setDate(timestamp);
		user.setStatus(0);
		loginService.insert(user);
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(LoginForm loginForm) {
		String userId = loginForm.getUserId();
		String password = loginForm.getPassword();
		Integer checkId = loginService.findByUserIdAndPassword(userId, password);
		if(checkId != null) {
			session.setAttribute("userId", checkId);
			return "main_menu";
		}else {
			return "login";
		}
		
	}
	
	
	
}
