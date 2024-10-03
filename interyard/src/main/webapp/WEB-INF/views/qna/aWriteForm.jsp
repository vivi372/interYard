<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변하기</title>
</head>
<body>
<br>
	<div class="container">
		<button type="button" class="btns float-right" onclick="history.back()"><b>돌아가기</b></button>
		<H4>답변하기</H4>
		<hr>
		<div class="bg-light p-5" style="border:3px solid #0084ff; border-radius:10px;">
			<div>
				<span class="float-right">${qvo.writeDate } <strong
					class="ml-3"
				> ${qvo.hit }</strong></span>
				<h5>
					<span style="font-size:30px; color:#0088cc;">Q.</span>
					<b class="ml-3">${qvo.qnaNo }. [${qvo.ctg}] [${qvo.semiCtg}] ${qvo.title }</b>
				</h5>
				<div>
					<small>${qvo.blindName} (<i>${qvo.blindId }</i>)
					</small>
				</div>

			</div>
			<hr>

			<pre style="height: 200px;">
${qvo.content }
			</pre>
		</div>
		<hr>
		<form action="aWrite.do?" method="get">
			<input type="hidden" value="${qvo.qnaNo }" id="qnaNo" name="qnaNo">
			<input type="hidden" value="${qvo.refNo }" id="refNo" name="refNo">
			<input type="hidden" value="${qvo.ordNo }" id="ordNo" name="ordNo">
			<input type="hidden" value="${(empty qvo)?0:qvo.levNo +1}" id="levNo" name="levNo">
			<input type="hidden" value="${qvo.qnaNo }" id="parentNo" name="parentNo">
			<input type="hidden" value="${param.rnum }" id="rnum" name="rnum">
			<input type="hidden" value="${param.perPageNum }" id="perPageNum" name="perPageNum">
			
			<div class="bg-light p-5 mt-2 mb-2" style="border:3px solid #ff9933; border-radius:10px;">
				<span style="font-size:30px; color:#ff1a1a;">A.</span><span style="font-size:20px;"> 답변하기</span>
			
				<p>
				<div class="form-inline mb-3">
					<div class="form-group">
						<label for="ctg"><i class="fa fa-angle-right"></i><span class="ml-1"><b>질문 유형</b></span></label> 
						<input class="form-control ml-2 mr-5" id="ctg" name="ctg" style="width:300px;" value="${qvo.ctg }" readonly>
					</div>
					<div class="form-group">
						<label for="semiCtg"><i class="fa fa-angle-right"></i> <span class="ml-1"><b>분류</b></span></label> 
						<input class="form-control ml-2 mr-5" id="semiCtg" name="semiCtg" style="width:300px;" value="${qvo.semiCtg }" readonly>
					</div>
				</div>
				<div class="form-group">
					<label for="title"><i class="fa fa-angle-right"></i> <b>제목</b></label>
					<input id="title" class="form-control" name="title" value="${'[답변] ' += qvo.title }">
					<br>
					<label for="content"><i class="fa fa-angle-right"></i> <b>내용</b></label>
					<textarea id="content" class="form-control" rows="10" name="content">



------------------------ 질문 글 ---------------------------
> 작성자 : ${qvo.blindName }(${qvo.blindId })      > 작성일 : ${qvo.writeDate}
-----------------------------------------------------------
${qvo.content }
</textarea>
				</div>
			</div>
			<p class="mt-3">
			<button class="btns"><b>등록</b></button>
			<button type="reset" class="btns"><b>초기화</b></button>
			<button type="button" class="btn btn-light" onclick="history.back()"><b>돌아가기</b></button>
		</form>

	</div>

</body>
</html>