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
	<tr><th>����� �̸�</th><th>�� ��</th></tr>
	<tr><td><div id="drink"></div></td><td><div id="price"></div></td></tr>
</table>
</div>
<script type="text/javascript">
var menu = prompt("�޴��� �̸��� �Է��ϼ���.");
var price;
if(isNaN(menu) == false){//�޴��̸��� ���ڰ� �ƴ� ���
	alert("�޴��� ���ڷ� �Է��ؾ� �մϴ�.");
}else {//�޴��̸��� ������ ���
	switch(menu){
	case "�ݶ�": alert("�ݶ��� ������ 2000�� �Դϴ�."); price = 2000; break;
	case "�Ƹ޸�ī��": alert("�Ƹ޸�ī���� ������ 2500�� �Դϴ�."); price = 2500; break;
	case "īǪġ��": alert("īǪġ���� ������ 3000�� �Դϴ�."); price = 3000; break;
	case "ī�����": alert("ī������� ������ 3500�� �Դϴ�."); price = 3500; break;
	default : alert("�ش� �޴��� �������� �ʽ��ϴ�."); price = "�������� ����"; 
	}
}
document.getElementById("drink").innerHTML = menu;
document.getElementById("price").innerHTML = price;
</script>
</body>
</html>














