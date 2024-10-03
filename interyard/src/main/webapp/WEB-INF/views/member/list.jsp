<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
<style type="text/css">


</style>
<script type="text/javascript">
$(function(){
	
	// 회원 한줄을 클릭하면 회원 정보보기로 이동 시키는 처리
	function dataRowClick(id){
		
		location = "view.do?id="+id+"&admin=1";
	}
	
	// 이벤트처리
	$(".dataRow").on("click", function(){
		let id = $(this).find(".id").text();
		dataRowClick(id);
		
	});
	
	$(".grade, .status").parent()
	.on("mouseover", function(){
		// dataRow의 click 이벤트를 없앤다.
		$(".dataRow").off("click");
	})
	.on("mouseout", function(){
		// dataRow의 click 이벤트를 다시 설정해준다..
		$(".dataRow").on("click", function(){
			let id = $(this).find(".id").text();
			dataRowClick(id);
		});
	});
	
	$(".dataRow").on("change", ".grade, .status", function(){
		// alert("값이 바뀜");
		// this - select 태그 선택 .next() 다음 태그 - div 태그
		// 변경되었는지 알아내는 것 처리.
		let changeData =  $(this).val();
		let data = $(this).data("data"); // data-data 속성으로 넣어 둔다.
		console.log("원래 데이터=" + data + ", 바꿀 데이터=" + changeData);
		if(changeData == data)
			$(this).next().find("button").prop("disabled", true);
		else
			$(this).next().find("button").prop("disabled", false);
	});

});
</script>
<style type="text/css">
.data{
	text-align: center;
}
.dataRow:hover {
	background: #ddd;
	cursor: pointer;
}
</style>
</head>
<body>
<div class="container">
	<h2 style="margin-top: 10px;">회원 리스트</h2>
		<table class="table">
		<thead>
			<tr class="data">
				<td>사진</td>
				<td>아이디</td>
				<td>이름</td>
				<td>성별</td>
				<td>연락처</td>
				<td>등급</td>
				<td>상태</td>
			</tr>	
		</thead>		
		<tbody>
		 <c:forEach items="${list }" var="vo">
			 <tr class="dataRow">
			 	<td>
				 	<c:if test="${ !empty vo.photo}">
				 	<img src="${vo.photo }" style="width:30px; height:30px;">
				 	</c:if>
				 	<c:if test="${ empty vo.photo}">
				 	<i class="fa fa-user-circle-o" style="font-size:30px;"></i>
				 	</c:if>
			 	</td>
				<td class="id">${vo.id }</td>			 
				<td>${vo.name }</td>			 
				<td>${vo.gender }</td>			 
				<td>${vo.tel }</td>		
				<td>
					<form action="changeGrade.do">
					<input name="id" value="${vo.id }" type="hidden">
						<div class="input-group mb-3">
							<select class="form-control grade" name="gradeNo"  data-data="${vo.gradeNo }">
							<option value="1" ${(vo.gradeNo == 1)?"selected":"" }>일반회원</option>
							<option value="9" ${(vo.gradeNo == 9)?"selected":"" }>관리자</option>
							</select>	
								<div class="input-group-append">
								<button class="btn btn-outline-info gradeS" type="submit" disabled>변경</button>
								</div>
						</div>
					</form>
				</td>	 
				<td>
					<form action="changeStatus.do">
					<input name="id" value="${vo.id }" type="hidden">
						<div class="input-group mb-3">
							<select class="form-control status" name="status" id="status" data-data="${vo.status }">
							<option  ${(vo.status == "정상")?"selected":"" }>정상</option>
							<option  ${(vo.status == "탈퇴")?"selected":"" }>탈퇴</option>
							<option  ${(vo.status == "휴면")?"selected":"" }>휴면</option>
							<option  ${(vo.status == "강퇴")?"selected":"" }>강퇴</option>
							</select>	
								<div class="input-group-append">
								<button class="btn btn-outline-info " type="submit" disabled>변경</button>
								</div>
						</div>
					</form>
				</td>	 
			 </tr>
		 </c:forEach>
		
		
		</tbody>
		</table>
		<div><pageNav:pageNav listURI="list.do" pageObject="${pageObject }"></pageNav:pageNav></div>
</div>
</body>
</html>