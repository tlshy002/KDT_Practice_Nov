<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="nov14_notice.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	NoticeDTO dto = (NoticeDTO)request.getAttribute("NOTICE");
	String id = (String)session.getAttribute("ID"); //세션에서 "ID"로 계정 찾기
%>
<h3 align="center">공지글 상세보기</h3>

<form action="noticeModify.do" method="post" onsubmit="return check()"> <!-- 내용때문에 post 사용해야함, onsubmit이 ture이면 action을 실행함-->
<input type="hidden" name="NO" value="<%= dto.getNum() %>"/>

<table>
		<tr><th>글번호</th><td><%= dto.getNum() %></td></tr>
		<tr><th>제 목</th><td><input type="text" name="TITLE" value="<%= dto.getTitle() %>"></td></tr>
		<tr><th>작성자</th><td><%= dto.getWriter() %></td></tr>
		<tr><th>작성일</th><td><%= dto.getNotice_date() %></td></tr>
		<tr><th>내 용</th><td><textarea rows="5" cols="40" name="CONTENT"><%= dto.getContent() %></textarea></td></tr>
<%	if(id != null && id.equals("admin")) { %>		
	<tr><td colspan="2" align="center">
		<input type="submit" value="수정" name="BTN"/>
		<input type="submit" value="삭제" name="BTN"/>
	</td></tr>
<% 	} %>

<script type="text/javascript">
function check() {
	if(! conform("정말로 해당 작업을 진행하시겠습니까?")) return false;
}
</script>
		
</table>
</form>
</body>
</html>