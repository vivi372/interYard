package com.interyard.qna.service;


import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.qna.dao.QnaDAO;

public class QnaDeleteService implements Service {

	QnaDAO dao = new QnaDAO();
	
	public void setDAO(DAO dao) {
		this.dao = (QnaDAO) dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		Long no = (Long) obj;
		
		
		return dao.delete(no);
	}

	
}
