package com.koreait.mylegacy.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.koreait.mylegacy.exception.RegistException;
=======
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
import com.koreait.mylegacy.model.domain.Dept;
import com.koreait.mylegacy.model.domain.Emp;
@Repository
public class MybatisDeptDAO {
	private SqlSession sqlSession=null;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 1건 등록
<<<<<<< HEAD
	public int insert(Dept dept) throws RegistException{// 나를 호출한 사람에게 전가한다..
		int result = 0;		
		result = sqlSession.insert("Dept.insert",dept);	// emp안에 dept가 포함
		// 만일 부서등록이 실패하면, 여기서 억지로 예외를 발생시켜버리자!!
		if(result==0) {
			// 자바에서는 에러를 억지로 발생시켜주는 기능이 지원..
			// 에러가 났다? 그럼 DAO에 일을 시키고 있는 service에 전달을 하자..DAO에 하지 말구..
			throw new RegistException("부서등록에 실패하였습니다.");
		}
=======
	public int insert(Dept dept) {
		int result = 0;
		
		result = sqlSession.insert("Dept.insert",dept);	// emp안에 dept가 포함
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
		return result;
	}	
}
