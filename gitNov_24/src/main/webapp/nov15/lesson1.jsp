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
	int a = 100;//����
	float b = 3.14f;
	double bb = 3.14;
	char c = 'A';
	boolean d = true;
	boolean dd = false;
%>
<script type="text/javascript">
var a = 100;//�ڹٽ�ũ��Ʈ������ ������Ÿ���� ����.
var b = 3.14;
var c = 'A';
var d = true;
var dd = false;
alert("�˾�â�Դϴ�.");//������ �����ϴ� ����
var data1 = prompt("�̸��� �Է��ϼ���.");//�����͸� �Է��ϴ� ����
alert("�Է��� �̸��� : "+data1);
var data2 = confirm("�۾��� �����Ͻðڽ��ϱ�?");//�����ϴ� ����
if(data2){
	alert("�۾� ������ �����߽��ϴ�.");
}else {
	alert("�۾� ��Ҹ� �����߽��ϴ�.");
}
</script>
</body>
</html>
















