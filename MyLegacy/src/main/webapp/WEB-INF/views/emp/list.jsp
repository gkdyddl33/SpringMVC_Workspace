<<<<<<< HEAD
<%@page import="com.koreait.mylegacy.model.domain.Emp"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	List<Emp> empList = (List)request.getAttribute("empList");
%>
=======
<%@ page contentType="text/html; charset=utf-8"%>
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원목록</title>
</head>
<body>
	<table width="100%" border="1px">
		<tr>
			<td>부서명</td>
			<td>부서번호</td>
			<td>부서위치</td>
			<td>사원번호</td>
			<td>사원명</td>
			<td>급여</td>
		</tr>
<<<<<<< HEAD
		<%for(int i=0;i<empList.size();i++){ %>
		<%Emp emp = empList.get(i); %>
		<tr>
			<td><%=emp.getDept().getDeptno() %></td>
			<td><%=emp.getDept().getDname() %></td>
			<td><%=emp.getDept().getLoc() %></td>
			<td><%=emp.getEmpno() %></td>
			<td><%=emp.getEname() %></td>
			<td><%=emp.getSal() %></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="6">
				<button onclick="location.href='/emp/registForm';">사원등록</button>
			</td>
		</tr>
=======
>>>>>>> 3d5dfe31c6524cf338c2b422a5b8260b2e0d3936
	</table>
</body>
</html>