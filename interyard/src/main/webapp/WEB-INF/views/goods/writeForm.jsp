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
		<form action="write.do" method="post" enctype="multipart/form-data">
			<input name="perPageNum" value="${param.perPageNum }" type="hidden">
			<input type="hidden" id="selectedCategory" name="categoryNo">
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
										<h5 class="dropdown-header">국내도서</h5>
										<a class="dropdown-item categoryNo" data-value="1101">소설</a>
										<a class="dropdown-item categoryNo" data-value="1102">시.에세이</a>
										<a class="dropdown-item categoryNo" data-value="1103">경제경영</a>
										<a class="dropdown-item categoryNo" data-value="1104">자기개발</a>
										<a class="dropdown-item categoryNo" data-value="1105">사회과학</a>
										<a class="dropdown-item categoryNo" data-value="1106">역사문화</a>
										<a class="dropdown-item categoryNo" data-value="1107">예술대중문화</a>
										<a class="dropdown-item categoryNo" data-value="1108">인문</a>
										<a class="dropdown-item categoryNo" data-value="1109">자연과학</a>
										<a class="dropdown-item categoryNo" data-value="1110">종교,역학</a>
										<a class="dropdown-item categoryNo" data-value="1111">유아</a>
										<a class="dropdown-item categoryNo" data-value="1112">아동</a>
										<a class="dropdown-item categoryNo" data-value="1113">가정과생활</a>
										<a class="dropdown-item categoryNo" data-value="1114">청소년</a>
										<a class="dropdown-item categoryNo" data-value="1115">초등학습</a>
										<a class="dropdown-item categoryNo" data-value="1116">중등학습</a>
										<a class="dropdown-item categoryNo" data-value="1117">고등학습</a>
										<a class="dropdown-item categoryNo" data-value="1118">국어,외국어사전</a>
										<a class="dropdown-item categoryNo" data-value="1119">자격서수험서</a>
										<a class="dropdown-item categoryNo" data-value="1120">공무원수험서</a>
										<a class="dropdown-item categoryNo" data-value="1121">컴퓨터.인터넷</a>
										<a class="dropdown-item categoryNo" data-value="1122">전공.대학교재</a>
										<a class="dropdown-item categoryNo" data-value="1123">여행</a>
										<a class="dropdown-item categoryNo" data-value="1124">취미레저</a>
										<a class="dropdown-item categoryNo" data-value="1125">건강뷰티</a>
										<a class="dropdown-item categoryNo" data-value="1126">잡지</a>
										<a class="dropdown-item categoryNo" data-value="1127">만화.라이트노벨</a>
										<a class="dropdown-item categoryNo" data-value="1128">유아동전집</a>
										<h5 class="dropdown-header">외국도서</h5>
										<a class="dropdown-item categoryNo" data-value="1201">어린이</a>
										<a class="dropdown-item categoryNo" data-value="1202">문학</a>
										<a class="dropdown-item categoryNo" data-value="1203">ELT.사전</a>
										<a class="dropdown-item categoryNo" data-value="1204">컴퓨터</a>
										<a class="dropdown-item categoryNo" data-value="1205">경제.인문</a>
										<a class="dropdown-item categoryNo" data-value="1206">실용.예술</a>
										<a class="dropdown-item categoryNo" data-value="1207">대학전문서적</a>
										<a class="dropdown-item categoryNo" data-value="1208">해외잡지</a>
										<a class="dropdown-item categoryNo" data-value="1209">학습교구/게임</a>
										<h5 class="dropdown-header">도서굿즈</h5>
										<a class="dropdown-item categoryNo" data-value="1301">문구/팬시</a>
										<a class="dropdown-item categoryNo" data-value="1302">사무/학용</a>
										<a class="dropdown-item categoryNo" data-value="1303">학습</a>
										<a class="dropdown-item categoryNo" data-value="1304">독서용품</a>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="goodsTitle">제목</label>
								<input id="goodsTitle" name="goodsTitle" required class="form-control">
							</div>
							<div class="form-group">
								<label for="goodsSubTitle">부제</label>
								<input id="goodsSubTitle" name="goodsSubTitle" required class="form-control">
							</div>
							<div class="row">
								<div class="col-sm-3">
									<div class="form-group">
										<label for="goodsMaker">저자</label>
										<input id="goodsMaker" name="goodsMaker" required class="form-control">
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label for="goodsTrans">역자</label>
										<input id="goodsTrans" name="goodsTrans" required class="form-control">
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label for="goodsPublicher">출판사</label>
										<input id="goodsPublicher" name="goodsPublicher" required class="form-control">
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label for="goodsRunTime">쪽수</label>
										<input id="goodsRunTime" name="goodsRunTime" required class="form-control">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-3">
									<div class="form-group">
										<label for="goodsNo">ISBN</label>
										<input id="goodsNo" name="goodsNo" required class="form-control">
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label for="goodsRating">등급</label>
										<input id="goodsRating" name="goodsRating" required class="form-control">
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label for="goodsStatus">상태</label>
										<input id="goodsStatus" name="goodsStatus" required class="form-control">
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label for="goodsCost">배송비</label>
										<input id="goodsCost" name="goodsCost" required class="form-control">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4">
									<div class="form-group">
										<label for="goodsPrice">정가</label>
										<input id="goodsPrice" name="goodsPrice" required class="form-control">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="goodsStartDate">출판일</label>
										<input id="goodsStartDate" name="goodsStartDate" required autocomplete="off" class="form-control datepicker">
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
