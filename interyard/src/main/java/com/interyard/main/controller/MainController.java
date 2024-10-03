package com.interyard.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.webjjang.util.page.PageObject;
import com.webjjang.util.page.ReplyPageObject;
import com.interyard.event.init.EventInit;
import com.interyard.goods.Init.GoodsInit;
import com.interyard.member.vo.LoginVO;
import com.interyard.notice.init.NoticeInit;
import com.interyard.qna.init.QnaInit;
import com.interyard.qna.vo.PageObjectQna;
import com.interyard.reply.init.ReplyInit;
import com.interyard.usedgoods.init.UsedGoodsInit;
import com.interyard.util.exe.Execute;

// Main Module 에 맞는 메뉴 선택 , 데이터 수집(기능별), 예외 처리
public class MainController {

	public String execute(HttpServletRequest request) {
		System.out.println("MainController.execute() --------------------------");
		// uri
		String uri = request.getRequestURI();

		Object result = null;

		String jsp = null;

		HttpSession session = request.getSession();
		int gradeNo = 0;
		LoginVO login = (LoginVO) session.getAttribute("login");
		if (login != null) {
			gradeNo = login.getGradeNo();
		}

		try { // 정상 처리

			// 메뉴 처리 : CRUD DB 처리 - Controller - Service - DAO
			switch (uri) {
			case "/main/main.do":
				System.out.println("1.메인처리");

				// 페이지 처리를 위한 객체 생성
				PageObject pageObject = new PageObject();

				if (gradeNo == 9) {
					pageObject.setPeriod("pre");
				} else {
					pageObject.setPeriod("pre");

				}
				// 메인에 표시할 데이터 - 공지사항 / 일반 게시판 / 이미지
				// DB에서 데이터 가져오기
				// 공지사항
				pageObject.setPerPageNum(4);

				result = Execute.execute(EventInit.get("/event/booklist.do"), pageObject);
				request.setAttribute("booklist", result);

				result = Execute.execute(EventInit.get("/event/shoplist.do"), pageObject);
				request.setAttribute("shoplist", result);

				result = Execute.execute(EventInit.get("/event/ticketlist.do"), pageObject);
				request.setAttribute("ticketlist", result);

				// DB에서 데이터 가져오기
				// 일반 게시판
				pageObject.setPerPageNum(6);
				result = Execute.execute(GoodsInit.get("/goods/shopList.do"), pageObject);
				request.setAttribute("goodslist", result);
				
				result = Execute.execute(UsedGoodsInit.get("/usedgoods/list.do"), pageObject);
				request.setAttribute("usedgoodslist", result);

				PageObjectQna pageQnaObject = (PageObjectQna) PageObjectQna.getInstance(request);
				result = Execute.execute(QnaInit.get("/qna/faqList.do"), pageQnaObject);
				request.setAttribute("qnalist", result);
				
				result = Execute.execute(NoticeInit.get("/notice/list.do"), pageQnaObject);
				request.setAttribute("noticelist", result);

				// 이미지 게시판
				pageObject.setPerPageNum(6);

				// /WEB-INF/views/ + board/list + .jsp
				jsp = "main/main";
				break;

			default:
				jsp = "error/404";
				break;
			} // end of switch
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();

			// 예외객체를 jsp에서 사용하기 위해 request에 담는다.
			request.setAttribute("e", e);

			jsp = "error/500";
		} // end of try~catch
		return jsp;
	} // end of execute()

} // end of class
