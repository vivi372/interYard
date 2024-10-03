package com.interyard.message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.interyard.member.Init.MemberInit;
import com.interyard.member.vo.LoginVO;
import com.interyard.message.init.MessageInit;
import com.interyard.message.vo.MessageVO;
import com.interyard.util.exe.Execute;
import com.interyard.util.multi.MultiPartUtil;
import com.webjjang.util.page.PageObject;

public class MessageController {

	public String execute(HttpServletRequest request) {

		String uri = request.getRequestURI();
		String jsp = null;
		Object result = null;
		
		// login 한 아이디 가져오기
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String id = null;
		
		if (login != null) id = login.getId();
		
		String savePath = "upload/image";
		
		try {
			
			MultiPartUtil multi = new MultiPartUtil(request, savePath);
			
			switch (uri) {
			case "/message/list.do":
				System.out.println("----- 1. message List -----");
				
				PageObject pageObject = PageObject.getInstance(request);
				String strMode = request.getParameter("mode");
				
				if (strMode != null && !strMode.equals("")) 
					pageObject.setAcceptMode(Integer.parseInt(strMode));
				
				pageObject.setAccepter(id);
				
				result = Execute.execute(MessageInit.get(uri), pageObject);
				login.setNewMsgCnt((Long) Execute.execute(MemberInit.get("/ajax/getNewMsgCnt.do"), id));
				
				request.setAttribute("list", result);
				request.setAttribute("pageObject", pageObject);
				
				jsp = "message/list";
				break;

			case "/message/view.do":
				System.out.println("----- 2. message View -----");
				
				// PageObject
				pageObject = PageObject.getInstance(request);
				// 메시지 상세보기 출력
				Long msgNo = Long.parseLong(request.getParameter("msgNo"));
				Long refNo = Long.parseLong(request.getParameter("no"));
				
				MessageVO vo = new MessageVO();
				vo.setMsgNo(msgNo);
				vo.setRefNo(refNo);
				if (request.getParameter("accept").equals("1"))
					vo.setAccepterId(id); // 로그인한 아이디
				
				result = Execute.execute(MessageInit.get(uri), vo);
				login.setNewMsgCnt((Long) Execute.execute(MemberInit.get("/ajax/getNewMsgCnt.do"), id));
				
				request.setAttribute("mvo", result);
				request.setAttribute("pageObject", pageObject);
				
				jsp = "message/view";
				break;
				
			case "/message/writeForm.do":
				System.out.println("----- 3-1. message WriteForm -----");
				
				jsp = "message/writeForm";
				break;
				
			case "/message/write.do":
				System.out.println("----- 3-2. message Write -----");
				
				multi.initMultipartRequest();
//				mult.deleteFile("uploadFile"); // 파일 삭제 시
				
				// 데이터 가져오기
				// 받는 사람Id, 제목, 내용, 첨부 파일, 관련 메시지 번호, 순서 번호, 들여쓰기 번호, 부모 메시지 번호
				String accepterId = multi.getParameter("accepterId");
				String title = multi.getParameter("title");
				String content = multi.getParameter("content");
				String uploadFile = multi.getParameter("uploadFile");
				String strRefNo = multi.getParameter("refNo");
				Long ordNo = Long.parseLong(multi.getParameter("ordNo"));
				Long levNo = Long.parseLong(multi.getParameter("levNo"));
				String strParentNo = multi.getParameter("parentNo");
				
				// 가져온 데이터 저장하기
				vo = new MessageVO();
				vo.setAccepterId(accepterId);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setSenderId(id); // 로그인한 아이디 사용
				vo.setUploadFile(uploadFile);
				vo.setOrdNo(ordNo);
				vo.setLevNo(levNo);
				// 답장인 경우 >> refNo & parentNo 도 vo 에 저장
				if (strRefNo != null && !strRefNo.equals("")) {
					vo.setRefNo(Long.parseLong(strRefNo));
					vo.setParentNo(Long.parseLong(strParentNo));
					vo.setReply(true); // 답장
				} else {
					vo.setReply(false); // 일반 메시지
				}
				
				// 실행
				Execute.execute(MessageInit.get(uri), vo);
				System.out.println("정상적으로 메시지가 [" + accepterId + "]님께 전송되었습니다.");
				pageObject = PageObject.getInstance(request);
				
				// 리스트로 가기
				jsp = "redirect:list.do?mode=2&" + pageObject.getPageQuery(); // writeForm 에서 write.do 로 넘길 때
				break;
				
			case "/message/delete.do":
				System.out.println("----- 4. message Delete -----");
				
				// 데이터 가져오기
				String[] strNos = request.getParameterValues("msgNo");
				
				if (strNos != null) {
		            for (String strNo : strNos) {
		                try {
		                    // 문자열을 Long 으로 변환하고 리스트에 추가
		                    msgNo = Long.parseLong(strNo);
		                    String acceptParam = request.getParameter("accept");
		                    accepterId = request.getParameter("accepterId"); // 받은 사람 Id
		                   
		                    // 데이터 저장
		                    MessageVO delete = new MessageVO();
		                    delete.setMsgNo(msgNo);
		                    if (acceptParam != null && acceptParam.equals("0"))
		                    	delete.setAccepterId(accepterId); // 보낸 메시지 지우기
		                    else
		                    	delete.setAccepterId(id); // 받은 메시지 지우기
		                    
		                    Execute.execute(MessageInit.get(uri), delete);
		                    
		                } catch (NumberFormatException e) {
		                    e.printStackTrace();
		                }
		            } // end of forEach
		        } // end of if
				
				
				System.out.println("메시지가 정상적으로 삭제되었습니다.");
				
				jsp = "redirect:list.do";
				break;

			default:
				break;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return jsp;
		
	} // end of execute()
	
} // end of class
