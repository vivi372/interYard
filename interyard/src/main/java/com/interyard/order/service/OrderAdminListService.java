package com.interyard.order.service;



import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.order.dao.OrderDAO;
import com.interyard.order.list.OrderList;
import com.webjjang.util.page.PageObject;

/**
 * 주문 관리 리스트 처리를 진행하는 메서드를 가지고 있는 클래스
 */
public class OrderAdminListService implements Service {
	private OrderDAO dao;
	
	/**
	 * 주문 관리 리스트 처리를 진행하는 메서드
	 */
	@Override
	public OrderList service(Object obj) throws Exception {
		//페이지 오브젝트의 메서드를 사용하기 위해 가져오기
		PageObject pageObj = (PageObject) obj;		
		//페이지 표현을 위해 데이터의 수 가져와 세팅
		pageObj.setTotalRow(dao.getAdminTotalRow(pageObj));
		//db에서 데이터 정보 가져오기 - OrderList
		return dao.adminList(pageObj);
	}

	@Override
	public void setDAO(DAO dao) {		
		this.dao = (OrderDAO) dao;
	}

}
