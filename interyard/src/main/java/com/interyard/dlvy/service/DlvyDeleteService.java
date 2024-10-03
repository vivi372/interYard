package com.interyard.dlvy.service;

import com.interyard.dlvy.dao.DlvyDAO;
import com.interyard.dlvy.vo.DlvyVO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

/**
 * 배송지 삭제 처리를 진행하는 메서드를 가지고 있는 클래스
 */
public class DlvyDeleteService implements Service {
	private DlvyDAO dao;
	
	/**
	 * 배송지 삭제 처리를 진행하는 메서드를 가지고 있는 클래스
	 */
	@Override
	public Integer service(Object obj) throws Exception {
		//db에서 해당 배송지를 삭제한다.
		return dao.delete((DlvyVO) obj);
	}

	@Override
	public void setDAO(DAO dao) {
		this.dao = (DlvyDAO) dao;

	}

}
