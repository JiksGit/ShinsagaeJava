package fileServer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class UploadServlet extends HttpServlet{

	// 클라이언트의 요청이 Post방식이므로, doXXX형 중 doPost() 재정의 해야 함
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// <%@ page contentType="text/html; charset=UTF-8"%>
//		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print("post 요청 바듬 한글 깨짐");
		
		// 파일 업로드 컴포넌트 중 cos.jar 써보자
		int maxLimit = 3*1024*1024; // 3M
		MultipartRequest multi = new MultipartRequest(request, "C:\\lecture_workspace\\back_workspace\\javaee_workspace\\fileServer\\src\\main\\webapp\\data",
				maxLimit, "utf-8"); 
		
	}
	
	
	
}
