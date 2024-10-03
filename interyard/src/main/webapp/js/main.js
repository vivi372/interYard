$(function() {
	$(".dataRow").click(function() {
		let no = $(this).find("input[name='no']").val(); // Retrieve hidden input value
		console.log("no=" + no);

		
		// Determine which module was clicked
		if ($(this).hasClass("shop")) {
			location = "/event/view.do?no=" + no + "&inc=1";
			
		} else if ($(this).hasClass("book")) {
			location = "/event/view.do?no=" + no + "&inc=1";
			
		} else if ($(this).hasClass("ticket")) {
			location = "/event/view.do?no=" + no + "&inc=1";
			
		} else if ($(this).hasClass("notice")) {
			location = "/notice/view.do?no=" + no;
			
		} else if ($(this).hasClass("faq")) {
			location = "/qna/list.do";
			
		} else if ($(this).hasClass("goods")) {
			location = "/goods/view.do?goodsNo=" + no;
			
		} else if ($(this).hasClass("usedgoods")) {
			location = "/usedgoods/list.do";
		}
	});
});


$(function() {
	const carousel = $('#demo');
	const caption = $('.carousel-caption h3');

	const updateCaption = () => {
		const activeItem = $('.carousel-item.active');
		const newCaption = activeItem.attr('data-caption');
		caption.text(newCaption);
	};

	carousel.on('slid.bs.carousel', updateCaption);

	// Initial caption update
	updateCaption();
});
