<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제</title>

<style>
.custom-control-label:hover{
	cursor: pointer;
}
.custom-checkbox .custom-control-input:checked~.custom-control-label::before{
 	background-color: black;
    border-color: black;   
}
.dataRow, .backBtn {
	cursor: pointer;
}
</style>

<script type="text/javascript" src="/js/order/telInput.js"></script>
<script type="text/javascript" src="/js/BoardInputUtil.js"></script>
<script type="text/javascript" src="/js/priceUtils.js"></script>
<link href="/css/basketOrder/basic.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

	$(function() {		
		//가격 정보에 , 삽입
		$(".dlvyChargePrint").each(function() {
			printComma($(this));
		});
		$(".orderPricePrint").each(function() {
			printComma($(this));
		});
		printComma($("#toalPrice"));
		printComma($("#totalCheckPrice"));
		printComma($("#totalCheckDlvyCharge"));
		
		//아작스를 이용해 모달창에 배송지 리스트 띄우기
		$("#dlvyList").load("/dlvy/list.do", function() {
			//결제 폼 시작시 기본 배송지 입력하기위해 배송지 정보 가져오기
			let $listItem = $("#dlvyList").find(".list-group-item:first");
			//배송지가 존재할때
			if($listItem.length > 0) {
				//결제 폼에 기본 배송지 입력
				dlvySelect($listItem);		
			} else {	
				//배송지가 없을때 부트스트랩 alert를 등장시켜 배송지 등록시키게 한다.
				let dlvyAlert = `
					<div class="alert alert-dark" id="dlvyAlert">
					아직 등록된 배송지가 없습니다. <a class="alert-link" id="dlvyAlertLink">배송지 등록하기</a>.
				  	</div>`;
				$("#dlvyBtn").hide();
				$("#dlvyPrintDiv").prepend(dlvyAlert);				
			}
			
		});
		
	});
	
	$(function() { //제이 쿼리 준비	
		//상품 사진,상품 이름 클릭 시 이벤트
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
		//처음 화면에서는 핸드폰 결제 상세 입력 부분을 숨긴다. 카드 결제는 창 나올때 선택되어 있으므로 숨김 x		
		$("#phoneDetail").hide();		
		$("#accountDetail").hide();
		$("#card").click(function() { // 카드 결제 선택될때 이벤트
			//카드 결제가 선택되면 카드 상세 입력 보이게 하고 다른 상세 입력은 숨긴다.
			$("#cardDetail").show();
			$("#phoneDetail").hide();
			$("#accountDetail").hide();
		});
		$("#phone").click(function() { // 핸드폰 결제 선택될때 이벤트
			//핸드폰 결제가 선택되면 핸드폰 상세 입력 보이게 하고 다른 상세 입력은 숨긴다.
			$("#cardDetail").hide();
			$("#phoneDetail").show();
			$("#accountDetail").hide();
		});
		$("#account").click(function() { // 무통장 결제 선택될때 이벤트
			//무통장 결제가 선택되면 다른 상세 입력은 숨긴다.
			$("#cardDetail").hide();
			$("#phoneDetail").hide();
			$("#accountDetail").show();
		});	
		//카드 번호 입력 이벤트 - 숫자만 입력가능하게 한다.
		$(".cardInput").on("input", function() {	
			$(this).val($(this).val().replace(/[^0-9]/g, ""));
				
		});
		
		//모달 관련 이벤트
		//배송지 변경 버튼 클릭시 이벤트 - 아작스를 이용해 모달에 배송지 정보 띄우기
		$("#dlvyBtn").click(function() {
			$("#dlvyList").load("/dlvy/list.do");
		});			
		
		//처음에는 개별 배송메모 입력을 숨기고 비활성화 시킨다.
		$("#eachDlvyMemoDiv").hide();
		$("#eachDlvyMemoDiv").find(".eachDlvyMemo").attr("disabled",true)
		//배송메모 개별 입력 이벤트
		$("#dlvyMemoCheck").change(function() {
			//배송메모 개별 입력을 체크하면 입력 태그를 보이게 한고 비활성화도 푼다
			if($(this).is(":checked")){			
				$("#dlvyMemo").hide();
				$("#dlvyMemo").attr('disabled',true);
				$("#eachDlvyMemoDiv").show();
				$("#eachDlvyMemoDiv").find(".eachDlvyMemo").removeAttr("disabled");
			} else{	
				//배송메모 개별 입력을 체크 해제하면 입력 태그를 숨기고 비활성화한다.
				$("#dlvyMemo").show();
				$("#dlvyMemo").removeAttr('disabled');
				$("#eachDlvyMemoDiv").hide();
				$("#eachDlvyMemoDiv").find(".eachDlvyMemo").attr("disabled",true);			
			}
		});		
		//결제 버튼 클릭
		$("#orderSubmitBtn").click(function() {
			//선택된 결제 방법을 알아내 선택한거에 따라 무결성 체크를 실행한다.
			let payWay = $("input[name='payWay']:checked").val();
			if(isEmpty($("#recipientInput"),"배송지",true) ){
				return false;
			}
			if(payWay=='카드') {
				if(isEmpty("#cardCom","카드 회사",false) || isEmpty("#card1","카드 번호",false) || isEmpty("#card2","카드 번호",false) || isEmpty("#card3","카드 번호",false) || isEmpty("#card4","카드 번호",false)) {
					
					return false;
				}
				if(lengthCheck("#card1","카드 번호",4,4,false) || lengthCheck("#card2","카드 번호",4,4,false) || lengthCheck("#card3","카드 번호",4,4,false) || lengthCheck("#card4","카드 번호",4,4,false) ) {
					
					return false;
				}
				$("#txtPhone").attr("disabled",true);
			} else if(payWay=='핸드폰') {
				$("#txtPhone").removeAttr("disabled");
				if(isEmpty("#txtPhone","전화번호",true)){					
					return false;				
				}
			} else if(payWay=='계좌') {
				if(isEmpty("#accountCom","은행",false)) {					
					return false;
				}
				$("#txtPhone").attr("disabled",true);
			}
		});
		
	});
	
	//배송지 선택 버튼 이벤트
	$(document).on("click", ".dlvySelBtn" , function() {
		//배송지 정보 가져오기
		let $listItem = $(this).closest(".list-group-item");
		dlvySelect($listItem);		
	});	
	//배송지 alert 등록 링크 클릭 이벤트
	$(document).on("click", "#dlvyAlertLink",function() {
		//배송지 모달 등장
		$("#dlvySelModal").modal("show");
		//아작스를 이용해 배송지 등록 폼 출력
		$("#dlvyList").load("/dlvy/writeForm.do" , function() {
			//기본 배송지를 체크된 상태로 바꾼후 숨겨 무조건 기본 배송지 만든다.
			$("#basic").attr('checked',true);
			$("#basicDiv").hide();
			$(".backBtn").hide();
			//fristDlvyWrite 전역 변수로 사용해 다른 위치에서도 체크 가능하게 한다.
			fristDlvyWrite = true;			
		});
	});
	
	//배송지 신규입력 버튼 클릭 이벤트
	$(document).on("click", "#dlvyWriteBtn",function() {	
		//아작스를 이용해 배송지 등록 폼 출력
		$("#dlvyList").load("/dlvy/writeForm.do");		
	});
	
	//배송지 입력 폼에서 등록 버튼 클릭
	$(document).on("click","#dlvyWriteFormBtn" , function() {
		//dlvyWriteForm 입력된 데이터를 배열로 가져온다.
		let formData = $("#dlvyWriteForm").serializeArray();
		//ajax에 데이터로 보내기 위해 json타입으로 변환
		let form = {};		
		$.each(formData, function() {
			form[this.name] = this.value;
		});	
		//ajax에 form 데이터를 보내 배송지를 등록한다. - 등록후 dlvyList에 dlvylist 출력
		$("#dlvyList").load("/dlvy/write.do", form,  function() {
			//만약 처음으로 배송지 등록하는 경우
			if(fristDlvyWrite) {
				//등록된 배송지를 바로 가져와 결제폼에 입력한다.
				let $listItem = $("#dlvySelModal").find(".list-group-item");
				dlvySelect($listItem);
				//배송지 alert를 지우고 배송지 변경 버튼 보이게 한다.
				$("#dlvyAlert").remove();
				$("#dlvyBtn").show();
				
			}
		});
	});
	//배송지 수정 버튼 클릭 이벤트
	$(document).on("click", ".dlvyUpdateFormBtn",function() {
		//바꿀 배송지의 번호를 가져온다.
		let dlvyAddrNo = $(this).closest(".list-group-item").data("dlvyaddrno");
		let dlvyLength = $(".dlvyListItem").length;
		//ajax를 사용해 수정할 배송지를 번호를 넘겨 배송지 수정 폼을 출력한다.
		$("#dlvyList").load("/dlvy/updateForm.do?dlvyAddrNo="+dlvyAddrNo+"&length="+dlvyLength);		
	});
	//배송지 수정 폼에서 수정 버튼 클릭
	$(document).on("click","#dlvyUpdateBtn" , function() {
		//dlvyUpdateForm 입력된 데이터를 배열로 가져온다.
		let formData = $("#dlvyUpdateForm").serializeArray();
		//ajax에 데이터로 보내기 위해 json타입으로 변환
		let form = {};		
		$.each(formData, function() {
			form[this.name] = this.value;
		});	
		//ajax에 form 데이터를 보내 배송지를 등록한다. 등록후 dlvyList에 dlvylist 출력
		$("#dlvyList").load("/dlvy/update.do", form);
	});
	
	//배송지 삭제 버튼 이벤트
	$(document).on("click",".dlvyDeleteBtn", function() {		
		//만약 삭제하려는 배송지가 기본 배송지라서 alert를 띄우고 삭제하지 않는다.
		if($(this).closest(".list-group-item").find(".basicAddr").length == 1) {
			alert("다른 배송지를 기본 배송지로 변경하고 삭제해주세요.");
			//해당 배송지 번호의 배송지를 삭제하고 배송지 리스트로 돌아온다.
		} else {
			let dlvyAddrNo = $(this).closest(".list-group-item").data("dlvyaddrno");			
			$("#dlvyList").load("/dlvy/delete.do?dlvyAddrNo="+dlvyAddrNo);			
		}
	});
	
	//배송지 선택시 호출되는 함수
	function dlvySelect($dlvyListItem) {		
		let recipient = $dlvyListItem.find(".recipientModal").text();
		let dlvyName = $dlvyListItem.find(".dlvyNameModal").text();
		let tel = $dlvyListItem.find(".telModal").text();
		let addr = $dlvyListItem.find(".addrModal").text();
		let postNo = $dlvyListItem.find(".postNoModal").text();
		
		//배송지 정보 출력
		$("#recipientPrint").text(recipient+"("+dlvyName+")");
		$("#telPrint").text(tel);
		$("#addrPrint").text(addr+"("+postNo+")");
		//배송지 정보 input 입력
		$("#dlvyNameInput").val(dlvyName);
		$("#recipientInput").val(recipient);
		$("#telInput").val(tel);
		$("#addrInput").val(addr);
		$("#postNoInput").val(postNo);
		
		$("#dlvySelModal").modal("hide");
	}
</script>

</head>
<body class="bg-light">

<div class="container" style="margin-bottom: 70px">
	<h3 class="text-center my-3"><b>주문/결제</b></h3>
	<br>
	<form action="write.do" method="get" id="writeForm">	
		<i class="material-icons float-right backBtn" onclick="history.back()" style="font-size:36px">arrow_back</i>		
		<h3><b>배송지</b></h3>
		<!-- 배송지를 입력하는 div -->
		<div class="border rounded p-3 mb-2 bg-white text-dark shadow-sm" id="dlvyPrintDiv">		
			<!-- 배송지 입력을 위한 모달을 나타내는 버튼 -->
			<button class="btn btn-outline-secondary btn-md float-right" id="dlvyBtn" type="button" data-toggle="modal" data-target="#dlvySelModal">변경</button>
			<!-- 배송지 관련 데이터를 write.jsp로 넘긴다 -->
			<!-- 받는 사람(배송지명) -->
			<h5><b id="recipientPrint"></b></h5>
			<input type="hidden" name="dlvyName" id="dlvyNameInput">
			<input type="hidden" name="recipient" id="recipientInput">
			<!-- 전화번호 -->
			<small class="text-secondary" id="telPrint"></small>
			<input type="hidden" name="tel" id="telInput">
			<!-- 주소 -->
			<br><span id="addrPrint"></span>
			<input type="hidden" name="addr" id="addrInput">
			<input type="hidden" name="postNo" id="postNoInput">
			<c:set var="isTiket" value="false"></c:set>
			<c:forEach items="${list }" var="vo">
				<!-- 티켓을 예매하는 경우 등장 -->
				<c:if test="${vo.categoryNo>=3000  && !isTiket }">
					<c:set var="isTiket" value="true"></c:set>
					<div class="alert alert-dark alert-dismissible fade show my-2">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						티켓을 예매하는 경우 배송지에 있는 전화 번호로 예매가 진행됩니다.
					</div>							
				</c:if>
			</c:forEach>
			<hr>
			<!-- 주문이 다중 주문일때 등장 -->
			<c:if test="${list.get(0).isMultiOrder() }">
				<div class="custom-control custom-checkbox mb-2">
					<input type="checkbox" class="custom-control-input" id="dlvyMemoCheck">    
					<label class="custom-control-label" for="dlvyMemoCheck"><b>배송 메모 개별 입력</b></label>							
		 		</div>
		 	</c:if>
		  	<input type="text" class="form-control my-3" id="dlvyMemo" name="dlvyMemo" maxlength="100" placeholder="배송 메모를 입력해주세요.">			  			
			<c:if test="${list.get(0).isMultiOrder() }">
				<div id="eachDlvyMemoDiv">
				<!-- 주문 상품 만큼 배송메모 입력 태그 생성 -->
					<c:forEach items="${list }" var="vo">
						${vo.goodsTitle }				
			  			<input type="text" class="form-control my-3 eachDlvyMemo" name="dlvyMemo" maxlength="100" placeholder="배송 메모를 입력해주세요.">			  			
		  			</c:forEach>
	  			</div>		
	 		</c:if>	  						
		</div>
		
		
		<br>
		<h3><b>주문상품</b></h3>
		<c:forEach items="${list }" var="vo" varStatus="vs">
			<div class="border rounded p-3 mb-2 bg-white text-dark shadow-sm orderListItem" data-goodsno="${vo.goodsNo }" data-categoryNo="${vo.categoryNo }">	
				<!-- 히든 입력 구간 : 상품 코드, 장바구니 번호(결제 삭제하기 위해),희망 관람일,카테고리 번호 -->
				<input type="hidden" id="goodsNo" name="goodsNo" value="${vo.goodsNo }">
				<input type="hidden" id="basketNo" name="basketNo" value="${vo.basketNo }">
				<input type="hidden" value="${vo.hopeDate }" name="hopeDate" class="orderData">
				<input type="hidden" value="${vo.categoryNo }" name="categoryNo" class="orderData">
				<input type="hidden" id="order" name="order" value="${vs.count }">
				<!-- 이미지와 상품 정보칸 나누기 위해 그리드 시스템 사용 -->
				<!-- 주문상품 정보를 출력하고 히든으로 주문 상품 번호,옵션 번호,수량를 입력하는 div -->
				<div class="row">
					<div class="col-2">
						<!-- 상품 이미지 -->
						<img class="rounded img-fluid dataRow" src="${vo.goodsImage }" alt="상품 사진" width="150" height="150">
					</div>
					<div class="col-10"> 
						<!-- 배송비 -->
						<span class="float-right text-secondary">
						<!-- 책과 상품일때는 배송비, 티켓일때 수수료 -->
						<c:if test="${vo.categoryNo<3000 }">
							배송비		
						</c:if>		
						<c:if test="${vo.categoryNo>=3000 }">
							수수료		
						</c:if>						 
						: <span class="dlvyChargePrint">${vo.dlvyCharge }</span><span class="won">원</span></span> 		
						<input type="hidden" id="dlvyCharge" name="dlvyCharge" value="${vo.dlvyCharge }">			
		  				<!-- 상품 이름 -->
		  				<h5><b class="dataRow">${vo.goodsTitle }</b></h5>	
		  				<!-- 티켓일 경우 희망 관람일, 책일 경우 ISBN, 상품일 경우 아무것도 출력되지 않는다. --> 
		  				<c:if test="${vo.categoryNo>=3000 }">
	  						<small class="mb-3">희망 관람일: ${vo.hopeDate }</small>
	  					</c:if> 
	  					<c:if test="${vo.categoryNo<2000 && vo.categoryNo>=1000 }">
	  						<small class="mb-3">ISBN: ${vo.goodsNo }</small>
	  					</c:if>	  				
		  				<c:forEach items="${optList }" var="opt">
		  					<c:if test="${vo.order == opt.lefOrder }">
				  				<!-- 상품 옵션 -->
				  				<c:if test="${opt.optName != '/' }">
									<h5><span class="badge badge-pill badge-secondary">옵션</span> ${opt.optName }</h5>
								</c:if>
								<input type="hidden" id="lefOrder" name="lefOrder" value="${vs.count }">
				  				<input type="hidden" id="optNo" name="optNo" value="${opt.optNo }">	 					
								<!-- 수량 -->
								<h5><span class="badge badge-pill badge-secondary">수량</span> ${opt.amount}개</h5>
				  				<input type="hidden" id="amount" name="amount" value="${opt.amount}">
		  					</c:if>
		  				</c:forEach>		
		  				<!-- 결제 금액 -->
		  				<h5><b class="mt-3"><span class="orderPricePrint">${vo.orderPrice }</span><span class="won">원</span></b></h5>	    
		  				<input type="hidden" id="orderPrice" name="orderPrice" value="${vo.orderPrice }">				
					</div>				
				</div>				
				
			</div>
		</c:forEach>
		
		<div class="border rounded p-3 mb-2 bg-white text-dark shadow-sm">	
		<!-- 글부분만 숫자 부분을 나누기 위해 그리드 사용 -->
			<div class="row">
				<div class="col-6"> <!-- 글부분(설명) -->
					<h5><b class="text-dark">주문금액</b></h5>
					<div style="border-left: 5px solid #aaa;"> <!-- 앞에 수직으로 선을 만들기 위해 div의 style 사용 -->
						<div class="ml-2 mt-1">상품 금액</div> <!-- 왼쪽의 마진을 주기위해 span 태그안에 데이터를 넣은 후 ml 클래스로 마진을 준다. -->
						<div class="ml-2 mt-1">배송비(수수료)</div>					
					</div>
				</div>				
				<div class="col-6 text-right"> <!-- 숫자부분(금액) - 텍스트를 오른쪽에 붙히기 위해 text-right 클래스 사용 -->
					<!-- 금액을 출력한다 -->
					<h5><b class="text-dark">총 <span id="toalPrice">${list.get(0).totalCheckPrice + list.get(0).totalCheckDlvyCharge }</span><span class="won">원</span></b></h5>								
					<div class="ml-2 mt-1"><span id="totalCheckPrice">${list.get(0).totalCheckPrice }</span><span class="won">원</span></div>
					<div class="ml-2 mt-1"><span id="totalCheckDlvyCharge">${list.get(0).totalCheckDlvyCharge }</span><span class="won">원</span></div>						
								
				</div>
			</div>	
		</div>			
		
		<br>
		<h3><b>결제 수단</b></h3>
		<!-- 결제 수단을 입력받는 div -->
		<div class="border rounded p-3 mb-2 bg-white text-dark shadow-sm">	
			<!-- radio 타입 input 태그를 이용해 결제 방법과 상세를 입력 받는다. -->
			<div class="custom-control custom-radio my-2">
			    <input type="radio" class="custom-control-input" id="card" name="payWay" checked value="카드">
			    <label class="custom-control-label" for="card">카드 결제</label>			    
			    <!-- 카드 상세 정보를 입력받는다 - 그리드를 이용해 카드 정보를 한줄로 입력 받는다. radio를 선택했을때 등장-->
			    <div id="cardDetail" class="ml-3 my-2"> 			    	
			    	<select class="form-control" id="cardCom" name="cardCom">		
						<option value="" selected style="display: none;">카드를 선택해주세요</option>	
						<option>국민</option>
						<option>현대</option>
						<option>신한</option>
						<option>비씨</option>
						<option>삼성</option>
						<option>롯데</option>
						<option>외환</option>
						<option>농협</option>
						<option>하나</option>
						<option>광주</option>
						<option>수협</option>
						<option>씨티</option>
						<option>전북</option>
						<option>제주</option>
						<option>카카오뱅크</option>
						<option>케이뱅크</option>			
					</select>			    	
			    	<div class="form-inline my-2">				    	
				    		<!--정규식 패턴을 이용해 숫자로 4만 입력했을때 통과할수 있다.  -->
	      					<input type="text" class="form-control cardInput" maxlength="4" id="card1" name="card" placeholder="0000">	    				
	    					<i class="fa fa-minus mx-2"></i>
	      					<input type="text" class="form-control cardInput" maxlength="4" id="card2" name="card" placeholder="0000">
	      					<i class="fa fa-minus mx-2"></i>	    				
	      					<input type="password" class="form-control cardInput" maxlength="4" id="card3" name="card" placeholder="0000">	
	      					<i class="fa fa-minus mx-2"></i>	    				
	      					<input type="password" class="form-control cardInput" maxlength="4" id="card4" name="card" placeholder="0000">	    				    				
    				</div>
			    </div>
  			</div>
  			<div class="custom-control custom-radio my-2">
			    <input type="radio" class="custom-control-input" id="phone" name="payWay" value="핸드폰">
			    <label class="custom-control-label" for="phone">핸드폰 결제</label>
			    <!-- 핸드폰 상세 정보를 입력받는다 - radio를 선택했을때 등장-->
			    <div id="phoneDetail" class="ml-3 my-2">
				    <!-- 정규식 패턴을 이용해 올바른 형태로 입력했을때만 통과 가능하다. -->
				    <input type="text" id="txtPhone" class="form-control txtPhone" name="tel" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" placeholder="결제할 핸드폰의 번호를 입력하세요.">
				</div>	
  			</div>		
  			<div class="custom-control custom-radio my-2">
			    <input type="radio" class="custom-control-input" id="account" name="payWay" value="계좌">
			    <label class="custom-control-label" for="account">계좌 결제</label>
			    <div id="accountDetail" class="ml-3 my-2">
				    <select class="form-control" name="accountCom" id="accountCom">		
						<option value="" selected style="display: none;">은행을 선택해주세요</option>	
						<option>농협</option>
						<option>국민은행</option>
						<option>신한은행</option>
						<option>우리은행</option>
						<option>기업은행</option>
						<option>하나은행</option>
						<option>im뱅크</option>
						<option>부산은행</option>
						<option>우체국</option>
						<option>SC제일은행</option>
						<option>광주은행</option>
						<option>경남은행</option>
						<option>수협</option>
						<option>케이뱅크</option>						
					</select>
				</div>
  			</div>
  			
		</div>
		
		<!-- 결제 버튼을 어디서도 볼수있게 하기 위해 하단에 고정된 네비바 생성 -->
		<nav class="navbar navbar-expand-sm bg-white navbar-dark fixed-bottom shadow">
			<!-- 결제 버튼을 클릭하면 입력된 정보와 함께 write.jsp로 이동한다. -->
			<button type="submit" id="orderSubmitBtn" class="btn btns btn-block"><b>결제</b></button>	 
		</nav>
	</form>
	
	<!-- 배송지 변경 모달 -->	    
	<div class="modal fade" id="dlvySelModal">
		<div class="modal-dialog modal-dialog-centered h-50  modal-lg">
	    	<div class="modal-content">		        
		        <div class="modal-header">
		          	<h4 class="modal-title"><b>배송지 목록</b></h4>
		          	<!-- 모달창이 사라지는 아이콘 버튼 -->
		          	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        </div>	        
		        
		        <div class="modal-body">
		        	<div id="dlvyList">
		        	
		        	</div>
		        </div>
		        	
		        
		        <div class="modal-footer">		        
		          	<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
		        </div>	        
	      	</div>
	 	</div>
	 </div>
  	
</div>
</body>

</html>