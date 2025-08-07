package test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("서버 가동 되었어요");
		ServletContext context = sce.getServletContext();
		context.setAttribute("tel", "012-341-3220");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("서버 중단 되었어요");
	}

}
