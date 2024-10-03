package com.interyard.goods.Init;

import java.util.HashMap;
import java.util.Map;

import com.interyard.goods.dao.GoodsDAO;
import com.interyard.goods.service.GoodsDeleteService;
import com.interyard.goods.service.GoodsListService;
import com.interyard.goods.service.GoodsOptionUpdateService;
import com.interyard.goods.service.GoodsOptionWriteService;
import com.interyard.goods.service.GoodsSATViewService;
import com.interyard.goods.service.GoodsSATWriteService;
import com.interyard.goods.service.GoodsUpdateService;
import com.interyard.goods.service.GoodsBookViewService;
import com.interyard.goods.service.GoodsBookWriteService;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

public class GoodsInit {

	private static Map<String, Service> serviceMap = new HashMap<>();

	private static Map<String, DAO> daoMap = new HashMap<>();

	static {

		daoMap.put("goodsDAO", new GoodsDAO());

		serviceMap.put("/goods/list.do", new GoodsListService());
		serviceMap.put("/goods/shopList.do", new GoodsListService());
		serviceMap.put("/goods/ticketList.do", new GoodsListService());
		serviceMap.put("/goods/view.do", new GoodsBookViewService());
		serviceMap.put("/goods/shopView.do", new GoodsSATViewService());
		serviceMap.put("/goods/ticketView.do", new GoodsSATViewService());
		serviceMap.put("/goods/write.do", new GoodsBookWriteService());
		serviceMap.put("/goods/shopWrite.do", new GoodsSATWriteService());
		serviceMap.put("/goods/ticketWrite.do", new GoodsSATWriteService());
		serviceMap.put("/goods/optionWrite.do", new GoodsOptionWriteService());
		serviceMap.put("/goods/update.do", new GoodsUpdateService());
		serviceMap.put("/goods/shopUpdate.do", new GoodsUpdateService());
		serviceMap.put("/goods/ticketUpdate.do", new GoodsUpdateService());
		serviceMap.put("/goods/optionUpdate.do", new GoodsOptionUpdateService());
		serviceMap.put("/goods/delete.do", new GoodsDeleteService());

		serviceMap.get("/goods/list.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/shopList.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/ticketList.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/view.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/shopView.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/ticketView.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/write.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/shopWrite.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/ticketWrite.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/optionWrite.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/update.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/shopUpdate.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/ticketUpdate.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/optionUpdate.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/delete.do").setDAO(daoMap.get("goodsDAO"));

		System.out.println("GoodsInit 초기화 블록 --- 객체 생성과 로딩");

	}

	public static Service get(String uri) {
		return serviceMap.get(uri);
	}

}
