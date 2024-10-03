package com.interyard.order.service;


import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.order.dao.OrderDAO;
import com.interyard.order.list.OrderList;

/**
 * 주문 상태 일괄 변경 처리를 진행하는 메서드를 가지고 있는 클래스
 */
public class OrderAllUpdateService implements Service {
	private OrderDAO dao;
	
	/**
	 * 주문 상태 일괄 변경 처리를 진행하는 메서드
	 */
	@Override
	public Integer service(Object obj) throws Exception {
		OrderList list = (OrderList) obj;
		// 해당 번호들의 주문의 상태를 수정
		if(list.get(0).getOrderState().equals("구매 확인")|| list.get(0).getOrderState().equals("구매 확인(리뷰작성)"))
			dao.confirmDateUpdate(list);	
		else
			dao.confirmDateReset(list);	
		return dao.allUpdate(list);
	}

	@Override
	public void setDAO(DAO dao) {		
		this.dao = (OrderDAO) dao;
	}

}
