package com.interyard.notice.service;

import com.interyard.notice.dao.NoticeDAO;
import com.interyard.notice.vo.NoticeVO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

public class NoticeWriteService implements Service {

	private NoticeDAO dao;
	@Override
	public Integer service(Object obj) throws Exception {
		// DB notice에서 글등록 쿼리 실행해서 데이터 저장
		// DB 처리는 DAO에서 처리 - NoticeDAO.write()
		// NoticeController - (Execute) - [NoticeListService] - NoticeDAO.write()
		return dao.write((NoticeVO)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao=(NoticeDAO)dao;
	}

}
