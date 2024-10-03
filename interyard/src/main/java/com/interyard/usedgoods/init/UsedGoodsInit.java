package com.interyard.usedgoods.init;

import java.util.HashMap;
import java.util.Map;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.usedgoods.dao.UsedGoodsDAO;
import com.interyard.usedgoods.service.UsedGoodsDeleteService;
import com.interyard.usedgoods.service.UsedGoodsListService;
import com.interyard.usedgoods.service.UsedGoodsUpdateService;
import com.interyard.usedgoods.service.UsedGoodsViewService;
import com.interyard.usedgoods.service.UsedGoodsWriteService;

public class UsedGoodsInit {

	// service 생성해서 저장하는 객체 - <URI, service>
	private static Map<String, Service> serviceMap = new HashMap<>();
	// dao 생성해서 저장하는 객체 - <className, dao>
	private static Map<String, DAO> daoMap = new HashMap<>();
	
	static {
		//--------------[중고장터 객체 생성과 조립]--------------------------------
		//dao생성
		daoMap.put("usedgoodsDAO", new UsedGoodsDAO());
		//service생성
		serviceMap.put("/usedgoods/list.do", new UsedGoodsListService());
		serviceMap.put("/usedgoods/view.do", new UsedGoodsViewService());
		serviceMap.put("/usedgoods/write.do", new UsedGoodsWriteService());
		serviceMap.put("/usedgoods/update.do", new UsedGoodsUpdateService());
		serviceMap.put("/usedgoods/delete.do", new UsedGoodsDeleteService());
		//serviceMap.put("/usedgoods/changeImage.do", new UsedGoodsChangeService());
		//조립 dao -> service
		serviceMap.get("/usedgoods/list.do").setDAO(daoMap.get("usedgoodsDAO"));
		serviceMap.get("/usedgoods/view.do").setDAO(daoMap.get("usedgoodsDAO"));
		serviceMap.get("/usedgoods/write.do").setDAO(daoMap.get("usedgoodsDAO"));
		serviceMap.get("/usedgoods/update.do").setDAO(daoMap.get("usedgoodsDAO"));
		serviceMap.get("/usedgoods/delete.do").setDAO(daoMap.get("usedgoodsDAO"));
		//serviceMap.get("/usedgoods/changeImage.do").setDAO(daoMap.get("usedgoodsDAO"));
	}
	public static Service get(String uri) {
		return serviceMap.get(uri);
	}
}