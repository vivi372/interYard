package com.interyard.notice.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.notice.vo.NoticeVO;
import com.interyard.util.db.DB;
import com.webjjang.util.page.PageObject;

public class NoticeDAO extends DAO {

	public List<NoticeVO> list(PageObject pageObject) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		List<NoticeVO> list = null;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			System.out.println("NoticeDAO.list().sql = " + getListSQL(pageObject));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getListSQL(pageObject));
			int idx = 0; // pstmt의 순서번호 사용. 먼저 1 증가하고 사용한다.
			idx = setSearchData(pageObject, pstmt, idx);
			pstmt.setLong(++idx, pageObject.getStartRow()); // 기본 값 = 1
			pstmt.setLong(++idx, pageObject.getEndRow()); // 기본 값 = 10
			// 5. 실행
			rs = pstmt.executeQuery();
			
			// 6. 표시 또는 담기
			if (rs != null) {
				while (rs.next()) {
					// rs - > vo -> list
					// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
					if (list == null) list = new ArrayList<>();
					// rs -> vo
					NoticeVO vo = new NoticeVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));

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

	// Totalrow
	public Long getTotalRow(PageObject pageObject) throws Exception {

		Long totalRow = null;
		try {

			con = DB.getConnection();

			pstmt = con.prepareStatement(TOTALROW + getSearch(pageObject));
			int idx = 0;
			idx = setSearchData(pageObject, pstmt, idx);

			rs = pstmt.executeQuery();

			if (rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			DB.close(con, pstmt, rs);
		}
		return totalRow;
	} // end of getTotalRow()

	// view
	public NoticeVO view(Long no) throws Exception {

		NoticeVO vo = null;

		try {

			con = DB.getConnection();

			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs != null && rs.next()) {
				vo = new NoticeVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setImage(rs.getString("image"));
				vo.setUpdateDate(rs.getString("updateDate"));
				vo.setWriteDate(rs.getString("writeDate"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}

		return vo;

	} // end of view()
	
	// write
	public int write(NoticeVO vo) throws Exception {

		int result = 0;

		try {

			con = DB.getConnection();

			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getImage());
			pstmt.setString(4, vo.getStartDate());
			pstmt.setString(5, vo.getEndDate());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}

		return result;

	} // end of write()
	
	// update
	public int update(NoticeVO vo) throws Exception {
		
		int result = 0;
		
		try {
			
			con = DB.getConnection();
			
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getImage());
			pstmt.setString(4, vo.getStartDate());
			pstmt.setString(5, vo.getEndDate());
			pstmt.setLong(6, vo.getNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		return result;
		
	} // end of update()
	
	// delete
	public int delete(Long no) throws Exception {
		
		int result = 0;
		
		try {
			
			con = DB.getConnection();
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		return result;
		
	} // end of update()
	
	

	final String LIST = " select no, title, content, startDate, endDate " 
			+ " from ( "
			+ " select rownum rnum, no, title, content, startDate, endDate "
				+ " from ( " 
				+ " select no, title, content,"
				+ " to_char(startDate, 'yyyy-mm-dd') startDate, "
				+ " to_char(endDate, 'yyyy-mm-dd') endDate "
				+ " from notice " 
				+ " where (1 = 1) ";

	final String TOTALROW = "select count(*) from notice where ( 1 = 1) ";

	// LIST에 검색을 처리해서 만들지는 sql문 작성 메서드
	private String getListSQL(PageObject pageObject) {
		String sql = LIST;
		sql += getSearch(pageObject);
		sql += getPeriod(pageObject);
		sql += " order by endDate desc, no desc" 
				+ " ) "
				+ " ) where rnum between ? and ? ";
		return sql;
	}

	// 리스트의 검색만 처리하는 쿼리 - where
	private String getSearch(PageObject pageObject) {
		String sql = "";
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		if (word != null && !word.equals("")) {
			sql += " and ( 1=0 ";
			// key안에 t가 포함되어 있으면 title로 검색을 한다.
			if (key.indexOf("t") >= 0) sql += " or title like ? ";
			if (key.indexOf("c") >= 0) sql += " or content like ? ";
			sql += ")";
		}
		return sql;

	}

	// 리스트의 기간 검색만 처리하는 쿼리 - where
	private String getPeriod(PageObject pageObject) {
		String sql = "";
		String period = pageObject.getPeriod();
		sql += " and ( 1 = 1 ";
		// period에 따라서 기간을 검색한다.
		if (period.equals("pre")) // 현재 공지
			sql += " and trunc(sysdate) between trunc(startDate ) and trunc(endDate) ";
		else if (period.equals("old")) // 지난 공지
			sql += " and trunc(sysdate) > trunc(endDate) ";
		else if (period.equals("res")) // 예약 공지
			sql += " and trunc(sysdate) < trunc(startDate) ";
		else
			sql += ""; // 모든 공지
		sql += ")";
		return sql;

	}

	// 검색 쿼리의 ? 데이터를 세팅하는 메서드
	private int setSearchData(PageObject pageObject, PreparedStatement pstmt, int idx) throws SQLException {
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		if (word != null && !word.equals("")) {
			// key안에 t가 포함되어 있으면 title로 검색을 한다.
			if (key.indexOf("t") >= 0)
				pstmt.setString(++idx, "%" + word + "%");
			if (key.indexOf("c") >= 0)
				pstmt.setString(++idx, "%" + word + "%");
		}
		return idx;
	}

	final String VIEW = " select no, title, content, image, "  
			+ " to_char(updateDate, 'yyyy-mm-dd') updateDate,"
			+ " to_char(writeDate, 'yyyy-mm-dd') writeDate "
			+ " from notice " 
			+ " where no = ?";
	
	final String WRITE = "insert into notice "
			+ " (no, title, content, image, startDate, endDate) "
			+ " values(notice_seq.nextval, ?, ?, ?, ?, ?)"; 
	
	final String UPDATE= "update notice "
			+ " set title = ?, content = ?, image = ?, startDate = ?, endDate = ? "
			+ " where no = ?"; 
	
	final String DELETE= "delete from notice "
			+ " where no = ?"; 
	

}
