package com.interyard.order.init;

import java.util.HashMap;
import java.util.Map;


import com.interyard.dlvy.dao.DlvyDAO;
import com.interyard.dlvy.service.DlvyDeleteService;
import com.interyard.dlvy.service.DlvyListService;
import com.interyard.dlvy.service.DlvyUpdateFormService;
import com.interyard.dlvy.service.DlvyUpdateService;
import com.interyard.dlvy.service.DlvyWriteService;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.order.dao.OrderDAO;
import com.interyard.order.service.OrderAdminListService;
import com.interyard.order.service.OrderAllUpdateService;
import com.interyard.order.service.OrderDeleteService;
import com.interyard.order.service.OrderDlvyUpdateService;
import com.interyard.order.service.OrderListService;
import com.interyard.order.service.OrderStateUpdateService;
import com.interyard.order.service.OrderViewService;
import com.interyard.order.service.OrderWriteFormService;
import com.interyard.order.service.OrderWriteService;


public class OrderInit {

	// service 생성해서 저장하는 객체 - <URI, service>
	private static Map<String, Service> serviceMap = new HashMap<>();
	// DAO 생성해서 저장하는 객체 - <className, DAO>
	private static Map<String, DAO> daoMap = new HashMap<>();
	
	static {
		daoMap.put("dlvyDAO", new DlvyDAO());
		daoMap.put("orderDAO", new OrderDAO());
		
		serviceMap.put("/dlvy/list.do", new DlvyListService());
		serviceMap.put("/dlvy/write.do", new DlvyWriteService());
		serviceMap.put("/dlvy/updateForm.do", new DlvyUpdateFormService());
		serviceMap.put("/dlvy/update.do", new DlvyUpdateService());
		serviceMap.put("/dlvy/delete.do", new DlvyDeleteService());
		
		serviceMap.put("/order/list.do", new OrderListService());
		serviceMap.put("/order/adminList.do", new OrderAdminListService());
		serviceMap.put("/order/view.do", new OrderViewService());
		serviceMap.put("/order/writeForm.do", new OrderWriteFormService());
		serviceMap.put("/order/write.do", new OrderWriteService());
		serviceMap.put("/order/stateUpdate.do", new OrderStateUpdateService());
		serviceMap.put("/order/dlvyUpdate.do", new OrderDlvyUpdateService());
		serviceMap.put("/order/delete.do", new OrderDeleteService());
		serviceMap.put("/order/allUpdate.do", new OrderAllUpdateService());
		
		serviceMap.get("/dlvy/list.do").setDAO(daoMap.get("dlvyDAO"));
		serviceMap.get("/dlvy/write.do").setDAO(daoMap.get("dlvyDAO"));
		serviceMap.get("/dlvy/updateForm.do").setDAO(daoMap.get("dlvyDAO"));
		serviceMap.get("/dlvy/update.do").setDAO(daoMap.get("dlvyDAO"));
		serviceMap.get("/dlvy/delete.do").setDAO(daoMap.get("dlvyDAO"));
		
		serviceMap.get("/order/list.do").setDAO(daoMap.get("orderDAO"));
		serviceMap.get("/order/adminList.do").setDAO(daoMap.get("orderDAO"));
		serviceMap.get("/order/view.do").setDAO(daoMap.get("orderDAO"));
		serviceMap.get("/order/writeForm.do").setDAO(daoMap.get("orderDAO"));
		serviceMap.get("/order/write.do").setDAO(daoMap.get("orderDAO"));
		serviceMap.get("/order/stateUpdate.do").setDAO(daoMap.get("orderDAO"));
		serviceMap.get("/order/dlvyUpdate.do").setDAO(daoMap.get("orderDAO"));
		serviceMap.get("/order/delete.do").setDAO(daoMap.get("orderDAO"));
		serviceMap.get("/order/allUpdate.do").setDAO(daoMap.get("orderDAO"));
		
		System.out.println("OrderInit(dlvy,order) 초기화 블럭");
	}
	
	public static Service get(String uri) {
		return serviceMap.get(uri);
	}
}
