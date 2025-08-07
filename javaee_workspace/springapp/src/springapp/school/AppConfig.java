package springapp.school;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration // XML을 대신하여, 스프링 컨테이너에게 Bean을 관리하도록 부탁하는 설정 파일
@EnableAspectJAutoProxy
@ComponentScan("springapp.school") // 지정된 패키지에 있는 요소들을 메모리에 올림
public class AppConfig {

	@Bean
	public Bell bell() { // = <bean class="springapp.~.Bell" id="bell"></bean>
		return new Bell();
	}
	
	@Bean
	public Student student() {
		return new Student();
	}
	
}
