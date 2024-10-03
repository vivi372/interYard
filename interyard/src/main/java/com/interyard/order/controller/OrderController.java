package com.interyard.order.controller;



import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.interyard.basket.init.BasketInit;
import com.interyard.basket.list.BasketList;
import com.interyard.basket.vo.BasketVO;
import com.interyard.member.vo.LoginVO;
import com.interyard.order.init.OrderInit;
import com.interyard.order.list.OrderList;
import com.interyard.order.vo.OrderVO;
import com.interyard.util.exe.Execute;
import com.webjjang.util.page.PageObject;

/**
 * 주문에 관련 된 처리를 하는 클래스
 */
public class OrderController {
	/**
	 * 주문에 관련 된 처리를 하는 메서드
	 */
	public String execute(HttpServletRequest request) {
		System.out.println("OrderController 실행");
		String jsp = null;
		HttpSession session = request.getSession();	
		
		//로그인에서 id 가져오기
		LoginVO login = (LoginVO) session.getAttribute("login");
		String id = null;
		if(login != null) {
			id = login.getId();
		}

		// 메뉴 입력
		String uri = request.getRequestURI();		
		
		
		try { // 정상 처리		
			PageObject pageObject = PageObject.getInstance(request);
			// 메뉴 처리 : CRUD DB 처리 - Controller - Service - DAO
			switch (uri) {
			case "/order/list.do":
				// [OrderController] - (Execute) - OrderListService - OrderDAO.list()
				System.out.println("1.주문 리스트");
				
				//id를 pageObject에 저장		
				pageObject.setAccepter(id);
				// DB에서 데이터 가져오기 - 가져온 데이터는 OrderList			
				OrderList list = (OrderList) Execute.execute(OrderInit.get(uri), pageObject);
				//옵션 정보와 주문 정보 분리
				OrderList optList = list.splitList();			
				
				//System.out.println(list);
				//System.out.println(optList);
				
				//리퀘스트에 데이터 저장
				request.setAttribute("list", list);	
				request.setAttribute("optList", optList);	
				request.setAttribute("pageObject", pageObject);
				jsp = "order/list";
				break;	
			case "/order/adminList.do":
				// [OrderController] - (Execute) - OrderAdminListService - OrderDAO.adminList()
				System.out.println("1.주문 관리");
				
				pageObject = PageObject.getInstance(request);
				//데이터 정렬 시킬 기준 데이터를 pageObject period에 저장
				pageObject.setPeriod(request.getParameter("optList"));
				// DB에서 데이터 가져오기 - 가져온 데이터는 OrderList			
				list = (OrderList) Execute.execute(OrderInit.get(uri), pageObject);
				
				
				//리퀘스트에 데이터 저장
				request.setAttribute("list", list);	
					
				request.setAttribute("pageObject", pageObject);
				jsp = "order/adminList";
				break;	
				
			case "/order/view.do":
				// [OrderController] - (Execute) - OrderViewService - OrderDAO.view()
				System.out.println("2.주문 상세보기");
				
				pageObject = PageObject.getInstance(request);					
				
				
				long orderNo = Long.parseLong(request.getParameter("orderNo"));
				
				
				OrderVO vo = new OrderVO();
				
				vo.setOrderNo(orderNo);
				
				vo.setId(id);
				
				// DB에서 데이터 가져오기 - 가져온 데이터는 OrderList			
				list = (OrderList) Execute.execute(OrderInit.get(uri), vo);
				//옵션 정보와 주문 정보 분리
				optList = list.splitList();
				//jsp에서 리스트 첫번째 vo에 있는 주소 정보만 사용
				//리스트 첫번째 vo 꺼내기
				vo = list.get(0);
				
				
				//System.out.println(vo);
				//System.out.println(optList);
				//리퀘스트에 데이터 저장
				request.setAttribute("vo", vo);	
				request.setAttribute("optList", optList);	
				
				jsp = "order/view";
				break;	
				
			case "/order/writeForm.do":
				System.out.println("3.주문 등록 폼");				
				
				// 데이터 수집 - 상품 정보				
				String[] goodsNos = request.getParameterValues("goodsNo");
				String[] orderPrices = request.getParameterValues("orderPrice");
				String[] dlvyCharges = request.getParameterValues("dlvyCharge");			
				String[] orders = request.getParameterValues("order");
				String[] basketNos = request.getParameterValues("basketNo");
				String[] hopeDates = request.getParameterValues("hopeDate");
				// 데이터 수집 -  옵션 정보			
				String[] optNos = request.getParameterValues("optNo"); 
				String[] optNames = request.getParameterValues("optName"); 
				String[] amounts = request.getParameterValues("amount"); 				
				String[] lefOrders = request.getParameterValues("lefOrder");
				
				long totalCheckPrice=0;
				long totalCheckDlvyCharge=0;
				//상품 이름, 상품 이미지를 DB에서 가져온다.
				list = (OrderList) Execute.execute(OrderInit.get(uri), goodsNos);
				//db에서 가져온 데이터 list에 request에 가져온 데이터를 집어 넣는다.
				//가져온 데이터에서 상품 가격와 배송비는 더해 총 가격과 총 배송비를 구한다.
				for(int i=0;i<goodsNos.length;i++) {
					//가져온 장바구니의 상품이 동일할때 처리
					if(list.size()<=i) {
						vo = new OrderVO();
						vo.setGoodsTitle(list.get(i-1).getGoodsTitle());
						vo.setGoodsImage(list.get(i-1).getGoodsImage());
						vo.setOrderPrice(Long.parseLong(orderPrices[i]));
						totalCheckPrice += vo.getOrderPrice();
						vo.setDlvyCharge(Long.parseLong(dlvyCharges[i]));
						totalCheckDlvyCharge += vo.getDlvyCharge();
						vo.setOrder(Long.parseLong(orders[i]));
						vo.setBasketNo(Long.parseLong(basketNos[i]));
						if(hopeDates != null) {
							vo.setHopeDate(hopeDates[i]);
						}
						list.add(vo);
					} else {
						list.get(i).setOrderPrice(Long.parseLong(orderPrices[i]));
						totalCheckPrice += list.get(i).getOrderPrice();
						list.get(i).setDlvyCharge(Long.parseLong(dlvyCharges[i]));
						totalCheckDlvyCharge += list.get(i).getDlvyCharge();
						list.get(i).setOrder(Long.parseLong(orders[i]));
						list.get(i).setBasketNo(Long.parseLong(basketNos[i]));
						if(hopeDates != null) {
							list.get(i).setHopeDate(hopeDates[i]);
						}
					}
					
				}
				//옵션 리스트도 따로 저장한다.
				optList = new OrderList();
				for(int i=0;i<optNos.length;i++) {
					vo = new OrderVO();
					vo.setOptNo(Long.parseLong(optNos[i]));
					vo.setOptName(optNames[i]);
					vo.setAmount(Long.parseLong(amounts[i]));
					vo.setLefOrder(Long.parseLong(lefOrders[i]));
					
					optList.add(vo);
				}
				
				//db에서 
				if(list.size()==1) {
					list.get(0).setMultiOrder(false);
				} else {
					list.get(0).setMultiOrder(true);
				}
				//총 가격과 배송비 리스트 첫번째 vo에 저장
				list.get(0).setTotalCheckPrice(totalCheckPrice);				
				list.get(0).setTotalCheckDlvyCharge(totalCheckDlvyCharge);				
				
				//System.out.println("list- "+list);
				//System.out.println("optList- "+optList);
				//리퀘스트에 데이터 저장
				request.setAttribute("list", list);
				request.setAttribute("optList", optList);
				
				
				jsp = "order/writeForm";
				
				break;
			case "/order/write.do":
				System.out.println("3-2. 주문 등록");				
				
				String dlvyName = request.getParameter("dlvyName");
				String recipient = request.getParameter("recipient");
				String tel = request.getParameter("tel");
				String addr = request.getParameter("addr");
				
				long postNo = Long.parseLong(request.getParameter("postNo"));
				
				
				String[] dlvyMemos = request.getParameterValues("dlvyMemo");				
				
				// 데이터 수집 - 상품 정보				
				goodsNos = request.getParameterValues("goodsNo");
				orderPrices = request.getParameterValues("orderPrice");
				dlvyCharges = request.getParameterValues("dlvyCharge");			
				orders = request.getParameterValues("order");	
				hopeDates = request.getParameterValues("hopeDate");
				String[] categoryNos = request.getParameterValues("categoryNo");
				// 데이터 수집 -  옵션 정보			
				optNos = request.getParameterValues("optNo"); 				
				amounts = request.getParameterValues("amount"); 				
				lefOrders = request.getParameterValues("lefOrder");
				
				String payWay = request.getParameter("payWay");
				String payDetail = "";
				//입력된 결제 방식에 따라 입력되는 payDetail 변화
				switch (payWay) {
				case "핸드폰":
					payDetail = request.getParameter("tel");
					break;
				case "계좌":	
					payDetail = request.getParameter("accountCom");
					break;
				case "카드":
					//카드 회사 0000-0000-0000-0000 입력
					String cardCom = request.getParameter("cardCom");
					String[] cards = request.getParameterValues("card");
					payDetail += cardCom+" ";
					for(int i=0;i<cards.length;i++) {						
						if(i==0) payDetail += cards[i];
						else payDetail += "-"+cards[i];
					}
					break;				
				}
				list = new OrderList();				
				
				//주문 정보 저장
				for(int i=0;i<goodsNos.length;i++) {					
					vo = new OrderVO();
					
					
					vo.setDlvyMemo((dlvyMemos.length>1)? dlvyMemos[i]: dlvyMemos[0]);
					vo.setDlvyName(dlvyName);
					vo.setRecipient(recipient);
					vo.setTel(tel);
					vo.setAddr(addr);					
					vo.setPostNo(postNo);
					vo.setGoodsNo(Long.parseLong(goodsNos[i]));
					vo.setOrderPrice(Long.parseLong(orderPrices[i]));
					vo.setDlvyCharge(Long.parseLong(dlvyCharges[i]));
					vo.setOrder(Long.parseLong(orders[i]));
					vo.setPayWay(payWay);
					vo.setPayDetail(payDetail);	
					vo.setId(id);
					vo.setHopeDate(hopeDates[i]);
					vo.setCategoryNo(Long.parseLong(categoryNos[i]));
					
					list.add(vo);				
					
				}
				//옵션 정보 저장
				OrderList optTempList = new OrderList();
				for(int i=0;i<optNos.length;i++) {
					vo = new OrderVO();
					
					vo.setOptNo(Long.parseLong(optNos[i]));
					vo.setAmount(Long.parseLong(amounts[i]));
					vo.setLefOrder(Long.parseLong(lefOrders[i]));
					
					optTempList.add(vo);
				}
				//주문 정보 위에 옵션 정보 리스트 스택으로 올리기
				list.addAll(optTempList);						
				
				
				
				// [OrderController] - OrderWriteService - OrdertDAO.write()
				Execute.execute(OrderInit.get(uri), list);
				
				//장바구니 삭제 - 주문한 장바구니 번호 받기
				basketNos = request.getParameterValues("basketNo");				
				
				//orderList에 장바구니 번호들 담기
				list = new OrderList();
				
				for(int i=0;i<basketNos.length;i++) {
					vo = new OrderVO();
					vo.setBasketNo(Long.parseLong(basketNos[i]));
					list.add(vo);
				}
				
				//OrderList 데이터 BasketList로 옮기기
				BasketList basketList = new BasketList();
				
				for(OrderVO temp:list) {
					BasketVO basketVO = new BasketVO();
					//삭제시 아이디도 필요하기 때문에 아이디도 같이 저장
					basketVO.setId(id);
					basketVO.setBasketNo(temp.getBasketNo());
					basketList.add(basketVO);
				}
				
				// DB 처리 - 해당 번호의 장바구니들 삭제
				Execute.execute(BasketInit.get("/basket/delete.do"), basketList);
				
				jsp = "redirect:/order/list.do";
				session.setAttribute("msg", "성공적으로 결제되었습니다.");
				break;
			case "/order/stateUpdate.do":
				System.out.println("4. 주문 상태 수정");
				
				// 데이터 수집		
				orderNo = Long.parseLong(request.getParameter("orderNo"));			
				String orderState = request.getParameter("orderState");	
				String cancleReason = request.getParameter("cancleReason");
				//주문 상태를 list,view,adminList에서 수정하기 때문에 돌아올 주소를 저장하기 위해 이전 uri를 받는다.
				String beforeUri = request.getParameter("beforeUri");
				//adminList에서 id를 따로 입력받기 때문에 따로 수집한다.
				if(request.getParameter("id") != null && !request.getParameter("id").equals("")) {
					id = request.getParameter("id");
				}
				
				
				vo = new OrderVO();
				
				vo.setOrderNo(orderNo);
				vo.setOrderState(orderState);
				vo.setCancleReason(cancleReason);
				vo.setId(id);
				
				// [OrderController] - OrderStateUpdateService - OrderDAO.stateUpdate()
				Execute.execute(OrderInit.get(uri), vo);
				//request에서 가져온 이전 uri로 이동 시킨다.
				//adminList에서는 optList 파라메터가 따로 존재하기 때문에 추가로 붙힌다.
				jsp = "redirect:"+beforeUri+((request.getParameter("optList")!=null)?"&optList="+URLEncoder.encode(request.getParameter("optList"),"utf-8"):"");
				session.setAttribute("msg", "주문 상태가 성공적으로 수정되었습니다.");
				break;
			case "/order/dlvyUpdate.do":
				System.out.println("5. 주문 주소 수정");
				
				// 데이터 수집	- 수정할 주문 번호, 바꿀 배송지 번호	
				orderNo = Long.parseLong(request.getParameter("orderNo"));
				dlvyName = request.getParameter("dlvyName");
				recipient = request.getParameter("recipient");
				tel = request.getParameter("tel");
				addr = request.getParameter("addr");			
				postNo = Long.parseLong(request.getParameter("postNo"));
				
				
				vo = new OrderVO();
				
				vo.setOrderNo(orderNo);
				vo.setDlvyName(dlvyName);
				vo.setRecipient(recipient);
				vo.setTel(tel);
				vo.setAddr(addr);
				vo.setPostNo(postNo);
				//확인을 위한 id
				vo.setId(id);
				
				// [OrderController] - OrderDlvyUpdateService - OrderDAO.stateUpdate()
				Execute.execute(OrderInit.get(uri), vo);
				
				jsp = "redirect:/order/view.do?orderNo="+orderNo+"&"+pageObject.getPageQuery();
				session.setAttribute("msg", "주문 주소가 성공적으로 수정되었습니다.");
				break;

			case "/order/delete.do":
				System.out.println("6.주문 삭제");
				// 데이터 수집 - DB에서 실행에 필요한 데이터 - 주문 번호들
				String[] strOrderNos = request.getParameterValues("orderNo");
				//String[] 타입을 long[]으로 형변환
				long[] orderNos = new long[strOrderNos.length];
				for(int i=0;i<strOrderNos.length;i++) {
					orderNos[i] = Long.parseLong(strOrderNos[i]);
				}
				// DB 처리 - 해당 번호에 해당하는 주문 삭제
				Execute.execute(OrderInit.get(uri), orderNos);
				
				jsp = "redirect:/order/adminList.do?"+pageObject.getPageQuery()+"&optList="+URLEncoder.encode(request.getParameter("optList"),"utf-8");
				session.setAttribute("msg","주문이 성공적으로 삭제되었습니다.");
				break;
			case "/order/allUpdate.do":
				System.out.println("7.일괄 변경");
				// 데이터 수집 - DB에서 실행에 필요한 데이터 - 주문 번호들,바꿀 상태
				strOrderNos = request.getParameterValues("orderNo");					
				orderState = request.getParameter("orderState");
				list= new OrderList();
				for(int i=0;i<strOrderNos.length;i++) {
					vo = new OrderVO();
					vo.setOrderState(orderState);
					vo.setOrderNo(Long.parseLong(strOrderNos[i]));
					list.add(vo);
				}
				// DB 처리 - 해당 번호의 주문 상태 일괄 수정
				Execute.execute(OrderInit.get(uri), list);
				
				jsp = "redirect:/order/adminList.do?"+pageObject.getPageQuery()+"&optList="+URLEncoder.encode(request.getParameter("optList"),"utf-8");
				session.setAttribute("msg","주문이 성공적으로 삭제되었습니다.");
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
