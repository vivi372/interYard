package com.interyard.basket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.interyard.basket.init.BasketInit;
import com.interyard.basket.list.BasketList;
import com.interyard.basket.vo.BasketVO;
import com.interyard.basket.vo.OptVO;
import com.interyard.member.vo.LoginVO;
import com.interyard.util.exe.Execute;
import com.webjjang.util.page.PageObject;


/**
 * 장바구니 관련 백엔드 처리를 담당하는 컨트롤러
 */
public class BasketController {
	public String execute(HttpServletRequest request) {
		String jsp = null;
		//실행 결과를 담기위해 session 가져오기
		HttpSession session = request.getSession();	
		
		//아이디를 가져오기 위해 session에서 login 가져오기
		LoginVO login = (LoginVO) session.getAttribute("login");
		String id = null;
		if(login != null) {
			//장바구니 표시를 위한 id 가져오기
			id = login.getId();
		}


		// 메뉴 입력
		String uri = request.getRequestURI();				
		
		try { // 정상 처리		
			// 메뉴 처리 : CRUD DB 처리 - Controller - Service - DAO
			switch (uri) {
			case "/basket/list.do":
				// [BasketController] - (Execute) - BasketListService - BasketDAO.list()
				System.out.println("1.장바구니 리스트");
			
				// DB에서 데이터 가져오기 - 가져온 데이터는 BasketList			
				BasketList list = (BasketList) Execute.execute(BasketInit.get(uri),id);	
				//리스트 장바구니 옵션 정보가 담겨있는 리스트와 장바구니 정보가 담겨있는 리스트로 분리한다.
				List<OptVO> optList = list.splitList();			
				
				//DB에서 아무런 데이터를 가져오지 못 했다면 담지 않는다.
				if(list.get(0).getBasketNo() != 0) {
					request.setAttribute("list", list);	
					request.setAttribute("optList", optList);	
				}
				
				jsp = "basket/list";
				//자동 삭제된 장바구니 수량을 가져와 msg로 출력한다.
				int deleteItem = list.get(0).getDeleteItem();
				if(deleteItem>0) {
					session.setAttribute("msg", "판매중이 아닌 물품이 담긴 "+deleteItem+"건의 장바구니가 삭제되었습니다.");
				}
				break;//			
			case "/basket/write.do":
				System.out.println("2.장바구니 등록");
				
				// 데이터 수집 - basket table			
				long goodsNo = Long.parseLong(request.getParameter("goodsNo"));
				long orderPrice = Long.parseLong(request.getParameter("orderPrice"));
				long dlvyCharge = Long.parseLong(request.getParameter("dlvyCharge"));
				long categoryNo = Long.parseLong(request.getParameter("categoryNo"));
				String hopeDate = request.getParameter("hopeDate");
				// 데이터 수집 -  baketOpt tables				
				String[] optNos = request.getParameterValues("optNo"); 
				String[] amounts = request.getParameterValues("amount"); 
				
				list = new BasketList();
				
				//가져온 데이터를 BasketList에 담는다.
				for(int i=0;i<optNos.length;i++) {
					BasketVO vo = new BasketVO();
					vo.setId(id);
					vo.setGoodsNo(goodsNo);
					vo.setOrderPrice(orderPrice);
					vo.setDlvyCharge(dlvyCharge);
					vo.setOptNo(Long.parseLong(optNos[i]));
					vo.setAmount(Long.parseLong(amounts[i]));
					vo.setHopeDate(hopeDate);
					list.add(vo);
				}
				
				// [BasketController] - BasketWriteService - BasketDAO.write()
				Execute.execute(BasketInit.get(uri), list);
				PageObject pageObject = PageObject.getInstance(request);
				//장바구니에 등록된 상품의 상세보기로 보낸다.
				if(categoryNo>=3000) {
					//티켓
					jsp = "redirect:/goods/ticketView.do?goodsNo="+goodsNo+"&"+pageObject.getPageQuery();
				} else if(categoryNo<3000 && categoryNo>=2000) {
					//일반 상품
					jsp = "redirect:/goods/shopView.do?goodsNo="+goodsNo+"&"+pageObject.getPageQuery();
				} else {
					//책
					jsp = "redirect:/goods/view.do?goodsNo="+goodsNo+"&"+pageObject.getPageQuery();
				}
				
				//장바구니가 등록되었는지 상품 상세보기에서 확인하기 위해 session에 값을 저장해 보낸다.
				session.setAttribute("basketWriteComplete", true);
				
				break;
			case "/basket/update.do":
				System.out.println("3. 장바구니 옵션 수정");
				
				// 데이터 수집 - basket table			
				long basketNo = Long.parseLong(request.getParameter("basketNo"));
				orderPrice = Long.parseLong(request.getParameter("orderPrice"));	
				hopeDate = request.getParameter("hopeDate");	
				 
				// 데이터 수집 -  baketOpt tables				
				optNos = request.getParameterValues("optNo"); 
				amounts = request.getParameterValues("amount"); 
				
				list = new BasketList();
				//가져온 데이터를 BasketList에 담는다.
				for(int i=0;i<optNos.length;i++) {
					BasketVO vo = new BasketVO();
					vo.setId(id);
					vo.setBasketNo(basketNo);
					vo.setOrderPrice(orderPrice);					
					vo.setHopeDate(hopeDate);					
					vo.setOptNo(Long.parseLong(optNos[i]));
					vo.setAmount(Long.parseLong(amounts[i]));
					list.add(vo);
				}
				
				// [BasketController] - BasketUpdateService - BasketDAO.update()
				Execute.execute(BasketInit.get(uri), list);
				
				//수정후 장바구니 리스트로 보낸다.
				jsp = "redirect:/basket/list.do";
				//msg로 완료 후 메시지를 출력한다.
				session.setAttribute("msg", "장바구니가 성공적으로 수정되었습니다.");
				break;

			case "/basket/delete.do":
				System.out.println("4.장바구니 삭제");
				// 데이터 수집 - DB에서 실행에 필요한 데이터 - 장바구니 번호들
				String[] strBasketNos = request.getParameterValues("basketNo");
				list = new BasketList();
				
				//여러 번호를 보내기위해 long 타입으로 변환후 리스트에 담는다.
				for(int i=0;i<strBasketNos.length;i++) {
					BasketVO vo = new BasketVO();
					vo.setBasketNo(Long.parseLong(strBasketNos[i]));
					list.add(vo);
				}
				//본인만 삭제 시키기 위해 id도 담는다.
				list.get(0).setId(id);
				// DB 처리
				Execute.execute(BasketInit.get(uri), list);
				
				//삭제후 장바구니 리스트로 보낸다.
				jsp = "redirect:/basket/list.do";
				//msg로 완료 후 메시지를 출력한다.
				session.setAttribute("msg",strBasketNos.length+"건의 상품을 장바구니에서 삭제했습니다.");
				break;
			

			default:
				jsp = "/error/404";
				break;
			} // end of switch
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("e", e);
			jsp="/error/500";
		} // end of try~catch
		return jsp;
	} // end of execute()
}
