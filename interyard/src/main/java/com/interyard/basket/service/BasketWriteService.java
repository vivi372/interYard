package com.interyard.basket.service;



import java.util.List;

import com.interyard.basket.dao.BasketDAO;
import com.interyard.basket.list.BasketList;
import com.interyard.basket.vo.OptVO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

/**
 * 장바구니 등록 처리를 진행하는 메서드를 가지고 있는 클래스
 */
public class BasketWriteService implements Service {
	private BasketDAO dao;
	
	/**
	 * 장바구니 등록 처리를 진행하는 메서드
	 */
	@Override
	public Integer service(Object obj) throws Exception {
		//BasketList 리스트의 메서드를 쓰기 위해 강제 형 변환
		BasketList list = (BasketList) obj;
		//리스트를 분리
		List<OptVO> optList = list.splitList();
		//장바구니 정보를 전달해 장바구니 등록
		dao.basketWrite(list.get(0));		
		//장바구니 옵션 정보를 전달해 장바구니 옵션 등록
		return dao.optWrite(optList);
	}

	@Override
	public void setDAO(DAO dao) {		
		this.dao = (BasketDAO) dao;
	}

}
