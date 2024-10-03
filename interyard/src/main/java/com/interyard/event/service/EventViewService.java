package com.interyard.event.service;

import com.interyard.event.dao.EventDAO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

public class EventViewService implements Service{
	
	private EventDAO dao;

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (EventDAO) dao;
	}

	@Override
	public Object service(Object obj) throws Exception {
		
		Long[] objs = (Long[]) obj; 
		Long no = objs[0];
		Long inc = objs[1];

		if(inc == 1) dao.increase(no);
		
		return dao.view(no);
	}

}
