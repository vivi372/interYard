package com.interyard.reply.service;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.reply.dao.ReplyDAO;
import com.interyard.reply.vo.ReplyVO;

public class ReplyDeleteService implements Service {
	
	private ReplyDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		this.dao = (ReplyDAO) dao;
	}
	
	@Override
	public Integer service(Object obj) throws Exception {
		return dao.delete((ReplyVO)obj);
	}
} 