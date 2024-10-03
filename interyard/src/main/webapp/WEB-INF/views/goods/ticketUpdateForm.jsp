<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 수정</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		$(".datepicker").datepicker({
			dateFormat : "yy-mm-dd",
		});
	});

	$(document).ready(function() {
		$('.dropdown-item.categoryNo').on('click', function() {
			var selectedText = $(this).text();
			var selectedValue = $(this).data('value');
			$('#dropdownMenuButton').text(selectedText);
			console.log('Selected Value:', selectedValue);

			// 선택된 값을 숨겨진 필드에 저장
			$('#selectedCategory').val(selectedValue);
		});
	});
</script>
<style>
#goodsContent {
	height: 300px; /* 원하는 높이로 설정 */
}
</style>
</head>
<body>
	<div class="container">
		<h1>상품 정보 수정</h1>
		<form action="ticketUpdate.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="page" value="${param.page }">
			<input type="hidden" name="perPageNum" value="${param.perPageNum }">
			<input type="hidden" name="key" value="${param.key }">
			<input type="hidden" name="word" value="${param.word }">
			<input type="hidden" id="selectedCategory" name="categoryNo">
			<input type="hidden" id="goodsPrice" name="goodsPrice" value="${vo.goodsPrice }">
			<input type="hidden" id="goodsNo" name="goodsNo" value="${vo.goodsNo }">
			<div class="card">
				<div class="card-header">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="goodsImage">첨부 이미지</label>
								<input id="goodsImage" name="goodsImage" required class="form-control" type="file">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="categoryNo">카테고리</label>
								<div class="dropdown">
									<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"
									>카테고리</button>
									<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
										<h5 class="dropdown-header">투어</h5>
										<a class="dropdown-item categoryNo" data-value="3101">항공</a>
										<a class="dropdown-item categoryNo" data-value="3102">해외숙소</a>
										<a class="dropdown-item categoryNo" data-value="3103">해외패키지</a>
										<a class="dropdown-item categoryNo" data-value="3104">투어.티켓</a>
										<a class="dropdown-item categoryNo" data-value="3105">국내숙소</a>
										<a class="dropdown-item categoryNo" data-value="3106">국내패키지</a>
										<a class="dropdown-item categoryNo" data-value="3107">렌터카</a>
										<h5 class="dropdown-header">티켓</h5>
										<a class="dropdown-item categoryNo" data-value="3201">뮤지컬</a>
										<a class="dropdown-item categoryNo" data-value="3202">콘서트</a>
										<a class="dropdown-item categoryNo" data-value="3203">스포츠</a>
										<a class="dropdown-item categoryNo" data-value="3204">전시/행사</a>
										<a class="dropdown-item categoryNo" data-value="3205">클래식/무용</a>
										<a class="dropdown-item categoryNo" data-value="3206">아동/가족</a>
										<a class="dropdown-item categoryNo" data-value="3207">연극</a>
										<a class="dropdown-item categoryNo" data-value="3208">레저/캠핑</a>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="goodsTitle">작품명</label>
								<input id="goodsTitle" name="goodsTitle" required value="${vo.goodsTitle }" class="form-control">
							</div>
							<div class="row">
								<div class="form-group">
									<label for="goodsSubTitle">부제</label>
									<input id="goodsSubTitle" name="goodsSubTitle" value="${vo.goodsSubTitle }" class="form-control">
								</div>
								<div class="form-group">
									<label for="goodsStatus">상태</label>
									<input id="goodsStatus" name="goodsStatus" required value="${vo.goodsStatus }" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label for="goodsPublicher">기획</label>
								<input id="goodsPublicher" name="goodsPublicher" required value="${vo.goodsPublicher }" class="form-control">
							</div>
							<div class="form-group">
								<label for="goodsMaker">장소</label>
								<input id="goodsMaker" name="goodsMaker" required value="${vo.goodsMaker }" class="form-control">
							</div>
							<div class="form-group">
								<label for="goodsRunTime">관람시간(분)</label>
								<input id="goodsRunTime" name="goodsRunTime" required value="${vo.goodsRunTime }" class="form-control">
							</div>
							<div class="row">
								<div class="form-group">
									<label for="goodsStartDate">작품시작일</label>
									<input id="goodsStartDate" name="goodsStartDate" required value="${vo.goodsStartDate }" class="form-control">
								</div>
								<div class="form-group">
									<label for="goodsEndDate">작품종료일</label>
									<input id="goodsEndDate" name="goodsEndDate" required value="${vo.goodsEndDate }" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label for="goodsRating">관람연령</label>
								<input id="goodsRating" name="goodsRating" required value="${vo.goodsRating }" class="form-control">
							</div>
							<div class="form-group">
								<label for="goodsCost">수수료(원)</label>
								<input id="goodsCost" name="goodsCost" required value="${vo.goodsCost }" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="card-body">
					<div class="form-group">
						<label for="goodsContent">작품 설명</label>
						<textarea class="form-control" id="goodsContent" name="goodsContent" required>${vo.goodsContent }</textarea>
					</div>
				</div>
				<div class="card-footer">
					<button class="btn btn-primary">수정</button>
					<button type="reset" class="btn btn-secondary">다시입력</button>
					<button type="button" onclick="history.back();" class="btn btn-warning">취소</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
