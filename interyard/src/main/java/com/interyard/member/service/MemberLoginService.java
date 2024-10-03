package com.interyard.member.service;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.member.dao.MemberDAO;
import com.interyard.member.vo.LoginVO;

public class MemberLoginService implements Service {
	
	private MemberDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MemberDAO) dao;
	}

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
	return dao.login( (LoginVO) obj);
	}


}
