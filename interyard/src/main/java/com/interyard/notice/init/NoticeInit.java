package com.interyard.notice.init;

import java.util.HashMap;
import java.util.Map;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.notice.dao.NoticeDAO;
import com.interyard.notice.service.NoticeDeleteService;
import com.interyard.notice.service.NoticeListService;
import com.interyard.notice.service.NoticeUpdateService;
import com.interyard.notice.service.NoticeViewService;
import com.interyard.notice.service.NoticeWriteService;


public class NoticeInit {
	private static Map<String, Service> serviceMap = new HashMap<>();
	private static Map<String, DAO> daoMap = new HashMap<>();
	
	static {
		daoMap.put("noticeDAO", new NoticeDAO());
		
		serviceMap.put("/notice/list.do", new NoticeListService());
		serviceMap.put("/notice/view.do", new NoticeViewService());
		serviceMap.put("/notice/write.do", new NoticeWriteService());
		serviceMap.put("/notice/update.do", new NoticeUpdateService());
		serviceMap.put("/notice/delete.do", new NoticeDeleteService());
		
		serviceMap.get("/notice/list.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/view.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/write.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/update.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/delete.do").setDAO(daoMap.get("noticeDAO"));
	}
	
	public static Service get(String uri) {
		
		return serviceMap.get(uri);
		
	}
}
