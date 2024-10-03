package com.interyard.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.interyard.event.init.EventInit;
import com.interyard.event.vo.EventVO;
import com.interyard.member.vo.LoginVO;
import com.interyard.util.exe.Execute;
import com.interyard.util.multi.MultiPartUtil;
import com.webjjang.util.page.PageObject;

public class EventController {
	public String execute(HttpServletRequest request) {

		HttpSession session = request.getSession();

		LoginVO login = (LoginVO) session.getAttribute("login");

		int gradeNo = 0;

		if (login != null) {
			gradeNo = login.getGradeNo();
		}

		String uri = request.getRequestURI();

		String jsp = null;

		Object result = null;

		Long no = 0L;

		String savePath = "upload/image";

		try {

			MultiPartUtil multi = new MultiPartUtil(request, savePath);

			switch (uri) {
			case "/event/list.do":
				
				System.out.println("EventController.list uri");
				
				PageObject pageObject = PageObject.getInstance(request);
				
				String period = request.getParameter("period");
				
				if (gradeNo == 9) {
					if (period == null || period == "") {
						pageObject.setPeriod("all");
					} else {
						pageObject.setPeriod(period);
					}
				} else {
					pageObject.setPeriod("pre");
					
				}
				
				result = Execute.execute(EventInit.get(uri), pageObject);
				
				request.setAttribute("list", result);
				
				request.setAttribute("pageObject", pageObject);
				
				jsp = "event/list";
				
				break;
				
			case "/event/booklist.do":

				System.out.println("EventController.booklist uri");

				pageObject = PageObject.getInstance(request);

				period = request.getParameter("period");

				if (gradeNo == 9) {
					if (period == null || period == "") {
						pageObject.setPeriod("all");
					} else {
						pageObject.setPeriod(period);
					}
				} else {
					pageObject.setPeriod("pre");

				}

				result = Execute.execute(EventInit.get(uri), pageObject);

				request.setAttribute("list", result);

				request.setAttribute("pageObject", pageObject);

				jsp = "event/booklist";

				break;

			case "/event/shoplist.do":

				System.out.println("EventController.shoplist uri");

				pageObject = PageObject.getInstance(request);

				period = request.getParameter("period");

				if (gradeNo == 9) {
					if (period == null || period == "") {
						pageObject.setPeriod("all");
					} else {
						pageObject.setPeriod(period);
					}
				} else {
					pageObject.setPeriod("pre");

				}

				result = Execute.execute(EventInit.get(uri), pageObject);

				request.setAttribute("list", result);
				
				request.setAttribute("pageObject", pageObject);

				jsp = "event/shoplist";

				break;

			case "/event/ticketlist.do":

				System.out.println("EventController.ticketlist uri");

				pageObject = PageObject.getInstance(request);

				period = request.getParameter("period");

				if (gradeNo == 9) {
					if (period == null || period == "") {
						pageObject.setPeriod("all");
					} else {
						pageObject.setPeriod(period);
					}
				} else {
					pageObject.setPeriod("pre");

				}

				result = Execute.execute(EventInit.get(uri), pageObject);

				request.setAttribute("list", result);
				
				request.setAttribute("pageObject", pageObject);

				jsp = "event/ticketlist";

				break;

			case "/event/view.do":

				System.out.println("EventController.view uri");

				String strNo = request.getParameter("no");
				no = Long.parseLong(strNo);
				String strInc = request.getParameter("inc");
				Long inc = Long.parseLong(strInc);
				
				System.out.println(request.getParameter("inc"));

				result = Execute.execute(EventInit.get(uri), new Long[]{no, inc});

				request.setAttribute("vo", result);
				
				jsp = "event/view";

				break;

			case "/event/writeForm.do":
				System.out.println("EventController.writeForm uri");

				jsp = "event/writeForm";
				break;

			case "/event/write.do":
				System.out.println("EventController.write uri");

				multi.initMultipartRequest();

				// 데이터 수집 - 사용자 : 제목, 내용, 작성자, 비밀번호
				String title = multi.getParameter("title");
				String content = multi.getParameter("content");
				String image = multi.getParameter("image");
				String startDate = multi.getParameter("startDate");
				String endDate = multi.getParameter("endDate");
				String categoryNo = multi.getParameter("categoryNo");

				// 변수 - vo 저장하고 Service
				EventVO vo = new EventVO();
				vo.setTitle(title);
				vo.setContent(content);
				vo.setImage(image);
				vo.setStartDate(startDate);
				vo.setEndDate(endDate);
				vo.setCategoryNo(Long.parseLong(categoryNo));

				// [NoticeController] - NoticeWriteService - NoticeDAO.write(vo)
				Execute.execute(EventInit.get(uri), vo);

				jsp = "redirect:list.do";

				break;

			case "/event/updateForm.do":
				System.out.println("EventController.updateForm uri");

				no = Long.parseLong(request.getParameter("no"));

				result = Execute.execute(EventInit.get("/event/view.do"), new Long[]{no, 0L});

				request.setAttribute("vo", result);

				jsp = "event/updateForm";
				break;

			case "/event/update.do":
				System.out.println("EventController.update uri");

				multi.initMultipartRequest();

				// 수정할 글번호를 받는다. - 데이터 수집
				no = Long.parseLong(multi.getParameter("no"));
				title = multi.getParameter("title");
				content = multi.getParameter("content");
				image = multi.getFilesystemName("image");
				startDate = multi.getParameter("startDate");
				endDate = multi.getParameter("endDate");
				categoryNo = multi.getParameter("categoryNo");

				// 변수 - vo 저장하고 Service
				vo = new EventVO();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setImage(image);
				vo.setStartDate(startDate);
				vo.setEndDate(endDate);
				vo.setCategoryNo(Long.parseLong(categoryNo));

				// 수정할 데이터 가져오기 - 글보기 - NoticeViewService
				// DB 에 데이터 수정하기 - NoticeUpdateService
				Execute.execute(EventInit.get(uri), vo);

				pageObject = PageObject.getInstance(request);

				session.setAttribute("msg", "이벤트가 수정되었습니다.");

				jsp = "redirect:list.do?no=" + no + "&" + pageObject.getPageQuery();
				break;

			case "/event/delete.do":
				System.out.println("EventController.delete uri");
				// 데이터 수집 - DB에서 실행에 필요한 데이터 - 글번호
				no = Long.parseLong(request.getParameter("no"));

				// DB 처리
				result = Execute.execute(EventInit.get(uri), no);

				session.setAttribute("msg", "공지가 삭제되었습니다.");

				jsp = "redirect:list.do";
				break;

			default:
				jsp = "error/404";
				break;
			} // end of switch

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return jsp;

	}
}
