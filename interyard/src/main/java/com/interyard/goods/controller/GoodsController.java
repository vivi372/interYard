package com.interyard.goods.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.interyard.goods.Init.GoodsInit;
import com.interyard.goods.vo.GoodsVO;
import com.interyard.member.vo.LoginVO;
import com.interyard.review.init.ReviewInit;
import com.interyard.util.exe.Execute;
import com.interyard.util.multi.MultiPartUtil;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webjjang.util.page.PageObject;

public class GoodsController {

	public String execute(HttpServletRequest request) {
		System.out.println("GoodsController.execute() --------------------------");
		// uri
		String uri = request.getRequestURI();

		Object result = null;

		String jsp = null;

		// 로그인 아이디 꺼내기
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute("login");

		String id = null;
		if (loginVO != null)
			id = loginVO.getId();

		// 파일 업로드 설정 ---------------------------------
		// 파일의 상대적인 저장 위치
		String savePath = "image";
		// 파일 시스템에서는 절대 저장 위치가 필요하다. - 파일 업로드 시 필요.
		// String realSavePath = request.getServletContext().getRealPath(savePath);
		// 업로드 파일 용량 제한
		// int sizeLimit = 100 * 1024 * 1024;

		// 입력 받는 데이터 선언
		Long goodsNo = 0L;

		try { // 정상 처리

			MultiPartUtil multi = new MultiPartUtil(request, savePath);

			// 메뉴 처리 : CRUD DB 처리 - Controller - Service - DAO
			switch (uri) {
			case "/goods/list.do":
				// [BoardController] - (Execute) - BoardListService - BoardDAO.list()
				System.out.println("1.상품게시판 리스트");
				// 페이지 처리를 위한 객체
				// getInstance - 기본 값이 있고 넘어오는 페이지와 검색 정보를 세팅 처리
				PageObject pageObject = PageObject.getInstance(request);

				// 이미지 게시판의 한페이지 이미지의 개수의 기본 값을 6으로 처리하자.
				// 중복처리를 하는 것이다. - 앞의 데이터에 덮어쓰기가 된다.
				String strPerPageNum = request.getParameter("perPageNum");
				if (strPerPageNum == null)
					pageObject.setPerPageNum(12);

				// DB에서 데이터 가져오기 - 가져온 데이터는 List<BoardVO>
				result = Execute.execute(GoodsInit.get(uri), pageObject);

				// pageObject 데이터 확인
				System.out.println("GoodsController.execute().pageObject = " + pageObject);
				// 가져온 데이터 request에 저장 -> jsp까지 전달된다.
				request.setAttribute("list", result);
				// pageObject 담기
				request.setAttribute("pageObject", pageObject);

				// 리뷰 개수 & 평균
				Object totalReview = Execute.execute(ReviewInit.get("/review/getTotalReivew.do"), goodsNo);
				request.setAttribute("totalReview", totalReview);

				// /WEB-INF/views/ + goods/list + .jsp
				jsp = "goods/list";
				break;
			case "/goods/shopList.do":
				// [BoardController] - (Execute) - BoardListService - BoardDAO.list()
				System.out.println("1.상품게시판 리스트-쇼핑");
				// 페이지 처리를 위한 객체
				// getInstance - 기본 값이 있고 넘어오는 페이지와 검색 정보를 세팅 처리
				pageObject = PageObject.getInstance(request);

				// 이미지 게시판의 한페이지 이미지의 개수의 기본 값을 12으로 처리하자.
				// 중복처리를 하는 것이다. - 앞의 데이터에 덮어쓰기가 된다.
				strPerPageNum = request.getParameter("perPageNum");
				if (strPerPageNum == null)
					pageObject.setPerPageNum(12);

				// DB에서 데이터 가져오기 - 가져온 데이터는 List<BoardVO>
				result = Execute.execute(GoodsInit.get(uri), pageObject);

				// pageObject 데이터 확인
				System.out.println("GoodsController.execute().pageObject = " + pageObject);
				// 가져온 데이터 request에 저장 -> jsp까지 전달된다.
				request.setAttribute("list", result);
				// pageObject 담기
				request.setAttribute("pageObject", pageObject);
				// /WEB-INF/views/ + goods/list + .jsp
				jsp = "goods/shopList";
				break;
			case "/goods/ticketList.do":
				// [BoardController] - (Execute) - BoardListService - BoardDAO.list()
				System.out.println("1.상품게시판 리스트-쇼핑");
				// 페이지 처리를 위한 객체
				// getInstance - 기본 값이 있고 넘어오는 페이지와 검색 정보를 세팅 처리
				pageObject = PageObject.getInstance(request);

				// 이미지 게시판의 한페이지 이미지의 개수의 기본 값을 12으로 처리하자.
				// 중복처리를 하는 것이다. - 앞의 데이터에 덮어쓰기가 된다.
				strPerPageNum = request.getParameter("perPageNum");
				if (strPerPageNum == null)
					pageObject.setPerPageNum(12);

				// DB에서 데이터 가져오기 - 가져온 데이터는 List<BoardVO>
				result = Execute.execute(GoodsInit.get(uri), pageObject);

				// pageObject 데이터 확인
				System.out.println("GoodsController.execute().pageObject = " + pageObject);
				// 가져온 데이터 request에 저장 -> jsp까지 전달된다.
				request.setAttribute("list", result);
				// pageObject 담기
				request.setAttribute("pageObject", pageObject);
				// /WEB-INF/views/ + goods/list + .jsp
				jsp = "goods/ticketList";
				break;
			case "/goods/view.do":
				System.out.println("2.상품 정보 보기");
				String strNo = request.getParameter("goodsNo");
				System.out.println("goodsNo 전달 완료 = " + goodsNo);
				goodsNo = Long.parseLong(strNo);
				System.out.println("goodsNo 전달 완료 = " + goodsNo);
				// 전달 데이터 - 글번호
				result = Execute.execute(GoodsInit.get(uri), goodsNo);
				System.out.println("view controller execute완료");
				// 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
				request.setAttribute("vo", result);
				System.out.println("view setAtt 완료");

				jsp = "goods/view";
				break;
			case "/goods/shopView.do":
				System.out.println("2.상품 정보 보기");
				strNo = request.getParameter("goodsNo");
				System.out.println("goodsNo 전달 완료 = " + goodsNo);
				goodsNo = Long.parseLong(strNo);
				System.out.println("goodsNo 전달 완료 = " + goodsNo);
				// 전달 데이터 - 글번호
				result = Execute.execute(GoodsInit.get(uri), goodsNo);
				System.out.println("view controller execute완료");
				// 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
				request.setAttribute("vo", result);
				System.out.println("view setAtt 완료");

				jsp = "goods/shopView";
				break;

			case "/goods/ticketView.do":
				System.out.println("2.상품 정보 보기");
				strNo = request.getParameter("goodsNo");
				System.out.println("goodsNo 전달 완료 = " + goodsNo);
				goodsNo = Long.parseLong(strNo);
				System.out.println("goodsNo 전달 완료 = " + goodsNo);
				// 전달 데이터 - 글번호
				result = Execute.execute(GoodsInit.get(uri), goodsNo);
				System.out.println("view controller execute완료");
				// 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
				request.setAttribute("vo", result);
				System.out.println("view setAtt 완료");

				jsp = "goods/ticketView";
				break;

			case "/goods/writeForm.do":
				System.out.println("3-1.상품 등록 폼-도서");
				jsp = "goods/writeForm";
				break;
			case "/goods/shopWriteForm.do":
				System.out.println("3-1.상품 등록 폼-쇼핑");
				jsp = "goods/shopWriteForm";
				break;
			case "/goods/ticketWriteForm.do":
				System.out.println("3-1.상품 등록 폼-예매");
				jsp = "goods/ticketWriteForm";
				break;
			case "/goods/optionWriteForm.do":
				System.out.println("3-1.상품 등록 폼-옵션");
				jsp = "goods/optionWriteForm";
				break;
			case "/goods/write.do":
				System.out.println("3.상품 등록 처리");

				multi.initMultipartRequest();

				// 일반 데이터 수집(사용자->서버 : form - input - name) : multi에서
				Long categoryNo = multi.getLongParameter("categoryNo");
				String goodsTitle = multi.getParameter("goodsTitle");
				String goodsSubTitle = multi.getParameter("goodsSubTitle");
				String goodsMaker = multi.getParameter("goodsMaker");
				String goodsTrans = multi.getParameter("goodsTrans");
				String goodsPublicher = multi.getParameter("goodsPublicher");
				Long goodsRunTime = multi.getLongParameter("goodsRunTime");
				goodsNo = multi.getLongParameter("goodsNo");
				String goodsRating = multi.getParameter("goodsRating");
				String goodsStatus = multi.getParameter("goodsStatus");
				Long goodsCost = multi.getLongParameter("goodsCost");
				Long goodsPrice = multi.getLongParameter("goodsPrice");
				String goodsStartDate = multi.getParameter("goodsStartDate");
				String goodsContent = multi.getParameter("goodsContent");
				String goodsImage = multi.getFilesystemName("goodsImage");
				// id는 session에서 받아야한다. -> 위에서 처리 함

				// 변수 - vo 저장하고 Service
				GoodsVO vo = new GoodsVO();
				vo.setCategoryNo(categoryNo);
				vo.setGoodsTitle(goodsTitle);
				vo.setGoodsSubTitle(goodsSubTitle);
				vo.setGoodsMaker(goodsMaker);
				vo.setGoodsTrans(goodsTrans);
				vo.setGoodsPublicher(goodsPublicher);
				vo.setGoodsRunTime(goodsRunTime);
				vo.setGoodsNo(goodsNo);
				vo.setGoodsRating(goodsRating);
				vo.setGoodsStatus(goodsStatus);
				vo.setGoodsCost(goodsCost);
				vo.setGoodsPrice(goodsPrice);
				vo.setGoodsStartDate(goodsStartDate);
				vo.setGoodsContent(goodsContent);
				vo.setGoodsImage(goodsImage);

				// [GoodsController] - GoodsWriteService - GoodsDAO.write(vo)
				Execute.execute(GoodsInit.get(uri), vo);

				// 메시지 보내기
				session.setAttribute("msg", "상품이 등록되었습니다.");

				// jsp 정보 앞에 "redirect:"가 붙어 있어 redirect를
				// 아니면 jsp로 forward로 시킨다.
				jsp = "redirect:list.do?perPageNum=" + multi.getParameter("perPageNum");

				break;
			case "/goods/shopWrite.do":
				System.out.println("3.상품 등록 처리-쇼핑");

				multi.initMultipartRequest();

				// 일반 데이터 수집(사용자->서버 : form - input - name) : multi에서
				categoryNo = multi.getLongParameter("categoryNo");
				goodsTitle = multi.getParameter("goodsTitle");
				goodsSubTitle = multi.getParameter("goodsSubTitle");
				goodsMaker = multi.getParameter("goodsMaker");
				goodsPublicher = multi.getParameter("goodsPublicher");
				goodsRunTime = multi.getLongParameter("goodsRunTime");
				goodsRating = multi.getParameter("goodsRating");
				goodsStatus = multi.getParameter("goodsStatus");
				goodsCost = multi.getLongParameter("goodsCost");
				goodsPrice = multi.getLongParameter("goodsPrice");
				goodsContent = multi.getParameter("goodsContent");
				goodsImage = multi.getFilesystemName("goodsImage");

				// id는 session에서 받아야한다. -> 위에서 처리 함

				// 변수 - vo 저장하고 Service
				vo = new GoodsVO();
				vo.setCategoryNo(categoryNo);
				vo.setGoodsTitle(goodsTitle);
				vo.setGoodsSubTitle(goodsSubTitle);
				vo.setGoodsMaker(goodsMaker);
				vo.setGoodsPublicher(goodsPublicher);
				vo.setGoodsRunTime(goodsRunTime);
				vo.setGoodsRating(goodsRating);
				vo.setGoodsStatus(goodsStatus);
				vo.setGoodsCost(goodsCost);
				vo.setGoodsPrice(goodsPrice);
				vo.setGoodsContent(goodsContent);
				vo.setGoodsImage(goodsImage);

				// [GoodsController] - GoodsWriteService - GoodsDAO.write(vo)
				Execute.execute(GoodsInit.get(uri), vo);

				// 메시지 보내기
				session.setAttribute("msg", "상품이 등록되었습니다.");

				// jsp 정보 앞에 "redirect:"가 붙어 있어 redirect를
				// 아니면 jsp로 forward로 시킨다.
				jsp = "redirect:shopList.do?perPageNum=" + multi.getParameter("perPageNum") + "&key=nt&word="
						+ categoryNo;

				session.setAttribute("optResult", 1);

				break;
			case "/goods/ticketWrite.do":
				System.out.println("3.상품 등록 처리-예매");

				multi.initMultipartRequest();

				// 일반 데이터 수집(사용자->서버 : form - input - name) : multi에서
				goodsImage = multi.getFilesystemName("goodsImage");
				categoryNo = multi.getLongParameter("categoryNo");
				goodsTitle = multi.getParameter("goodsTitle");
				goodsSubTitle = multi.getParameter("goodsSubTitle");
				goodsStatus = multi.getParameter("goodsStatus");
				goodsPublicher = multi.getParameter("goodsPublicher");
				goodsMaker = multi.getParameter("goodsMaker");
				goodsRunTime = multi.getLongParameter("goodsRunTime");
				goodsStartDate = multi.getParameter("goodsStartDate");
				String goodsEndDate = multi.getParameter("goodsEndDate");
				goodsRating = multi.getParameter("goodsRating");
				goodsCost = multi.getLongParameter("goodsCost");
				goodsContent = multi.getParameter("goodsContent");
				// 숨은 데이터 수집구간
				goodsPrice = multi.getLongParameter("goodsPrice");
				// id는 session에서 받아야한다. -> 위에서 처리 함

				// 변수 - vo 저장하고 Service
				vo = new GoodsVO();
				vo.setGoodsImage(goodsImage);
				vo.setCategoryNo(categoryNo);
				vo.setGoodsTitle(goodsTitle);
				vo.setGoodsSubTitle(goodsSubTitle);
				vo.setGoodsStatus(goodsStatus);
				vo.setGoodsPublicher(goodsPublicher);
				vo.setGoodsMaker(goodsMaker);
				vo.setGoodsRunTime(goodsRunTime);
				vo.setGoodsStartDate(goodsStartDate);
				vo.setGoodsEndDate(goodsEndDate);
				vo.setGoodsRating(goodsRating);
				vo.setGoodsCost(goodsCost);
				vo.setGoodsContent(goodsContent);
				// 숨은 데이터 구간
				vo.setGoodsPrice(goodsPrice);

				// [GoodsController] - GoodsWriteService - GoodsDAO.write(vo)
				Execute.execute(GoodsInit.get(uri), vo);

				// 메시지 보내기
				session.setAttribute("msg", "상품이 등록되었습니다.");

				// jsp 정보 앞에 "redirect:"가 붙어 있어 redirect를
				// 아니면 jsp로 forward로 시킨다.
				jsp = "redirect:shopList.do?perPageNum=" + multi.getParameter("perPageNum") + "&key=nt&word="
						+ categoryNo;

				session.setAttribute("optResult", 1);

				break;
			case "/goods/optionWrite.do":
				System.out.println("3.상품 등록 처리-옵션");

				multi.initMultipartRequest();

				// 일반 데이터 수집(사용자->서버 : form - input - name) : multi에서
				String optName = multi.getParameter("optName");
				Long optPrice = multi.getLongParameter("optPrice");
				goodsNo = multi.getLongParameter("goodsNo");
				// id는 session에서 받아야한다. -> 위에서 처리 함

				// 변수 - vo 저장하고 Service
				vo = new GoodsVO();
				vo.setOptName(optName);
				vo.setOptPrice(optPrice);
				vo.setGoodsNo(goodsNo);

				// [GoodsController] - GoodsWriteService - GoodsDAO.write(vo)
				Execute.execute(GoodsInit.get(uri), vo);

				// 메시지 보내기
				session.setAttribute("msg", "상품 옵션이 등록되었습니다.");

				// jsp 정보 앞에 "redirect:"가 붙어 있어 redirect를
				// 아니면 jsp로 forward로 시킨다.
				jsp = "redirect:shopView.do?goodsNo=" + goodsNo + "&perPageNum=" + multi.getParameter("perPageNum");

				break;
			case "/goods/updateForm.do":
				System.out.println("4-1.상품 수정 폼");

				// 사 -> 서버 : 글번호
				goodsNo = Long.parseLong(request.getParameter("goodsNo"));

				// no맞는 데이터 DB에서 가져온다. BoardViewService
				result = Execute.execute(GoodsInit.get("/goods/view.do"), goodsNo);
				// 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
				request.setAttribute("vo", result);

				// jsp 정보
				jsp = "goods/updateForm";

				break;
			case "/goods/shopUpdateForm.do":
				System.out.println("4-1.상품 수정 폼");

				// 사 -> 서버 : 글번호
				goodsNo = Long.parseLong(request.getParameter("goodsNo"));

				// no맞는 데이터 DB에서 가져온다. BoardViewService
				result = Execute.execute(GoodsInit.get("/goods/shopView.do"), goodsNo);
				// 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
				request.setAttribute("vo", result);

				// jsp 정보
				jsp = "goods/shopUpdateForm";

				break;
			case "/goods/ticketUpdateForm.do":
				System.out.println("4-1.상품 수정 폼 - 예매");

				// 사 -> 서버 : 글번호
				goodsNo = Long.parseLong(request.getParameter("goodsNo"));

				// no맞는 데이터 DB에서 가져온다. BoardViewService
				result = Execute.execute(GoodsInit.get("/goods/ticketView.do"), goodsNo);
				// 가져온 데이터를 JSP로 보내기 위해서 request에 담는다.
				request.setAttribute("vo", result);

				// jsp 정보
				jsp = "goods/ticketUpdateForm";

				break;
			case "/goods/update.do":
				System.out.println("4-2.상품 수정 처리");

				multi.initMultipartRequest();

				// 데이터 수집(사용자->서버 : form - input - name)
				categoryNo = multi.getLongParameter("categoryNo");
				goodsTitle = multi.getParameter("goodsTitle");
				goodsSubTitle = multi.getParameter("goodsSubTitle");
				goodsMaker = multi.getParameter("goodsMaker");
				goodsTrans = multi.getParameter("goodsTrans");
				goodsPublicher = multi.getParameter("goodsPublicher");
				goodsRunTime = multi.getLongParameter("goodsRunTime");
				goodsNo = multi.getLongParameter("goodsNo");
				goodsRating = multi.getParameter("goodsRating");
				goodsStatus = multi.getParameter("goodsStatus");
				goodsCost = multi.getLongParameter("goodsCost");
				goodsPrice = multi.getLongParameter("goodsPrice");
				goodsStartDate = multi.getParameter("goodsStartDate");
				goodsContent = multi.getParameter("goodsContent");
				goodsImage = multi.getFilesystemName("goodsImage");

				// 변수 - vo 저장하고 Service
				vo = new GoodsVO();
				vo.setCategoryNo(categoryNo);
				vo.setGoodsTitle(goodsTitle);
				vo.setGoodsSubTitle(goodsSubTitle);
				vo.setGoodsMaker(goodsMaker);
				vo.setGoodsTrans(goodsTrans);
				vo.setGoodsPublicher(goodsPublicher);
				vo.setGoodsRunTime(goodsRunTime);
				vo.setGoodsNo(goodsNo);
				vo.setGoodsRating(goodsRating);
				vo.setGoodsStatus(goodsStatus);
				vo.setGoodsCost(goodsCost);
				vo.setGoodsPrice(goodsPrice);
				vo.setGoodsStartDate(goodsStartDate);
				vo.setGoodsContent(goodsContent);
				vo.setGoodsImage(goodsImage);

				// DB 적용하는 처리문 작성. GoodsUpdateservice
				Execute.execute(GoodsInit.get(uri), vo);

				// 처리결과 메시지 처리
				session.setAttribute("msg", "상품정보가 수정되었습니다.");

				// 페이지 정보 받기 & uri에 붙이기
				pageObject = PageObject.getInstance(request);
				// 글보기로 자동 이동 -> jsp 정보를 작성해서 넘긴다.
				jsp = "redirect:view.do?goodsNo=" + goodsNo + "&" + pageObject.getPageQuery();
				break;
			case "/goods/shopUpdate.do":
				System.out.println("4-2.상품 수정 처리");

				multi.initMultipartRequest();

				// 데이터 수집(사용자->서버 : form - input - name)
				categoryNo = multi.getLongParameter("categoryNo");
				goodsTitle = multi.getParameter("goodsTitle");
				goodsSubTitle = multi.getParameter("goodsSubTitle");
				goodsMaker = multi.getParameter("goodsMaker");
				goodsTrans = multi.getParameter("goodsTrans");
				goodsPublicher = multi.getParameter("goodsPublicher");
				goodsRunTime = multi.getLongParameter("goodsRunTime");
				goodsNo = multi.getLongParameter("goodsNo");
				goodsRating = multi.getParameter("goodsRating");
				goodsStatus = multi.getParameter("goodsStatus");
				goodsCost = multi.getLongParameter("goodsCost");
				goodsPrice = multi.getLongParameter("goodsPrice");
				goodsStartDate = multi.getParameter("goodsStartDate");
				goodsContent = multi.getParameter("goodsContent");
				goodsImage = multi.getFilesystemName("goodsImage");

				// 변수 - vo 저장하고 Service
				vo = new GoodsVO();
				vo.setCategoryNo(categoryNo);
				vo.setGoodsTitle(goodsTitle);
				vo.setGoodsSubTitle(goodsSubTitle);
				vo.setGoodsMaker(goodsMaker);
				vo.setGoodsTrans(goodsTrans);
				vo.setGoodsPublicher(goodsPublicher);
				vo.setGoodsRunTime(goodsRunTime);
				vo.setGoodsNo(goodsNo);
				vo.setGoodsRating(goodsRating);
				vo.setGoodsStatus(goodsStatus);
				vo.setGoodsCost(goodsCost);
				vo.setGoodsPrice(goodsPrice);
				vo.setGoodsStartDate(goodsStartDate);
				vo.setGoodsContent(goodsContent);
				vo.setGoodsImage(goodsImage);

				// DB 적용하는 처리문 작성. GoodsUpdateservice
				Execute.execute(GoodsInit.get(uri), vo);

				// 처리결과 메시지 처리
				session.setAttribute("msg", "상품정보가 수정되었습니다.");

				// 페이지 정보 받기 & uri에 붙이기
				pageObject = PageObject.getInstance(request);
				// 글보기로 자동 이동 -> jsp 정보를 작성해서 넘긴다.
				jsp = "redirect:shopView.do?goodsNo=" + goodsNo + "&" + pageObject.getPageQuery();
				break;
			case "/goods/ticketUpdate.do":
				System.out.println("4-2.상품 수정 처리 - 예매");

				multi.initMultipartRequest();

				// 데이터 수집(사용자->서버 : form - input - name)
				goodsImage = multi.getFilesystemName("goodsImage");
				categoryNo = multi.getLongParameter("categoryNo");
				goodsTitle = multi.getParameter("goodsTitle");
				goodsSubTitle = multi.getParameter("goodsSubTitle");
				goodsStatus = multi.getParameter("goodsStatus");
				goodsPublicher = multi.getParameter("goodsPublicher");
				goodsMaker = multi.getParameter("goodsMaker");
				goodsRunTime = multi.getLongParameter("goodsRunTime");
				goodsStartDate = multi.getParameter("goodsStartDate");
				goodsEndDate = multi.getParameter("goodsEndDate");
				goodsRating = multi.getParameter("goodsRating");
				goodsCost = multi.getLongParameter("goodsCost");
				goodsContent = multi.getParameter("goodsContent");
				// 보이지 않거나 쓰이지 않는 데이터 수집
				goodsNo = multi.getLongParameter("goodsNo");
				goodsPrice = multi.getLongParameter("goodsPrice");

				// 변수 - vo 저장하고 Service
				vo = new GoodsVO();
				vo.setGoodsImage(goodsImage);
				vo.setCategoryNo(categoryNo);
				vo.setGoodsTitle(goodsTitle);
				vo.setGoodsSubTitle(goodsSubTitle);
				vo.setGoodsStatus(goodsStatus);
				vo.setGoodsPublicher(goodsPublicher);
				vo.setGoodsMaker(goodsMaker);
				vo.setGoodsRunTime(goodsRunTime);
				vo.setGoodsStartDate(goodsStartDate);
				vo.setGoodsEndDate(goodsEndDate);
				vo.setGoodsRating(goodsRating);
				vo.setGoodsCost(goodsCost);
				vo.setGoodsContent(goodsContent);
				// 보이지 않거나 쓰이지 않는 데이터 저장
				vo.setGoodsNo(goodsNo);
				vo.setGoodsPrice(goodsPrice);

				// DB 적용하는 처리문 작성. GoodsUpdateservice
				Execute.execute(GoodsInit.get(uri), vo);

				// 처리결과 메시지 처리
				session.setAttribute("msg", "상품정보가 수정되었습니다.");

				// 페이지 정보 받기 & uri에 붙이기
				pageObject = PageObject.getInstance(request);
				// 글보기로 자동 이동 -> jsp 정보를 작성해서 넘긴다.
				jsp = "redirect:ticketView.do?goodsNo=" + goodsNo + "&" + pageObject.getPageQuery();
				break;
			case "/goods/delete.do":
				System.out.println("5.상품 삭제");

				multi.deleteFile("goodsImage");

				// 데이터 수집 - DB에서 실행에 필요한 데이터 - 글번호, id - session
				goodsNo = Long.parseLong(request.getParameter("goodsNo"));
				categoryNo = Long.parseLong(request.getParameter("categoryNo"));

				vo = new GoodsVO();
				vo.setGoodsNo(goodsNo);

				// DB 처리
				Execute.execute(GoodsInit.get(uri), vo);

				// 메시지 처리
				session.setAttribute("msg", "상품 삭제가 완료되었습니다.");

				if (categoryNo >= 3000) {
					// 티켓
					jsp = "redirect:/goods/ticketList.do?perPageNum=" + request.getParameter("perPageNum");
				} else if (categoryNo < 3000 && categoryNo >= 2000) {
					// 일반 상품
					jsp = "redirect:/goods/shopList.do?perPageNum=" + request.getParameter("perPageNum");
				} else {
					// 책
					jsp = "redirect:/goods/list.do?perPageNum=" + request.getParameter("perPageNum");
				}

				break;

			default:
				System.out.println("####################################");
				;
				System.out.println("## 잘못된 메뉴를 선택하셨습니다.          ##");
				;
				System.out.println("## [0~5, 0] 중에서 입력하셔야 합니다.    ##");
				;
				System.out.println("####################################");
				;
				break;
			} // end of switch
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("<< 오류 출력 >>");
			System.out.println("타입 : " + e.getClass().getSimpleName());
			System.out.println("내용 : " + e.getMessage());
		} // end of try~catch
		return jsp;
	} // end of execute()

}
