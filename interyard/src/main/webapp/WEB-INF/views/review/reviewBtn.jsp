<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<script type="text/javascript">
$(function() {
	$(".writeBtn").click(function() {
		// 데이터 받아서 넘기기
	    let orderNo = $(this).closest(".orderNo").data("orderno");
	    let goodsNo = $(this).closest(".orderNo").data("goodsno");		
	    console.log(orderNo+" "+goodsNo);
		openNewWindow(orderNo,goodsNo);
	});
	
});
function openNewWindow(orderNo,goodsNo) {	
    // 팝업 url
    let url = "/review/reviewForm.do?orderNo=" + orderNo + "&goodsNo=" + goodsNo;
    // 팝업 이름
    let windowName = "리뷰쓰기";
    // 팝업 크기
    let windowFeatures = "width=600,height=850";
    // 팝업 특성
    window.open(url, windowName, windowFeatures);
}
</script>

<button class="btn btn-primary writeBtn form-control" id="writeBtn" type="button" style="height:50px; font-size:20px;">
<b>리뷰쓰기</b>
</button>
