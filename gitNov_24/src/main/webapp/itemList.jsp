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
<h3 align="center">��ǰ ���</h3>
<div align="center">
	<table border="1">
	<tr><th>��ǰ�ڵ�</th><th>��ǰ�̸�</th><th>��ǰ����</th><th>������</th><th>�����</th></tr>
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