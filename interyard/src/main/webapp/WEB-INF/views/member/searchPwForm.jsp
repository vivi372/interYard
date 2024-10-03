<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<script type="text/javascript" src="/js/order/telInput.js"></script>
<style>
    #input1 {
    	width: 300px;
        height: 50px;
        border-top: none;
        border-left: none;
        border-right: none;
        border-bottom : 3px solid black;
    }
    .btn {
    	width: 100px;
        height: 55px;
        border-top: none;
        border-left: none;
        border-right: none;
        border-bottom : 3px solid black;
    }
    .container{
    	margin: 0 auto;
    }
</style>
</head>
<body>
<div class="container" style="width: 600px;height: 500px; ">
	<form action="searchPw.do" method="post">
		<div class="form-group">
			<input type="text" class="form-control mt-5" id="input1" name="id" placeholder="Id">
		</div>
		<div class="form-group">
			<input type="email" class="form-control mt-5" id="input1" name="email" placeholder="email : xxx@exaple.com">
		</div>
		<div style="margin-top: 20px;">
		 	<label for="tel">전화번호 : </label>
		  	<input type="text" class="txtPhone mt-5" name="tel"   style="text-align:center;" maxlength="13"
		   	placeholder="000-0000-00000" 
			pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}"/>
		</div>
		<div class="text-right">
		<button class="btn" style="height: 50px;">제출</button>
		</div>
	</form>
</div>
</body>
</html>								