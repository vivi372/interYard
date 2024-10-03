package com.interyard.goods.service;

import com.interyard.goods.dao.GoodsDAO;
import com.interyard.goods.vo.GoodsVO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

public class GoodsSATViewService implements Service {

	private GoodsDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (GoodsDAO) dao;
	}

	@Override
	public GoodsVO service(Object obj) throws Exception {
		// DB 처리는 DAO에서 처리 - ImageDAO.view()
		// ImageController - (Execute) - [ImageViewService] - ImageDAO.view()
		return dao.goodsSATVIEW((Long) obj);
	}

}
