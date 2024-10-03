<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 내역</title>

<style>
.page-link {
  color: #444;  
  border-color: #444;
}

.page-item.active .page-link {
 z-index: 1;
 color: #ccc;
 font-weight:bold;
 background-color: #333;
  border-color: #444;
 
}

.page-link:focus, .page-link:hover {
  color: #ccc;
  background-color: #222; 
  border-color: #444;
}

.custom-control-input:checked ~ .custom-control-label::before {
    background-color: #555; /* 중간 회색 */
    border-color: #555; /* 중간 회색 */
}       

.dataRow {
	cursor: pointer;
} 
</style>
<script type="text/javascript" src="/js/priceUtils.js"></script>
<link href="/css/basketOrder/basic.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(function() {
	//주문 가격과 배송비에 콤마 삽입
	$(".orderPrice").each(function() {
		printComma($(this));
	});
	$(".dlvyCharge").each(function() {
		printComma($(this));
	});
	//주문 취소 요청 버튼 클릭 이벤트
	$(".orderCancleBtn").click(function() {
		//해당 listItem에 존재하는 orderno 데이타를 찾아 변수에 저장한다.
		let orderNo = $(this).closest(".orderListItem").data("orderno");
		//주문 취소 요청 모달의 orderNo 입력 태그에 변수 orderNo 입력
		$("#modalOrderNo").val(orderNo);
	});
	//구매확인 버튼 클릭
	$(".orderConfirmBtn").click(function() {
		//해당 listItem에 존재하는 orderno 데이타를 찾아 변수에 저장한다.
		let orderNo = $(this).closest(".orderListItem").data("orderno");
		//console.log(orderNo);
		//현재 페이지의 uri,'구매 확인' 상태,주문 번호가 입력 되어있는 form 생성
		let confirmForm = `
			 	<form action="stateUpdate.do" method="post" id="confirmForm">
			 		<input type="hidden" name="beforeUri" value="/order/list.do?${pageObject.pageQuery}">
        			<input type="hidden" name="orderState" value="구매 확인">
        			<input type="hidden" name="orderNo" value="\${orderNo}">
        		</form>
			`
		$(".container").append(confirmForm);	
		//그 후 submit
		$("#confirmForm").submit();
		
	});
	//관리자 페이지 가기 체크박스 클릭 이벤트
	$("#adminList").click(function() {
		//주문 관리 페이지로 이동한다.
		location = "/order/adminList.do";
	});
	//상품 이미지,상품 이름 클릭 이벤트
	$(".dataRow").click(function() {
		let listItem = $(this).closest(".orderListItem");
		//해당 상품에 해당하는 상세보기로 이동시킨다.
		let goodsNo = listItem.data("goodsno");
		let categoryNo = listItem.data("categoryno");
		
		if(2000 > categoryNo && categoryNo >=1000){
			location = "/goods/view.do?goodsNo="+goodsNo;		
		} else if(3000> categoryNo && categoryNo >= 2000){
			location = "/goods/shopView.do?goodsNo="+goodsNo;			
		} else if(4000>categoryNo && categoryNo>=3000){
			location = "/goods/ticketView.do?goodsNo="+goodsNo;							
		}
	});
});

</script>

</head>
<body class="bg-light">
<!-- 컨테이너 div -->
<div class="container">
	<div class="border rounded p-3 mb-2 bg-white text-dark shadow-sm">
		<!-- 관리자일 경우 관리자 페이지로 이동 체크박스가 등장한다. -->
		<c:if test="${!empty login && login.gradeNo == 9 }">
			<div class="custom-control custom-switch mb-3 float-right">				
			    <input type="checkbox" class="custom-control-input" id="adminList" value="1">
			    <label class="custom-control-label" for="adminList">관리자 페이지로 이동</label>
	  		</div>
  		</c:if>
		<h3>주문 목록</h3>
		<hr>
		<!-- 데이터를 칸을 나누어 보여주기 위해 부트스트랩에 리스트 그룹 사용 -->
		<ul class="list-group">
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="vo">
			  		<li class="list-group-item orderListItem" data-goodsNo="${vo.goodsNo }" data-orderNo="${vo.orderNo }" data-categoryNo="${vo.categoryNo }">
			  			<!-- 주문 상태 -->
			  			<c:if test="${vo.orderState=='취소 요청'}">
			  				<h6><b class="text-danger">취소 요청</b></h6>
			  			</c:if>
			  			<c:if test="${vo.orderState!='취소 요청'}">			  			
			  				<h6><b>${(vo.orderState=='구매 확인(리뷰작성)')?'구매 확인':vo.orderState}</b></h6>
			  			</c:if>
			  			
			  			<!-- 이미지와 상품 정보칸 옵션칸을 나누기 위해 그리드 시스템 사용 -->
			  			<div class="row">
			  				<div class="col-2">
			  					<!-- 상품 이미지 -->
		  						<img class="rounded img-fluid dataRow" src="${vo.goodsImage }" alt="상품 이미지" width="150" height="150">
		  					</div>
			  				<div class="col-5 d-flex flex-column">
			  					<span class="mb-2">${vo.orderDate } 결제</span>	   
			  					<!-- 티켓일 경우 희망 관람일, 책일 경우 ISBN, 상품일 경우 아무것도 출력되지 않는다. --> 
			  					<c:if test="${vo.categoryNo>=3000 }">
			  						<small class="mb-2">희망 관람일: ${vo.hopeDate }</small>
			  					</c:if> 
			  					<c:if test="${vo.categoryNo<2000 && vo.categoryNo>=1000 }">
			  						<small class="mb-2">ISBN: ${vo.goodsNo }</small>
			  					</c:if> 			  										
			    				<!-- 상품 이름 / 클릭시 상품 상세보기로 이동시키기 위해 dataRow 클래스 추가 -->
			    				<h5><b class="dataRow">${vo.goodsTitle }</b></h5>
			    				<!-- 결제 금액 배송비 -->
			    				<h5><b><span class="orderPrice">${vo.orderPrice }</span><span class="won">원</span></b> <small><span class="dlvyCharge">${vo.dlvyCharge }</span><span class="won">원</span></small></h5>	  
			    				<br>
			    				<!-- 버튼 클릭시 해당 주문번호에 해당하는 상세보기 페이지가 열린다. --> 				
			    				<a href="/order/view.do?orderNo=${vo.orderNo }&categoryNo=${vo.categoryNo }&${pageObject.pageQuery}" class="btn btn-outline-dark mt-auto w-25">주문 상세</a>
		  					</div>  					
		  					<div class="col-5" style="border-left: 1px solid #ccc;">
		  						<!-- 옵션 정보 출력 -->
		  						<c:forEach items="${optList }" var="opt">
		  							<!-- 주문의 주문 번호와 옵션의 주문번호가 같으면 해당 주문의 옵션이므로 출력 -->
			  						<c:if test="${vo.orderNo == opt.orderNo }">
			  							<c:if test="${opt.optName!='/'}">
					  						<!-- 상품 옵션 -->
					  						<h5><span class="badge badge-pill badge-secondary">옵션</span> ${opt.optName }</h5>
				  						</c:if>
				  						<!-- 수량 -->
				  						<h5><span class="badge badge-pill badge-secondary">수량</span> ${opt.amount}개</h5>
				  						<br>
				  						
		  							</c:if>
		  							<!-- 다르면 출력하지 않는다. -->
		  						</c:forEach>	  					
		  					</div>
						</div>
						<hr>
						<div class="text-center orderNo" data-orderno="${vo.orderNo }" data-goodsno="${vo.goodsNo }">								
							<c:if test="${vo.orderState != '취소 요청' && vo.orderState != '구매 확인' && vo.orderState != '구매 확인(리뷰작성)'}">			
								<button class="btn btn-outline-dark ml-1 w-25 orderCancleBtn" data-toggle="modal" data-target="#cancleModal">취소 요청</button>
								<button class="btn btn-outline-dark ml-1 w-25 orderConfirmBtn">구매 확인</button>
							</c:if>
							<!-- 리뷰 버튼 -->
							<c:if test="${vo.orderState == '구매 확인'}">			
								<jsp:include page="/WEB-INF/views/review/reviewBtn.jsp"></jsp:include>
							</c:if>
							<!-- 리뷰까지 작성후 감사 인사 출력 -->
							<c:if test="${vo.orderState == '구매 확인(리뷰작성)'}">	
								<b>고객님의 주문에 감사드립니다.</b>
							</c:if>
							<!-- 취소 요청후 취소 문구 출력 -->
							<c:if test="${vo.orderState == '취소 요청'}">	
								<b>고객님의 취소 요청을 확인했습니다.</b>
							</c:if>
						</div>
			  		</li>	  	
		  		</c:forEach>	
	  		</c:if>
	  		<!-- 아직 주문하지 않았을때 문구 출력 -->
	  		<c:if test="${ empty list }">
	  			<div class="alert alert-dark">
    				 <strong>주문 내역이 없습니다. </strong> 상품을 주문하고 여기에 주문 내역을 확인하세요!
  				</div>
	  		</c:if>
		</ul>
		<!-- 페이지 네이션 -->
		<div class="pagination justify-content-center mt-4">
			<pageNav:pageNav listURI="list.do" pageObject="${pageObject }"></pageNav:pageNav>
		</div>
	</div>  	
</div>
	 
	<!-- 취소 요청 모달 -->	    
	<div class="modal fade" id="cancleModal">
		<div class="modal-dialog modal-dialog-centered">
	    	<div class="modal-content">		        
		        <div class="modal-header">
		          	<h4 class="modal-title"><b>취소 요청</b></h4>
		          	<!-- 모달창이 사라지는 아이콘 버튼 -->
		          	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        </div>	        
		        <form action="stateUpdate.do" method="post">
		        	<input type="hidden" name="beforeUri" value="/order/list.do?${pageObject.pageQuery}">
		        	<input type="hidden" id="modalOrderState" name="orderState" value="취소 요청">
		        	<input type="hidden" id="modalOrderNo" name="orderNo">
			        <div class="modal-body">
			        	<!-- 배송지에 필요한 데이터를 입력받는다. -->
						<div class="form-group">
						  	<label for="cancleReason"><b>취소 사유</b></label>
	 						<!-- 결제창에서만 사용하는 데이터이므로 name x -->
						  	<input type="text" class="form-control" id="cancleReason" name="cancleReason" maxlength="100" placeholder="취소하는 이유를 입력해주세요.">
						</div>		          	
			        </div>	        
			        
			        <div class="modal-footer">
			        	<!-- 배송지 변경을 클릭하면 모달창이 닫히고 입력된 데이터로 화면이 변한다. -->
			          	<button id="dlvyWriteBtn" class="btns">취소 요청</button>
			          	<!-- 모달창이 사라지는 버튼 -->
			          	<button type="button" class="btns" data-dismiss="modal">닫기</button>
			        </div>
			        
			     </form>	        
	      	</div>
	 	</div>
	 </div>
</body>
</html>