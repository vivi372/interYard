<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/checkBax.css">

<title>Message Main</title>
<style type="text/css">

h3 {
	margin: 10px 0;
	text-align: center;
}
.listBox > .row {
	padding: 12px;
	border-bottom: 1px solid #e0e0e0;
	margin: 0 3px;
}
.title {
	text-decoration: underline;
}
.dataRow:hover {
	background: #e0e0e0;
	cursor: pointer;
}

</style>
<script type="text/javascript">
	$(function(){
		// 이벤트 처리
		// 검색 데이터 세팅
		$("#key").val("${(empty pageObject.key)?'t':pageObject.key}");
		$("#perPageNum")
			.val("${(empty pageObject.perPageNum)?'10':pageObject.perPageNum}");
		
		// 전체 선택
		$("#allDeleteBox").click(function(){
			if ($(this).is(":checked")) {
				$(".deleteBox").prop("checked", true);
			} else {
				$(".deleteBox").prop("checked", false);
			}
		});
		
		// 전체 선택 해제
		$(".deleteBox").click(function(){
			let total = $(".deleteBox").length;
			let checked = $(".deleteBox:checked").length;
			
			if (total != checked) {
				$("#allDeleteBox").prop("checked", false);
			} else {
				$("#allDeleteBox").prop("checked", false);
			}
		});
		
		// 칼럼을 클릭하면 상세보기로 화면 이동
		$(".title").click(function(){
			let msgNo = $(this).closest(".dataRow").find(".no").val();
			let no = $(this).closest(".dataRow").find(".refNo").val();
			// alert(no);
			let accept = $(this).closest(".dataRow").data("accept");
			// alert(accept);
			location = "view.do?msgNo=" + msgNo + "&no=" + no 
					+ "&mode=${pageObject.acceptMode}&${pageObject.pageQuery}"
					+ "&accept=" + accept;
		});
	});
</script>

</head>
<body>

<div class="container">

<h3>chat</h3>

<form action="list.do" id="searchForm">
<input name="page" value="1" type="hidden">
<div class="row">
	<div class="col-md-7">
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<select name="key" id="key" class="form-control">
					<option value="s">sender Id</option>
					<option value="t">title</option>
					<option value="c">content</option>
					<option value="tc">title/content</option>
				</select>
			</div>
			<input type="text" class="form-control" placeholder="검색"
			 	id="word" name="word" value="${pageObject.word }">
			<div class="input-group-append">
				<button class="btn btn-outline-dark">
					<i class="fa fa-search"></i>
				</button>
			</div>
		</div>
	</div>
	<!-- col-md-8의 끝 : 검색 -->
	<!-- 메시지 유형 -->
	<div class="col-md-5">
		<div class="float-right">
			<button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">
				${(pageObject.acceptMode == 1)?"받은":(pageObject.acceptMode == 2)?"보낸":"전체" } 메시지
			</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="list.do?mode=1&${pageObject.pageQuery }">받은 메시지</a>
				<a class="dropdown-item" href="list.do?mode=2&${pageObject.pageQuery }">보낸 메시지</a>
				<a class="dropdown-item" href="list.do?mode=3&${pageObject.pageQuery }">전체 메시지</a>
			</div>
		</div>
	</div>
</div>
</form>
<!-- 메시지 리스트 출력  -->
<c:if test="${empty list }">
	<div class="alert alert-dark text-center">
		<strong>참여 중인 채팅이 없습니다.</strong>
	</div>
</c:if>
<c:if test="${!empty list }">
<div class="alert alert-dark text-center">
	<strong>content</strong>을 클릭해서, 메시지의 세부내용을 확인해보세요.
</div>
<form action="delete.do" method="post" class="listBox">
	<div class="row">
		<div class="col-md-1 input-group-prepend">
			<div class="custom-control custom-checkbox">
				<input type="checkbox" class="custom-control-input" id="allDeleteBox">
				<label class="custom-control-label" for="allDeleteBox"></label>
			</div>
		</div> <!-- 삭제 박스 -->
		<div class="col-md-2">user ID</div> <!-- 받은 편지 -->
		<div class="col-md-5">content</div> <!-- 채팅 내용 -->
		<div class="col-md-2">Date</div> <!-- 보낸 날짜 -->
		<div class="col-md-2"></div> <!-- 상태 -->
	</div>
	<c:forEach items="${list }" var="vo">
		<!-- 받은 메시지 - mode : 1 -->
		<c:if test="${vo.accepterId == login.id }">
			<div class="row dataRow 
				${(empty vo.acceptDate)?'font-weight-bolder':'' }" data-accept="${(vo.senderId == login.id)?0:1 }">
				<input class="refNo" name="refNo" value="${vo.refNo }" type="hidden">
				<div class="col-md-1 input-group-prepend">
					<div class="custom-control custom-checkbox">
						<input type="checkbox" class="custom-control-input deleteBox no" 
							id="${vo.msgNo }" name="msgNo" value="${vo.msgNo }">
						<label class="custom-control-label" for="${vo.msgNo }"></label>
					</div>
				</div> <!-- 삭제 박스 -->
				<div class="col-md-2">${vo.senderId }</div> <!-- 보낸 사람 >> 다른 사람 -->
				<div class="col-md-5 title">
					${vo.content } <span><small>${vo.title }</small></span>
				</div> <!-- 내용 + 제목 -->
				<div class="col-md-2">${vo.sendDate }</div> <!-- 보낸 날짜 -->
				<div class="col-md-2">
					<i class="${(empty vo.acceptDate)?'fa fa-envelope':'fa fa-envelope-open-o' }"></i>
				</div> <!-- 상태 -->
			</div>
		</c:if>
		<!-- 보낸 메시지 -->
		<c:if test="${vo.accepterId != login.id }">
			<div class="row dataRow" data-accept="${(vo.senderId == login.id)?0:1 }">
				<input class="refNo" name="refNo" value="${vo.refNo }" type="hidden">
				<div class="col-md-1 input-group-prepend">
					<div class="custom-control custom-checkbox">
						<input type="checkbox" class="custom-control-input deleteBox no" 
							id="${vo.msgNo }" name="msgNo" value="${vo.msgNo }">
						<label class="custom-control-label" for="${vo.msgNo }"></label>
					</div>
				</div> <!-- 삭제 박스 -->
				<div class="col-md-2">${vo.accepterId }</div> <!-- 보낸 사람 >> 나 -->
				<div class="col-md-5 title">
					${vo.content } <span><small>${vo.title }</small></span>
				</div> <!-- 내용 + 제목 -->
				<div class="col-md-2">${vo.sendDate }</div> <!-- 보낸 날짜 -->
				<div class="col-md-2">
					<i class="${(empty vo.acceptDate)?'fa fa-envelope':'fa fa-envelope-open-o' }"></i>
				</div> <!-- 상태 -->
			</div>
		</c:if>
	</c:forEach>
	<br>
	<!-- 삭제 : 체크박스에 표시된 쪽지 삭제 : 상태 변경 >> 삭제 -->
	<c:if test="${pageObject.acceptMode == 1 }">
		<button class="btns" id="deleteBtn">메시지 삭제</button>
	</c:if>
</form>
</c:if>

</div>

</body>
</html>