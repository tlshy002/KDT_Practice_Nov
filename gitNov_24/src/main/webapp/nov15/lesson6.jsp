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
//�ڹٽ�ũ��Ʈ�� Math ��ü ��� ��
var max = Math.max(123, 456, 789, 1122);//�ִ밪�� �����ϴ� �Լ�
document.write("<h3>�ִ밪:"+max+"</h3>");//�������� ���
var min = Math.min(456, 789, 123);//�ּҰ��� �����ϴ� �Լ�
document.write("<h3>�ּҰ�:"+min+"</h3>");//�������� ���
var value = 3.141592654;
var r1 = Math.round(value);//�ݿø� �Լ�(����)
var r2 = Math.ceil(value);//�ø� �Լ�(����)
var r3 = Math.floor(value);//���� �Լ�(����)
document.write("<h3>�ݿø�:"+r1+",�ø�:"+r2+",����:"+r3+"</h3>");
var abs = Math.abs(-3.1415);//���밪 �Լ�(����)
var r4 = Math.PI;//������ ����
var rnd = Math.random();//���� �Լ�(����)
document.write("<h3>���밪:"+abs+",������:"+r4+",����:"+rnd+"</h3>");
function makeRand(){
	var rnd = parseInt(Math.random() * 10);//0 ~ 9 �� ��������
	return rnd;
}
var myRnd = makeRand();
document.write("<h3>���� ���� �Լ��� ���ϰ�:"+myRnd+"</h3>");
var newRnd = ""+makeRand()+makeRand()+makeRand()+makeRand()+makeRand();
document.write("<h3>�ټ��ڸ� ���� : "+newRnd+"</h3>");
</script>
</body>
</html>
















