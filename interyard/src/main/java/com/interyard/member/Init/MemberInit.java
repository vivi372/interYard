package com.interyard.member.Init;

import java.util.HashMap;
import java.util.Map;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.member.dao.MemberDAO;
import com.interyard.member.service.MemberChangeGradeNoService;
import com.interyard.member.service.MemberChangeStatusService;
import com.interyard.member.service.MemberCheckEmailService;
import com.interyard.member.service.MemberCheckIdService;
import com.interyard.member.service.MemberCheckTelService;
import com.interyard.member.service.MemberConUpdateService;
import com.interyard.member.service.MemberListService;
import com.interyard.member.service.MemberLoginService;
import com.interyard.member.service.MemberNewMsgCntService;
import com.interyard.member.service.MemberSearchIdService;
import com.interyard.member.service.MemberSearchPwService;
import com.interyard.member.service.MemberUpdateService;
import com.interyard.member.service.MemberVeriFyService;
import com.interyard.member.service.MemberViewService;
import com.interyard.member.service.MemberWriteService;

public class MemberInit {

	private static Map<String, Service> serviceMap = new HashMap<>();
	private static Map<String, DAO> daoMp = new HashMap<>();
	
	static {
		// dao 생성
		daoMp.put("memberDAO", new MemberDAO());
		// service 새성
		serviceMap.put("/member/list.do", new MemberListService());
		serviceMap.put("/member/view.do", new MemberViewService());
		serviceMap.put("/member/write.do", new MemberWriteService());
		serviceMap.put("/member/update.do", new MemberUpdateService());
		serviceMap.put("/member/login.do", new MemberLoginService());
		serviceMap.put("/member/changeGrade.do", new MemberChangeGradeNoService());
		serviceMap.put("/member/changeStatus.do", new MemberChangeStatusService());
		serviceMap.put("/member/searchId.do", new MemberSearchIdService());
		serviceMap.put("/member/searchPw.do", new MemberSearchPwService());
		serviceMap.put("/member/veriFy.do", new MemberVeriFyService());
		serviceMap.put("/member/updateConDate.do", new MemberConUpdateService());
		// ajax
		serviceMap.put("/ajax/checkId.do", new MemberCheckIdService());
		serviceMap.put("/ajax/checkEmail.do", new MemberCheckEmailService());
		serviceMap.put("/ajax/checkTel.do", new MemberCheckTelService());
		serviceMap.put("/ajax/getNewMsgCnt.do", new MemberNewMsgCntService());
		// 조립 dao -> service
		serviceMap.get("/member/list.do").setDAO(daoMp.get("memberDAO"));
		serviceMap.get("/member/view.do").setDAO(daoMp.get("memberDAO"));
		serviceMap.get("/member/update.do").setDAO(daoMp.get("memberDAO"));
		serviceMap.get("/member/write.do").setDAO(daoMp.get("memberDAO"));
		serviceMap.get("/member/login.do").setDAO(daoMp.get("memberDAO"));
		serviceMap.get("/member/changeGrade.do").setDAO(daoMp.get("memberDAO"));
		serviceMap.get("/member/changeStatus.do").setDAO(daoMp.get("memberDAO"));
		serviceMap.get("/member/searchId.do").setDAO(daoMp.get("memberDAO"));
		serviceMap.get("/member/searchPw.do").setDAO(daoMp.get("memberDAO"));
		serviceMap.get("/member/veriFy.do").setDAO(daoMp.get("memberDAO"));
		serviceMap.get("/member/updateConDate.do").setDAO(daoMp.get("memberDAO"));
		
		// ajax
		serviceMap.get("/ajax/checkId.do").setDAO(daoMp.get("memberDAO"));
		serviceMap.get("/ajax/checkEmail.do").setDAO(daoMp.get("memberDAO"));
		serviceMap.get("/ajax/checkTel.do").setDAO(daoMp.get("memberDAO"));
		serviceMap.get("/ajax/getNewMsgCnt.do").setDAO(daoMp.get("memberDAO"));
	}
	
	public static Service get(String uri) {
		return serviceMap.get(uri);
	}
}
