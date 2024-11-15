<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	int a = 100;//정수
	float b = 3.14f;
	double bb = 3.14;
	char c = 'A';
	boolean d = true;
	boolean dd = false;
%>
<script type="text/javascript">
var a = 100;//자바스크립트에서는 데이터타입이 없다.
var b = 3.14;
var c = 'A';
var d = true;
var dd = false;
alert("팝업창입니다.");//정보를 전달하는 목적
var data1 = prompt("이름을 입력하세요.");//데이터를 입력하는 목적
alert("입력한 이름은 : "+data1);
var data2 = confirm("작업을 진행하시겠습니까?");//선택하는 목적
if(data2){
	alert("작업 진행을 선택했습니다.");
}else {
	alert("작업 취소를 선택했습니다.");
}
</script>
</body>
</html>
















