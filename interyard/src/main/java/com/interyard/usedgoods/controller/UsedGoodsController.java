package com.interyard.usedgoods.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.interyard.member.vo.LoginVO;
import com.interyard.reply.init.ReplyInit;
import com.interyard.usedgoods.init.UsedGoodsInit;
import com.interyard.usedgoods.vo.UsedGoodsVO;
import com.interyard.util.exe.Execute;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webjjang.util.page.PageObject;
import com.webjjang.util.page.ReplyPageObject;

public class UsedGoodsController extends ReplyPageObject {

    public String execute(HttpServletRequest request) {
        System.out.println("UsedGoodsController.execute() --------------------------");
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
        String savePath = "/upload/image";
        // 파일 시스템에서는 절대 저장 위치가 필요하다. - 파일 업로드 시 필요.
        String realSavePath 
        = request.getServletContext().getRealPath(savePath);
        // 업로드 파일 용량 제한
        int sizeLimit = 100 * 1024 * 1024;

        // 입력 받는 데이터 선언
        Long no = 0L;

        try {
            switch(uri){
            case "/usedgoods/list.do":
            	 System.out.println("중고장터 리스트");
                 PageObject pageObject = PageObject.getInstance(request);
                 String strPerPageNum = request.getParameter("perPageNum");
                 if(strPerPageNum == null) pageObject.setPerPageNum(6);
                 result = Execute.execute(UsedGoodsInit.get(uri), pageObject);
                 System.out.println("UsedgoodsController.execute().pageObject = " + pageObject);
                 System.out.println("realSavePath = " + realSavePath);
                 
                 request.setAttribute("list", result);
                 request.setAttribute("pageObject", pageObject);
                 jsp = "usedgoods/list";
                 break;


                
            case "/usedgoods/view.do":
                System.out.println("2.상품상세 보기");
                Long ugno = Long.parseLong(request.getParameter("ugno"));
                result = Execute.execute(UsedGoodsInit.get(uri), ugno);
                request.setAttribute("vo", result);
               
                
                ReplyPageObject replypageObject = ReplyPageObject.getInstance(request);
                //request.getParameter("ugno");
                //Long.parseLong(strUgno);
                result = Execute.execute(ReplyInit.get("/reply/list.do"),  replypageObject);
                request.setAttribute("replyVO", result);
                request.setAttribute("replypageObject", replypageObject);

                System.out.println("Result: " + result); // 디버깅용 출력
                jsp = "usedgoods/view";
                break;

                
            case "/usedgoods/writeForm.do":
                System.out.println("3-1 상품 등록 폼");
                jsp="usedgoods/writeForm";
                break;

                
            case "/usedgoods/write.do":
                System.out.println("3-2.상품 등록 처리");
                // 이미지 업로드 처리
                MultipartRequest multi
                = new MultipartRequest(request, realSavePath, sizeLimit,
                        "utf-8", new DefaultFileRenamePolicy() );
                // 일반 데이터 수집(사용자->서버 : form - input - name) : multi에서
                String title = multi.getParameter("title");
                String content = multi.getParameter("content");
                String image = multi.getFilesystemName("imageFile");
                Long price = Long.parseLong(multi.getParameter("price"));

                String imagePath = savePath + "/" + image;

                UsedGoodsVO vo = new UsedGoodsVO();
                vo.setTitle(title);
                vo.setContent(content);
                vo.setId(id);
                vo.setPrice(price);
                vo.setImage(imagePath);
                // [ImageController] - ImageWriteService - ImageDAO.write(vo)
                Execute.execute(UsedGoodsInit.get(uri), vo);
                // 메시지 보내기
                session.setAttribute("msg", "상품이 등록되었습니다.");
                // jsp 정보 앞에 "redirect:"가 붙어 있어 redirect를
                // 아니면 jsp로 forward로 시킨다.
                jsp = "redirect:list.do?perPageNum=" 
                        + multi.getParameter("perPageNum");
                break;   

            case "/usedgoods/delete.do":
                System.out.println("4.이미지게시판 글삭제");
                // 데이터 수집 - DB에서 실행에 필요한 데이터 - 글번호, id - session
                no = Long.parseLong(request.getParameter("no"));
                UsedGoodsVO deleteVO = new UsedGoodsVO();
                deleteVO.setUgno(no);
                deleteVO.setId(id);
                // DB 처리
                Execute.execute(UsedGoodsInit.get(uri), deleteVO);
                System.out.println();
                System.out.println("***************************");
                System.out.println("**" + deleteVO.getUgno()+"글이 삭제되었습니다.**");
                System.out.println("***************************");
                // 파일 삭제
                // 삭제할 파일 이름
                String deleteFileName
                    = request.getParameter("deleteFileName");
                File deleteFile = new File(request.getServletContext()
                        .getRealPath(deleteFileName));
                if(deleteFile.exists()) deleteFile.delete();
                // 메시지 처리
                session.setAttribute("msg", "상품이 삭제가 되었습니다.");
                jsp = "redirect:list.do?perPageNum=" 
                        + request.getParameter("perPageNum");
                break;

                
            case "/usedgoods/updateForm.do":
                System.out.println("4-1.상품수정 폼");
                no = Long.parseLong(request.getParameter("no"));
                result = Execute.execute(UsedGoodsInit.get("/usedgoods/view.do"), no);
                request.setAttribute("vo", result);
                jsp = "usedgoods/updateForm";
                break;

                
            case "/usedgoods/update.do":
                System.out.println("4-2. 상품수정 처리");
                multi
                = new MultipartRequest(request, realSavePath, sizeLimit,
                        "utf-8", new DefaultFileRenamePolicy());
                no = Long.parseLong(multi.getParameter("ugno"));
                title = multi.getParameter("title");
                content = multi.getParameter("content");
                price = Long.parseLong(multi.getParameter("price"));
                image = multi.getFilesystemName("imageFile");
                deleteFileName = multi.getParameter("deleteFileName");
                String ugstatus = multi.getParameter("ugstatus");
                vo = new UsedGoodsVO();
                vo.setUgno(no);
                vo.setTitle(title);
                vo.setContent(content);
                vo.setPrice(price);
                vo.setUgstatus(ugstatus);
                vo.setId(id);
                if (image != null) {
                    vo.setImage(savePath + "/" + image);
                    if (deleteFileName != null && !deleteFileName.isEmpty()) {
                        deleteFile = new File(request.getServletContext().getRealPath(deleteFileName));
                        if (deleteFile.exists()) deleteFile.delete();
                    }
                } else {
                    vo.setImage(multi.getParameter("originalImage"));
                }

                Execute.execute(UsedGoodsInit.get(uri), vo);

                session.setAttribute("msg", "상품이 수정되었습니다.");
                pageObject = PageObject.getInstance(request);
                jsp = "redirect:view.do?ugno=" + no  + "&" + pageObject.getPageQuery() + "&no=" + no;
                break;   
            }//end of switch
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsp;
    }//end of execute
}// end of class
