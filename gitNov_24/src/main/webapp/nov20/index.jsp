<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<style type="text/css">
table{ width: 90%; height: 90%; border: 1px solid blue; background-color: orange;
		border-collapse: collapse; margin-left: 20px; margin-right: 20px;
}
td.main { width: 30%; border: 1px solid green; }
#menu { margin-left: 10px; margin-top: 10px; margin-right: 10px; 
		background-color: lightgreen; width: 90%; border: 1px dashed red; }
#login { margin-left: 10px; margin-top: 10px; width: 90%; background-color: yellow;
		border: 1px dashed orange; }	
#content { background-color: wheat; }

</style>
</head>
<body>
<header>
	<div align="center"><img alt="" src="logo.gif"></div>
</header>
	
<section>
<%
	String body = request.getParameter("BODY");
	String id = (String)session.getAttribute("ID");
%>
	<table id="body">
		<tr>
			<td width="40%" height="300px" class="main">
				<div id="login">			
				<% if(id != null) { %>
					<jsp:include page="logout.jsp"/>
				<% } else { %>	
					<jsp:include page="login.jsp"/>
				<% } %>
				</div>
				<div id="menu">
					<a href="index.jsp">■ 홈으로</a><br/>
					<a href="boardList.do">■ 게시글 보기</a><br>
					<a href="bbsLoginCheck.do">■ 게시글 쓰기</a><br>
					<a href="">■ 상품 보기</a><br>
					<a href="itemLoginCheck.do">■ 상품 등록</a><br>
					<a href="index.jsp?BODY=imageWriteForm.jsp">■ 이미지 및 답글 게시글 쓰기</a><br>
					<a href="">■ 이미지 및 답글 게시글 보기</a><br>
					
					
					<a href="noticeList.do">■ 공지사항 보기</a><br>
					<% if(id != null && id.equals("admin")) { %>
						<a href="index.jsp?BODY=notice_input.jsp">■ 공지사항 쓰기</a><br>
					<% } %>
				</div>			
			</td>
			<td id="content">
			<%	if(body != null){ %>
				<jsp:include page="<%= body %>"/>
			<%	} else { %>
				<jsp:include page="intro.jsp"/>
			<%	} %>
			</td>
		</tr>	
	</table>
</section>

<footer>
	<h3 align="center">웹 어플리케이션 작성 프로젝트. Copyright 2024</h3>
</footer>

</body>
</html>