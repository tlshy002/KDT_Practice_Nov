<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="nov13_bbs.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>



<section>
<%
	BoardDTO dto = (BoardDTO)request.getAttribute("DTO");
	String id = (String)session.getAttribute("ID");
%>
<h3 align="center">게시글 상세 정보</h3>
<div align="center">
<form action="boardModify.do" method="post">
	<table border="1">
		<tr><th>글제목</th>
			<td><input type="hidden" name="TITLE" value="<%= dto.getTitle() %>">
			<%= dto.getTitle() %></td>
		<tr><th>작성자</th><td><%= dto.getWriter() %></td>
		<tr><th>작성일</th><td><%= dto.getWrite_date() %></td>
		<tr><th>내 용</th>
			<td><textarea rows="4" cols="20" name="CONTENT"><%= dto.getContent() %></textarea></td>
			</td></tr>
			
		<!-- 수정,삭제 버튼을 로그인한 이후 보이도록하기 -->
<%
		if(id != null && id.equals(dto.getWriter())) { // id,계정이 있고 작성자와 같을때
%>
		<tr><td colspan="2">
			<input type="submit" value="수정" name="BTN"/>
			<input type="submit" value="삭제" name="BTN"/></td></tr>
<%
		}
%>
	</table>
</form>
</div>
</section>





</body>
</html>