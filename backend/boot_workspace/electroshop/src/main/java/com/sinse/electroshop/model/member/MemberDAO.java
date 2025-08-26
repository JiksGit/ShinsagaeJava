package com.sinse.electroshop.model.member;

import com.sinse.electroshop.domain.Member;

import java.util.List;

public interface MemberDAO {

    public Member save(Member member);

    public Member login(Member member);

    public Member findById(int member_id);

    public List<Member> findAll();

    public Member update(Member member);

    public void delete(Member member);
}
