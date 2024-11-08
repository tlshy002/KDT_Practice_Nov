<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, nov08_item.*"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	ArrayList<ItemDTO> list = (ArrayList<ItemDTO>)request.getAttribute("ITEMS");
%>
<h3 align="center">상품 목록</h3>
<div align="center">
	<table border="1">
	<tr><th>상품코드</th><th>상품이름</th><th>상품가격</th><th>원산지</th><th>등록일</th></tr>
<%
	for(ItemDTO dto : list) {
%>
	<tr><td><%= dto.getCode() %></td><td><%= dto.getName() %></td>
		<td><%= dto.getPrice() %></td><td><%= dto.getOrigin() %></td>
		<td><%= dto.getReg_date() %></td></tr>
<%
	}
%>
	</table>
</div>


</body>
</html>