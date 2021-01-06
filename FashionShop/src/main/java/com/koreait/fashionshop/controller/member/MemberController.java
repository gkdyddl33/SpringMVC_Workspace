package com.koreait.fashionshop.controller.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.exception.MailSendException;
import com.koreait.fashionshop.exception.MemberRegistException;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.member.service.MemberService;
import com.koreait.fashionshop.model.product.service.ProductService;
import com.koreait.fashionshop.model.product.service.TopCategoryServiceImpl;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private MemberService memberService;
	@Autowired
	private TopCategoryServiceImpl topCategoryService;
	@Autowired
	private ProductService productService;
	
	// 회원가입폼 요청
	@RequestMapping(value="/shop/member/registForm",method=RequestMethod.GET)
	 public ModelAndView getRegistForm() {
	      ModelAndView mav=new ModelAndView("shop/member/signup");
	      List topList=topCategoryService.selectAll();
	      mav.addObject("topList", topList);
	      return mav;
	   }
	
	// 회원가입 요청 처리(가입버튼)
	@RequestMapping(value="/shop/member/regist", method = RequestMethod.POST,produces = "text/html;charset=utf-8")
	@ResponseBody
	public String regist(Member member) {
		logger.debug("아이디 "+member.getUser_id());
		logger.debug("이름 "+member.getName());
		logger.debug("비번 "+member.getPassword());
		logger.debug("이메일ID "+member.getEmail_id());
		logger.debug("이메일SERVER"+member.getEmail_server());
		logger.debug("우편번호"+member.getZipcode());
		logger.debug("주소 "+member.getAddr());
		
		memberService.regist(member); 		// 부탁만.. service에서 일을 전문적으로 한다.
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(" \"result\":1, ");
		sb.append(" \"msg\":\"회원가입 성공\"");		
		sb.append("}");		
		return "redirect:/";		// 쇼핑몰 메인..
	}
	
	// 예외 핸들러 2가지 처리
	@ExceptionHandler(MailSendException.class)
	@ResponseBody
	public String handleException(MemberRegistException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(" \"result\":0, ");
		sb.append(" \"msg\":\""+e.getMessage()+"\"");		
		sb.append("}");		
		return sb.toString();
	}
}
