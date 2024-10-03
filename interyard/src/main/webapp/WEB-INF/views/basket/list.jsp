<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<!-- 부트스트랩과 제이쿼리를 사용하기 위한 라이브러리 가져오기 -->

<style>
.custom-control-label:hover{
	cursor: pointer;
}
.custom-checkbox .custom-control-input:checked~.custom-control-label::before{
 	background-color: black;
    border-color: black;   
}
.dataRow {
	cursor: pointer;
}
.list-group-item img {
    max-width: 150px; /* 원하는 최대 너비 설정 */
    height: auto;     /* 비율에 맞게 높이 조정 */
    margin-right: 10px; /* 이미지와 텍스트 사이의 간격 */
}
/* 드롭다운 옆의 세모 제거 */
#dropdown::after {
    font-size: 20px;    
}
#priceDetail {
	width: 400px;
	
}
</style>

<!-- 옵션 리스트 관련 js 파일 -->
<script type="text/javascript" src="/js/basket/opt.js"></script>
<!-- 데이터 무결성 검사를 위한 js 파일 -->
<script type="text/javascript" src="/js/BoardInputUtil.js"></script>
<!-- 데이트 피커를 위한 js 파일 -->
<script type="text/javascript" src="/js/datePicker/jquery-ui.min.js"></script>
<!-- 데이트 피커를 위한 css 파일 -->
<link href="/css/datePicker/jquery-ui.css" rel="stylesheet" type="text/css" />
<!-- 장바구니 주문 기본 css -->
<link href="/css/basketOrder/basic.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
$(function() {	
	
	//주문 가격에 ,를 끼어 넣는다.
	$(".orderPrice").each(function() {
		printComma($(this));		
	});
	//배송비에 ,를 끼어 넣는다.
	$(".dlvyCharge").each(function() {	
		if($(this).text() == 0) {
			$(this).text('무료');
		} else
			printComma($(this));
	});
	//가격에 , 붙히기
	$(".printCommaNum").each(function() {
		printComma($(this));
	});
	//데이트 피커 기본 세팅
	let now = new Date();
	$.datepicker.setDefaults({
		  dateFormat: 'yy-mm-dd',		 
		  maxDate: '${vo.goodsEndDate }',
		  prevText: '이전 달',
		  nextText: '다음 달',
		  monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		  monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		  dayNames: ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'],
		  dayNamesShort: ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'],
		  dayNamesMin: ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'],
		  showMonthAfterYear: true,
		  changeMonth: true, 
		  yearSuffix: '년',		 
	});
	
	
	//옵션 수정 버튼 클릭시 이벤트
	$(".optUpdateBtn").click(function() {
		//필요한 데이터 들을 가져온다.
		let listItem = $(this).closest(".basketListItem");
		let goodsImage = listItem.find(".goodsImage").data("goodsiamge");
		let goodsTitle = listItem.find(".goodsTitle").text();
		let dlvyCharge = listItem.find(".dlvyCharge").text();
		let goodsNo = listItem.data("goodsno");	
		let basketNo = listItem.data("basketno");
		let categoryNo = listItem.data("categoryno");		
		
		
		//console.log(goodsPrice);
		
		//가져온 데이터를 옵션 수정 모달에 입력한다.
		$("#updateModalImage").attr("src",goodsImage);
		$("#updateModalTitle").text(goodsTitle);
		if(dlvyCharge == '무료') {
			$("#dlvyWon").text("");
		} else {
			$("#dlvyWon").text("원");
		}
		$("#updateModalCharge").text(dlvyCharge);			
		$("#basketNo").val(basketNo);
		
		//해당 장바구니의 상품 코드를 보내 해당 상품의 옵션 리스트를 가져온다.
		$("#selDiv").load("/opt/list.do?goodsNo="+goodsNo , function() {
			//아작스 통신 성공후
			//책일때 책에 필요한 정보를 추가로 모달에 입력하고 필요없는건 사라지게 한다.
			if(2000 > categoryNo && categoryNo >=1000){
				//console.log("책");		
				$("#updateDlvyPrint").text("배송비");
				$("#datePicker").hide();
				let amount = listItem.find(".amount").val();
				let goodsPrice = numWithoutComma(listItem.find(".orderPrice").text())/amount;
				$(".optPrice").attr("data-basicprice",goodsPrice);
				$(".amount").val(amount);
				$("#totalPrice").text(listItem.find(".orderPrice").text());
				
			} else if(3000> categoryNo && categoryNo >= 2000){
				//상품일때 상품에 필요한 정보를 추가로 모달에 입력하고 필요없는건 사라지게 한다.
				//console.log("상품");
				$("#updateDlvyPrint").text("배송비");
				$("#datePicker").hide();
				//장바구니에 존재하는 옵션과 수량을 찾아 옵션 모달에 생성한다.
				listItem.find(".optNo").each(function() {				
					createTag($(this).val(),$(this).siblings(".amount").val());					
				});
			} else if(4000>categoryNo && categoryNo>=3000){
				//티켓일때 티켓에 필요한 정보를 추가로 모달에 입력하고 필요없는건 사라지게 한다.
				//console.log("티켓");
				$("#updateDlvyPrint").text("수수료");
				let hopeDate = listItem.find(".hopeDate").val();
				$("#datePicker").show();
				//데이트피커에 기본 값을 장바구니의 희망 관람일로 바꾼다.
				$('#datePicker').datepicker('setDate',hopeDate);
				$("#hopeDate").val(hopeDate);
				
				let goodsStartDate = listItem.find(".goodsDate").data("goodsstartdate");
				
				//데이트 피커의 최댓날을 상품의 종료날로 바꾸고 최솟날은 현재날짜와 비교하여 바꾼다.
				$('#datePicker').datepicker('option','minDate',(formatDate(now)>goodsStartDate)?now:goodsStartDate);
				$('#datePicker').datepicker('option','maxDate',listItem.find(".goodsDate").data("goodsenddate"));
				//장바구니에 존재하는 옵션과 수량을 찾아 옵션 모달에 생성한다.
				listItem.find(".optNo").each(function() {				
					createTag($(this).val(),$(this).siblings(".amount").val());					
				});
				
			}			
			//옵션이 하나라뿐이면 옵션 삭제 버튼을 지운다.
			removeDelete();
		});	
	});
	
	//모달이 사라질때 이벤트
	$("#optUpdateModal").on("hidden.bs.modal", function() {		
		//모달에 존재하는 모든 옵션을 지운다.
		$(this).find(".list-group-item").each(function() {
			$(this).remove();
		});		
	});
	//옵션 수정 폼이 submit 이벤트
	$("#updateForm").submit(function() {		
		//주문 가격에서 ,를 뺀후 백엔드로 보낸다.
		$("#orderPrice").val(numWithoutComma($("#totalPrice").text()));
	});
	//체크박스 클릭할때 이벤트
	$(".basketCheckBox").change(function() {		
		//체크된 가격과 수량을 계산해 표시된 값을 바꿔준다.
		checkedPriceAmount();
	});
	//전체 체크 체크박스 클릭 이벤트
	$("#allCheck").change(function() {
		//전체 체크가 체크된 상태면 모든 체크박스를 체크하고
		if($(this).is(":checked")){			
			$(".basketCheckBox").prop('checked',true);
			//아니면 체크를 푼다.
		} else{					
			$(".basketCheckBox").prop('checked',false);
		}
		//체크된 가격과 수량을 계산해 표시된 값을 바꿔준다.
		checkedPriceAmount();
	});
	
	//주문 이벤트
	//단일 주문
	$(".orderWriteBtn").click(function() {
		//모든 input 태그를 비활성화 시킨다.
		$(".orderData").prop("disabled","true");
		
		//주문하기 버튼이 존재하는 곳의 listItem에 input 태그만 비활성화를 푼다.
		$(this).closest(".basketListItem").find(".orderData").each(function() {
			$(this).removeAttr("disabled");
		});
		//그 이후 폼을 submit() 시킨다.
		$("#orderWriteFromBasketForm").submit();
	});
	//다중 주문
	$("#checkedOrderWriteBtn").click(function() {
		//체크된 체크박스만 가져온다.
		$checkedBax = $(".basketCheckBox:checked");
		//체크박스 무결성 체크
		if(!checkedLength($checkedBax)) {
			//전체 주문 가격을 가져와 전체 주문 가격 input에 입력 시킨다.
			$("#totalCheckPriceInput").val(numWithoutComma($("#totalCheckPrice").text()));
			//모든 input 태그를 비활성화 시킨다.
			$(".orderData").prop("disabled","true");
			//체크된 곳의 listItem에 input 태그만 비활성화를 푼다.
			$checkedBax.each(function(index){
				$(this).closest(".basketListItem").find(".orderData").each(function() {
					$(this).removeAttr("disabled");
				});
			});		
			//그 이후 폼을 submit() 시킨다.
			$("#orderWriteFromBasketForm").submit();
		}
	});
	
	
	//장바구니 삭제 이벤트
	//단일 삭제
	$(".basketRemove").click(function() {
		let basketNo = $(this).closest(".list-group-item").find(".basketCheckBox").val();
		location="/basket/delete.do?basketNo="+basketNo;
	});
	//다중 삭제
	$("#checkedDeleteBtn").click(function() {
		//체크된 체크박스만 가져온다
		$checkedBax = $(".basketCheckBox:checked");
		//무결성 체크
		if(!checkedLength($checkedBax)) {
			//체크된 장바구니 번호를 담아 삭제 시킨다.
			let href = "/basket/delete.do?";
			$checkedBax.each(function(index){
				if(index==0) href += "basketNo="+$(this).val();
				else href += "&basketNo="+$(this).val();
			});
			if(confirm("선택하신 "+$checkedBax.length+"개 상품을 장바구니에서 삭제하시겠습니까?")){
				location = href;		
			}
		}
	});
	
	//상품 사진,상품 이름 클릭 시 이벤트
	$(".dataRow").click(function() {
		let listItem = $(this).closest(".basketListItem");
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
	
	
	
	$('#datePicker').datepicker({
		 onSelect: function(date) {
			 $("#hopeDate").val(date);
		},
	});
});
//옵션이 하나일때 삭제 버튼 지우는 함수
function removeDelete() {
	let $listItem = $("#optUpdateModal").find(".list-group-item");
	if($listItem.length == 1)
		$listItem.find(".remove").hide();
};
function removeShow() {
	let $listItem = $("#optUpdateModal").find(".list-group-item");
	if($listItem.length>1){
		$listItem.find(".remove").show();		
	}
};

//체크된 장바구니의 가격과 건수 가져온후 표시해 주는 함수
function checkedPriceAmount() {
	let totalGoodsPrice = 0;
	let totalDlvyPrice = 0;
	let index = 0;
	$(".basketCheckBox:checked").each(function() {
		index += 1;
		let $listItem = $(this).closest(".list-group-item");
		totalGoodsPrice += +(numWithoutComma($listItem.find(".orderPrice").text()));			
		totalDlvyPrice += +(numWithoutComma($listItem.find(".dlvyCharge").data("dlvycharge")));			
	});
	
	$("#totalCheckAmount").text(index);
	$(".totalGoodsPrice").text(numWithComma(totalGoodsPrice));
	$(".totalDlvyPrice").text(numWithComma(totalDlvyPrice));
	$(".totalCheckPrice").text(numWithComma(totalGoodsPrice+(+totalDlvyPrice)));	
	
	
};

function formatDate(dateString) {
    // 주어진 날짜 문자열을 Date 객체로 변환
    var date = new Date(dateString);

    // 연도, 월, 일 추출 (월은 0부터 시작하므로 +1 필요)
    var year = date.getFullYear();
    var month = ("0" + (date.getMonth() + 1)).slice(-2);
    var day = ("0" + date.getDate()).slice(-2);

    // yyyy-mm-dd 형식으로 조합하여 반환
    return year + '-' + month + '-' + day;
}



$(document).on("click",".remove", function() {
	$(this).closest(".list-group-item").remove();
	totalPrice();
	removeDelete();
	
});

$(document).on("change","#opt", function(){					
	createTag($(this).val(),1);
	$(this).val("");
	removeShow();
});

</script>

</head>
<body>
<!-- 컨테이너 div -->
<div class="container">
	<nav class="navbar navbar-expand-sm navbar-dark fixed-bottom" style="background-color: black; height: 80px;">
		<div class="container text-white">
			<span class="ml-2">총 <b><span id="totalCheckAmount">0</span>건</b> 주문금액 <b style="font-size: 24px;"> <span class="totalCheckPrice">0</span>원</b></span>
			<div class="dropup ml-2">
				<span class="dropdown-toggle" id="dropdown" data-toggle="dropdown"></span>
				<div class="dropdown-menu" id="priceDetail">			    
			    	<div class="dropdown-item-text">
						<span class="float-right"><span class="printCommaNum totalGoodsPrice">0</span><span class="won">원</span></span>   						
						<span>총 선택상품금액:</span>   						
   					</div>	
   					<div class="dropdown-item-text">
						<span class="float-right">+<span class="printCommaNum totalDlvyPrice">0</span><span class="won">원</span></span>   						
						<span>총 배송비:</span>   						
   					</div>
   					<div class="dropdown-item-text">
						<span class="float-right"><b><span class="printCommaNum totalCheckPrice">0</span><span class="won">원</span></b></span>   						
						<span><b>총 주문 금액:</b></span>   						
   					</div>		
	    		</div>
    		</div>
			<button class="btn btn-light ml-auto" id="checkedOrderWriteBtn"><b>주문 하기</b></button>
		</div>
	</nav>
	<div class="border rounded p-3 mb-2 bg-white text-dark shadow-sm">
		<h3>장바구니</h3>
		<hr>
		<c:if test="${ !empty list }">
			<div class="row my-3">			
				<div class="custom-control custom-checkbox ml-3 col pt-2">
					<input type="checkbox" class="custom-control-input" id="allCheck">    
					<label class="custom-control-label" for="allCheck"><b>전체 선택</b></label>							
		 		</div>
		 		<div class="text-right col mr-3">
					<button class="btn btn-dark ml-auto" id="checkedDeleteBtn">선택 삭제</button>	
				</div>		
			</div>
		</c:if>
		<!-- 데이터를 칸을 나누어 보여주기 위해 부트스트랩에 리스트 그룹 사용 -->
		<form action="/order/writeForm.do" method="post" id="orderWriteFromBasketForm">
			<input type="hidden" id="totalCheckPriceInput" name="totalCheckPrice"> 
			<ul class="list-group">
				<c:if test="${!empty list }">
					<c:forEach items="${list }" var="vo" varStatus="vs">
				  		<li class="list-group-item basketListItem" data-goodsNo="${vo.goodsNo }" data-basketno="${vo.basketNo }" data-categoryNo="${vo.categoryNo }">
				  			<input type="hidden" value="${vo.goodsNo }" name="goodsNo" class="orderData"> 
				  			<input type="hidden" value="${vo.basketNo }" name="basketNo" class="orderData"> 			
				  			<input type="hidden" value="${vo.hopeDate }" name="hopeDate" class="orderData hopeDate"> 			
				  			<input type="hidden" value="${vs.count }" name="order" class="orderData"> 			
				  			<!-- 이미지와 상품 정보칸 옵션칸을 나누기 위해 그리드 시스템 사용 -->
				  			<div class="row">
				  				<div class="col-1 d-flex flex-column justify-content-center">
					  				<div class="custom-control custom-checkbox">
			    						<input type="checkbox" class="custom-control-input basketCheckBox" value="${vo.basketNo }" id="${vo.basketNo }">    
			    						<label class="custom-control-label" for="${vo.basketNo }"></label>							
			 						 </div>
		 						 </div>
				  				<div class="col-2 dataRow">
				  					<!-- 상품 이미지 -->
			  						<img class="rounded goodsImage img-fluid" src="${vo.goodsImage }" data-goodsiamge="${vo.goodsImage }" alt="상품이미지" width="150" height="150">
			  					</div>
				  				<div class="col-4">	 
				  					<button type="button" class="close basketRemove">&times;</button><br><br>  
				  					<c:if test="${vo.categoryNo>=3000 }">
				  						<small>희망 관람일: ${vo.hopeDate }</small>
				  						<div class="goodsDate" data-goodsstartdate="${vo.goodsStartDate }" data-goodsenddate="${vo.goodsEndDate }"></div>
				  					</c:if> 
				  					<c:if test="${vo.categoryNo<2000 && vo.categoryNo>=1000 }">
				  						<small>ISBN: ${vo.goodsNo }</small>
				  					</c:if> 						
				    				<!-- 상품 이름 -->
				    				<h5><b class="goodsTitle dataRow mt-2">${vo.goodsTitle }</b></h5>
				    				<!-- 결제 금액 배송비 -->
				    				<h5><b class="orderPrice">${vo.orderPrice }</b> <small class="dlvyCharge" data-dlvyCharge="${vo.dlvyCharge }">${vo.dlvyCharge }</small></h5>
				    				<input type="hidden" value="${vo.orderPrice }" name="orderPrice" class="orderData">
				    				<input type="hidden" value="${vo.dlvyCharge }" name="dlvyCharge" class="orderData">	  
				    				<br>	    				
			  					</div>  					
			  					<div class="col-5 d-flex flex-column" style="border-left: 1px solid #ccc;">
			  						<c:forEach items="${optList }" var="opt">
			  							<c:if test="${vo.basketNo == opt.basketNo }">		  								
			  								<input type="hidden" value="${vs.count }" name="lefOrder" class="orderData">
			  								<c:if test="${opt.optName != '/' }">
						  						<!-- 상품 옵션 -->
						  						<h5><span class="badge badge-pill badge-secondary" data-optno="${opt.optNo }" data-amount="${opt.amount }">
						  							옵션</span> ${opt.optName }
						  						</h5>
					  						</c:if>
					  						
					  						<input type="hidden" value="${opt.optNo }" name="optNo" class="orderData optNo">
					  						<input type="hidden" value="${opt.optName }" name="optName" class="orderData">
					  						<!-- 수량 -->
					  						<h5><span class="badge badge-pill badge-secondary">수량</span> ${opt.amount }</h5>
					  						<input type="hidden" value="${opt.amount }" name="amount" class="orderData amount">
					  						<br>				  						
				  						</c:if>
			  						</c:forEach>
			  						<!-- 옵션 변경을 위한 버튼 -->
			  						<button type="button" class="btn btn-dark optUpdateBtn w-25 mt-auto" data-toggle="modal" data-target="#optUpdateModal">
			    						옵션 변경
			  						</button>
			  					</div>
							</div>
							<hr>
							<div class="text-center">
								<!-- 주문을 위한 버튼 -->
								<button type="button" class="btn btn-outline-dark mr-1 w-25 orderWriteBtn">주문 하기</button>
								
							</div>
				  		</li>
			  		</c:forEach>
		  		</c:if>
		  		<c:if test="${ empty list }">
	  			<div class="alert alert-dark">
    				 <strong>아직 장바구니에 담긴 상품이 없습니다.</strong> 쇼핑을 시작하고 마음에 드는 상품을 추가해보세요. 
  				</div>
	  		</c:if>
			</ul>
	  	</form>
		
	</div>  	
</div>
<jsp:include page="updateForm.jsp"></jsp:include>
</body>
</html>