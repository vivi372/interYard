package com.interyard.goods.service;

import java.util.List;

import com.interyard.goods.dao.GoodsDAO;
import com.interyard.goods.vo.GoodsVO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.webjjang.util.page.PageObject;

public class GoodsListService implements Service {

	private GoodsDAO dao;

	// dao setter
	public void setDAO(DAO dao) {
		this.dao = (GoodsDAO) dao;
	}

	@Override
	public List<GoodsVO> service(Object obj) throws Exception {

		PageObject pageObject = (PageObject) obj;
		// DB board에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴
		// 전체 데이터의 개수
		pageObject.setTotalRow(dao.getTotalRow(pageObject));

		// DB 처리는 DAO에서 처리 - BoardDAO.list()
		// ImageController - (Execute) - [ImageListService] - ImageDAO.list()
		return dao.goodsList(pageObject);
	}

}
