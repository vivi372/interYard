package com.interyard.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;

import com.interyard.member.Init.MemberInit;
import com.interyard.member.vo.LoginVO;
import com.interyard.member.vo.MemberVO;
import com.interyard.util.exe.Execute;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webjjang.util.page.PageObject;

public class MemberController {

	public String execute(HttpServletRequest request) {
		
		Object result = null;
		
		String jsp = null;
		
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		
		String id = null;
		LoginVO login = (LoginVO) session.getAttribute("login");
		if(login != null) id = login.getId();
		
		//파일 업로드 설정----------------------------
		
		// 파일의 상대적인 저장 위치
		String savePath = "/upload/member";
		// 파일 시스템에서는 절대 저장위치가 필요하다. - 파일 업로드시 필요
		String realSavePath = request.getServletContext().getRealPath(savePath);
		// 업로드 용량 제한
		int sizeLimit = 50 * 1024 * 1024;
		// realSavePath 폴더가 존재하지 않으면 만들자
		File realSavePathFile = new File(realSavePath);
		if(realSavePathFile.exists()) realSavePathFile.mkdirs();
		
		
		
		MemberVO vo = null;
		try {
			
			switch (uri) {
			case "/member/checkId.do" :
				System.out.println("아이디 중복 체크");
			
				id = request.getParameter("id");
				
				id = (String) Execute.execute(MemberInit.get(uri), id);
				request.setAttribute("id", id);
				
				jsp = "member/checkId";
				break;
				
			case "/member/checkEmail.do" :
				System.out.println("이메일 중복 체크");
				
				String email = request.getParameter("email");
				
				email = (String) Execute.execute(MemberInit.get(uri), email);
				request.setAttribute("email", email);
				
				jsp = "member/checkEmail";
				break;
				
			case "/member/checkTel.do" :
				
				System.out.println("전화번호 중복 체크");
				
				String tel = request.getParameter("tel");
				
				tel = (String) Execute.execute(MemberInit.get(uri), tel);
				request.setAttribute("tel", tel);
				
				jsp = "member/checkTel";
				break;
				
			case "/member/loginForm.do": 
				System.out.println("로그인 폼");
				
					jsp = "member/loginForm";

				break;
				
			case "/member/login.do": 
				
				System.out.println("로그인");
				// 데이터 수집
				id = request.getParameter("id");
				String pw = request.getParameter("pw");

				// 변수 저장
				LoginVO loginVO = new LoginVO();
				loginVO.setId(id);
				loginVO.setPw(pw);
				
				result  = Execute.execute(MemberInit.get(uri), loginVO);
				
				session.setAttribute("login", result);
				
				String referer = request.getHeader("Referer");
				
				// 로그인 실패시 다시 loginForm 으로 이동한다.
				if(result == null) {
					session.setAttribute("msg", "비밀번호나 아이디를 다시한번 확인해주세요!");
					jsp = "member/loginForm";
				}else {
				session.setAttribute("msg", "로그인이 완료 되었습니다.");
				   // Referer가 존재하지 않는 경우 기본 페이지로 설정
				jsp = "redirect:/main/main.do";
				}
				break;
				
			case "/member/logout.do": 
				System.out.println("로그아웃");
				referer = request.getHeader("Referer");
				// session의 로그인 내용 지우기 - 로그아웃 처리
				session.removeAttribute("login");
				
				// Referer가 존재하지 않는 경우 기본 페이지로 리다이렉트
			    if (referer == null || referer.isEmpty()) {
			        referer = "main/main";
			    }
			    jsp = "redirect:" + referer;
				session.setAttribute("msg", "로그아웃이 완료 되었습니다.");
				break;
				
			case "/member/list.do": 
				System.out.println("리스트");
				// getInstance - 기본 값이 있고 넘어오는 페이지와 검색 정보를 세팅 처리
				PageObject pageObject = PageObject.getInstance(request);
				// id setting = 관리자 계정은 제외시키기 위해서 -> id - accepter
				pageObject.setAccepter(id);
				
				System.out.println("MemberController.list" + id);
				System.out.println("BoardController.execute().pageObject = " + pageObject);
				// 가져오는 데이터  request 에 담기
				request.setAttribute("list", Execute.execute(MemberInit.get(uri),
						pageObject));
				request.setAttribute("pageObject", pageObject);	
				jsp = "member/list";
				break;
				
			case "/member/view.do": 
				System.out.println("상세 보기");
				if(request.getParameter("admin") != null) {
				id = request.getParameter("id");
				}
				// 가져오는 데이터  request 에 담기
				request.setAttribute("vo", Execute.execute(MemberInit.get(uri),
						id));
				
				jsp = "member/view";
				
				break;
			
			case "/member/writeForm.do":
				System.out.println("회원 가입폼");
				jsp = "/member/writeForm";
				break;
				
				
			case "/member/write.do" :
				System.out.println("회원가입처리");
				MultipartRequest multi = 
						new MultipartRequest(request, realSavePath,sizeLimit,"utf-8", new DefaultFileRenamePolicy());
				
				id = multi.getParameter("id");
				pw = multi.getParameter("pw");
				String name = multi.getParameter("name");
				String gender = multi.getParameter("gender");
				String birth = multi.getParameter("birth");
				tel = multi.getParameter("tel");
				email = multi.getParameter("email");
				String photo = multi.getFilesystemName("photoFile");
				vo = new MemberVO();
				vo.setId(id);
				vo.setPw(pw);
				vo.setName(name);
				vo.setGender(gender);
				vo.setBirth(birth);
				vo.setTel(tel);
				vo.setEmail(email);
				if(!(photo == null || photo.equals("")))
					vo.setPhoto(savePath+ "/" +photo);
				
				int result1 =  (int) Execute.execute(MemberInit.get(uri), vo);
				
				if(result1 == 0) {
					session.setAttribute("msg", "회원가입 불가 입니다.");
					jsp = "redirect:writeForm.do";
				}else {
				session.setAttribute("msg", "회원가입완료");
				jsp = "main/main";
				}
				break;
				
			case "/member/updateForm.do":
				System.out.println("업데이트 폼");
				if(request.getParameter("admin") != null) {
					id = request.getParameter("id");
					}
				result = Execute.execute(MemberInit.get("/member/view.do"), id);
				request.setAttribute("vo", result);
				jsp = "/member/updateForm";
				break;		
				
			case "/member/update.do":	
				
				System.out.println("업데이트");
				
				
				name = request.getParameter("name");
				gender = request.getParameter("gender");
				birth = request.getParameter("birth");
				tel = request.getParameter("tel");
				email = request.getParameter("email");
				
				vo = new MemberVO();
				vo.setId(id);
				vo.setName(name);
				vo.setGender(gender);
				vo.setBirth(birth);
				vo.setTel(tel);
				vo.setEmail(email);
				
				Execute.execute(MemberInit.get(uri), vo);
				
				pageObject = PageObject.getInstance(request);
				
				jsp = "redirect:view.do?id=" + id+"&admin=1";
				session.setAttribute("msg", "업데이트 완료");
				break;
			case "/member/changeGrade.do": 
					System.out.println("등급 수정");
					id = request.getParameter("id");
					Integer gradeNo = Integer.parseInt(request.getParameter("gradeNo"));
					
					vo = new MemberVO();
					vo.setId(id);
					vo.setGradeNo(gradeNo);
					
					Execute.execute(MemberInit.get(uri), vo);
					
					session.setAttribute("msg", "회원 ["+id+"] 님 등급을 ["+((gradeNo == 1)?"일반회원 으로":"관리자 로")+" 변경 되었습니다.");
					
					jsp = "redirect:list.do";
					break;
				
			case "/member/changeStatus.do": 
				System.out.println("등급 수정");
				id = request.getParameter("id");
				String status = request.getParameter("status");
				
				vo = new MemberVO();
				vo.setId(id);
				vo.setStatus(status);
				Execute.execute(MemberInit.get(uri), vo);
				
				if(login.getGradeNo() == 9) {
					if(id.equals(login.getId())) {
						session.removeAttribute("login");
						jsp = "redirect:/main/main.do";
						session.setAttribute("msg", "탈퇴가 완료되었습니다");
					} else {
						session.setAttribute("msg", "회원 ["+id+"] 님 상태를 ["+status+"] 로 변경 되었습니다.");
						jsp = "redirect:list.do";
					}
				}else if(login.getStatus() != "정상") {
					session.removeAttribute("login");
					jsp = "redirect:/main/main.do";
					session.setAttribute("msg", "탈퇴가 완료되었습니다");
				}
				break;	
				
			case "/member/searchIdForm.do":
				System.out.println("아이디 찾기 폼");
				jsp = "/member/searchIdForm";
				break;
				
			case "/member/searchId.do": 
				System.out.println("아이디 찾기");
				
				 name = request.getParameter("name");
				 tel = request.getParameter("tel");
				 
				 vo = new MemberVO();
				 vo.setName(name);
				 vo.setTel(tel);
				 
				 result = Execute.execute(MemberInit.get(uri), vo);
				 
				 request.setAttribute("vo",result);
				 if(result == null) {
					 session.setAttribute("msg", "당신은 사람이 아닙니다.");
					 jsp = "redirect:searchIdForm.do";
				 }else
				     jsp = "/member/searchId";
				break;
				
			case "/member/searchPwForm.do":
				System.out.println("비밀번호 찾기 폼");
				jsp = "/member/searchPwForm";
				break;	
			case "/member/searchPw.do": 
				System.out.println("비밀번호 찾기");
				id = request.getParameter("id");
				tel = request.getParameter("tel");
				email = request.getParameter("email");
				 
				 vo = new MemberVO();
				 vo.setId(id);
				 vo.setTel(tel);
				 vo.setEmail(email);
				 
				result = (String) Execute.execute(MemberInit.get(uri), vo);
				
				if (result != null && !result.equals(""))
				jsp = "/member/pwReSet";
				else {
					jsp = "redirect:searchPwForm.do";
					session.setAttribute("msg", " 입력하신 내용이 틀립니다. 다시 작성 부탁드려...");
					}
				break;	
				
			case "/member/pwReSet.do": 
				System.out.println("비밀번호 변경 폼");

					jsp = "member/pwReSet";
					break;
				
			case "/member/veriFy.do" :
				System.out.println("변경완료");
					
				pw = request.getParameter("pw");
				id = request.getParameter("id");
				vo = new MemberVO();
				vo.setPw(pw);
				vo.setId(id);
				Execute.execute(MemberInit.get(uri), vo);
				session.setAttribute("msg", "비밀번호 변경완료!");
				jsp = "redirect:loginForm.do";
				break;		
				
			default:
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return jsp;
	}
	
}
