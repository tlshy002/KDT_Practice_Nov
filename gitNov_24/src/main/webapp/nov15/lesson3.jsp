<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2>�Ʒ� ������ �����Ͱ� ��µ˴ϴ�.</h2>
<input type="button" value="CLICK" onclick="doIt()">
<div id="here">�̰��� �����Ͱ� ��µ�</div>
<script type="text/javascript">
function doIt(){
	document.getElementById("here").innerHTML = "�ȳ��ϼ���.";
}
</script>
</body>
</html>