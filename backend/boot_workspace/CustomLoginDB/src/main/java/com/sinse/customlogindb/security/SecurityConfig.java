package com.sinse.customlogindb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// 스프링이 디폴트로 지원하는 폼로그인을 커스텀 하려면, 설정 클래스가 필요함
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 스프링 시큐리티의 처리를 담당하는 객체인 SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth-> auth
                        .anyRequest().authenticated()).formLogin(form->form
                        .loginPage("/loginform") // 로그인 폼을 만나기 위한 요청 주소 등록
                        .loginProcessingUrl("/login") // 로그인 요청을 처리하는 uri 등록
                        .usernameParameter("id")
                        .passwordParameter("pwd")
                        .defaultSuccessUrl("/main", true)
                        .permitAll()
                ); // anyRequest()- 모든 요청에 대해서 authenticated() - 인증 요청을 한다

        return http.build();
    }

}
