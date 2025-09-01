package com.sinse.securitytest.model.member;

import com.sinse.securitytest.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaMemberDAO implements MemberDAO{

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public void insert(Member member) {
    }

    @Override
    public void update(Member member) {
    }

    @Override
    public void delete(Member member) {
    }

    @Override
    public List<Member> selectAll() {
        return List.of();
    }

    @Override
    public Member selectByUsername(String username) {
        return jpaMemberRepository.findByUsername(username);
    }
}
