package com.interyard.member.service;


import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.member.dao.MemberDAO;

public class MemberCheckIdService implements Service{
	private MemberDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MemberDAO) dao;
	}

	@Override
	public String  service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.checkId((String)obj);
	}
}
