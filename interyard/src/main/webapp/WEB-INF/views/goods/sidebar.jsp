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
						<a class="card-link" data-toggle="collapse" href="#collapseOne"> 국내도서 </a>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=소설">소설</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion" style="margin-top: 1px">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=시.에세이">시.에세이</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=경제경영">경제경영</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=자기개발">자기개발</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=사회과학">사회과학</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=역사문화">역사문화</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=예술대중문화">예술대중문화</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=인문">인문</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=자연과학">자연과학</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=종교,역학">종교,역학</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=유아">유아</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=아동">아동</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=가정과생활">가정과생활</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=청소년">청소년</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=초등학습">초등학습</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=중등학습">중등학습</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=고등학습">고등학습</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=국어,외국어사전">국어,외국어사전</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=자격서수험서">자격서수험서</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=공무원수험서">공무원수험서</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=컴퓨터.인터넷">컴퓨터.인터넷</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=전공.대학교재">전공.대학교재</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=여행">여행</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=취미레저">취미레저</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=건강뷰티">건강뷰티</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=잡지">잡지</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=만화.라이트노벨">만화.라이트노벨</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/list.do?page=1&perPageNum=12&key=ct&word=유아동전집">유아동전집</a>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse" href="#collapseTwo"> 외국도서 </a>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<!-- 여기에 카테고리 추가하셈 -->
					</div>
				</div>
				<div class="card">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse" href="#collapseThird"> 잡화 </a>
					</div>
					<div id="collapseThird" class="collapse" data-parent="#accordion">
						<!-- 여기에 카테고리 추가하셈 -->
						<!-- 처음에 접혀있는 상태로 하려면 collapse 에 show 지우세열 -->
					</div>
				</div>
			</div>
		</ul>
		<br>
	</div>
	<div class='bd-sidebar-footer'>Sidebar Footer</div>
</div>
