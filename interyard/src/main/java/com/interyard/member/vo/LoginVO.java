package com.interyard.member.vo;

import lombok.Data;

@Data
public class LoginVO {

	private String id;
	private String pw;
	private String name;
	private String photo;
	private String gradeName;
	private int gradeNo;
	private Long newMsgCnt;
	private String status;
	
}
