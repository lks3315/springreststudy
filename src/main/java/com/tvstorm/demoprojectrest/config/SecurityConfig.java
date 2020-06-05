package com.tvstorm.demoprojectrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter { // Security 설정을 위한 클래스


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 인증 처리 과정을 넘어가기 위해
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll(); // h2-console뒤의 오는 요청들 모두 퍼미션 허용
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth)throws Exception {
        auth.inMemoryAuthentication()
                .withUser("KSLee")
                .password("{noop}test1234") // {noop}은 인코딩 없이 바로 사용할 수 있도록 하는 옵션, "test1234"는 plaintext라서 오류가 날 수 있기 때문
                .roles("USER"); // 로그인 완료시 USER 권한 role

    }
}

