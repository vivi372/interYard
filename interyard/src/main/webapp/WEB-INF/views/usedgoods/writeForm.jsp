<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
<!-- 부트스트랩과 같은 CSS 라이브러리를 사용할 경우 여기에 링크 추가 가능 -->
<style type="text/css">
/* 버튼 스타일 */
.btns {
    margin: 10px; /* 버튼 주위에 여백 */
    background-color: #000; /* 배경색 검정 */
    color: #fff; /* 텍스트 색상 흰색 */
    border: none; /* 테두리 없음 */
    padding: 10px 20px; /* 위아래 10px, 좌우 20px 여백 */
    text-align: center; /* 텍스트 가운데 정렬 */
    text-decoration: none !important; /* 텍스트 밑줄 없음 (강제로 적용) */
    display: inline-block; /* 버튼을 인라인 블록으로 표시 */
    font-size: 16px; /* 글자 크기 */
    border-radius: 5px; /* 모서리 둥글게 */
    transition: background-color 0.3s, transform 0.2s; /* 배경색과 트랜스폼 애니메이션 */
}

.btns:hover {
    background-color: #333; /* 호버 시 배경색 변경 */
    transform: scale(1.05); /* 호버 시 크기 확대 */
}

.btns:active {
    background-color: #555; /* 클릭 시 배경색 변경 */
    transform: scale(0.95); /* 클릭 시 크기 축소 */
}
</style>
</head>
<body>
<div class="container">
    <h2>상품등록</h2>
    <!-- 상품 등록 폼 -->
    <form action="write.do" method="post" enctype="multipart/form-data">
        <!-- 페이지 네이션과 관련된 파라미터를 숨겨진 필드로 전달 -->
        <input name="perPageNum" value="${param.perPageNum}" type="hidden">
         
        <div class="form-group">
            <!-- 상품명 입력 필드 -->
            <label for="title">상품명</label>
            <input id="title" name="title" required 
                class="form-control" maxlength="100"
                pattern="^[^ .].{2,99}$"
                title="상품명을 입력해주세요."
                placeholder="상품명을 입력해주세요.">
        </div>
          
        <div class="form-group">
            <!-- 상품내용 입력 필드 -->
            <label for="content">상품내용</label>
            <textarea class="form-control" id="content" name="content" required
                rows="7" placeholder="상품내용을 입력해주세요."></textarea>
        </div>
          
        <div class="form-group">
            <!-- 가격 입력 필드 -->
            <label for="price">가격(숫자만 입력해주세요)</label>
            <input id="price" name="price" required 
                class="form-control" placeholder="가격을 입력하세요"
                type="text" 
                oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
        </div>

        <div class="form-group">
            <!-- 이미지 파일 첨부 필드 -->
            <label for="imageFile">첨부 이미지</label>
            <input id="imageFile" name="imageFile" required 
                class="form-control" type="file">
        </div>
          
        <!-- 등록 버튼 -->
        <button class="btns">등록</button>
        <!-- 다시 입력 버튼 (폼 리셋) -->
        <button type="reset" class="btns">다시입력</button>
        <!-- 취소 버튼 (이전 페이지로 이동) -->
        <button type="button" onclick="history.back();" class="btns">취소</button>
    </form>
</div>
</body>
</html>
