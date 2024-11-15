<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
document.write("<h1>자바스크립트</h1>");
var a = 1234;
show(); show(); show(); rnd();
function show(){
	document.write("<h2><font color='red'>자바스크립트 입니다.</font></h2>");	
}
function rnd(){
	var gamer = parseInt(Math.random() * 6 + 1);
	var com = parseInt(Math.random() * 6 + 1);
	document.write("<h2>게이머의 주사위 눈금 : "+gamer+"</h2>");
	document.write("<h2>컴퓨터의 주사위 눈금 : "+com+"</h2>");
}
</script>
</body>
</html>










