package com.interyard.review.service;


import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.review.dao.ReviewDAO;
import com.interyard.review.vo.ReviewVO;

public class ReviewOrderInfoService implements Service {

	ReviewDAO dao = new ReviewDAO();
	
	public void setDAO(DAO dao) {
		this.dao = (ReviewDAO) dao;
	}

	@Override
	public ReviewVO service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		// 넘어 오는 데이터
		Long[] objs = (Long[]) obj;
		Long orderNo = objs[0];
		Long goodsNo = objs[1];
		
		return dao.orderInfo(orderNo, goodsNo);
	}

	
}
