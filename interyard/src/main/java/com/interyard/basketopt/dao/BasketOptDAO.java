package com.interyard.basketopt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.util.db.DB;


public class BasketOptDAO extends DAO{
	public List<AjaxOptVO> list(long goodsNo) throws SQLException{
		List<AjaxOptVO> list = null;
		
		try {
			System.out.println(LIST);
			con = DB.getConnection();
			pstmt = con.prepareStatement(LIST);
			pstmt.setLong(1, goodsNo);
			rs = pstmt.executeQuery();
			
			if(rs!=null) {
				list = new ArrayList<AjaxOptVO>();
				while(rs.next()){
					AjaxOptVO vo =new AjaxOptVO();
					vo.setOptNo(rs.getLong("optNo")); 
					vo.setOptName(rs.getString("optName")); 
					vo.setOptPrice(rs.getLong("optPrice")); 
					vo.setGoodsNo(rs.getLong("goodsNo")); 				 
					vo.setGoodsPrice(rs.getLong("goodsPrice")); 				 
					list.add(vo);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	
	
	private final String LIST = "select go.optNo,go.optName,go.optPrice,go.goodsNo,g.goodsPrice from goodsOption go,goods g "
			+ " where go.goodsNo = ? and (g.goodsNo = go.goodsNo)";
}
	
