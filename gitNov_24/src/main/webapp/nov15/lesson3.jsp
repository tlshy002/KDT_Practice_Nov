<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2>아래 영역에 데이터가 출력됩니다.</h2>
<input type="button" value="CLICK" onclick="doIt()">
<div id="here">이곳에 데이터가 출력됨</div>
<script type="text/javascript">
function doIt(){
	document.getElementById("here").innerHTML = "안녕하세요.";
}
</script>
</body>
</html>