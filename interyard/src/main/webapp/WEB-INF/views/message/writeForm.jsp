<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 쓰기</title>

<style type="text/css">

.container {
	margin-top: 30px;
}

.row {
	padding: 6px 12px;
}

</style>

</head>
<body>

<div class="container">

<h3 class="text-center">chat</h3>
<hr>

<form action="write.do" method="post" enctype="multipart/form-data">
<input name="perPageNum" value="${param.perPageNum }" type="hidden">
<input name="refNo" value="${vo.refNo }" type="hidden">
<input name="ordNo" value="${(empty vo)?1:vo.ordNo+1 }" type="hidden">
<input name="levNo" value="${(empty vo)?0:vo.levNo+0 }" type="hidden">
<input name="parentNo" value="${vo.msgNo }" type="hidden">
<div class="row form-group">
	<div class="col-md-2">
		<label for="accepterId">받는 사람</label> <!-- senderId : 본인 -->
	</div>
	<div class="col-md-10">
		<input class="form-control" placeholder="받는 사람 아이디 입력" 
			id="accepterId" name="accepterId" value="${param.id }" readonly> <!-- usedgoods.id -->
	</div>
</div>
<div class="row form-group">
	<div class="col-md-2">
		<label for="title">제목</label>
	</div>
	<div class="col-md-10">
		<input class="form-control" placeholder="제목 입력" 
			id="title" name="title" value="${param.title }" readonly> <!-- usedgoods.title -->
	</div>
</div>
<div class="row form-group">
	<div class="col-md-2">
		<label for="content">내용</label>
	</div>
	<div class="col-md-10">
		<textarea class="form-control" rows="7" placeholder="내용 입력, 최대 300 자" 
			id="content" name="content" maxlength="300" required></textarea>
	</div>
</div>
<div class="row form-group">
	<div class="col-md-2">
		<label for="uploadFile">첨부 파일</label>
	</div>
	<div class="col-md-10">
		<input type="file" class="form-control" id="uploadFile" name="uploadFile" 
			style="border-style: none; background: none;">
	</div>
</div>
<hr>
<!-- 버튼 -->
<button class="btns">전송</button>
<button type="button" class="btns" onclick="history.back()">취소</button>

</form>

</div>

</body>
</html>