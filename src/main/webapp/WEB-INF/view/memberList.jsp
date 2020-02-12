<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Member</h2>
<c:forEach var="data" items="${list}" varStatus="status">
	회원번호 : ${status.index } <br>
	아이디 : ${data.id} <br>
	비밀번호 : ${data.password} <br>
	이름 : ${data.name} <br>
	가입일 : ${data.regdate}<br>
	<c:if test="${!empty data.filename}">
	사진 : <img src="<c:url value="/upload/member/${data.filename}"/>" width="10px" height="10px">
	</c:if>
	${data.info }
	<br>
</c:forEach>