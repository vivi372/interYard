<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNavQna" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA List</title>
<script type="text/javascript">
	$(function() {
		$(".dataRow").click(function() {
			let no = $(this).find(".no").data("data");
			let rnum = $(this).find(".rnum").data("data");
			console.log("no = " + no);
			console.log("rnum = " + rnum);
			location = "view.do?qnaNo=" + no + "&inc=1&rnum=" + rnum + "&${pageObject.pageQuery}";
		});
		
		// perPageNum 처리
		$("#perPageNum").change(function() {
			// 			 alert("change perPageNum")
			// page는 1페이지 + 검색 데이터를 전부 보낸다.
			$("#searchForm").submit();
		})

		// 검색 데이터 세팅
		$("#key").val("${(empty pageObject.ctg)?'t':pageObject.ctg}");
		$("#perPageNum").val(
				"${(empty pageObject.perPageNum)?'10':pageObject.perPageNum}");
		
		// 카테고리 버튼
		$(".ctgBtn").click(function () {
			let ctg = $(this).parent(".collapse").data("data");
			let semiCtg = $(this).data("data");
// 			alert("ctgBtn click" + ctg + "/" + semiCtg);
			$(".ctgs").val("${(empty pageObject.ctg)?'':pageObject.ctg}");
			$(".semiCtg").val("${(empty pageObject.semiCtg)?'':pageObject.semiCtg}");
			
			location = "list.do?&ctg=" + ctg + "&semiCtg=" + semiCtg;
			
// 			$("#ctgDisplay").val(" > " + ctg);
// 			$("#semiCtgDisplay").val(" > " + semiCtg);

		});
	});
</script>
<style type="text/css">
.dataRow:hover {
	background: #e0e0e0;
	cursor: pointer;
}
.ctg:hover {
	background: #e0e0e0;
	cursor: pointer
}
.ctg {
	border-radius:10px;
}
</style>
</head>
<body>
<br>
	<div class="container">
		<div class="float-right">
			<button onclick="location.href='list.do'" class="btn btn-light">
				<b>전체보기</b>
			</button>
			<button onclick="location.href='qWriteForm.do?${pageObject.pageQuery}'" class="btns mb-1">
				<b>질문하기</b>
			</button>
		</div>
		<h4>QnA 리스트 
			<c:if test="${!empty param.ctg }">
				> ${param.ctg } > ${param.semiCtg }
			</c:if>
		</h4>
		<hr>
		<form action="list.do">
			<div class="row">
				<div class="col-sm-8">
					<div class="input-group mb-8">
						<div class="input-group-prepend">
							<select name="key" id="key" class="form-control">
								<option selected value="t">제목</option>
								<option value="c">내용</option>
								<option value="i">아이디</option>
								<option value="tc">제목 + 내용</option>
								<option value="ti">제목 + 아이디</option>
								<option value="ci">내용 + 아이디</option>
								<option value="tci">모두</option>
							</select>
						</div>
						<input type="text" class="form-control" placeholder="검색" id="word"
							name="word" value="${pageObject.word }"
						>
						<div class="input-group-append">
							<button class="btn btn-dark">
								<i class="fa fa-search"></i>
							</button>
						</div>

					</div>
				</div>
				<!-- col-md-8의 끝 : 검색 -->
				<div class="col-sm-4">
					<!-- 너비 조정을 위한 div 추가. float-right : 오른쪽 정렬 -->
					<div style="width: 170px;" class="float-right">
						<div class="input-group mb-2">
							<div class="input-group-prepend">
								<span class="input-group-text">Rows/Page</span>
							</div>
							<select name="perPageNum" id="perPageNum" class="form-control">
								<option value="10">10</option>
								<option value="15">15</option>
								<option value="20">20</option>
								<option value="25">25</option>
								<option value="30">30</option>
							</select>
						</div>
					</div>
				</div>
				<!-- col-sm-4 의 끝 : 한페이지당 표시 데이터 개수 -->
			</div>
		</form>
		<hr>
		<!-- 자주 묻는 질문(인기 많은 질문) -->
		<h5> <span class="text-white p-2" style="background: #006699; border-radius:10px 10px 0px 0px;">FAQ. 자주 묻는 질문</span></h5>
		<div style="border: 5px solid #006699; border-radius:0px 10px 10px 10px; padding: 5px;">
			<table class="table table-hover">
				<c:forEach items="${faqList }" var="faq">
					<tr class="dataRow table-light">
						<td class="float-right no" data-data="${faq.qnaNo }">${faq.qnaNo}.</td>
						<td class="rnum" data-data="${faq.rnum }"><b>[${faq.ctg }]
								[${faq.semiCtg}] ${faq.title} </b> <c:if test="${faq.answerCnt != 0}">
								<span class="badge badge-danger ml-2">${faq.answerCnt }</span>
							</c:if></td>
						<td>${faq.blindName }</td>
						<td>${faq.writeDate }</td>
						<td>${faq.hit }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<hr>
		<!-- 카테고리 검색 -->
		<div>
			<table class="table table-hober"
				style="border-radius: 10px;"
			>
				<tr style="text-align: center;">
					<th class="bg-success text-white ctg" data-toggle="collapse" data-target="#goods" style="margin:0 auto;">
						<p>상품<p>
						<div id="goods" class="collapse ctgs" data-data="상품문의">
						 	<button class="btn btn-light ctgBtn" data-data="주문/결제/입금확인"> > 주문/결제/입금확인 </button>
						 	<button class="btn btn-light ctgBtn" data-data="배송문의"> > 배송문의 </button>
						 	<button class="btn btn-light ctgBtn" data-data="반품/교환/AS/환불"> > 반품/교환/AS/환불</button>
						 	<button class="btn btn-light ctgBtn" data-data="이벤트/할인쿠폰"> > 이벤트/할인쿠폰 </button>
						 	<button class="btn btn-light ctgBtn" data-data="안전거래/시스템오류"> > 안전거래/시스템오류 </button>
						</div>
					</th>
					<th class="bg-primary text-white ctg " data-toggle="collapse" data-target="#ticket" >
						<p>티켓<p>
						<div id="ticket" class="collapse ctgs" data-data="티켓문의">
						 	<button class="btn btn-light ctgBtn" data-data="티켓"> > 티켓 </button>
						 	<button class="btn btn-light ctgBtn" data-data="회원/공통/기타"> > 회원/공통/기타</button>
						</div>
					</th>
					<th class="bg-warning text-white ctg" data-toggle="collapse" data-target="#books" >
						<p>도서<p>
						<div id="books" class="collapse ctgs" data-data="도서문의">
						 	<button class="btn btn-light ctgBtn" data-data="주문/결제"> > 주문/결제 </button>
						 	<button class="btn btn-light ctgBtn" data-data="배송"> > 배송 </button>
						 	<button class="btn btn-light ctgBtn" data-data="취소/반품/교환/환불"> > 취소/반품/교환/환불</button>
						 	<button class="btn btn-light ctgBtn" data-data="상품문의"> > 상품문의 </button>
						 	<button class="btn btn-light ctgBtn" data-data="회원/시스템"> > 회원/시스템 </button>
						 	<button class="btn btn-light ctgBtn" data-data="중고책 판매"> > 중고책 판매 </button>
						 	<button class="btn btn-light ctgBtn" data-data="법인/대량주문"> > 법인/대량주문 </button>
						</div>
					</th>
					<th class="bg-info text-white ctg " data-toggle="collapse" data-target="#tour" >
						<p>투어<p>
						<div id="tour" class="collapse ctgs" data-data="투어문의">
						 	<button class="btn btn-light ctgBtn" data-data="항공"> > 항공 </button>
						 	<button class="btn btn-light ctgBtn" data-data="패키지"> > 패키지</button>
						 	<button class="btn btn-light ctgBtn" data-data="숙소"> > 숙소</button>
						 	<button class="btn btn-light ctgBtn" data-data="투어티켓"> > 투어티켓</button>
						 	<button class="btn btn-light ctgBtn" data-data="회원/공통/기타"> > 회원/공통/기타</button>
						</div>
					</th>
					<th class="bg-dark text-white ctg " data-toggle="collapse" data-target="#other" >
						<p>기타<p>
						<div id="other" class="collapse ctgs" data-data="기타문의">
						 	<button class="btn btn-light ctgBtn" data-data="기타문의"> > 기타 문의 </button>
						</div>
					</th>
				</tr>
			</table>
		</div>
		<c:if test="${empty list }">
			게시글이 존재하지 않습니다.<br>
			게시글을 등록해 주세요.	
		</c:if>
		<c:if test="${!empty list }">

			<table class="table table-hover">
				<tr style="font-size:13px;" class="bg-dark text-white">
					<th style="text-align:center; border-radius: 8px 0px 0px 8px;">No</th>
					<th >질문글</th>
					<th style="text-align:center;">작성자</th>
					<th style="text-align:center;">등록날짜</th>
					<th style="text-align:center; border-radius: 0px 8px 8px 0px;">Hit</th>
				</tr>
				<c:forEach items="${list }" var="vo">
					<tr class="dataRow table-light">
						<td class="float-right no" data-data="${vo.qnaNo }" data-id="${vo.id }" data-name="${vo.name }">${vo.qnaNo}.</td>
						<td class="rnum" data-data="${vo.rnum }"><b>[${vo.ctg }]
								[${vo.semiCtg}] ${vo.title} </b> <c:if test="${vo.answerCnt != 0}">
								<span class="badge badge-danger ml-2">${vo.answerCnt }</span>
							</c:if></td>
						<td>${vo.blindName }</td>
						<td>${vo.writeDate }</td>
						<td>${vo.hit }</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<hr>
		<div style="margin:0 auto;">
			<!-- 페이지 네이션 -->
			<div>
				<pageNavQna:pageNavQna listURI="list.do" pageObject="${pageObject }"></pageNavQna:pageNavQna>
			</div>
		</div>
	</div>
</body>
</html>