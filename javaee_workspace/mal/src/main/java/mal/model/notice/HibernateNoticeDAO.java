package mal.model.notice;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mal.domain.Notice;
import mal.exception.NoticeException;

@Slf4j
@Repository
public class HibernateNoticeDAO implements NoticeDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public List selectAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Notice", Notice.class);
		return query.getResultList();
	}

	@Override
	public Notice select(int notice_id) {
		return null;
	}

	@Override
	public void insert(Notice notice) throws NoticeException{
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(notice);
		} catch (Exception e) {
			e.printStackTrace(); // 개발자를 위한 로그 출력
			log.error("등록 실패", e.getMessage(), e);
			throw new NoticeException("등록 실패", e);
		}
	}

	@Override
	public void update(Notice notice) {
		
	}

	@Override
	public void delete(int notice_id) {
		
	}

}
