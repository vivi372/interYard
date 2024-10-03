package com.interyard.ajax.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.interyard.member.Init.MemberInit;
import com.interyard.member.vo.LoginVO;
import com.interyard.util.exe.Execute;

public class AjaxController {

	public String excute(HttpServletRequest request) {
		
		String id = null;
		
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		if(login != null)id = login.getId();
		String jsp =null;
	
		String uri = request.getRequestURI();
		
		try {
			
			switch (uri) {
			case "/ajax/checkId.do":
				System.out.println("아이디 중복 체크");
				id = request.getParameter("id");
				
				id = (String) Execute.execute(MemberInit.get(uri), id);
				
				request.setAttribute("id", id);
				
				// jsp 정보 
				jsp = "member/checkId";
				break;
				
			case "/ajax/checkEmail.do":
				System.out.println("이메일 체크");
				
				String email = request.getParameter("email");
				email = (String) Execute.execute(MemberInit.get(uri), email);
				Execute.execute(MemberInit.get(uri), email);
				
				request.setAttribute("email", email);
				
				// jsp 정보 
				jsp = "member/checkEmail";
				break;
				
			case "/ajax/checkTel.do":
				System.out.println("이메일 체크");
				
				String tel = request.getParameter("tel");
				tel = (String) Execute.execute(MemberInit.get(uri), tel);
				Execute.execute(MemberInit.get(uri), tel);
				
				request.setAttribute("tel", tel);
				
				// jsp 정보 
				jsp = "member/checkTel";
				break;
				
			case "/ajax/getNewMsgCnt.do":
				System.out.println("새로운 매세지 카운트");
				
				Long result = (Long) Execute.execute(MemberInit.get(uri), id);
				request.setAttribute("newMsgCnt", result);
				// jsp 정보 
				jsp = "member/newMsgCnt";
				break;
				
				
			default:
				jsp="error/noModule_404";
				break;
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
		return jsp;
		
	}
	
}
