package com.interyard.member.service;

import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.member.dao.MemberDAO;
import com.interyard.member.vo.MemberVO;
import com.webjjang.util.page.PageObject;

public class MemberListService implements Service{
	private MemberDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MemberDAO) dao;
	}

	@Override
	public List<MemberVO> service(Object obj) throws Exception {
		PageObject pageObject = (PageObject) obj;
		
		pageObject.setTotalRow(dao.getTotalRow(pageObject));	
		// TODO Auto-generated method stub
		return dao.list(pageObject);
	}
}
