<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, nov14_notice.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	ArrayList<NoticeDTO> list = (ArrayList<NoticeDTO>)request.getAttribute("NOTICES"); 
	int pageCount = (int)request.getAttribute("PAGES");
%>
<h3 align="center">공지사항 목록</h3>
<div align="center">
<table border="1">
	<tr><th>글번호</th><th>제 목</th><th>작성일</th><th>작성자</th></tr>
<%
	for(NoticeDTO dto : list){
%>	
	<tr><td><%= dto.getNum() %></td>
		<td><a href="noticeDetail.do?COUNT=<%= dto.getNum() %>"><%= dto.getTitle() %></a></td>
		<td><%= dto.getNotice_date() %></td>
		<td><%= dto.getWriter() %></td></tr>
<%	} %>
</table>
<% for(int i=1; i<=pageCount; i++) { %>
	<a href="noticeList.do?PAGE_NUM=<%= i %>"><%= i %></a>
<% } %>
</div>
</body>
</html>