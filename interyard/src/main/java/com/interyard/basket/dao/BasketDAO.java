package com.interyard.basket.dao;

import java.util.List;
import com.interyard.basket.list.BasketList;
import com.interyard.basket.vo.BasketVO;
import com.interyard.basket.vo.OptVO;
import com.interyard.main.dao.DAO;
import com.interyard.util.db.DB;


public class BasketDAO extends DAO{
	
	
	
	// 1. 리스트 처리
	// BasketController - (Execute) - BasketListService - [BasketDAO.list()]
	public BasketList list(String id) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		BasketList list = null;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			System.out.println(LIST);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(LIST);
			// 검색에 대한 데이터 세팅 - list() 에서만 사용			
			pstmt.setString(1, id); 
			
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null) {
				// rs - > vo -> list
				// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
				if(list == null) list = new BasketList();
				while(rs.next()) {
					// rs -> vo
					BasketVO vo = new BasketVO();
					vo.setBasketNo(rs.getLong("basketNo"));
					vo.setGoodsNo(rs.getLong("goodsNo"));
					vo.setOrderPrice(rs.getLong("orderPrice"));
					vo.setDlvyCharge(rs.getLong("dlvyCharge"));
					vo.setOptNo(rs.getLong("optNo"));
					vo.setOptPrice(rs.getLong("optPrice"));
					vo.setAmount(rs.getLong("amount"));
					vo.setCategoryNo(rs.getLong("categoryNo"));
					
					vo.setId(rs.getString("id"));
					vo.setOptName(rs.getString("optName"));
					vo.setGoodsTitle(rs.getString("goodsTitle"));
					vo.setGoodsMaker(rs.getString("goodsMaker"));
					vo.setGoodsImage(rs.getString("goodsImage"));
					vo.setHopeDate(rs.getString("hopeDate"));
					vo.setGoodsStartDate(rs.getString("goodsStartDate"));
					vo.setGoodsEndDate(rs.getString("goodsEndDate"));
					
					
					// vo -> list
					list.add(vo);
				} // end of while
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("예외 발생 : 장바구니 리스트 처리중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally
		
		// 결과 데이터를 리턴해 준다.
		return list;
	} // end of list()
	
	// 1-2. 판매중이지 않은 장바구니 아이템 삭제
	// BasketController - (Execute) - BasketListService - [BasketDAO.deleteList()]
	public int deleteList(String id) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 DELETELIST
			System.out.println(DELETELIST);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(DELETELIST);	
			pstmt.setString(1, id);
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
							
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		} // end of try ~ catch ~ finally			
		// 결과 데이터를 리턴해 준다.
		
		return result;
	} // end of deleteList()
	
	// 2-1. 다음 시퀀스 번호 가져오기
	// BasketController - (Execute) - BasketWriteService - [BasketDAO.getBasketNo()]
	public long getBasketNo() throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		long basketNo = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 WRITE
			System.out.println(GET_NO);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(GET_NO);			
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null && rs.next()) {								
				basketNo = rs.getLong(1);
			} // end of if			
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 장바구니 번호 가져오기 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}		
		// 결과 데이터를 리턴해 준다.
		return basketNo;
	} // end of getBasketNo()
	
	// 2-2. 장바구니 등록
	// BasketController - (Execute) - BasketWriteService - [BasketDAO.basketWrite()]
	public int basketWrite(BasketVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 WRITE
			System.out.println(BASKET_WRITE);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(BASKET_WRITE);		
			
			pstmt.setLong(1, vo.getGoodsNo());
			pstmt.setLong(2, vo.getOrderPrice());
			pstmt.setLong(3, vo.getDlvyCharge());
			pstmt.setString(4, vo.getHopeDate());
			pstmt.setString(5, vo.getId());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();				
			System.out.println();
			System.out.println("*** 장바구니 등록이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 장바구니 등록 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of basketWrite() 
	
	// 2-3. 장바구니 옵션 등록
	// BasketController - (Execute) - BasketWriteService - [BasketDAO.optWrite()]
	public int optWrite(List<OptVO> optList) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 WRITE
			System.out.println(getOptWriteSql(optList));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getOptWriteSql(optList));						
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();				
			System.out.println();
			System.out.println("*** 장바구니 옵션 등록이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 장바구니 옵션 등록 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of optWrite()
	
	// 3-1. 장바구니 수정
	// BasketController - (Execute) - BasketUpdateService - [BasketDAO.basketUpdate()]
	public int basketUpdate(BasketVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 BASKET_UPDATE
			System.out.println(BASKET_UPDATE);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(BASKET_UPDATE);
			
			pstmt.setLong(1, vo.getOrderPrice());
			pstmt.setString(2, vo.getHopeDate());			
			pstmt.setLong(3, vo.getBasketNo());			
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();				
			System.out.println();
			System.out.println("*** 장바구니 수정이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 장바구니 수정 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of basketUpdate() 
	
	// 3-2. 장바구니 옵션 수정
	// BasketController - (Execute) - BasketUpdateService - [BasketDAO.optUpdate()]
	public int optUpdate(List<OptVO> optList) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 WRITE
			System.out.println(getOptUpdateSql(optList));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getOptUpdateSql(optList));						
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();				
			System.out.println();
			System.out.println("*** 장바구니 옵션 수정이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 장바구니 옵션 수정 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of optUpdate()
	
	// 3-3. 장바구니 옵션 삭제
	// BasketController - (Execute) - BasketUpdateService - [BasketDAO.optDelete()]
	public int optDelete(List<OptVO> optList) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 WRITE
			System.out.println(getOptDeleteSql(optList));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getOptDeleteSql(optList));						
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();				
			System.out.println();
			System.out.println("*** 장바구니 옵션 삭제가 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 장바구니 옵션 삭제 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of optDelete()
	
	// 3-3. 장바구니 옵션 삭제
	// BasketController - (Execute) - BasketDeleteService - [BasketDAO.delete()]
	public int delete(BasketList list) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 WRITE
			System.out.println(getDeleteSql(list));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getDeleteSql(list));	
			pstmt.setString(1, list.get(0).getId());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();				
			System.out.println();
			System.out.println("*** 장바구니 삭제가 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 장바구니 삭제 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of delete()
	
	
	
	//db에서 현재 아이디의 장바구니 데이터 중 판매중이거나 공연중인 데이터를 가져온다.
	private final String LIST = " select b.basketNo, b.goodsNo, to_char(b.hopeDate,'yyyy-mm-dd') hopeDate,g.goodsTitle, g.goodsMaker, g.goodsImage,g.categoryNo,g.goodsStatus,g.goodsStartDate,g.goodsEndDate, b.orderPrice, b.dlvyCharge, b.id,"
					+ " bo.optNo, go.optName, go.optPrice, bo.amount from basket b, goods g, goodsOption go, basketOpt bo "
					+ " where (b.id=?) and (g.goodsStatus = '판매중' or g.goodsStatus = '공연중') and (b.goodsNo = g.goodsNo) and (go.optNo = bo.optNo) and (b.basketNo = bo.basketNo) order by basketNo desc ";
			
	//판매중이나 공연중이 아닌 장바구니 아이템 지워주는 쿼리문
	private final String DELETELIST = "delete from basket where basketNo in ( "
									+ " select basketNo from basket b, goods g where (not g.goodsStatus = '판매중' and not g.goodsStatus = '공연중' ) and (b.id = ?) and (b.goodsNo = g.goodsNo) "
									+ " ) ";
	//basket_seq의 다음 번호를 가져온다.
	private final String GET_NO = "select basket_seq.nextval from dual";
	//basket 테이블에 데이터를 등록한다.
	private final String BASKET_WRITE = "insert into basket(basketNo,goodsNo,orderPrice,dlvyCharge,hopeDate,id) "
								+ " values(basketOpt_seq.nextval ,?,?,?,?,?) ";
	//basketOpt에 데이터를 등록한는 쿼리를 가져오는 메서드
	private String getOptWriteSql(List<OptVO> optList) {
		String sql = "insert into basketOpt(basketOptNo,optNo,amount,basketNo) "
					+ "    select basketOpt_seq.nextval,optNo,amount,(select max(basketNo) from basket) "
					+ "    		from ( ";
		//union all를 이용해 가져온 옵션 정보들을 테이블 형태로 만들어 basketOpt 테이블에 등록한다.
		for(int i=0;i<optList.size();i++) {
			OptVO opt = optList.get(i);
			if(i==0) sql += "select "+opt.getOptNo()+" optNo,"+opt.getAmount()+" amount from dual ";
			else sql += " union all select "+opt.getOptNo()+", "+opt.getAmount()+" from dual";
		}
		sql += " ) ";
		
		return sql;
	}
	//basket 테이블에 한행의 상품 가격과 희망 관람일을 수정한다. 
	private final String BASKET_UPDATE = "update basket set orderPrice=? , hopeDate=? where basketNo=? ";
	//basketOpt 테이블의 데이터를 수정하는 퀴리문을 가져오는 메서드
	private String getOptUpdateSql(List<OptVO> optList) {
		//옵션 테이블에서 수정할 장바구니의 번호를 가져온다.
		long basketNo = optList.get(0).getBasketNo();
		//MERGE INTO targetTable using(sourceTable) on 조건 WHEN MATCHED THEN update/delete  WHEN NOT MATCHED THEN insert
		//- targetTable과  sourceTable 테이블을 조건을 통해 비교하여 조건에 맞으면 업데이트나 삭제를 진행하고 틀리면 등록한다.
		String sql ="MERGE INTO basketOpt t "
				+ "		USING ( ";
		//가져온 옵션 리스트로 sourceTable 생성
		for(int i=0;i<optList.size();i++) {
			OptVO opt = optList.get(i);
			if(i==0) sql += "select "+(i+1)+" rnum, "+opt.getOptNo()+" optNo, "+opt.getAmount()+" amount, "+basketNo+" basketNo from dual ";
			else sql += " union all select "+(i+1)+", "+opt.getOptNo()+", "+opt.getAmount()+", "+basketNo+" from dual ";
		}
		sql +=" ) s "
				//입력된 basketNo인 타겟 테이블의 순서번호가 소스테이블에 존재하면 소스테이블에 데이터를 타켓 테이블에 집어넣고
			+ " ON (s.rnum = (select rnum from(select rownum rnum,basketOptno from basketOpt where basketNo = "+basketNo+") where basketOptno = t.basketOptNo) and t.basketNo = "+basketNo+") "
			+ " WHEN MATCHED THEN "
			+ "    UPDATE SET t.optNo = s.optNo, t.amount = s.amount "
			//존재하지 않으면 소스테이블에 데이터를 basketOpt에서 새로 등록한다.
			+ " WHEN NOT MATCHED THEN "
			+ "    INSERT (basketOptNo, optNo, amount, basketNo) "
			+ "    VALUES (basketOpt_seq.nextval, s.optNo, s.amount, "+basketNo+")";
		
		return sql;
	}
	
	//타켓 데이블에 순서가 소스테이블에 존재하지 않을때 해당 행을 삭제하는 쿼리를 가져오는 메서드 
	private String getOptDeleteSql(List<OptVO> optList) {
		//수정과 삽입후 jsp에서 입력된 옵션과 다른 옵션이 존재하면 삭제한다.
		String sql = " delete from basketOpt where (basketNo="+optList.get(0).getBasketNo()+") and OptNo not in( ";
		//in을 이용해 여러개 행 삭제한다.
		for(int i=0;i<optList.size();i++) {
			if(i==0) sql += optList.get(i).getOptNo();
			else sql += ","+optList.get(i).getOptNo();
		}
		sql += " ) ";
		return sql;
	}
	//장바구니 삭제하는 쿼리를 가져오는 메서드
	private String getDeleteSql(BasketList list) {
		String sql = " delete from basket where basketNo in( ";
		//in을 이용해 여러개 행 삭제한다.
		for(int i=0;i<list.size();i++) {
			if(i==0) sql += list.get(i).getBasketNo();
			else sql += ","+list.get(i).getBasketNo();
		}
		sql += " ) and id=? ";
		return sql;
	}
}
