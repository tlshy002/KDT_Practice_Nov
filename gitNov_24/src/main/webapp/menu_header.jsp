<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%	String id = (String)session.getAttribute("USER"); %>
	<table border="1">
		<tr>
			<td><a href="index.jsp">�� Ȩ����</a></td> 
			<td><a href="index.jsp?BODY=input_item.jsp">�� ��ǰ���</a></td> 
			<td><a href="itemList.do">�� ��ǰ���</a></td> 
			<td>
			<%	if(id == null) { %>
				<a href="index.jsp?BODY=login.jsp">�� �α���</a>
			<%
				} else {
			%>	
				<a href="logout.do">�� �α׾ƿ�</a>
				<font color="red">ȯ���մϴ�<%= id %>��</font><br/>
			<%
				}
			%>			
			</td></tr>
	</table>