package com.sinse.jwtredis.model.member;

import com.sinse.jwtredis.Domain.Member;
import com.sinse.jwtredis.controller.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public class RegistServiceImpl implements RegistService {

    private final RegistRedisService registRedisService;

    public RegistServiceImpl(RegistRedisService registRedisService) {
        this.registRedisService = registRedisService;
    }

    // 회원가입
    @Override
    public void regist(MemberDTO memberDTO) {

        String code = registRedisService.generateCode6();
        memberDTO.setCode(code);

        // 임시 회원 정보를 redis에 등록
        // registRedisService.savePending(memberDTO);

    }
}
