<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div align="right"><a href="index.jsp?BODY=userEntry.jsp">가입하기</a></div>
<div align="center"></div>

	<form action="login.do" method="post" onsubmit="return check(this)">
	계정: <input type="text" name="ID" size="10"><br/>
	암호: <input type="password" name="PW" size="10"><br/><br/>
	<input type="submit" value="로그인"/>
	<input type="reset" value="취 소"/>
	</form>

<script type="text/javascript">
function check(frm) {
	if(frm.ID.value == '') { alert("계정을 입력하세요."); frm.ID.focus(); return false; }
	if(frm.PW.value == '') { alert("암호을 입력하세요."); frm.PW.focus(); return false; }
	
}
</script>
</body>
</html>