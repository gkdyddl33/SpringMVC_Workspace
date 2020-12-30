package com.koreait.mylegacy.comtroller.emp;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.mylegacy.model.dao.JdbcDeptDAO;
import com.koreait.mylegacy.model.dao.JdbcEmpDAO;
import com.koreait.mylegacy.model.domain.Dept;
import com.koreait.mylegacy.model.domain.Emp;
<<<<<<< HEAD
import com.koreait.mylegacy.model.service.JdbcEmpService;
import com.koreait.mylegacy.model.service.MybatisEmpService;
=======
import com.koreait.mylegacy.model.service.EmpService;
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936

// component-scan 대상이 되려면 어노테이션을 지정해야 한다.
@Controller
public class EmpController {// DAO의 존재를 알면은 안된다.. Service가 그 역할을 해준다..(Model)
											 // ex. 버거킹 주방에서 남자인지 여자인지 몇살인지 사람의 존재를 알 수 없듯이..
	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);		//로고 출력 객체가 올라온다. 정보를 찍을 수 있음..
	@Autowired
<<<<<<< HEAD
	private JdbcEmpService empService;
	@Autowired
	private MybatisEmpService mybatisEmpService;
	
	// (2) 사원등록 폼 요청
	@RequestMapping("/emp/regist_form")
=======
	private EmpService empService;
	
	// (2) 사원등록 폼 요청
	@RequestMapping("/emp/registform")
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
	public String registForm() {
		//저장할 것이 없고, 그냥 뷰만 반환하고 싶을 때는 String 도 가능..
		return "emp/regist_form";
	}
	
	// (1) 사용등록 요청을 처리하는 메서드 -> regist_form 에 사원등록 버튼 -> regist로 넘긴다.
	@RequestMapping(value="emp/regist", method=RequestMethod.POST)
	public String regist(Dept dept, Emp emp) {
		// 파라미터 받아와 출력해보기
		// logger level 때문에 출력이 안됨.. 
		// log4j라는 라이브러리는 우리가 개발 시 디버그 목적으로 사용하는 콘솔 출력보다도 훨씬 다양하며 복잡한 기능을 지원하는 로그 라이브러리이다.
		// 특히 출력 로그 레벨이라는 기능을 둬서, 개발자가 원하는 레벨을 선택하여 출력을 제어할 수 있도록 지원한다..		
		// 주석으로 막지 않고 파악하는 방법..
		// ALL(모든로깅) < DEBUG(확인) < INFO(강조) <WARN(경고) < ERROR(오류) < FATAL(심각에러) < OFF(로딩해제)		
		logger.debug(""+dept.getDeptno());
		logger.debug(dept.getDname());
		logger.debug(dept.getLoc());
		
		logger.debug(""+emp.getEmpno());
		logger.debug(emp.getEname());
		logger.debug(""+emp.getSal());
		//System.out.println(emp.getDept().getDeptno());
		
		// DB 등록! 파라미터를 받아와서..	
		// 부서등록과 사원등록이라는 두 개의 업무가 모두 성공되어야 전체를 성공으로 간주하는 트랜잭션 상황!
		// -- 서비스에게 등록 요청(사원,부서)가 다 등록 되어야 하므로..
		 emp.setDept(dept);	// emp와 부서를 합체!!
<<<<<<< HEAD
		 int result = mybatisEmpService.regist(emp);
		 logger.debug("등록결과 "+result);
		return "redirect:/emp/list";
=======
		// int result = empService.regist(emp);
		 //logger.debug("등록결과 "+result);
		return null;
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
	}
	
	// (3) 사원목록
	@RequestMapping(value="/emp/list", method=RequestMethod.GET)
	public ModelAndView selectAll() {
<<<<<<< HEAD
		ModelAndView mav =new ModelAndView();		
		// 일 시킨다.
		List empList = mybatisEmpService.selectAll();
		logger.debug("empList : "+empList);
		// 저장
		mav.addObject("empList", empList);		
		mav.setViewName("emp/list");
=======
		ModelAndView mav =new ModelAndView();
		
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
		return mav;
	}
	
}
