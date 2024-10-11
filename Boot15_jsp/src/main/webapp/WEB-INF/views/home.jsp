<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/home.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지 입니다</h1>
		<p>
			jsp 페이지에서는  el 과  jstl 을 활용해서 request 영역에 담긴 내용을 추출해서
			클라이언트에게 출력할수 있다.
			request 영역에서 추출하는 기능은 el 이 담당하고 
			반복문은 jstl 을 활용한다.
		</p>
		<h3>공지사항</h3>
		<ul>
			<c:forEach var="tmp" items="${requestScope.notice }">
				<li>${tmp }</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>











