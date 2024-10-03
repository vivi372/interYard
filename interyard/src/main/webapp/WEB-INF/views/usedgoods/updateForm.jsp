<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품수정</title>
</head>
<body>
<div class="container">
    <h1>상품수정</h1>
    <form action="update.do" method="post" enctype="multipart/form-data">
        <input type="hidden" name="page" value="${param.page }">
        <input type="hidden" name="perPageNum" value="${param.perPageNum }">
        <input type="hidden" name="key" value="${param.key }">
        <input type="hidden" name="word" value="${param.word }">

        <div class="form-group">
            <label for="ugno">상품번호</label>
            <input id="ugno" name="ugno" class="form-control" value="${vo.ugno }" readonly>
        </div>

        <div class="form-group">
            <label for="title">상품명</label>
            <input id="title" name="title" required value="${vo.title }"
                class="form-control" maxlength="100"
                pattern="^[^ .].{2,99}$"
                title="맨앞에 공백문자 불가. 3~100자 입력"
                placeholder="제목 입력 : 3자 이상 100자 이내">
        </div>

        <div class="form-group">
            <label for="content">상품설명</label>
            <textarea class="form-control" id="content" name="content" required
            rows="7" placeholder="첫글자는 공백문자나 줄바꿈을 입력할 수 없습니다."
            >${vo.content }</textarea>
        </div>

         <div class="form-group">
            <!-- 가격 입력 필드 -->
            <label for="price">가격(숫자만 입력해주세요)</label>
            <input id="price" name="price" required"
                class="form-control" placeholder="가격을 입력하세요"
                type="text" 
                oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" value="${vo.price }">
        </div>

        <div class="form-group">
            <label for="ugstatus">상품상태</label>
            <select id="ugstatus" name="ugstatus" class="form-control">
                <option value="판매중" ${vo.ugstatus == '판매중' ? 'selected' : ''}>판매중</option>
                <option value="판매완료" ${vo.ugstatus == '판매완료' ? 'selected' : ''}>판매완료</option>
            </select>
        </div>

        <div class="form-group">
            <label for="imageFile">이미지 변경</label>
            <input id="imageFile" name="imageFile" class="form-control" type="file">
            <input type="hidden" name="deleteFileName" value="${vo.image}">
            <input type="hidden" name="originalImage" value="${vo.image}">
        </div>

        <button class="btns">수정</button>
        <button type="reset" class="btns">다시입력</button>
        <button type="button" onclick="history.back();" class="btns">취소</button>
    </form>
</div>
</body>
</html>
