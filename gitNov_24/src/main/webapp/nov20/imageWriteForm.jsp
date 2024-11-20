<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>이미지 게시글 작성</h2>
	<form action="imageWrite.do" method="post" enctype="multipart/form-data"
			onsubmit="return check(this)"> <!-- this: 현재의 객체인 form을 의미함 -->
	<table>
		<tr><th>제 목</th><td><input type="text" name="TITLE" value="제목을 입력하세요"/></td></tr>
		<tr><th>암 호</th><td><input type="password" name="PWD"/></td></tr>
		<tr><th>이미지</th><td><input type="file" name="IMAGENAME"/></td></tr>
		<tr><th>내 용</th><td><textarea rows="8" cols="40" name="CONTENT"></textarea></td></tr>
		<tr><td colspan="2" align="center"><input type="submit" value="글 올리기"/>
			<input type="reset" value="취 소" /> </td></tr>
	</table>
	</form>
<script type="text/javascript">
function check(frm) {

		if(frm.TITLE.value == '') {
			alert("제목을 입력하세요");
			frm.TITLE.focus();
			return false;
		}
		if(frm.PWD.value == ''){
			alert("암호를 입력하세요");
			frm.PWD.focus();
			return false;
		}
		if(frm.IMAGENAME.value == '') {
			alert("이미지 파일을 선택하세요");
			frm.IMAGENAME.focus();
			return false;
		}
		if(frm.CONTENT.value == '') {
			alert("내용을 입력하세요");
			frm.CONTENT.focus();
			return false;
		}
		if( !confirm("정말로 글을 올리시겠습니까?")) return false;

	
}
</script>

</body>
</html>