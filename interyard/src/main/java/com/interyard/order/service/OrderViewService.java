package com.interyard.order.service;



import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.order.dao.OrderDAO;
import com.interyard.order.list.OrderList;
import com.interyard.order.vo.OrderVO;

public class OrderViewService implements Service {
	private OrderDAO dao;
	
	@Override
	public OrderList service(Object obj) throws Exception {
		
		return dao.view((OrderVO)obj);
	}

	@Override
	public void setDAO(DAO dao) {		
		this.dao = (OrderDAO) dao;
	}

}
