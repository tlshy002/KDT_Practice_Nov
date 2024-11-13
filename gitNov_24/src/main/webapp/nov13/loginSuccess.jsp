<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	String id = (String)session.getAttribute("ID"); //세션에 저장된 데이터(계정)을 가져옴
%>
<h3 align="center">로그인 되었습니다. 환영합니다. <%= id %>님 </h3>


</body>
</html>