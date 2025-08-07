package mal.model.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mal.domain.Notice;
import mal.exception.NoticeException;

@Slf4j
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService{
	
//	@Qualifier("mybatisNoticeDAO") // 스프링 컨테이너가 보유한 여러 인스턴스 중 원하는 아이디를 
	@Qualifier("hibernateNoticeDAO")
	@Autowired
	private NoticeDAO noticeDAO;

	public List selectAll() {
		log.debug("service의 selectAll() 도달");
		return noticeDAO.selectAll(); // DAO의 메서드 호출
	}

	public Notice select(int notice_id) {
		return null;
	}

	@Transactional
	public void regist(Notice notice) throws NoticeException{
		noticeDAO.insert(notice);
	}

	public void update(Notice notice) {
		
	}

	public void delete(int notice_id) {
		
	}

}
