package com.sinse.jwtredis.model.member;

import com.sinse.jwtredis.Domain.Member;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final JpaMemberRepository jpaMemberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(JpaMemberRepository jpaMemberRepository,  PasswordEncoder passwordEncoder) {
        this.jpaMemberRepository = jpaMemberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void regist(Member member) throws RuntimeException {

        // 평문을 암호화
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        Member obj = jpaMemberRepository.save(member);
        if(obj == null){
            throw new RuntimeException("회원 등록 실패");
        }
    }
}
