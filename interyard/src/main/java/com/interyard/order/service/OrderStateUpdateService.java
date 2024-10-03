package com.interyard.order.service;


import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.order.dao.OrderDAO;
import com.interyard.order.list.OrderList;
import com.interyard.order.vo.OrderVO;

/**
 * 주문 상태 변경 처리를 진행하는 메서드를 가지고 있는 클래스
 */
public class OrderStateUpdateService implements Service {
	private OrderDAO dao;
	
	/**
	 * 주문 상태 변경 처리를 진행하는 메서드
	 */
	@Override
	public Integer service(Object obj) throws Exception {
		OrderVO vo = (OrderVO) obj;
		// 해당 번호의 주문의 상태를 수정
		if(vo.getOrderState().equals("구매 확인")|| vo.getOrderState().equals("구매 확인(리뷰작성)")) {
			OrderList list = new OrderList();
			list.add(vo);
			dao.confirmDateUpdate(list);			
		} else {
			OrderList list = new OrderList();
			list.add(vo);
			dao.confirmDateReset(list);	
		}
		return dao.stateUpdate(vo);
	}

	@Override
	public void setDAO(DAO dao) {		
		this.dao = (OrderDAO) dao;
	}

}
