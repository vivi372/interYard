<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #pw , #pw2 {
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

<script type="text/javascript">
$(function(){
	
	// 비밀번호와 비밀번호 확인의 이벤트 시작
	$("#pw, #pw2").keyup(function(){

		let pw = $("#pw").val();
		let pw2 = $("#pw2").val();
		
		// 비밀번호 길이 체크
		if(pw.length < 4){
			// 디자인 부분 적용 
			$("#pwDiv").removeClass("alert-success alert-danger")
			.addClass("alert-danger");
			// 글자 오류 적용
			$("#pwDiv").text("비밀번호는 필수 입력 입니다. 4글자 이상 입력하셔야 합니다.");
		} else {
			// 디자인 부분 적용 
			$("#pwDiv").removeClass("alert-success alert-danger")
			.addClass("alert-success");
			// 글자 오류 적용
			$("#pwDiv").text("적당한 비밀번호를 입력하셨습니다.");
		}
		// 비밀번호확인 길이 체크
		if(pw2.length < 4){
			// 디자인 부분 적용 
			$("#pw2Div").removeClass("alert-success alert-danger")
			.addClass("alert-danger");
			// 글자 오류 적용
			$("#pw2Div").text("비밀번호 확인은 필수 입력 입니다. 4글자 이상 입력하셔야 합니다.");
		} else {
			// 비밀번호와 비밀번호 확인이 같은지 물어봐야 한다.
			if(pw != pw2){
				// 디자인 부분 적용 
				$("#pw2Div").removeClass("alert-success alert-danger")
				.addClass("alert-danger");
				// 글자 오류 적용
				$("#pw2Div").text("비밀번호와 비밀번호 확인은 같아야 합니다.");
			} else {
				// 디자인 부분 적용 
				$("#pw2Div").removeClass("alert-success alert-danger")
				.addClass("alert-success");
				// 글자 오류 적용
				$("#pw2Div").text("적당한 비밀번호 확인을 입력하셨습니다.");
			}
		}
	});
	// 비밀번호와 비밀번호 확인의 이벤트 끝
	
});


</script>
</head>
<body>
<div class="container" style="width: 600px; ">
	<form action="veriFy.do" method="post" class="my-3">
	<input type="hidden" name="id" value="${param.id }">
		<div class="form-group">
		    <label for="pw">비밀번호</label>
			<input id="pw" name="pw" required type="password"
				class="form-control" maxlength="20"
				pattern="^.{4,20}$"
				title="4~20 이내로 입력"
				placeholder="비밀번호 입력"
			>
		  </div>
		  <div id="pwDiv" class="alert alert-danger">
		  	비밀번호는 필수 입력 입니다. 4글자 이상 입력하셔야 합니다.
		  </div>
		  
		 <div class="form-group">
		    <label for="pw2">비밀번호 확인</label>
			<input id="pw2" required type="password"
				class="form-control" maxlength="20"
				pattern="^.{4,20}$"
				title="4~20 이내로 입력"
				placeholder="비밀번호 확인 입력"
			>
		  </div>
		  <div id="pw2Div" class="alert alert-danger">
		  	비밀번호 확인는 필수 입력 입니다. 4글자 이상 입력하셔야 합니다.
		  </div>
		  <div class="text-right">
		<button class="btn" style="height: 50px;">제출</button>
		  </div>
	</form>
</div>
</body>
</html>