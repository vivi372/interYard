package com.interyard.review.service;


import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.review.dao.ReviewDAO;
import com.interyard.review.vo.ReviewVO;

public class ReviewTotalReviewService implements Service {

	ReviewDAO dao = new ReviewDAO();
	
	public void setDAO(DAO dao) {
		this.dao = (ReviewDAO) dao;
	}

	@Override
	public ReviewVO service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		
		return dao.getTotalRow((Long) obj);
	}

	
}
