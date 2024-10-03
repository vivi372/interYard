package com.interyard.order.service;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.order.dao.OrderDAO;

public class OrderDeleteService implements Service {
	private OrderDAO dao;
	
	@Override
	public Integer service(Object obj) throws Exception {
				
		return dao.delete( (long[]) obj);
	}

	@Override
	public void setDAO(DAO dao) {		
		this.dao = (OrderDAO) dao;
	}

}
