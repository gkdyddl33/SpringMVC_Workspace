package com.koreait.mvclegacy.controller.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.mvclegacy.exception.DMLException;
import com.koreait.mvclegacy.model.domain.Notice;
import com.koreait.mvclegacy.model.notice.MybatisNoticeDAO;
import com.koreait.mvclegacy.model.notice.NoticeService;

@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	@Autowired
	private NoticeService noticeService;
	
	// 2) 글쓰기 폼 요청
	@RequestMapping("/notice/regist_form")
	public String getRegistForm() {
		return "notice/regist_form";
	}
	
	// 3) 글등록 요청 처리
	@RequestMapping(value="/notice/regist",method = RequestMethod.POST)
	public String regist(Notice notice) {
		// 스프링에서는 vo의 멤버변수와 html의 파라미터명이 같으면 자동으로 데이터값이 vo에 채워진다.
		noticeService.insert(notice);
		return "redirect:/client/notice/list";
	}
	
	// 1) 모든 글 가져오기
	@RequestMapping(value="/notice/list", method=RequestMethod.GET)
	public ModelAndView selectAll() {
		logger.info("글목록 메서드 호출");
		ModelAndView mav = new ModelAndView("notice/list");
		List noticeList = noticeService.selectAll();
		mav.addObject("noticeList", noticeList);
		return mav;
	}
	
	// 4) 한건 가져오기
	@RequestMapping(value="/notice/detail",method=RequestMethod.GET)
	public ModelAndView select(int notice_id) {
		Notice notice = noticeService.select(notice_id);
		
		ModelAndView mav = new ModelAndView("notice/detail");
		mav.addObject("notice", notice);
		return mav;
	}
	
	// 5) 글수정 요청 처리
	@RequestMapping(value="/notice/edit",method=RequestMethod.POST)
	public ModelAndView update(Notice notice) {
		ModelAndView mav = new ModelAndView();
		
		try {
			noticeService.update(notice);
			//mav.addObject("msg","등록성공");
			mav.setViewName("redirect:/client/notice/detail?notice_id="+notice.getNotice_id());
		} catch (DMLException e) {
			mav.addObject("msg", e.getMessage());		// 심어놓은 메시지를 끄집어 낼 수 있음..
			mav.setViewName("message/result");
			e.printStackTrace();
		}			
		return mav;
	}
	
	// 6) 글삭제 요청 처리
	@RequestMapping(value="/notice/del",method=RequestMethod.POST)
	public String delete(int notice_id) {
		noticeService.delete(notice_id);					
		return "redirect:/client/notice/list";
	}
	
	// ★ 스프링의 컨트롤러의 요청 처리 메서드 중 어느 하나라도 예외가 발생하면, 그 예외를 처리할 수 있는 별도의 메서드가 지원된다. == 코드 중복 방지를 위해..
	// ★ 에러를 감지하는 이벤트 핸들러 - 어노테이션에 명시한 예외만 감지하여 처리
	@ExceptionHandler(DMLException.class)
	public ModelAndView handleException(DMLException e) {// 에러원인이 매개변수로 들어온다..
		ModelAndView mav = new ModelAndView();
		// 1) 어떤내용을 담을지? 에러 메시지를 담자. = dao에 메시지를 이미 담아놓음 - 끄집어내자
		 mav.addObject("msg", e.getMessage());
		
		// 2) 어느 페이지를 보여줄지? message/result.jsp
		mav.setViewName("message/result");
		return mav;
	}
}
