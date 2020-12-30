package com.springmvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;
import com.model2.domain.Board;

public class DeleteController implements Controller{
	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 3단계 : 일을 시킨다 = 삭제하기 한건의 게시물..
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		int result =boardDAO.delete(board_id);
	    
 		ModelAndView mav = new ModelAndView();
		//mav.addObject("result", result);
		if(result==1) {
			mav.setViewName("redirect:/board/list");	// 성공
		}else {
			mav.addObject("msg", "삭제실패");
			mav.setViewName("error/result");	// 실패
		}
		return mav;
	}

}