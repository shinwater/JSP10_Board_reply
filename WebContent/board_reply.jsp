<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	table {
		border: 0;
		cellspacing: 0;
	}

	th {
		align:right;
	}
	td {
		align:center;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="yellow" />
		<h3>^*^ JSP_BBS 테~이블~ 게시물 답변 폼 ^*^</h3>
		<hr width="50%" color="yellow" />


		<form method="get" action="board_relpy_ok.do">
			<c:set var="dto" value="${reply }"/>
			<input type="hidden" name="board_no" value="${dto.getBoard_no() }"> 
			<input type="hidden" name="board_group" value="${dto.getBoard_group() }"> 
			<input type="hidden" name="board_step" value="${dto.getBoard_step() }"> 
			<input type="hidden" name="board_indent" value="${dto.getBoard_indent() }"> 
			<table>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" value="${dto.getBoard_writer() }"></td>
				</tr>
				<tr>
					<th>글제목</th>
					<td><input type="text" name="title" value="${dto.getBoard_title() }"></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="8" cols="30" name="cont">${dto.getBoard_cont() }</textarea>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd" required></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="submit" value="답변"> 
					<input type="reset" value="다시 작성"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>