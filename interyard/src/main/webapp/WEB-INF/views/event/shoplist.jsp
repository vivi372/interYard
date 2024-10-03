<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event</title>
<link rel="stylesheet" href="/css/eventList.css">

<script type="text/javascript">
	$(function() {
		$("#${pageObject.period}").prop('checked', true);
		
		$(".dataRow").click(function() {
			let no = $(this).find(".no").text();
			console.log("no = " + no);
			location = "view.do?no=" + no + "&inc=1&${pageObject.pageQuery}";
		});

		$("#${pageObject.period}").prop('checked', true);


		$("#perPageNum").change(function() {
			$("#searchForm").submit();
		});

		$("#key").val("${(empty pageObject.key)?'t':pageObject.key}");
		$("#perPageNum").val(
				"${(empty pageObject.perPageNum)?'10':pageObject.perPageNum}");

		$(".eventOption input[type='radio']").change(function() {
			location = "/event/shoplist.do?period=" + $(this).val();
		});
	});
</script>
</head>
<body>
	<!-- Header Section -->
	<div class="wrap">
		<h3>Event</h3>
		<br>
		<span>Simply dummy text of the printing and typesetting industry.<br>
			Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.
		</span>
	</div>

	<!-- Navigation Bar -->
	<nav class="navbar navbar-expand-sm bg-light navBar">
		<ul class="navbar-nav item">
			<li class="nav-item">
				<a class="nav-link link" href="/notice/list.do">Notice</a>
			</li>
			<li class="nav-item">
				<a class="nav-link link" href="list.do">Event</a>
			</li>
		</ul>
	</nav>

	<div class="container">
		<h1>Event list</h1>
		<a href="booklist.do" class="btns">Book Event</a>
		<a href="shoplist.do" class="btns">Shop Event</a>
		<a href="ticketlist.do" class="btns">Ticket Event</a>

		<c:if test="${!empty login && login.gradeNo == 9}">
			<a href="writeForm.do?perPageNum=${pageObject.perPageNum}" class="btn btn-dark">등록</a>
		</c:if>

		<c:if test="${!empty login && login.gradeNo == 9}">
			<form class="eventOption mb-3">
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" class="custom-control-input" id="pre" name="optionList" value="pre">
					<label class="custom-control-label" for="pre">Pre Event</label>
				</div>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" class="custom-control-input" id="old" name="optionList" value="old">
					<label class="custom-control-label" for="old">Old Event</label>
				</div>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" class="custom-control-input" id="res" name="optionList" value="res">
					<label class="custom-control-label" for="res">Res Event</label>
				</div>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" class="custom-control-input" id="all" name="optionList" value="all">
					<label class="custom-control-label" for="all">All Event</label>
				</div>
			</form>
		</c:if>

		<!-- 검색란의 시작 -->
		<form action="shoplist.do" id="searchForm">
			<input name="page" value="1" type="hidden">
			<div class="row">
				<div class="col-md-8">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<select name="key" id="key" class="form-control">
								<option value="t">Title</option>
								<option value="c">Content</option>
								<option value="tc">Title/Content</option>
							</select>
						</div>
						<input type="text" class="form-control" placeholder="검색" id="word" name="word" value="${pageObject.word}">
						<div class="input-group-append">
							<button class="btn btn-outline-dark">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="float-right" style="width: 200px;">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">Rows/Page</span>
							</div>
							<select id="perPageNum" name="perPageNum" class="form-control">
								<option>10</option>
								<option>15</option>
								<option>20</option>
								<option>25</option>
							</select>
						</div>
					</div>
				</div>
			</div>
		</form>

		<c:if test="${empty list}">
			<div class="jumbotron">
				<h4>Not exist datas</h4>
			</div>
		</c:if>
		
		<c:if test="${!empty list}">
			<div class="row">
				<!-- 이미지의 데이터가 있는 만큼 반복해서 표시하는 처리 시작 -->
				<c:forEach items="${list}" var="vo" varStatus="vs">
					<!-- 줄바꿈처리 - 찍는 인덱스 번호가 3의 배수이면 줄바꿈을 한다. -->
					<c:if test="${(vs.index != 0) && (vs.index % 3 == 0)}">
	  					${"</div>"}
	  					${"<div class='row'>"}
	  				</c:if>
					<!-- 데이터 표시 시작 -->
					<div class="col-md-4 dataRow">
						<div class="card" style="width: 100%">
							<div class="imageDiv text-center align-content-center">
								<img class="card-img-top" src="${vo.image}" alt="image">
							</div>
							<div class="card-body">
								<strong class="card-title">
									<span class="float-right">${vo.startDate} ${vo.endDate}</span>
									${vo.categoryNo}
								</strong>
									<div class="no">${vo.no}</div>
									<div class  = "title"> ${vo.title }</div>
									<div class  = "hit"> ${vo.hit }</div>
							</div>
						</div>
					</div>
					<!-- 데이터 표시 끝 -->
				</c:forEach>
				<!-- 이미지의 데이터가 있는 만큼 반복해서 표시하는 처리 끝 -->
			</div>

			<!-- 페이지 네이션 처리 -->
			<div>
				<pageNav:pageNav listURI="list.do" pageObject="${pageObject}" />
			</div>
		</c:if>
	</div>
</body>
</html>
