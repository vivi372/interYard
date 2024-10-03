package com.interyard.dlvy.service;


import com.interyard.dlvy.dao.DlvyDAO;
import com.interyard.dlvy.vo.DlvyVO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
/**
 * 배송지 수정 폼 처리를 진행하는 메서드를 가지고 있는 클래스
 */
public class DlvyUpdateFormService implements Service {
	
	private DlvyDAO dao;
	
	/**
	 * 배송지 수정 폼 처리를 진행하는 메서드
	 */
	@Override
	public DlvyVO service(Object obj) throws Exception {
		//db에서 특정 배송지 정보를 가져온다.
		return dao.view((long) obj);
	}

	@Override
	public void setDAO(DAO dao) {
		this.dao = (DlvyDAO) dao;

	}

}
