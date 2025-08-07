package mal.model.member;

import mal.domain.Member;

public interface MemberDAO {

	public Member checkDuplicate(String id);
	
	public void insert(Member member);
}
