package com.interyard.review.dao;

import java.util.ArrayList;
import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.review.util.NameStringUtil;
import com.interyard.review.vo.ReviewVO;
import com.interyard.util.db.DB;

public class ReviewDAO extends DAO {
	
	// 1. Review 리스트 상품 하나에 리뷰 뿌리기
	public List<ReviewVO> list(Long no) throws Exception {
		
		// 리스트 저장 변수
		List<ReviewVO> list = null;
		
		try {
			// 1. DB 확인 - DB 클래스에서 확인
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. SQL
			System.out.println("SQL = " + LIST);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(LIST);
			pstmt.setLong(1, no);
			// 5. 실행 객체 실행
			rs = pstmt.executeQuery();
			// 6. 결과 데이터 표시 및 담기
			if (rs != null) {
				while (rs.next()) {
					if (list == null)
						list = new ArrayList<>();
						
						// 데이터 저장
						ReviewVO vo = new ReviewVO();
						vo.setRevNo(rs.getLong("revNo"));
						vo.setTitle(rs.getString("title"));
						vo.setContent(rs.getString("content"));
						vo.setImgFile(rs.getString("imgFile"));
						vo.setGrade(rs.getFloat("grade"));
						vo.setWriteDate(rs.getString("writeDate"));
						vo.setId(rs.getString("id"));
						vo.setBlindId(NameStringUtil.nameExchange(rs.getString("id")));
						vo.setName(rs.getString("name"));
						vo.setBlindName(NameStringUtil.nameExchange(rs.getString("name")));
						vo.setPhoto(rs.getString("photo"));
						vo.setLikeCnt(rs.getLong("likeCnt"));
						vo.setOrdNo(rs.getLong("ordNo"));
						vo.setLevNo(rs.getLong("levNo"));
						vo.setGoodsNo(rs.getLong("goodsNo"));
						vo.setGoodsTitle(rs.getString("goodsTitle"));
						vo.setOrderNo(rs.getLong("orderNo"));
						vo.setParentNo(rs.getLong("parentNo"));
						vo.setOptName(rs.getString("optName"));
						
						list.add(vo);
				} // list 반복 끝
			} // 결과 저장하기 끝
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}
		return list;
	} // end of list()
	
	// 1-2. 전체 데이터 개수, 평균 가져오기
	// ImageController - (execute) - ImageListService - [ImageDAO.view()]
	public ReviewVO getTotalRow(Long no) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		ReviewVO vo = new ReviewVO();

		try {
			// 1. 드라이버 확인 - DB 클래스에서 확인 완료
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. sql 문 - 아래 LIST
			// 전체 데이터 개수 쿼리인 경우 조건이 있으면 where를 붙혀라 : true
			System.out.println("sql : " + TOTALREVIEW + LIST + " ) subquery ");
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(TOTALREVIEW + LIST + " ) subquery ");
			pstmt.setLong(1, no);
			// 5. 실행 객체 실행
			rs = pstmt.executeQuery();
			// 6. 데이터 표시 또는 담기
			if (rs != null && rs.next()) {
				vo.setTotalReview(rs.getLong("review_count"));
				vo.setAvgReview( rs.getFloat("avg_grade"));
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			throw e; // 오류 나면 던진다
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ finally
		
		// 결과 데이터를 리턴 해 준다.
		return vo;
		
	} // end of getTotalRow
	
	// 1-3. 리뷰 별 점수 카운트
		// ImageController - (execute) - ImageListService - [ImageDAO.view()]
		public ReviewVO getGradeCnt(Long no) throws Exception {
			// 결과를 저장할 수 있는 변수 선언.
			ReviewVO vo = new ReviewVO();

			try {
				// 1. 드라이버 확인 - DB 클래스에서 확인 완료
				// 2. 오라클 접속
				con = DB.getConnection();
				// 3. sql 문 - 아래 LIST
				// 전체 데이터 개수 쿼리인 경우 조건이 있으면 where를 붙혀라 : true
				System.out.println("sql : " + GRADECNT);
				// 4. 실행 객체 선언
				pstmt = con.prepareStatement(GRADECNT);
				pstmt.setLong(1, no);
				// 5. 실행 객체 실행
				rs = pstmt.executeQuery();
				// 6. 데이터 표시 또는 담기
				if (rs != null && rs.next()) {
					vo.setGrade5(rs.getLong("grade5"));
					vo.setGrade4(rs.getLong("grade4"));
					vo.setGrade3(rs.getLong("grade3"));
					vo.setGrade2(rs.getLong("grade2"));
					vo.setGrade1(rs.getLong("grade1"));
				} // end of if
			} catch (Exception e) {
				e.printStackTrace();
				throw e; // 오류 나면 던진다
			} finally {
				// 7. 닫기
				DB.close(con, pstmt, rs);
			} // end of try ~ finally
			
			// 결과 데이터를 리턴 해 준다.
			return vo;
			
		} // end of getTotalRow
	
	
	// 2. Review 쓰기 - 주문 정보 뿌리기
	public ReviewVO orderInfo(Long orderNo, Long goodsNo) throws Exception {
		
		// 리스트 저장 변수
		ReviewVO vo = null;
		
		try {
			// 1. DB 확인 - DB 클래스에서 확인
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. SQL
			System.out.println("SQL = " + ORDERINFO);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(ORDERINFO);
			pstmt.setLong(1, orderNo);
			pstmt.setLong(2, goodsNo);
			// 5. 실행 객체 실행
			rs = pstmt.executeQuery();
			// 6. 결과 데이터 표시 및 담기
			if (rs != null && rs.next()) {
				// 데이터 저장
				vo = new ReviewVO();
				vo.setGoodsNo(rs.getLong("goodsNo"));
				vo.setGoodsImage(rs.getString("goodsImage"));
				vo.setGoodsTitle(rs.getString("goodsTitle"));
				vo.setGoodsSubTitle(rs.getString("goodsSubTitle"));
				vo.setOrderNo(rs.getLong("orderNo"));
				vo.setOptName(rs.getString("optNames"));
						
			} // 결과 저장하기 끝
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}
		return vo;
	} // end of orderInfo()
	
	// 3. Review 등록 
	public int write(ReviewVO vo) throws Exception {
		
		// 리스트 저장 변수
		int result = 0;
		
		try {
			// 1. DB 확인 - DB 클래스에서 확인
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. SQL
			System.out.println("SQL = " + WRITE);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setFloat(3, vo.getGrade());
			pstmt.setString(4, vo.getId());
			pstmt.setString(5, vo.getImgFile());
			pstmt.setLong(6, vo.getGoodsNo());
			pstmt.setLong(7, vo.getOrderNo());
			// 5. 실행 객체 실행
			result = pstmt.executeUpdate();
			// 6. 결과 데이터 표시 및 담기
			if (result != 0) {
				System.out.println("리뷰 등록 처리 성공");
			} else {
				throw new Exception("예외 발생 : 리뷰 등록 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		return result;
	} // end of orderInfo()
	
	// 4. 글 삭제
	public int delete(ReviewVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB 클래스에서 확인 완료
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. sql 문 - 아래 LIST
			System.out.println("sql : " + DELETE);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, vo.getRevNo());
			// 5. 실행 객체 실행
			result = pstmt.executeUpdate();
			// 6. 데이터 표시 또는 담기
			if(result == 0) {
				System.out.println("리뷰 번호가 존재하지 않거나 본인 게시글이 아닙니다.");
			} else {
				System.out.println();
				System.out.println(result + "개 리뷰 삭제 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			// 그 외 처리 중 나타나는 오류에 대해서 사용자가 볼 수 잇는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 리뷰 삭제 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		} // end of try ~ finally

		// 결과 데이터를 리턴 해 준다.
		return result;

	} // end of delete
	
	
	public String getTotalReview (String sql) {
		
		sql = null;
		
		sql += TOTALREVIEW;
		sql += LIST;
		sql += " ) ";
		
		return sql;
	}
	
	
	final String LIST = " select"
				+ " r.revNo, r.title, r.content, r.imgFile, r.grade, TO_CHAR(r.writeDate, 'yyyy-mm-dd HH24:Mi') AS writeDate, r.id, "
				+ " m.name, m.photo, r.likeCnt, r.ordNo, r.levNo, r.goodsNo, g.goodsTitle, r.orderNo,  r.parentNo, "
				+ " LISTAGG(gopt.optName, ', ') WITHIN GROUP (ORDER BY gopt.optName) AS optName "
			+ " from review r "
				+ " JOIN member m ON r.id = m.id "
				+ " JOIN goods g ON r.goodsNo = g.goodsNo "
				+ " JOIN orders o ON r.orderNo = o.orderNo "
				+ " JOIN ordersOpt oopt ON o.orderNo = oopt.orderNo "
				+ " JOIN goodsOption gopt ON oopt.optNo = gopt.optNo "
			+ " where g.goodsNo = ? "
			+ " group by "
				+ " r.revNo, r.title, r.content, r.imgFile, r.grade, TO_CHAR(r.writeDate, 'yyyy-mm-dd HH24:Mi'), r.id, "
				+ " m.name, m.photo,  r.likeCnt, r.ordNo, r.levNo, r.goodsNo, g.goodsTitle, r.orderNo,  r.parentNo "
			+ " ORDER BY r.revNo desc";
	
	
	final String TOTALREVIEW = " select "
			+ " COUNT(*) AS review_count, "
			+ " SUM(subquery.grade) AS total_grade, "
			+ " round(AVG(subquery.grade),2) AS avg_grade "
			+ "from ( " ;
	
	final String GRADECNT = " SELECT "
			+ " count(case when grade >= 4.5 and grade <= 5 then 1 end) as grade5, "
			+ " count(case when grade >= 3.5 and grade <= 4 then 1 end) as grade4, "
			+ " count(case when grade >= 2.5 and grade <= 3 then 1 end) as grade3, "
			+ " count(case when grade >= 1.5 and grade <= 2 then 1 end) as grade2, "
			+ " count(case when grade >= 0.5 and grade <= 1 then 1 end) as grade1 "
			+ " from review "
			+ " where goodsNo = ? "
			;

	
	final String ORDERINFO = " SELECT o.goodsNo, g.goodsImage, g.goodsTitle, g.goodsSubTitle, o.orderNo, "
				+ " LISTAGG(gopt.optName, ', ') WITHIN GROUP (ORDER BY gopt.optName) AS optNames "
			+ " FROM goods g "
				+ " JOIN orders o ON g.goodsNo = o.goodsNo "
				+ " JOIN OrdersOpt oopt ON o.orderNo = oopt.orderNo "
				+ " JOIN goodsOption gopt ON oopt.optNo = gopt.optNo "
			+ " WHERE o.orderNo = ? and g.goodsNo = ?"
			+ " GROUP BY o.goodsNo, g.goodsImage, g.goodsTitle, g.goodsSubTitle, o.orderNo " ;
	
	final String WRITE = " insert into review (revNo, title, content, grade, id, imgFile, goodsNo, orderNo, parentNo)"
			+ " values(review_seq.nextval, ?, ?, ?, ?, ?, ?, ?, null)";
	
	final String DELETE = "delete from review " + " where revNo = ? ";

}
