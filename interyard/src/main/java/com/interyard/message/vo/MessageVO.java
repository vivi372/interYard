package com.interyard.message.vo;

import lombok.Data;

@Data
public class MessageVO {

	private Long msgNo;
	private String title;
	private String content;
	private String senderId;
	private String senderName; // by. member
	private String senderPhoto; // by. member
	private String sendDate;
	private String accepterId;
	private String accepterName; // by. member
	private String accepterPhoto; // by. member
	private String acceptDate;
	private String uploadFile;
	private Long refNo; // message
	private Long ordNo;
	private Long levNo;
	private Long parentNo; // message
	private boolean isReply;
	
}
