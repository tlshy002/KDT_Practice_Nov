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
<table border="1">
	<tr><th>음료수 이름</th><th>가 격</th></tr>
	<tr><td><div id="drink"></div></td><td><div id="price"></div></td></tr>
</table>
</div>
<script type="text/javascript">
var menu = prompt("메뉴의 이름을 입력하세요.");
var price;
if(isNaN(menu) == false){//메뉴이름이 문자가 아닌 경우
	alert("메뉴는 문자로 입력해야 합니다.");
}else {//메뉴이름이 문자인 경우
	switch(menu){
	case "콜라": alert("콜라의 가격은 2000원 입니다."); price = 2000; break;
	case "아메리카노": alert("아메리카노의 가격은 2500원 입니다."); price = 2500; break;
	case "카푸치노": alert("카푸치노의 가격은 3000원 입니다."); price = 3000; break;
	case "카페라테": alert("카페라테의 가격은 3500원 입니다."); price = 3500; break;
	default : alert("해당 메뉴는 존재하지 않습니다."); price = "존재하지 않음"; 
	}
}
document.getElementById("drink").innerHTML = menu;
document.getElementById("price").innerHTML = price;
</script>
</body>
</html>














