<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<script>
function regist() {
	var form = document.querySelector("form");
	form.action="/emp/regist";
	form.method="post";
	form.submit();
}
</script>
</head>
<body>
	[입사등록 양식]
	<form action="">
		<input type="text" name="deptno" value="50">
		<input type="text" name="dname" value="MARKETTING">
		<input type="text" name="loc" value="KOREA">
		
		<input type="text" name="empno" value="7777">
		<input type="text" name="ename" value="batman">
		<input type="text" name="sal" value="8900">
		<button type="button" onclick="regist()">사원등록</button>
	</form>
</body>
</html>