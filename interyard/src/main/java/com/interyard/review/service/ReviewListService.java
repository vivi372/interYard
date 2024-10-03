package com.interyard.review.service;

import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.review.dao.ReviewDAO;
import com.interyard.review.vo.ReviewVO;

public class ReviewListService implements Service {

	ReviewDAO dao = new ReviewDAO();
	
	public void setDAO(DAO dao) {
		this.dao = (ReviewDAO) dao;
	}

	@Override
	public List<ReviewVO> service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		// DB board에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴
		
		
		// DB 처리는 DAO에서 처리 - BoardDAO.list()
		// QnaController - (Execute) - [QnaListService] - QnaDAO.list()
		return dao.list((Long) obj);	
	}

	
}
