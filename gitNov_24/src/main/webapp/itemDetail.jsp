<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="nov11_item.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	ItemDTO dto = (ItemDTO)request.getAttribute("ITEM");
%>
<h3 align="center">상품 상세 정보</h3>
<div align="center">

<form action="itemModify.do" method="post">
<table border="1">
	<tr><th>상품번호</th><td><input type="hidden" name="CODE" value="<%= dto.getCode() %>"><%= dto.getCode() %></td></tr>
	<tr><th>상품이름</th><td><input type="text" name="NAME" value="<%= dto.getName() %>" /></td></tr>
	<tr><th>상품가격</th><td><input type="text" name="PRICE" value="<%= dto.getPrice() %>" /></td></tr>
	<tr><th>등록일</th><td><%= dto.getReg_date() %></td></tr>
	<tr><th>원산지</th>
		<!-- 콤보박스의 기본 설정하기 -->
		<td><select name="ORIGIN">
		<% if(dto.getOrigin().equals("KOR")) { %>
			<option value="KOR" selected="selected">대한민국</option>
		<% } else { %>		
			<option value="KOR">대한민국</option>
		<% } %>	
		
		<% if(dto.getOrigin().equals("JPN")) { %>
			<option value="JPN" selected="selected">일 본</option>
		<% } else { %>		
			<option value="JPN">일 본</option>
		<% } %>	
		
		<% if(dto.getOrigin().equals("CHN")) { %>
			<option value="CHN" selected="selected">중 국</option>
		<% } else { %>		
			<option value="CHN">중 국</option>
		<% } %>	
		
		<% if(dto.getOrigin().equals("USA")) { %>
			<option value="USA" selected="selected">미 국</option>
		<% } else { %>		
			<option value="USA">미 국</option>
		<% } %>	
		
		<% if(dto.getOrigin().equals("AUS")) { %>
			<option value="AUS" selected="selected">호 주</option>
		<% } else { %>		
			<option value="AUS">호 주</option>
		<% } %>	
		
		<% if(dto.getOrigin().equals("GBR")) { %>
			<option value="GBR" selected="selected">영 국</option>
		<% } else { %>		
			<option value="GBR">영 국</option>
		<% } %>	
		
		<% if(dto.getOrigin().equals("RUS")) { %>
			<option value="RUS" selected="selected">러시아</option>
		<% } else { %>		
			<option value="RUS">러시아</option>
		<% } %>	
		
		<% if(dto.getOrigin().equals("ETC")) { %>
			<option value="ETC" selected="selected">기 타</option>
		<% } else { %>		
			<option value="ETC">기 타</option>
		<% } %>	
		
		</select>
	
	
	<tr><th>상품설명</th><td><textarea rows="5" cols="40" name="CONTENT"><%= dto.getInfo() %></textarea></td></tr>

<%
	String id = (String)session.getAttribute("ID");
	if(id != null && id.equals("admin")) { // id가 null이 아니고 admin일때(로그인한 상태이고, 관리자계정일때)
%>	
	<tr><td colspan="2"><input type="submit" value="상품정보 수정" name="BTN" />
						<input type="submit" value="상품정보 삭제" name="BTN" />
						</td></tr>
<% } else if(id != null) { %>

	<tr><td colspan="2" align="center">
		<input type="button" value="장바구니 담기" onclick="buy()"/></td></tr>
						
<% } %>
</table>
</form>

</div>

<script type="text/javascript">
function buy() {
	confirm("정말로 구매하시겠습니까?");
}
</script>

</body>
</html>