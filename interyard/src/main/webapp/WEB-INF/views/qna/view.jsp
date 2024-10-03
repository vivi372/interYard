<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Qna View</title>
<script type="text/javascript">
	$(function() {
		// 질문의 답변
		$(".aWriteBtn").click(function() {
			let no = $(this).data("data");
			console.log("no = " + no);
			location = "aWriteForm.do?qnaNo=" + no + "&${pageObject.pageQuery}";
		});

		// 질문 수정
		$(".updateBtn").click(function() {
			let no = $(this).data("data");
			let refNo = $(this).data("refno");
			console.log("no = " + no);
			location = "updateForm.do?qnaNo=" + no + "&refNo=" + refNo;
		});
		
		// 답변의 답변
		$(".answerBtn").click(function() {
			let ano = $(this).data("data");
			console.log("ano = " + ano);
			location = "aWriteForm.do?qnaNo=" + ano;
		});
		
		// 삭제 버튼 - 모달 이동
		$("#aDeleteBtn, #qDeleteBtn").click(function() {
			let qnaNo = $(this).data("data");
			console.log("deleteQnaNo = " + qnaNo);
			$("#qnaNoInput").val(qnaNo); // 모달 창의 히든 필드에 qnaNo 설정
			$("#qnaNoDisplay").text(qnaNo); // 모달 창의 표시 요소에 qnaNo 설정
		    $("#deleteModal").modal('show'); // 모달 창 열기
		});
		
		$("#lastDeleteBtn").click(function() {
			let qnaNo = $("#qnaNoInput").val();
			console.log("lastDeleteBtn No = " + qnaNo);
			location = "delete.do?qnaNo=" + qnaNo;
		})

		
	});
</script>
</head>
<body>
<br>
	<div class="container">
		<div>
			<div class="float-right">
				<button onclick="location.href='list.do?${pageObject.pageQuery}'" class="btns">
				<b>리스트</b>
				</button>
			</div>
			<h4>QnA 상세보기 <span> > ${qvo.ctg } > ${qvo.semiCtg }</span></h4>
			<div>
				<hr>
				<div class="p-5" style="background:#e6faff; border-radius:10px;">
					<div >
						<span class="float-right">${qvo.writeDate } <strong
							class="ml-3 mb-0"
						> ${qvo.hit }</strong></span>
						<h5>
							<span style="font-size:30px; color:#0088cc;">Q.</span><b class="ml-3">${qvo.qnaNo }. (${qvo.ctg}) (${qvo.semiCtg})
								${qvo.title }</b>
						</h5>
						<div>
							<div class="float-right">
								<c:if test="${login.gradeNo == 9 }">
									<button class="btn btn-light aWriteBtn" data-data="${qvo.qnaNo }">
										<b>답변하기</b>
									</button>
								</c:if>
								<c:if test="${ empty avo && login.id == qvo.id }">
									<button class="btn btn-light updateBtn" data-data="${qvo.qnaNo }" data-refno="${qvo.refNo }">
										<b>질문수정</b>
									</button>
								</c:if>
								<c:if test="${ login.id == qvo.id && empty avo || login.gradeNo == 9}">
									<button id="qDeleteBtn" type="button" class="btn btn-danger"
										data-toggle="modal" data-target="#myModal" data-data="${qvo.qnaNo }"
									>삭제</button>
								</c:if>
							</div>
							<small>${qvo.blindName} (<i>${qvo.blindId }</i>)
							</small>
						</div>
	
					</div>
				
					<hr>

					<pre style="height: 180px;">
${qvo.content }
					</pre>
				</div>
			</div>
			<div>
				<hr>
				<div >
					<c:if test="${ empty avo }">
						<strong>답변이 없습니다.<br> 답변을 등록해 주세요.
						</strong>
					</c:if>
					<c:if test="${ !empty avo }">
						<c:forEach items="${avo }" var="avo">
							<div class="p-5" style="background:#ffeee6; border-radius:10px;">
								<span class="float-right">${avo.writeDate } </span>
								<h5>
									<span style="font-size:30px; color:#ff1a1a;">A.</span><b class="ml-3">${avo.qnaNo }. (${avo.ctg})
										(${avo.semiCtg}) ${avo.title }</b>
								</h5>
								<div>
									<div class="float-right">
										<c:if test="${login.gradeNo == 9 }">
											<button class="btn btn-light updateBtn"
												data-data="${avo.qnaNo }" data-refno="${avo.refNo }"
											>
												<b>답변수정</b>
											</button>
										
											<button id="aDeleteBtn" type="button" class="btn btn-danger"
												data-toggle="modal" data-target="#myModal" data-data="${avo.qnaNo }"
											>삭제</button>
										</c:if>
									</div>
										<small>${avo.name} (<i>${avo.id }</i>)
										</small>
								</div>
								<hr>
								<div>
									<pre style="text-align: left;">
${avo.content }
									</pre>
								</div>
							</div>
							<p>
						</c:forEach>
					</c:if>
				</div>
				<!-- 이전글 다음글 -->
				<hr>
				<table class="table table-hover"
					style="text-align: center; border-radius: 10px; border-style: hidden;"
				>
					<c:forEach items="${rnumList }" var="rvo">
						<tr class="dataRow table-light">
							<th style="width: 10%">
								<c:if test="${param.rnum > rvo.rnum }">다음글
						</c:if>
								<c:if test="${param.rnum < rvo.rnum }">이전글
						</c:if>
							</th>
							<td class="no" style="width: 10%"><b>${rvo.qnaNo }</b></td>
							<td style="width: 40%; text-align: left;">${rvo.title }</td>
							<td style="width: 25%">${rvo.name }(${rvo.id })</td>
							<td style="width: 20%">${rvo.writeDate }</td>
							<td style="width: 5%" class="rnum" data-data="${rvo.rnum }">${rvo.hit }</td>
						</tr>
					</c:forEach>
				</table>
				<hr>
			</div>
		</div>

	</div>

<!-- The Modal -->
<div class="modal fade" id="myModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">글 삭제</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
    	  <input type="hidden" id="qnaNoInput" value="">
	      	<p>
		        <span id="qnaNoDisplay"></span> 번을 정말 삭제하시겠습니까? 
		        <br> 삭제하면 다시 복구할 수 없습니다. 
		        <br><br> <b>그래도 삭제하려면 Delete 버튼을 누르세요.</b>
	        <p>
        
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button id="lastDeleteBtn" type="button" class="btn btn-danger" >Delete</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>	

</body>
</html>