<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Notice Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style type="text/css">
/* CSS Styles */
/* Reset some default browser styles */
body, h3, span, a {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
}

/* Body Styles */
body {
	background-color: #f4f4f4;
	color: #333;
}

/* Wrapper for header */
.wrap {
	width: 100%;
	height: 300px;
	text-align: center;
	color: white;
	padding: 50px 20px;
	background-image: url(/image/noticeH.jpg);
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	position: relative;
}

.wrap h3 {
	font-size: 2.5em;
	margin-bottom: 20px;
}

.wrap span {
	font-size: 1.2em;
	line-height: 1.6;
}

/* Navigation Bar */
.navBar {
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	background-color: #ffffff;
}

.navBar .navbar-nav {
	justify-content: center;
}

.navBar .nav-link {
	color: #333;
	text-transform: uppercase;
	font-weight: bold;
	padding: 15px 20px;
	transition: 0.5s;
}

.navBar .nav-link:hover {
	color: #007bff;
	transform: scale(0.95);
	
}

/* Main Content */
.container {
	margin: 20px auto;
	padding: 20px;
	max-width: 1200px;
	background-color: #ffffff;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

.container h3 {
	font-size: 2em;
	margin-bottom: 20px;
}

.container .btn-dark {
	margin: 20px 0;
}

/* Notice List Styles */
.notice-list {
    margin-bottom: 20px;
}

.notice-list-header,
.notice-list-row {
    display: flex;
    align-items: center;
    border-bottom: 1px solid #ddd;
}

.notice-list-header {
    background-color: #242424;
    color: white;
    font-weight: bold;
}

.notice-list-item {
    flex: 1;
    padding: 12px;
    text-align: left;
}

.notice-list-row:nth-child(even) {
    background-color: #f9f9f9;
}

.notice-list-row {
    cursor: pointer;
}

.notice-list-details {
    padding: 15px;
}

.toggle-icon {
    font-size: 24px;
    cursor: pointer;
    transition: color 0.3s;
}

.notice-list-row:hover .toggle-icon {
    color: #007bff;
}

/* Responsive Design */
@media (max-width: 768px) {
    .notice-list-header,
    .notice-list-row {
        flex-direction: column;
    }

    .notice-list-item {
        text-align: center;
        padding: 10px 0;
    }
}


.card {
	margin: 10px 0;
}

.card-body {
	padding: 20px;
}

/* Form Styles */
.input-group {
	margin-bottom: 20px;
}

.form-control {
	border-radius: 4px;
	box-shadow: none;
}

.input-group-prepend select {
	border-radius: 4px 0 0 4px;
}

.input-group-append .btn {
	border-radius: 0 4px 4px 0;
}

.custom-control-label {
	font-size: 1em;
}

/* Responsive Design */
@media ( max-width : 768px) {
	.navBar .navbar-nav {
		flex-direction: column;
	}
	.navBar .nav-link {
		padding: 10px;
	}
	.wrap h3 {
		font-size: 2em;
	}
	.container {
		padding: 10px;
	}
}
</style>

<script type="text/javascript">
	$(function() {
		$('.toggle-icon').click(function(event) {
			event.stopPropagation();
			var target = $(this).data('target');
			$(target).collapse('toggle');
		});

		$("#${pageObject.period}").prop('checked', true);

		$(".notice-list-row").click(function() {
			let no = $(this).find(".no").text();
			console.log("no = " + no);
			location = "view.do?no=" + no;
		});

		$("#perPageNum").change(function() {
			$("#searchForm").submit();
		});

		$("#key").val("${(empty pageObject.key)?'t':pageObject.key}");
		$("#perPageNum").val(
				"${(empty pageObject.perPageNum)?'10':pageObject.perPageNum}");

		$(".noticeOption input[type='radio']").change(function() {
			location = "/notice/list.do?period=" + $(this).val();
		});
	});
</script>

</head>
<body>
	<!-- Header Section -->
	<div class="wrap">
		<h3>Notice</h3>
		<br> <span>Simply dummy text of the printing and
			typesetting industry.<br>Lorem Ipsum has been the industry's
			standard dummy text ever since the 1500s.
		</span>
	</div>

	<!-- Navigation Bar -->
	<nav class="navbar navbar-expand-sm bg-light navBar">
		<ul class="navbar-nav item">
	
			<li class="nav-item">
				<a class="nav-link link" href="list.do">Notice</a>
			</li>
			<li class="nav-item">
				<a class="nav-link link" href="/event/list.do">Event</a>
			</li>
		</ul>
	</nav>

	<!-- Main Content -->
	<div class="container">
		<h3>Notice</h3>
		<hr>
		<c:if test="${!empty login && login.gradeNo == 9 }">
			<tr>
				<td colspan="5">
					<!-- a tag : 데이터를 클릭하면 href의 정보를 가져와서 페이지 이동시킨다. --> <a
						href="writeForm.do?perPageNum=${pageObject.perPageNum }"
						class="btn btn-dark"
					>등록</a>
				</td>
			</tr>
		</c:if>
		<c:if test="${!empty login && login.gradeNo == 9}">
			<form class="noticeOption mb-3">
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" class="custom-control-input" id="pre"
						name="optionList" value="pre"
					>
					<label class="custom-control-label" for="pre">현재공지</label>
				</div>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" class="custom-control-input" id="old"
						name="optionList" value="old"
					>
					<label class="custom-control-label" for="old">이전공지</label>
				</div>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" class="custom-control-input" id="res"
						name="optionList" value="res"
					>
					<label class="custom-control-label" for="res">예정공지</label>
				</div>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" class="custom-control-input" id="all"
						name="optionList" value="all"
					>
					<label class="custom-control-label" for="all">모든공지</label>
				</div>
			</form>
		</c:if>

		<!-- Search Form -->
		<form action="list.jsp" id="searchForm">
			<div class="row">
				<div class="col-md-8">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<select name="key" id="key" class="form-control">
								<option value="n">no</option>
								<option value="t">title</option>
								<option value="c">content</option>
								<option value="tc">title/content</option>
							</select>
						</div>
						<input type="text" class="form-control" placeholder="Search"
							id="word" name="word"
						>
						<div class="input-group-append">
							<button class="btn btn-outline-primary" type="submit">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>

		<!-- Notice List Table -->
		<!-- Notice List Section -->
		<div class="notice-list">
			<div class="notice-list-header">
				<div class="notice-list-item">No</div>
				<div class="notice-list-item">Title</div>
				<div class="notice-list-item">Start Date</div>
				<div class="notice-list-item">End Date</div>
				<div class="notice-list-item"></div>
			</div>
			<c:forEach items="${list}" var="vo">
				<div class="notice-list-row">
					<div class="notice-list-item no">${vo.no}</div>
					<div class="notice-list-item">${vo.title}</div>
					<div class="notice-list-item">${vo.startDate}</div>
					<div class="notice-list-item">${vo.endDate}</div>
					<div class="notice-list-item">
						<i class="fa fa-angle-down toggle-icon"
							data-target="#collapse${vo.no}"
						></i>
					</div>
				</div>
				<div class="notice-list-details collapse" id="collapse${vo.no}">
					<div class="card card-body">
						<h4>${vo.title}</h4>
						<pre>${vo.content}</pre>
					</div>
				</div>
			</c:forEach>
		</div>

		<div class = "pageNav">
			<pageNav:pageNav listURI="list.do" pageObject="${pageObject }"></pageNav:pageNav>
		</div>

	</div>
</body>
</html>
