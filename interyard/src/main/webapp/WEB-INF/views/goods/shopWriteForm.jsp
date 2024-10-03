<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
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
		<h2>상품 등록</h2>
		<form action="shopWrite.do" method="post" enctype="multipart/form-data">
			<input name="perPageNum" value="${param.perPageNum }" type="hidden">
			<input type="hidden" id="selectedCategory" name="categoryNo">
			<input type="hidden" id="goodsRunTime" name="goodsRunTime" value="1">
			<input type="hidden" id="goodsRating" name="goodsRating" value="전체이용가">
			<div class="card">
				<div class="card-header">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="goodsImage">첨부 이미지</label>
								<input id="goodsImage" name="goodsImage" required class="form-control" type="file">
							</div>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<label for="categoryNo">카테고리</label>
								<div class="dropdown">
									<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"
									>카테고리</button>
									<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
										<h5 class="dropdown-header">스포츠/레저</h5>
										<a class="dropdown-item categoryNo" data-value="2101">등산/캠핑/낚시/여행</a>
										<a class="dropdown-item categoryNo" data-value="2102">자전거/인라인/오토바이</a>
										<a class="dropdown-item categoryNo" data-value="2103">헬스/다이어트/보충제</a>
										<a class="dropdown-item categoryNo" data-value="2104">구기/라켓/수영/스키</a>
										<a class="dropdown-item categoryNo" data-value="2105">골프클럽/의류/용품</a>
										<a class="dropdown-item categoryNo" data-value="2106">스포츠의류/잡화</a>
										<a class="dropdown-item categoryNo" data-value="2107">스포츠/운동화</a>
										<a class="dropdown-item categoryNo" data-value="2108">블랙박스/내비/자동차용품</a>
										<h5 class="dropdown-header">패션/잡화</h5>
										<a class="dropdown-item categoryNo" data-value="2201">캐주얼의류</a>
										<a class="dropdown-item categoryNo" data-value="2202">여성의류</a>
										<a class="dropdown-item categoryNo" data-value="2203">남성의류</a>
										<a class="dropdown-item categoryNo" data-value="2204">홈/언더웨어</a>
										<a class="dropdown-item categoryNo" data-value="2205">슈즈</a>
										<a class="dropdown-item categoryNo" data-value="2206">패션잡화</a>
										<h5 class="dropdown-header">뷰티</h5>
										<a class="dropdown-item categoryNo" data-value="2301">스킨케어</a>
										<a class="dropdown-item categoryNo" data-value="2302">메이크업</a>
										<a class="dropdown-item categoryNo" data-value="2303">헤어/바디/미용</a>
										<a class="dropdown-item categoryNo" data-value="2304">클렌징</a>
										<a class="dropdown-item categoryNo" data-value="2305">향수</a>
										<a class="dropdown-item categoryNo" data-value="2306">헤어케어</a>
										<a class="dropdown-item categoryNo" data-value="2307">바디케어</a>
										<a class="dropdown-item categoryNo" data-value="2308">핸드케어</a>
										<a class="dropdown-item categoryNo" data-value="2309">미용기기/헤어기기</a>
										<a class="dropdown-item categoryNo" data-value="2310">남성화장품</a>
										<a class="dropdown-item categoryNo" data-value="2311">선물세트</a>
										<h5 class="dropdown-header">식품</h5>
										<a class="dropdown-item categoryNo" data-value="2401">가공식품/즉석/간식</a>
										<a class="dropdown-item categoryNo" data-value="2402">커피/음료</a>
										<a class="dropdown-item categoryNo" data-value="2403">건강식품/홍삼/다이어트</a>
										<a class="dropdown-item categoryNo" data-value="2404">신선식품</a>
										<a class="dropdown-item categoryNo" data-value="2405">간편식/밀키트/반찬</a>
										<h5 class="dropdown-header">유아동</h5>
										<a class="dropdown-item categoryNo" data-value="2501">완구</a>
										<a class="dropdown-item categoryNo" data-value="2502">기저귀/분유.물티슈.생리대</a>
										<a class="dropdown-item categoryNo" data-value="2503">출산/임부/유아용품</a>
										<a class="dropdown-item categoryNo" data-value="2504">유아동의류/잡화/임부복</a>
										<h5 class="dropdown-header">디지털/가전</h5>
										<a class="dropdown-item categoryNo" data-value="2601">스마트폰/웨어러블</a>
										<a class="dropdown-item categoryNo" data-value="2602">음향/스마트홈</a>
										<a class="dropdown-item categoryNo" data-value="2603">카메라/드론/VR/360</a>
										<a class="dropdown-item categoryNo" data-value="2604">노트북/데스크탑/태블릿</a>
										<a class="dropdown-item categoryNo" data-value="2605">모니터/프린트/잉크</a>
										<a class="dropdown-item categoryNo" data-value="2606">pc부품/주변기기</a>
										<a class="dropdown-item categoryNo" data-value="2607">저장장치/메모리</a>
										<a class="dropdown-item categoryNo" data-value="2608">게임기/타이틀</a>
										<a class="dropdown-item categoryNo" data-value="2609">tv/세탁기/냉장고</a>
										<a class="dropdown-item categoryNo" data-value="2610">주방가전</a>
										<a class="dropdown-item categoryNo" data-value="2611">계절가전</a>
										<a class="dropdown-item categoryNo" data-value="2612">청소기/이미용/소형가전</a>
										<h5 class="dropdown-header">가구/인테리어</h5>
										<a class="dropdown-item categoryNo" data-value="2701">침실가구/매트리스</a>
										<a class="dropdown-item categoryNo" data-value="2702">학생/사무/아동가구</a>
										<a class="dropdown-item categoryNo" data-value="2703">거실/주방/수납가구</a>
										<a class="dropdown-item categoryNo" data-value="2704">DIT/시공/원예/꽃배달</a>
										<a class="dropdown-item categoryNo" data-value="2705">홈인테리어/조명</a>
										<a class="dropdown-item categoryNo" data-value="2706">침구/커튼/카페트</a>
										<h5 class="dropdown-header">생활/건강/반려</h5>
										<a class="dropdown-item categoryNo" data-value="2801">주방/수입주방</a>
										<a class="dropdown-item categoryNo" data-value="2802">생활/수납/청소/욕실</a>
										<a class="dropdown-item categoryNo" data-value="2803">세제/화장지/구강/면도</a>
										<a class="dropdown-item categoryNo" data-value="2804">건강/의료</a>
										<a class="dropdown-item categoryNo" data-value="2805">반려동물/애완용품</a>
										<h5 class="dropdown-header">공구/오피스/취미</h5>
										<a class="dropdown-item categoryNo" data-value="2901">공구/철물/산업/안전</a>
										<a class="dropdown-item categoryNo" data-value="2902">문구/사무</a>
										<a class="dropdown-item categoryNo" data-value="2903">키덜트/악기/취미</a>

									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="goodsTitle">품명</label>
								<input id="goodsTitle" name="goodsTitle" required class="form-control">
							</div>
							<div class="row">
								<div class="col-sm-8">
									<div class="form-group">
										<label for="goodsSubTitle">부제</label>
										<input id="goodsSubTitle" name="goodsSubTitle" class="form-control">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="goodsStatus">상태</label>
										<input id="goodsStatus" name="goodsStatus" required class="form-control">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label for="goodsPrice">가격</label>
										<input id="goodsPrice" name="goodsPrice" required class="form-control">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-6">
										<label for="goodsCost">배송비</label>
										<input id="goodsCost" name="goodsCost" required class="form-control">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4">
									<div class="form-group">
										<label for="goodsPublicher">메이커</label>
										<input id="goodsPublicher" name="goodsPublicher" required class="form-control">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="goodsMaker">원산지</label>
										<input id="goodsMaker" name="goodsMaker" required class="form-control">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-body">
					<div class="form-group">
						<label for="goodsContent">상품 설명</label>
						<textarea class="form-control" id="goodsContent" name="goodsContent" required></textarea>
					</div>
				</div>
				<div class="card-footer">
					<button class="btn btn-primary">등록</button>
					<button type="reset" class="btn btn-secondary">다시입력</button>
					<button type="button" onclick="history.back();" class="btn btn-warning">취소</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
