package com.interyard.usedgoods.service;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.usedgoods.dao.UsedGoodsDAO;
import com.interyard.usedgoods.vo.UsedGoodsVO;

public class UsedGoodsDeleteService implements Service {

	private UsedGoodsDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (UsedGoodsDAO) dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		// DB 처리는 DAO에서 처리 - UsedGoodsDAO.delete()
		// UsedGoodsController - (Execute) - [UsedGoodsDeleteService] - UsedGoodsDAO.delete()
		return dao.delete((UsedGoodsVO)obj);
	}

}
