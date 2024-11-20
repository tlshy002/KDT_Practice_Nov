<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3 align="center">개인 정보 입력</h3>
	<form action="register.do" method="post" onsubmit="return check(this)" name="frm">
	<table>
		<tr><th>계 정</th><td><input type="text" name="ID"/>
			<input type="button" value="중복검사" onclick="idCheck()"/></td></tr>
		<tr><th>이 름</th><td><input type="text" name="NAME"/></td></tr>
		<tr><th>주 소</th><td><input type="text" name="ADDR"/></td></tr>
		<tr><th>연락처</th><td><input type="text" name="PHONE"/></td></tr>
		<tr><th>암 호</th><td><input type="password" name="PW"/></td></tr>
		<tr><th>암호확인</th><td><input type="password" name="CONFTRM"/></td></tr>
		<tr><th>성 별</th><td>남<input type="radio" name="GENDER" value="M"/>,
						<td>여<input type="radio" name="GENDER" value="F"/></td></tr>
		<tr><th>이메일</th><td><input type="text" name="EMAIL"/></td></tr>
		<tr><th>생 일</th><td><input type="date" name="BIRTH"/></td></tr>
		<tr><th>직 업</th><td>
			<select name="JOB"><option>---선택하세요---</option>
				<option>학 생</option><option>회사원</option><option>주 부</option>
				<option>자영업</option><option>기 타</option>
			</select>
		<tr><td colspan="2" align="center"><input type="submit" value="가입하기"/>
			<input type="reset" value="취 소">
	</table><br/>
	</form>
<script type="text/javascript">
function idCheck() {
	var url = "idCheck.do?ID="+document.frm.ID.value;
	window.open(url, "_blank_", "width=450, height=200")
	
}
</script>

</body>
</html>