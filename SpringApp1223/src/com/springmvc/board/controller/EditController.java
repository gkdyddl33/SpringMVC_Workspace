package com.springmvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;
import com.model2.domain.Board;

public class EditController implements Controller{
	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// -- update
		String board_id = request.getParameter("board_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Board board = new Board();
		board.setBoard_id(Integer.parseInt(board_id));
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		int result = boardDAO.update(board); 		
		
		ModelAndView mav = new ModelAndView();
		//mav.addObject("board", board);
		//mav.addObject("result", result);
		
		mav.setViewName("/board/edit"); 
		if(result==1) {
			// 컨트롤러를 한번 거쳐서 갈 경우..
			mav.setViewName("redirect:/board/detail?board_id="+board.getBoard_id());	// 성공
			// 서버의 jsp로 포워드를 원하는 경우
			//mav.setViewName("board/detail");
		}else {
			mav.addObject("msg", "수정실패");
			mav.setViewName("error/result");	// 실패
		}
		return mav;
	}

}

