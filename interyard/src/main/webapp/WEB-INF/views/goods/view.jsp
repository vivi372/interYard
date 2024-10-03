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
			<jsp:include page="sidebar.jsp"></jsp:include>

			<div class="container">
				<div class="card">
					<div class="card-header">
						<div class="row">
							<div class="col-md-4">
								<img src="${vo.goodsImage }" alt="image">
							</div>
							<div class="col-md-8">
								<h4>
									<b>${vo.goodsTitle }</b>
								</h4>
								<h5>${vo.goodsSubTitle }</h5>
								<hr>
								<!-- 저자, 역자, 출판사, 출판일 -->
								저자 : ${vo.goodsMaker }, 역자 : ${vo.goodsTrans }, 출판사 : ${vo.goodsPublicher }, 등록일 : ${vo.goodsWriteDate }<br>
								<!-- 발행일, 쪽수, ISBN -->
								발행일 : ${vo.goodsStartDate }, 쪽수 : ${vo.goodsRunTime }, ISBN : ${vo.goodsNo } <br> 등급 : ${vo.goodsRating },
								판매상태 : ${vo.goodsStatus }
								<hr>
								<!-- 가격 -->
								<h4>
									<b>정가 : ${vo.goodsPrice }원</b>
								</h4>
								<br> 이벤트/혜택 :
								<a class="btn btn-info">확인하기</a>
								<br>
								<!-- 배송비 -->
								배송비 : ${vo.goodsCost }원
								<hr>
								<!-- 옵션이 들어갑니다 -->
								<div style="width: 500px"><jsp:include page="/WEB-INF/views/basket/write.jsp"></jsp:include></div>
								<hr>
							</div>
						</div>
					</div>
					<div class="card-body">
						<h4>
							<b>상품 소개</b>
						</h4>
						<hr>
						${vo.goodsContent }
					</div>
					<div class="card-footer" id="reviewDiv" name="reviewDiv">
						<!-- 리뷰페이지 -->
						<jsp:include page="/WEB-INF/views/review/list.jsp"></jsp:include>

					</div>
					<div class="btn-group">
						<!-- a tag : 데이터를 클릭하면 href의 정보를 가져와서 페이지 이동시킨다. -->
						<c:if test="${login.gradeNo == 9 }">
							<a
								href="updateForm.do?goodsNo=${param.goodsNo }&page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
								class="btn btn-primary" title="이미지를 제외한 정보만 수정합니다." data-toggle="tooltip" data-placement="top" id="updateBtn"
							>수정</a>
							<a class="btn btn-danger" id="deleteBtn"
								href="delete.do?goodsNo=${vo.goodsNo }&goodsImage=${vo.goodsImage}&perPageNum=${param.perPageNum}&categoryNo=${vo.categoryNo}"
							>삭제</a>
						</c:if>
						<a href="list.do?page=${param.page }&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
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