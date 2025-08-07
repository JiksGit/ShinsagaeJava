package mal.spring.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.SessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.oauth.OAuth20Service;


/*
 * 스프링의 고전적 설정을 담당하는 xml을 대신하는 java class
*/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"mal.shop.controller"})
public class UserWebConfig {

	/* 하위 컨트롤러가 3, 4단계를 수행한 후, DispatcherServlet에게 정확한 파일명을 알려주는 게 아니라
	 * 파일의 일부 단서만 반환한다(ModelAndView에 심어서), 따라서 이 객체를 넘겨받은 DispatcherServlet은
	 * 일부 단서를 직접 해석하지 않고, View에 대한 해석을 담당하는 전담객체에 맡긴다..
	 * 이 View 영역을 전담하는 객체들을 가리켜 스프링에서는 ViewResolver라 한다.
	 * JSP 사용시 주로 사용되는 ViewResolver는 InternalResourceViewResolver
	 * 
	 * 예시 ) 하위 컨트롤러가 notice/list를 심어서 보내면 -->/WEB-INF/views/notice/list.jsp
	 * */
	
	/*
	 * <bean id="handlerMapping" class="SimpleUrlHandlerMapping"></bean>
	 * <bean id="handlerMapping" class="DefaultAnnotationHandlerMapping"></bean>
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	// 구글 로그인 관련 서비스 객체 등록
	/*
	 * @Bean public OAuth20Service googleAuthService() { // 클라이언트 ID, Secret, 콜백 주소,
	 * 리소스owner 접근 범위 ServiceBuilder builder = new ServiceBuilder(
	 * "721206143278-g4tbstj31k9t8pmeiu8mf9p3b7c8urgm.apps.googleusercontent.com");
	 * builder.apiSecret(""); builder.defaultScope("email profile openid");
	 * builder.callback("http://localhost:8888/shop/callback/sns/google"); return
	 * builder.build(GoogleApi20.instance()); }
	 */
}













