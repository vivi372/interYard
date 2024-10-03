<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message view</title>

<style type="text/css">

.container {
	margin-top: 30px;
}
.senderInfo, .accepterInfo {
	width: 40%;
}
.msgContent {
	padding: 6px 12px;
	border-radius: 15px;
	color: white;
	background: #33334d;
}
.senderInfo > .msgContent {
	color: white;
	background: #8a00e6;
}
.senderInfo > .msgContent:hover {
	cursor: pointer;
	opacity: 70%;
}
.readed {
	margin: 42px 10px;
}
.uploadFileBtn, .uploadFile {
	padding: 5px;
}
.uploadFile	 {
  padding: 15px;
  display: none;
}
.acceptPhoto {
	text-align: right;
}
.sendPhoto {
	text-align: left;
}

</style>
<script>

$(function(){
	// 이벤트처리
	// 보낸 메시지 전송 취소
	// badge 클래스 안에 글자가 있으면 alert 를 보여라
	$(".senderInfo .msgContent").click(function(){
		let readed = $(this).closest(".media-body").find("span.badge").text();
		// alert(readed);
		let msgNo = $(this).closest(".media-body").find(".msgNo").val();
		// alert(msgNo);
		let accepterId = $(this).closest(".media-body").find(".accepterId").val()
		// alert(accepterId);
		if (readed) {
			if (confirm("전송을 취소하시겠습니까??")) {
				// alert("메시지 삭제");
				location = "delete.do?msgNo=" + msgNo + "&accepterId=" + accepterId + "&accept=0";
			}
		} else {
			alert("해당 메시지는 전송 취소할 수 없습니다.");
		}
	});
	// 첨부파일 블럭 슬라이드
	$(".uploadFileBtn").click(function(){
		$(this).next(".uploadFile").slideToggle("slow");
	});
});

</script>

</head>
<body>

<div class="container">

<!-- 메시지 상세보기 리스트 -->
<p class="font-weight-bolder text-center" style="font-size: 150%;">${mvo.get(0).title }</p>
<c:forEach items="${mvo }" var="msgvo">
	<!-- 보낸 메시지 >> 우측 정렬 >> 본인 -->
	<c:if test="${login.id == msgvo.senderId }">
		<div class="media p-3">
			<div class="media-body text-right mr-2">
				<div class="senderInfo float-right">
					<input class="msgNo" name="msgNo" value="${msgvo.msgNo }" type="hidden">
					<input class="accepterId" name="accepterId" value="${msgvo.accepterId }" type="hidden">
					${msgvo.senderName } <small><i>&lt; @${msgvo.senderId } &gt;</i></small>
					<div class="msgContent">
						<div>${msgvo.content }</div>
					</div>
					<!-- 첨부파일 표시 -->
					<c:if test="${!empty msgvo.uploadFile }">
						<div class="sendPhoto">
							<div class="uploadFileBtn"><i class="fa fa-chevron-down"></i> 첨부파일</div>
							<div class="uploadFile">
								<a href="${msgvo.uploadFile }" download>
									<img src="${msgvo.uploadFile }" alt="첨부파일" height="142">
								</a>
							</div>
						</div>
					</c:if>
					<!-- 전송시간 : sendDate -->
					<c:set var="datePart" value="${fn:substring(msgvo.sendDate, 0, 10)}" />
					<c:set var="timePart" value="${fn:substring(msgvo.sendDate, 11, 16)}" />
					<span style="float: left;">${datePart} ${timePart}</span>
				</div>
				<!-- 읽음표시 -->
				<span class="badge badge-dark readed">${(empty msgvo.acceptDate)?1:'' }</span>
			</div>
			<img src="${(empty msgvo.senderPhoto)?'/upload/image/userPhoto.png':msgvo.senderPhoto }" 
				class="mt-3 rounded-circle" style="width:60px;">
		</div>
	</c:if>
	<!-- 받은 메시지 >> 좌측정렬 >> 상대방 -->
	<c:if test="${login.id != msgvo.senderId }">
		<div class="media p-3">
			<img src="${(empty msgvo.senderPhoto)?'/upload/image/userPhoto.png':msgvo.senderPhoto }" 
				class="mr-3 mt-3 rounded-circle" style="width:60px;">
			<div class="media-body">
				<div class="accepterInfo float-left">
					<input class="msgNo" name="msgNo" value="${msgvo.msgNo }" type="hidden">
					${msgvo.senderName } <small><i>&lt; @${msgvo.senderId } &gt;</i></small>
					<div class="msgContent">
						<div>${msgvo.content }</div>
					</div>
					<!-- 첨부파일 표시 -->
					<c:if test="${!empty msgvo.uploadFile }">
						<div class="acceptPhoto">
							<div class="uploadFileBtn"><i class="fa fa-chevron-down"></i> 첨부파일</div>
							<div class="uploadFile">
								<a href="${msgvo.uploadFile }" download>
									<img src="${msgvo.uploadFile }" alt="첨부파일" height="142">
								</a>
							</div>
						</div>
					</c:if>
					<!-- 전송시간 : sendDate -->
					<c:set var="datePart" value="${fn:substring(msgvo.sendDate, 0, 10)}" />
					<c:set var="timePart" value="${fn:substring(msgvo.sendDate, 11, 16)}" />
					<span style="float: right;">${datePart} ${timePart}</span>
				</div>
				<div>
					<!-- 읽음표시 -->
					<span class="badge badge-dark readed">${(empty msgvo.acceptDate)?1:'' }</span>
				</div>
			</div>
		</div>
	</c:if>
</c:forEach>

<div style="margin-top: 15px;">
	<button type="button" class="btns" 
		data-toggle="modal" data-target="#replymModal">답장</button>		
	<a href="list.do" class="btns">돌아가기</a>
	<!-- 받은 메시지만 삭제 가능 -->
	<c:if test="${mvo.get(0).accepterId == login.id }">
		<a href="delete.do?msgNo=${mvo.get(0).msgNo }&accept=1" class="btns">채팅방 나가기</a>
	</c:if>
</div>

</div>

<!-- 답장 쓰기 모달 -->
<div class="modal" id="replymModal">
	<div class="modal-dialog">
		<div class="modal-content">
		
			<div class="modal-header">
				<h4 class="modal-title">답장 하기</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<form action="write.do" method="post" enctype="multipart/form-data">
				<!-- 페이지 정보 -->
				<input name="perPageNum" value="${param.perPageNum }" type="hidden">
				<!-- 메시지 데이터 정보 -->
				<input name="title" value="[Re:]${mvo.get(0).title }" type="hidden">
				<input name="accepterId" 
					value="${(login.id != mvo.get(0).getSenderId())?mvo.get(0).senderId : (mvo.get(0).accepterId)}" 
					type="hidden">
				<input name="refNo" value="${mvo.get(0).refNo }" type="hidden">
				<input name="ordNo" value="${(empty mvo)?1:mvo.get(0).ordNo + 1 }" type="hidden">
				<input name="levNo" value="${(empty mvo)?0:mvo.get(0).levNo + 0 }" type="hidden">
				<input name="parentNo" value="${mvo.get(0).msgNo }" type="hidden">
				<!-- Modal body -->
				<div class="modal-body">
					<div class="row form-group">
						<div class="col-md-3">
							<label for="content">내용</label>
						</div>
						<div class="col-md-9">
							<textarea class="form-control" rows="7" placeholder="내용 입력, 최대 300 자" 
								id="content" name="content" maxlength="300" required></textarea>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-md-3">
							<label for="uploadFile">첨부 파일</label>
						</div>
						<div class="col-md-9">
							<input type="file" class="form-control" id="uploadFile" name="uploadFile" 
								style="border-style: none;">
						</div>
					</div>
				</div>
				
				<!-- Modal footer -->
				<div class="modal-footer">
					<button class="btns">전송</button>
					<button type="button" class="btns" data-dismiss="modal">취소</button>
				</div>
			</form>
			
		</div>
	</div>
</div>

</body>
</html>