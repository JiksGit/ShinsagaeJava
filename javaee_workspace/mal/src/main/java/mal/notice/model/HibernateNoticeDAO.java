package mal.notice.model;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import mal.domain.Notice;

@Slf4j
@Repository
public class HibernateNoticeDAO implements NoticeDAO{

	@Override
	public List selectAll() {
		return null;
	}

	@Override
	public Notice select(int notice_id) {
		return null;
	}

	@Override
	public void insert(Notice notice) {
		
	}

	@Override
	public void update(Notice notice) {
		
	}

	@Override
	public void delete(int notice_id) {
		
	}

}
