<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Main</title>

<script type="text/javascript" src="/js/main.js"></script>

<!-- Link to Google Fonts -->
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@400;700&display=swap"
	rel="stylesheet"
>

<style>
/* Global Styles */
body {
	margin: 0;
	font-family: 'Open Sans', sans-serif;
	color: #000; /* Set text color to black */
	line-height: 1.6;
	background-color: #fff; /* Set background to white */
}

/* Headings */
h1, h2, h3 {
	font-family: 'Roboto', sans-serif;
	font-weight: 600;
	text-align: center;
	margin: 0;
	color: #000; /* Set heading color to black */
}

h1 {
	font-size: 3rem;
	margin-bottom: 20px;
}

h2 {
	font-size: 2rem;
	margin-bottom: 15px;
}

h3 {
	font-size: 1.5rem;
}

/* Header Section */
.header {
	width: 100%;
	height: 60vh;
	background: linear-gradient(to bottom, rgba(0, 0, 0, 0.7),
		rgba(0, 0, 0, 0.5)),
		url('https://i.pinimg.com/originals/7f/0e/8c/7f0e8c154002bc26c703745c3ed88f78.jpg');
	background-size: cover;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	color: #fff; /* White text for contrast */
	text-align: center;
	padding: 40px;
	position: relative;
}

.header::after {
	content: '';
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.4); /* Dark overlay */
	z-index: 1;
}

.header h1 {
	font-size: 3.5rem;
	margin-bottom: 10px;
	text-shadow: 3px 3px 8px rgba(0, 0, 0, 0.6);
	z-index: 2;
}

.header span {
	font-size: 1.25rem;
	line-height: 1.8;
	text-shadow: 1px 1px 6px rgba(0, 0, 0, 0.4);
	z-index: 2;
}

/* Carousel Styles */
.carousel-container {
	padding: 30px 15px;
	text-align: center;
	position: relative;
}

.carousel-inner .carousel-item {
	width: 100%;
	background-color: #fff;
	border-radius: 12px;
	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
	padding: 20px;
	text-align: center;
	position: relative;
	transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
}

.carousel-inner .carousel-item:hover {
	transform: scale(1.05);
	box-shadow: 0 12px 24px rgba(0, 0, 0, 0.3);
}

.carousel-control-prev, .carousel-control-next {
	width: 50px;
	height: 50px;
	background-color: rgba(0, 0, 0, 0.7);
	/* Black background for controls */
	border-radius: 50%;
	display: flex;
	align-items: center;
	justify-content: center;
	color: #fff;
	font-size: 1.8rem;
	position: absolute;
	top: 50%;
	transform: translateY(-50%);
	cursor: pointer;
	transition: background-color 0.3s;
	z-index: 3;
}

.carousel-control-prev {
	left: 20px;
}

.carousel-control-next {
	right: 20px;
}

.carousel-control-prev:hover, .carousel-control-next:hover {
	background-color: rgba(0, 0, 0, 0.9);
}

.carousel-caption {
	position: absolute;
	bottom: 30px;
	left: 50%;
	transform: translateX(-50%);
	background-color: rgba(255, 255, 255, 0.8);
	padding: 20px;
	border-radius: 10px;
	color: #000; /* Black text */
	width: 80%;
	max-width: 450px;
	box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
	z-index: 2;
}

/* Card Styles */
.card {
	background: #fff;
	border-radius: 10px;
	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
	padding: 20px;
	margin: 15px;
	overflow: hidden;
	transition: box-shadow 0.3s ease-in-out, transform 0.3s ease-in-out;
}

.card:hover {
	box-shadow: 0 12px 24px rgba(0, 0, 0, 0.3);
	transform: translateY(-5px);
}

.card-title {
	font-size: 1.6rem;
	margin-bottom: 15px;
}

.card-content {
	font-size: 1rem;
	color: #000; /* Set card text color to black */
}

/* Row and Column Styles */
.row {
	display: flex;
	flex-wrap: wrap;
	margin: 0 -15px;
}

.col {
	padding: 15px;
	box-sizing: border-box;
}

.col-sm-4 {
	flex: 0 0 33.3333%;
	max-width: 33.3333%;
}

.col-sm-6 {
	flex: 0 0 50%;
	max-width: 50%;
}

.col-sm-8 {
	flex: 0 0 66.6667%;
	max-width: 66.6667%;
}

.wrap1 {
	padding: 30px;
	background: #fff; /* White background for content sections */
	margin: 0 auto;
	max-width: 1200px;
	border-radius: 10px;
	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
}

/* Custom Styles */
.card-container {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
}

.card-container .card {
	flex: 1;
	margin: 15px;
	min-width: 300px;
}

.notice-card {
	flex: 2;
	min-width: 500px;
	max-width: 100%;
	margin: 15px;
}

/* Responsive Design */
@media ( max-width : 768px) {
	.col-sm-4, .col-sm-6, .col-sm-8 {
		flex: 0 0 100%;
		max-width: 100%;
	}
	.carousel-control-prev, .carousel-control-next {
		width: 40px;
		height: 40px;
		font-size: 1.5rem;
	}
	.header h1 {
		font-size: 2.5rem;
	}
	.header span {
		font-size: 1rem;
	}
}
</style>

</head>
<body>
	<!-- Header Section -->
	<div class="header">
		<h1>InterYard</h1>
		<span>Explore our latest events, products, and updates. Stay
			tuned for more exciting opportunities and information.</span>
	</div>

	<!-- Event Section -->
	<div class="carousel-container section-title">
		<h2>Featured Events</h2>
		<div id="demo" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#demo" data-slide-to="0" class="active"></li>
				<li data-target="#demo" data-slide-to="1"></li>
				<li data-target="#demo" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner">
				<div class="carousel-item active book" data-caption="BookEvent">
					<jsp:include page="eventBookList.jsp" />
				</div>
				<div class="carousel-item shop" data-caption="ShopEvent">
					<jsp:include page="eventShopList.jsp" />
				</div>
				<div class="carousel-item ticket" data-caption="TicketEvent">
					<jsp:include page="eventTicketList.jsp" />
				</div>
			</div>
			<div class="carousel-caption">
				<h3>
					<strong>Event Highlights</strong>
				</h3>
			</div>
			<a class="carousel-control-prev" href="#demo" role="button"
				data-slide="prev"
			>
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="carousel-control-next" href="#demo" role="button"
				data-slide="next"
			>
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>

	<!-- Content Section -->
	<div class="wrap1">
		<div class="notice-card">
			<div class="card">
				<h2 class="card-title">Notices</h2>
				<jsp:include page="noticeList.jsp" />
			</div>
		</div>


		<div class="row card-container">
			<div class="col-sm-6 col">
				<div class="card">
					<h2 class="card-title">Products</h2>
					<jsp:include page="goodsShopList.jsp" />
				</div>
			</div>
			<div class="col-sm-6 col">
				<div class="card">
					<h2 class="card-title">Used Goods</h2>
					<jsp:include page="usedGoodsList.jsp" />
				</div>
			</div>
		</div>
		<div class="card FAQ">
			<h2 class="card-title">FAQ</h2>
			<jsp:include page="qnaList.jsp" />
		</div>
	</div>
</body>
</html>
