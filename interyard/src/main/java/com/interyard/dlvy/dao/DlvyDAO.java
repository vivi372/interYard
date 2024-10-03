package com.interyard.dlvy.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interyard.dlvy.vo.DlvyVO;
import com.interyard.main.dao.DAO;
import com.interyard.util.db.DB;


public class DlvyDAO extends DAO{
	
	//1.배송지 리스트
	// DlvyController - (Execute) - DlvyListService - [DlvyDAO.list()]
	public List<DlvyVO> list(String id) throws SQLException{
		// 결과를 저장할 수 있는 변수 선언.
		List<DlvyVO> list = null;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			System.out.println(LIST);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(LIST);
			pstmt.setString(1, id);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs!=null) {
				// rs - > vo -> list
				// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
				list = new ArrayList<DlvyVO>();
				while(rs.next()){
					// rs -> vo
					DlvyVO vo =new DlvyVO();
					vo.setDlvyAddrNo(rs.getLong("dlvyAddrNo")); 
					vo.setDlvyName(rs.getString("dlvyName")); 
					vo.setRecipient(rs.getString("recipient")); 
					vo.setAddr(rs.getString("addr")); 
					vo.setTel(rs.getString("tel")); 
					vo.setPostNo(rs.getLong("postNo")); 
					vo.setBasic(rs.getLong("basic")); 
					// vo -> list
					list.add(vo);
				} // end of while
			} // end of if
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}// end of try ~ catch ~ finally
		// 결과 데이터를 리턴해 준다.
		return list;
	}// end of list()
	
	//2-1. 배송지 등록
	// DlvyController - (Execute) - DlvyWriteService - [DlvyDAO.write()]
	public int write(DlvyVO vo) throws SQLException{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 WRITE
			System.out.println(WRITE);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getDlvyName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getRecipient());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getAddr());
			pstmt.setLong(6, vo.getPostNo());
			pstmt.setLong(7, vo.getBasic());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		// 결과 데이터를 리턴해 준다.
		return result;
	}// end of write() 
	
	//2-2. 기본 배송지 변경
	// DlvyController - (Execute) - DlvyWriteService - [DlvyDAO.changeBasic()]
	public int changeBasic(String id) throws SQLException{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		
		try {	
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 WRITE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(CHANGE_BASIC);
			pstmt.setString(1, id);
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		// 결과 데이터를 리턴해 준다.
		return result;
	}// end of changeBasic() 
	
	//3-1. 수정 전 정보 가져오기
	// DlvyController - (Execute) - DlvyUpdateFormService - [DlvyDAO.view()]
	public DlvyVO view(long dlvyAddrNo) throws SQLException{
		// 결과를 저장할 수 있는 변수 선언.
		DlvyVO vo = null;
		
		try {	
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 VIEW
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, dlvyAddrNo);
			// 5. 실행
			rs = pstmt.executeQuery();	
			
			// 6. 표시 또는 담기
			if(rs!=null && rs.next()) {
				vo = new DlvyVO();
				vo.setDlvyAddrNo(rs.getLong("dlvyAddrNo")); 
				vo.setDlvyName(rs.getString("dlvyName")); 
				vo.setRecipient(rs.getString("recipient")); 
				vo.setAddr(rs.getString("addr")); 
				vo.setTel(rs.getString("tel")); 
				vo.setPostNo(rs.getLong("postNo")); 
				vo.setBasic(rs.getLong("basic")); 
			}			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}
		// 결과 데이터를 리턴해 준다.
		return vo;
	}// end of view() 
	
	//3-2. 수정 전 정보 가져오기
	// DlvyController - (Execute) - DlvyUpdateService - [DlvyDAO.update()]
	public int update(DlvyVO vo) throws SQLException{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		
		try {	
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 UPDATE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getDlvyName());			
			pstmt.setString(2, vo.getRecipient());
			pstmt.setString(3, vo.getTel());
			pstmt.setString(4, vo.getAddr());
			pstmt.setLong(5, vo.getPostNo());
			pstmt.setLong(6, vo.getBasic());
			pstmt.setLong(7, vo.getDlvyAddrNo());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();		
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		// 결과 데이터를 리턴해 준다.
		return result;
	}// end of update() 
	
	//4.배송지 삭제
	// DlvyController - (Execute) - DlvyDeleteService - [DlvyDAO.delete()]
	public int delete(DlvyVO vo) throws SQLException{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 DELETE
			System.out.println(DELETE);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(DELETE);		
			pstmt.setLong(1, vo.getDlvyAddrNo());
			pstmt.setString(2, vo.getId());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		// 결과 데이터를 리턴해 준다.
		return result;
	}// end of delete()
	
	private final String LIST = "select dlvyAddrNo,dlvyName,recipient,tel,addr,postNo,basic "
								+ " from dlvyAddr where id = ? order by basic desc, dlvyAddrNo desc ";
	private final String WRITE = "insert into dlvyAddr(dlvyAddrNo,dlvyName,id,recipient,tel,addr,postNo,basic) "
								+ " values(dlvyAddr_seq.nextval,?,?,?,?,?,?,?) ";
	//다른 기본 배송지를 일반 배송지로 바꾸는 쿼리
	private final String CHANGE_BASIC = "update dlvyAddr set basic = 0 where basic = 1 and id=?";
	private final String VIEW = "select dlvyAddrNo,dlvyName,recipient,tel,addr,postNo,basic from dlvyAddr where dlvyAddrNo=?";
	private final String UPDATE = "update dlvyAddr set dlvyName=?,recipient=?,tel=?,addr=?,postNo=?,basic=? where dlvyAddrNo=?";
	private final String DELETE = "delete from dlvyAddr where dlvyAddrNo=? and id = ?";
}
