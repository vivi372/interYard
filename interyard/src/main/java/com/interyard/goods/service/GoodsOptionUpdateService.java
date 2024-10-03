package com.interyard.goods.service;

import com.interyard.goods.dao.GoodsDAO;
import com.interyard.goods.vo.GoodsVO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

public class GoodsOptionUpdateService implements Service {

	private GoodsDAO dao;

	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (GoodsDAO) dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		// DB image에서 수정 쿼리 실행해서 데이터 처리
		// ImageController - (Execute) - [ImageUpdateService] - ImageDAO.update()
		return dao.optionUpdate((GoodsVO) obj);
	}

}
