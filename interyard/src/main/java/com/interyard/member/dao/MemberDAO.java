package com.interyard.member.dao;

import java.util.ArrayList;
import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.member.vo.LoginVO;
import com.interyard.member.vo.MemberVO;
import com.interyard.util.db.DB;
import com.webjjang.util.page.PageObject;

public class MemberDAO extends DAO{

	// 로그인 처리
	public LoginVO login(LoginVO login) throws Exception{
		
		LoginVO vo = null;
		
		try {
			
			con = DB.getConnection();
			pstmt = con.prepareStatement(LOGIN);
			pstmt.setString(1, login.getId());
			pstmt.setString(2, login.getPw());
			
			rs = pstmt.executeQuery();
			
			if(rs != null && rs.next()) {
				vo = new LoginVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
				vo.setPhoto(rs.getString("photo"));
				vo.setNewMsgCnt(rs.getLong("newMsgCnt"));
			} // end if
			if(vo == null) {
				throw new Exception("아이디가 맞지 않거나 비밀번호가 다릅니다.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DB.close(con, pstmt, rs);
		}
		
		return vo;
	} // end login()
	// 맴버 리스트
	public List<MemberVO> list(PageObject pageObject) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		List<MemberVO> list = null;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getListSQL(pageObject));
			System.out.println("asdasdasdasd" + getListSQL(pageObject));
			pstmt.setString(1, pageObject.getAccepter()); // id = accepter
			pstmt.setLong(2, pageObject.getStartRow());
			pstmt.setLong(3, pageObject.getEndRow());
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null) {
				while(rs.next()) {
					// rs - > vo -> list
					// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
					if(list == null) list = new ArrayList<>();
					// rs -> vo
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setGender(rs.getString("gender"));
					vo.setTel(rs.getString("tel"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));
					vo.setStatus(rs.getString("status"));
					vo.setPhoto(rs.getString("photo"));
					
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
	
	
	public Long getTotalRow(PageObject pageObject) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		Long totalRow = null;
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(TOTALROW );
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null && rs.next()) {
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
	
	
	
	// 회원가입
	public int write(MemberVO vo) throws Exception{
		
		int result = 0;
	
		try {
			con = DB.getConnection();
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getPhoto());
			
			result = pstmt.executeUpdate();
			System.out.println("회원 가입완효");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt);
		}
		
		return result;
	}
	
	// 아이디 중복체크
	public String checkId(String id) throws Exception{
		String result = null;
		try {
			con = DB.getConnection();
			pstmt = con.prepareStatement(CHECKID);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			DB.close(con, pstmt, rs);
		}
		return result;
	}
	
	// 새로운 메시지 
	public Long getMsgCnt(String id) throws Exception{
		Long newMsgCnt = 0L;
		try {
			con = DB.getConnection();
			pstmt = con.prepareStatement(NEWMSGCNT);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()) {
				newMsgCnt = rs.getLong(1);
			}
			} catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
		}finally {
			DB.close(con, pstmt, rs);
		}
		return newMsgCnt;
	}
	
	// 등급명 변경
	public int changeGrade(MemberVO vo) throws Exception{
		int result = 0;
		try {
			
			con = DB.getConnection();
			pstmt = con.prepareStatement(CHANGEGRADE);
			pstmt.setLong(1, vo.getGradeNo());
			pstmt.setString(2, vo.getId());
			
			result = pstmt.executeUpdate();
			if( result == 0)
				throw new Exception("아이디가 맞지 않아요 ::::: 오류 발생");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt);
		}
		return result;
	}
	
	// 상태 변경
	public int changeStatus(MemberVO vo) throws  Exception{
		
		int result = 0;
		try {
			
			con = DB.getConnection();
			pstmt = con.prepareStatement(CHANGESTATUS);
			pstmt.setString(1, vo.getStatus());
			pstmt.setString(2, vo.getId());
			
			result = pstmt.executeUpdate();
			if( result == 0)
				throw new Exception("아이디가 맞지 않아요 ::::: 오류 발생");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt);
		}
		return result;
	}
	
	// 상세보기
	public MemberVO view(String id) throws Exception{
		
		MemberVO vo = null;
	
		try {
			con = DB.getConnection();
			pstmt = con.prepareStatement(VIEW);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				
				vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setStatus(rs.getString("status"));
				vo.setGender(rs.getString("gender"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
				vo.setPhoto(rs.getString("photo"));
				vo.setGradeName(rs.getString("gradeName"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setWriteDate(rs.getString("writeDate"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt,rs);
		}
		
		return vo;
	}
	
	
	// 아이디 찾기
	public String searchId(MemberVO vo) throws Exception{
			String id = null;
			try {
				con = DB.getConnection();
				
				pstmt = con.prepareStatement(SEARCHID);
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getTel());
				rs = pstmt.executeQuery();
				
				if(rs!=null && rs.next()) {
					id = rs.getString("id");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				DB.close(con, pstmt,rs);
			}
			
			return id;
		}
	
	// 비밀번호 찾기
	public String searchPw(MemberVO vo) throws Exception{
			String pw = null;
			try {
				con = DB.getConnection();
				
				pstmt = con.prepareStatement(SEARCHPW);
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getTel());
				pstmt.setString(3, vo.getEmail());
				rs = pstmt.executeQuery();
				if(rs!=null && rs.next()) {
					pw = rs.getString("pw");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				DB.close(con, pstmt,rs);
			}
			
			return pw;
		}
	
	// 상태 변경
	public int veriFy(MemberVO vo) throws  Exception{
			
			int result = 0;
			try {
				
				con = DB.getConnection();
				pstmt = con.prepareStatement(CHANGEPW);
				pstmt.setString(1, vo.getPw());
				pstmt.setString(2, vo.getId());
				result = pstmt.executeUpdate();
				
				if( result == 0)
					throw new Exception("오류 발생 합니다");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				DB.close(con, pstmt);
			}
			return result;
		}

	
	// 이메일 중복체크
	public String checkEmail(String email) throws Exception{
			String result = null;
			try {
				con = DB.getConnection();
				pstmt = con.prepareStatement(CHECKEMAIL);
				pstmt.setString(1,email);
				rs = pstmt.executeQuery();
				if(rs != null && rs.next()) {
					result = rs.getString(1);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				DB.close(con, pstmt, rs);
			}
			return result;
		}	
	
	// 전화번호 중복체크
	public String checkTel(String tel) throws Exception{
		String result = null;
		try {
			con = DB.getConnection();
			pstmt = con.prepareStatement(CHECKETEL);
			pstmt.setString(1,tel);
			rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			DB.close(con, pstmt, rs);
		}
		return result;
	}	
	
	public int update(MemberVO vo) throws Exception{
		int result = 0;
	
		try {
			System.out.println("1");
			con = DB.getConnection();
			System.out.println("1");
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getEmail());
			pstmt.setString(7, vo.getId());
			System.out.println("1");
			
			result = pstmt.executeUpdate();
			System.out.println("1");
			if(result == 0) throw new Exception("오류 발생 합니다");
			System.out.println("수정완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt);
		}
		
		return result;
	}
	

	public int updateConDate(String id) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 UPDATE
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(UPDATE_CONDATE);
			pstmt.setString(1, id);
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if(result == 0) { // 글번호가 존재하지 않는다. -> 예외로 처리한다.
				throw new Exception("예외 발생 : 아이디가 존재하지 않습니다. 정보를 확인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 특별한 예외는 그냥 전달한다.
			if(e.getMessage().indexOf("예외 발생") >= 0) throw e;
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			else throw new Exception("예외 발생 : 최근 접속일 수정 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}
		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of update()
	
	
	final String LIST= ""
			+ " select id, name,gender,tel,gradeName,status,photo , gradeNo "
			+ " from ( "
			+ " select rownum rnum , id, name,gender,tel,gradeName,status,photo , gradeNo "
			+ "from ("
			+ "select m.id, m.name, m.gender, m.tel, g.gradeName, m.status, m.photo , m.gradeNo "
			+ " from member m, grade g "
			+ " where (1=1) and (id != ? )  "
			; 
	
	final String TOTALROW = "select count(*)-1 from member ";
	
	
	private String getListSQL(PageObject pageObject) {
		String sql = LIST; 
		sql += " and (m.gradeNo = g.gradeNo)  order by id desc "
				+ " ) "
				+ " ) where rnum between ? and ? ";
		return sql;
	}
	
	final String LOGIN = "select m.id, m.name, m.gradeNo, "
			+ " g.gradeName, m.photo, m.newMsgCnt  "
			+ " from member m, grade g "
			+ " where (id = ? and pw = ? and status = '정상') "
			+ " and (g.gradeNo = m.gradeNo) ";
	
	final  String CHECKID = " select id from member where id = ? ";
	
	final  String CHECKEMAIL = " select email from member where email = ? ";

	final  String CHECKETEL = " select tel from member where tel = ? ";
	
	final String NEWMSGCNT = " select newMsgCnt from member where id = ? ";
	
	final String CHANGEGRADE = " update member set gradeNo = ? where id = ? ";
	
	final String CHANGESTATUS = " update member set status = ? where id = ? ";
	
	final String WRITE = " insert into member "
			+ "(id,pw,name,gender,birth,tel,email,photo) "
			+ " values(?,?,?,?,?,?,?,?)";
	
	final String VIEW = " select m.id , m.name , m.gender ,to_char(m.birth, 'yyyy-mm-dd') birth , m.tel , m.email , m.photo , "
			+ " m.status , g.gradeName,m.gradeNo , to_char(m.writeDate, 'yyyy-mm-dd') writeDate from member m, grade g where id = ? and "
			+ " m.gradeNo = g.gradeNo "
			;
	
	final String SEARCHID = "select id from member where name = ? and tel = ? ";
	
	final String SEARCHPW = " select pw from member where id = ? and tel = ? and email = ? ";
	
	final String CHANGEPW = " update member set pw = ? where id = ? ";

	final String UPDATE = " update member set "
			+ "name = ?, id = ? ,  gender = ? , tel = ? , birth = ? , email = ? where id = ? "; 
	
	final String UPDATE_CONDATE= "update member "
			+ " set conDate = sysdate where id = ? "; 
}
