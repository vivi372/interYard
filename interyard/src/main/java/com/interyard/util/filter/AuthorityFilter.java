package com.interyard.util.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.interyard.member.vo.LoginVO;


/**
 * Servlet Filter implementation class AuthorityFilter
 */
//@WebFilter("/AuthorityFilter")
public class AuthorityFilter extends HttpFilter implements Filter { 
	private static final long serialVersionUID = 1L;
	
	//uri에 따른 권한 저장 Map
	private static Map<String, Integer> authMap = new HashMap<>(); 	
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		System.out.println("AuthorityFilter.doFilter()");	
		
		HttpServletRequest req = (HttpServletRequest) request;
 		HttpServletResponse res = (HttpServletResponse) response;
 		HttpSession session = req.getSession();
 		
 		String uri = req.getRequestURI();
 		System.out.println("doFilter()" + uri);
 		
 		session.setAttribute("uri", uri);
 		Integer pageGradeNo = authMap.get(uri);
 		LoginVO loginVO = null;
 		
 		if(pageGradeNo != null) {
 			loginVO = (LoginVO) session.getAttribute("login");
 			if(loginVO == null) {
 				session.setAttribute("msg", "로그인이 필요합니다. 로그인을 해주세요");
 				
 				res.sendRedirect("/member/loginForm.do");
 				return;
 				
 			}
 			Integer userGradeNo = loginVO.getGradeNo();
 			if(pageGradeNo > 1) {
 				System.out.println("관리자 권한 체크");
 				if(pageGradeNo > userGradeNo) {
 					System.out.println("권한체크 - 부적합");
 					req.getRequestDispatcher("/WEB-INF/views/error/authority.jsp").forward(req, res);
 					return;
 				}
 			}
 			// 권한 처리끝
 		}
 		
		// pass the request along the filter chain - 실제적으로 실행되는 곳으로 이동
		chain.doFilter(request, response);
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//서버가 시작될 때 딱 한번 실행되는 메서드
		System.out.println("AuthorityFilter.init()");
		// qna 권한 처리
		authMap.put("/qna/qWriteForm.do", 1);
		authMap.put("/qna/updateForm.do", 1);
		authMap.put("/qna/delete.do", 1);
		
		authMap.put("/qna/aWriteForm.do", 9);
		// review 
		authMap.put("/review/write.do", 1);
		authMap.put("/review/reviewForm.do", 1);
		
		authMap.put("/review/delete.do", 9);
		
		// member 권한처리
		authMap.put("/member/list.do", 9);
		
		authMap.put("/member/view.do", 1);
		authMap.put("/member/updateForm.do", 1);
		
		// 공지사항, 이벤트 권한처리
		authMap.put("/notice/writeForm.do", 9);
		authMap.put("/notice/updateForm.do", 9);
		authMap.put("/notice/delete.do", 9);
		
		authMap.put("/event/writeForm.do", 9);
		authMap.put("/event/updateForm.do", 9);
		authMap.put("/event/delete.do", 9);
		
		//장바구니 권한 처리
		authMap.put("/basket/list.do", 1);
		authMap.put("/basket/write.do", 1);
		authMap.put("/basket/update.do", 1);
		authMap.put("/basket/delete.do", 1);
		//배송지 주문 권한 처리
		authMap.put("/dlvy/list.do", 1);
		authMap.put("/dlvy/writeForm.do", 1);
		authMap.put("/dlvy/write.do", 1);
		authMap.put("/dlvy/updateForm.do", 1);
		authMap.put("/dlvy/update.do", 1);
		authMap.put("/dlvy/delete.do", 1);
		
		authMap.put("/order/list.do", 1);
		authMap.put("/order/view.do", 1);
		authMap.put("/order/writeForm.do", 1);
		authMap.put("/order/stateUpdate.do", 1);
		authMap.put("/order/dlvyUpdate.do", 1);
		authMap.put("/order/adminList.do", 9);
		authMap.put("/order/delete.do", 9);
		authMap.put("/order/allUpdate.do", 9);
		//메세지 권한 처리
		authMap.put("/message/list.do", 1);
		authMap.put("/message/view.do", 1);
		authMap.put("/message/writeForm.do", 1);
		authMap.put("/message/write.do", 1);
		authMap.put("/message/delete.do", 1);
		authMap.put("/message/view.do", 1);
		
		//댓글 권한 처리
		authMap.put("/reply/write.do", 1);
		authMap.put("/reply/update.do", 1);
		authMap.put("/reply/delete.do", 1);
		
		// 중고장터 권한 처리
		authMap.put("/usedgoods/writeForm.do", 1);
		authMap.put("/usedgoods/updateForm.do", 1);
		
		
	}

}
