<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, nov13_bbs.*" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<section>
<%
	ArrayList<BoardDTO> al = (ArrayList<BoardDTO>)request.getAttribute("BOARD");
	int pageCount = (int)request.getAttribute("PAGES");
%>
	<h3 align="center">게시글 목록</h3>
<div align="center">
	<table border="1">
		<tr><th>작성자</th><th>제 목</th><th>작성일</th></tr>
		<%
		for(BoardDTO dto : al) {
		%>
		<tr><td><%= dto.getWriter() %></td>
			<!-- 앵커를 통한 서블릿 호출은 doGet() 메서드가 실행됨 -->
			<td><a href="boardDetail.do?TITLE=<%= dto.getTitle() %>"><%= dto.getTitle() %></a></td>
			<td><%= dto.getWrite_date() %> </td></tr>
		<%
		}
		%>
	</table>
	
<%
	for(int i=1; i <= pageCount; i++){
%>
	<a href=""><%= i %></a>
<%	} %>

</div>



</section>


</body>
</html>