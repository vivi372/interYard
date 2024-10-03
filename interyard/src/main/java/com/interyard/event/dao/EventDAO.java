package com.interyard.event.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interyard.event.vo.EventVO;
import com.interyard.main.dao.DAO;
import com.interyard.util.db.DB;
import com.webjjang.util.page.PageObject;

public class EventDAO extends DAO {


	public List<EventVO> list(PageObject pageObject) throws Exception {
		
		List<EventVO> list = null;
		
		try {
			con = DB.getConnection();
			System.out.println("EventDAO.list().sql = " + getListSQL(pageObject));
			
			pstmt = con.prepareStatement(getListSQL(pageObject));
			int idx = 0;
			idx = setSearchData(pageObject, pstmt, idx);
			pstmt.setLong(++idx, pageObject.getStartRow()); // 기본 값 = 1
			pstmt.setLong(++idx, pageObject.getEndRow());   // 기본 값 = 10
			
			rs = pstmt.executeQuery(); // Execute the query and get the ResultSet
			
			while (rs.next()) {
				if (list == null)
					list = new ArrayList<>();
				EventVO vo = new EventVO();
				vo.setNo(rs.getLong("no"));
				vo.setCategoryNo(rs.getLong("categoryNo"));
				vo.setTitle(rs.getString("title"));
                vo.setStartDate(rs.getString("startDate"));
                vo.setEndDate(rs.getString("endDate"));
				vo.setImage(rs.getString("image"));
				vo.setHit(rs.getLong("hit"));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		return list;
	}
	
    public List<EventVO> eventBookList(PageObject pageObject) throws Exception {

        List<EventVO> list = null;
        Long categoryNo = 1000L;

        try {
            con = DB.getConnection();
            System.out.println("EventDAO.list().sql = " + getListSQL(pageObject, categoryNo));
            
            pstmt = con.prepareStatement(getListSQL(pageObject, categoryNo));
            int idx = 0;
            idx = setSearchData(pageObject, pstmt, idx);
            pstmt.setLong(++idx, pageObject.getStartRow()); // 기본 값 = 1
            pstmt.setLong(++idx, pageObject.getEndRow());   // 기본 값 = 10

            rs = pstmt.executeQuery(); // Execute the query and get the ResultSet

            while (rs.next()) {
                if (list == null)
                    list = new ArrayList<>();
                EventVO vo = new EventVO();
                vo.setNo(rs.getLong("no"));
                vo.setCategoryNo(rs.getLong("categoryNo"));
                vo.setTitle(rs.getString("title"));
                vo.setStartDate(rs.getString("startDate"));
                vo.setEndDate(rs.getString("endDate"));
                vo.setImage(rs.getString("image"));
                vo.setHit(rs.getLong("hit"));

                list.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(con, pstmt, rs);
        }

        return list;
    }

    public List<EventVO> eventShopList(PageObject pageObject) throws Exception {
        List<EventVO> list = null;
        Long categoryNo = 2000L;
        try {
            con = DB.getConnection();
            System.out.println("EventDAO.eventShopList().sql = " + getListSQL(pageObject, categoryNo));
            
            pstmt = con.prepareStatement(getListSQL(pageObject, categoryNo));
            int idx = 0;
            idx = setSearchData(pageObject, pstmt, idx);
            pstmt.setLong(++idx, pageObject.getStartRow());
            pstmt.setLong(++idx, pageObject.getEndRow());
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                if (list == null)
                    list = new ArrayList<>();
                EventVO vo = new EventVO();
                vo.setNo(rs.getLong("no"));
                vo.setCategoryNo(rs.getLong("categoryNo"));
                vo.setTitle(rs.getString("title"));
                vo.setStartDate(rs.getString("startDate"));
                vo.setEndDate(rs.getString("endDate"));
                vo.setImage(rs.getString("image"));
                vo.setHit(rs.getLong("hit"));
                
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(con, pstmt, rs);
        }
        return list;
    }
    
    public List<EventVO> eventTicketList(PageObject pageObject) throws Exception {
        List<EventVO> list = null;
        Long categoryNo = 3000L;
        try {
            con = DB.getConnection();
            System.out.println("EventDAO.eventTicketList().sql = " + getListSQL(pageObject, categoryNo));
            
            pstmt = con.prepareStatement(getListSQL(pageObject, categoryNo));
            int idx = 0;
            idx = setSearchData(pageObject, pstmt, idx);
            pstmt.setLong(++idx, pageObject.getStartRow());
            pstmt.setLong(++idx, pageObject.getEndRow());
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                if (list == null)
                    list = new ArrayList<>();
                EventVO vo = new EventVO();
                vo.setNo(rs.getLong("no"));
                vo.setCategoryNo(rs.getLong("categoryNo"));
                vo.setTitle(rs.getString("title"));
                vo.setStartDate(rs.getString("startDate"));
                vo.setEndDate(rs.getString("endDate"));
                vo.setImage(rs.getString("image"));
                vo.setHit(rs.getLong("hit"));
                
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(con, pstmt, rs);
        }
        return list;
    }



	public Long getTotalRow(PageObject pageObject) throws Exception {

		Long totalRow = null;
		try {

			con = DB.getConnection();

			System.out.println("EventDAO.getTotalRow().sql=" + TOTALROW + getSearch(pageObject, true));

			pstmt = con.prepareStatement(TOTALROW + getSearch(pageObject, true));
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
	
	// increase()
	public int increase(Long no) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {

			con = DB.getConnection();

			pstmt = con.prepareStatement(INCREASE);
			pstmt.setLong(1, no);
			
			result = pstmt.executeUpdate();
			
			if (result == 0) { 
				throw new Exception("예외 발생 : 글번호가 존재하지 않습니다. 글번호를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else
				throw new Exception("예외 발생 : 게시판 글보기 조회수 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}

		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of increase()

	// view
	public EventVO view(Long no) throws Exception {

		EventVO vo = null;

		try {

			con = DB.getConnection();

			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs != null && rs.next()) {
				vo = new EventVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setImage(rs.getString("image"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setEndDate(rs.getString("endDate"));
				vo.setHit(rs.getLong("hit"));
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
	public int write(EventVO vo) throws Exception {

		int result = 0;

		try {

			con = DB.getConnection();

			pstmt = con.prepareStatement(WRITE);
			pstmt.setLong(1, vo.getCategoryNo());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getImage());
			pstmt.setString(5, vo.getStartDate());
			pstmt.setString(6, vo.getEndDate());

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
	public int update(EventVO vo) throws Exception {

		int result = 0;

		try {

			con = DB.getConnection();

			pstmt = con.prepareStatement(UPDATE);
			pstmt.setLong(1, vo.getCategoryNo());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getImage());
			pstmt.setString(5, vo.getStartDate());
			pstmt.setString(6, vo.getEndDate());
			pstmt.setLong(7, vo.getNo());

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

	} // end of delete()

	// SQL Queries
    final String LIST = "" +
        " select no, categoryNo, title, startDate, endDate, image, hit  " +
        " from ( " +
        " select rownum rnum, no, categoryNo, title, " +
        " startDate, endDate, image, hit " +
        " from ( " +
        " select no, categoryNo, title, " +
        " to_char(startDate, 'yyyy-mm-dd') startDate, " +
        " to_char(endDate, 'yyyy-mm-dd') endDate, " +
        " image, hit" +
        " from event " +
        " where 1=1 ";

    final String TOTALROW = "select count(*) from event ";

    private String getListSQL(PageObject pageObject, Long categoryNo) {
        String sql = LIST;
        if (categoryNo != null && categoryNo == 1000) {
            sql += "and categoryNo between 1000 and 1999 ";
        }
        if (categoryNo != null && categoryNo == 2000) {
            sql += "and categoryNo between 2000 and 2999 ";
        }
        if (categoryNo != null && categoryNo == 3000) {
            sql += "and categoryNo between 3000 and 3999 ";
        }
        sql += getSearch(pageObject, false);
        sql += getPeriod(pageObject);
        sql += " order by updateDate desc " + " ) " + " ) where rnum between ? and ? ";
        return sql;
    }

    private String getListSQL(PageObject pageObject) {
        String sql = LIST;
        sql += getSearch(pageObject, false);
        sql += getPeriod(pageObject);
        sql += " order by updateDate desc " + " ) " + " ) where rnum between ? and ? ";
        return sql;
    }

    private String getSearch(PageObject pageObject, boolean isWhere) {
        String sql = "";
        String key = pageObject.getKey();
        String word = pageObject.getWord();
        if (word != null && !word.equals("")) {
            if (isWhere)
                sql += " where 1=1 ";
            sql += " and ( 1=0 ";
            if (key.indexOf("t") >= 0) sql += " or title like ? ";
            if (key.indexOf("c") >= 0) sql += " or content like ? ";
            sql += " ) ";
        }
        return sql;
    }

	// 검색 쿼리의 ? 데이터를 세팅하는 메서드
	private int setSearchData(PageObject pageObject, PreparedStatement pstmt, int idx) throws SQLException {
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		if (word != null && !word.equals("")) {

			if (key.indexOf("t") >= 0)
				pstmt.setString(++idx, "%" + word + "%");
			if (key.indexOf("c") >= 0)
				pstmt.setString(++idx, "%" + word + "%");
		}
		return idx;
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

	final String VIEW = " select no, title, content, image, " + " to_char(startDate, 'yyyy-mm-dd') startDate,"
			+ " to_char(endDate, 'yyyy-mm-dd') endDate, hit " + " from event " + " where no = ?";

	final String WRITE = "insert into event " + " (no, categoryNo, title, content, image, startDate, endDate) "
			+ " values(event_seq.nextval, ?, ?, ?, ?, ?, ?)";

	final String UPDATE = "update event "
			+ " set categoryNo = ?, title = ?, content = ?, image = ?, startDate = ?, endDate = ?" + " where no = ?";

	final String DELETE = "delete from event " + " where no = ?";

	final String INCREASE = "update event set hit = hit + 1 " + " where no = ?";
}
