package com.sinse.shopadmin.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sinse.shopadmin.common.config.Config;

// AppMain에서 데이터베이스를 핸들링 하지 말고, 보다 중립적인 객체에서 Connection을 얻는 것
// 뿐 아니라, 닫는 것 마저도 대행해주는 기능을 보유한 객체를 선언..
public class DBManager {
	
	private static DBManager instance;
	
	private Connection con;
	
	public DBManager() {
		try {
			// 1) 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2) 접속
			con = DriverManager.getConnection(Config.url, Config.user, Config.pass);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DBManager getInstance() {
		if(instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return con;
	}
	
	// 데이터베이스 관련된 자원을 해제하는 메서드
	public void release(PreparedStatement pstmt) { // DML (insert, update, delete);
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void release(ResultSet rs, PreparedStatement pstmt) { // select;
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void release(ResultSet rs, PreparedStatement pstmt, Connection con) { 
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
