package com.interyard.message.service;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.message.dao.MessageDAO;
import com.interyard.message.vo.MessageVO;

public class MessageWriteService implements Service {
	
	private MessageDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MessageDAO) dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 메시지 번호 & 아이디 & 관련 메시지 번호 저장
		MessageVO vo = (MessageVO) obj;
		Long msgNo = dao.getMsgNo();
		vo.setMsgNo(msgNo);
		
		if (!vo.isReply()) {
			// 일반 메시지 >> refNo == msgNo
			vo.setRefNo(msgNo);
		} else {
			dao.incOrdNo(vo);
		}
		
		Integer result = dao.write(vo);
		dao.incNewMsgcnt(vo.getAccepterId());
		
		return result;
	}

}
