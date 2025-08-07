package mal.model.member;

import mal.domain.Member;

public interface MemberService {

	public Member checkDuplicate(String id);

	public void regist(Member member);
}
