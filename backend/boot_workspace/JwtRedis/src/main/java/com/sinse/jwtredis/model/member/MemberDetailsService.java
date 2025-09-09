package com.sinse.jwtredis.model.member;

import com.sinse.jwtredis.Domain.CustomUserDetails;
import com.sinse.jwtredis.Domain.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailsService implements UserDetailsService {

    private final JpaMemberRepository jpaMemberRepository;

    public MemberDetailsService(JpaMemberRepository jpaMemberRepository) {
        this.jpaMemberRepository = jpaMemberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = jpaMemberRepository.findById(username);
        if (member == null) {
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetails(member);
    }
}
