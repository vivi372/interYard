package com.interyard.qna.vo;

import lombok.Data;

@Data
public class QnaVO {
	
	private Long qnaNo;
	private String ctg;
	private String semiCtg;
	private String title;
	private String content;
	private String id;
	private String blindId;
	private String name;
	private String blindName;
	private String writeDate;
	private Long hit;
	private Long refNo;
	private Long ordNo;
	private Long levNo;
	private Long parentNo;
	private Long answerCnt;
	private Long rnum;

}
