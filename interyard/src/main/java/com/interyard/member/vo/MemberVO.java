package com.interyard.member.vo;

import lombok.Data;

@Data
public class MemberVO {

	private String id;
	private String pw;
	private String name;
	private String gender;
	private String birth;
	private String tel;
	private String email;
	private String writeDate;
	private String conDate;
	private String photo;
	private String status;
	private String gradeName;
	private int gradeNo;
	private Long newMsgCnt;
}
