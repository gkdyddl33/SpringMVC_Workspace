package com.koreait.mylegacy.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.koreait.mylegacy.exception.RegistException;
=======
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
import com.koreait.mylegacy.model.dao.MybatisDeptDAO;
import com.koreait.mylegacy.model.dao.MybatisEmpDAO;
import com.koreait.mylegacy.model.domain.Emp;
import com.koreait.mylegacy.mybatis.config.MybatisConfigManager;
@Service
public class MybatisEmpService {
	@Autowired
	private MybatisConfigManager manager;
	@Autowired
	private MybatisEmpDAO mybatisEmpDAO;
	@Autowired
	private MybatisDeptDAO mybatisDeptDAO;
	
	// 모든 사원 레코드 가져오기
	public List selectAll() {
		List list = null;
		SqlSession sqlSession =  manager.getSqlSession();
		mybatisEmpDAO.setSqlSession(sqlSession);
		list=mybatisEmpDAO.selectAll();
		manager.close(sqlSession);
		return list;
	}	
	
	// 사원등록(부서등록+사원등록 = 2개의 업무로 구성된 트랜잭션 상황)
	public int regist(Emp emp) {
		int result = 0;
		// 일 시키기 전에 SqlSession을 배분!
		SqlSession sqlSession =  manager.getSqlSession();	// default false <commit>		
		mybatisEmpDAO.setSqlSession(sqlSession);
		mybatisDeptDAO.setSqlSession(sqlSession);
		
<<<<<<< HEAD
		try {
			// 아래의 두 DML 메서드를 대상으로 커밋(commit,rollback)해야 할 코드 라인은?
			mybatisEmpDAO.insert(emp);		// 트랜잭션 대상들..
			mybatisDeptDAO.insert(emp.getDept());
			sqlSession.commit();
			result=1;
		} catch (RegistException e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
=======
		mybatisEmpDAO.insert(emp);		// 트랜잭션 대상들..
		mybatisDeptDAO.insert(emp.getDept());
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
		
		manager.close(sqlSession);
		return result;
	}
}
