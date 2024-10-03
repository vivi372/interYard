package com.interyard.reply.dao;

import java.util.ArrayList;
import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.reply.vo.ReplyVO;
import com.interyard.util.db.DB;
import com.webjjang.util.page.ReplyPageObject;

public class ReplyDAO extends DAO {

	
	// 1-1. 리스트 처리
	// ReplyController - (Execute) - ReplyListService - [ReplyDAO.list()]
	public List<ReplyVO> list(ReplyPageObject pageObject) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		List<ReplyVO> list = null;
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST - 콘솔 확인하고 여기에 쿼리에 해당되는 LIST 출력해 본다.
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(LIST);
			pstmt.setLong(1, pageObject.getNo()); // 기본 값 = 1
			pstmt.setLong(2, pageObject.getStartRow()); // 기본 값 = 1
			pstmt.setLong(3, pageObject.getEndRow()); // 기본 값 = 10
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if (rs != null) {
				while (rs.next()) {
					// rs - > vo -> list
					// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
					if (list == null)
						list = new ArrayList<ReplyVO>();
					// rs -> vo
					ReplyVO vo = new ReplyVO();
					vo.setRno(rs.getLong("rno"));
					vo.setUgno(rs.getLong("ugno"));
					vo.setContent(rs.getString("content"));
					vo.setId(rs.getString("id"));
					vo.setWriteDate(rs.getString("writeDate"));
					// vo -> list
					list.add(vo);
				} // end of while
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally
			// 결과 데이터를 리턴해 준다.
		return list;
	} // end of list()
		
	
	// 1-2. 전체 데이터 개수 처리
	// ReplyController - (Execute) - ReplyListService - [ReplyDAO.getTotalRow()]
	public Long getTotalRow(ReplyPageObject pageObject) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		Long totalRow = null;
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(TOTALROW);
			pstmt.setLong(1, pageObject.getNo());
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if (rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally
		// 결과 데이터를 리턴해 준다.
		return totalRow;
	} // end of getTotalRow()
		
	
	//2 댓글 등록
	public int write(ReplyVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(WRITE);
			pstmt.setLong(1, vo.getUgno());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			System.out.println();
			System.out.println("*** 댓글 등록이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 댓글 등록 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of write()

	
	//3 댓글수정
	public int update(ReplyVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 UPDATE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getContent());
			pstmt.setLong(2, vo.getRno());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if(result == 0) { // 글번호가 존재하지 않는다. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 댓글번호가 맞지 않습니다. 정보를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if(e.getMessage().indexOf("예외 발생") >= 0) throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else throw new Exception("예외 발생 : 댓글 수정 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of update()	
	
	
	
	//4 댓글삭제
	public int delete(ReplyVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 DELETE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, vo.getRno());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if(result == 0) { // 글번호가 존재하지 않거나 비번 틀림. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 댓글번호가 맞지 않습니다. 정보를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if(e.getMessage().indexOf("예외 발생") >= 0) throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else throw new Exception("예외 발생 : 댓글삭제 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of delete()
	
	
	
	
	
	final String LIST = ""
			+ " select rno, ugno, content, id, writeDate "
			+ " from ( "
				+ " select rownum rnum, rno, ugno, content, id, writeDate "
				+ " from ( "
					+ " select  r.rno, r.ugno, r.content, r.id,  "
					+ " to_char(r.writeDate, 'yyyy-mm-dd') writeDate "
					+ " from reply r , member m "
					+ "  where 1=1 and ugno = ? and (r.id = m.id) "
					+ " order by rno desc"
				+ " ) "
			+ " ) where rnum between ? and ? ";
	
	
	
	final String TOTALROW = "select count(*) from reply "
			+ " where rno = ? ";
	
	
	final String WRITE = "insert into reply "
			+ " (rno, ugno, content, id) "
			+ " values(reply_seq.nextval, ?, ?, ? )"; 
	
	
	final String UPDATE= "update reply "
			+ " set content = ?  "
			+ " where rno = ? ";
	
	final String DELETE= "delete reply "
			+ " where rno = ? "; 
}
	