package com.sinse.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shop.common.exception.ProductImgException;
import com.sinse.shop.common.util.DBManager;
import com.sinse.shop.product.model.ProductImg;


public class ProductImgDAO {
	DBManager dbManager = DBManager.getInstance();
	
	//하나의 제품에 딸려있는 이미지 등록 
	public void insert(ProductImg productImg)  throws ProductImgException{
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=dbManager.getConnection();
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert into product_img(filename, product_id) values(?,?)");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, productImg.getFilename());
			pstmt.setInt(2, productImg.getProduct().getProduct_id());
			int result =pstmt.executeUpdate();
			if(result == 0) {
				throw new ProductImgException("상품 이미지 등록 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductImgException("상품 이미지 등록 실패", e);
		}finally {
			dbManager.release(pstmt);
		}
	}
}
