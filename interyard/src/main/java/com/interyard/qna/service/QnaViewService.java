package com.interyard.qna.service;


import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.qna.dao.QnaDAO;
import com.interyard.qna.vo.QnaVO;

public class QnaViewService implements Service {

	QnaDAO dao = new QnaDAO();
	
	public void setDAO(DAO dao) {
		this.dao = (QnaDAO) dao;
	}

	@Override
	public QnaVO service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		// 넘어 오는 데이터
		Long[] objs = (Long[]) obj;
		Long no = objs[0];
		Long inc = objs[1];
		
		// inc == 1 일 때 조회수 증가 메소드 실행
		if(inc == 1) dao.increase(no);
		
		return dao.view(no);
	}

	
}
