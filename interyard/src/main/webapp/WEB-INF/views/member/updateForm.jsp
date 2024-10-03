<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보수정</title>
<script type="text/javascript" src="/js/order/telInput.js"></script>
<script type="text/javascript">

$(function(){
	
	// 전화번호 체크 처리
	$("#tel").keyup(function(){
		let tel = $("#tel").val();
		// data  확인하기
		console.log("tel = " + tel);
			// 서버에 가서 데이터를 확인하고 온다. - 결과를 JSP로 받는다.
			// $("#checkIdDIV") 안에 넣을 문구를 가져 와서 넣는다.
			// ajax의 load 함수 사용 load(url, data, fucntion(){});
			$("#checkTelDiv").load("/ajax/checkTel.do?tel=" + tel,
			// callback 함수 - function(결과, 상태-success/error, 통신객체){
			function(result){
				if(result.indexOf("중복") >= 0)
					$("#checkTelDiv").removeClass("alert-success alert-danger")
					.addClass("alert-danger");
				else
					$("#checkTelDiv").removeClass("alert-success alert-danger")
					.addClass("alert-success");
				;
			}); // checkTel Ajax 처리의 끝	
}); // $("#tel").keyup() 이벤트 처리의 끝
	
	
//이메일 체크 처리
$("#email").keyup(function(){
	let email = $("#email").val();
	// data  확인하기
	console.log("email = " + email);
		// 서버에 가서 데이터를 확인하고 온다. - 결과를 JSP로 받는다.
		// $("#checkIdDIV") 안에 넣을 문구를 가져 와서 넣는다.
		// ajax의 load 함수 사용 load(url, data, fucntion(){});
		$("#checkEmailDiv").load("/ajax/checkEmail.do?email=" + email,
		// callback 함수 - function(결과, 상태-success/error, 통신객체){
		function(result){
			if(result.indexOf("중복") >= 0){
				$("#checkEmailDiv").removeClass("alert-success alert-danger")
				.addClass("alert-danger");
				$("#btn").prop("disabled",true);
			}
			else{
				$("#checkEmailDiv").removeClass("alert-success alert-danger")
				.addClass("alert-success");
				$("#btn").removeAttr("disabled");
			}
			;
		}); // checkEmail Ajax 처리의 끝		
}); // $("#email").keyup() 이벤트 처리의 끝
});

</script>
</head>
<body>
<div class="card" style="width:600px; height : 850px; margin: 0 auto; ">
	   <form action="update.do">
	    	<c:if test="${ !empty vo.photo}">
			<img src="${vo.photo }" alt="Card image" style="width:100%">
			</c:if>
			<c:if test="${ empty vo.photo}">
			<i class="fa fa-user-circle-o" style="font-size:100px"></i>
			</c:if>
	    <div class="card-body">
	      <h4 class="card-title">이름 :  <input name="name" value="${vo.name }" maxlength="10"></h4>
	      <h6>
	       아이디 : ${vo.id }<br>  
	       </h6>
			<div class="form-group">
			    <label>성별</label>
				<div class="form-check-inline">
			 	 <label class="form-check-label">
			    <input type="radio" class="form-check-input" name="gender"
			     checked value="남자" > 남자
			  </label>
			</div>
			<div class="form-check-inline">
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input" name="gender"
			     value="여자" > 여자
			  </label>
			</div>
		  </div>
	      <p class="card-text">생년월일 : <input name="birth" value="${vo.birth }"> <br>
	     <div style="margin-top: 20px;" class="form-group">
		 	<label for="tel">전화번호 입력 : </label>
		  	<input type="text" class="txtPhone" name="tel" id="tel" style="text-align:center;" maxlength="13"
		  	placeholder="000-0000-00000" 
			pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" required value="${vo.tel }"/>
			</div>
		  	<div id="checkTelDiv" class="alert alert-danger">
		  	전화번호는 중복 사용이 불가능합니다.
		   </div>
	      <br>  회원 등급 : ${vo.gradeName }<br>
	      <p class="card-text"> 가입일 : ${vo.writeDate } <br>
	       		 <div class="form-group">
		    <label for="email">이메일</label>
			<input id="email" name="email" required
				class="form-control" value="${vo.email }"
				placeholder="id@도메인">
		  </div>
		  <div id="checkEmailDiv" class="alert alert-danger">
		  	이메일은 중복 사용이 불가능합니다.
		  </div>

			<br>  회원 상태 : ${vo.status }  </p><br>
	   </div>
	   	<button class="btn btn-dark">수정완료</button>
	   </form>
	  		 <form action="changeStatus.do"  style="width: 200px;" class="text-right">
				<input name="id" value="${vo.id }" type="hidden">
				<div class="input-group">
				<select class="form-control status" name="status" id="status" data-data="${vo.status }">
				<option  ${(vo.status == "탈퇴")?"selected":"" }>탈퇴</option>
				</select>	
				<div class="input-group-append">
				<button class="btn btn-dark " type="submit">탈퇴</button>
				</div>
				</div>
			</form>
</div>
</body>
</html>