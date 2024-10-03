<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 글등록</title>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css"
>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f9;
	margin: 0;
	padding: 0;
}

.container {
	width: 80%;
	max-width: 900px;
	margin: 2rem auto;
	padding: 2rem;
	background-color: #ffffff;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	color: #333;
}

.form-control {
	width: 100%;
	margin: 0.5rem 0;
	border: 1px solid #ddd;
	border-radius: 4px;
	box-sizing: border-box;
}

.form-control:focus {
	border-color: #007bff;
	outline: none;
	box-shadow: 0 0 0 0.2rem rgba(38, 143, 255, 0.25);
}

.form-group {
	margin-bottom: 1rem;
}

.form-group label {
	display: block;
	margin-bottom: 0.5rem;
	color: #333;
}

.btn {
	margin-bottom: 20px; /* 버튼 아래 여백 */
	background-color: #000; /* 배경색 검정 */
	color: #fff; /* 텍스트 색상 흰색 */
	border: none; /* 테두리 없음 */
	padding: 10px 20px; /* 위아래 10px, 좌우 20px 여백 */
	text-align: center; /* 텍스트 가운데 정렬 */
	text-decoration: none !important; /* 텍스트 밑줄 없음 (강제로 적용) */
	display: inline-block; /* 버튼을 인라인 블록으로 표시 */
	font-size: 16px; /* 글자 크기 */
	border-radius: 5px; /* 모서리 둥글게 */
	transition: background-color 0.3s, transform 0.2s;
	/* 배경색과 트랜스폼 애니메이션 */
}

.btn:hover {
	background-color: #555; /* 호버 시 배경색 변경 */
	transform: scale(1.05); /* 호버 시 크기 확대 */
	color: #fff;
}

.btn:active {
	background-color: #555; /* 클릭 시 배경색 변경 */
	transform: scale(0.95); /* 클릭 시 크기 축소 */
	color: #fff;
}

.datepicker {
	width: calc(100% - 1.5rem);
}
</style>
<script type="text/javascript">
	$(function() {
		let now = new Date();
		let startYear = now.getFullYear();
		let yearRange = startYear + ":" + (startYear + 10);
		$(".datepicker").datepicker(
				{
					dateFormat : "yy-mm-dd",
					changeMonth : true,
					changeYear : true,
					monthNamesShort : [ "1월", "2월", "3월", "4월", "5월", "6월",
							"7월", "8월", "9월", "10월", "11월", "12월" ],
					dayNamesMin : [ "일", "월", "화", "수", "목", "금", "토" ],
					yearRange : yearRange,
				});

		$("#startDate").datepicker(
				"option",
				{
					"minDate" : now,
					onClose : function(selectedDate) {
						if ($(this).val() != "")
							$("#endDate").datepicker("option", "minDate",
									selectedDate);
					}
				});

		$("#endDate").datepicker(
				"option",
				{
					"minDate" : now,
					onClose : function(selectedDate) {
						if ($(this).val() != "")
							$("#startDate").datepicker("option", "maxDate",
									selectedDate);
					}
				});
	});
</script>
</head>
<body>
	<div class="container">
		<h1>이벤트 작성</h1>
		<form action="write.do" method="post" enctype="multipart/form-data">
			<input name="perPageNum" value="${param.perPageNum}" type="hidden">
			<div class="form-group">
				<label for="categoryNo">카테고리</label> <select name="categoryNo"
					id="categoryNo" class="form-control"
				>
					<option value="1000">도서</option>
					<option value="2000">쇼핑</option>
					<option value="3000">티켓</option>
				</select>
			</div>
			<div class="form-group">
				<label for="title">제목</label>
				<input id="title" name="title" required class="form-control"
					maxlength="100" pattern="^[^ .].{2,99}$"
					title="맨앞에 공백문자 불가. 3~100자 입력" placeholder="제목 입력 : 3자 이상 100자 이내"
				>
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" id="content" name="content" required
					rows="7" placeholder="첫글자는 공백문자나 줄바꿈을 입력할 수 없습니다."
				></textarea>
			</div>
			<div class="form-group">
				<label for="imageFile">첨부 이미지</label>
				<input id="image" name="image" required class="form-control"
					type="file"
				>
			</div>
			<div class="form-group">
				<label for="startDate">게시일</label>
				<input id="startDate" name="startDate" required autocomplete="off"
					class="form-control datepicker"
				>
			</div>
			<div class="form-group">
				<label for="endDate">종료일</label>
				<input id="endDate" name="endDate" required autocomplete="off"
					class="form-control datepicker"
				>
			</div>
			<div class="form-group">
				<button type="submit" class="btn">등록</button>
				<button type="reset" class="btn">다시입력</button>
				<button type="button" onclick="history.back();" class="btn">취소</button>
			</div>
		</form>
	</div>
</body>
</html>
