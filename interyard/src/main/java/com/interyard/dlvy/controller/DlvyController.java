package com.interyard.dlvy.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.interyard.dlvy.vo.DlvyVO;
import com.interyard.member.vo.LoginVO;
import com.interyard.order.init.OrderInit;
import com.interyard.util.exe.Execute;




/**
 * 배송지 관련 백엔드 처리를 담당하는 컨트롤러
 */
public class DlvyController {
	
	public String execute(HttpServletRequest request) {
		String jsp = null;
		
		String uri = request.getRequestURI();
		//아이디를 가져오기 위해 session 가져오기
		HttpSession session = request.getSession();
		
		LoginVO login = (LoginVO) session.getAttribute("login");
		String id = null;
		if(login != null) {
			//배송지를 위한 id 가져오기
			id = login.getId();
		}

		
		try {
			// 메뉴 처리 : CRUD DB 처리 - Controller - Service - DAO
			switch (uri) {
			case "/dlvy/list.do":
				// [DlvyController] - (Execute) - DlvyListService - DlvyDAO.list()
				System.out.println("1.배송지 리스트");
				// DB에서 데이터 가져오기 - 가져온 데이터는 List<DlvyVO>	
				@SuppressWarnings("unchecked") 
				List<DlvyVO> list = (List<DlvyVO>) Execute.execute(OrderInit.get(uri), id);
				
				//가져온 주소를 / 기준으로 주소와 상세 주소로 나눈다.
				if(list != null) {				
					for(DlvyVO vo:list) {
						String addr = vo.getAddr();
						vo.setAddrDetail(addr.substring(addr.indexOf('/')+1));
						vo.setAddr(addr.substring(0,addr.indexOf('/')-1));
						
					}
				}
				request.setAttribute("list", list);
				
				jsp = "dlvy/list";
				break;
				
			case "/dlvy/writeForm.do":				
				System.out.println("2-1.배송지 등록 폼");
				jsp = "dlvy/writeForm";
				break;
			
			case "/dlvy/write.do":				
				System.out.println("2-2.배송지 등록");
				//배송지 데이터 수집
				long postNo = Long.parseLong(request.getParameter("postNo"));
				String addr = request.getParameter("addr")+" /"+request.getParameter("addrDetail");
				String dlvyName = request.getParameter("dlvyName");
				String recipient = request.getParameter("recipient");
				String tel = request.getParameter("tel");
				//기본 배송지가 입력 안 됐을때 0으로 되면 1으로 저장
				long basic = (request.getParameter("basic")==null)?0:1;
				
				DlvyVO vo = new DlvyVO();
				//vo에 가져온 데이터 저장
				vo.setPostNo(postNo);
				vo.setAddr(addr);
				vo.setId(id);
				vo.setDlvyName(dlvyName);
				vo.setRecipient(recipient);
				vo.setTel(tel);
				vo.setBasic(basic);
				// [DlvyController] - (Execute) - DlvyWriteService - DlvyDAO.write()
				Execute.execute(OrderInit.get(uri), vo);
				//등록 배송지 리스트로 이동
				jsp = "redirect:/dlvy/list.do";
				break;
			case "/dlvy/updateForm.do":				
				System.out.println("3. 배송지 수정 폼");
				//데이터 수집
				long dlvyAddrNo = Long.parseLong(request.getParameter("dlvyAddrNo"));
				// [DlvyController] - (Execute) - DlvyUpdateService - DlvyDAO.update()
				vo = (DlvyVO) Execute.execute(OrderInit.get(uri), dlvyAddrNo);
				
				if(vo != null) {					
					addr = vo.getAddr();
					vo.setAddrDetail(addr.substring(addr.indexOf('/')+1));
					vo.setAddr(addr.substring(0,addr.indexOf('/')-1));
					System.out.println(vo.getAddr()+" "+vo.getAddrDetail());					
				}
				
				request.setAttribute("vo", vo);
				
				
				jsp = "dlvy/updateForm";
				break;
				
			case "/dlvy/update.do":				
				System.out.println("4. 배송지 수정");
				//데이터 수집
				dlvyAddrNo = Long.parseLong(request.getParameter("dlvyAddrNo"));
				postNo = Long.parseLong(request.getParameter("postNo"));
				addr = request.getParameter("addr")+" /"+request.getParameter("addrDetail");
				dlvyName = request.getParameter("dlvyName");
				recipient = request.getParameter("recipient");
				tel = request.getParameter("tel");
				basic = (request.getParameter("basic")==null)?0:1;
				//vo에 가져온 데이터 저장
				vo = new DlvyVO();
				
				vo.setDlvyAddrNo(dlvyAddrNo);
				vo.setPostNo(postNo);
				vo.setAddr(addr);
				vo.setId(id);
				vo.setDlvyName(dlvyName);
				vo.setRecipient(recipient);
				vo.setTel(tel);
				vo.setBasic(basic);
				
				Execute.execute(OrderInit.get(uri), vo);
				
				jsp = "redirect:/dlvy/list.do";
				break;
				
			case "/dlvy/delete.do":				
				//데이터 수집
				dlvyAddrNo = Long.parseLong(request.getParameter("dlvyAddrNo"));
				
				vo = new DlvyVO();
				
				vo.setDlvyAddrNo(dlvyAddrNo);
				vo.setId(id);				
				// [DlvyController] - (Execute) - DlvyWriteService - DlvyDAO.write()
				Execute.execute(OrderInit.get(uri), vo);
				
				jsp = "redirect:/dlvy/list.do";
				break;

			default:
				break;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsp;
	}
}
