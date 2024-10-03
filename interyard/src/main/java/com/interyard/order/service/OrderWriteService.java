package com.interyard.order.service;



import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.order.dao.OrderDAO;
import com.interyard.order.list.OrderList;

/**
 * 주문 쓰기 처리를 진행하는 메서드를 가지고 있는 클래스
 */
public class OrderWriteService implements Service {
	private OrderDAO dao;
	
	/**
	 * 주문 쓰기 처리를 진행하는 메서드
	 */
	@Override
	public Integer service(Object obj) throws Exception {
		//OrderList의 메서드를 사용하기 위해 가져오기
		OrderList list = (OrderList) obj;
		//주문 정보와 옵션 정보를 분리
		OrderList optList = list.splitStack();	
		//write() - 주문 리스트,옵션 리스트를 이용해
		//주문 등록 쿼리, 주문 옵션 등록 쿼리를 짠후 주문 등록을 실행하고 주문 옵션 등록 쿼리를 반환한다.
		String writeOptSql = dao.write(list,optList);		
		//반환된 주문 옵션 등록 쿼리를 writeOpt에 넣어 주문 옵션을 등록한다.
		return dao.writeOpt(writeOptSql);
	}

	@Override
	public void setDAO(DAO dao) {		
		this.dao = (OrderDAO) dao;
	}

}
