package com.koreait.mvclegacy.model.member;
/*
 	톰캣이 가동될 때 빈 xml을 읽어들여야 한다.. 모든 서블릿 간 데이터를 공유할 수 있어야 하고, 서블릿보다 먼저 태어나 있어야 한다..이 것을 만족하는 객체는?
 	jsp의 내장객체 중 어플리케이션의 전역적 정보를 가진 객체
 	ServletContext - session 보다 강함.. 톰캣을 재 가동해야 사라짐.. 
 	/ request - request~response 까지..생명력 살아있음
 	/ response
 	/ session 	
 	즉, 요청이 서블릿에 들어오기도 전에  무언가 작업할 수 있는가? yes~ javaEE 에 기술이 있다. 
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.koreait.mvclegacy.controller.admin.AdminController;
@Service
public class MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	public void regist() {
		logger.info("regist() 회원등록 메서드 호출");
	}
}
