package com.interyard.basket.service;



import com.interyard.basket.dao.BasketDAO;
import com.interyard.basket.list.BasketList;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

/**
 * 장바구니 삭제 처리를 진행하는 메서드를 가지고 있는 클래스
 */
public class BasketDeleteService implements Service {
	private BasketDAO dao;
	
	/**
	 * 장바구니 삭제 처리를 진행하는 메서드
	 */
	@Override
	public Integer service(Object obj) throws Exception {
		//장바구니 번호와 id가 담긴 리스트를 보내 삭제
		return dao.delete( (BasketList) obj);
	}

	@Override
	public void setDAO(DAO dao) {		
		this.dao = (BasketDAO) dao;
	}

}
