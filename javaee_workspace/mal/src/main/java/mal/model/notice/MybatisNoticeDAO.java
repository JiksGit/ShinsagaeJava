package mal.model.notice;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import mal.domain.Notice;
import mal.exception.NoticeException;

// 애플리케이션 설계 분야에서 CRUD를 수행하는 역할을 가리켜 Repository라 한다.
// @EnableWebMvc 애노테이션에 의해 @Controller, @Repository, @Service, @Component 등을 찾아 인스턴스
// 생성하여 싱글턴으로 관리
@Slf4j
@Repository
public class MybatisNoticeDAO implements NoticeDAO{
	
	// 스프링 컨테이너로 하여금 자동으로 주입시켜달라!
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		log.debug("DAO의 selectAll() 도달");
		return sqlSessionTemplate.selectList("Notice.selectAll");
	}

	@Override
	public Notice select(int notice_id) {
		return null;
	}

	@Override
	public void insert(Notice notice) throws NoticeException{
		int result = sqlSessionTemplate.insert("Notice.insert", notice);
		if(result < 1) {
			log.error("글 등록 실패");
			throw new NoticeException("글 등록 실패");
		}
	}

	@Override
	public void update(Notice notice) {
		
	}

	@Override
	public void delete(int notice_id) {
		
	}
}
