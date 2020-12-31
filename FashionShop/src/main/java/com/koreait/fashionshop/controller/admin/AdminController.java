package com.koreait.fashionshop.controller.admin;

import org.springframework.stereotype.Controller;
// 관리자..
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class AdminController {
	// 관리자모드 메인 요청..및 인증처리..
	
	@RequestMapping(value="/admin")
	public String adminMain() {
		return "admin/main";
	}
}
