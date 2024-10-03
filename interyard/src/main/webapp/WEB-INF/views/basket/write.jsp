<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!-- 스타일 정의 -->
<style type="text/css">
/*수량 입력 태그 중앙 정렬*/
.amount {
	text-align: center;
}
</style>

<!-- JavaScript 및 CSS 파일 포함 -->
<script type="text/javascript" src="/js/basket/opt.js"></script>
<script type="text/javascript" src="/js/BoardInputUtil.js"></script>
<script type="text/javascript" src="/js/datePicker/jquery-ui.min.js"></script>
<link href="/css/datePicker/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="/css/basketOrder/basic.css" rel="stylesheet" type="text/css" />

<!-- JavaScript 코드 -->
<script type="text/javascript">	
	// 현재 날짜 설정
	let now = new Date();

	// jQuery UI datepicker 기본 설정
	$.datepicker.setDefaults({
		dateFormat: 'yy-mm-dd',
		minDate: now,
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

	$(function() {
		// 장바구니 담기 완료 후 확인
		//컨트롤러에서 장바구니 등록 후 session에 basketWriteComplete 저장
		//저장이 된 상태면 장바구니가 등록된 상태이므로 confirm 출력
		if(${!empty basketWriteComplete && basketWriteComplete}){
			//confirm 출력된 상태니 다시 안 나오기 위해 session에서 basketWriteCompletet삭제
			<% session.removeAttribute("basketWriteComplete"); %>
			if(confirm("장바구니에 상품이 담겼습니다. 장바구니로 가시겠습니까?")) {
				location = "/basket/list.do";
			}			
		}
		
		// 옵션 리스트 로드
		$(".selDiv").load("/opt/list.do?goodsNo=${param.goodsNo }", function() {
			//현재 상품의 가격을 goodsPrice에 저장
			let goodsPrice = ${vo.goodsPrice};
			//클래스가 optPrice 태그에 data-basicprice='goodsPrice' 추가
			$(".optPrice").attr("data-basicprice", goodsPrice);
		});		
		
		// 구매하기 버튼 클릭 이벤트 처리
		$("#orderBtn").click(function() {
			//무결성 체크
			if(!integrityOpt(${vo.categoryNo}, $("#optCard"), $(".amount"), $("#hopeDate"))) {
				//form의 action을 주문 쓰기 폼으로 변경
				$("#obWriteForm").attr("action", "/order/writeForm.do");
				//옵션과 수량을 포함한 전체 가격을 form의 orderPrice 태그에 넣기
				$("#orderPrice").val(numWithoutComma($("#totalPrice").text()));
				$("#obWriteForm").submit();				
			}
		});
		
		// 장바구니 버튼 클릭 이벤트 처리
		$("#basketBtn").click(function() {
			//무결성 체크
			if(!integrityOpt(${vo.categoryNo}, $("#optCard"), $(".amount"), $("#hopeDate"))) {
				//form의 action을 장바구니 쓰기 으로 변경
				$("#obWriteForm").attr("action", "/basket/write.do");
				//옵션과 수량을 포함한 전체 가격을 form의 orderPrice 태그에 넣기
				$("#orderPrice").val(numWithoutComma($("#totalPrice").text()));
				$("#obWriteForm").submit();
			}
		});		
		
		// 날짜 선택 이벤트 처리
		$('#datePicker').datepicker({
			//데이트피커가 선택 됐을때 희망 관람일 입력 태그에 해당 값 넣기
			 onSelect: function(date) {
				 $("#hopeDate").val(date);
			},
		});
		//희망 관람일 입력 태그에 오늘 날짜 입력
		$("#hopeDate").val(formatDate(now));
	});
	
	// 옵션 제거 버튼 클릭 이벤트 처리
	$(document).on("click", ".remove", function() {
		//해당 listItem 삭제
		$(this).closest(".list-group-item").remove();
		//옵션이 변화 된 상태이므로 전체 가격 다시 계산
		totalPrice();
	});
	
	// 옵션 선택 시 태그 생성
	$(document).on("change", "#opt", function(){					
		createTag($(this).val(), 1);
		$(this).val("");
	});
	
	// 날짜 형식 변환 함수
	function formatDate(dateString) {
		var date = new Date(dateString);
		var year = date.getFullYear();
		var month = ("0" + (date.getMonth() + 1)).slice(-2);
		var day = ("0" + date.getDate()).slice(-2);
		return year + '-' + month + '-' + day;
	}
	
</script>

<!-- 상품 주문/장바구니 담기 폼 -->
<form method="post" id="obWriteForm">
	<!-- 특정 카테고리의 상품인 경우 날짜 선택 기능 제공 -->
	<c:if test="${vo.categoryNo >= 3000 }">		
		<div id="datePicker" class="mb-3 mx-auto"></div>
		<!-- 희망 관람일 입력 태그 -->
		<input type="hidden" id="hopeDate" name="hopeDate" class="form-control">
	</c:if>
	
	<!-- 옵션 선택 영역 -->
	<div class="selDiv"></div>
	<!-- 히든 입력 영역 -->
	<input type="hidden" name="goodsNo" value="${param.goodsNo }">			
	<input type="hidden" name="orderPrice" id="orderPrice">			
	<input type="hidden" name="dlvyCharge" value="${vo.goodsCost }">			
	<input type="hidden" name="categoryNo" value="${vo.categoryNo }">	
	<input name="page" value="${param.page }" type="hidden">
	<input name="perPageNum" value="${param.perPageNum }" type="hidden">
	<input name="key" value="${param.key }" type="hidden">
	<input name="word" value="${param.word }" type="hidden">		
	<input type="hidden" name="order" value="1">			
	<input type="hidden" name="basketNo" value="1">		
	<!-- 옵션 listItem 생성 되는 곳 -->	
	<ul class="list-group my-2" id="optCard"></ul>

	<hr>
	
	<!-- 총 주문 금액 및 수량 표시 -->
	<div class="mb-2">
		<div class="float-right">
			<!-- 총 수량 표시 -->
			<span>총 수량 <span id="totalAmount">0</span>개</span>
			<!-- 총 금액 표시 -->
			<span style="border-left: 1px solid #ccc; font-size: 24px;" class="pl-2 text-dark font-weight-bold"><span id="totalPrice">0</span>원</span>
		</div>
		<div>
			<b>총 상품 금액</b>(배송비는 포함되어 있지 않습니다.)
		</div>
	</div>
	<br>
	
	<!-- 상품 상태에 따른 버튼 표시 -->
	<c:if test="${vo.goodsStatus == '판매중' || vo.goodsStatus == '공연중' }">
		<div class="row">
			<div class="col">
				<button type="button" class="btn btn-dark btn-block" id="orderBtn">구매하기</button>				
			</div>
			<div class="col">
				<button type="button" class="btn btn-outline-dark btn-block" id="basketBtn">장바구니</button>		
			</div>	
		</div>
	</c:if>
	<c:if test="${vo.goodsStatus != '판매중' && vo.goodsStatus != '공연중' }">
		<div class="alert alert-dark">
    		<strong>판매 종료된 상품입니다.</strong>    		    		
  		</div>
	</c:if>
</form>
