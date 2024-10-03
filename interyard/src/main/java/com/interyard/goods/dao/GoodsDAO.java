package com.interyard.goods.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interyard.goods.vo.GoodsVO;
import com.interyard.main.dao.DAO;
import com.interyard.util.db.DB;
import com.webjjang.util.page.PageObject;

public class GoodsDAO extends DAO {

	// 필요한 객체 선언 - 상속(DAO)
	// 접속 - DB사용

	// 1. 리스트 처리
	// GoodsController - (Execute) - GoodsListService - [GoodsDAO.list()]
	public List<GoodsVO> goodsList(PageObject pageObject) throws Exception {

		// 결과를 저장할 수 있는 변수 선언.
		List<GoodsVO> list = null;

		try {
			// 1. 드라이버 확인 : DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래의 LIST
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(getListSQL(pageObject));
			// 검색에 대한 데이터 세팅
			int idx = 0; // pstmt의 순서번호 사용. 먼저 1 증가하고 사용된다
			idx = setSearchData(pageObject, pstmt, idx);
			pstmt.setLong(++idx, pageObject.getStartRow()); // 기본값 = 1
			pstmt.setLong(++idx, pageObject.getEndRow()); // 기본값 = 10
			// 5.실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if (rs != null) {
				while (rs.next()) {
					if (list == null)
						list = new ArrayList<GoodsVO>();
					GoodsVO vo = new GoodsVO();
					vo.setGoodsNo(rs.getLong("goodsNo"));
					vo.setGoodsImage(rs.getString("goodsImage"));
					vo.setGoodsTitle(rs.getString("goodsTitle"));
					vo.setGoodsMaker(rs.getString("goodsMaker"));
					vo.setGoodsTrans(rs.getString("goodsTrans"));
					vo.setGoodsPublicher(rs.getString("goodsPublicher"));
					vo.setGoodsPrice(rs.getLong("goodsPrice"));
					vo.setGoodsStartDate(rs.getString("goodsStartDate"));
					vo.setGoodsEndDate(rs.getString("goodsEndDate"));
					list.add(vo);
				} // end of while
			} // end of if (rs != null)
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally
			// 결과 데이터 리턴
		return list;
	} // end of list()

	// 1-2. 전체 데이터 개수 처리
	// GoodsController - (Execute) - GoodsListService - [GoodsDAO.getTotalRow()]
	public Long getTotalRow(PageObject pageObject) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		Long totalRow = null;
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 TOTALROW
			System.out.println("GoodsDAO.getTotalRow().sql=" + TOTALROW + getSearch(pageObject, true));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(TOTALROW
					// 전체 데이터 개수 쿼리인 경우 검색 조건이 있으면 where를 붙여라:true
					+ getSearch(pageObject, true));
			int idx = 0;
			idx = setSearchData(pageObject, pstmt, idx);
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

	// 2. 상품 보기 처리 - 도서
	// GoodsController - (Execute) - GoodsViewService - [GoodsDAO.view()]
	public GoodsVO goodsBookView(Long goodsNo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		GoodsVO vo = null;
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			System.out.println("view연결");
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(BOOKSVIEW);
			pstmt.setLong(1, goodsNo);
			System.out.println("view 실행객체 세팅완료");
			// 5. 실행
			rs = pstmt.executeQuery();
			System.out.println("view 실행 완료");
			// 6. 표시 또는 담기
			if (rs != null && rs.next()) {
				// rs -> vo
				vo = new GoodsVO();
				vo.setCategoryNo(rs.getLong("categoryNo"));
				vo.setGoodsTitle(rs.getString("goodsTitle"));
				vo.setGoodsSubTitle(rs.getString("goodsSubTitle"));
				vo.setGoodsMaker(rs.getString("goodsMaker"));
				vo.setGoodsPublicher(rs.getString("goodsPublicher"));
				vo.setGoodsRunTime(rs.getLong("goodsRunTime"));
				vo.setGoodsNo(rs.getLong("goodsNo"));
				vo.setGoodsImage(rs.getString("goodsImage"));
				vo.setGoodsPrice(rs.getLong("goodsPrice"));
				vo.setGoodsQuantity(rs.getLong("goodsQuantity"));
				vo.setGoodsCost(rs.getLong("goodsCost"));
				vo.setGoodsStatus(rs.getString("goodsStatus"));
				vo.setGoodsContent(rs.getString("goodsContent"));
				vo.setGoodsWriteDate(rs.getString("goodsWriteDate"));
				vo.setGoodsRating(rs.getString("goodsRating"));
				vo.setGoodsStartDate(rs.getString("goodsStartDate"));
				vo.setGoodsEndDate(rs.getString("goodsEndDate"));
				vo.setGoodsTrans(rs.getString("goodsTrans"));
				vo.setCategoryName(rs.getString("categoryName"));
				System.out.println("가나다라마바사" + vo);
			} // end of if
			System.out.println("view 담기 완료");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally

		// 결과 데이터를 리턴해 준다.
		return vo;
	} // end of view()

	// 2. 상품 보기 처리 - 상품, 예매
	// GoodsController - (Execute) - GoodsViewService - [GoodsDAO.view()]
	public GoodsVO goodsSATVIEW(Long goodsNo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		GoodsVO vo = null;
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			System.out.println("view연결");
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(SATVIEW);
			pstmt.setLong(1, goodsNo);
			System.out.println("view 실행객체 세팅완료");
			// 5. 실행
			rs = pstmt.executeQuery();
			System.out.println("view 실행 완료");
			// 6. 표시 또는 담기
			if (rs != null && rs.next()) {
				// rs -> vo
				vo = new GoodsVO();
				vo.setCategoryNo(rs.getLong("categoryNo"));
				vo.setGoodsTitle(rs.getString("goodsTitle"));
				vo.setGoodsSubTitle(rs.getString("goodsSubTitle"));
				vo.setGoodsMaker(rs.getString("goodsMaker"));
				vo.setGoodsPublicher(rs.getString("goodsPublicher"));
				vo.setGoodsRunTime(rs.getLong("goodsRunTime"));
				vo.setGoodsNo(rs.getLong("goodsNo"));
				vo.setGoodsImage(rs.getString("goodsImage"));
				vo.setGoodsPrice(rs.getLong("goodsPrice"));
				vo.setGoodsQuantity(rs.getLong("goodsQuantity"));
				vo.setGoodsCost(rs.getLong("goodsCost"));
				vo.setGoodsStatus(rs.getString("goodsStatus"));
				vo.setGoodsContent(rs.getString("goodsContent"));
				vo.setGoodsWriteDate(rs.getString("goodsWriteDate"));
				vo.setGoodsRating(rs.getString("goodsRating"));
				vo.setGoodsStartDate(rs.getString("goodsStartDate"));
				vo.setGoodsEndDate(rs.getString("goodsEndDate"));
				vo.setGoodsTrans(rs.getString("goodsTrans"));
				vo.setOptNo(rs.getLong("optNo"));
				vo.setOptName(rs.getString("optName"));
				vo.setOptPrice(rs.getLong("optPrice"));
				vo.setCategoryName(rs.getString("categoryName"));
				System.out.println("가나다라마바사" + vo);
			} // end of if
			System.out.println("view 담기 완료");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally

		// 결과 데이터를 리턴해 준다.
		return vo;
	} // end of view()

	// 3-1. 상품 등록 처리 - 도서
	// GoodsController - (Execute) - GoodsWriteService - [GoodsDAO.goodsWrite()]
	public int goodsBookWrite(GoodsVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(BOOKWRITE);
			pstmt.setLong(1, vo.getCategoryNo());
			pstmt.setString(2, vo.getGoodsTitle());
			pstmt.setString(3, vo.getGoodsSubTitle());
			pstmt.setString(4, vo.getGoodsMaker());
			pstmt.setString(5, vo.getGoodsPublicher());
			pstmt.setLong(6, vo.getGoodsRunTime());
			pstmt.setLong(7, vo.getGoodsNo());
			pstmt.setString(8, vo.getGoodsImage());
			pstmt.setLong(9, vo.getGoodsPrice());
			pstmt.setLong(10, vo.getGoodsCost());
			pstmt.setString(11, vo.getGoodsStatus());
			pstmt.setString(12, vo.getGoodsContent());
			pstmt.setString(13, vo.getGoodsRating());
			pstmt.setString(14, vo.getGoodsStartDate());
			pstmt.setString(15, vo.getGoodsEndDate());
			pstmt.setString(16, vo.getGoodsTrans());

			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			System.out.println();
			System.out.println("상품등록이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 상품등록 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}

		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of increase()

	// 3-1. 상품 등록 처리 - 상품, 예매
	// GoodsController - (Execute) - GoodsWriteService - [GoodsDAO.goodsWrite()]
	public int goodsSATWrite(GoodsVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(SATWRITE);
			pstmt.setLong(1, vo.getCategoryNo());
			pstmt.setString(2, vo.getGoodsTitle());
			pstmt.setString(3, vo.getGoodsSubTitle());
			pstmt.setString(4, vo.getGoodsMaker());
			pstmt.setString(5, vo.getGoodsPublicher());
			pstmt.setLong(6, vo.getGoodsRunTime());
			pstmt.setString(7, vo.getGoodsImage());
			pstmt.setLong(8, vo.getGoodsPrice());
			pstmt.setLong(9, vo.getGoodsCost());
			pstmt.setString(10, vo.getGoodsStatus());
			pstmt.setString(11, vo.getGoodsContent());
			pstmt.setString(12, vo.getGoodsRating());
			pstmt.setString(13, vo.getGoodsStartDate());
			pstmt.setString(14, vo.getGoodsEndDate());
			pstmt.setString(15, vo.getGoodsTrans());

			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			System.out.println();
			System.out.println("상품등록이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 상품등록 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}

		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of increase()

	// 3-2. 옵션등록 처리
	// GoodsController - (Execute) - GoodsWriteService - [GoodsDAO.optionWrite()]
	public int optionWrite(GoodsVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(OPTIONWRITE);
			pstmt.setString(1, vo.getOptName());
			pstmt.setLong(2, vo.getOptPrice());
			pstmt.setLong(3, vo.getGoodsNo());

			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			System.out.println();
			System.out.println("옵션 등록이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 옵션등록 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}

		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of increase()

	// 4-1. 상품 정보 수정 처리
	// GoodsController - (Execute) - GoodsUpdateService - [GoodsDAO.goodsUpdate()]
	public int goodsUpdate(GoodsVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 UPDATE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(GOODSUPDATE);
			pstmt.setLong(1, vo.getCategoryNo());
			pstmt.setString(2, vo.getGoodsTitle());
			pstmt.setString(3, vo.getGoodsSubTitle());
			pstmt.setString(4, vo.getGoodsMaker());
			pstmt.setString(5, vo.getGoodsPublicher());
			pstmt.setLong(6, vo.getGoodsRunTime());
			pstmt.setString(7, vo.getGoodsImage());
			pstmt.setLong(8, vo.getGoodsPrice());
			pstmt.setLong(9, vo.getGoodsCost());
			pstmt.setString(10, vo.getGoodsStatus());
			pstmt.setString(11, vo.getGoodsContent());
			pstmt.setString(12, vo.getGoodsRating());
			pstmt.setString(13, vo.getGoodsStartDate());
			pstmt.setString(14, vo.getGoodsEndDate());
			pstmt.setString(15, vo.getGoodsTrans());
			pstmt.setLong(16, vo.getGoodsNo());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if (result == 0) { // 글번호가 존재하지 않는다. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 상품 번호가 맞지 않습니다. 정보를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else
				throw new Exception("예외 발생 : 상품 정보 수정 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}

		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of update()

	// 4-2. 옵션 정보 수정 처리
	// GoodsController - (Execute) - GoodsUpdateService - [GoodsDAO.optionUpdate()]
	public int optionUpdate(GoodsVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 UPDATE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(OPTIONUPDATE);
			pstmt.setString(1, vo.getOptName());
			pstmt.setLong(2, vo.getOptPrice());
			pstmt.setLong(3, vo.getOptNo());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if (result == 0) { // 글번호가 존재하지 않는다. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 상품 번호가 맞지 않습니다. 정보를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else
				throw new Exception("예외 발생 : 상품 정보 수정 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}

		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of update()

	// 5. 상품 삭제 처리
	// GoodsController - (Execute) - GoodsDeleteService - [GoodsDAO.delete()]
	public int delete(GoodsVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 UPDATE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, vo.getGoodsNo());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if (result == 0) { // 글번호가 존재하지 않거나 비번 틀림. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 번호가 맞지 않습니다. 정보를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else
				throw new Exception("예외 발생 : 상품 삭제 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}

		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of delete()

	final String TOTALROW = "SELECT COUNT(*) FROM goods g, category c ";

	private String getSearch(PageObject pageObject, boolean isWhere) {
		String sql = "";
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		if (word != null && !word.equals("")) {
			// where 붙이기 처리
			if (isWhere)
				sql += " where 1=1 ";
			sql += " and ( 1=0 ";
			// key안에 t가 포함되어 있으면 title로 검색을 한다.
			if (key.indexOf("t") >= 0)
				sql += " or g.goodsTitle like ? ";
			if (key.indexOf("m") >= 0)
				sql += " or g.goodsMaker like ? ";
			if (key.indexOf("p") >= 0)
				sql += " or g.goodsPublicher like ? ";
			if (key.indexOf("c") >= 0)
				sql += " or c.categoryName like ? ";
			if (key.indexOf("n") >= 0)
				sql += " or c.categoryno like ? ";
			sql += " ) ";
		}
		return sql;

	}

	final String LIST = ""
			+ " SELECT goodsNo, goodsImage, goodsTitle, goodsMaker, goodsTrans, goodsPublicher, goodsPrice, goodsStartDate, goodsEndDate "
			+ " FROM ( "
			+ " SELECT rownum rnum, goodsNo, goodsImage, goodsTitle, goodsMaker, goodsTrans, goodsPublicher, goodsPrice, goodsStartDate, goodsEndDate "
			+ " FROM ( "
			+ " SELECT g.goodsNo, g.goodsImage, g.goodsTitle, g.goodsMaker, g.goodsTrans, g.goodsPublicher, g.goodsPrice, to_char(g.goodsStartDate, 'yyyy-mm-dd') goodsStartDate, to_char(g.goodsEndDate, 'yyyy-mm-dd') goodsEndDate "
			+ " FROM goods g, category c WHERE 1=1";

	// 검색 쿼리의 ? 데이터를 세팅하는 메서드
	private int setSearchData(PageObject pageObject, PreparedStatement pstmt, int idx) throws SQLException {
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		if (word != null && !word.equals("")) {
			// key안에 t가 포함되어 있으면 title로 검색을 한다.
			if (key.indexOf("t") >= 0)
				pstmt.setString(++idx, "%" + word + "%");
			if (key.indexOf("m") >= 0)
				pstmt.setString(++idx, "%" + word + "%");
			if (key.indexOf("p") >= 0)
				pstmt.setString(++idx, "%" + word + "%");
			if (key.indexOf("c") >= 0)
				pstmt.setString(++idx, "%" + word + "%");
			if (key.indexOf("n") >= 0)
				pstmt.setString(++idx, "%" + word + "%");
		}
		return idx;
	}

	// LIST에 검색을 처리해서 만들지는 sql문 작성 메서드
	private String getListSQL(PageObject pageObject) {
		String sql = LIST;
		// String word = pageObject.getWord();
		// 검색 쿼리 추가 - where를 추가 안한다. : false
		sql += getSearch(pageObject, false);
		sql += " and (g.categoryNo = c.categoryNo) ";
		sql += " order by goodsNo desc " + " ) " + " ) where rnum between ? and ? ";
		return sql;
	}

	final String BOOKSVIEW = "SELECT g.categoryNo, g.goodsTitle, g.goodsSubTitle, g.goodsMaker, g.goodsPublicher, g.goodsRunTime, g.goodsNo, "
			+ "g.goodsImage, g.goodsPrice, g.goodsQuantity, g.goodsCost, g.goodsStatus, g.goodsContent, "
			+ "TO_CHAR(g.goodsWriteDate, 'yyyy-mm-dd') AS goodsWriteDate, g.goodsRating, "
			+ "TO_CHAR(g.goodsStartDate, 'yyyy-mm-dd') AS goodsStartDate, TO_CHAR(g.goodsEndDate, 'yyyy-mm-dd') AS goodsEndDate, g.goodsTrans, "
			+ "c.categoryName " + "FROM goods g, category c " + "WHERE g.goodsNo = ? AND g.categoryNo = c.categoryNo";

	final String SATVIEW = "SELECT g.categoryNo, g.goodsTitle, g.goodsSubTitle, g.goodsMaker, g.goodsPublicher, g.goodsRunTime, g.goodsNo, "
			+ "g.goodsImage, g.goodsPrice, g.goodsQuantity, g.goodsCost, g.goodsStatus, g.goodsContent, "
			+ "TO_CHAR(g.goodsWriteDate, 'yyyy-mm-dd') AS goodsWriteDate, g.goodsRating, "
			+ "TO_CHAR(g.goodsStartDate, 'yyyy-mm-dd') AS goodsStartDate, TO_CHAR(g.goodsEndDate, 'yyyy-mm-dd') AS goodsEndDate, g.goodsTrans, "
			+ "o.optNo, o.optName, o.optPrice, c.categoryName " + "FROM goods g, goodsOption o, category c "
			+ "WHERE g.goodsNo = ? AND g.categoryNo = c.categoryNo AND g.goodsNo = o.goodsNo";

	final String BOOKWRITE = "INSERT INTO goods ("
			+ "categoryNo, goodsTitle, goodsSubTitle, goodsMaker, goodsPublicher, goodsRunTime, goodsNo, "
			+ "goodsImage, goodsPrice, goodsCost, goodsStatus, goodsContent, goodsRating, "
			+ "goodsStartDate, goodsEndDate, goodsTrans) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	final String SATWRITE = "INSERT INTO goods ("
			+ "categoryNo, goodsTitle, goodsSubTitle, goodsMaker, goodsPublicher, goodsRunTime, goodsNo, "
			+ "goodsImage, goodsPrice, goodsCost, goodsStatus, goodsContent, goodsRating, "
			+ "goodsStartDate, goodsEndDate, goodsTrans) "
			+ "VALUES (?, ?, ?, ?, ?, ?, goods_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	final String OPTIONWRITE = "INSERT INTO goodsOption (optNo, optName, optPrice, goodsNo) " + "VALUES (goodsOption_seq.nextval, ?, ?, ?)";

	final String GOODSUPDATE = "UPDATE goods SET "
			+ "categoryNo = ?, goodsTitle = ?, goodsSubTitle = ?, goodsMaker = ?, goodsPublicher = ?, goodsRunTime = ?, "
			+ "goodsImage = ?, goodsPrice = ?, goodsCost = ?, goodsStatus = ?, goodsContent = ?, goodsRating = ?, "
			+ "goodsStartDate = ?, goodsEndDate = ?, goodsTrans = ? " + "WHERE goodsNo = ?";

	final String OPTIONUPDATE = "UPDATE goodsOption SET " + "optName = ?, optPrice = ? " + "WHERE optNo = ?";

	final String DELETE = "DELETE FROM goods WHERE goodsNo = ?";

} // end of class GoodsDAO
