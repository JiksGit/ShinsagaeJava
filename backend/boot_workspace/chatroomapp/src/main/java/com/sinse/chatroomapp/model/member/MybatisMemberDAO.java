package com.sinse.chatroomapp.model.member;

import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.exception.MemberException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mybatisMemberDAO")
public class MybatisMemberDAO implements MemberDAO {

    private MemberMapper memberMapper;

    public MybatisMemberDAO(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public List<Member> selectAll() {
        return memberMapper.selectAll();
    }

    @Override
    public Member select(int member_id) {
        return memberMapper.select(member_id);
    }

    @Override
    public Member login(Member member) {
        Member obj=memberMapper.login(member);
        return obj;
    }

    @Override
    public void insert(Member member) throws MemberException {
        try {
            memberMapper.insert(member);
        } catch( DataAccessException e) {
            throw new MemberException("등록 실패", e);
        }
    }

    @Override
    public void update(Member member) throws MemberException {
        try{
            memberMapper.update(member);
        } catch( DataAccessException e) {
            throw new MemberException("업데이트 실패", e);
        }
    }

    @Override
    public void delete(int member_id) throws MemberException {
        try {
            memberMapper.delete(member_id);
        }  catch( DataAccessException e) {
            throw new MemberException("삭제 실패", e);
        }
    }
}
