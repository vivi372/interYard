<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${(qvo.levNo == 0)?'질문':'답변' }글 수정하기 폼</title>
<script type="text/javascript">
	$(function () {
		$("#ctg").change(function() {
			let ctg = $(this).val();
			
			$("#semiCtg").empty();
			
			if (ctg == "상품문의") {
				$('#semiCtg').append('<option value="주문/결제/입금확인" ${(qvo.semiCtg == "주문/결제/입금확인")?"selected":"" }>주문/결제/입금확인</option>');
				$('#semiCtg').append('<option value="배송문의" ${(qvo.semiCtg == "배송문의")?"selected":"" }>배송문의</option>');
				$('#semiCtg').append('<option value="반품/교환/AS/환불" ${(qvo.semiCtg == "반품/교환/AS/환불")?"selected":"" }>반품/교환/AS/환불</option>');
				$('#semiCtg').append('<option value="이벤트/할인쿠폰" ${(qvo.semiCtg == "이벤트/할인쿠폰")?"selected":"" }>이벤트/할인쿠폰</option>');
				$('#semiCtg').append('<option value="안전거래/시스템오류" ${(qvo.semiCtg == "안전거래/시스템오류")?"selected":"" }>안전거래/시스템오류</option>');
			}
			if (ctg == "티켓문의") {
				$('#semiCtg').append('<option value="티켓" ${(qvo.semiCtg == "티켓")?"selected":"" }>티켓</option>');
				$('#semiCtg').append('<option value="회원/공통/기타" ${(qvo.semiCtg == "회원/공통/기타")?"selected":"" }>회원/공통/기타</option>');
			}
			if (ctg == "도서문의") {
				$('#semiCtg').append('<option value="주문/결제" ${(qvo.semiCtg == "주문/결제")?"selected":"" }>주문/결제</option>');
				$('#semiCtg').append('<option value="배송" ${(qvo.semiCtg == "배송")?"selected":"" }>배송</option>');
				$('#semiCtg').append('<option value="취소/반품/교환/환불" ${(qvo.semiCtg == "취소/반품/교환/환불")?"selected":"" }>취소/반품/교환/환불</option>');
				$('#semiCtg').append('<option value="상품문의" ${(qvo.semiCtg == "상품문의")?"selected":"" }>상품문의</option>');
				$('#semiCtg').append('<option value="회원/시스템" ${(qvo.semiCtg == "회원/시스템")?"selected":"" }>회원/시스템</option>');
				$('#semiCtg').append('<option value="중고책 판매" ${(qvo.semiCtg == "중고책 판매")?"selected":"" }>중고책 판매</option>');
				$('#semiCtg').append('<option value="법인/대량주문" ${(qvo.semiCtg == "법인/대량주문")?"selected":"" }>법인/대량주문</option>');
			}
			if (ctg == "투어문의") {
				$('#semiCtg').append('<option value="항공" ${(qvo.semiCtg == "항공")?"selected":"" }>항공</option>');
				$('#semiCtg').append('<option value="패키지" ${(qvo.semiCtg == "패키지")?"selected":"" }>패키지</option>');
				$('#semiCtg').append('<option value="숙소" ${(qvo.semiCtg == "숙소")?"selected":"" }>숙소</option>');
				$('#semiCtg').append('<option value="투어티켓" ${(qvo.semiCtg == "투어티켓")?"selected":"" }>투어티켓</option>');
				$('#semiCtg').append('<option value="회원/공통/기타" ${(qvo.semiCtg == "회원/공통/기타")?"selected":"" }>회원/공통/기타</option>');
			}
			if (ctg == "기타문의") {
				$('#semiCtg').append('<option value="기타문의" ${(qvo.semiCtg == "기타문의")?"selected":"" }>기타문의</option>');
			}
		})
	});
</script>
</head>
<body>
<br>
	<div class="container">
		<H4>${(qvo.levNo == 0)?'질문':'답변' }하기 수정</H4>
		<hr>
		<div class="bg-light p-3">
			<form action="update.do" method="post">
				<div class="form-inline mb-3">
					<div class="form-group">
						<input type="hidden" value="${qvo.qnaNo }" id="qnaNo" name="qnaNo">
						<input type="hidden" value="${qvo.refNo }" id="refNo" name="refNo">
						<input type="hidden" value="${qvo.ordNo }" id="ordNo" name="ordNo">
						<input type="hidden" value="${qvo.levNo}" id="levNo" name="levNo">
						<input type="hidden" value="${qvo.qnaNo }" id="parentNo" name="parentNo">
						<input type="hidden" value="${param.rnum }">
					
						<label for="ctg"><i class="fa fa-angle-right"></i><span class="ml-1"><b>질문 유형</b></span></label> 
						<c:if test="${qvo.levNo == 0 }">
							<select class="form-control ml-2 mr-5" id="ctg" name="ctg" style="width:300px;">
								<option value="상품문의" ${(qvo.ctg == '상품문의')?'selected':'' }>상품문의</option>
								<option value="배송문의" ${(qvo.ctg == '도서문의')?'selected':'' }>도서문의</option>
								<option value="티켓문의" ${(qvo.ctg == '티켓문의')?'selected':'' }>티켓문의</option>
								<option value="투어문의" ${(qvo.ctg == '투어문의')?'selected':'' }>투어문의</option>
								<option value="기타문의" ${(qvo.ctg == '기타문의')?'selected':'' }>기타</option>
							</select>
						</c:if>
						<c:if test="${qvo.levNo != 0 }">
							<input class="form-control ml-2 mr-5" id="ctg" name="ctg" value="${qvo.ctg }" style="width:300px;" readonly>
						</c:if>
					</div>
					<div class="form-group">
						<label for="semiCtg"><i class="fa fa-angle-right"></i> <span class="ml-1"><b>분류</b></span></label> 
						<c:if test="${qvo.levNo == 0 }">
							<select class="form-control  ml-2 mr-5" id="semiCtg" name="semiCtg" style="width:300px;">
								<option value="주문/결제/입금확인" >주문/결제/입금확인</option>
								<option value="배송문의" >배송문의</option>
								<option value="반품/교환/AS/환불" >반품/교환/AS/환불</option>
								<option value="이벤트/할인쿠폰" >이벤트/할인쿠폰</option>
								<option value="안전거래/시스템오류" >안전거래/시스템오류</option>
							</select>
						</c:if>
						<c:if test="${qvo.levNo != 0 }">
							<input class="form-control ml-2 mr-5" id="semiCtg" name="semiCtg" value="${qvo.semiCtg }" style="width:300px;" readonly>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label for="title"><i class="fa fa-angle-right"></i><span class="ml-1 "><b>제목</b></span></label>
					<input id="title" class="form-control mb-2" name="title" value="${qvo.title }">
					<label for="content"><i class="fa fa-angle-right"></i><span class="ml-1"><b>내용</b></span></label>
					<textarea id="content" class="form-control" rows="15" name="content">${qvo.content }</textarea>
				</div>
				<p>
				<button class="btns"><b>수정</b></button>
				<button type="reset" class="btns"><b>초기화</b></button>
				<button type="button" class="btn btn-light" onclick="history.back()"><b>돌아가기</b></button>
			</form>
		</div>
	</div>

</body>
</html>