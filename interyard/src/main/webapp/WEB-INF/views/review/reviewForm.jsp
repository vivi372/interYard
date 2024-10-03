<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰쓰기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<style type="text/css">
    .star {
        font-size: 2rem;
        cursor: pointer;
        color: #737373;
    }
    .checked {
        color: orange;
    }
    .popupClose:hover {
    	cursor:pointer;
    }
</style>
</head>
<body>
<div class="form-control bg-secondary text-white" style="height:50px; text-align:center;">
	<span class="float-right mt-0 popupClose" style="font-size:23px;" >&times;</span>
	<span style="font-size:20px; margin:0 auto;"><b>리뷰 쓰기</b></span>
</div>
    <div class="p-3">
        <!-- 주문한 상품 정보 -->
        <div class="p-3 goods" style="border: 1px dotted #444; border-radius: 10px;">
            <div class="media">
                <img src="${vo.goodsImage }" class="align-self-center mr-3" style="width: 60px">
                <div class="media-body">
                    <h5>${vo.goodsTitle }</h5>
                    ${vo.goodsSubTitle }
                    <br>
                    <c:if test="${vo.optName != '/' }">
                    <i>${vo.optName } / </i></c:if><i> 구매</i>
                </div>
            </div>
        </div>
        <p>
        <div class="p-3" style="border: 1px dotted #444; border-radius: 10px; text-align:center;">
            <div>
                <form action="write.do" method="post" id="reviewForm" enctype="multipart/form-data">
                    <input type="hidden" value="${param.orderNo }" id="orderNo" name="orderNo">
                    <input type="hidden" value="${param.goodsNo }" id="goodsNo" name="goodsNo">

                    <div class="mx-auto">
                        <h5 class="mb-2"><strong>전체 만족도</strong></h5>
                        <div id="star-rating" class="mt-2">
                            <i class="star fa fa-star-o" style="font-size:48px;" data-value="0.5"></i>
                            <i class="star fa fa-star-o" style="font-size:48px" data-value="1.5"></i>
                            <i class="star fa fa-star-o" style="font-size:48px" data-value="2.5"></i>
                            <i class="star fa fa-star-o" style="font-size:48px" data-value="3.5"></i>
                            <i class="star fa fa-star-o" style="font-size:48px" data-value="4.5"></i>
                        </div>
                        ( <b><span id="grade-display" style="color:red;">0</span></b> ) 점 <br>
                        <small>
                       	 	<i><b><span id="grade-text" style="color:red;"> 점수를 주세요!</span></b></i>
                        </small>
                        <input class="grade" type="hidden" id="grade-input" name="grade" value="0">
                    </div>
                    <hr>
                    <h5 class="mt-3"><label for="title"><strong>한줄평</strong></label></h5> 
                    <input class="form-control" id="title" name="title" required placeholder="한줄 평을 입력해 주세요. 100자 이내.">
                    <p>
                    <h5><label for="content"><strong>상세리뷰</strong></label></h5> 
                    <textarea class="form-control" id="content" name="content" required rows="5" placeholder="상세 리뷰를 남겨주세요. 1000자 이내."></textarea>
                    <p>
                    <h5><label for="title"><strong>이미지 첨부</strong></label></h5> 
                    <input class="form-control-file border" type="file" id="imgFile" name="imgFile">
                    <p>
                    <div class="btn-group btn-group-lg form-control p-0 mb-0" style="height:65px;">
	                    <button class="form-control btn btn-primary" style="height:50px;" 
	                    id="lastSubmitBtn" type="button"><strong>리뷰 등록</strong></button>
	                    <button class="form-control btn btn-secondary popupClose" style="height:50px;" 
	                    type="button"><strong>취소</strong></button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        $(".star").click(function(e) {
            let $this = $(this);
            let offset = $this.offset();
            let relativeX = e.pageX - offset.left;
            let halfWidth = $this.width() / 2;

            let rating = parseFloat($this.data("value"));
            if (relativeX > halfWidth) {
                rating += 0.5;
            }

            // Update displayed grade and hidden input
            $("#grade-display").text(rating);
            $("#grade-input").val(rating);
            if (rating == 0.5) {
            	$("#grade-text").text("매우 실망이에요.");
            } else if (rating == 1) {
            	$("#grade-text").text("실망이에요.");
            } else if (rating == 1.5) {
            	$("#grade-text").text("별로에요.");
            } else if (rating == 2) {
            	$("#grade-text").text("마음에 들지 않아요.");
            } else if (rating == 2.5) {
            	$("#grade-text").text("아쉬워요.");
            } else if (rating == 3) {
            	$("#grade-text").text("그저 그래요.");
            } else if (rating == 3.5) {
            	$("#grade-text").text("괜찮아요.");
            } else if (rating == 4) {
            	$("#grade-text").text("마음에 들어요.");
            } else if (rating == 4.5) {
            	$("#grade-text").text("정말 마음에 들어요.");
            } else if (rating == 5) {
            	$("#grade-text").text("적극 추천 해요!");
            };

            // Update star icons
            $(".star").removeClass("fa-star fa-star-half-full checked").addClass("fa-star-o");
            
            $(".star").each(function() {
            	let starValue = parseFloat($(this).data("value"));
                if (rating >= starValue + 0.5) {
                    $(this).removeClass("fa-star-o fa-star-half-full").addClass("fa-star checked");
                } else if (rating >= starValue) {
                    $(this).removeClass("fa-star-o").addClass("fa-star-half-full checked");
                }
            });
        });
        
    // grade 안넣을 때 체크
	$("#lastSubmitBtn").on("click", function() {
		let grade = $("#grade-input").val();
		if (grade == 0) {
			alert("평점(전체만족도)을 입력하셔야 합니다. 평점을 입력해 주세요.");
			return false;
		};
		
		// FormData 객체 생성
        let formData = new FormData($("#reviewForm")[0]); 
	
		$.ajax({
            url: "/review/write.do",
            type: "POST",
            enctype: "multipart/form-data",
            data: formData,
            processData: false,
            contentType: false,
            // 글쓰기 성공하면 팝업 닫고, 부모창을 링크로 이동
            success: function(result) {
                	window.close();
                    window.opener.location.href = "/order/list.do";
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("리뷰 등록 중 오류가 발생했습니다: " + textStatus);
            }
        });
	});

        
    $(".popupClose").click(function () {
    	window.close();
    });
    
});
    </script>
</body>
</html>