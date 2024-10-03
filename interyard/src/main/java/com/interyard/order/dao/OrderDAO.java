package com.interyard.order.dao;

import com.interyard.main.dao.DAO;
import com.interyard.order.list.OrderList;
import com.interyard.order.vo.OrderVO;
import com.interyard.util.db.DB;
import com.webjjang.util.page.PageObject;

public class OrderDAO extends DAO{
	
	// 1-1. 리스트 처리 글 수 구하기
	// OrderController - (Execute) - OrderListService - [OrderDAO.getTotalRow()]
	public Long getTotalRow(PageObject pageObject) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		long totalRow = 0;
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 GETTOTALROW
			System.out.println(GETTOTALROW);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(GETTOTALROW);	
			pstmt.setString(1, pageObject.getAccepter());
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
	
	// 1. 리스트 처리
	// OrderController - (Execute) - OrderListService - [OrderDAO.list()]
	public OrderList list(PageObject pageObj) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		OrderList list = null;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 LIST
			System.out.println(LIST);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(LIST);
			// 검색에 대한 데이터 세팅 - list() 에서만 사용			
			pstmt.setString(1, pageObj.getAccepter());
			pstmt.setLong(2, pageObj.getStartRow()); // 기본 값 = 1
			pstmt.setLong(3, pageObj.getEndRow()); // 기본 값 = 10
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null) {
				// rs - > vo -> list
				// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
				if(list == null) list = new OrderList();
				while(rs.next()) {
					// rs -> vo
					OrderVO vo = new OrderVO();
					vo.setOrderNo(rs.getLong("orderNo"));
					vo.setGoodsNo(rs.getLong("goodsNo"));
					vo.setCategoryNo(rs.getLong("categoryNo"));
					vo.setOrderPrice(rs.getLong("orderPrice"));
					vo.setDlvyCharge(rs.getLong("dlvyCharge"));
					vo.setOptNo(rs.getLong("optNo"));					
					vo.setAmount(rs.getLong("amount"));
					
					vo.setId(rs.getString("id"));
					vo.setOrderState(rs.getString("orderState"));
					vo.setOptName(rs.getString("optName"));
					vo.setGoodsTitle(rs.getString("goodsTitle"));					
					vo.setGoodsImage(rs.getString("goodsImage"));
					vo.setHopeDate(rs.getString("hopeDate"));
					vo.setOrderDate(rs.getString("orderDate"));
					vo.setConfirmDate(rs.getString("confirmDate"));
					
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
	
	// 1-1. 관리자 리스트 처리 글 수 구하기
	// OrderController - (Execute) - OrderAdminListService - [OrderDAO.getAdminTotalRow()]
	public Long getAdminTotalRow(PageObject pageObject) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		long totalRow = 0;
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 getAdminTotalRowSQL(pageObject)
			System.out.println(getAdminTotalRowSQL(pageObject));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getAdminTotalRowSQL(pageObject));				
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
	} // end of getAdminTotalRow()
	
	// 1-2. 관리자 리스트 처리
	// OrderController - (Execute) - OrderAdminListService - [OrderDAO.adminList()]
	public OrderList adminList(PageObject pageObj) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		OrderList list = null;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 getAdminListSQL(pageObj)
			System.out.println(getAdminListSQL(pageObj));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getAdminListSQL(pageObj));
			// 검색에 대한 데이터 세팅 - list() 에서만 사용				
			pstmt.setLong(1, pageObj.getStartRow()); // 기본 값 = 1
			pstmt.setLong(2, pageObj.getEndRow()); // 기본 값 = 10
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null) {
				// rs - > vo -> list
				// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
				if(list == null) list = new OrderList();
				while(rs.next()) {
					// rs -> vo
					OrderVO vo = new OrderVO();
					vo.setOrderNo(rs.getLong("orderNo"));
					vo.setGoodsNo(rs.getLong("goodsNo"));
					vo.setCategoryNo(rs.getLong("categoryNo"));
					vo.setOrderPrice(rs.getLong("orderPrice"));
					vo.setDlvyCharge(rs.getLong("dlvyCharge"));					
					vo.setPostNo(rs.getLong("postNo"));
					
					vo.setId(rs.getString("id"));
					vo.setOrderState(rs.getString("orderState"));					
					vo.setGoodsTitle(rs.getString("goodsTitle"));					
					vo.setGoodsPublicher(rs.getString("goodsPublicher"));
					vo.setGoodsPrice(rs.getLong("goodsPrice"));
					vo.setGoodsCost(rs.getLong("goodsCost"));
					vo.setGoodsStatus(rs.getString("goodsStatus"));
					vo.setCategoryName(rs.getString("categoryName"));
					
					vo.setPayWay(rs.getString("payWay"));
					vo.setPayDetail(rs.getString("payDetail"));					
					vo.setRecipient(rs.getString("recipient"));
					vo.setName(rs.getString("name"));
					vo.setMemberTel(rs.getString("memberTel"));
					vo.setTel(rs.getString("tel"));
					vo.setHopeDate(rs.getString("hopeDate"));
					vo.setOrderDate(rs.getString("orderDate"));
					vo.setConfirmDate(rs.getString("confirmDate"));
					vo.setAddr(rs.getString("addr"));					
					
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
	} // end of adminList()
	
	// 2. 상세보기 처리
	// OrderController - (Execute) - OrderViewService - [OrderDAO.view()]
	public OrderList view(OrderVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		OrderList list = null;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 VIEW
			System.out.println(VIEW);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(VIEW);
			// 검색에 대한 데이터 세팅 - list() 에서만 사용			
			pstmt.setString(1, vo.getId());
			pstmt.setLong(2, vo.getOrderNo()); 
			
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			if(rs != null) {
				// rs - > vo -> list
				// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
				if(list == null) list = new OrderList();
				while(rs.next()) {
					// rs -> vo
					OrderVO viewVO = new OrderVO();
					viewVO.setOrderNo(rs.getLong("orderNo"));
					viewVO.setGoodsNo(rs.getLong("goodsNo"));
					viewVO.setCategoryNo(rs.getLong("categoryNo"));
					viewVO.setOrderPrice(rs.getLong("orderPrice"));
					viewVO.setDlvyCharge(rs.getLong("dlvyCharge"));
					viewVO.setOptNo(rs.getLong("optNo"));					
					viewVO.setAmount(rs.getLong("amount"));
					viewVO.setPostNo(rs.getLong("postNo"));
					
					viewVO.setId(rs.getString("id"));
					viewVO.setOrderState(rs.getString("orderState"));
					viewVO.setOptName(rs.getString("optName"));
					viewVO.setGoodsTitle(rs.getString("goodsTitle"));					
					viewVO.setGoodsImage(rs.getString("goodsImage"));
					viewVO.setPayWay(rs.getString("payWay"));
					viewVO.setPayDetail(rs.getString("payDetail"));
					viewVO.setDlvyName(rs.getString("dlvyName"));
					viewVO.setRecipient(rs.getString("recipient"));
					viewVO.setTel(rs.getString("tel"));
					viewVO.setAddr(rs.getString("addr"));
					viewVO.setDlvyMemo(rs.getString("dlvyMemo"));
					viewVO.setHopeDate(rs.getString("hopeDate"));
					viewVO.setOrderDate(rs.getString("orderDate"));
					viewVO.setConfirmDate(rs.getString("confirmDate"));
						
					// vo -> list
					list.add(viewVO);				
				}
			} // end of if
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("예외 발생 : 주문 상세보기 처리중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		} // end of try ~ catch ~ finally
		
		// 결과 데이터를 리턴해 준다.
		return list;
	} // end of view()
	
	
	// 3-1. 주문 등록 폼 데이터 가져오기
	// OrderController - (Execute) - OrderWriteFormService - [OrderDAO.writeForm()]
	public OrderList writeForm(String[] goodsNos) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		OrderList orderList = null;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 getWriteFormSql(goodsNos)
			System.out.println(getWriteFormSql(goodsNos));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getWriteFormSql(goodsNos));			
				
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			rs = pstmt.executeQuery();			
			
			// 6. 표시 또는 담기
			if(rs != null) {				
				// list가 null이면 생성해서 저장할 수 있게 해줘야 한다.
				if(orderList == null) orderList = new OrderList();
				while(rs.next()) {
					OrderVO vo = new OrderVO();
					vo.setGoodsNo(rs.getLong("goodsNo"));
					vo.setGoodsTitle(rs.getString("goodsTitle"));
					vo.setGoodsImage(rs.getString("goodsImage"));
					vo.setCategoryNo(rs.getLong("categoryNo"));
					orderList.add(vo);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 주문 등록 폼 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt, rs);
		}		
		// 결과 데이터를 리턴해 준다.
		return orderList;
	} // end of writeForm() 
	
	// 3-2. 주문 등록
	// OrderController - (Execute) - OrderWriteService - [OrderDAO.write()]
	public String write(OrderList list, OrderList optList) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		String writeOptSql = null;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 WRITE
			//주문 정보 리스트와 옵션 정보 리스트를 입력해 주문 등록 쿼리와 주문 옵션 등록 쿼리 작성 후 반환
			String sql = getWriteSql(list,optList);
			System.out.println(sql);
			//반환된 쿼리 /을 기준으로 잘라 주문 등록 쿼리와 주문 옵션 등록 쿼리로 나누기
			String writeSql = sql.substring(0,sql.indexOf("/"));
			writeOptSql = sql.substring(sql.indexOf("/")+1);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(writeSql);			
				
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			pstmt.executeUpdate();		
			
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 주문 등록 폼 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return writeOptSql;
	} // end of write() 
	
	// 3-3. 주문 옵션 등록
	// OrderController - (Execute) - OrderWriteService - [OrderDAO.writeOpt()]
	public int writeOpt(String writeOptSql) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 WRITE			
			System.out.println(writeOptSql);
			
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(writeOptSql);			
				
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();		
			
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 주문 등록 폼 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of writeOpt() 	
	
	
	// 4. 주문 상태 수정
	// OrderController - (Execute) - OrderStateUpdateService - [OrderDAO.stateUpdate()]
	public int stateUpdate(OrderVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 STATEUPDATE
			System.out.println(STATEUPDATE);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(STATEUPDATE);	
			pstmt.setString(1, vo.getOrderState());
			pstmt.setString(2, vo.getCancleReason());
			pstmt.setString(3, vo.getId());
			pstmt.setLong(4, vo.getOrderNo());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();				
			System.out.println();
			System.out.println("*** 주문 상태 수정이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 주문 상태 수정 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of stateUpdate()
	
	// 5. 주문 주소 수정
	// OrderController - (Execute) - OrderStateUpdateService - [OrderDAO.dlvyUpdate()]
	public int dlvyUpdate(OrderVO vo) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 DLVYUPDATE
			System.out.println(DLVYUPDATE);
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(DLVYUPDATE);	
			pstmt.setString(1,vo.getDlvyName());		
			pstmt.setString(2,vo.getRecipient());		
			pstmt.setString(3,vo.getTel());		
			pstmt.setString(4,vo.getAddr());		 
			pstmt.setLong(5,vo.getPostNo());		 
			pstmt.setString(6, vo.getId());
			pstmt.setLong(7, vo.getOrderNo());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();				
			System.out.println();
			System.out.println("*** 주문 주소 수정이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 주문 주소 수정 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of dlvyUpdate()
	
	
	
	// 6.  주문 삭제
	// OrderController - (Execute) - OrderDeleteService - [OrderDAO.delete()]
	public int delete(long[] orderNos) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 getDeleteSql(orderNos)
			System.out.println(getDeleteSql(orderNos));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getDeleteSql(orderNos));				
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();				
			System.out.println();
			System.out.println("*** 주문 삭제가 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 주문 삭제 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of delete()
	
	// 7-1.  주문 일괄 수정
	// OrderController - (Execute) - OrderAllUpdateService - [OrderDAO.allUpdate()]
	public int allUpdate(OrderList list) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 getAllUpdateSql(list)
			System.out.println(getAllUpdateSql(list));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getAllUpdateSql(list));	
			pstmt.setString(1, list.get(0).getOrderState());
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();				
			System.out.println();
			System.out.println("*** 주문 일괄 변경이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 주문 일괄 변경 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of allUpdate()
	
	// 7-2. 구매확정일 수정
	// OrderController - (Execute) - OrderAllUpdateService - [OrderDAO.confirmDateUpdate()]
	public int confirmDateUpdate(OrderList list) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 getConfirmDateUpdateSql(list)
			System.out.println(getConfirmDateUpdateSql(list));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getConfirmDateUpdateSql(list));			
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();				
			System.out.println();
			System.out.println("*** 구매 확인일 변경이 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 구매 확인일 변경 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of confirmDateUpdate()
	
	// 7-2. 구매확정일 초기화
	// OrderController - (Execute) - OrderAllUpdateService - [OrderDAO.confirmDateReset()]
	public int confirmDateReset(OrderList list) throws Exception{
		// 결과를 저장할 수 있는 변수 선언.
		int result = 0;		
		try {
			// 1. 드라이버 확인 - DB
			// 2. 연결
			con = DB.getConnection();
			// 3. sql - 아래 getConfirmDateUpdateSql(list)
			System.out.println(getConfirmDateResetSql(list));
			// 4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(getConfirmDateResetSql(list));			
			// 5. 실행 - update : executeUpdate() -> int 결과가 나옴.
			result = pstmt.executeUpdate();				
			System.out.println();
			System.out.println("*** 구매 확인일 초기화가 완료 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			// 그외 처리 중 나타나는 오류에 대해서 사용자가 볼수 있는 예외로 만들어 전달한다.
			throw new Exception("예외 발생 : 구매 확인일 초기화 DB 처리 중 예외가 발생했습니다.");
		} finally {
			// 7. 닫기
			DB.close(con, pstmt);
		}		
		// 결과 데이터를 리턴해 준다.
		return result;
	} // end of confirmDateReset()
	
	private String getWriteFormSql(String[] goodsNos) {
		String sql="";
		//해당하는 상품 번호의 데이터들을 가져와 union all로 합쳐 데이터를 가져온다.
		for(int i=0;i<goodsNos.length;i++) {			
			if(i==0) sql += " select goodsNo,goodsImage,goodsTitle,categoryNo from goods where goodsNo= "+ goodsNos[i];
			else sql += " union all select goodsNo,goodsImage,goodsTitle,categoryNo from goods where goodsNo= "+ goodsNos[i];
		}
		
		return sql;
	}
	
	
	
	private String getWriteSql(OrderList list,OrderList optList) {		
		int x=0,y=0;
		//주문 등록 쿼리
		String sql = "insert all ";				
		//주문 옵션 등록 쿼리
		String optSql = "insert all ";					
		
		
		for(OrderVO vo:list) {
			sql += " into orders(orderNo,dlvyName,recipient,tel,addr,postNo,dlvyMemo,orderPrice,dlvyCharge,payWay,payDetail,id,goodsNo,orderState,hopeDate) "
				+ " values((select nvl(max(orderNo),0)+"+(++x)+" from orders) ,'"+vo.getDlvyName()+"','"+vo.getRecipient()+"','"+vo.getTel()+"','"+vo.getAddr()+"',"+vo.getPostNo()+",'"+vo.getDlvyMemo()+"',"+vo.getOrderPrice()+","+vo.getDlvyCharge()+",'"+vo.getPayWay()+"','"+vo.getPayDetail()+"','"+vo.getId()+"','"+vo.getGoodsNo()+"','주문 확인','"+vo.getHopeDate()+"') ";
			while(y<optList.size()) {
				if(optList.get(y).getLefOrder()==vo.getOrder()) {
					OrderVO opt = optList.get(y);
					optSql += " into ordersOpt(orderOptNo,optNo,amount,orderNo) "
						+ " values((select nvl(max(orderOptNo),0)+"+(++y)+" from ordersOpt) ,"+opt.getOptNo()+","+opt.getAmount()+", (select nvl(max(orderNo),0)+"+(x-list.size())+" from orders)) ";
				} else break;
			}
		}		
		
		sql += " select * from dual ";
		optSql += " select * from dual ";		
		
		//나중에 분리하기 위해 /로 구분해 둔다.
		sql += "/"+optSql;
		
		
		return sql;			
		
	}
	
	
	
	
	private final String GETTOTALROW="select count(*) cnt from ("
			+ "select o.orderNo, o.goodsNo, o.orderState,  to_char(o.hopeDate,'yyyy-mm-dd') hopeDate,o.orderDate,o.confirmDate,g.goodsTitle, g.goodsImage,g.categoryNo ,o.orderPrice, o.dlvyCharge, o.id,"
			+ " oo.optNo, go.optName, oo.amount from orders o, goods g, goodsOption go, ordersOpt oo "
			+ " where (id=?) and (o.goodsNo = g.goodsNo) and (go.optNo = oo.optNo) and (o.orderNo = oo.orderNo) order by orderNo desc "
			+ ") ";
	
	private final String LIST = "select orderNo,goodsNo,orderState,hopeDate,orderDate,confirmDate,goodsTitle,goodsImage,categoryNo,orderPrice,dlvyCharge,id,optNo,optName,amount from( "
				+ " select rowNum rnum,orderNo,goodsNo,orderState,hopeDate,orderDate,confirmDate,goodsTitle,goodsImage,categoryNo,orderPrice,dlvyCharge,id,optNo,optName,amount from("
					+ " select o.orderNo, o.goodsNo, o.orderState,  to_char(o.hopeDate,'yyyy-mm-dd') hopeDate,o.orderDate,o.confirmDate,g.goodsTitle, g.goodsImage,g.categoryNo ,o.orderPrice, o.dlvyCharge, o.id,"
					+ " oo.optNo, go.optName, oo.amount from orders o, goods g, goodsOption go, ordersOpt oo "
					+ " where (id=?) and (o.goodsNo = g.goodsNo) and (go.optNo = oo.optNo) and (o.orderNo = oo.orderNo) order by orderNo desc "
				+ " ) ) "
			+ " where rnum between ? and ? ";
	
	// getAdminTotalRow에 검색을 처리해서 만들어지는 sql문 작성 메서드
	private String getAdminTotalRowSQL(PageObject pageObj) {
		String sql = "select count(*) cnt from("
				+ "select o.orderNo, o.goodsNo, o.orderState,g.goodsTitle, g.goodsPublicher , g.categoryNo , c.categoryName ,o.orderPrice, o.dlvyCharge, o.id,"
				+ " o.recipient, o.tel, o.addr, o.postNo, o.payWay,o.payDetail from orders o, goods g, category c "				
				+ " where 1=1 ";		
		sql += getSearch(pageObj)+getPeriod(pageObj);
		sql += " and (o.goodsNo = g.goodsNo) and (g.categoryNo = c.categoryNo)) ";
		return sql;
	}
	// AdmingetTotalRow에 검색을 처리해서 만들어지는 sql문 작성 메서드
	private String getAdminListSQL(PageObject pageObj) {
		String sql = " select  orderNo,goodsNo,orderState,hopeDate,orderDate,confirmDate,goodsTitle,goodsPublicher,goodsPrice,goodsCost,goodsStatus,categoryNo,categoryName,orderPrice,dlvyCharge,id,name,memberTel,dlvyAddrNo,recipient,tel,addr,postNo,payWay,payDetail  from("
				+ " select rowNum rnum, orderNo,goodsNo,orderState,hopeDate,orderDate,confirmDate,goodsTitle,goodsPublicher,goodsPrice,goodsCost,goodsStatus,categoryNo,categoryName,orderPrice,dlvyCharge,id,name,memberTel,dlvyAddrNo,recipient,tel,addr,postNo,payWay,payDetail from( "
				+ " select o.orderNo, o.goodsNo, o.orderState, to_char(o.hopeDate,'yyyy-mm-dd') hopeDate,o.orderDate,o.confirmDate,g.goodsTitle, g.goodsPublicher , g.goodsPrice,g.goodsCost,g.goodsStatus,g.categoryNo , c.categoryName ,o.orderPrice, o.dlvyCharge, o.id,m.name,m.tel as memberTel, "
				+ " o.recipient, o.tel, o.addr, o.postNo, o.payWay,o.payDetail from orders o, goods g, category c, member m "
				+ " where 1=1 " ;		
		sql += getSearch(pageObj)+getPeriod(pageObj);
		sql += " and (o.goodsNo = g.goodsNo) and (g.categoryNo = c.categoryNo) and (o.id = m.id)"
				+ " order by orderNo desc "
				+ " ) )"
				+ " where rnum between ? and ? ";
		return sql;
	}
	
	//단어 검색하는 쿼리를 출력하는 메서드
	private String getSearch(PageObject pageObj) {
		String searchSql = " ";
		String word = pageObj.getWord();
		
		if(word != null && !word.equals("")) {
			String key = pageObj.getKey();
			searchSql += " and ( 1=0";			
			if(key.indexOf("n") >= 0) searchSql += " or o.orderNo = '"+word+"'";
			if(key.indexOf("i") >= 0) searchSql += " or o.id like '%"+word+"%'";
			if(key.indexOf("t") >= 0) searchSql += " or goodsTitle like '%"+word+"%'";			
			if(key.indexOf("p") >= 0) searchSql += " or goodsPublicher like '%"+word+"%'";			
			searchSql+=") ";
		}
		return searchSql;
	}	
	//주문 상태를 검색하는 쿼리를 가져오는 메서드
	private String getPeriod(PageObject pageObj) {
		String sql = "";
		String period = pageObj.getPeriod();
		if(period!=null && !period.equals("")) {
			sql += "and ( orderState ";
			if(period.equals("구매 확인")) {
				sql += " in('구매 확인','구매 확인(리뷰작성)'))";
			} else {
				sql += "= '"+period+"')";
			}			
		}
		return sql;
	}
	
	private final String VIEW = "select o.orderNo, o.goodsNo, o.orderState, o.orderDate,o.confirmDate,o.payWay, o.payDetail,  g.goodsTitle, g.goodsImage,g.categoryNo ,o.orderPrice, o.dlvyCharge, o.id,"
			+ " oo.optNo, go.optName, oo.amount, to_char(o.hopeDate,'yyyy-mm-dd') hopeDate,o.dlvyName, o.recipient, o.tel, o.addr, o.postNo,o.dlvyMemo from orders o, goods g, goodsOption go, ordersOpt oo "
			+ " where (o.id=? and o.orderNo=?) and (o.goodsNo = g.goodsNo) and (go.optNo = oo.optNo) and (o.orderNo = oo.orderNo) ";
	
	//주문 상태를 수정하는 쿼리
	private final String STATEUPDATE = "update orders set orderState = ?, cancleReason=? where id=? and orderNo = ?";
	//배송지 정보를 바꾸는 쿼리
	private final String DLVYUPDATE = "update orders set dlvyName = ?,recipient = ?,tel = ?,addr = ?,postNo = ? where id=? and orderNo = ?";	
	
	
	//in을 이용해 다중 삭제 구현
	private String getDeleteSql(long[] orderNos) {
		String sql = " delete from orders where orderNo in( ";
		for(int i=0;i<orderNos.length;i++) {
			if(i==0) sql += orderNos[i];
			else sql += ","+orderNos[i];
		}
		sql += " ) ";
		return sql;
	}
	//in을 이용해 다중 수정 구현
	private String getAllUpdateSql(OrderList list) {
		String sql = " update orders set orderState = ? where (not orderState = '구매 확인(리뷰작성)') and orderNo in( ";
		for(int i=0;i<list.size();i++) {
			if(i==0) sql += list.get(i).getOrderNo();
			else sql += ","+list.get(i).getOrderNo();
		}
		sql += " ) ";
		return sql;
	}
	
	private String getConfirmDateUpdateSql(OrderList list) {
		String sql = " update orders set confirmDate = sysDate where (not orderState in('구매 확인(리뷰작성)','구매 확인')) and orderNo in( ";
		for(int i=0;i<list.size();i++) {
			if(i==0) sql += list.get(i).getOrderNo();
			else sql += ","+list.get(i).getOrderNo();
		}
		sql += " ) ";
		return sql;
	}
	
	private String getConfirmDateResetSql(OrderList list) {
		String sql = " update orders set confirmDate = null where (not orderState = '구매 확인(리뷰작성)') and orderNo in( ";
		for(int i=0;i<list.size();i++) {
			if(i==0) sql += list.get(i).getOrderNo();
			else sql += ","+list.get(i).getOrderNo();
		}
		sql += " ) ";
		return sql;
	}
}
