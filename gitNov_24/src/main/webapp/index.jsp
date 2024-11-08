<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<header>
	<h2 align="center">My Home</h2>
</header>
<menu>
	<%@ include file="/menu_header.jsp" %>
</menu>


<section>
<% 
	String body = request.getParameter("BODY"); 
	if(body != null) {
%>
	<jsp:include page="<%= body %>" />
<%
	} else {
%>
	<p>이 프로젝트는 SQL 6장의 연습문제를 웹프로그램으로 작성한 프로젝트 입니다.<br/>
	메뉴에 연습문제가 있으며 원하는 메뉴를 클릭하면 해당 연습문제의 해답을 브라우저로 볼 수 있습니다. 
	로그인을 해야 연습문제의 해답을 볼 수 있으며 로그인하지 않았상태에서 메뉴를 실릭하면 로그인 화면으로 전환됩니다.</p>
<%	} %>

</section>


<footer>
<h4 align="center">명지전문대 웹개발 프로젝트. Copyright 2024.</h4>
</footer>

</body>
</html>