package com.interyard.goods.service;

import com.interyard.goods.dao.GoodsDAO;
import com.interyard.goods.vo.GoodsVO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

public class GoodsOptionWriteService implements Service {

	private GoodsDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (GoodsDAO) dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		// DB board에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴
		// DB 처리는 DAO에서 처리 - ImageDAO.write()
		// ImageController - (Execute) - [ImageWriteService] - ImageDAO.write()
		return dao.optionWrite((GoodsVO)obj);
	}

}
