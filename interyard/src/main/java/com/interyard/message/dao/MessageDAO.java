package com.interyard.message.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.message.vo.MessageVO;
import com.interyard.util.db.DB;
import com.webjjang.util.page.PageObject;

public class MessageDAO extends DAO{

	// 1-1. list
	public List<MessageVO> list(PageObject pageObject) throws SQLException {
		List<MessageVO> list = null;
		
		try {
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. 운영 쿼리
			// 4. 실행 객체 만들기
			pstmt = con.prepareStatement(getListSQL(pageObject));
			System.out.println("MessageDAO.list().getListSQL() = " + getListSQL(pageObject));
			int idx = 0; // pstmt 의 순서번호 사용. 먼저 1증가 루 사용
			// 메시지 본인 정보 세팅
			pstmt.setString(++idx, pageObject.getAccepter());
			if (pageObject.getAcceptMode() == 3) 
				pstmt.setString(++idx, pageObject.getAccepter());
			idx = setSearchData(pageObject, pstmt, idx);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 실행 객체 담기 및 출력
			if (rs != null) {
				while (rs.next()) {
					if (list == null) list = new ArrayList<>();

					MessageVO vo = new MessageVO();
					vo.setMsgNo(rs.getLong("msgNo"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setSenderId(rs.getString("senderId"));
					vo.setAccepterId(rs.getString("accepterId"));
					vo.setSendDate(rs.getString("sendDate"));
					vo.setAcceptDate(rs.getString("acceptDate"));
					vo.setRefNo(rs.getLong("refNo"));
					
					list.add(vo);
				} // end of while
			} // end of if
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}
		
		return list;
	} // end of list()
	
	// 1-2. 전체 및 안읽은 메시지 개수
	public int allMsgCnt(String accepterId) throws SQLException {
		int result = 0;
		
		try {
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. 운영 쿼리
			// 4. 실행 객체 만들기
			pstmt = con.prepareStatement(ALLMSGCNT);
			pstmt.setString(1, accepterId);
			pstmt.setString(2, accepterId);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 실행 객체 담기 및 출력
			System.out.println("----- 전체 및 안읽은 메시지 개수 -----");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		return result;
	} // end of allMsgCnt()
	
	// 2-1. 받은 날짜가 null 인 데이터를 현재 날짜로 세팅
	public int setReaded(Long refNo) throws SQLException {
		int result= 0;
		
		try {
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. 운영 쿼리
			// 4. 실행 객체 만들기
			pstmt = con.prepareStatement(READED);
			pstmt.setLong(1, refNo);
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 실행 객체 담기 및 출력
			System.out.println("----- 메시지 읽음 -----");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		return result;
	} // end of setReaded()
	
	// 2-2. view
	public List<MessageVO> view(Long refNo) throws SQLException {
		List<MessageVO> list = null;
		
		try {
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. 운영 쿼리
			// 4. 실행 객체 만들기
			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, refNo);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 실행 객체 담기 및 출력
			if (rs != null) {
				while (rs.next()) {
					if (list == null) list = new ArrayList<>();

					MessageVO vo = new MessageVO();
					vo.setMsgNo(rs.getLong("msgNo"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setSenderId(rs.getString("senderId"));
					vo.setSenderName(rs.getString("senderName"));
					vo.setSenderPhoto(rs.getString("senderPhoto"));
					vo.setSendDate(rs.getString("sendDate"));
					vo.setAccepterId(rs.getString("accepterId"));
					vo.setAccepterName(rs.getString("accepterName"));
					vo.setAccepterPhoto(rs.getString("accepterPhoto"));
					vo.setAcceptDate(rs.getString("acceptDate"));
					vo.setRefNo(rs.getLong("refNo"));
					vo.setOrdNo(rs.getLong("ordNo"));
					vo.setLevNo(rs.getLong("levNo"));
					vo.setParentNo(rs.getLong("parentNo"));
					vo.setUploadFile(rs.getString("uploadFile"));
					
					list.add(vo);
				} // end of while
			} // end of if
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}
		
		return list;
	} // end of view()
	
	// 2-3. 받은 날짜가 변경되면 회원의 새로운 메시지 수가 1 감소함
	public int decMsgCnt(String id) throws SQLException {
		int result = 0;
		
		try {
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. 운영 쿼리
			// 4. 실행 객체 만들기
			pstmt = con.prepareStatement(DECMSGCNT);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			System.out.println("MessageDAO.decMsgCnt() = " + DECMSGCNT);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 실행 객체 담기 및 출력
			System.out.println("----- 메시지 1 감소 -----");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		return result;
	} // end of decNewMsg()
	
	// 3-1. getMsgNo
	public Long getMsgNo() throws SQLException {
		Long msgNo = null;
		
		try {
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. 운영 쿼리
			// 4. 실행 객체 만들기
			pstmt = con.prepareStatement(MSGNO);
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 실행 객체 담기 및 출력
			if (rs != null && rs.next()) {
				msgNo = rs.getLong(1);
			} // end of if
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}
		
		return msgNo;
	} // end of getMsgNo()
	
	// 3-2. incOrdNo
	public int incOrdNo(MessageVO vo) throws SQLException {
		int result = 0;
		
		try {
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. 운영 쿼리
			// 4. 실행 객체 만들기
			pstmt = con.prepareStatement(INCORDNO);
			pstmt.setLong(1, vo.getRefNo());
			pstmt.setLong(2, vo.getOrdNo());
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 실행 객체 담기 및 출력
			System.out.println("----- 순서 번호 1 증가 -----");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		return result;
	} // end of incOrdNo()
	
	// 3-3. 일반 메시지 작성
	public int write(MessageVO vo) throws SQLException {
		int result = 0;
		
		try {
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			System.out.println("MessageDAO.write().MSGVO = " + vo);
			// 3. 운영 쿼리
			// 4. 실행 객체 만들기
			if (!vo.isReply())
				pstmt = con.prepareStatement(NOMALMSG);
			else 
				pstmt = con.prepareStatement(REPLYMSG);
			System.out.println("MessageDAO.write() = " + REPLYMSG);
			pstmt.setLong(1, vo.getMsgNo());
			pstmt.setString(2, vo.getAccepterId());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getSenderId());
			pstmt.setString(6, vo.getUploadFile());
			pstmt.setLong(7, vo.getRefNo());
			pstmt.setLong(8, vo.getOrdNo());
			pstmt.setLong(9, vo.getLevNo());
			if (vo.isReply())
				pstmt.setLong(10, vo.getParentNo());
			
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 실행 객체 담기 및 출력
			System.out.println("----- 메시지 전송 완료 -----");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		return result;
	} // end of nomalMsgWrite()
	
	// 3-4. 안읽은 메시지 수 증가
	public int incNewMsgcnt(String id) throws SQLException {
		int result = 0;
		
		try {
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. 운영 쿼리
			// 4. 실행 객체 만들기
			pstmt = con.prepareStatement(INCNEWMSGCNT);
			pstmt.setString(1, id);
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 실행 객체 담기 및 출력
			System.out.println("----- 메시지 1 증가 -----");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		return result;
	} // end of incNewMsgcnt()
	
	// 4-3. delete
	public int delete(MessageVO vo) throws SQLException {
		int result = 0;
		
		try {
			// 1. 드라이버 접속
			// 2. 오라클 접속
			con = DB.getConnection();
			// 3. 운영 쿼리
			// 4. 실행 객체 만들기
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, vo.getMsgNo());
			pstmt.setString(2, vo.getAccepterId());
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 실행 객체 담기 및 출력
			System.out.println("----- 메시지 영구 삭제 완료 -----");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		return result;
	} // end of write()
	
	// 운영 쿼리
	final String LIST = ""
			+ " select msgNo, title, content, senderId, accepterId, sendDate, acceptDate, refNo "
			+ " from ("
				+ " select msgNo, title, content, senderId, accepterId, sendDate, acceptDate, refNo "
				+ " from ( "
					+ " select msg.msgNo, msg.title, msg.content, msg.senderId, msg.accepterId, "
					+ " to_char(msg.sendDate, 'yyyy-mm-dd') sendDate, "
					+ " to_char(msg.acceptDate, 'yyyy-mm-dd') acceptDate, msg.refNo "
					+ " from message msg, member sm, member am ";
					// mode 조건 처리 & 검색 조건 처리 & 조인 조건
	
	private String getListSQL(PageObject pageObject) {
		String sql = LIST;
		// mode 조건 처리 : 1-받은 / 2-보낸 / 3-전체
		sql += getModeSQL(pageObject);
		// 검색 처리
		sql += getSearch(pageObject);
		// 조인 조건 처리
		sql += getJoin();
		sql += " order by msgNo desc ";
		sql	+= " ) ";
		sql += " )";
		
		return sql;
	}
	
	// 조인 조건
	private String getJoin() {
		String sql = "";
		sql += " and ( ";
		sql += " msg.accepterId = am.id and msg.senderId = sm.id ";
		sql += " ) ";
		return sql;
	}
	
	// 가져올 메시지 모드에 대한 조건 처리
	private String getModeSQL(PageObject pageObject) {
		String sql = " where ( 1=0 ";
		int mode = pageObject.getAcceptMode();
		
		if (mode == 1 || mode == 3) 
			sql += " or accepterId = ? ";
		if (mode == 2 || mode == 3) 
			sql += " or senderId = ? ";
		sql += " ) ";
		
		return sql;
	}
	
	private String getSearch(PageObject pageObject) {
		String sql = "";
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		
		if (word != null && !word.equals("")) {
			sql += "and ( 1=0 ";
			if (key.indexOf("s") >= 0) sql += " or senderId like ? ";
			if (key.indexOf("t") >= 0) sql += " or title like ? ";
			if (key.indexOf("c") >= 0) sql += " or content like ? ";
			sql += " ) ";
		}
		
		return sql;
	}
	
	private int setSearchData(PageObject pageObject, PreparedStatement pstmt, int idx) throws SQLException {
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		
		if (word != null && !word.equals("")) {
			if (key.indexOf("s") >= 0) pstmt.setString(++idx, "%" + word + "%");
			if (key.indexOf("t") >= 0) pstmt.setString(++idx, "%" + word + "%");
			if (key.indexOf("c") >= 0) pstmt.setString(++idx, "%" + word + "%");
		}
		
		return idx;
	}
	
	// 리스트 전체 메시지 개수 구하기
	final String ALLMSGCNT = " update member set newMsgCnt = "
			+ " ( "
			+ " select count(*) from message "
			+ " where accepterId = ? and acceptDate is null "
			+ " ) "
			+ " where id = ? ";
	
	final String READED = ""
			+ " update message set acceptDate = sysdate "
			+ " where (refNo = ?) and acceptDate is null ";
	
	final String VIEW = ""
			+ " select msg.msgNo, msg.title, msg.content, "
			+ "	msg.senderId, sm.name senderName, sm.photo senderPhoto, msg.sendDate, "
			+ "	msg.accepterId, am.name accepterName, am.photo accepterPhoto, msg.acceptDate, "
			+ " msg.uploadFile, msg.refNo, msg.ordNo, msg.levNo, msg.parentNo "
			+ "	from message msg, member sm, member am "
			+ "	where (msg.refNo = ?) and (sm.id = msg.senderId and am.id = msg.accepterId) "
			+ " order by msg.msgNo, msg.ordNo desc ";
	
	final String DECMSGCNT = ""
			+ " update member set newMsgCnt = newMsgCnt - ( "
				+ " select count(*) from message "
				+ " where accepterId = ? and acceptDate is null and refNo = 249 "
			+ " ) where id = ? ";
	
	final String TRASH = ""
			+ " update message set msgStatus = '삭제' where msgNo = ? and accepterId = ? ";
	
	// 등록할 메시지 번호를 받아온다.
	final String MSGNO = ""
			+ " select msg_seq.nextval from dual ";
	
	final String INCORDNO = ""
			+ " update message set ordNo = ordNo + 1 "
			+ " where refNo = ? and ordNo >= ? ";
	
	// 일반 메시지 작성 쿼리
	final String NOMALMSG = ""
			+ " insert into message "
			+ " (msgNo, accepterId, title, content, senderId, uploadFile, refNo, ordNo, levNo) "
			+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	
	// 답장 작성 쿼리
	final String REPLYMSG = ""
			+ " insert into message "
			+ " (msgNo, accepterId, title, content, senderId, uploadFile, refNo, ordNo, levNo, parentNo) "
			+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	
	final String INCNEWMSGCNT = ""
			+ " update member set newMsgCnt = newMsgCnt + 1 "
			+ " where id = ?";
	
	final String DELETE = ""
			+ " delete from message where msgNo = ? and accepterId = ? ";
}
