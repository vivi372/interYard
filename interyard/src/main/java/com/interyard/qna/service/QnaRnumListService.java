package com.interyard.qna.service;

import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.qna.dao.QnaDAO;
import com.interyard.qna.vo.QnaVO;

public class QnaRnumListService implements Service {
	private QnaDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (QnaDAO) dao;
	}

	@Override
	public List<QnaVO> service(Object obj) throws Exception {
		
		Long rnum = (Long) obj;
		return dao.rnumList(rnum);
	}

}