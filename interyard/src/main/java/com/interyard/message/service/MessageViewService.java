package com.interyard.message.service;

import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.message.dao.MessageDAO;
import com.interyard.message.vo.MessageVO;

public class MessageViewService implements Service {
	
	private MessageDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MessageDAO) dao;
	}

	@Override
	public List<MessageVO> service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		MessageVO vo = (MessageVO) obj;
		Long refNo = vo.getRefNo();
		String id = vo.getAccepterId(); // 로그인한 아이디
		
		if (vo.getAccepterId() != null) {
			int readed = dao.setReaded(refNo);
			if (readed == 1) dao.decMsgCnt(id); // 로그인한 아이디
		}
		
		return dao.view(refNo);
	}

}
