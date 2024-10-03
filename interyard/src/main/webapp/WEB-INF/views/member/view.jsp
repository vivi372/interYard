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
<div  class="container my-3">
<div class="card" style="width:600px; height : 600px; margin: 0 auto; ">
	    	<c:if test="${ !empty vo.photo}">
			<img src="${vo.photo }" alt="Card image" style="width:30%">
			</c:if>
			<c:if test="${ empty vo.photo}">
			<i class="fa fa-user-circle-o" style="font-size:100px"></i>
			</c:if>
	    <div class="card-body">
	      <h4 class="card-title">이름 : ${vo.name }</h4><h6> 아이디 : ${vo.id } / 성별 : ${vo.gender}</h6>
	      <p class="card-text">생년월일 : ${vo.birth } / 전화번호 : ${vo.tel } / 회원 등급 : ${vo.gradeName }</p>
	      <p class="card-text"> 가입일 : ${vo.writeDate } / 이메일 : ${vo.email } / 회원 상태 : ${vo.status }  </p>
	   </div>
	   <c:if test="${vo.id == login.id}">
	   <form action="updateForm.do?id=${vo.id }&admin=1" method="post">
	   	<button class="btn btn-dark">정보수정</button>
	   	<a href="pwReSet.do?id=${vo.id }" class="btn btn-dark">비밀번호 수정</a>
	   </form>
	   </c:if>
</div>
</div>
</body>
</html>