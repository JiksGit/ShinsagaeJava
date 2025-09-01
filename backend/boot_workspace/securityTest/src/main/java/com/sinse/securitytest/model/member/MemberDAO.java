package com.sinse.securitytest.model.member;

import com.sinse.securitytest.domain.Member;

import java.util.List;

public interface MemberDAO {

    public void insert(Member member);
    public void update(Member member);
    public void delete(Member member);
    public List<Member> selectAll();

    public Member selectByUsername(String username);
}
