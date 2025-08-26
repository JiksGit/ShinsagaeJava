package com.sinse.electroshop.model.member;

import com.sinse.electroshop.domain.Member;

import java.util.List;

public interface MemberService {

    public Member regist(Member member);

    public Member authenticate(Member member);
}
