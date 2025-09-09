package com.sinse.jwtredis.security;

import com.sinse.jwtredis.model.member.MemberDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 개발자가 정의한 컨트롤러에서 AuthenticationManager를 사용할 예정이므로, 미리 등록
    // 만일 개발자가 필요한 시점에 new를 해버리면, 스프링이 관리하는 Bean이 아니게 됨..
    // 따라서 @Bean으로 등록해야 함
    @Bean
    public AuthenticationManager authenticationManager(MemberDetailsService memberDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        // AuthenticationManager는 DaoAuthenticationProvider를 통해
        // 1) 유저 얻기(by id)
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(memberDetailsService);

        // 2) 비번 검증(using passwordEncoder)
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // JWT 기반이므로 CSRF 토큰 불 필요

                // 세션 기반 사용하지 않겠어
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 시큐리티에게 인증 받은 회원을 직접 알려주겠다
                .securityContext(sc -> sc.requireExplicitSave(false))

                // 스프링의 디폴트 폼로그인을 비활성화시킨다
                .formLogin(formLogin -> formLogin.disable())
                
                // 스프링의 기본 로그아웃 비활성화 시킴
                .logout(logout -> logout.disable())

                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/member/regist.html").permitAll()
                        .requestMatchers("/member/regist").permitAll()
                        .requestMatchers("/member/login.html").permitAll()
                        .requestMatchers("/member/login").permitAll()
                        .requestMatchers("/member/refresh").permitAll()
                        .requestMatchers("/member/logout").permitAll()
                        .anyRequest().authenticated() // 이외의 요청은 로그인을 해야함
                )
                .build();
    }
    
}
