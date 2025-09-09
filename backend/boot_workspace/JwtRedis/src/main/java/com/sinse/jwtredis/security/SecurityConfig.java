package com.sinse.jwtredis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
