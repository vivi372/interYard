package com.interyard.event.service;

import com.interyard.event.dao.EventDAO;
import com.interyard.event.vo.EventVO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

public class EventUpdateService implements Service {

	private EventDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (EventDAO) dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		
		return dao.update((EventVO) obj);
	}


}
