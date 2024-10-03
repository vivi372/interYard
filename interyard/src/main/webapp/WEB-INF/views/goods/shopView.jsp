<%@page import="com.interyard.goods.vo.GoodsVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 보기</title>
<script type="text/javascript">
	$(function() {
		$('[data-toggle="tooltip"]').tooltip();

		// 이벤트 처리
		$("#deleteBtn").click(function() {
			// js 경고창 - alert : 일반 경고, conform : 확인/취소, prompt : 키인
			// 확인 창이 나타나는데 취소를 누르면 삭제 페이지 이동을 취소시킨다.
			if (!confirm("정말삭제하시겠습니까?"))
				return false;
		});
		$("#reviewDiv").load("/review/list.do?goodsNo=${param.goodsNo}");
	});
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row flex-nowrap">
			<jsp:include page="shopSidebar.jsp"></jsp:include>

			<div class="container">
				<div class="card">
					<div class="card-header">
						<div class="row">
							<div class="col-md-6">
								<img src="${vo.goodsImage }" alt="image" style="width: 450px; height: 500px;">
							</div>
							<div class="col-md-6">
								<h4>
									<!-- 제품명 -->
									<b>${vo.goodsTitle }</b>
								</h4>
								<!-- 부제와 판매상태 -->
								<h5>${vo.goodsSubTitle },${vo.goodsStatus }</h5>
								<hr>
								<!-- 가격 -->
								<h4>
									<b>${vo.goodsPrice } 원</b><br>
								</h4>
								배송비 : ${vo.goodsCost }
								<hr>
								<!-- 사이즈 메이커 원산지 -->
								메이커 : ${vo.goodsPublicher }, 원산지 : ${vo.goodsMaker }<br> 이벤트/혜택 :
								<a class="btn btn-info">확인하기</a>
								<hr>
								<!-- 옵션이 들어갑니다 -->
								<div style="width: 500px"><jsp:include page="/WEB-INF/views/basket/write.jsp"></jsp:include></div>
							</div>
						</div>
					</div>
					<div class="card-body">
						<h4>
							<b>상품 소개</b>
						</h4>
						<hr>
						${vo.goodsContent }
						<hr>
						<%
						GoodsVO vo = (GoodsVO) request.getAttribute("vo");
						if (vo != null) {
							String imageUri = "/image/s" + vo.getGoodsNo() + "c.jpg";
						%>
						<img src="<%=imageUri%>" alt="동적 상품 이미지" style="max-width: 100%; height: auto;">
						<%
						} else {
						%>
						<p>상품 정보를 불러오는 데 실패했습니다.</p>
						<%
						}
						%>
					</div>
					<div class="card-footer" id="reviewDiv" name="reviewDiv">
						<!-- 리뷰페이지 -->
						<jsp:include page="/WEB-INF/views/review/list.jsp"></jsp:include>
					</div>
					<div class="btn-group">
						<c:if test="${login.gradeNo == 9 }">
							<!-- a tag : 데이터를 클릭하면 href의 정보를 가져와서 페이지 이동시킨다. -->
							<a
								href="optionWriteForm.do?goodsNo=${param.goodsNo }&page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
								class="btn btn-primary" id="updateBtn"
							>옵션추가</a>
							<a
								href="shopUpdateForm.do?goodsNo=${param.goodsNo }&page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
								class="btn btn-primary" id="updateBtn"
							>수정</a>
							<a class="btn btn-danger" id="deleteBtn"
								href="delete.do?goodsNo=${vo.goodsNo }&goodsImage=${vo.goodsImage}&perPageNum=${param.perPageNum}&categoryNo=${vo.categoryNo}"
							>삭제</a>
						</c:if>
						<a href="shopList.do?page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
							class="btn btn-info"
						>돌아가기</a>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- container의 끝 -->

</body>
</html>