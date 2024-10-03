package com.interyard.review.init;

import java.util.HashMap;
import java.util.Map;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.review.dao.ReviewDAO;
import com.interyard.review.service.ReviewDeleteService;
import com.interyard.review.service.ReviewGradeCntService;
import com.interyard.review.service.ReviewListService;
import com.interyard.review.service.ReviewOrderInfoService;
import com.interyard.review.service.ReviewTotalReviewService;
import com.interyard.review.service.ReviewWriteService;

public class ReviewInit {

	// Service 생성해서 저장하는 객체
	private static Map<String, Service> serviceMap = new HashMap<>();
	// DAO 생성해서 저장하는 객체
	private static Map<String, DAO> daoMap = new HashMap<>();
	
	static {
		// ---------------- QNA 게시판 객체 생성 및 조립 ---------------------------
		// dao 생성
		daoMap.put("ReviewDAO", new ReviewDAO());
		
		// service 생성
		serviceMap.put("/review/list.do", new ReviewListService());
		serviceMap.put("/review/getTotalReivew.do", new ReviewTotalReviewService());
		serviceMap.put("/review/gradeCnt.do", new ReviewGradeCntService());
		serviceMap.put("/review/reviewForm.do", new ReviewOrderInfoService());
		serviceMap.put("/review/write.do", new ReviewWriteService());
		serviceMap.put("/review/delete.do", new ReviewDeleteService());
		
		// 조립
		serviceMap.get("/review/list.do").setDAO(daoMap.get("ReviewDAO"));
		serviceMap.get("/review/getTotalReivew.do").setDAO(daoMap.get("ReviewDAO"));
		serviceMap.get("/review/gradeCnt.do").setDAO(daoMap.get("ReviewDAO"));
		serviceMap.get("/review/reviewForm.do").setDAO(daoMap.get("ReviewDAO"));
		serviceMap.get("/review/write.do").setDAO(daoMap.get("ReviewDAO"));
		serviceMap.get("/review/delete.do").setDAO(daoMap.get("ReviewDAO"));
		
		System.out.println("QnaInit.static 초기화 블록 ------------------------------- 객체 생성과 로딩");
		
	}
	
	public static Service get (String uri) {
		
		return serviceMap.get(uri);
	}
	
}
