<!-- 0730: 페이지 설정 및 JSTL 태그 라이브러리 설정 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고장터 리스트</title>

<style type="text/css">
/* 스타일 설정 */
.dataRow:hover {
    opacity: 70%;
    cursor: pointer;
}

.title {
    text-overflow: ellipsis;
    overflow: hidden;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
}

.product-list {
    display: flex;
    flex-wrap: wrap;
    gap: 1rem;
}

.product-item {
    border: 1px solid #ddd;
    border-radius: 5px;
    overflow: hidden;
    width: calc(33.333% - 1rem);
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.product-item img {
    width: 100%;
    height: 200px;
    object-fit: cover;
}

.product-item .content {
    padding: 1rem;
}

.product-item .content h5 {
    margin: 0 0 0.5rem;
    font-size: 1.25rem;
}

.product-item .content p {
    margin: 0;
    color: #777;
}

.imageDiv {
    width: 100%;
    text-align: center;
    overflow: hidden;
}

.imageDiv img {
    width: 100%;
    height: 200px;
    object-fit: cover;
}

.perPageNum {
    margin-left: 550px;
}
</style>    

<script type="text/javascript">
$(function() {
    // 제목 높이를 동적으로 맞추기 위해 모든 제목의 높이를 수집하고 최대 높이로 설정
    let titleHeights = [];
    $(".title").each(function(idx, title) {
        titleHeights.push($(title).height());
    });
    let maxTitleHeight = Math.max.apply(null, titleHeights);
    $(".title").height(maxTitleHeight);

    // 각 행을 클릭했을 때 해당 항목의 상세보기 페이지로 이동
    $(".dataRow").click(function() {
        let ugno = $(this).find(".ugno").text();    
        location = "view.do?ugno=" + ugno + "&${pageObject.pageQuery}&no=" + ugno;
    });

    // 페이지당 항목 수 변경 시 폼 제출
    $("#perPageNum").change(function() {
        $("#searchForm").submit();
    });

    // 검색 키워드 및 페이지당 항목 수 초기화
    $("#key").val("${(empty pageObject.key) ? 't' : pageObject.key}");
    $("#perPageNum").val("${(empty pageObject.perPageNum) ? '10' : pageObject.perPageNum}");
});
</script>
</head>
<body>
<div class="container">
    <br>
    <h2>중고장터 리스트</h2>
    <!-- 검색 폼 -->
    <form action="list.do" id="searchForm">
        <input name="page" value="1" type="hidden">
        <div class="row">
            <div class="col-md-5">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <!-- 검색 조건 선택 -->
                        <select name="key" id="key" class="form-control">
                            <option value="t">상품명</option>
                            <option value="c">내용</option>
                            <option value="tc">상품명/내용</option>
                        </select>
                    </div>
                    <!-- 검색 입력 -->
                    <input type="text" class="form-control" placeholder="검색" id="word" name="word" value="${pageObject.word}">
                    <div class="input-group-append">
                        <button class="btn btn-outline-dark">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </div>
            <div class="perPageNum">
                <c:if test="${!empty login}">
                    <!-- 로그인 상태에서만 등록 버튼 표시 -->
                    <a href="writeForm.do?perPageNum=${pageObject.perPageNum}" class="btns">등록</a>
                </c:if>
            </div>
        </div>
    </form>
    <!-- 데이터가 없을 경우 -->
    <c:if test="${empty list}">
        <div class="jumbotron">
            <h4>데이터가 존재하지 않습니다.</h4>
        </div>
    </c:if>
    <!-- 데이터가 있을 경우 -->
    <c:if test="${!empty list}">
        <div class="product-list">
            <!-- 목록을 반복하여 각 항목을 표시 -->
            <c:forEach items="${list}" var="vo" varStatus="vs">
                <div class="product-item dataRow">
                    <div class="card">
                        <div class="imageDiv text-center align-content-center">
                            <!-- 상품 이미지 표시 -->
                            <img class="card-img-top" src="${pageContext.request.contextPath}${vo.image}" alt="image">
                        </div>
                        <div class="card-body">
                            <strong class="card-title">
                                <span class="float-right">${vo.writeDate}</span>
                                <span class="ugno">${vo.ugno}</span>.
                                ${vo.title}
                            </strong>
                            <p class="card-text title">
                                <h5><fmt:formatNumber value="${vo.price}" pattern="#,###"/>원</h5> 
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <br>
        <br>
         <div class="mt-2">
            <pageNav:pageNav listURI="list.do" pageObject="${pageObject}" />
        </div>
    </c:if>
</div>
</body>
</html>
