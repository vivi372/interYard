package com.interyard.event.service;

import com.interyard.event.dao.EventDAO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.webjjang.util.page.PageObject;

public class EventShopListService implements Service{
	
	private EventDAO dao;

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (EventDAO) dao;
	}

	@Override
	public Object service(Object obj) throws Exception {
		
		PageObject pageObject = (PageObject) obj;
		// DB board에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴
		// 전체 데이터의 개수
		pageObject.setTotalRow(dao.getTotalRow(pageObject));	
		
		// DB 처리는 DAO에서 처리 - BoardDAO.list()
		// BoardController - (Execute) - [BoardListService] - BoardDAO.list()
		return dao.eventShopList(pageObject);
	}

}
