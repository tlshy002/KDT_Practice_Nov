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
//자바스크립트의 Math 객체 사용 예
var max = Math.max(123, 456, 789, 1122);//최대값을 리턴하는 함수
document.write("<h3>최대값:"+max+"</h3>");//브라우저로 출력
var min = Math.min(456, 789, 123);//최소값을 리턴하는 함수
document.write("<h3>최소값:"+min+"</h3>");//브라우저로 출력
var value = 3.141592654;
var r1 = Math.round(value);//반올림 함수(리턴)
var r2 = Math.ceil(value);//올림 함수(리턴)
var r3 = Math.floor(value);//버림 함수(리턴)
document.write("<h3>반올림:"+r1+",올림:"+r2+",버림:"+r3+"</h3>");
var abs = Math.abs(-3.1415);//절대값 함수(리턴)
var r4 = Math.PI;//원주율 변수
var rnd = Math.random();//난수 함수(리턴)
document.write("<h3>절대값:"+abs+",원주율:"+r4+",난수:"+rnd+"</h3>");
function makeRand(){
	var rnd = parseInt(Math.random() * 10);//0 ~ 9 중 난수생성
	return rnd;
}
var myRnd = makeRand();
document.write("<h3>내가 만든 함수의 리턴값:"+myRnd+"</h3>");
var newRnd = ""+makeRand()+makeRand()+makeRand()+makeRand()+makeRand();
document.write("<h3>다섯자리 난수 : "+newRnd+"</h3>");
</script>
</body>
</html>
















