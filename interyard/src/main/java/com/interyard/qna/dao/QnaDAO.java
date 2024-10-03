package com.interyard.qna.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.interyard.main.dao.DAO;
import com.interyard.qna.vo.PageObjectQna;
import com.interyard.qna.vo.QnaVO;
import com.interyard.review.util.NameStringUtil;
import com.interyard.util.db.DB;
import com.webjjang.util.page.PageObject;

public class QnaDAO extends DAO {
	
	// 1. Qna 리스트
	public List<QnaVO> list(PageObjectQna pageObject) throws Exception {
		
		// 리스트 저장 변수
		List<QnaVO> list = null;
		
		try {
			// 1. DB 확인 - DB 클래스에서 확인
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. SQL
			System.out.println("SQL = " + getListSQL(pageObject));
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(getListSQL(pageObject));
			// 검색에 대한 데이터 세팅 - list() 만 사용
			int idx = 0; // pstmt의 순서번호 사용. 먼저 1 증가하고 사용한다.
			idx = setSearchData(pageObject, pstmt, idx);
			if (pageObject.getCtg() != null && !pageObject.getCtg().equals("")) {
				pstmt.setString(++idx, "%" + pageObject.getCtg() + "%");
				pstmt.setString(++idx, "%" + pageObject.getSemiCtg() + "%");
			}
			pstmt.setLong(++idx, pageObject.getStartRow());
			pstmt.setLong(++idx, pageObject.getEndRow());
			// 5. 실행 객체 실행
			rs = pstmt.executeQuery();
			// 6. 결과 데이터 표시 및 담기
			if (rs != null) {
				while (rs.next()) {
					if (list == null)
						list = new ArrayList<>();
						
						// 데이터 저장
						QnaVO vo = new QnaVO();
						vo.setQnaNo(rs.getLong("qnaNo"));
						vo.setCtg(rs.getString("ctg"));
						vo.setSemiCtg(rs.getString("semiCtg"));
						vo.setTitle(rs.getString("title"));
						vo.setId(rs.getString("id"));
						vo.setBlindId(NameStringUtil.nameExchange(rs.getString("id")));
						vo.setName(rs.getString("name"));
						vo.setBlindName(NameStringUtil.nameExchange(rs.getString("name")));
						vo.setWriteDate(rs.getString("writeDate"));
						vo.setHit(rs.getLong("hit"));
						vo.setLevNo(rs.getLong("levNo"));
						vo.setParentNo(rs.getLong("parentNo"));
						vo.setAnswerCnt(rs.getLong("answerCnt"));
						vo.setRnum(rs.getLong("rnum"));
						
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
	
	
	
	// 1-2. 전체 데이터 개수 가져오기
	// QnaController - (execute) - QnaListService - [QnaDAO.getTotalRow()]
	public Long getTotalRow(PageObjectQna pageObject) throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		Long totalRow = null;

		try {
			// 1. 드라이버 확인 - DB 클래스에서 확인 완료
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. sql 문 - 아래 LIST
			System.out.println("sql : " + TOTALROW + getCtg((PageObjectQna) pageObject) + getSearch(pageObject));
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(TOTALROW + getCtg((PageObjectQna) pageObject) + getSearch(pageObject));
			int idx = 0;
			idx = setSearchData(pageObject, pstmt, idx);
			if (pageObject.getCtg() != null && !pageObject.getCtg().equals("")) {
				pstmt.setString(++idx, pageObject.getCtg());
				pstmt.setString(++idx, pageObject.getSemiCtg());
			}
			// 5. 실행 객체 실행
			rs = pstmt.executeQuery();
			// 6. 데이터 표시 또는 담기
			if (rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			throw e; // 오류 나면 던진다
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ finally
		
		// 결과 데이터를 리턴 해 준다.
		return totalRow;
		
	} // end of getTotalRow
	
	// 1-3. Qna 리스트
	public List<QnaVO> faqList(PageObjectQna pageObject) throws Exception {
		
		// 리스트 저장 변수
		List<QnaVO> list = null;
		
		try {
			// 1. DB 확인 - DB 클래스에서 확인
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. SQL
			System.out.println("SQL = " + FAQLIST);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(FAQLIST);
			// 5. 실행 객체 실행
			rs = pstmt.executeQuery();
			// 6. 결과 데이터 표시 및 담기
			if (rs != null) {
				while (rs.next()) {
					if (list == null)
						list = new ArrayList<>();
						
						// 데이터 저장
						QnaVO vo = new QnaVO();
						vo.setQnaNo(rs.getLong("qnaNo"));
						vo.setCtg(rs.getString("ctg"));
						vo.setSemiCtg(rs.getString("semiCtg"));
						vo.setTitle(rs.getString("title"));
						vo.setId(rs.getString("id"));
						vo.setBlindId(NameStringUtil.nameExchange(rs.getString("id")));
						vo.setName(rs.getString("name"));
						vo.setBlindName(NameStringUtil.nameExchange(rs.getString("name")));
						vo.setWriteDate(rs.getString("writeDate"));
						vo.setHit(rs.getLong("hit"));
						vo.setLevNo(rs.getLong("levNo"));
						vo.setParentNo(rs.getLong("parentNo"));
						vo.setAnswerCnt(rs.getLong("answerCnt"));
						vo.setRnum(rs.getLong("rnum"));
						
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
	
	// 2-1. Qna 조회수 증가
	public int increase(Long no) throws Exception {
		
		// 결과 저장 변수
		int result = 0;
		
		try {
			// 1. DB 확인 - DB 클래스에서 확인
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. SQL
			System.out.println("SQL = " + INCREASE);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(INCREASE);
			pstmt.setLong(1, no);
			// 5. 실행 객체 실행
			result = pstmt.executeUpdate();
			// 6. 결과 데이터 표시 및 담기
			System.out.println(no + "번 글 조회수 " + result + " 증가 ");
				
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	} // end of increase()
	
	// 2-2. Qna 상세보기
	public QnaVO view(Long no) throws Exception {
		
		// 리스트 저장 변수
		QnaVO vo = null;
		
		try {
			// 1. DB 확인 - DB 클래스에서 확인
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. SQL
			System.out.println("SQL = " + VIEW);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, no);
			// 5. 실행 객체 실행
			rs = pstmt.executeQuery();
			// 6. 결과 데이터 표시 및 담기
			if (rs != null && rs.next()) {
				// 데이터 저장
				vo = new QnaVO();
				vo.setQnaNo(rs.getLong("qnaNo"));
				vo.setCtg(rs.getString("ctg"));
				vo.setSemiCtg(rs.getString("semiCtg"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setId(rs.getString("id"));
				vo.setBlindId(NameStringUtil.nameExchange(rs.getString("id")));
				vo.setName(rs.getString("name"));
				vo.setBlindName(NameStringUtil.nameExchange(rs.getString("name")));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
				vo.setRefNo(rs.getLong("refNo"));
				vo.setOrdNo(rs.getLong("ordNo"));
				vo.setLevNo(rs.getLong("levNo"));
				vo.setParentNo(rs.getLong("parentNo"));
			} // 결과 저장하기 끝
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}
		return vo;
	} // end of view()
	
	// 2-2. Qna 답변리스트
	public List<QnaVO> answerList(Long no) throws Exception {
		
		// 리스트 저장 변수
		List<QnaVO> list = null;
		
		try {
			// 1. DB 확인 - DB 클래스에서 확인
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. SQL
			System.out.println("SQL = " + ANSWERLIST);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(ANSWERLIST);
			pstmt.setLong(1, no);
			// 5. 실행 객체 실행
			rs = pstmt.executeQuery();
			// 6. 결과 데이터 표시 및 담기
			if (rs != null) {
				while (rs.next()) {
					if (list == null)
						list = new ArrayList<>();
						
						// 데이터 저장
						QnaVO vo = new QnaVO();
						vo.setQnaNo(rs.getLong("qnaNo"));
						vo.setCtg(rs.getString("ctg"));
						vo.setSemiCtg(rs.getString("semiCtg"));
						vo.setTitle(rs.getString("title"));
						vo.setContent(rs.getString("content"));
						vo.setId(rs.getString("id"));
						vo.setName(rs.getString("name"));
						vo.setWriteDate(rs.getString("writeDate"));
						vo.setHit(rs.getLong("hit"));
						vo.setRefNo(rs.getLong("refNo"));
						vo.setLevNo(rs.getLong("levNo"));
						vo.setParentNo(rs.getLong("parentNo"));
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
	} // end of answeList()
		
	// 2-3. 답변 리스트
	public List<QnaVO> rnumList(Long rnum) throws Exception {
		
		// 리스트 저장 변수
		List<QnaVO> list = null;
		
		try {
			// 1. DB 확인 - DB 클래스에서 확인
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. SQL
			System.out.println("SQL = " + RNUMLIST);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(RNUMLIST);
			pstmt.setLong(1, rnum);
			pstmt.setLong(2, rnum);
			
			// 5. 실행 객체 실행
			rs = pstmt.executeQuery();
			// 6. 결과 데이터 표시 및 담기
			if (rs != null) {
				while (rs.next()) {
					if (list == null)
						list = new ArrayList<>();
						
						// 데이터 저장
						QnaVO vo = new QnaVO();
						vo.setQnaNo(rs.getLong("qnaNo"));
						vo.setCtg(rs.getString("ctg"));
						vo.setSemiCtg(rs.getString("semiCtg"));
						vo.setTitle(rs.getString("title"));
						vo.setId(rs.getString("id"));
						vo.setName(rs.getString("name"));
						vo.setWriteDate(rs.getString("writeDate"));
						vo.setHit(rs.getLong("hit"));
						vo.setRefNo(rs.getLong("refNo"));
						vo.setOrdNo(rs.getLong("ordNo"));
						vo.setLevNo(rs.getLong("levNo"));
						vo.setParentNo(rs.getLong("parentNo"));
						
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
	} // end of rnumList()
	
	// 3-1. Qna 질문글 작성
	public int qWrite(QnaVO vo) throws Exception {
		
		// 리스트 저장 변수
		int result = 0;
		
		try {
			// 1. DB 확인 - DB 클래스에서 확인
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. SQL
			System.out.println("SQL = " + QWRITE);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(QWRITE);
			pstmt.setString(1, vo.getCtg());
			pstmt.setString(2, vo.getSemiCtg());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getId());
			// 5. 실행 객체 실행
			result = pstmt.executeUpdate();
			// 6. 결과 데이터 표시 및 담기
			if (result == 0) {
				throw new Exception("예외 발생 : 질문 글 등록 중 예외가 발생했습니다.");
			} else {
				System.out.println("질문 글 등록이 정상 처리되었습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		return result;
	} // end of qWrite()
	
	// 3-2. 답변글 등록 - 순서번호 1 증가
	// QnaController - (execute) - QnaWriteService - Qna.DAO.increaseOrdNo()]
	public int increaseOrdNo(QnaVO vo) throws Exception {

		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;

		try {
			// 1. 드라이버 확인 - DB 클래스에서 확인 완료
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. sql 문 - 아래 LIST
			System.out.println("sql : " + INCREASEORDNO);
			// 4. 실행 객체 선언 & 데이터 세팅
			pstmt = con.prepareStatement(INCREASEORDNO);
			pstmt.setLong(1, vo.getRefNo());
			pstmt.setLong(2, vo.getOrdNo());
			// 5. 실행 객체 실행 -> executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 데이터 표시 또는 담기
			System.out.println("QnaDAO.increaseOrdNo() - 순서 번호 1증가 처리 완료 ");

		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if (e.getMessage().indexOf("예외 발생") >= 0)
				throw e; // 오류 나면 던진다
			// 그 외 처리 중 나타나는 오류에 대해서 사용자가 볼 수 잇는 예외로 만들어 전달한다.
			else
				throw new Exception("예외 발생 : Qna 보기 순서번호 증가 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		} // end of try ~ finally

		// 결과 데이터를 리턴 해 준다.
		return result;
	} // end of increaseOrdNo()
	
	// 3-3. 질문답변 등록 글 번호 받아오기
	// QnaController - (execute) - QnaWriteService - [QnaDAO.getNo()]
	public Long getNo() throws Exception {
		// 결과를 저장할 수 있는 변수 선언.
		Long no = null;

		try {
			// 1. 드라이버 확인 - DB 클래스에서 확인 완료
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. sql 문 - 아래 LIST
			System.out.println("sql : " + NO);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(NO);
			// 5. 실행 객체 실행
			rs = pstmt.executeQuery();
			// 6. 데이터 표시 또는 담기
			if (rs != null && rs.next()) {
				no = rs.getLong(1);
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			throw e; // 오류 나면 던진다
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ finally
		
		// 결과 데이터를 리턴 해 준다.
		return no;
	}
	
	// 3-4. Qna 답변글 작성
	public int aWrite(QnaVO vo) throws Exception {
		
		// 리스트 저장 변수
		int result = 0;
		
		try {
			// 1. DB 확인 - DB 클래스에서 확인
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. SQL
			System.out.println("SQL = " + AWRITE);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(AWRITE);
			pstmt.setString(1, vo.getCtg());
			pstmt.setString(2, vo.getSemiCtg());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getId());
			pstmt.setLong(6, vo.getRefNo());
			pstmt.setLong(7, vo.getOrdNo());
			pstmt.setLong(8, vo.getLevNo());
			pstmt.setLong(9, vo.getParentNo());
			
			// 5. 실행 객체 실행
			result = pstmt.executeUpdate();
			// 6. 결과 데이터 표시 및 담기
			if (result == 0) {
				throw new Exception("예외 발생 : 답변 글 등록 중 예외가 발생했습니다.");
			} else {
				System.out.println("답변 글 등록이 정상 처리되었습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		return result;
	} // end of aWrite()
	
	// 4-1. 글 수정
	public int update(QnaVO vo) throws Exception {
		
		// 리스트 저장 변수
		int result = 0;
		
		try {
			// 1. DB 확인 - DB 클래스에서 확인
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. SQL
			System.out.println("SQL = " + QUPDATE);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(QUPDATE);
			pstmt.setString(1, vo.getCtg());
			pstmt.setString(2, vo.getSemiCtg());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setLong(5, vo.getQnaNo());
			// 5. 실행 객체 실행
			result = pstmt.executeUpdate();
			// 6. 결과 데이터 표시 및 담기
			if (result == 0) {
				throw new Exception("예외 발생 : 글 수정 중 예외가 발생했습니다.");
			} else {
				System.out.println("글 수정 처리되었습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		return result;
	} // end of qUpdate()
	
	// 5. 글 삭제 처리
	public int delete(Long no) throws Exception {
		
		// 리스트 저장 변수
		int result = 0;
		
		try {
			// 1. DB 확인 - DB 클래스에서 확인
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. SQL
			System.out.println("SQL = " + DELETE);
			// 4. 실행 객체 선언
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, no);
			// 5. 실행 객체 실행
			result = pstmt.executeUpdate();
			// 6. 결과 데이터 표시 및 담기
			if (result == 0) {
				throw new Exception("예외 발생 : 글 삭제 처리 중 예외가 발생했습니다.");
			} else {
				System.out.println("글 삭제가 정상 처리되었습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		return result;
	} // end of qUpdate()
	
		

	final String LIST = " select rnum, qnaNo, ctg, semiCtg, title, content, id, name, writeDate, hit, levNo, refNo, ordNo, parentNo, answerCnt "
			+ " from ( select rownum rnum, qnaNo, ctg, semiCtg, title, content, id, name, writeDate, hit, levNo, refNo, ordNo, parentNo, answerCnt"
				+ " from (select q.qnaNo, q.ctg, q.semiCtg, q.title, q.content, q.id, m.name,"
					+ " to_char(q.writeDate, 'yyyy-MM-dd HH24:Mi') writeDate, "
					+ " q.hit, q.levNo , q.refNo, q.ordNo, q.parentNo, "
					+ " (select count(*) from qna where refNo = q.refNo) -1 as answerCnt "
						+ " from qna q, member m "
						+ " where levNo = 0  "
						;
	
	final String TOTALROW = " select count(*) from qna q where 1=1 and levNo = 0";
	
	// LIST에 검색을 처리해서 만들어지는 sql문 작성 메서드
		private String getListSQL(PageObjectQna pageObject) {
		String sql = LIST;
		sql += getCtg(pageObject);
		sql += getSearch(pageObject);
		sql += " and ( m.id = q.id ) " ;
		sql += " order by q.refNo desc, q.ordNo " + " ) "
				+ " ) where rnum between ? and ? ";
		
		return sql;
	}
		
	// 리스트의 검색만 처리하는 쿼리 - where
	private String getCtg(PageObjectQna pageObject) {
		// where 뒤에 false를 붙힌다. 뒤에가 true 면 true
		String sql = "";
		String ctg = pageObject.getCtg();
		// key 안에 t가 포함되어 있으면 title로 검생을 한다.
		if(ctg != null && !ctg.equals("")) {
			sql += " and ( 1=0 ";
			sql += " or ( q.ctg like ? ";
			sql += " and ( q.semiCtg like ? ";
			sql += " ) ) )";
		}
		return sql;
	}
		
	// 리스트의 검색만 처리하는 쿼리 - where
	private String getSearch(PageObject pageObject) {
		// where 뒤에 false를 붙힌다. 뒤에가 true 면 true
		String sql = "";
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		// key 안에 t가 포함되어 있으면 title로 검생을 한다.
		if(word != null && !word.equals("")) {
			sql += " and ( 1=0 ";
			if(key.indexOf("t") >= 0) sql += " or q.title like ? ";
			if(key.indexOf("c") >= 0) sql += " or q.content like ? ";
			if(key.indexOf("i") >= 0) sql += " or q.id like ? ";
			sql += " ) ";
		}
		return sql;
	}
	
	// 검색 쿼리의 ?(물음표) 데이터를 세팅하는 메서드
	private int setSearchData(PageObjectQna pageObject, PreparedStatement pstmt, int idx) throws SQLException {

		String key = pageObject.getKey();
		String word = pageObject.getWord();
		
		if(word != null && !word.equals("")) {
			// key 안에 t가 포함되어 있으면 title로 검생을 한다.
			// % + 데이터 + % -> like 연산자
			if(key.indexOf("t") >= 0) pstmt.setString(++idx, "%" + word + "%");
			if(key.indexOf("c") >= 0) pstmt.setString(++idx, "%" + word + "%");
			if(key.indexOf("i") >= 0) pstmt.setString(++idx, "%" + word + "%");
		}
		return idx;
	}
	
	final String RNUMLIST = "select rnum, qnaNo, ctg, semiCtg, title, id, name, writeDate, hit, levNo, refNo, ordNo, parentNo "
			+ " from (select rownum rnum, qnaNo, ctg, semiCtg, title, id, name, writeDate, hit, levNo, refNo, ordNo, parentNo "
				+ " from (select q.qnaNo, q.ctg, q.semiCtg, q.title, q.id, m.name, "
					+ "	to_char(q.writeDate, 'yyyy-mm-dd') writeDate, "
					+ "	q.hit, q.levNo , q.refNo, q.ordNo, q.parentNo "
					+ " from qna q, member m where q.id = m.id and levNo = 0 ) ) "
			+ " where rnum = ? -1 "
			+ " or rnum = ? +1 ";
	
	final String FAQLIST = " select rownum rnum, qnaNo, ctg, semiCtg, title, content, id, name, writeDate, hit, levNo, refNo, ordNo, parentNo, answerCnt"
			+ " from ( select q.qnaNo, q.ctg, q.semiCtg, q.title, q.content, q.id, m.name, "
			+ "	to_char(q.writeDate, 'yyyy-MM-dd HH24:Mi') writeDate,  "
			+ " q.hit, q.levNo , q.refNo, q.ordNo, q.parentNo,  (select count(*) from qna where refNo = q.refNo) -1 as answerCnt "
			+ " from qna q, member m where levNo = 0 and m.id = q.id order by q.hit desc "
			+ " ) where rownum between 1 and 3";
	
	final String INCREASE = " update qna set hit = hit + 1 where qnaNo = ?";
	
	final String VIEW = " select q.qnaNo, q.ctg, q.semiCtg, q.title, q.content, q.id, m.name, "
			+ "	to_char(q.writeDate, 'yyyy-MM-dd HH24:Mi') writeDate, "
			+ "	q.hit, q.refNo, q.ordNo, q.levNo, q.parentNo "
			+ "	from qna q, member m"
			+ " where q.id = m.id and qnaNo = ? ";
	
	final String ANSWERLIST = " select q.qnaNo, q.ctg, q.semiCtg, q.title, q.content, q.id, m.name, "
			+ " to_char(q.writeDate, 'yyyy-MM-dd HH24:Mi') writeDate, 	q.hit, q.refNo, q.ordNo, q.levNo, q.parentNo "
			+ " from qna q, member m "
			+ " where q.id = m.id and refNo = ? and levNo != 0 "
			+ " order by  ordNo asc";
	
	final String QWRITE = " insert into qna (qnaNo, ctg, semiCtg, title, content, id, refNo, ordNo, levNo, parentNo) "
			+ " values(qna_seq.nextval, ?, ?, ?, ?, ?, qna_seq.nextVal, 1, 0, null) ";
	
	final String INCREASEORDNO = "update qna set ordNo = ordNo + 1 " 
			+ " where refNo = ? and ordNo >= ?";
	
	// 등록할 글 번호를 받아오는 쿼리 작성
	final String NO = "select qna_seq.nextval from dual ";
	
	final String AWRITE = " insert into qna (qnaNo, ctg, semiCtg, title, content, id, refNo, ordNo, levNo, parentNo) "
			+ " values(qna_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	
	final String QUPDATE = " update qna set ctg = ?, semiCtg = ?, title = ?, content = ?"
			+ " where qnaNo = ? ";
	
	final String DELETE = " delete qna where qnaNo = ? ";
	
}
