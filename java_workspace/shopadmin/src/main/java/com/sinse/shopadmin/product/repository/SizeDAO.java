package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Size;

public class SizeDAO {

	DBManager dbManager = DBManager.getInstance();
	
	public int insertSize(Size size) {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		int result = 0;
		
		con = dbManager.getConnection();
		
		sql.append("insert into size(size_name) values(?)");
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, size.getSize_name());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}
		
		return result;
	}
	
	public List selectAllSize() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Size> list = new ArrayList();
		
		con = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from size");
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Size size = new Size();
				size.setSize_id(rs.getInt("size_id"));
				size.setSize_name(rs.getString("size_name"));
				list.add(size);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(rs, pstmt);
		}
		
		return list;
	}
}
