package com.interyard.dlvy.service;

import com.interyard.dlvy.dao.DlvyDAO;
import com.interyard.dlvy.vo.DlvyVO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

/**
 * 배송지 수정 처리를 진행하는 메서드를 가지고 있는 클래스
 */
public class DlvyUpdateService implements Service {
	private DlvyDAO dao;
	
	/**
	 * 배송지 수정 처리를 진행하는 메서드
	 */
	@Override
	public Integer service(Object obj) throws Exception {
		DlvyVO vo = (DlvyVO) obj;
		//만약 수정하는 배송지가 기본 배송지면 다른 배송지를 기본 배송지가 아니게 바꾼다.
		if(vo.getBasic()==1) {
			dao.changeBasic(vo.getId());
		}
		//db에서 배송지를 수정한다.
		return dao.update((DlvyVO) obj);
	}

	@Override
	public void setDAO(DAO dao) {
		this.dao = (DlvyDAO) dao;

	}

}
