package com.interyard.usedgoods.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.usedgoods.vo.UsedGoodsVO;
import com.interyard.util.db.DB;
import com.webjjang.util.page.PageObject;

public class UsedGoodsDAO extends DAO {

    // 1. 리스트
    public List<UsedGoodsVO> list(PageObject pageObject) throws Exception {
        List<UsedGoodsVO> list = null;
        try {
            con = DB.getConnection(); // 데이터베이스 연결
            pstmt = con.prepareStatement(getListSQL(pageObject)); // SQL 준비
            int idx = 0;
            idx = setSearchData(pageObject, pstmt, idx); // 검색 조건 설정
            pstmt.setLong(++idx, pageObject.getStartRow()); // 시작 행 설정
            pstmt.setLong(++idx, pageObject.getEndRow()); // 끝 행 설정
            rs = pstmt.executeQuery(); // 쿼리 실행
            if (rs != null) {
                while (rs.next()) {
                    if (list == null)
                        list = new ArrayList<UsedGoodsVO>();
                    UsedGoodsVO vo = new UsedGoodsVO();
                    vo.setUgno(rs.getLong("ugno"));
                    vo.setTitle(rs.getString("title"));
                    vo.setId(rs.getString("id"));
                    vo.setPrice(rs.getLong("price"));
                    vo.setWriteDate(rs.getString("writeDate"));
                    vo.setImage(rs.getString("image"));
                    list.add(vo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DB.close(con, pstmt, rs); // 자원 해제
        }
        return list;
    }

    // 1-2. 전체 데이터 개수
    public Long getTotalRow(PageObject pageObject) throws Exception {
        Long totalRow = null;
        try {
            con = DB.getConnection(); // 데이터베이스 연결
            System.out.println("UsedGoodsDAO.getTotalRow().sql=" + TOTALROW + getSearch(pageObject, true));
            pstmt = con.prepareStatement(TOTALROW + getSearch(pageObject, true)); // SQL 준비
            int idx = 0;
            idx = setSearchData(pageObject, pstmt, idx); // 검색 조건 설정
            rs = pstmt.executeQuery(); // 쿼리 실행
            if (rs != null && rs.next()) {
                totalRow = rs.getLong(1); // 결과 처리
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DB.close(con, pstmt, rs); // 자원 해제
        }
        return totalRow;
    }

    // 2. 상세보기
    public UsedGoodsVO view(Long ugno) throws Exception {
        UsedGoodsVO vo = null;
        try {
            con = DB.getConnection(); // 데이터베이스 연결
            pstmt = con.prepareStatement(VIEW); // SQL 준비
            pstmt.setLong(1, ugno); // 데이터 세팅
            rs = pstmt.executeQuery(); // 쿼리 실행
            if (rs != null && rs.next()) {
                vo = new UsedGoodsVO();
                vo.setUgno(rs.getLong("ugno"));
                vo.setTitle(rs.getString("title"));
                vo.setContent(rs.getString("content"));
                vo.setId(rs.getString("id"));
                vo.setPrice(rs.getLong("price"));
                vo.setWriteDate(rs.getString("writeDate"));
                vo.setImage(rs.getString("image"));
                vo.setUgstatus(rs.getString("ugstatus"));
                System.out.println("조회된 데이터: " + vo); // 디버깅 로그 추가
            } else {
                System.out.println("해당 번호의 데이터가 없습니다. 번호: " + ugno); // 디버깅 로그 추가
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DB.close(con, pstmt, rs); // 자원 해제
        }
        return vo;
    }

    // 3. 등록
    public int write(UsedGoodsVO vo) throws Exception {
        int result = 0;
        try {
            con = DB.getConnection(); // 데이터베이스 연결
            pstmt = con.prepareStatement(WRITE); // SQL 준비
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setString(3, vo.getId());
            pstmt.setString(4, vo.getImage());
            pstmt.setLong(5, vo.getPrice());
            result = pstmt.executeUpdate(); // 쿼리 실행
            System.out.println("*** 등록이 완료 되었습니다."); // 등록 완료 메시지
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("예외 발생 : 상품 등록 DB 처리 중 예외가 발생했습니다."); // 예외 처리
        } finally {
            DB.close(con, pstmt); // 자원 해제
        }
        return result;
    }

    // 4. 수정 처리
    public int update(UsedGoodsVO vo) throws Exception {
        int result = 0;
        try {
            con = DB.getConnection(); // 데이터베이스 연결
            pstmt = con.prepareStatement(UPDATE); // SQL 준비
            pstmt.setString(1, vo.getTitle());
            pstmt.setLong(2, vo.getPrice());
            pstmt.setString(3, vo.getContent());
            pstmt.setString(4, vo.getImage());
            pstmt.setString(5, vo.getUgstatus());
            pstmt.setLong(6, vo.getUgno());
            pstmt.setString(7, vo.getId());
            result = pstmt.executeUpdate(); // 쿼리 실행
            if (result == 0) {
                throw new Exception("예외 발생 : 번호가 맞지 않거나 본인 글이 아닙니다. 정보를 확인해 주세요."); // 예외 처리
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().indexOf("예외 발생") >= 0)
                throw e;
            else
                throw new Exception("예외 발생 : 이미지 게시판 수정 DB 처리 중 예외가 발생했습니다."); // 예외 처리
        } finally {
            DB.close(con, pstmt); // 자원 해제
        }
        return result;
    }

    // 5. 삭제
    public int delete(UsedGoodsVO vo) throws Exception {
        int result = 0;
        try {
            con = DB.getConnection(); // 데이터베이스 연결
            pstmt = con.prepareStatement(DELETE); // SQL 준비
            pstmt.setLong(1, vo.getUgno());
            pstmt.setString(2, vo.getId());
            result = pstmt.executeUpdate(); // 쿼리 실행
            if (result == 0) {
                throw new Exception("예외 발생 : 번호 맞지 않거나 본인 글이 아닙니다. 정보를 확인해 주세요."); // 예외 처리
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().indexOf("예외 발생") >= 0)
                throw e;
            else
                throw new Exception("예외 발생 : 상품삭제 처리 중 예외가 발생했습니다."); // 예외 처리
        } finally {
            DB.close(con, pstmt); // 자원 해제
        }
        return result;
    }

    // SQL 쿼리들
    final String LIST = ""
            + " select ugno, title, id, price, writeDate, image"
            + " from ( "
            + " select rownum rnum, ugno, title, id, price, writeDate, image  "
            + " from ( "
            + " select u.ugno, u.title, m.id, u.price, "
            + " to_char(u.writeDate, 'yyyy-mm-dd') writeDate, "
            + " u.image "
            + " from usedgoods u , member m "
            + " where 1=1 ";

    private String TOTALROW = "select count(*) from usedgoods ";

    // 리스트 쿼리 생성 메서드
    private String getListSQL(PageObject pageObject) {
        String sql = LIST;
        sql += getSearch(pageObject, false); // 검색 조건 추가
        sql += " and (m.id = u.id) ";
        sql += " order by ugno desc "
                + " ) "
                + " ) where rnum between ? and ? ";
        return sql;
    }

    // 검색 조건 쿼리 생성 메서드
    private String getSearch(PageObject pageObject, boolean isWhere) {
        String sql = "";
        String key = pageObject.getKey();
        String word = pageObject.getWord();
        if (word != null && !word.equals("")) {
            if (isWhere) sql += " where 1=1 ";
            sql += " and ( 1=0 ";
            if (key.indexOf("t") >= 0) sql += " or title like ? ";
            if (key.indexOf("c") >= 0) sql += " or content like ? ";
            if (key.indexOf("f") >= 0) sql += " or fileName like ? ";
            sql += " ) ";
        }
        return sql;
    }

    // 검색 조건 쿼리의 ? 데이터 세팅 메서드
    private int setSearchData(PageObject pageObject, PreparedStatement pstmt, int idx) throws SQLException {
        String key = pageObject.getKey();
        String word = pageObject.getWord();
        if (word != null && !word.equals("")) {
            if (key.indexOf("t") >= 0)
                pstmt.setString(++idx, "%" + word + "%");
            if (key.indexOf("c") >= 0)
                pstmt.setString(++idx, "%" + word + "%");
            if (key.indexOf("f") >= 0)
                pstmt.setString(++idx, "%" + word + "%");
        }
        return idx;
    }

    final String VIEW = "select u.ugno, u.title, u.content, u.id, u.price, "
            + " to_char(u.writeDate, 'yyyy-mm-dd') writeDate, u.image, u.ugstatus "
            + " from usedgoods u, member m "
            + " where (u.ugno = ?) and (m.id = u.id) and (u.ugstatus in ('판매중', '판매완료')) ";

    final String WRITE = "insert into usedgoods "
            + " ( ugno, title, content, id, image, price) "
            + " values(usedgoods_seq.nextval, ?, ?, ?, ?, ?)";

    final String UPDATE = "update usedgoods "
            + " set title = ?, price = ?, content = ?, image = ?, ugstatus=?"
            + " where ugno = ? and id = ? ";

    final String DELETE = "delete from usedgoods "
            + " where ugno = ? and id = ? ";

} // end of class
