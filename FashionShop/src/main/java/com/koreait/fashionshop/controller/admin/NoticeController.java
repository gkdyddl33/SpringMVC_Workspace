package com.koreait.fashionshop.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.exception.NoticeException;
import com.koreait.fashionshop.model.domain.Notice;
import com.koreait.fashionshop.model.product.repository.NoticeDAO;
import com.koreait.fashionshop.model.product.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	// 공지사항 폼
	@RequestMapping(value="/admin/notice/registform")
	public String noticeForm() {
		return "admin/notice/regist_form";
	}
	
	// 공지사항 목록
	@RequestMapping(value="/admin/notice/list",method = RequestMethod.GET)
	public ModelAndView getNoticeList() {
		ModelAndView mav = new ModelAndView("admin/notice/notice_list");
		List noticeList = noticeService	.selectAll();
		mav.addObject("noticeList", noticeList);
		return mav;
	}
	
	// 공지사항 등록 요청
	@RequestMapping(value="/admin/notice/list",method = RequestMethod.POST)
	public String regist(Notice notice) {
		noticeService.insert(notice);
		return "redirect:/admin/notice/list";
	}
	
	// 공지사항 상세보기
	@RequestMapping(value="/admin/notice/detail",method = RequestMethod.GET)
	public ModelAndView select(int notice_id) {
		Notice notice = noticeService.select(notice_id);
		
		ModelAndView mav = new ModelAndView("/admin/notice/detail");
		mav.addObject("notice", notice);
		return mav;
	}
	
	// 공지사항 글 수정하기
	@RequestMapping(value="/admin/notice/edit",method = RequestMethod.POST)
	public ModelAndView update(Notice notice) {
		ModelAndView mav = new ModelAndView();
				
		noticeService.update(notice);		
		mav.setViewName("redirect:/admin/notice/detail?notice_id="+notice.getNotice_id());
				
		return mav;
	}
	// 공지사항 글 삭제하기
	public String delete(int notice_id) {
		noticeService.delete(notice_id);					
		return "redirect:/admin/notice/list";
	}
	
	// 예외처리 등록
	@ExceptionHandler(NoticeException.class)
	@ResponseBody
	public String handleException(NoticeException e) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"result\":0");
		sb.append("\"msg\":\""+e.getMessage()+"\"");
		sb.append("}");
		return sb.toString();
	}
}
