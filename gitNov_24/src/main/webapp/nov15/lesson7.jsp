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
<h2>�ڹٽ�ũ��Ʈ�� �˾�â ����</h2>
<a href="#" onclick="openSamePopup()">�� â����(�ѹ��� ����)</a><br/>
<a href="#" onclick="openNewPopup()">�� â����(�Ź� �ٸ�â ����)</a><br/>
<a href="#" onclick="openHtml()">�� ��������</a><br/>
<a href="#" onclick="openGoogle()">�� ���ۿ���</a><br/>
<a href="#" onclick="goGoogle()">�� ���۷� �̵�</a><br/>
<input type="button" value="���۷� �̵�" onclick="goGoogle()"/><br/>
<input type="button" value="��ȣ �ޱ�" onclick="goReload()"><br/>
</div>
<script type="text/javascript">
function goReload(){
	window.open("reload.html", "reload", "width=350, height=300");
}
function goGoogle(){
	if(confirm("������ ���۷� �̵��Ͻðڽ��ϱ�?"))
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
















