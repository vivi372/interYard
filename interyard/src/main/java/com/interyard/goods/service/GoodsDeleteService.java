package com.interyard.goods.service;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

import com.interyard.goods.dao.GoodsDAO;
import com.interyard.goods.vo.GoodsVO;

public class GoodsDeleteService implements Service {

	private GoodsDAO dao;

	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (GoodsDAO) dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		// DB 처리는 DAO에서 처리 - ImageDAO.delete()
		// ImageController - (Execute) - [ImageDeleteService] - ImageDAO.delete()
		return dao.delete((GoodsVO) obj);
	}

}
