package com.interyard.basket.init;

import java.util.HashMap;
import java.util.Map;

import com.interyard.basket.dao.BasketDAO;
import com.interyard.basket.service.BasketDeleteService;
import com.interyard.basket.service.BasketListService;
import com.interyard.basket.service.BasketUpdateService;
import com.interyard.basket.service.BasketWriteService;
import com.interyard.basketopt.dao.BasketOptDAO;
import com.interyard.basketopt.service.BasketOptListService;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;


public class BasketInit {

	// service 생성해서 저장하는 객체 - <URI, service>
	private static Map<String, Service> serviceMap = new HashMap<>();
	// DAO 생성해서 저장하는 객체 - <className, DAO>
	private static Map<String, DAO> daoMap = new HashMap<>();
	
	static {
		daoMap.put("basketDAO", new BasketDAO());
		daoMap.put("basketOptDAO", new BasketOptDAO());
		
		serviceMap.put("/basket/list.do", new BasketListService());
		serviceMap.put("/basket/write.do", new BasketWriteService());
		serviceMap.put("/basket/update.do", new BasketUpdateService());
		serviceMap.put("/basket/delete.do", new BasketDeleteService());
		serviceMap.put("/opt/list.do", new BasketOptListService());
		
		serviceMap.get("/basket/list.do").setDAO(daoMap.get("basketDAO"));
		serviceMap.get("/basket/write.do").setDAO(daoMap.get("basketDAO"));
		serviceMap.get("/basket/update.do").setDAO(daoMap.get("basketDAO"));
		serviceMap.get("/basket/delete.do").setDAO(daoMap.get("basketDAO"));
		serviceMap.get("/opt/list.do").setDAO(daoMap.get("basketOptDAO"));
		
		System.out.println("장바구니.init() 초기화 블럭");
	}
	
	public static Service get(String uri) {
		return serviceMap.get(uri);
	}
}
