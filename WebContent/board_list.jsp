<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="red"/>
		<h3>JSP_BBS 테이블 게시판 전체 리스트</h3>
		<hr width="50%" color="red"/>
		
		<br/><br/><br/>
		
		<table border="1" cellspacing="0" width="800">
			<tr>
				<th>글번호</th><th>글제목</th><th>조회수</th><th>작성일자</th>
				<th>Group</th><th>Step</th><th>Indent</th>
			</tr>
			<c:set var="list" value="${List }"/>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td>${dto.getBoard_title() }</td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_date().substring(0,10) }</td>
						<td>${dto.getBoard_group() }</td>
						<td>${dto.getBoard_step() }</td>
						<td>${dto.getBoard_indent() }</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty list }">
				<tr>
					<td colspan="7" align="center">
						<h3>검색된 레코드가 없습니ㅏㄷ,.</h3>
					</td>
				</tr>
			</c:if>
			
			
			
			<tr>
				<td colspan="7" align="right">
					<input type="button" value="그,ㄹ쓰기" onclick="location.href='board_write.do'"/>&emsp;&emsp;
				</td>
			</tr>
		</table>
	</div>
</body>
</html>