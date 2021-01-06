package com.koreait.fashionshop.controller.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.model.product.service.TopCategoryService;

@Controller
public class MainController {
	@Autowired
	private TopCategoryService topCategoryService;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView main() {
		// 카테고리들 가져오기
		ModelAndView mav =new ModelAndView();
		
		List topList = topCategoryService.selectAll();
		mav.addObject("topList", topList);
		mav.setViewName("index");
		
		return mav;	// views 안에 들어가있으므로..
	}
}
