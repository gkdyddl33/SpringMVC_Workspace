package com.koreait.mylegacy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.mylegacy.model.domain.Dept;
import com.koreait.mylegacy.model.pool.PoolManager;

// Dept 테이블에 대한 CRUD를 수행하되, jdbc기반으로 코드를 작성
@Repository
public class JdbcDeptDAO {// dao에 사용되는 저장소 어노테이션.. 
	/*
	 * @Autowired private PoolManager poolManager; // DI 주입..oracle 연결..
	 */
	private Connection con;
	public void setCon(Connection con) {
		this.con = con;
	}
	public List selectAll() {//모두가져오기
		List list = null;
		
		return list;
	}
	
	public Dept select(int deptno) {// 한건가져오기
		Dept dept = null;
		
		return dept;
	}
	
	public int regist(Dept dept) throws SQLException{// 등록	-> 예외처리 전가..( service의 예외처리로 전가됨)
		int result = 0;
		// default setAutoCommit(true)
		PreparedStatement pstmt = null;
		String sql = "insert into dept(deptno,dname,loc) values(?,?,?)";
	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptno());
			pstmt.setString(2, dept.getDname());
			pstmt.setString(3, dept.getLoc());
			result = pstmt.executeUpdate();
			
		}  finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;		
	}
	
	public int update(Dept dept) {//수정
		int result = 0;
		return result;
	}
	
	public int delete(int deptno) {//삭제
		int result = 0;
		return result;
	}
}
