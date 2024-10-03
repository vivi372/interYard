/**
 * 
 */
//$(function(){
//	//모달 관련 이벤트
//	//배송지 변경 버튼 클릭시 이벤트
//	$("#dlvyBtn").click(function() {
//		console.log("클릭");
//		//$("#dlvyList").load("/dlvy/list.do");
//	});		
//});

//	$(document).on("click","#dlvyBtn",function() {
//		console.log("클릭");
//		$("#dlvySelModal").modal("show");
//		$("#dlvyList").load("/dlvy/list.do");
//	});
			
	//배송지 신규입력 버튼 클릭 이벤트
	$(document).on("click", "#dlvyWriteBtn",function() {
		$("#dlvyList").load("/dlvy/writeForm.do");
		
	});		
	//배송지 입력 폼에서 등록 버튼 클릭
	$(document).on("click","#dlvyWriteFormBtn" , function() {
		let formData = $("#dlvyWriteForm").serializeArray();
		let form = {};		
		$.each(formData, function() {
			form[this.name] = this.value;
		});	
		$("#dlvyList").load("/dlvy/write.do", form);
	});
	//배송지 수정 버튼 클릭 이벤트
	$(document).on("click", ".dlvyUpdateFormBtn",function() {
		let dlvyAddrNo = $(this).closest(".list-group-item").data("dlvyaddrno");
		$("#dlvyList").load("/dlvy/updateForm.do?dlvyAddrNo="+dlvyAddrNo);		
	});
	//배송지 수정 폼에서 수정 버튼 클릭
	$(document).on("click","#dlvyUpdateBtn" , function() {
		let formData = $("#dlvyUpdateForm").serializeArray();
		let form = {};		
		$.each(formData, function() {
			form[this.name] = this.value;
		});	
		$("#dlvyList").load("/dlvy/update.do", form);
	});
	$(document).on("click",".dlvyDeleteBtn", function() {		
		if($(this).closest(".list-group-item").find(".basicAddr").length == 1) {
			alert("다른 배송지를 기본 배송지로 변경하고 삭제해주세요.");
		} else {
			let dlvyAddrNo = $(this).closest(".list-group-item").data("dlvyaddrno");			
			$("#dlvyList").load("/dlvy/delete.do?dlvyAddrNo="+dlvyAddrNo);
		}
	});