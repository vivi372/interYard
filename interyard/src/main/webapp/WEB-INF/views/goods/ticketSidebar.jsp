<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.bd-sidebar {
	position: sticky;
	top: 4rem;
	z-index: 1000;
	height: calc(100vh - 4rem);
	background: #eee;
	border-right: 1px solid rgba(0, 0, 0, .1);
	max-width: 220px;
	display: flex;
	padding: 0;
	overflow-y: hidden;
	flex-direction: column;
}

.bd-sidebar-body {
	height: 100%;
	overflow-y: auto;
	display: block;
}

.bd-sidebar-body .nav {
	display: block;
}

.bd-sidebar-body .nav>a {
	display: block;
	padding: .20rem 1rem;
	font-size: 10%;
}

.card-body {
	padding: 0.3rem 1rem;
	font-size: 13px;
}

.collapse {
	margin: 0;
	padding: 0;
}

.bd-sidebar-footer {
	padding: 1rem;
	background: #ddd;
}
</style>
<div class="col-3 bd-sidebar">
	<div class="bd-sidebar-body">
		<ul class="nav">
			<div id="accordion">
				<div class="card">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse" href="#collapseOne"> 투어 </a>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3101">항공</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3102">해외숙소</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3103">해외패키지</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3104">투어.티켓</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3105">국내숙소</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3106">국내패키지</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3107">렌터카</a>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse" href="#collapseTwo"> 티켓 </a>
					</div>

					<!-- 여기에 카테고리 추가하셈 -->
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3201">뮤지컬</a>
						</div>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3202">콘서트</a>
						</div>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3203">스포츠</a>
						</div>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3204">전시/행사</a>
						</div>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3205">클래식/무용</a>
						</div>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3206">아동/가족</a>
						</div>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3207">연극</a>
						</div>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3208">레저/캠핑</a>
						</div>
					</div>
				</div>
			</div>
		</ul>
		<br>
	</div>
	<div class='bd-sidebar-footer'>Sidebar Footer</div>
</div>
