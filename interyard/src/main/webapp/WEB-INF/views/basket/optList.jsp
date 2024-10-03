<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 리스트가 비어있지 않을 때 옵션을 선택할 수 있는 드롭다운 메뉴를 생성 -->
<c:if test="${!empty list }">
	<select class="form-control" id="opt">
		<!-- 기본 옵션 항목, 초기 선택 상태이며 표시되지 않음 -->
		<option value="" selected style="display: none;">옵션</option>
		<!-- 리스트에 있는 항목들을 순회하며 드롭다운에 옵션 추가 -->
		<c:forEach items="${list }" var="vo2">
			<option value="${vo2.optNo }" data-optPrice="${vo2.optPrice + vo2.goodsPrice }" class="optList" data-optName="${vo2.optName }">${vo2.optName } (+${vo2.optPrice })</option>
		</c:forEach>
	</select>
</c:if>

<!-- 리스트가 비어 있을 때, 수량을 선택할 수 있는 UI를 생성 -->
<c:if test="${empty list }">
	<div class="optListItem">
		<!-- 옵션 번호, 옵션 이름 및 관련 주문을 숨김 필드로 설정 -->
		<input type="hidden" name="optNo" value="1">
		<input type="hidden" name="optName" value="/">
		<input type="hidden" name="lefOrder" value="1">
		<div class="optPrice" style="display: none"></div>
		<!-- 수량 선택 인터페이스 (숫자 증가/감소 버튼과 입력 필드) -->
		<div class="input-group mb-3 optAmount" style="width: 150px;">
			<div class="input-group-prepend">
				<!-- 마이너스 버튼 -->
				<button class="btn btn-dark minusBtn" type="button">
					<i class="fa fa-minus"></i>
				</button>
			</div>
			<input type="text" name="amount" class="form-control amount" value="0">
			<div class="input-group-append">
				<!-- 플러스 버튼 -->
				<button class="btn btn-dark plusBtn" type="button">
					<i class="fa fa-plus"></i>
				</button>
			</div>
		</div>
	</div>
</c:if>
