<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h3>글쓰기 폼 ^*^</h3>
		<hr width="50%" color="yellow" />


		<form method="get" action="board_write_ok.do">
			<table>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" required></td>
				</tr>
				<tr>
					<th>글제목</th>
					<td><input type="text" name="title" required></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="8" cols="30" name="cont" required></textarea>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd" required></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="submit" value="글 보내기"> 
					<input type="reset" value="다시 작성"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>