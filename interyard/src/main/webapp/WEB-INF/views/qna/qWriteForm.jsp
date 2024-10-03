<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문하기</title>
<script type="text/javascript">
	$(function () {
		$("#ctg").change(function() {
			let ctg = $(this).val();
			
			$("#semiCtg").empty();
			
			if (ctg == "상품문의") {
				$('#semiCtg').append('<option value="주문/결제/입금확인">주문/결제/입금확인</option>');
				$('#semiCtg').append('<option value="배송문의">배송문의</option>');
				$('#semiCtg').append('<option value="반품/교환/AS/환불">반품/교환/AS/환불</option>');
				$('#semiCtg').append('<option value="이벤트/할인쿠폰">이벤트/할인쿠폰</option>');
				$('#semiCtg').append('<option value="ti">안전거래/시스템오류</option>');
			}
			if (ctg == "티켓문의") {
				$('#semiCtg').append('<option value="티켓">티켓</option>');
				$('#semiCtg').append('<option value="회원/공통/기타">회원/공통/기타</option>');
			}
			if (ctg == "도서문의") {
				$('#semiCtg').append('<option value="주문/결제">주문/결제</option>');
				$('#semiCtg').append('<option value="배송">배송</option>');
				$('#semiCtg').append('<option value="취소/반품/교환/환불">취소/반품/교환/환불</option>');
				$('#semiCtg').append('<option value="상품문의">상품문의</option>');
				$('#semiCtg').append('<option value="회원/시스템">회원/시스템</option>');
				$('#semiCtg').append('<option value="중고책 판매">중고책 판매</option>');
				$('#semiCtg').append('<option value="법인/대량주문">법인/대량주문</option>');
			}
			if (ctg == "투어문의") {
				$('#semiCtg').append('<option value="항공">항공</option>');
				$('#semiCtg').append('<option value="패키지">패키지</option>');
				$('#semiCtg').append('<option value="숙소">숙소</option>');
				$('#semiCtg').append('<option value="투어티켓">투어티켓</option>');
				$('#semiCtg').append('<option value="회원/공통/기타">회원/공통/기타</option>');
			}
			if (ctg == "기타문의") {
				$('#semiCtg').append('<option value="기타문의">기타문의</option>');
			}
		})
	});
</script>
</head>
<body>
<br>
	<div class="container">
		<H4>질문하기</H4>
		<hr>
		<div class="bg-light p-3">
			<form action="qWrite.do" method="get">
			<div class="form-inline mb-3">
				<input type="hidden" value="${param.perPageNum }" id="perPageNum" name="perPageNum">
			
				<div class="form-group">
					<label for="ctg"><i class="fa fa-angle-right"></i> <span class="ml-1"><b>질문 유형</b></span></label> 
					<select class="form-control ml-2 mr-5" id="ctg" name="ctg" style="width:300px;">
						<option value="상품문의">상품문의</option>
						<option value="티켓문의">티켓문의</option>
						<option value="도서문의">도서문의</option>
						<option value="투어문의">투어문의</option>
						<option value="기타문의">기타</option>
					</select>
				</div>
				<div class="form-group">
					<label for="semiCtg"><i class="fa fa-angle-right"></i> <span class="ml-1"><b>분류</b></span></label> 
					<select class="form-control ml-2 mr-5" id="semiCtg" name="semiCtg" style="width:300px;">
						<option value="주문/결제/입금확인">주문/결제/입금확인</option>
						<option value="배송문의">배송문의</option>
						<option value="반품/교환/AS/환불">반품/교환/AS/환불</option>
						<option value="이벤트/할인쿠폰">이벤트/할인쿠폰</option>
						<option value="안전거래/시스템오류">안전거래/시스템오류</option>
					</select>
				</div>
			</div>
				<div class="form-group">
					<label for="title"><i class="fa fa-angle-right"></i> <b>제목</b></label>
					<input id="title" class="form-control" name="title">
					<br>
					<label for="content"><i class="fa fa-angle-right"></i> <b>내용</b></label>
					<textarea id="content" class="form-control" rows="13" name="content"></textarea>
				</div>
				<p>
				<button class="btns"><b>등록</b></button>
				<button type="reset" class="btns"><b>초기화</b></button>
				<button type="button" class="btn btn-light" onclick="history.back()"><b>돌아가기</b></button>
			</form>
		</div>
	</div>

</body>
</html>