<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>파일 첨부형 게시판 - 글보기(View)</h2>
	<table border="1" width="90%">
	<tr>
		<td width="15%">글번호</td>
		<td width="35%">${ board.idx }</td>
		<td width="15%">작성일</td>
		<td width="35%">${ board.postdate }</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${ board.name }</td>
		<td>조회수</td>
		<td>${ board.visitcount }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td colspan="3">${ board.title }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3">
			${ board.content }
		</td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td>${ board.ofile }</td>
		<td>다운 횟수</td>
		<td>${ board.downcount }</td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<button type="button" onclick="location.href='./pass.do?idx='${ board.idx }&mode=modify">수정하기</button>
			<button type="button" onclick="location.href='./pass.do?idx='${ board.idx }&mode=remove">삭제하기</button>
			<button type="button" onclick="location.href='./list.do'">글 목록</button>
		</td>
	</tr>
</table>
</body>
</html>