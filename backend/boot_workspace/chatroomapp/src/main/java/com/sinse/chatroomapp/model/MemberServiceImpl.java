package com.sinse.chatroomapp.model;

import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.exception.MemberException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO;

    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public List<Member> selectAll() {
        return memberDAO.selectAll();
    }

    @Override
    public Member select(int member_id) {
        return memberDAO.select(member_id);
    }

    @Override
    public Member selectById(String id) {
        return memberDAO.selectById(id);
    }

    @Override
    public void regist(Member member) throws MemberException {
        memberDAO.insert(member);
    }

    @Override
    public void update(Member member) throws MemberException {
        memberDAO.update(member);
    }

    @Override
    public void delete(int member_id) throws MemberException {
        memberDAO.delete(member_id);
    }
}
