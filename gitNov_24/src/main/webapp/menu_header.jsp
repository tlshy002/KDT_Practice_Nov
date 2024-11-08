<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%	String id = (String)session.getAttribute("USER"); %>
	<table border="1">
		<tr>
			<td><a href="index.jsp">■ 홈으로</a></td> 
			<td><a href="index.jsp?BODY=input_item.jsp">■ 상품등록</a></td> 
			<td><a href="itemList.do">■ 상품목록</a></td> 
			<td>
			<%	if(id == null) { %>
				<a href="index.jsp?BODY=login.jsp">■ 로그인</a>
			<%
				} else {
			%>	
				<a href="logout.do">■ 로그아웃</a>
				<font color="red">환영합니다<%= id %>님</font><br/>
			<%
				}
			%>			
			</td></tr>
	</table>