package com.interyard.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {

	public Connection con = null; // 연결 객체
	public PreparedStatement pstmt = null; // 실행 객체
	public ResultSet rs = null; // 가져온 데이터 저장 객체
	
}
