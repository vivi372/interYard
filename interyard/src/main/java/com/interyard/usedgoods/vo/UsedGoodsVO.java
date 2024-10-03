package com.interyard.usedgoods.vo;

import lombok.Data;

@Data
public class UsedGoodsVO {

	private Long ugno; //글번호
	private String title; //상품명
	private String content;	//상품설명
	private Long price;	//상품가격
	private String image; //상품이미지
	private String writeDate; //등록일
	private String id; //아이디
	private String ugstatus; //상태변경
}