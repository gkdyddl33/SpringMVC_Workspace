package com.koreait.mylegacy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
<<<<<<< HEAD
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
=======
import java.sql.SQLException;
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.mylegacy.model.domain.Dept;
import com.koreait.mylegacy.model.domain.Emp;
import com.koreait.mylegacy.model.pool.PoolManager;

// Dept 테이블에 대한 CRUD를 수행하되, jdbc기반으로 코드를 작성
@Repository
public class JdbcEmpDAO {// dao에 사용되는 저장소 어노테이션.. 
	/*
	 * @Autowired private PoolManager poolManager; // DI 주입..oracle 연결..
	 */
	
	private Connection con;
	public void setCon(Connection con) {
		this.con = con;
	}
	public List selectAll() {//모두가져오기
<<<<<<< HEAD
		List list = new ArrayList();	// empty 상태로 놓치 말고 메모리에 올려놓자
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb =new StringBuilder();
		sb.append("select d.deptno as deptno,dname,loc,emptno,ename,sal");
		sb.append(" from dept d, emp e");
		sb.append(" where d.deptno = e.deptno");
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			
			// 리스트에 채우기
			while(rs.next()) {
				Emp emp = new Emp();
				Dept dept = new Dept();
				
				// 부서정보
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
				// 사원정보
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setSal(rs.getInt("sal"));
				emp.setDept(dept); 		// 합체! 부서+사원
				
				list.add(emp);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
=======
		List list = null;
		
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
		return list;
	}
	
	public Emp select(int empno) {// 한건가져오기
		Emp emp = null;
		
		return emp;
	}
	
	public int regist(Emp emp) throws SQLException{// 등록
		int result = 0;
		PreparedStatement pstmt = null;
<<<<<<< HEAD
		String sql = "insert into emp(empno,ename,sal,deptno) values(?,?,?,?)";
=======
		String sql = "insert into e(empno,ename,sal,deptno) values(?,?,?,?)";
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setInt(3, emp.getSal());
			pstmt.setInt(4, emp.getDept().getDeptno());
			result = pstmt.executeUpdate();
			
		} finally {
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
	
	public int update(Emp emp) {//수정
		int result = 0;
		return result;
	}
	
	public int delete(int emptno) {//삭제
		int result = 0;
		return result;
	}
}
