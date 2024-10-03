<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InterYard Ticket</title>
<style type="text/css">
.dataRow:hover {
	opacity: 70%; /* 투명도 */
	cursor: pointer;
}

.imageDiv {
	background: black;
}

.title {
	text-overflow: ellipsis;
	overflow: hidden;
	display: -webkit-box;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 2;
}

.card-title {
	white-space: nowrap; /* 텍스트가 한 줄로 유지되도록 합니다 */
	overflow: hidden; /* 넘치는 텍스트를 숨깁니다 */
	text-overflow: ellipsis; /* 넘치는 텍스트를 ...으로 표시합니다 */
	display: block; /* 기본적으로 블록 요소로 표시되도록 합니다 */
	width: 100%; /* 카드 제목의 너비를 100%로 설정합니다 */
	max-width: 300px; /* 카드 제목의 최대 너비를 설정합니다 */
}
</style>

<script type="text/javascript">
	$(function() {

goodsNo = ${list.get(0).goodsNo};
		
		if (${!empty optResult}) {
			<%session.removeAttribute("optResult");%>
			location = "optionWriteForm.do?goodsNo="+goodsNo+"&page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}";		
		}
		
		
		// 제목 해당 태그 중 제일 높은 것을 이용하여 모두 맞추기
		// console.log($(".title"));
		let titleHeights = [];

		$(".title").each(function(idx, title) {
			console.log($(title).height());
			titleHeights.push($(title).height());
		});
		console.log(titleHeights.join(", "));

		let maxTitleHeight = Math.max.apply(null, titleHeights);
		console.log(maxTitleHeight);

		$(".title").height(maxTitleHeight);

		// 이미지 사이즈 조정 5:4
		let imgWidth = $(".imageDiv:first").width();
		let imgHeight = $(".imageDiv:first").height();
		console.log("image width=" + imgWidth + ", height=" + imgHeight)
		// 높이 계산 - 너비는 동일하다. : 이미지와 이미지를 감싸고 있는 div의 높이로 사용
		let height = imgWidth / 5 * 4;
		// 전체 imageDiv의 높이를 조정한다.
		$(".imageDiv").height(height);
		// 이미지 배열로 처리하면 안된다. foreach 사용 - jquery each()
		$(".imageDiv > img").each(function(idx, image) {
			//alert(image);
			//alert(height);
			//alert($(image).height());
			// 이미지가 계산된 높이 보다 크면 줄인다.
			if ($(image).height() > height) {
				let image_width = $(image).width();
				let image_height = $(image).height();
				let width = height / image_height * image_width;

				console.log("chaged image width = " + width);

				// 이미지 높이 줄이기
				$(image).height(height);
				// 이미지 너비 줄이기
				$(image).width(width);

			}
		});

		// 이벤트 처리
		$(".dataRow").click(
				function() {
					// alert("click");
					// 글번호 필요 - 수집
					let goodsNo = $(this).find(".goodsNo").data("goodsno");
					console.log("goodsNo = " + goodsNo);
					location = "ticketView.do?goodsNo=" + goodsNo
							+ "&${pageObject.pageQuery}";
				});

		// perPageNum 처리
		$("#perPageNum").change(function() {
			// alert("change perPageNum");
			// page는 1페이지 + 검색 데이터를 전부 보낸다.
			$("#searchForm").submit();
		});

		// 검색 데이터 세팅
		$("#key").val("${(empty pageObject.key)?'nt':pageObject.key}");
		$("#perPageNum").val(
				"${(empty pageObject.perPageNum)?'12':pageObject.perPageNum}");
	});
</script>

</head>
<body>
	<div class="container-fluid">
		<div class="row flex-nowrap">
			<jsp:include page="ticketSidebar.jsp"></jsp:include>
			<main class="col-9 py-md-3 pl-md-5 bd-content" role="main">
				<div class="container">
					<h2>
						<b>InterYard Ticket</b>
					</h2>
					<form action="list.do" id="searchForm">
						<input name="page" value="1" type="hidden">
						<div class="row">
							<div class="col-md-8">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<select name="key" id="key" class="form-control">
											<option value="nt">제목</option>
											<option value="nm">장소</option>
										</select>
									</div>
									<input type="text" class="form-control" placeholder="검색" id="word" name="word" value="">
									<div class="input-group-append">
										<button class="btn btn-outline-primary">
											<i class="fa fa-search"></i>
										</button>
									</div>
								</div>
							</div>
							<!-- col-md-8의 끝 : 검색 -->
							<div class="col-md-4">
								<!-- 너비를 조정하기 위한 div 추가. float-right : 오른쪽 정렬 -->
								<div style="width: 200px;" class="float-right">
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Rows/Page</span>
										</div>
										<select id="perPageNum" name="perPageNum" class="form-control">
											<option>12</option>
											<option>24</option>
											<option>36</option>
											<option>48</option>
										</select>
									</div>
								</div>
							</div>
							<!-- col-md-4의 끝 : 한페이지당 표시 데이터 개수 -->
						</div>
					</form>
					<c:if test="${empty list }">
						<div class="jumbotron">
							<h4>데이터가 존재하지 않습니다.</h4>
						</div>
					</c:if>

					<c:if test="${!empty list }">
						<div class="row">
							<!-- 이미지의 데이터가 있는 만큼 반복해서 표시하는 처리 시작 -->
							<c:forEach items="${list }" var="vo" varStatus="vs">
								<!-- 줄바꿈처리 - 찍는 인덱스 번호가 3의 배수이면 줄바꿈을 한다. -->
								<c:if test="${(vs.index != 0) && (vs.index % 4 == 0) }">
	  			${"</div>"}
	  			${"<div class='row'>"}
	  		</c:if>
								<!-- 데이터 표시 시작 -->
								<div class="col-md-3 dataRow">
									<div class="card" style="width: 100%">
										<div class="imageDiv text-center align-content-center">
											<img class="card-img-top" src="${vo.goodsImage }" alt="image">
										</div>
										<div class="card-body">
											<!-- 표시되는 내용 : 제목, 장소, 시작일, 종료일, 숨은데이터 : 번호 -->
											<strong class="card-title"> ${vo.goodsTitle } </strong>
											<p class="card-text title">
												장소 : <b>${vo.goodsMaker }</b><br> ${vo.goodsStartDate } ~ ${vo.goodsEndDate} <span class="goodsNo"
													data-goodsNo="${vo.goodsNo}"
												></span>
											</p>
										</div>
									</div>
								</div>
								<!-- 데이터 표시 끝 -->
							</c:forEach>
							<!-- 이미지의 데이터가 있는 만큼 반복해서 표시하는 처리 끝 -->
						</div>

						<!-- 페이지 네이션 처리 -->
						<div>
							<pageNav:pageNav listURI="ticketList.do" pageObject="${pageObject }" />
						</div>

					</c:if>
					<c:if test="${login.gradeNo == 9 }">
						<!-- 리스트 데이터 표시의 끝 -->
						<a href="ticketWriteForm.do?perPageNum=${pageObject.perPageNum }" class="btn btn-primary">등록</a>
					</c:if>
				</div>
			</main>
		</div>
	</div>
</body>
</html>