package com.sinse.hiberasync.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sinse.hiberasync.model.FoodType;
import com.sinse.hiberasync.model.Store;
import com.sinse.hiberasync.repository.StoreDAO;
import com.sinse.hiberasync.util.Message;

// 맛집 정보 수정 요청을 처리하는 서블릿
public class StoreEdit extends HttpServlet{

	StoreDAO storeDAO = new StoreDAO(); 
	Logger logger = LoggerFactory.getLogger(getClass());
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String store_id = request.getParameter("store_id");
		String store_name = request.getParameter("store_name");
		String tel = request.getParameter("tel");
		String food_type_id = request.getParameter("food_type_id");
		
		logger.debug("store_id = " + store_id);
		logger.debug("store_name = " + store_name);
		logger.debug("tel = " + tel);
		logger.debug("food_type_id = " + food_type_id);
		
		FoodType foodType = new FoodType();
		foodType.setFood_type_id(Integer.parseInt(food_type_id));
		
		Store store = new Store();
		store.setStore_id(Integer.parseInt(store_id));
		store.setStore_name(store_name);
		store.setTel(tel);
		store.setFoodType(foodType);
		
		// 응답 정보 만들기
		Message message = new Message();
		Gson gson = new Gson();
		response.setContentType("application/json;");
		PrintWriter out = response.getWriter();
		
		try {
			storeDAO.update(store);	
			response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 (update success but return null)
			message.setResult("success");
			message.setMsg("수정 성공");
		} catch (Exception e) {
			e.printStackTrace();
			message.setResult("fail");
			message.setMsg(e.getMessage());
		}
		String responseMsg = gson.toJson(message);
		out.print(responseMsg);
	}
	
	
}











