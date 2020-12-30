package com.koreait.mylegacy.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.koreait.mylegacy.exception.RegistException;
=======
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
import com.koreait.mylegacy.model.domain.Emp;
@Repository
public class MybatisEmpDAO {
	private SqlSession sqlSession=null;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 목록 가져오기
	public List selectAll() {
		List list = null;
		list = sqlSession.selectList("Emp.selectAll");
		return list;
	}
	// 1건 등록
<<<<<<< HEAD
	public int insert(Emp emp) throws RegistException{// 예외를 반환하는 메서드를 만들것이다..왜? service에서 트랜잭션을 마무리 해야 하므로..
																					// try가 아닌 런타임 exception을 만들어줘야 한다..
		int result = 0;		
		result = sqlSession.insert("Emp.insert",emp);	// emp안에 dept가 포함
		if(result==0) {
			throw new RegistException("사원등록에 실패하였습니다.");
		}
=======
	public int insert(Emp emp) {
		int result = 0;
		
		result = sqlSession.insert("Emp.insert",emp);	// emp안에 dept가 포함
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
		return result;
	}	
}
