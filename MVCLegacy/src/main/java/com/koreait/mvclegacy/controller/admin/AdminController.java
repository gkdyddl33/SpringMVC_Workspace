package com.koreait.mvclegacy.controller.admin;

import  org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.mvclegacy.model.member.MemberService;

/*
	관리자기능과 관련된 모든 요청을 처리하는 컨트롤러
*/
@Controller
@RequestMapping(value="/")
public class AdminController {
	// ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/main",method=RequestMethod.GET)
	public String test() {
		logger.info("test() 메서드 호출");
		logger.info("어드민에서 사용중인 MemberService의 주소값 "+memberService);
		memberService.regist();
		return null;
	}
}
