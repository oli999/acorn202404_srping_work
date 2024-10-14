<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/file/list.jsp</title>
</head>
<body>
    <div class="container">        
        <h1>자료실 목록 입니다</h1>
        <a href="${pageContext.request.contextPath }/file/upload_form">새자료 올리기</a>
        <table class="table table-striped">
            <thead class="table-dark">
                <tr>
                    <th>번호</th>
                    <th>작성자</th>
                    <th>제목</th>
                    <th>파일명</th>
                    <th>등록일</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <tbody>    
            	<c:forEach var="tmp" items="${list }">
            		<td>${tmp.num }</td>
            		<td>${tmp.writer }</td>
            		<td>${tmp.title }</td>
            		<td>
            			<a href="${pageContext.request.contextPath }/file/download?num=${tmp.num }">${tmp.orgFileName }</a>
            		</td>
            		<td>${tmp.regdate }</td>
            		<td>
            			<c:if test="${userName eq tmp.writer }">
            				<a href="${pageContext.request.contextPath }/file/delete?num=${tmp.num }">삭제</a>
            			</c:if>
            		</td>
            	</c:forEach>
            </tbody>
        </table>
        <nav>
        	<c:if test="${totalPageCount ne 0 }">
        		<ul class="pagination">
        			<c:if test="${startPageNum != 1 }">
        				<li class="page-item">
        					<a class="page-link" href="${pageContext.request.contextPath }/file/list?pageNum=${startPageNum-1 }&condition=${dto.condition}&keyword=${dto.keyword}">Prev</a>
        				</li>
        			</c:if>
        			<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
        				<li class="page-item">
        					<a class="page-link" href="${pageContext.request.contextPath }/file/list?pageNum=${i }&condition=${dto.condition}&keyword=${dto.keyword}">${i }</a>
        				</li>
        			</c:forEach>
        			<c:if test="${endPageNum < totalPageCount }">
        				<li class="page-item">
        					<a class="page-link" href="${pageContext.request.contextPath }/file/list?pageNum=${endPageNum+1}&condition=${dto.condition}&keyword=${dto.keyword}">Next</a>
        				</li>
        			</c:if>
        		</ul>
        	</c:if>
        </nav>
    	<!-- 검색폼 -->
        <form th:action="@{/file/list}" method="get">
        	<label for="condition">검색조건</label>
        	<select name="condition" id="condition">
        		<option th:selected="${dto.condition eq 'title_filename'}" value="title_filename">제목 + 파일명</option>
        		<option th:selected="${dto.condition eq 'title'}" value="title">제목</option>
        		<option th:selected="${dto.condition eq 'writer'}" value="writer">작성자</option>
        	</select>
        	<input th:value="${dto.keyword}" type="text" name="keyword" placeholder="검색어..."/>
        	<button class="btn btn-primary btn-sm" type="submit">검색</button>
        	<a class="btn btn-success btn-sm" th:href="@{/file/list}">새로고침</a>
        </form>
        <p th:if="${not #strings.isEmpty(dto.keyword)}">
        	<strong th:text="${totalRow}"></strong> 개의 자료가 검색 되었습니다
        </p>
    </div>
</body>
</html>