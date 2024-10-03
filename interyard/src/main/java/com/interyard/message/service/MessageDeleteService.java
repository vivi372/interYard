package com.interyard.message.service;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.message.dao.MessageDAO;
import com.interyard.message.vo.MessageVO;

public class MessageDeleteService implements Service {
	
	private MessageDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MessageDAO) dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		MessageVO delete = (MessageVO) obj;
		return dao.delete(delete);
	}

}
