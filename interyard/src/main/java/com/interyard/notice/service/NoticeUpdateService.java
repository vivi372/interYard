package com.interyard.notice.service;

import com.interyard.notice.dao.NoticeDAO;
import com.interyard.notice.vo.NoticeVO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

public class NoticeUpdateService implements Service {
	private NoticeDAO dao;

	@Override
	public Integer service(Object obj) throws Exception {
		// DB notice에서 수정 쿼리 실행해서 데이터 처리
		// NoticeController - (Execute) - [NoticeListService] - NoticeDAO.update()
		return dao.update((NoticeVO)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao=(NoticeDAO) dao;
	}

}
