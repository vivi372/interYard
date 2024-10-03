package com.interyard.message.init;

import java.util.HashMap;
import java.util.Map;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.message.dao.MessageDAO;
import com.interyard.message.service.MessageDeleteService;
import com.interyard.message.service.MessageListService;
import com.interyard.message.service.MessageViewService;
import com.interyard.message.service.MessageWriteService;

public class MessageInit {

	public static Map<String, DAO> daoMap = new HashMap<>();
	public static Map<String, Service> serviceMap = new HashMap<>();
	
	static {
		
		// [ message 객체 생성 ]
		// dao
		daoMap.put("messageDAO", new MessageDAO());
		// service
		serviceMap.put("/message/list.do", new MessageListService());
		serviceMap.put("/message/view.do", new MessageViewService());
		serviceMap.put("/message/write.do", new MessageWriteService());
		serviceMap.put("/message/delete.do", new MessageDeleteService());
		
		// dao + service
		serviceMap.get("/message/list.do").setDAO(daoMap.get("messageDAO"));
		serviceMap.get("/message/view.do").setDAO(daoMap.get("messageDAO"));
		serviceMap.get("/message/write.do").setDAO(daoMap.get("messageDAO"));
		serviceMap.get("/message/delete.do").setDAO(daoMap.get("messageDAO"));
		
		// 답장 dao + service
		
	}
	
	public static Service get(String uri) {
		return serviceMap.get(uri);
	}
	
}
