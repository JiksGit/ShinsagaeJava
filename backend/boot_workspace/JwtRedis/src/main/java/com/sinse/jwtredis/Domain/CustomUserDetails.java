package com.sinse.jwtredis.Domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    }

    /*
    * 1) ROLE_ 접두어 사용이유 ( 왜 그냥 USER 라고 하면 되는데, ROLE_를 붙이는가?)
    *      - 시큐리티 내에서는 ROLE_ 접두어가 붙은 문자열을 권한으로 인식함
    * 2) 어디에 사용할 수 있는가?
    *     (1) 다음과 같은 URL 접근 제어에 사용가능
    *          http.authorizeHttpRequest(auth -> auth
    *                .requestMatchers("/admin/**").hasRole("ADMIN")
    *     (2) 서비스나 컨트롤러 메서드에서 어노테이션을 붙혀서 제어 가능
    *          @PreAuthorize("hasRole('ADMIN')")
    *           public void removeMember(){}
    *     (3) 런타임(실행타임)시 현재 유저가 보유한 권한을 확인할 때 사용가능
    *           Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    *           Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
    *
    *           for(GrantedAuthority authority : authorities){
    *               log.debug(authority.getAuthority());
    *           }
    *
    * */
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
//        return List.of(new SimpleGrantedAuthority("ROLE_"+member.getRole().getRole_name()));
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getId();
    }

    public String getEmail(){
        return member.getEmail();
    }

//    public String getRoleName() {
//        return member.getRole().getRole_name();
//    }
}
