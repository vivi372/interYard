package com.interyard.qna.service;

import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.qna.dao.QnaDAO;
import com.interyard.qna.vo.PageObjectQna;
import com.interyard.qna.vo.QnaVO;

public class QnaListService implements Service {

	QnaDAO dao = new QnaDAO();
	
	public void setDAO(DAO dao) {
		this.dao = (QnaDAO) dao;
	}

	@Override
	public List<QnaVO> service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		PageObjectQna pageObject = (PageObjectQna) obj;
		
		// DB board에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴
		
		// 전체 데이터의 개수 - jsp의 페이지 네이션과 데이터가져오기 개수 세팅
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		
		// DB 처리는 DAO에서 처리 - BoardDAO.list()
		// QnaController - (Execute) - [QnaListService] - QnaDAO.list()
		return dao.list(pageObject);	
	}

	
}
