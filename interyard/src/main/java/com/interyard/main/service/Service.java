package com.interyard.main.service;

import com.interyard.main.dao.DAO;

public interface Service {

	// 실행해야할 메서드
	public Object service(Object obj) throws Exception;
	// dao 저장 메서드 - setter
	public void setDAO(DAO dao);
	
}
