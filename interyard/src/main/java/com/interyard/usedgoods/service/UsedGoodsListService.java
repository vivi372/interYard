package com.interyard.usedgoods.service;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.usedgoods.dao.UsedGoodsDAO;
import com.webjjang.util.page.PageObject;

public class UsedGoodsListService implements Service {

	// UsedGoodsDAO 객체를 저장할 변수.
	private UsedGoodsDAO dao;
	
	@Override
	// Service 인터페이스의 service 메소드를 구현
	public Object service(Object obj) throws Exception {
		// obj를 PageObject로 캐스팅합니다.
		PageObject pageObject = (PageObject) obj;
		// PageObject에 총 행 수를 설정합니다.
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		// DAO의 list 메소드를 호출하여 결과를 반환합니다.
		return dao.list(pageObject);
	}

	@Override
	// Service 인터페이스의 setDAO 메소드를 구현
	public void setDAO(DAO dao) {
		// DAO 객체를 UsedGoodsDAO로 캐스팅하여 저장
		this.dao = (UsedGoodsDAO) dao;
	}
}