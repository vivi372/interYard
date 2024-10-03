<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 관리</title>

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

.custom-checkbox .custom-control-input:checked~.custom-control-label::before{
 	background-color: black;
    border-color: black;   
}

.custom-control-input:checked ~ .custom-control-label::before {
    background-color: #555; /* 중간 회색 */
    border-color: #555; /* 중간 회색 */
}    

.custom-control-label:hover, .detailBtn:hover, .copyBtn:hover {
	cursor: pointer;
}

.page-link:focus, .page-link:hover {
  color: #ccc;
  background-color: #222; 
  border-color: #444;
}

.link-text {
      color: #007bff; /* Bootstrap 기본 링크 색상 */
      text-decoration: underline;
      cursor: pointer;
}
.id:hover {
	cursor: pointer;
	text-decoration: underline;
}
</style>
<link rel="stylesheet" href="/css/checkBax.css">
<script type="text/javascript" src="/js/BoardInputUtil.js"></script>
<script type="text/javascript" src="/js/priceUtils.js"></script>
<link href="/css/basketOrder/basic.css" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/1.7.1/clipboard.min.js"></script>
<script type="text/javascript">
	
	$(function() {	
		//관리자 페이지로 이동 체크 박스 클릭시 주문 리스트로 이동
		$("#adminList").click(function() {
			location = "/order/list.do";
		});
		//가격에 , 붙히기
		$(".printCommaNum").each(function() {
			printComma($(this));
		});
		//주문 상태 클릭시 이벤트
		$(".stateUpdateBtn").click(function() {
			let orderState = $(this).text();
			//주문 상태가 리뷰 작성일 경우 수정 불가
			if(orderState == '구매 확인(리뷰작성)') {
				alert("리뷰까지 작성된 주문은 상태 수정이 불가능합니다.");
			} else {
				//아닌 경우 모달을 등장시키고
				//모달에 주문 번호와 주문자 아이디를 입력한다.
				//select 태그의 선택된 값을 현재 주문 상태로 보여주고 현재 주문 상태인 옵션을 숨겨
				//선택 불가능하게 만든다.
				$("#orderStateUpdateModal").modal("show");
				$("#modalOrderNo").val($(this).parent().siblings(".orderNo").text());
				$("#modalId").val($(this).closest(".orderData").find(".id").text());
				
				$(".stateOpt").css("display","block");
				$(".stateOpt").removeAttr("selected");
				$(".stateOpt").each(function() {	
					let orderOptVal = $(this).val();
					if(orderOptVal==orderState) {					
						$(this).attr("selected","selected");
						$(this).css("display","none");
					}
				});
			}
			
			
		});
		//일괄 선택 버튼 클릭 이벤트
		$("#allCheck").change(function() {
			//체크 박스 전체 선택과 체크 박스 전체 해제
			if($(this).is(":checked")){			
				$(".orderListCheckBox").prop('checked',true);
			} else{					
				$(".orderListCheckBox").prop('checked',false);
			}			
		});
		//상태 일괄 수정 버튼 클릭 이벤트
		$("#allUpdate").click(function() {
			//체크 박스중 체크된 값만 가져온다.
			$checkedBax = $(".orderListCheckBox:checked");
			//체크박스 무결성 체크
			if(!checkedLength($checkedBax)) {
				//수정할 주문 상태를 가져온다.
				let orderState = $("#orderStateSelect").val();
				//일괄 수정 폼 생성
				let confirmForm = `
					 	<form action="allUpdate.do?${pageObject.pageQuery}&optList=${pageObject.period}" method="post" id="confirmForm">
					 		<input type="hidden" name="orderState" value="\${orderState}">
		        		</form>
					`
				$("body").append(confirmForm);	
				let orderNoinput = "";
				//바꾸고 싶은 주문 만큼 input 태그 생성
				$checkedBax.each(function(){
					let orderNo = $(this).val();
					console.log(orderNo);
					orderNoinput += "<input type='hidden' name='orderNo' value='"+orderNo+"''>\n";
				});		
				
				$("#confirmForm").append(orderNoinput);
				//그 후 submit()
				$("#confirmForm").submit();
			}
		});
		//일괄 삭제 버튼 클릭 이벤트
		$("#allDelete").click(function() {
			//체크 박스중 체크된 값만 가져온다.
			$checkedBax = $(".orderListCheckBox:checked");
			//체크박스 무결성 체크
			if(!checkedLength($checkedBax)) {
				let orderState = $("#orderStateSelect").val();
				//일괄 삭제 폼 생성
				let confirmForm = `
					 	<form action="delete.do?${pageObject.pageQuery}&optList=${pageObject.period}" method="post" id="confirmForm">				 		
		        		</form>`
				$("body").append(confirmForm);	
				let orderNoinput = "";
				//삭제하고 싶은 주문 만큼 input 태그 생성
				$checkedBax.each(function(){
					let orderNo = $(this).val();
					console.log(orderNo);
					orderNoinput += "<input type='hidden' name='orderNo' value='"+orderNo+"''>\n";
				});		
				
				$("#confirmForm").append(orderNoinput);
				//그 후 submit()
				$("#confirmForm").submit();
			}
		});
		
		
		
		//orderStateRadio 바꿀때 마다 폼 submit
		$(".orderStateRadio").change(function() {
			$(this).submit();
		});
		
		// perPageNum 처리
		$("#perPageNum").change(function() {
			//alert("change perPageNum");
			// page는 1페이지 + 검색 데이터 전부 보낸다.
			$("#search").submit();
		});
		//일괄 수정 버튼에 마우스 올렸을때 popover등장
		$(document).ready(function(){
			$('[data-toggle="tooltip"]').tooltip();   
		});
		
		//검색 데이터 세팅		
		$("#key").val("${(empty pageObject.key)?'t':pageObject.key}");
		$("#perPageNum").val("${(empty pageObject.perPageNum)?'10':pageObject.perPageNum}");
		$("#word").val("${pageObject.word}");
		$("#perPageNum").val("${pageObject.perPageNum}");
		$("[value='${pageObject.period}']").attr("checked","true");	
		//상세 정보는 처음에는 숨긴다.
		$(".detailData").hide();
		//상세 정보 보기 버튼 클릭시 상세 정보를 보이게 하고 버튼의 형태를 바꾼다.
		//또한 다른 상세 정보를 숨긴다.
		$(".detailBtn").click(function() {
			$detailData = $(this).closest(".orderData").next();
			if($detailData.css("display") == 'none'){	
				$(".detailData").hide();
				$(".detailBtn").attr("class","fa fa-angle-down detailBtn");
				$detailData.show();
				$(this).attr("class","fa fa-angle-up detailBtn");
			} else {
				$detailData.hide();
				$(this).attr("class","fa fa-angle-down detailBtn");
			}
		});
		
		let clipboard = new Clipboard('.copyBtn');
	    clipboard.on('success', function(e) {
	    	$("#clipboardAlert").show();
        	setTimeout(function() {
        		$("#clipboardAlert").fadeOut();
			},1000);
	    });
	    clipboard.on('error', function(e) {
	        alert("지원하지 않는 브라우저 입니다.");
	    });
		
		
	});
</script>

</head>
<body>
	<div class="alert alert-dark alert-dismissible fade show fixed-bottom w-50 mx-auto" id="clipboardAlert" style="display: none; opacity: 75%;">	   
	    	클립보드에 복사되었습니다.
  	</div>

	<div class="border rounded p-3 mb-2 bg-white text-dark shadow-sm">
	<div class="container">
	<div class="custom-control custom-switch mb-3 float-right">
	    <input type="checkbox" class="custom-control-input" checked id="adminList" value="1">
	    <label class="custom-control-label" for="adminList">관리자 페이지로 이동</label>
  	</div>
  	</div>
	<h3>주문 관리</h3>
	<!-- 검색을 위한 폼 -->
	<form action="adminList.do" id="search">
		<input name="page" value="1" type="hidden">
		<div class="row">		
			<div class="col-md-8">				
				<div class="input-group mb-3">
  					<div class="input-group-prepend">
  						<!-- 검색 키 전달 -->				
   						<select name="key" id="key" class="form-control">
   							<option value="n">주문 번호만</option>
   							<option value="i">아이디</option>
   							<option value="t">상품 이름</option>
   							<option value="p">공급자</option>
   							<option value="it">아이디+상품 이름</option>
   							<option value="ip">아이디+공급자</option>
   							<option value="tp">상품 이름+공급자</option>
   							<option value="itp">모두</option>
   						</select>    					
  					</div>
  					<!-- 검색 word 입력 -->
 				 	<input type="text" class="form-control" placeholder="검색" id="word" name="word">
 				 	<div class="input-group-append">
    					<button class="btn btn-dark searchBtn" type="submit"><i class="fa fa-search"></i></button>
  					</div>
				</div>				
			</div>	<!-- col-8 end : 검색 -->			
			<div class="col-md-4">
				<!-- 너비를 조정하기 위한 div 추가 - float-right : 오른쪽 정렬 -->
				<div style="width: 200px;" class="float-right">
					<div class="input-group mb-3">
	    				<div class="input-group-prepend">
	      					<span class="input-group-text bg-dark text-white">Rows/Page</span>
	    				</div>
	    				<select name="perPageNum" id="perPageNum" class="form-control">
	   						<option value="10">10</option>
	   						<option value="15">15</option>
	   						<option value="20">20</option>
	   						<option value="25">25</option>   							
	   					</select>
	   					<input name="optList" type="hidden" value="${pageObject.period}">
	  				</div>
  				</div>
			</div>	<!-- col-4 end : 한 페이지당 페이지 수 -->	
		</div>
	</form>	
	<div class="row">
		<div class="col form-inline">
			<div class="input-group input-group-sm mb-3" style="width: 500px;">
			<!-- 체크박스 전체 선택 버튼 -->
			<div class="custom-control input-group-prepend custom-checkbox checkBax mx-3 pt-2">
				<input type="checkbox" class="custom-control-input checkBaxInput" id="allCheck">    
				<label class="custom-control-label checkLabel" for="allCheck"><b>전체 선택</b></label>							
			</div>		
				<!-- 주문 정렬 기준이 '취소 요청'이 아닐때 등장  -->
				<c:if test="${pageObject.period != '취소 요청'}">				
					<select class="form-control orderState" id="orderStateSelect" name="orderState">
						<option value="주문 확인">주문 확인</option>
						<option value="배송 대기">배송 대기</option>
						<option value="배송중">배송중</option>
						<option value="배송 완료">배송 완료</option>
						<option value="구매 확인">구매 확인</option>								
						<option value="구매 확인">구매 확인(리뷰작성)</option>								
						<option value="취소 요청">취소 요청</option>
					</select>			
					<div class="input-group-append">
						<button class="btn btn-dark btns" id="allUpdate" type="button" data-toggle="tooltip" title="*리뷰까지 작성된 주문은 주문 수정이 불가능합니다.">일괄 변경</button>						
					</div>					
				</c:if>
				<!-- 주문 정렬 기준이 '취소 요청'일때 등장  -->
				<c:if test="${pageObject.period == '취소 요청'}">
					<div class="input-group-append">
						<button class="btn btn-dark btns" id="allDelete" type="button">일괄 삭제</button>
					</div>
				</c:if>
			</div>				
		</div>		
		<div class="col-6">
			<!-- 주문 상태에 의한 정렬을 보내주는 폼 -->
			<form class="orderStateRadio mb-3 float-right">				
				<div class="custom-control custom-radio custom-control-inline">		  
					<input type="radio" class="custom-control-input" id="all" name="optList" value="">  	
			    	<label class="custom-control-label" for="all">
			    		모두
			    	</label>
			  	</div>						
				<div class="custom-control custom-radio custom-control-inline">		  
					<input type="radio" class="custom-control-input" id="ordCopm" name="optList" value="주문 확인">  	
			    	<label class="custom-control-label" for="ordCopm">
			    		주문 확인
			    	</label>
			  	</div>
			  	<div class="custom-control custom-radio custom-control-inline">
			    	<input type="radio" class="custom-control-input" id="stdBy" name="optList" value="배송 대기">
			    	<label class="custom-control-label" for="stdBy">배송 대기</label>
			  	</div>
			  	<div class="custom-control custom-radio custom-control-inline">
			    	<input type="radio" class="custom-control-input" id="ing" name="optList" value="배송중">
			    	<label class="custom-control-label" for="ing">배송중</label>
			  	</div>
			  	<div class="custom-control custom-radio custom-control-inline">
			    	<input type="radio" class="custom-control-input" id="comp" name="optList" value="배송 완료">
			    	<label class="custom-control-label" for="comp">배송 완료</label>
			  	</div>		
			  	<div class="custom-control custom-radio custom-control-inline">
			    	<input type="radio" class="custom-control-input" id="chk" name="optList" value="구매 확인">
			    	<label class="custom-control-label" for="chk">구매 확인</label>
			  	</div>	
			  	<div class="custom-control custom-radio custom-control-inline">
			    	<input type="radio" class="custom-control-input" id="canc" name="optList" value="취소 요청">
			    	<label class="custom-control-label" for="canc">취소 요청</label>
			  	</div>	
			  	<!-- 페이지 정보 넘기기 -->
			  	<input type="hidden" name="key" value="${param.key }">
			  	<input type="hidden" name="word" value="${param.word }">
			  	<input type="hidden" name="perPageNum" value="${param.perPageNum }">
			</form>	
		</div>				  		
	</div>			
	
	
	<!-- 주문 정보 리스트를 보여주는 테이블 -->
	<table class="table">	
		<thead>	
			<tr>			
				<th>선택</th>
				<th>상세</th>
				<th>주문 번호</th>
				<th>주문 상태</th>
				<th>결제일</th>
				<th>아이디</th>
				<th>상품 코드</th>
				<th>공급자</th>
				<th>상품 이름</th>				
				<th>주문 금액</th>
				<th>배송비</th>								
				<th>삭제</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="vo">
				<tr class="orderData">
					<td>
						<!-- 주문 체크 박스 -->
						<div class="custom-control custom-checkbox d-flex flex-row justify-content-center">
    						<input type="checkbox" class="custom-control-input orderListCheckBox" id="${vo.orderNo }" value="${vo.orderNo }">    
    						<label class="custom-control-label" for="${vo.orderNo }"></label>							
		 				</div>
					</td>
					<td>
						<!-- 주문 상세 정보 보여주는 버튼 -->
						<i class="fa fa-angle-down detailBtn"></i>
					</td>
					<td class="orderNo">${vo.orderNo }</td>
					<td>
						<!-- 주문 상태 -->
			  			<c:if test="${vo.orderState=='취소 요청'}">			  				
			  				<span class="link-text stateUpdateBtn text-danger">${vo.orderState}</span>
			  			</c:if>
			  			<c:if test="${vo.orderState!='취소 요청'}">			  			
			  				<span class="link-text stateUpdateBtn">${vo.orderState}</span>
			  			</c:if>						
					</td>
					<td>${vo.orderDate }</td>
					<td>
						<span class="id" class="dropdown-toggle" data-toggle="dropdown">${vo.id}</span>
						<!-- 아이디 클릭시 나오는 드롭다운 메뉴 -->
						<div class="dropdown-menu">
						    <a class="dropdown-item" href="/member/view.do?id=${vo.id}&admin=1">회원 정보 보기</a>
						    <a class="dropdown-item" href="/message/writeForm.do?id=${vo.id}&title=${vo.orderNo }번 주문 관련 안내">메시지 보내기</a>
						    <a class="dropdown-item" href="/order/adminList.do?perPageNum=${param.perPageNum}&key=i&word=${vo.id}">주문 조회</a>
						</div>
					</td>
					
					<td>${vo.goodsNo}</td>
					<td>${vo.goodsPublicher}</td>
					<td>${vo.goodsTitle}</td>					
					<td class="printCommaNum">${vo.orderPrice}</td>
					<td class="printCommaNum">${vo.dlvyCharge}</td>						
					<td>
						<!-- 해당 주문의 상태가 '취소 요청'상태면 삭제 버튼이 생긴다. -->
						<c:if test="${vo.orderState=='취소 요청'}">
							<button class="btn btn-dark btn-sm btns" onclick="location='/order/delete.do?orderNo=${vo.orderNo }&${pageObject.pageQuery}&optList=${pageObject.period}';">삭제</button>
						</c:if>
					</td>									
				</tr>
				<!-- 상세 정보 데이블 -->
				<tr class="detailData">
					<td colspan="11">
						<table class="table table-borderless">	
							<tr>
								<th>구매확인일</th>
								<td>${vo.confirmDate }</td>
								<c:if test="${vo.categoryNo>=3000 }">
									<th>희망 관람일</th>
									<td>${vo.hopeDate }</td>
								</c:if>
								<c:if test="${vo.categoryNo<2000 && vo.categoryNo>=1000 }">
									<th>ISBN</th>
									<td>${vo.goodsNo }</td>
								</c:if>
							</tr>
											
							<tr>
								<th>상품제조사</th>
								<td>${vo.goodsPublicher }</td>
								<th>받는 사람 이름</th>
								<td>
									<span class="copyText">${vo.recipient }</span>
									<span><i class="material-icons copyBtn" data-clipboard-text="${vo.recipient}" style="font-size:20px">content_copy</i></span>
								</td>
							</tr>
							<tr>
								<th>상품가격</th>
								<td class="printCommaNum">${vo.goodsPrice }</td>
								<th>받는 사람 연락처</th>
								<td>
									<span class="copyText">${vo.tel }</span>
									<span><i class="material-icons copyBtn" data-clipboard-text="${vo.tel}" style="font-size:20px">content_copy</i></span>
								</td>
							</tr>
							<tr>
								<th>상품배송비(수수료)</th>
								<td class="printCommaNum">${vo.goodsCost }</td>
								<th>주소</th>
								<td>
									<span class="copyText">${vo.addr}</span>
									<span><i class="material-icons copyBtn" data-clipboard-text="${vo.addr}" style="font-size:20px">content_copy</i></span>
								</td>
							</tr>
							<tr>
								<th>카테고리</th>
								<td>${vo.categoryName }</td>
								<th>우편 번호</th>
								<td>${vo.postNo }</td>
							</tr>
							<tr>
								<th>판매 상태</th>
								<td>${vo.goodsStatus }</td>
								<th>주문자 이름</th>
								<td>${vo.name }</td>
							</tr>
							<tr>
								<th>결제 방법</th>
								<td>${vo.payWay }</td>
								<th>주문자 연락처</th>
								<td>${vo.memberTel }</td>
							</tr>
							<tr>
								<th>결제 상세</th>
								<td colspan="3">${vo.payDetail }</td>
							</tr>
						</table>					
					</td>
				</tr>
				
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination justify-content-center">
		<pageNav:pageNav listURI="adminList.do" pageObject="${pageObject }"></pageNav:pageNav>
	</div>		
</div>


<!-- 취소 요청 모달 -->	    
	<div class="modal fade" id="orderStateUpdateModal">
		<div class="modal-dialog modal-dialog-centered">
	    	<div class="modal-content">		        
		        <div class="modal-header">
		          	<h4 class="modal-title mx-auto"><b>주문 상태 수정</b></h4>
		          	<!-- 모달창이 사라지는 아이콘 버튼 -->
		          	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        </div>	        
		        <form action="stateUpdate.do" method="post">		        	
		        	<input type="hidden" id="modalOrderNo" name="orderNo">
					<input id="modalId" name="id" type="hidden">			
					<input type="hidden" name="beforeUri" value="/order/adminList.do?${pageObject.pageQuery}">				
					<input type="hidden" name="optList" value="${pageObject.period}">				
			        <div class="modal-body">			        	
						<div class="input-group mb-3">
							<select class="form-control orderState" id="orderStateSelect" name="orderState">
								<option class="stateOpt" value="주문 확인">주문 확인</option>
								<option class="stateOpt" value="배송 대기">배송 대기</option>
								<option class="stateOpt" value="배송중">배송중</option>
								<option class="stateOpt" value="배송 완료">배송 완료</option>
								<option class="stateOpt" value="구매 확인">구매 확인</option>								
								<option class="stateOpt" value="취소 요청">취소 요청</option>
							</select>							
						</div>
			        </div>	        
			        
			        <div class="modal-footer">
			        	<!-- 배송지 변경을 클릭하면 모달창이 닫히고 입력된 데이터로 화면이 변한다. -->
			          	<button class="btn btn-dark" type="submit">변경</button>
			          	<!-- 모달창이 사라지는 버튼 -->
			          	<button type="button" class="btn btn-dark" data-dismiss="modal">닫기</button>
			        </div>
			        
			     </form>	        
	      	</div>
	 	</div>
	 </div>

</body>
</html>