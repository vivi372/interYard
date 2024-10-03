<!-- 0730 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<style>
.tn{
margin-left: 10px;
}
</style>
<div class="card">
	<div class="card-header">
		<!-- 24 06 25 --> <!--  id="replywriteBtn" 06 27 추가 -->
		<c:if test="${!empty login}">
			<span class="btns float-right" id="replywriteBtn" >등록</span>
		</c:if>	
		<h3>댓글 리스트</h3>
	</div>
	
	<div class="card-body">
		<c:if test="${ empty replyVO }">
	 		댓글이 없습니다.
	 	</c:if>
		<c:if test="${ !empty replyVO }">
			<!-- 데이터가 있는 만큼 반복 처리 시작 -->
			<c:forEach items="${replyVO }" var="vo">
				<div class="card replyDataRow" data-rno="${vo.rno }" style="margin: 5px 0;">
					<div class="card-header">
						<span class="float-right">${vo.writeDate }</span> <b class="replyWriter">${vo.id }</b>
					</div>
					<div class="card-body">
						<pre class="replyContent">${vo.content }</pre>
					</div>
					<div class="tn"> <!--replyUpdateBtn  replyDeleteBtn 추가 0627 -->
						<c:if test="${!empty login && login.id == vo.id}">
							<button class="btns replyUpdateBtn">수정</button>
							<button class="btns replyDeleteBtn" >삭제</button>
						</c:if>	
					</div>
				</div>
			</c:forEach>
			<!-- 데이터가 있는 만큼 반복 처리 끝 -->
			<!-- 0628 -->
				<div class="d-flex justify-content-center"> 
					<pageNav:replayPageNav listURI="view.do" pageObject="${replypageObject}"  />
				</div>
		</c:if>
	</div>
</div>

<!--  댓글 등록 / 수정 / 삭제를 위한 모달창 -->
<div class="modal" id="replyModal">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<!--  버튼에 따라서 제목을 수정해서 사용 .text(제목) -->
				<h4 class="modal-title">댓글등록</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- 데이터를 넘기는 form tag -->
			<form id="replyForm" method="post" action="/reply/write.do">
				<input type="hidden" name="rno" id="rno"> 
				<input type="hidden" name="no" value="${param.ugno }">
				
				<!-- 페이지 정보 보내기 -->
				<input type="hidden" name="page" value="${param.page }">
				<input type="hidden" name="perPageNum" value="${param.perPageNum }">
				<input type="hidden" name="key" value="${param.key }">
				<input type="hidden" name="word" value="${param.word }">
				
				<!-- Modal body -->
				<div class="modal-body">
					<!-- 내용 / 작성자 / 비밀번호 -->
					<div class="form-group" id="contentDiv">
						<label for="content">댓글작성</label>
						<textarea class="form-control" rows="3" id="content" name="content"  
							 title="댓글을 입력해주세요." placeholder="댓글을 입력해주세요()." maxlength="100"></textarea>
							<div id="charCount">0/100</div>
							<div id="contentError" style="color: red; display: none;">댓글은 최대 100글자까지 입력할 수 있습니다.</div>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
				<button class="btns" id="replyModalWriteBtn" type="button">등록</button>    <!-- 0627 -->
				<button class="btns" id="replyModalUpdateBtn" type="button">수정</button> <!-- 0627 -->
				<button class="btns" id="replyModalDeleteBtn" type="button">삭제</button>  <!-- 0627 -->
					<button type="button" class="btns" data-dismiss="modal">취소</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 0627 -->
<script>
$(function () {
	
	//댓글 100글자 표시  	
	document.getElementById('content').addEventListener('input', function (event) {
	    var content = event.target.value;
	    var charCount = document.getElementById('charCount');
	    var contentError = document.getElementById('contentError');
	    
	    charCount.textContent = content.length + "/100";

	    if (content.length > 100) {
	        contentError.style.display = 'block';
	    } else {
	        contentError.style.display = 'none';
	    }
	});
	
	//모달 화면
	//댓글 등록
	//replywriteBtn 아이디를 가진 요소가 클릭되면 이 함수가 실행
	$("#replywriteBtn").click(function () {
		
		//모달 제목을 댓글 등록으로 설정
		$("#replyModal").find(".modal-title").text("댓글등록"); 
		//input / text를 선택한다
		// ReplyForm 아이디를 가진 모든 input, textarea 찾아서 보이게 한다
		$("#replyForm").find(".form-group").show();
		//data 지우기
		$("#replyForm").find(".form-group>input, .form-group>textarea").val("");
		
		//버튼을 선택
		//먼저 버튼이 다 보이게
		$("#replyForm button").show(); 
		//필요없는 버튼은 안보이게 한다
		//업데이트 버튼 삭제 버튼 삭제
		$("#replyModalUpdateBtn, #replyModalDeleteBtn").hide(); 
		//ReplyModal 이란 아이디 가진 모달창 보이게 한다
		$("#replyModal").modal("show"); 
	});

	//댓글 수정
	// 댓글 수정 버튼
	$(".replyUpdateBtn").click(function(){
		
		// 제목을 댓글 수정
		$("#replyModal").find(".modal-title").text("댓글 수정");
		// input / text를 선택한다. - 내용 / 작성자 / 비밀번호
		$("#ReplyForm").find(".form-group").show();
		
		// data 지우기
		$("#ReplyForm")
		.find(".form-group>input, .form-group>textarea").val("");

		// 댓글 번호와 내용, 작성자를 데이터를 수집해서 value값으로 세팅해야 한다.
		let replyDataRow = $(this).closest(".replyDataRow");
		// data("rno") -> tag 안에 data-rno = "값"
		let rno = replyDataRow.data("rno");
		let content = replyDataRow.find(".replyContent").text();
		let id = replyDataRow.find(".replyid").text();
		
		// reply Modal 입력란에 세팅하기
		$("#rno").val(rno);
		$("#content").val(content);
		$("#id").val(id);
		
		$("#ReplyForm").find("input, textarea").show();

		// 버튼을 선택
		//  먼저 버튼이 다 보이게
		$("#ReplyForm button").show();
		//  필요 없는 버튼은 안보이게 한다.
		$("#replyModalWriteBtn, #replyModalDeleteBtn").hide();
		
		$("#replyModal").modal("show");
	});
	
	//댓글 삭제
	$(".replyDeleteBtn").click(function () {
		// 제목을 댓글 등옥
		$("#replyModal").find(".modal-title")
		.text("댓글을 삭제 하시겠습니까?")
		//input / text를 선택한다
		let replyDataRow = $(this).closest(".replyDataRow");
		
		$("#replyForm").find(".form-group").hide();
		
		//data 지우기
		$("#replyForm").find(".form-group>input, .form-group>textarea").val("");
		
		// data("rno") -> tag 안에 data-rno = 값
		$("#rno").val($(this).closest(".replyDataRow").data("rno"));
		
		//버튼을 선택
		//버튼이 다 보이게
		$("#replyForm button").show(); 
		//필요없는 버튼은 안보이게 한다
		$("#replyModalUpdateBtn, #replyModalWriteBtn").hide(); 
		
		
		$("#replyModal").modal("show");
		
		
	});
	
	//--------------Modal 화면 안의 처리 버튼 이벤트 처리 --------------------------
	$("#replyModalWriteBtn").click(function(){
			//데이터 전송해서 실행된ㄴ uri를 지정한다
			$("#replyForm").attr("action", "/reply/write.do");
			//데이터를 전송하면서 페이지 이동을 시킨다 
			//다시 글보기 돌아가기
			$("#replyForm").submit();
	});
	$("#replyModalUpdateBtn").click(function(){
		//데이터 전송해서 실행된ㄴ uri를 지정한다
		$("#replyForm").attr("action", "/reply/update.do");
		//데이터를 전송하면서 페이지 이동을 시킨다 
		//다시 글보기 돌ㅇ
		$("#replyForm").submit();
	});
	$("#replyModalDeleteBtn").click(function(){
			//데이터 전송해서 실행된ㄴ uri를 지정한다
			$("#replyForm").attr("action", "/reply/delete.do");
			//데이터를 전송하면서 페이지 이동을 시킨다 
			//다시 글보기 돌ㅇ
			$("#replyForm").submit();
	});
});
</script>