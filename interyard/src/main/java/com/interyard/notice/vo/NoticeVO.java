package com.interyard.notice.vo;

import lombok.Data;

@Data
public class NoticeVO {
	private Long no;
	private String title;
	private String content;
	private String image;
	private String startDate;
	private String endDate;
	private String writeDate;
	private String updateDate;
	
}
