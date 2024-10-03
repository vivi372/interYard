package com.interyard.notice.service;

import java.util.List;

import com.interyard.notice.dao.NoticeDAO;
import com.interyard.notice.vo.NoticeVO;
import com.webjjang.util.page.PageObject;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

public class NoticeListService implements Service {

	private NoticeDAO dao;
	@Override
	public List<NoticeVO> service(Object obj) throws Exception {
		PageObject pageObject = (PageObject) obj;
		// DB board에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴
		// 전체 데이터의 개수
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		// DB notice에서 리스트 쿼리 실행해서 데이터 가져오기 - 리턴
		// DB 처리는 DAO에서 처리 - NoticeDAO.list()
		// NoticeController - (Execute) - [NoticeListService] - NoticeDAO.list()
		System.out.println("NoticeListService.service().dao=" + dao);
		return dao.list(pageObject);
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao= (NoticeDAO)dao;
	}

}
