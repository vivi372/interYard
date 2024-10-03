<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>권한 오류</title>
</head>
<body>
<div class="container">
	<div class="card mb-2">
  		<div class="card-header"><h3><i class="material-icons">error_outline</i> 권한 오류</h3></div>
  		<div class="card-body" id="errorDiv">  			
  			<div class="row">
  				<div class="col-md-3"> 요청 uri</div>
  				<div class="col-md-9">
  					${uri }
  				</div>
  			</div>
  			<div class="row">
  				<div class="col-md-3">오류 메세지</div>
  				<div class="col-md-9">
  					요청하신 페이지의 접근할 권한이 없습니다.  					
  				</div>
  			</div>
  			<div class="row">
  				<div class="col-md-3">조치 사항</div>
  				<div class="col-md-9">
  					요청하신 페이지의 주소를 확인하시고 다시 시도해주세요.
  				</div>
  			</div>  			
			<div class="text-center" style="border-top: 1px dotted #ccc;">
				<img alt="오류 이미지" src="/upload/img/errors_9679787.png" id="errorImg">
			</div>
  			
  		</div>
  		<div class="card-footer">
  			<a href="/board/list.do" class="btn btn-dark">일반 게시판으로 가기</a>
  		</div>
	</div>	
</div>
</body>
</html>