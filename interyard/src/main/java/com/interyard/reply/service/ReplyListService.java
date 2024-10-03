package com.interyard.reply.service;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.reply.dao.ReplyDAO;
import com.webjjang.util.page.ReplyPageObject;

public class ReplyListService implements Service {
	
	private ReplyDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		this.dao = (ReplyDAO) dao;
	}
	
	@Override
	public Object service (Object obj) throws Exception {
		
		ReplyPageObject pageObject = (ReplyPageObject) obj;
		
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		

		return dao.list(pageObject);
	}
}