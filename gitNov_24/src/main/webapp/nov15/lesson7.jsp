<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h2>자바스크립트로 팝업창 띄우기</h2>
<a href="#" onclick="openSamePopup()">■ 창열기(한번만 열기)</a><br/>
<a href="#" onclick="openNewPopup()">■ 창열기(매번 다른창 열기)</a><br/>
<a href="#" onclick="openHtml()">■ 문서열기</a><br/>
<a href="#" onclick="openGoogle()">■ 구글열기</a><br/>
<a href="#" onclick="goGoogle()">■ 구글로 이동</a><br/>
<input type="button" value="구글로 이동" onclick="goGoogle()"/><br/>
<input type="button" value="암호 받기" onclick="goReload()"><br/>
</div>
<script type="text/javascript">
function goReload(){
	window.open("reload.html", "reload", "width=350, height=300");
}
function goGoogle(){
	if(confirm("정말로 구글로 이동하시겠습니까?"))
		location.href="http://www.google.com";
}
function openGoogle(){
	window.open("http://www.google.com", "google", "width=400,height=400");
}
function openHtml(){
	window.open("intro.html", "intro", "width=400,height=250");
}
function openSamePopup(){
	window.open("http://www.naver.com","custom","width=500,height=300");
}
function openNewPopup(){
	window.open("http://www.naver.com","","width=500,height=300");
}
</script>
</body>
</html>
















