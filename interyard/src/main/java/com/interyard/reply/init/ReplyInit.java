package com.interyard.reply.init;

import java.util.HashMap;
import java.util.Map;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.reply.dao.ReplyDAO;
import com.interyard.reply.service.ReplyDeleteService;
import com.interyard.reply.service.ReplyListService;
import com.interyard.reply.service.ReplyUpdateService;
import com.interyard.reply.service.ReplyWriteService;

public class ReplyInit {

	// service 생성해서 저장하는 객체 - <URI, service>
	private static Map<String, Service> serviceMap = new HashMap<>();
	// dao 생성해서 저장하는 객체 - <className, dao>
	private static Map<String, DAO> daoMap = new HashMap<>();
	
	static {
		//--------------[댓글 객체 생성과 조립]--------------------------------
		//dao생성
		daoMap.put("replyDAO", new ReplyDAO());
		//service생성
		serviceMap.put("/reply/list.do", new ReplyListService());
		serviceMap.put("/reply/write.do", new ReplyWriteService());
		serviceMap.put("/reply/update.do", new ReplyUpdateService());
		serviceMap.put("/reply/delete.do", new ReplyDeleteService());
		//조립 dao -> service
		serviceMap.get("/reply/list.do").setDAO(daoMap.get("replyDAO"));
		serviceMap.get("/reply/write.do").setDAO(daoMap.get("replyDAO"));
		serviceMap.get("/reply/update.do").setDAO(daoMap.get("replyDAO"));
		serviceMap.get("/reply/delete.do").setDAO(daoMap.get("replyDAO"));

	}
	public static Service get(String uri) {
		return serviceMap.get(uri);
	}
}