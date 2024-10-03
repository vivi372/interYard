package com.interyard.basketopt.service;


import com.interyard.basketopt.dao.BasketOptDAO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

public class BasketOptListService implements Service {
	private BasketOptDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {		
		return dao.list((long) obj);
	}

	@Override
	public void setDAO(DAO dao) {
		this.dao = (BasketOptDAO) dao;

	}

}
