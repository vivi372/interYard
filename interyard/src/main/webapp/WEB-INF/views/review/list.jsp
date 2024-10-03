<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<script type="text/javascript">

$(function() {
	// 삭제 버튼 - 모달 이동
	$(".deleteBtn").click(function() {
		let revNo = $(this).data("no");
		let title = $(this).data("title");
		let id = $(this).data("id");
		let name = $(this).data("name");
		let imgFile = $(this).data("file");
		let orderNo = $(this).data("orderno");
		console.log("deleteNo = " + revNo);
		$("#revNoInput").val(revNo); // 모달 창의 히든 필드에 qnaNo 설정
		$("#imgFileInput").val(imgFile); // 모달 창의 히든 필드에 qnaNo 설정
		$("#orderNo").val(orderNo); // 모달 창의 히든 필드에 qnaNo 설정
		$("#revNameDisplay").text(name); // 모달 창의 표시 요소에 qnaNo 설정
		$("#revTitleDisplay").text(title); // 모달 창의 표시 요소에 qnaNo 설정
		$("#revIdDisplay").text(id); // 모달 창의 표시 요소에 qnaNo 설정
	    $("#deleteModal").modal('show'); // 모달 창 열기
	});
	
	$("#lastDeleteBtn").click(function() {
		let revNo = $("#revNoInput").val();
		let imgFile = $("#imgFileInput").val();
		let goodsNo = $("#goodsNo").val();
		let orderNo = $("#orderNo").val();
		console.log("lastDeleteBtn No = " + revNo);
		location = "/review/delete.do?revNo=" + revNo + "&imgFile=" + imgFile + "&goodsNo=" + goodsNo + "&orderNo=" + orderNo;
	});
	
})
	function openNewWindow() {
		// 데이터 받아서 넘기기
		let orderNo = $("#writeBtn").data("orderno");
		let goodsNo = $("#writeBtn").data("goodsno");

		// 새로운 창을 열고 싶은 URL을 입력합니다.
		var url = "/review/reviewForm.do?orderNo=" + orderNo + "&goodsNo="
				+ goodsNo;
		var windowName = "리뷰쓰기"; // 창의 이름을 지정합니다.
		var windowFeatures = "width=800,height=940"; // 창의 크기와 특성을 지정합니다.
		var popupWindow = window.open(url, windowName, windowFeatures);
		
        // 팝업 창이 닫혔는지 확인하는 함수
        var checkPopupClosed = setInterval(function() {
            if (popupWindow.closed) {
                clearInterval(checkPopupClosed);
                // 부모 창 새로고침
                location.reload("/order/list.do");
            }
        }, 500); // 0.5초마다 체크
	}
</script>
<style>
.filled {
	color: orange; /* 예: 좋아요가 채워진 상태를 나타내는 색상 */
	font-weight: bold; /* 채워진 상태를 강조하기 위한 스타일 */
}

.unfilled {
	color: gray; /* 예: 좋아요가 비어있는 상태를 나타내는 색상 */
}
.dataRow:hover {
	cursor: pointer;
	background: #e8e8e8;
}
</style>
<p>
<h4>
	리뷰<strong> ${totalReview.totalReview } 건 </strong>
</h4>
<hr>

<c:if test="${empty list }">
	<h5>
		리뷰가 없습니다.<br> 상품을 구매하고 리뷰의 첫번째 주인공이 되세요!
	</h5>
</c:if>
<c:if test="${!empty list }">
	<div class="p-3 mb-0 ">
		<div class="row">
			<div class="col-sm-4 p-2">
				<div style="text-align: center;">
					<span class="mb-3"><b>사용자 총 평점</b></span>
					<p>
						<br>
						<c:if
							test="${totalReview.avgReview >= 0 && totalReview.avgReview <= 0.32}"
						>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
						</c:if>
						<c:if
							test="${totalReview.avgReview >= 0.25 && totalReview.avgReview <= 0.74}"
						>
							<i class="fa fa-star-half-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
						</c:if>
						<c:if
							test="${totalReview.avgReview >= 0.75 && totalReview.avgReview <= 1.32}"
						>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
						</c:if>
						<c:if
							test="${totalReview.avgReview >= 1.25 && totalReview.avgReview <= 1.74}"
						>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-half-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
						</c:if>
						<c:if
							test="${totalReview.avgReview >= 1.75 && totalReview.avgReview <= 2.32}"
						>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
						</c:if>
						<c:if
							test="${totalReview.avgReview >= 2.25 && totalReview.avgReview <= 2.74}"
						>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-half-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
						</c:if>
						<c:if
							test="${totalReview.avgReview >= 2.75 && totalReview.avgReview <= 3.32}"
						>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
						</c:if>
						<c:if
							test="${totalReview.avgReview >= 3.25 && totalReview.avgReview <= 3.74}"
						>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-half-o" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
						</c:if>
						<c:if
							test="${totalReview.avgReview >= 3.75 && totalReview.avgReview <= 4.32}"
						>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-o" style="font-size: 40px; color: orange;"></i>
						</c:if>
						<c:if
							test="${totalReview.avgReview >= 4.25 && totalReview.avgReview <= 4.74}"
						>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star-half-o" style="font-size: 40px; color: orange;"></i>
						</c:if>
						<c:if
							test="${totalReview.avgReview >= 4.75 && totalReview.avgReview <= 5}"
						>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
							<i class="fa fa-star" style="font-size: 40px; color: orange;"></i>
						</c:if>
						<br><Br> <span style="font-size: 28px;"><strong>${totalReview.avgReview }</strong></span>
						<span style="font-size: 28px; color: #999999;">/ 5</span>
					<p>
				</div>
			</div>
			<div class="col-sm-4 p-2">
				<div style="text-align: center;">
					<div style="text-align: center;">
						<span class="mb-3"><b>전체 리뷰 수</b></span><br><br> <i
							class="fa fa-commenting" style="font-size: 48px; color: #999999"
						></i> <br> <span style="font-size: 40px;"><strong>${totalReview.totalReview }</strong></span>
					</div>
				</div>
			</div>
			<div class="col-sm-4 p-2">
				<div style="text-align: center;">
					<div style="text-align: center;">
						<span class="mb-1"><b>점수별 리뷰 수</b></span><br><br>
						<div class="row">
							<div class="col-sm-4">
								<i class="fa fa-star" style="color: orange;"></i>
								<span> ~ 5 점 </span>
							</div>
							<div class="col-sm-6 p-1">
								<div class="progress" style="width: 100%;">
								   	<div class="progress-bar 
								   		${(gradeCnt.grade5 > gradeCnt.grade4 && 
								   			gradeCnt.grade5 > gradeCnt.grade3 &&
								   			gradeCnt.grade5 > gradeCnt.grade2 &&
								   			gradeCnt.grade5 > gradeCnt.grade1
								   			)?'bg-warning':'bg-secondary'
								   		}" 
								   		style="width: ${gradeCnt.grade5 / totalReview.totalReview * 100}%;">
								  	</div><br>
								</div>
							</div>
							<div class="col-sm-2">
								<span class="badge p-1 ml-3 
											${(gradeCnt.grade5 > gradeCnt.grade4 && 
								   			gradeCnt.grade5 > gradeCnt.grade3 &&
								   			gradeCnt.grade5 > gradeCnt.grade2 &&
								   			gradeCnt.grade5 > gradeCnt.grade1
								   			)?'bg-warning':''
								   		}" style="font-size:15px;">${gradeCnt.grade5 }</span>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4">
								<i class="fa fa-star" style="color: orange;"></i>
								<span> ~ 4 점 </span>
							</div>
							<div class="col-sm-6 p-1">
								<div class="progress" style="width: 100%;">
								   	<div class="progress-bar 
								   		${(gradeCnt.grade4 > gradeCnt.grade5 && 
								   			gradeCnt.grade4 > gradeCnt.grade3 &&
								   			gradeCnt.grade4 > gradeCnt.grade2 &&
								   			gradeCnt.grade4 > gradeCnt.grade1
								   			)?'bg-warning':'bg-secondary'
								   		}" 
								   		style="width: ${gradeCnt.grade4 / totalReview.totalReview * 100}%;">
								  	</div><br>
								</div>
							</div>
							<div class="col-sm-2">
								<span class="badge p-1 ml-3
											${(gradeCnt.grade4 > gradeCnt.grade5 && 
								   			gradeCnt.grade4 > gradeCnt.grade3 &&
								   			gradeCnt.grade4 > gradeCnt.grade2 &&
								   			gradeCnt.grade4 > gradeCnt.grade1
								   			)?'bg-warning':''
								   		}" style="font-size:15px;">${gradeCnt.grade4 }</span>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4">
								<i class="fa fa-star" style="font-size: 18px; color: orange;"></i>
								<span> ~ 3 점 </span>
							</div>
							<div class="col-sm-6 p-1">
								<div class="progress" style="width: 100%;">
								   	<div class="progress-bar 
								   		${(gradeCnt.grade3 > gradeCnt.grade5 && 
								   			gradeCnt.grade3 > gradeCnt.grade4 &&
								   			gradeCnt.grade3 > gradeCnt.grade2 &&
								   			gradeCnt.grade3 > gradeCnt.grade1
								   			)?'bg-warning':'bg-secondary'
								   		}"  style="width: ${gradeCnt.grade3 / totalReview.totalReview * 100}%;">
								  	</div><br>
								</div>
							</div>
							<div class="col-sm-2">
								<span class="badge p-1 ml-3 
											${(gradeCnt.grade3 > gradeCnt.grade5 && 
								   			gradeCnt.grade3 > gradeCnt.grade4 &&
								   			gradeCnt.grade3 > gradeCnt.grade2 &&
								   			gradeCnt.grade3 > gradeCnt.grade1
								   			)?'bg-warning':''
								   		}" style="font-size:15px;">${gradeCnt.grade3 }</span>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4">
								<i class="fa fa-star" style="font-size: 18px; color: orange;"></i>
								<span> ~ 2 점 </span>
							</div>
							<div class="col-sm-6 p-1">
								<div class="progress" style="width: 100%;">
								   	<div class="progress-bar 
								   		${(gradeCnt.grade2 > gradeCnt.grade5 && 
								   			gradeCnt.grade2 > gradeCnt.grade4 &&
								   			gradeCnt.grade2 > gradeCnt.grade3 &&
								   			gradeCnt.grade2 > gradeCnt.grade1
								   			)?'bg-warning':'bg-secondary'
								   		}"  style="width: ${gradeCnt.grade2 / totalReview.totalReview * 100}%;">
								  	</div><br>
								</div>
							</div>
							<div class="col-sm-2">
								<span class="badge p-1 ml-3
											${(gradeCnt.grade2 > gradeCnt.grade5 && 
								   			gradeCnt.grade2 > gradeCnt.grade4 &&
								   			gradeCnt.grade2 > gradeCnt.grade3 &&
								   			gradeCnt.grade2 > gradeCnt.grade1
								   			)?'bg-warning':''
								   		}" style="font-size:15px;">${gradeCnt.grade2 }</span>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4">
								<i class="fa fa-star" style="font-size: 18px; color: orange;"></i>
								<span> ~ 1 점 </span>
							</div>
							<div class="col-sm-6 p-1">
								<div class="progress" style="width: 100%;">
								   	<div class="progress-bar 
								   		${(gradeCnt.grade1 > gradeCnt.grade5 && 
								   			gradeCnt.grade1 > gradeCnt.grade4 &&
								   			gradeCnt.grade1 > gradeCnt.grade3 &&
								   			gradeCnt.grade1 > gradeCnt.grade2
								   			)?'bg-warning':'bg-secondary'
								   		}"  style="width: ${gradeCnt.grade1 / totalReview.totalReview * 100}%;">
								  	</div><br>
								</div>
							</div>
							<div class="col-sm-2">
								<span class="badge p-1 ml-3
											${(gradeCnt.grade1 > gradeCnt.grade5 && 
								   			gradeCnt.grade1 > gradeCnt.grade4 &&
								   			gradeCnt.grade1 > gradeCnt.grade3 &&
								   			gradeCnt.grade1 > gradeCnt.grade2
								   			)?'bg-warning':''
								   		}" style="font-size:15px;">${gradeCnt.grade2 }</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<hr>
	<c:forEach items="${list }" var="vo">
		<div class="media p-3 dataRow" data-toggle="collapse"
			data-target="#reviewContent${vo.revNo }"
		>
			<img src="${(!empty vo.photo)? vo.photo : '/upload/member/noImage.png' }" alt="i"
				class="mr-3 mt-3 rounded-circle" style="width: 30px; height: 30px;"
			>
			<div class="media-body">
				<span class="float-right mr-4"> 
					<c:if test="${login.gradeNo == 9 }">
						<button  class="btn btn-light brn-sm deleteBtn" data-toggle="modal" data-target="#myModal"
							data-no="${vo.revNo }" data-title="${vo.title }" data-id="${vo.id }"
							data-name="${vo.name }" data-file="${vo.imgFile }" data-orderno="${vo.orderNo }">
							<b>삭제</b>
						</button>
					</c:if> 
					${vo.writeDate }<br>
				</span>
				<c:if test="${vo.grade == 0 }">
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
				</c:if>
				<c:if test="${vo.grade == 0.5 }">
					<i class="fa fa-star-half-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
				</c:if>
				<c:if test="${vo.grade == 1 }">
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
				</c:if>
				<c:if test="${vo.grade == 1.5 }">
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-half-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
				</c:if>
				<c:if test="${vo.grade == 2 }">
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
				</c:if>
				<c:if test="${vo.grade == 2.5 }">
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-half-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
				</c:if>
				<c:if test="${vo.grade == 3 }">
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
				</c:if>
				<c:if test="${vo.grade == 3.5 }">
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-half-o" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
				</c:if>
				<c:if test="${vo.grade == 4 }">
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-o" style="font-size: 24px; color: orange;"></i>
				</c:if>
				<c:if test="${vo.grade == 4.5 }">
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star-half-o" style="font-size: 24px; color: orange;"></i>
				</c:if>
				<c:if test="${vo.grade == 5 }">
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
					<i class="fa fa-star" style="font-size: 24px; color: orange;"></i>
				</c:if>
				<span style="color: orange;">${vo.grade }</span>
				<p>
				<h5 class="mt-1 mb-0">
					<strong>${vo. title } </strong> 
					<small><i data-id="${vo.id }">${vo.blindName }(${vo.blindId })</i> 
						
						<span class="badge badge-pill badge-secondary ml-3 mr-2 p-2"> 
							<c:if test="${vo.optName != '/' }">
								${vo.optName } / 
							</c:if>
							 구매 
						</span> 
					 </small>
					 <c:if test="${!empty vo.imgFile }">
						<i class="fa fa-file-image-o" style="font-size:18px;"></i>
					</c:if>
				</h5 >
				<div id="reviewContent${vo.revNo }" class="collapse">
					<hr>
					<p>${vo. content }</p>
					<c:if test="${!empty vo.imgFile}">
						<img src="${vo.imgFile }" alt="${vo.imgFile }" showed>
						<input name="deleteFileName" value="${vo.imgFile }" type="hidden">
						<hr>
					</c:if>
				</div>

			</div>
		</div>
	</c:forEach>
</c:if>

<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">리뷰 삭제</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
    	  <input type="hidden" id="revNoInput" value="">
    	  <input type="hidden" id="imgFileInput" value="">
    	  <input type="hidden" id="orderNo" value="">
    	  <input type="hidden" id="goodsNo" value="${param.goodsNo }">
	      	<p>
		        <b><span id="revNameDisplay"></span></b><i>(<span id="revIdDisplay"></span>)</i> 님의 <br>
		         리뷰 :  <b><span id="revTitleDisplay"></span></b>
		        <p> 를 정말 삭제하시겠습니까? 
		        <br> 삭제하면 다시 복구할 수 없습니다. 
		        <br><br> <b>그래도 삭제하려면 Delete 버튼을 누르세요.</b>
	        <p>
        
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button id="lastDeleteBtn" type="button" class="btn btn-danger" >Delete</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
