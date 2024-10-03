package com.interyard.event.init;

import java.util.HashMap;
import java.util.Map;

import com.interyard.event.dao.EventDAO;
import com.interyard.event.service.EventBookListService;
import com.interyard.event.service.EventDeleteService;
import com.interyard.event.service.EventListService;
import com.interyard.event.service.EventShopListService;
import com.interyard.event.service.EventTicketListService;
import com.interyard.event.service.EventUpdateService;
import com.interyard.event.service.EventViewService;
import com.interyard.event.service.EventWriteService;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;


public class EventInit {
	private static Map<String, Service> serviceMap = new HashMap<>();
	private static Map<String, DAO> daoMap = new HashMap<>();
	
	static {
		daoMap.put("eventDAO", new EventDAO());
		
		serviceMap.put("/event/list.do", new EventListService());
		serviceMap.put("/event/booklist.do", new EventBookListService());
		serviceMap.put("/event/shoplist.do", new EventShopListService());
		serviceMap.put("/event/ticketlist.do", new EventTicketListService());
		serviceMap.put("/event/view.do", new EventViewService());
		serviceMap.put("/event/write.do", new EventWriteService());
		serviceMap.put("/event/update.do", new EventUpdateService());
		serviceMap.put("/event/delete.do", new EventDeleteService());
		
		serviceMap.get("/event/list.do").setDAO(daoMap.get("eventDAO"));
		serviceMap.get("/event/booklist.do").setDAO(daoMap.get("eventDAO"));
		serviceMap.get("/event/shoplist.do").setDAO(daoMap.get("eventDAO"));
		serviceMap.get("/event/ticketlist.do").setDAO(daoMap.get("eventDAO"));
		serviceMap.get("/event/view.do").setDAO(daoMap.get("eventDAO"));
		serviceMap.get("/event/write.do").setDAO(daoMap.get("eventDAO"));
		serviceMap.get("/event/update.do").setDAO(daoMap.get("eventDAO"));
		serviceMap.get("/event/delete.do").setDAO(daoMap.get("eventDAO"));
	}
	
	public static Service get(String uri) {
		
		return serviceMap.get(uri);
		
	}
}
