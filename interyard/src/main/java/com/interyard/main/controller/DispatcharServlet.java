package com.interyard.main.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.interyard.ajax.controller.AjaxController;
import com.interyard.basket.controller.BasketController;
import com.interyard.basketopt.controller.BasketOptController;
import com.interyard.dlvy.controller.DlvyController;
import com.interyard.event.controller.EventController;
import com.interyard.goods.controller.GoodsController;
import com.interyard.member.controller.MemberController;
import com.interyard.message.controller.MessageController;
import com.interyard.notice.controller.NoticeController;
import com.interyard.order.controller.OrderController;
import com.interyard.qna.controller.QnaController;
import com.interyard.reply.controller.ReplyController;
import com.interyard.review.controller.ReviewController;
import com.interyard.usedgoods.controller.UsedGoodsController;

/**
 * Servlet implementation class DispatcharServlet
 */
//웹서버와 연결하기 위해서 servlet 으로 등록이 되어 있어야 한다.
//1. @WebServlet - 프로그램 수정 가능, 2. web.xml에 등록 - 프로그램 수정 불가능
//@WebServlet(urlPatterns = "*.do", loadOnStartup = 1)
//Servlet을 상속 - 기능 : URL 연결 - 서버에서 동작 프로그램 - 한번만 생성(싱글톤 프로그램)
public class DispatcharServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	private final MainController mainController = new MainController();
	private final QnaController qnaController = new QnaController();
	private final MessageController messageController = new MessageController();
	private final BasketController basketController =  new BasketController();
	private final MemberController memberController =  new MemberController();	
	private final UsedGoodsController usedGoodsController = new UsedGoodsController();
	private final BasketOptController basketOptController = new BasketOptController();
	private final NoticeController noticeController = new NoticeController();	
	private final AjaxController ajaxController =  new AjaxController();
	private final GoodsController goodsController = new GoodsController();
	private final DlvyController dlvyController = new DlvyController();
	private final OrderController orderController = new OrderController();
	private final ReviewController reviewController = new ReviewController();
	private final ReplyController replyController = new ReplyController();
	private final EventController eventController = new EventController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcharServlet() {    	
        super();                
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		//드라이버 확인 / 객체 생성 처리 - Class.forName(class명)
		//서버가 실행될 때 먼저 실행되어야만 한다.
		System.out.println("DispatcharServlet 실행 -- 초기화 진행");
		
		try {			
			//init 확인
			Class.forName("com.interyard.qna.init.QnaInit");
			Class.forName("com.interyard.message.init.MessageInit");
			Class.forName("com.interyard.basket.init.BasketInit");
			Class.forName("com.interyard.order.init.OrderInit");
			Class.forName("com.interyard.member.Init.MemberInit");
			Class.forName("com.interyard.usedgoods.init.UsedGoodsInit");
			Class.forName("com.interyard.notice.init.NoticeInit");
			Class.forName("com.interyard.goods.Init.GoodsInit");
			Class.forName("com.interyard.review.init.ReviewInit");
			Class.forName("com.interyard.reply.init.ReplyInit");
			Class.forName("com.interyard.event.init.EventInit");
			// 오라클 드라이버 확인 + 로딩
			Class.forName("com.interyard.util.db.DB");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//메뉴를 출력하고 선택(uri) 처리
		//uri - /board가 앞에 붙어 있으면 BoardController로 가게 만든다.
		System.out.println("DispatcharServlet.service() 실행");
		
		String uri = request.getRequestURI();
		System.out.println("DispatcharServlet - uri = "+uri);
		
		//메인처리 - localhost -> localhost/main.do -> /main/main.do
		if(uri.equals("/") || uri.equals("/main.do")) {
			response.sendRedirect("/main/main.do");
			return;
		}
		
		//uri = /module/기능 -> /board/list.do
		int pos = uri.indexOf("/",1);
		
		String jsp = null;
		
		if(pos==-1) {
			request.setAttribute("uri", uri);
			request.getRequestDispatcher("/WEB-INF/views/error/nomodule404.jsp").forward(request, response);
			return;
		}
		
		String module = uri.substring(0,pos);
		System.out.println("module = "+module);
		
		//request에 module 담아서 어떤 메뉴가 선택되었는지 처리 
		request.setAttribute("module", module);
		request.setAttribute("uri", uri);
		switch (module) {
		case "/main":
			System.out.println("메인 게시판");		
			jsp = mainController.execute(request);
			break;
		case "/qna":
			System.out.println("질문 답변");			
			jsp = qnaController.execute(request);
			break;
		case "/review":
			System.out.println("후기");			
			jsp = reviewController.execute(request,response);
			break;
		case "/message":
			System.out.println("메세지");			
			jsp = messageController.execute(request);
			break;
		case "/basket":
			System.out.println("장바구니");			
			jsp = basketController.execute(request);
			break;
		case "/opt":
			System.out.println("장바구니 옵션");			
			jsp = basketOptController.execute(request,response);
			break;
		case "/dlvy":
			System.out.println("배송지 관리");			
			jsp = dlvyController.execute(request);
			break;
		case "/order":
			System.out.println("주문 관리");			
			jsp = orderController.execute(request);
			break;
		case "/member":
			System.out.println("회원 관리");			
			jsp = memberController.execute(request);
			break;
		case "/ajax":
			System.out.println("회원 관리 에이젝스");			
			jsp = ajaxController.excute(request);
			break;
		case "/usedgoods":
			System.out.println("중고 장터");			
			jsp = usedGoodsController.execute(request);
			break;
		case "/reply":
			System.out.println("댓글");			
			jsp = replyController.execute(request);
			break;
		case "/notice":
			System.out.println("공지 사항");			
			jsp = noticeController.execute(request);
			break;
		case "/event":
			System.out.println("이벤트");			
			jsp = eventController.execute(request);
			break;
		case "/goods":
			System.out.println("상품 관리");			
			jsp = goodsController.execute(request);
			break;
		
		default:
			request.setAttribute("uri", uri);
			request.getRequestDispatcher("/WEB-INF/views/error/nomodule404.jsp").forward(request, response);
			return;			
		}
		//jsp 정보 앞에 "redirect:"이 붙어 있으면 redirect 시킨다.(페이지 자동 이동)
		//jsp 정보 앞에 "redirect:"이 붙어 있지 않으면 forward 시킨다.(페이지 자동 이동)
		if(jsp.equals("return")) return;
		if(jsp.indexOf("redirect:")==0) {		
			// uri로 사용하기 위해 redirect:은 잘라 버린다.
			response.sendRedirect(jsp.substring("redirect:".length()));
		} else {
			//jsp로 포워드 한다.
			request.getRequestDispatcher("/WEB-INF/views/"+jsp+".jsp").forward(request, response);			
		}
		
	}

}
