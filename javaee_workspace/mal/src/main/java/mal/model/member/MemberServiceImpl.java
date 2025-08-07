package mal.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mal.domain.Member;
import mal.exception.MemberException;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public Member checkDuplicate(String id) throws MemberException{
		return memberDAO.checkDuplicate(id);
	}

	@Override
	public void regist(Member member) {
		memberDAO.insert(member);
		
		// 이메일 발송
		
		
	}

}
