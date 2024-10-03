package com.interyard.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.interyard.member.vo.LoginVO;
import com.interyard.qna.init.QnaInit;
import com.interyard.qna.vo.PageObjectQna;
import com.interyard.qna.vo.QnaVO;
import com.interyard.util.exe.Execute;

public class QnaController {
	
	public String execute (HttpServletRequest request) {
		
		// 결과 저장 변수 선언
		String jsp = null;
		String uri = null;
		Object result = null;
		
		// 로그인 정보
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute("login");
		
		String id = null;
		if (loginVO != null) id = loginVO.getId();
		
		// uri 받기
		uri = request.getRequestURI();
		
		try {
			switch (uri) {
			case "/qna/list.do":
				System.out.println("a. QnA 게시판 리스트");
				
				PageObjectQna pageObject = (PageObjectQna) PageObjectQna.getInstance(request);
				
				result = Execute.execute(QnaInit.get(uri), pageObject);
				System.out.println("QnAController.execute().pageObjcet : " + pageObject);
				
				Object FaqResult = Execute.execute(QnaInit.get("/qna/faqList.do"), pageObject);
				
				request.setAttribute("list", result);
				request.setAttribute("faqList", FaqResult);
				request.setAttribute("pageObject", pageObject);
				
				jsp = "qna/list";
				
				break;
			case "/qna/view.do":
				System.out.println("b. QnA 게시판 상세보기");
				
				// 데이터 수집
				Long no = Long.parseLong(request.getParameter("qnaNo"));
				Long inc = Long.parseLong(request.getParameter("inc"));
				String strRnum = request.getParameter("rnum");
				
				result = Execute.execute(QnaInit.get(uri), new Long[] {no, inc});
				request.setAttribute("qvo", result);
				
				Object answerResult = Execute.execute(QnaInit.get("/qna/answerList.do"), no);
				request.setAttribute("avo", answerResult);
				
				// 페이지 객체 - 수정, 리스트로 가기에서 페이지 정보가 필요
				pageObject = (PageObjectQna) PageObjectQna.getInstance(request);
				request.setAttribute("pageObject", pageObject);
				
				if(strRnum != null && strRnum.equals("")) {
					Long rnum = Long.parseLong(strRnum);
					Object rnumResult = Execute.execute(QnaInit.get("/qna/rnumList.do"), rnum);
					request.setAttribute("rnumList", rnumResult);
				}
				
				
				jsp = "qna/view";
				
				break;
				
			case "/qna/qWriteForm.do":
				System.out.println("c-1. QnA 게시판 질문글 등록");
				
				
				jsp = "qna/qWriteForm";
				
				break;
				
			case "/qna/qWrite.do":
				System.out.println("c-2. QnA 게시판 질문글 등록 처리");
				
				String ctg = request.getParameter("ctg");
				String semiCtg = request.getParameter("semiCtg");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				QnaVO vo = new QnaVO();
				vo.setCtg(ctg);
				vo.setSemiCtg(semiCtg);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setId(id);
				
				result = Execute.execute(QnaInit.get(uri), vo);
				
				if ((int) result == 0) {
					session.setAttribute("msg", "질문 등록을 실패했습니다. <br><br> 다시 시도해 주세요.");
				} else {
					session.setAttribute("msg", "질문 글이 등록 되었습니다.");
				}
				
				
				jsp = "redirect:list.do?perPageNum=" + request.getParameter("perPageNum");
				
				break;
			
			case "/qna/aWriteForm.do":
				System.out.println("c-1. QnA 게시판 답변글 등록");
				
				no = Long.parseLong(request.getParameter("qnaNo"));
				// 질문글 보기
				result = Execute.execute(QnaInit.get("/qna/view.do"), new Long[] {no, 0L});
				request.setAttribute("qvo", result);
				
				jsp = "qna/aWriteForm";
				
				break;
				
			case "/qna/aWrite.do":
				System.out.println("c-2. QnA 게시판 답변글 등록 처리");
				
				// 데이터 수집
				ctg = request.getParameter("ctg");
				semiCtg = request.getParameter("semiCtg");
				title = request.getParameter("title");
				content = request.getParameter("content");
				no = Long.parseLong(request.getParameter("qnaNo"));
				String strRefNo = request.getParameter("refNo");
				Long ordNo = Long.parseLong(request.getParameter("ordNo"));
				Long levNo = Long.parseLong(request.getParameter("levNo"));
				String strParentNo = request.getParameter("qnaNo");
				
				// 답변 데이터 저장
				vo = new QnaVO();
				vo.setCtg(ctg);
				vo.setSemiCtg(semiCtg);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setId(id);
				vo.setQnaNo(no);
				vo.setOrdNo(ordNo +1 );
				vo.setLevNo(levNo);
				
				if (strRefNo != null && !strRefNo.equals("")) {
					vo.setRefNo(Long.parseLong(strRefNo));
					vo.setParentNo(Long.parseLong(strParentNo));
				}
				
				result = Execute.execute(QnaInit.get(uri), vo);
				
				if ((int) result == 0) {
					session.setAttribute("msg", "답변 등록을 실패했습니다. <br><br> 다시 시도해 주세요.");
				} else {
					session.setAttribute("msg", "답변 글이 등록 되었습니다.");
				}
				
				
				jsp = "redirect:view.do?qnaNo=" + no + "&inc=0" + "&perPageNum=" + request.getParameter("perPageNum");
				
				break;
				
			case "/qna/updateForm.do":
				System.out.println("d-1. QnA 게시판 질문글 수정폼");
				
				no = Long.parseLong(request.getParameter("qnaNo"));
				// 질문글 보기
				result = Execute.execute(QnaInit.get("/qna/view.do"), new Long[] {no, 0L});
				request.setAttribute("qvo", result);
				
				jsp = "qna/updateForm";
				
				break;
				
			case "/qna/update.do":
				System.out.println("c-2. QnA 게시판 수정 처리");
				
				
				// 데이터 수집
				ctg = request.getParameter("ctg");
				semiCtg = request.getParameter("semiCtg");
				title = request.getParameter("title");
				content = request.getParameter("content");
				no = Long.parseLong(request.getParameter("qnaNo"));
				Long refNo = Long.parseLong(request.getParameter("refNo"));
				
				// 답변 데이터 저장
				vo = new QnaVO();
				vo.setCtg(ctg);
				vo.setSemiCtg(semiCtg);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setQnaNo(no);
				
				result = Execute.execute(QnaInit.get(uri), vo);
				
				// 페이지 정보 받기 & uri에 붙이기
				pageObject = (PageObjectQna) PageObjectQna.getInstance(request);
				System.out.println(pageObject);
				
				if ((int) result == 0) {
					session.setAttribute("msg", "글 수정을 실패했습니다. <br><br> 다시 시도해 주세요.");
				} else {
					session.setAttribute("msg", "글 수정이 정상 처리 되었습니다.");
				}
				
				jsp = "redirect:view.do?qnaNo=" + refNo + "&inc=0&" + pageObject.getPageQuery(); 
				
				break;
				
			case "/qna/delete.do":
				System.out.println("e. QnA 글 삭제 처리");
				
				// 데이터 수집
				no = Long.parseLong(request.getParameter("qnaNo"));
				
				result = Execute.execute(QnaInit.get(uri), no);
			
				if( request.getParameter("perPageNum") != null) {
					jsp = "redirect:list.do" + "?perPageNum=" + request.getParameter("perPageNum");
				} else {
					jsp = "redirect:list.do";
				}
				
				if ((int) result == 0) {
					session.setAttribute("msg", "글 삭제에 실팼습니다. <br> 다시 시도해 주세요.");
				} else {
					session.setAttribute("msg", "글이 삭제 되었습니다.");
				}
				
				break;
	
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsp;
	}

}
