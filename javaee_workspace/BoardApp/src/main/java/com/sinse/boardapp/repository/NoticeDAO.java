package com.sinse.boardapp.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.sinse.boardapp.model.Notice;

public class NoticeDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url;
	String user;
	String pass;

	// 모든 레코드
	public List selectAll() {
		return null;
	}
	
	// 한 건 가져오기
	public Notice select() {
		return null;
	}
	
	// 한 건 넣기
	public void insert() {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass); // 접속 시도
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 수정
	public void update() {
		
	}
	
	// 삭제
	public void delete() {
		
	}
}
