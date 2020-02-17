<%@page import="member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%MemberVO vo = (MemberVO)session.getAttribute("member"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
아이디 : <%=vo.getId() %><br>
이름 : <%=vo.getName() %><br>
가입일 : <%=vo.getRegdate() %><br>
사진 : <img src="/upload/member/<%=vo.getFilename() %>" width="100px" height="100px"><br>
자기소개 : <%=vo.getInfo() %>
<input type="button" onclick="location.href='memberDelete.do'" value="회원탈퇴">
</body>
</html>