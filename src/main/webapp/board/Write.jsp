<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>파일 첨부형 게시판 - 글쓰기(Write)</h2>
<script>
	function vaildateForm(form) {
		if(form.name.value == "") {
			alert("작성자를 입력해 주세요.")
			form.name.focus()
			return false
		} else if(form.title.value == "") {
			alert("제목을 입력해 주세요.")
			form.title.focus()
			return false
		} else if(form.content.value == "") {
			alert("내용을 입력해 주세요.")
			form.content.focus()
			return false
		} else if(form.pass.value == "") {
			alert("비밀번호를 입력해 주세요.")
			form.pass.focus()
			return false
		}
	}
</script>
	<form action="./write.do" method="post" enctype="multipart/form-data" onsubmit="return vaildateForm(this)">
		<table border="1" width="90%">
			<tr>
				<td>작성자</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" style="width: 90%;"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" rows="10" style="width: 90%;"></textarea></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="file" name="ofile"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<button type="submit">저장하기</button>
					<button type="reset">다시 작성</button>
					<button type="button" onclick="location.href='./list.do'">목록으로</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>