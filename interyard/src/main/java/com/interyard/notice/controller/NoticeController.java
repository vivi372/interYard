package com.interyard.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.interyard.member.vo.LoginVO;
import com.interyard.notice.init.NoticeInit;
import com.interyard.notice.vo.NoticeVO;
import com.interyard.util.exe.Execute;
import com.interyard.util.multi.MultiPartUtil;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webjjang.util.page.PageObject;

public class NoticeController {
	public String execute(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		int gradeNo = 0;
		
		LoginVO login = (LoginVO) session.getAttribute("login");
		
		if (login != null) {
			gradeNo =login.getGradeNo();
		}
		
		
		String uri = request.getRequestURI();
		
		String jsp = null;
		
		Object result = null;
		
		Long no = 0L;
		
		String savePath = "upload/image";
		// 파일 시스템에서는 절대 저장 위치가 필요하다. - 파일 업로드 시 필요.
		
		try {
			
			MultiPartUtil multi = new MultiPartUtil(request, savePath);
			
			switch (uri) {
			case "/notice/list.do":
				// [NoticeController] - (Execute) - NoticeListService - NoticeDAO.list()
				System.out.println("NoticeController.list uri");
				
				PageObject pageObject = PageObject.getInstance(request);
				
				String period = request.getParameter("period");
				
				if (gradeNo == 9) {
					if (period == null || period == "") {
						pageObject.setPeriod("all");
					}
					else {
						pageObject.setPeriod(period);
					}
				}
				else {
					pageObject.setPeriod("pre");
					
				}
				// DB에서 데이터 가져오기 - 가져온 데이터는 List<NoticeVO>
				result = Execute.execute(NoticeInit.get(uri), pageObject);
				
				request.setAttribute("list", result);
				
				request.setAttribute("pageObject", pageObject);
				
				jsp = "notice/list";
				break;
				
			case "/notice/view.do":
				
				System.out.println("NoticeController.view uri");
				
				String strNo = request.getParameter("no");
				no = Long.parseLong(strNo);
			
				result = Execute.execute(NoticeInit.get(uri), no);
				
				request.setAttribute("vo", result);
				
				jsp = "notice/view";
				
				break;
				
			case "/notice/writeForm.do":
				System.out.println("NoticeController.writeForm uri");
				
				jsp="notice/writeForm";
				break;
				
			case "/notice/write.do":
				System.out.println("NoticeController.write uri");

				multi.initMultipartRequest();
				
				// 데이터 수집 - 사용자 : 제목, 내용, 작성자, 비밀번호
				String title = multi.getParameter("title");
				String content = multi.getParameter("content");
				String image = multi.getFilesystemName("image");
				String startDate = multi.getParameter("startDate");
				String endDate = multi.getParameter("endDate");
				
				// 변수 - vo 저장하고 Service
				NoticeVO vo = new NoticeVO();
				vo.setTitle(title);
				vo.setContent(content);
				vo.setImage(image);
				vo.setStartDate(startDate);
				vo.setEndDate(endDate);
				
				// [NoticeController] - NoticeWriteService - NoticeDAO.write(vo)
				Execute.execute(NoticeInit.get(uri), vo);
				
				jsp ="redirect:list.do";
				
				break;
				
				
			case "/notice/updateForm.do":
				System.out.println("NoticeController.updateForm uri");
				
				no = Long.parseLong(request.getParameter("no"));

				result = Execute.execute(NoticeInit.get("/notice/view.do"), no);
				
				request.setAttribute("vo", result);
				
				jsp="notice/updateForm";
				break;
				
			case "/notice/update.do":
				System.out.println("NoticeController.updateForm uri");
				
				multi.initMultipartRequest();
				
				// 수정할 글번호를 받는다. - 데이터 수집
				no = Long.parseLong(multi.getParameter("no"));
				title = multi.getParameter("title");
				content = multi.getParameter("content");
				image = multi.getFilesystemName("image");
				startDate = multi.getParameter("startDate");
				endDate = multi.getParameter("endDate");
				
				// 변수 - vo 저장하고 Service
				vo = new NoticeVO();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setImage(image);
				vo.setStartDate(startDate);
				vo.setEndDate(endDate);
				
				// 수정할 데이터 가져오기 - 글보기 - NoticeViewService
				// DB 에 데이터 수정하기 - NoticeUpdateService
				result = Execute.execute(NoticeInit.get(uri), vo);
				
				pageObject = PageObject.getInstance(request);
				
				session.setAttribute("msg", "공지가 수정되었습니다.");
				
				jsp = "redirect:list.do?no=" + no 
					+ "&" + pageObject.getPageQuery();
				break;
				
			case "/notice/delete.do":
				System.out.println("5.공지사항 글삭제");
				// 데이터 수집 - DB에서 실행에 필요한 데이터 - 글번호
				no = Long.parseLong(request.getParameter("no"));
				
				// DB 처리
				result =Execute.execute(NoticeInit.get(uri), no);
				
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
			
			request.setAttribute("e", e); // e(예외)를 jsp에 보내서 표시한다.
			
			jsp = "error/500";
		} // end of try~catch
		
		return jsp;
		
	}
}
