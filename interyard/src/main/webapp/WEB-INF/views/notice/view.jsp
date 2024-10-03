<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice View Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 부트스트랩 CSS -->



<script type="text/javascript">
	$(function() { // 문서가 준비되면 실행

		// 메인 삭제 버튼 클릭 시
		$("#deleteBtn").click(function() {
			$("#pw").val(""); // 비밀번호 입력 필드 초기화
		});

		// 삭제 모달의 삭제 버튼 클릭 시
		$("#deleteForm").submit(function() {
			console.log("deleteForm submit Event - -----------------");
			// 필수 입력 체크
			if (isEmpty("#pw", "비밀번호", 0))
				return false;
			// 길이 체크
			if (lengthCheck("#pw", "비밀번호", 3, 20, 0))
				return false;
		});

		// 삭제 모달의 취소 버튼 클릭 시
		$("#deleteCancelBtn").click(function() {
			console.log("deleteForm 취소 버튼 click Event - -----------------");
			$("#pw").val(""); // 비밀번호 입력 필드 초기화
			$("#deleteModal").modal("hide"); // 삭제 모달 숨기기
			$("#deleteBtn").attr("disabled", false); // 삭제 버튼 활성화
		});
	});
</script>

<style type="text/css">
/* 전체 페이지 스타일 */
body {
	font-family: 'Helvetica Neue', Arial, sans-serif;
	color: #333;
	background-color: #f4f7fa;
	margin: 0;
}

/* 상단 wrap */
.wrap {
	position: relative;
	width: 100%;
	text-align: center;
	color: white;
	padding-top: 50px;
	min-height: 25vh;
	background-image: url('/image/noticeH.jpg');
	background-size: cover;
	background-position: center;
	background-blend-mode: overlay;
}

/* 상단 아이콘 */
.wrap>.icon {
	position: absolute;
	top: 10px;
	left: 10px;
	color: white;
	font-size: 24px;
	cursor: pointer;
}

/* 제목 스타일 */
h3 {
	margin-top: 20px;
	font-size: 2em;
	font-weight: bold;
}

/* 컨테이너 스타일 */
.container {
	margin-top: 20px;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
}

/* 공지사항 제목 */
.notice-header {
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	gap: 10px;
}

.notice-header .title-and-dates {
	display: flex;
	align-items: center;
	gap: 20px;
}

.notice-header h2 {
	font-size: 1.75em;
	margin: 0;
	color: #2c3e50;
}

/* 날짜 스타일 */
.notice-header b {
	font-size: 0.9em;
	color: #95a5a6;
}

/* 구분선 스타일 */
.hrtag {
	width: 50%;
	margin: 20px auto;
	border: 1px solid #ecf0f1;
}

/* 공지사항 내용 스타일 */
.notice-content {
	margin-bottom: 20px;
	font-size: 1em;
	line-height: 1.6;
	color: #34495e;
	text-align: center;
}

/* 이미지 스타일 */
.notice-content img {
	max-width: 100%;
	height: auto;
	margin-top: 20px;
	border-radius: 10px;
}

/* 버튼 스타일 */
.btn {
	margin: 5px;
	padding: 10px 20px;
	font-size: 1em;
	border-radius: 5px;
	transition: background-color 0.3s ease;
}


/* 삭제 모달 스타일 */
.modal-content {
	border-radius: 10px;
	padding: 20px;
}

.modal-header {
	border-bottom: none;
}

.modal-title {
	font-size: 1.25em;
}

.close {
	font-size: 1.5em;
}

.modal-body {
	padding: 20px;
}

#pw {
	width: 100%;
	padding: 10px;
	font-size: 1em;
	border: 1px solid #ced4da;
	border-radius: 5px;
}

.modal-footer {
	border-top: none;
	padding: 10px;
}

.btn-danger {
	background-color: #e74c3c;
	border: none;
	color: #fff;
}

.btn-danger:hover {
	background-color: #c0392b;
}

.btn-dark {
	background-color: #242424;
	border: none;
	color: #fff;
}

.btn-dark:hover {
	background-color: #000;
}
</style>
</head>
<body>
	<!-- 상단 wrap -->
	<div class="wrap">
		<i class="fas fa-chevron-left icon" onclick="history.back();"></i>
		<h3>Notice View</h3>
	</div>
	<div class="container">
		<div class="notice-header">
			<div class="title-and-dates">
				<h2 class="notice-title">${vo.title}</h2>
				<b>${vo.writeDate}</b> <b>${vo.updateDate}</b>
			</div>
		</div>
		<hr class="hrtag">
		<br>
		<br>
		<div class="notice-content">
			<span>${vo.content}</span> <br>
			<br><img src="${vo.image}" alt="image">
		</div>
		<hr class="hrtag">
		<br>
		<br>
		<div class="text-center">
			<c:if test="${!empty login && login.gradeNo == 9}">
			<a
				href="updateForm.do?no=${param.no}&page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
				class="btn btn-dark"
			>수정</a>
			<a
				href="delete.do?no=${param.no}&page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
				class="btn btn-dark"
			>삭제</a>
			
			</c:if>
			
			<a
				href="list.do?page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
				class="btn btn-dark"
			>리스트</a>
		</div>


		<br>

		<!-- 삭제 모달 -->
		<div class="modal fade" id="deleteModal">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<!-- 모달 헤더 -->
					<div class="modal-header">
						<h4 class="modal-title">Enter Password</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<!-- 모달 본문 -->
					<div class="modal-body">
						<form action="delete.jsp" method="post" id="deleteForm">
							<input type="hidden" name="no" value="1">
							<!-- 삭제할 공지 번호 -->
							<input name="pw" maxlength="20" pattern="^.{3,20}$" id="pw"
								title="3~20자 입력 가능" type="password"
								placeholder="password confirm"
							>
						</form>
					</div>

					<!--  Modal footer -->
					<div class="modal-footer">
						<button class="btn btn-danger">delete</button>
						<button type="button" class="btn btn-dark" id="deleteCancelBtn">cancel</button>
					</div>

				</div>
			</div>
		</div>

	</div>
</body>
</html>
