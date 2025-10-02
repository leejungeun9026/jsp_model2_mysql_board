<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>파일 첨부형 게시판 - 목록 보기(List)</h2>
	<form method="get">
		<table border="1" width="90%">
			<tr>
				<td align="center">
					<select name="searchField">
							<option value="all" <c:if test="${ param.searchField eq 'all' }">selected</c:if>>제목+내용</option>
							<option value="title" <c:if test="${ param.searchField eq 'title' }">selected</c:if>>제목</option>
							<option value="content" <c:if test="${ param.searchField eq 'content' }">selected</c:if>>내용</option>
					</select>
					<input type="text" name="searchWord" value="${ param.searchWord }">
					<input type="submit" value="검색">
					<input type="reset" onclick="location.href='./list.do'"  value="지우기">
				</td>
			</tr>
		</table>
	</form>
	<table border="1" width="90%">
		<tr>
			<th width="10%">글번호</th>
			<th width="*%">제목</th>
			<th width="10%">작성자</th>
			<th width="15%">날짜</th>
			<th width="10%">조회수</th>
		</tr>
		<c:choose>
			<c:when test="${ empty boardList }">
				<td colspan="5" align="center">게시물이 없습니다.</td>
			</c:when>
			<c:otherwise>
				<c:forEach var="board" items="${ boardList }">
					<tr>
						<td>${ board.idx }</td>
						<td><a href="./view.do?idx=${ board.idx }">${ board.title }</a></td>
						<td>${ board.name }</td>
						<td>${ board.postdate }</td>
						<td>${ board.visitcount }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="5" align="right">
				<button onclick="location.href='./write.do'">글쓰기</button>
			</td>
		</tr>
	</table>
</body>
</html>