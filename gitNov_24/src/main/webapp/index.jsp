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
	<p>�� ������Ʈ�� SQL 6���� ���������� �����α׷����� �ۼ��� ������Ʈ �Դϴ�.<br/>
	�޴��� ���������� ������ ���ϴ� �޴��� Ŭ���ϸ� �ش� ���������� �ش��� �������� �� �� �ֽ��ϴ�. 
	�α����� �ؾ� ���������� �ش��� �� �� ������ �α������� �ʾһ��¿��� �޴��� �Ǹ��ϸ� �α��� ȭ������ ��ȯ�˴ϴ�.</p>
<%	} %>

</section>


<footer>
<h4 align="center">���������� ������ ������Ʈ. Copyright 2024.</h4>
</footer>

</body>
</html>