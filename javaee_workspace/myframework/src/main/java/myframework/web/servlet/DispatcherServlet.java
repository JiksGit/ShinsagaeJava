package myframework.web.servlet;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import myframework.web.handler.HandlerMapping;

// 웹애플리케이션의 모든 요청을 1차적으로 처리하는 전면 컨트롤러
public class DispatcherServlet extends HttpServlet{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	/*
	 * 이 서블릿이 초기화될 때, 매핑 파일에 등록된 컨트롤러들만 인스턴스를 생성하여 모아야 하는데,
	 * 이 서블릿이 직접 하지 않고, 개발자가 등록한 핸들러 매핑에게 맡긴다.
	 * 또한 추후 요청을 처리할 때도, 어떤 하위 컨트롤러가 동작해야 하는지도, 핸들러 매핑이 알아서 분석하여
	 * 이 서블릿에게 반환한다. 왜? 어제까지는 요청이 들어올 때 마다 하위 컨트롤러의 인스턴스를 생성하는 방식이기
	 * 때문에 메모리 낭비..
	 */	
	HandlerMapping handlerMapping; // 이 핸들러에 동생 인스턴스들이 uri 키값을 가지고 모여있다.
	JsonObject root; 
	@Override
	public void init(ServletConfig config) throws ServletException {
		//초기화 파라미터 읽기 ( 설정파일의 위치 얻기 ) 
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		
		// 현재 애플리케이션의 정보 얻기
		ServletContext context = config.getServletContext();
		String realPath = context.getRealPath(contextConfigLocation);
		
		try(FileReader reader = new FileReader(realPath)){
			root = JsonParser.parseReader(reader).getAsJsonObject();
			logger.debug("root = " + root);
			
			String mappingType = root.get("mappingType").getAsString();
			logger.debug("우리가 사용할 핸들러 매핑은 " + mappingType);
			
			// 동작할 HandlerMapping이 누구인지는 모르지만, 그 패키지를 포함한 클래스명이 mappingType에
			// 들어있으므로, 스트링을 이용한 클래스 로드를 수행할 수 있는 Class.forName()
			Class clazz = Class.forName(mappingType);
			handlerMapping = (HandlerMapping) clazz.newInstance();
			handlerMapping.setRoot(root);
			handlerMapping.initialize();
			
		} catch(Exception e) {
			
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 해당 요청을 처리
		String uri = request.getRequestURI();
		Controller controller = handlerMapping.getController(uri);
		controller.execute(request, response); // 다형성으로 동작했음..
		
		// 하위 컨트롤러로부터 반환받은 뷰의 이름으로 가지고, 
		JsonObject viewMappings = root.getAsJsonObject("viewMappings");
		Iterator<String> it =viewMappings.keySet().iterator();
		
		String viewPage = null;
		
		while( it.hasNext() ) {
			String viewName = it.next(); 
			if(controller.getViewName().equals(viewName)) {
				viewPage = viewMappings.get(viewName).getAsString();
				break;
			}
		}
		
		// 구해온 결과 페이지를 포워딩으로 처리할 경우
		if(controller.isForward()) {
			RequestDispatcher dis = request.getRequestDispatcher(viewPage);
			dis.forward(request, response);// 포워딩 발생
		} else {
			response.sendRedirect(viewPage);
		}
		
		// 클라이언트로 하여금 결과 페이지를 재접속 하게 처리할 경우(redirect)
		
		
		
		
		
		
		
	}
}
