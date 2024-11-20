<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">계정 중복 확인</h2>
	<% String id = request.getParameter("ID"); %>
	<form action="">
	계 정 : <input type="text" name="ID" value="<%= id %>"/>
		<input type="submit" value="중복검사"/>
	</form>
	<%= id %>는 사용 가능합니다. <input type="button" value="사용" onclick="idOk()"/>
	<script type="text/javascript">
	function idOk(){
		self.close();
	}
	</script>
</body>
</html>