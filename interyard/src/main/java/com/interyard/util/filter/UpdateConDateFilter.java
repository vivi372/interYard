package com.interyard.util.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.interyard.member.Init.MemberInit;
import com.interyard.member.vo.LoginVO;
import com.interyard.util.exe.Execute;

/**
 * Servlet Filter implementation class UpdateConDateFilter
 */
//@WebFilter("/UpdateConDateFilter")
public class UpdateConDateFilter extends HttpFilter implements Filter {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public UpdateConDateFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("UpdateConDateFilter.doFilter()");		
		HttpSession session = ((HttpServletRequest) request).getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		
		String id = null;
		if(login != null) {
			id = login.getId();
			try {
				Execute.execute(MemberInit.get("/member/updateConDate.do"), id);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
