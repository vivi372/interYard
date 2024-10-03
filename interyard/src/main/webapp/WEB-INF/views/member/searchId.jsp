<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="container" style="width: 600px;">

	<table class="table" >
	<tr>
	<td>찾은 아이디 : </td><td>${vo }</td>
	</tr>
	</table>
	<form action="/member/loginForm.do" method="post">
		<button class="btn btn-outline-secondary float-right">돌아가기</button>
	</form>
	
</div>
</body>
</html>