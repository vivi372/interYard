<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500 Error</title>
<style type="text/css">
	#errorDiv > .row{
		padding: 10px;
		border-top: 1px dotted #ccc;
		margin: 0 10px;
	}
	
</style>
</head>
<body>
<div class="container">
	<div class="card mb-2">
  		<div class="card-header"><h3><i class="material-icons">error_outline</i> 프로세스 처리 오류(500)</h3></div>
  		<div class="card-body" id="errorDiv">  			
  			<div class="row">
  				<div class="col-md-3">오류 객체</div>
  				<div class="col-md-9">
  					${e.getClass().getSimpleName() }
  				</div>
  			</div>
  			<div class="row">
  				<div class="col-md-3">오류 메세지</div>
  				<div class="col-md-9">
  					${ e.getMessage()}
  				</div>
  			</div>
  			<div class="row">
  				<div class="col-md-3">조치 사항</div>
  				<div class="col-md-9">
  					오류로 인해 불편을 드려 죄송합니다.<br>
  					다시 한번 시도해 주세요.<p>
  					오류가 계속 발생이되면 전산 담당자에게 연락해주세요.
  				</div>
  			</div>  			
			<div class="text-center" style="border-top: 1px dotted #ccc;">
				<img alt="오류 이미지" id="errorImg">
			</div>
  			
  		</div>
  		<div class="card-footer">
  			<a href="/" class="btn btn-dark">메인으로 가기</a>
  		</div>
	</div>
</div>
</body>
</html>