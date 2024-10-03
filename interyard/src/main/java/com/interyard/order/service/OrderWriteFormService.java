package com.interyard.order.service;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.order.dao.OrderDAO;
import com.interyard.order.list.OrderList;

/**
 * 주문 쓰기 폼 처리를 진행하는 메서드를 가지고 있는 클래스
 */
public class OrderWriteFormService implements Service {
	private OrderDAO dao;
	
	/**
	 * 주문 쓰기 폼 처리를 진행하는 메서드
	 */
	@Override
	public OrderList service(Object obj) throws Exception {		
		//db에서 해당 상품 번호의 이미지, 이름 가져오기
		return dao.writeForm((String[]) obj);
	}

	@Override
	public void setDAO(DAO dao) {		
		this.dao = (OrderDAO) dao;
	}

}
