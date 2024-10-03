package com.interyard.review.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.interyard.member.vo.LoginVO;
import com.interyard.order.init.OrderInit;
import com.interyard.order.vo.OrderVO;
import com.interyard.review.init.ReviewInit;
import com.interyard.review.vo.ReviewVO;
import com.interyard.util.exe.Execute;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReviewController {

	public String execute (HttpServletRequest request, HttpServletResponse response) {
		
		// 결과 저장 변수
		String jsp = null;
		String uri = null;
		Object result = null;
		
		// 로그인 정보
		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO) session.getAttribute("login");
		
		String id = null;
		if (loginVO != null) id = loginVO.getId();
		
		// 파일 업로드 설정 --------------------------------------
		// 파일의 상대적인 저장 위치
		String savePath = "/upload/image";
		// 파일 시스템에서는 절대 저장위치가 필요하다. - 파일 업로드 시 필요
		String realSavePath = request.getServletContext().getRealPath(savePath);
		
		// 업로드 파일 용량 제한(100mb)
		int sizeLimit = 100 * 1024 * 1024;
		
		
		// 주문 정보
		Long orderNo = 0L;
		Long goodsNo = 0L;
		
		// uri 받기
		uri = request.getRequestURI();
		
		try {
			
			switch (uri) {
			case "/review/list.do" :
				System.out.println("a. 리뷰 리스트 출력 ---------------");
				
				// 상품 정보
				String strGoodsNo = request.getParameter("goodsNo");
				if (strGoodsNo != null && !strGoodsNo.equals("")) {
					goodsNo = Long.parseLong(strGoodsNo);
				}
				
				// 리뷰 정보
				result = Execute.execute(ReviewInit.get(uri), goodsNo);
				request.setAttribute("list", result);
				
				// 리뷰 개수 & 평균
				Object totalReview = Execute.execute(ReviewInit.get("/review/getTotalReivew.do"), goodsNo);
				request.setAttribute("totalReview", totalReview);
				
				// 리뷰별 갯수
				Object gradeCnt = Execute.execute(ReviewInit.get("/review/gradeCnt.do"), goodsNo);
				request.setAttribute("gradeCnt", gradeCnt);
				

				
				jsp = "review/list";
				
				break;
				
			case "/review/reviewForm.do" :
				System.out.println("b. 리뷰 쓰기 폼 이동 ---------------");
				
				orderNo = Long.parseLong(request.getParameter("orderNo"));
				goodsNo = Long.parseLong(request.getParameter("goodsNo"));
				
				// 주문 정보와 상품 정보 (new Long[] {orderNo, goodsNo}
				result = Execute.execute(ReviewInit.get(uri), new Long[] {orderNo, goodsNo});
				request.setAttribute("vo", result);
				
				jsp = "review/reviewForm";
				
				break;
				
			case "/review/write.do" :
				System.out.println("c. 리뷰 쓰기 처리 ---------------");
				
				MultipartRequest multi = 
						new MultipartRequest(request, realSavePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
				
				orderNo = Long.parseLong(multi.getParameter("orderNo"));
				goodsNo = Long.parseLong(multi.getParameter("goodsNo"));
				float grade = Float.parseFloat(multi.getParameter("grade"));
				String title = multi.getParameter("title");
				String content = multi.getParameter("content");
				String imgFile = multi.getFilesystemName("imgFile");
				
				ReviewVO vo = new ReviewVO();
				vo.setOrderNo(orderNo);
				vo.setGoodsNo(goodsNo);
				vo.setId(id);
				vo.setGrade(grade);
				vo.setTitle(title);
				vo.setContent(content);
				if (imgFile != null && !imgFile.equals("")) {
					vo.setImgFile(savePath + "/" + imgFile);
				}
				
				// 주문 정보와 상품 정보 (new Long[] {orderNo, goodsNo}
				result = Execute.execute(ReviewInit.get(uri), vo);
				request.setAttribute("vo", result);
				
				// ajax를 이용한 폼 제출 확인
				response.getWriter().print(result);
				
				// oders 리뷰작성 변경
				OrderVO orderVO = new OrderVO();
				orderVO.setOrderNo(orderNo);
				orderVO.setId(id);
				orderVO.setOrderState("구매 확인(리뷰작성)");
				
				Execute.execute(OrderInit.get("/order/stateUpdate.do"), orderVO );
				
				jsp = "return";
				
				session.setAttribute("msg", "리뷰가 정상적으로 등록되었습니다. <br><br> 리뷰를 등록해 주셔서 감사합니다.");
				
				break;
				
			case "/review/delete.do" :
				System.out.println("d. 리뷰 삭제 처리 ---------------");
				
				ReviewVO deleteVO = new ReviewVO();
				Long revNo = Long.parseLong(request.getParameter("revNo"));
				goodsNo = Long.parseLong(request.getParameter("goodsNo"));
				orderNo = Long.parseLong(request.getParameter("orderNo"));
				
				deleteVO.setRevNo(revNo);
				deleteVO.setId(id);
				
				// DB 처리
				result = (int) Execute.execute(ReviewInit.get(uri),deleteVO);

				// 파일 삭제
				// 삭제할 파일 이름
				String deleteFileName = request.getParameter("imgFile");
				if(deleteFileName != null) {
					File deleteFile = new File(request.getServletContext().getRealPath(deleteFileName));
					
					// 파일 삭제 처리
					if(deleteFile.exists()) deleteFile.delete();
				}
				
				if((int) result == 1) {
					session.setAttribute("msg", "리뷰 삭제가 성공적으로 처리 되었습니다.");

					// oders 리뷰작성 변경
					orderVO = new OrderVO();
					orderVO.setOrderNo(orderNo);
					orderVO.setId(id);
					orderVO.setOrderState("구매 확인");
					
					Execute.execute(OrderInit.get("/order/stateUpdate.do"), orderVO );
					
				} else {
					session.setAttribute("msg", "리뷰 삭제가 처리되지 않았습니다. [리뷰 번호가 다릅니다.]");
				}
				
				jsp = "redirect:/goods/view.do?goodsNo=" + goodsNo;
				
				
				break;
	
			default:
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsp;
	}
	
}
