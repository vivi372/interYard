package com.interyard.reply.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.interyard.member.vo.LoginVO;
import com.interyard.reply.init.ReplyInit;
import com.interyard.reply.vo.ReplyVO;
import com.interyard.util.exe.Execute;
import com.webjjang.util.page.ReplyPageObject;

public class ReplyController{
public String execute(HttpServletRequest request) {
	System.out.println("ReplyController.execute()--------------");
	
	HttpSession session = request.getSession();
	
    LoginVO loginVO = (LoginVO) session.getAttribute("login");
    String id = loginVO.getId();
    System.out.println("id: " + id);

	String uri = request.getRequestURI();
	Object result = null;
	String jsp = null;
	
	try {
		ReplyPageObject pageObject
			= ReplyPageObject.getInstance(request);
		
		switch (uri) {
		
			case "/reply/write.do":
				System.out.println("댓글등록");
				
				// 데이터 수집(사용자->서버 : form - input - name)
				String content = request.getParameter("content");
				
				// 변수 - vo 저장하고 Service
				ReplyVO vo = new ReplyVO();
				vo.setUgno(pageObject.getNo());
				vo.setContent(content);
				vo.setId(id);
				
				Execute.execute(ReplyInit.get(uri), vo);
				
				jsp = "redirect:/usedgoods/view.do?ugno=" + pageObject.getNo()
	            + "&" + pageObject.getPageObject().getPageQuery()
	            + "&" + pageObject.getPageQuery() + "&no=" + pageObject.getNo();
				session.setAttribute("msg", "댓글 등록이 성공적으로 되었습니다.");
				break;
				
				
			case "/reply/update.do":
				System.out.println("댓글 수정");
				
				// 데이터 수집(사용자->서버 : form - input - name)
				Long rno = Long.parseLong(request.getParameter("rno"));
				content = request.getParameter("content");
				
				// 변수 - vo 저장하고 Service
				vo = new ReplyVO();
				vo.setRno(rno);
				vo.setContent(content);
				
				// DB 적용하는 처리문 작성. BoardUpdateservice
				Execute.execute(ReplyInit.get(uri), vo);
				
					// 글보기로 자동 이동 -> jsp 정보를 작성해서 넘긴다.
				jsp = "redirect:/usedgoods/view.do?ugno=" + pageObject.getNo() + "&inc=0"
						// 일반게시판의 페이지 & 검색 정보 붙이기
						+ "&" + pageObject.getPageObject().getPageQuery()
						+ "&no=" + pageObject.getNo();
				session.setAttribute("msg", "댓글이 수정되었습니다.");
				break;				
			
				
			case "/reply/delete.do":
				System.out.println("3.댓글 삭제");
				// 데이터 수집 - DB에서 실행에 필요한 데이터 - 글번호, 비밀번호 - BoardVO
				rno = Long.parseLong(request.getParameter("rno"));
				ReplyVO deleteVO = new ReplyVO();
				deleteVO.setRno(rno);
				
				// DB 처리
				Execute.execute(ReplyInit.get(uri), deleteVO);
				System.out.println();
				System.out.println("***************************");
				System.out.println("**  " + deleteVO.getRno()+ "글이 삭제되었습니다.  **");
				System.out.println("***************************");
				
				// 글보기로 자동 이동 -> jsp 정보를 작성해서 넘긴다.
				jsp = "redirect:/usedgoods/view.do?ugno=" + pageObject.getNo()
                + "&" + pageObject.getPageObject().getPageQuery() + "&no=" + pageObject.getNo()
                + "&" + pageObject.getPageObject().getPageQuery();
				session.setAttribute("msg", "댓글이 삭제되었습니다.");
				break;
			case "0":		
			
		}
	}catch (Exception e) {
		e.printStackTrace();
		System.out.println();
		System.out.println("$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@");
		System.out.println("$%@ << 오류 출력 >>                         $%@");
		System.out.println("$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@");
		System.out.println("$%@ 타입 : " + e.getClass().getSimpleName());
		System.out.println("$%@ 내용 : " + e.getMessage());
		System.out.println("$%@ 조치 : 데이터를 확인 후 다시 실행해 보세요.");
		System.out.println("$%@     : 계속 오류가 나면 전산담당자에게 연락하세요.");
	}
	return jsp;
}
}