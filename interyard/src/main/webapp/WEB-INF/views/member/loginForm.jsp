<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Interyard</title>
<link href="/css/loginForm.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container my-5" >
<div class="login-box">
  <h6 align="left">Interyard</h6><h2>Login</h2>
  <form action="login.do" method="post">
    <div class="user-box">
      <input type="text" name="id" required>
      <label>Username</label>
    </div>
    <div class="user-box">
      <input type="password" name="pw" required>
      <label>Password</label>
	<button class="btn btn-outline-dark btn-block">로그인</button>
    </div>
	<div class="btn-group " style="width: 300px; margin-left: 15px;">
	  <a href="searchIdForm.do" style="font-size: 11px; padding : 0px;">아이디 찾기&nbsp </a>
	  <a href="searchPwForm.do" style="font-size: 11px; padding: 0px;">|&nbsp비밀번호 찾기 </a>
	  <a href="writeForm.do" style="font-size: 11px; padding: 0px;">&nbsp|&nbsp회원 가입</a>
	</div>
  </form>
</div>
</div>
</body>
</html>