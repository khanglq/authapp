package com.khanglq.authapp.controller;

import com.khanglq.authapp.utils.ApiResult;
import com.khanglq.authapp.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class AuthController {


	@RequestMapping(value = "/login")
	public String login() {

	log.info("now to login jsp page");
		return "login";
	}
	
	@RequestMapping(value="/denied")
	public String denied(){
		return "denied";
	}
	
	@RequestMapping(value="/userPage")
	public String userPage(){
		return "user";
	}
	
	@RequestMapping(value="/adminPage")
	public String adminPage(){
		return "user";
	}
	
	@RequestMapping(value="/dashboard")
	public String dashboard(Model model){
		model.addAttribute("roles", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return "dashboard";
	}

	@RequestMapping("/login/error")
	public void loginError(HttpServletRequest request, HttpServletResponse response) {
		AuthenticationException e = (AuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		log.error(e.getMessage());
		ResponseUtils.out(response, ApiResult.fail(e.getMessage()));
	}

	@RequestMapping(value="/mo-hinh")
	public String mohinh(Model model){
		model.addAttribute("roles", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return "mohinh";
	}

	@RequestMapping(value="/nhan-su")
	public String nhansu(Model model){
		model.addAttribute("roles", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return "nhansu";
	}

	@RequestMapping(value="/kpi")
	public String kpi(Model model){
		model.addAttribute("roles", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return "kpi";
	}
}
