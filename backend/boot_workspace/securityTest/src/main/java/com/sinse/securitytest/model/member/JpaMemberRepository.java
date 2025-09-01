package com.sinse.securitytest.model.member;

import com.sinse.securitytest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, Integer> {

    public Member findByUsername(String username);
}
