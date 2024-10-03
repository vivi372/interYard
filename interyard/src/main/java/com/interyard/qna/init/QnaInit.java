package com.interyard.qna.init;

import java.util.HashMap;
import java.util.Map;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.qna.dao.QnaDAO;
import com.interyard.qna.service.QnaAWriteService;
import com.interyard.qna.service.QnaAnswerViewService;
import com.interyard.qna.service.QnaDeleteService;
import com.interyard.qna.service.QnaFaqListService;
import com.interyard.qna.service.QnaListService;
import com.interyard.qna.service.QnaUpdateService;
import com.interyard.qna.service.QnaQWriteService;
import com.interyard.qna.service.QnaRnumListService;
import com.interyard.qna.service.QnaViewService;

public class QnaInit {

	// Service 생성해서 저장하는 객체
	private static Map<String, Service> serviceMap = new HashMap<>();
	// DAO 생성해서 저장하는 객체
	private static Map<String, DAO> daoMap = new HashMap<>();
	
	static {
		// ---------------- QNA 게시판 객체 생성 및 조립 ---------------------------
		// dao 생성
		daoMap.put("QnaDAO", new QnaDAO());
		
		// service 생성
		serviceMap.put("/qna/list.do", new QnaListService());
		serviceMap.put("/qna/faqList.do", new QnaFaqListService());
		serviceMap.put("/qna/view.do", new QnaViewService());
		serviceMap.put("/qna/answerList.do", new QnaAnswerViewService());
		serviceMap.put("/qna/rnumList.do", new QnaRnumListService());
		serviceMap.put("/qna/qWrite.do", new QnaQWriteService());
		serviceMap.put("/qna/aWrite.do", new QnaAWriteService());
		serviceMap.put("/qna/update.do", new QnaUpdateService());
		serviceMap.put("/qna/delete.do", new QnaDeleteService());
		
		// 조립
		serviceMap.get("/qna/list.do").setDAO(daoMap.get("QnaDAO"));
		serviceMap.get("/qna/faqList.do").setDAO(daoMap.get("QnaDAO"));
		serviceMap.get("/qna/view.do").setDAO(daoMap.get("QnaDAO"));
		serviceMap.get("/qna/answerList.do").setDAO(daoMap.get("QnaDAO"));
		serviceMap.get("/qna/rnumList.do").setDAO(daoMap.get("QnaDAO"));
		serviceMap.get("/qna/qWrite.do").setDAO(daoMap.get("QnaDAO"));
		serviceMap.get("/qna/aWrite.do").setDAO(daoMap.get("QnaDAO"));
		serviceMap.get("/qna/update.do").setDAO(daoMap.get("QnaDAO"));
		serviceMap.get("/qna/delete.do").setDAO(daoMap.get("QnaDAO"));
		
		System.out.println("QnaInit.static 초기화 블록 ------------------------------- 객체 생성과 로딩");
		
	}
	
	public static Service get (String uri) {
		
		return serviceMap.get(uri);
	}
	
}
