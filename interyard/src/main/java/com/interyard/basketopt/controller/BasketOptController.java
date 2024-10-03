package com.interyard.basketopt.controller;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.interyard.basket.init.BasketInit;
import com.interyard.util.exe.Execute;


public class BasketOptController {
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String jsp = null;
		
		String uri = request.getRequestURI();	
		
		try {
			switch (uri) {
			case "/opt/opt.do":
				
				jsp = "basket/write";
				break;
			case "/opt/list.do":
				
				long goodsNo = Long.parseLong(request.getParameter("goodsNo"));
				
				request.setAttribute("list", Execute.execute(BasketInit.get(uri), goodsNo));
				
				request.setCharacterEncoding("utf-8");
				
				
				jsp = "basket/optList";
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
