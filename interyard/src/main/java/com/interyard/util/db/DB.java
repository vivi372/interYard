package com.interyard.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

	// DB 접속 관련 -> DB 클래스에만 존재 : con을 만들어서 전달
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String ID = "local_interyard";
	private static final String PW = "interyard";
	
	static {
		try {
			// 1. 확인 - 딱 한번만 한다.
			Class.forName(DRIVER);
			System.out.println("1. 드라이버 확인 완료");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("** 드라이버가 없으므로 프로그램을 종료합니다.");
			// System.exit(1);
		}
	}
	
	// DB.getConnection()
	public static final Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, ID, PW);
	}
	
	// 사용한 객체 닫기(자원 반환)
	public static final void close(Connection con, PreparedStatement pstmt)
			throws SQLException {
		// 7. 닫기 - DB 클래스
		if(con != null) con.close();
		if(pstmt != null) pstmt.close();
	}
	
	public static final void close(Connection con, PreparedStatement pstmt,
			ResultSet rs) throws SQLException {
		// 7. 닫기 - DB 클래스
		close(con, pstmt);
		if(rs != null) rs.close();
	}
	
}
