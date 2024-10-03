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
						<a class="card-link" data-toggle="collapse" href="#collapseOne"> 스포츠/레저 </a>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=등산/캠핑/낚시/여행">등산/캠핑/낚시/여행</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion" style="margin-top: 1px">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=자전거/인라인/오토바이">자전거/인라인/오토바이</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=헬스/다이어트/보충제">헬스/다이어트/보충제</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=구기/라켓/수영/스키">구기/라켓/수영/스키</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=골프클럽/의류/용품">골프클럽/의류/용품</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=스포츠의류/잡화">스포츠의류/잡화</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=스포츠/운동화">스포츠/운동화</a>
						</div>
					</div>
					<div id="collapseOne" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=블랙박스/내비/자동차용품">블랙박스/내비/자동차용품</a>
						</div>
					</div>

				</div>
				<div class="card">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse" href="#collapseTwo"> 패션/잡화 </a>
					</div>

					<!-- 여기에 카테고리 추가하셈 -->
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=캐주얼의류">캐주얼의류</a>
						</div>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=여성의류">여성의류</a>
						</div>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=남성의류">남성의류</a>
						</div>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=홈/언더웨어">홈/언더웨어</a>
						</div>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=슈즈">슈즈</a>
						</div>
					</div>
					<div id="collapseTwo" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=패션잡화">패션잡화</a>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse" href="#collapseThird"> 뷰티 </a>
					</div>
					<!-- 여기에 카테고리 추가하셈 -->
					<!-- 처음에 접혀있는 상태로 하려면 collapse 에 show 지우세열 -->
					<div id="collapseThird" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=스킨케어">스킨케어</a>
						</div>
					</div>
					<div id="collapseThird" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=메이크업">메이크업</a>
						</div>
					</div>
					<div id="collapseThird" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=헤어/바디/미용">헤어/바디/미용</a>
						</div>
					</div>
					<div id="collapseThird" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=클렌징">클렌징</a>
						</div>
					</div>
					<div id="collapseThird" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=향수">향수</a>
						</div>
					</div>
					<div id="collapseThird" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=헤어케어">헤어케어</a>
						</div>
					</div>
					<div id="collapseThird" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=바디케어">바디케어</a>
						</div>
					</div>
					<div id="collapseThird" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=핸드케어">핸드케어</a>
						</div>
					</div>
					<div id="collapseThird" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=미용기기/헤어기기">미용기기/헤어기기</a>
						</div>
					</div>
					<div id="collapseThird" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=남성화장품">남성화장품</a>
						</div>
					</div>
					<div id="collapseThird" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=선물세트">선물세트</a>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse" href="#collapseFour"> 식품 </a>
					</div>
					<!-- 여기에 카테고리 추가하셈 -->
					<!-- 처음에 접혀있는 상태로 하려면 collapse 에 show 지우세열 -->
					<div id="#collapseFour" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=가공식품/즉석/간식">가공식품/즉석/간식</a>
						</div>
					</div>
					<div id="#collapseFour" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=커피/음료">커피/음료</a>
						</div>
					</div>
					<div id="#collapseFour" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=건강식품/홍삼/다이어트">건강식품/홍삼/다이어트</a>
						</div>
					</div>
					<div id="#collapseFour" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=신선식품">신선식품</a>
						</div>
					</div>
					<div id="#collapseFour" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=간편식/밀키트/반찬">간편식/밀키트/반찬</a>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse" href="#collapseFive"> 유아동 </a>
					</div>
					<!-- 여기에 카테고리 추가하셈 -->
					<!-- 처음에 접혀있는 상태로 하려면 collapse 에 show 지우세열 -->
					<div id="#collapseFive" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=완구">완구</a>
						</div>
					</div>
					<div id="#collapseFive" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=기저귀/분유.물티슈.생리대">기저귀/분유.물티슈.생리대</a>
						</div>
					</div>
					<div id="#collapseFive" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=출산/임부/유아용품">출산/임부/유아용품</a>
						</div>
					</div>
					<div id="#collapseFive" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=유아동의류/잡화/임부복">유아동의류/잡화/임부복</a>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse" href="#collapseSix"> 디지털/가전 </a>
					</div>
					<div id="collapseSix" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=스마트폰/웨어러블">스마트폰/웨어러블</a>
						</div>
					</div>
					<div id="collapseSix" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=음향/스마트홈">음향/스마트홈</a>
						</div>
					</div>
					<div id="collapseSix" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=카메라/드론/VR/360">카메라/드론/VR/360</a>
						</div>
					</div>
					<div id="collapseSix" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=노트북/데스크탑/태블릿">노트북/데스크탑/태블릿</a>
						</div>
					</div>
					<div id="collapseSix" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=모니터/프린트/잉크">모니터/프린트/잉크</a>
						</div>
					</div>
					<div id="collapseSix" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=pc부품/주변기기">pc부품/주변기기</a>
						</div>
					</div>
					<div id="collapseSix" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=저장장치/메모리">저장장치/메모리</a>
						</div>
					</div>
					<div id="collapseSix" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=게임기/타이틀">게임기/타이틀</a>
						</div>
					</div>
					<div id="collapseSix" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=tv/세탁기/냉장고">tv/세탁기/냉장고</a>
						</div>
					</div>
					<div id="collapseSix" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=주방가전">주방가전</a>
						</div>
					</div>
					<div id="collapseSix" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=계절가전">계절가전</a>
						</div>
					</div>
					<div id="collapseSix" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=청소기/이미용/소형가전">청소기/이미용/소형가전</a>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse" href="#collapseSeven"> 가구/인테리어 </a>
					</div>
					<div id="collapseSeven" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=청소기/이미용/소형가전">침실가구/매트리스</a>
						</div>
					</div>
					<div id="collapseSeven" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=학생/사무/아동가구">학생/사무/아동가구</a>
						</div>
					</div>
					<div id="collapseSeven" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=거실/주방/수납가구">거실/주방/수납가구</a>
						</div>
					</div>
					<div id="collapseSeven" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=DIT/시공/원예/꽃배달">DIT/시공/원예/꽃배달</a>
						</div>
					</div>
					<div id="collapseSeven" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=홈인테리어/조명">홈인테리어/조명</a>
						</div>
					</div>
					<div id="collapseSeven" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=침구/커튼/카페트">침구/커튼/카페트</a>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse" href="#collapseEight"> 생활/건강/반려 </a>
					</div>
					<div id="collapseEight" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=주방/수입주방">주방/수입주방</a>
						</div>
					</div>
					<div id="collapseEight" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=생활/수납/청소/욕실">생활/수납/청소/욕실</a>
						</div>
					</div>
					<div id="collapseEight" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=세제/화장지/구강/면도">세제/화장지/구강/면도</a>
						</div>
					</div>
					<div id="collapseEight" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=건강/의료">건강/의료</a>
						</div>
					</div>
					<div id="collapseEight" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=반려동물/애완용품">반려동물/애완용품</a>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header">
						<a class="card-link" data-toggle="collapse" href="#collapseNine"> 공구/오피스/취미 </a>
					</div>
					<div id="collapseNine" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=공구/철물/산업/안전">공구/철물/산업/안전</a>
						</div>
					</div>
					<div id="collapseNine" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=문구/사무">문구/사무</a>
						</div>
					</div>
					<div id="collapseNine" class="collapse" data-parent="#accordion">
						<div class="card-body">
							<a href="/goods/shopList.do?page=1&perPageNum=12&key=ct&word=키덜트/악기/취미">키덜트/악기/취미</a>
						</div>
					</div>
				</div>
			</div>
		</ul>
		<br>
	</div>
	<div class='bd-sidebar-footer'>Sidebar Footer</div>
</div>
