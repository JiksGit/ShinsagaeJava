package com.sinse.electroshop.model.member;

import com.sinse.electroshop.domain.Member;
import com.sinse.electroshop.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;

    @Override
    public Member regist(Member member) {
        return memberDAO.save(member);
    }

    @Override
    public Member authenticate(Member member) throws MemberNotFoundException {
        Member obj= memberDAO.login(member);
        if(obj == null){
            throw new MemberNotFoundException("로그인 정보가 일치하지 않습니다.");
        }
        return obj;
    }
}
