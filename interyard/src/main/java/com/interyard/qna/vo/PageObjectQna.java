package com.interyard.qna.vo;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import com.webjjang.util.page.PageObject;

public class PageObjectQna extends PageObject{
	

		private String ctg;
		private String semiCtg;
		
		public String getCtg() {
			return ctg;
		}

		public void setCtg(String ctg) {
			this.ctg = ctg;
		}
		
		public String getSemiCtg() {
			return semiCtg;
		}

		public void setSemiCtg(String semiCtg) {
			this.semiCtg = semiCtg;
		}
		
		
		// 객체로 만들어 주는 메서드 -> 웹프로젝트의  request 객체를 이용해서
		public static PageObjectQna getInstance(HttpServletRequest request) throws Exception {
			
			PageObjectQna pageObject = PageObjectQna.getInstance(request, "page", "perPageNum");

			return pageObject;
		}
		
		
		// 댓글의 페이지 처리를 위한 객체로 만들어 주는 메서드 -> 웹프로젝트의  request 객체를 이용해서
		public static PageObjectQna getInstance(HttpServletRequest request, String pageName, String perPageNumName) throws Exception {
			
			// 페이지 처리를 위한 프로그램
			// 페이지 처리를 위한 객체 사용
			PageObjectQna pageObject = new PageObjectQna();
			// 페이지에 대한 정보를 받는다.
			// page는 jsp에서 기본객체로 사용하고 있다. -> 페이지의 정보가 담겨져 있다.
			String strPage = request.getParameter(pageName);
			// 넘어오는 페이지가 있는 경우는 넘어오는 페이지를 현재 페이지로 셋팅. 그렇지 않으면 1이 셋팅된다.
			if(strPage != null && !strPage.equals("")) pageObject.setPage(Integer.parseInt(strPage));
			// 한페이지에 표시할 데이터의 수를 받는다.
			String strPerPageNum = request.getParameter(perPageNumName);
			// 한 페이지당 표시할 데이터의 수가 안넘어오면 10으로 셋팅된다. 넘어오면 넘어 오는 데이터를 사용한다.
			if(strPerPageNum != null && !strPerPageNum.equals("")) pageObject.setPerPageNum(Integer.parseInt(strPerPageNum));
			
			// 검색을 위한 데이터 전달
			pageObject.setKey(request.getParameter("key"));
			pageObject.setWord(request.getParameter("word"));
			pageObject.setCtg(request.getParameter("ctg"));
			pageObject.setSemiCtg(request.getParameter("semiCtg"));

			// PageObject - 확인
			System.out.println("PageObject.getInstance() [pageName = " + pageName + " ]");
			System.out.println("PageObject.getInstance() [perPageNumName = " + perPageNumName + " ]");
			System.out.println("PageObject.getInstance() [pageObject = " + pageObject + " ]");
			
			return pageObject;
		}
		
		
		@Override
		public String toString() {
			return "PageObject [ctg=" + ctg + ", semiCtg=" + semiCtg + "]";
		}
		
		public String getNotPageQuery() throws Exception {
			return ""
			+ "perPageNum=" + getPerPageNum()
			+ "&key=" + ((getKey() == null)?"":getKey())
			+ "&word=" + ((getWord() == null)?"":URLEncoder.encode(getWord(),"utf-8"))
			+ "&ctg=" + ((getCtg() == null)?"":URLEncoder.encode(getCtg(),"utf-8"))
			+ "&semiCtg=" + ((getSemiCtg() == null)?"":URLEncoder.encode(getSemiCtg(),"utf-8"))
			;
		}
		
		

}
