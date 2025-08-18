package com.sinse.chatroomapp.model;

import com.sinse.chatroomapp.domain.Member;

import java.util.List;

public interface MemberService {

    public List<Member> selectAll();
    public Member select(int member_id);
    public Member selectById(String id);
    public void regist(Member member);
    public void update(Member member);
    public void delete(int member_id);
}
