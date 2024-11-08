<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h3 align="center">상품등록</h3>
<div align="center">
<form action="inputItem.do" method="post">
	<table border="1">
	<tr><th>상품코드</th><td><input type="text" name="CODE" size="10"></td></tr>
	<tr><th>상품이름</th><td><input type="text" name="NAME" size="15"></td></tr>
	<tr><th>상품가격</th><td><input type="text" name="price" size="10"></td></tr>
	<tr><th>상품가격</th>
	<td><select name="ORIGIN">
		<option value="KOR">대한민국</option><option value="JPN">일본</option>
		<option value="KOR">중 국</option><option value="JPN">미 국</option>
		<option value="KOR">호 주</option><option value="JPN">영 국</option>
		<option value="KOR">러시아</option><option value="JPN">기 타</option>
	</select></td></tr>
	<tr><th>상품설명</th><td><textarea rows="5" cols="40" name="INFO"></textarea></td></tr>
	<tr><td colspan="2" align="center">
		<input type="submit" value="상품 등록">
		<input type="reset" value="취 소">
	</table>
</form>

</div>

</body>
</html>