package com.interyard.usedgoods.service;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.usedgoods.dao.UsedGoodsDAO;

public class UsedGoodsViewService implements Service {

	private UsedGoodsDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		return dao.view((Long) obj);
	}

	@Override
	public void setDAO(DAO dao) {
		this.dao = (UsedGoodsDAO) dao;
	}

}
