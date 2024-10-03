package com.interyard.notice.service;

import com.interyard.notice.dao.NoticeDAO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

public class NoticeDeleteService implements Service {
	private NoticeDAO dao;

	@Override
	public Integer service(Object obj) throws Exception {
		// DB 처리는 DAO에서 처리 - NoticeDAO.delete()
		// NoticeController - (Execute) - [NoticeListService] - NoticeDAO.delete()
		return dao.delete((Long)obj);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao=(NoticeDAO) dao;
	}

}
