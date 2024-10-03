<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>옵션 등록</title>
</head>
<body>
	<div class="container">
		<h2>옵션 등록</h2>
		<form action="optionWrite.do" method="post" enctype="multipart/form-data">
			<input name="perPageNum" value="${param.perPageNum }" type="hidden">
			<div class="card">
				<div class="card-header">
					<div class="form-group">
						<label for="goodsNo">상품번호</label>
						<input id="goodsNo" name="goodsNo" required value="${param.goodsNo }" class="form-control">
					</div>
					<div class="form-group">
						<label for="optName">옵션명,내용</label>
						<input id="optName" name="optName" required class="form-control">
					</div>
				</div>
			</div>
			<div class="card-body">
				<div class="form-group">
					<label for="optPrice">옵션 가격</label>
					<input id="optPrice" name="optPrice" required class="form-control">
				</div>
			</div>
			<div class="card-footer">
				<button class="btn btn-primary">등록</button>
				<button type="reset" class="btn btn-secondary">다시입력</button>
				<button type="button" onclick="history.back();" class="btn btn-warning">취소</button>
			</div>
		</form>
	</div>
</body>
</html>
