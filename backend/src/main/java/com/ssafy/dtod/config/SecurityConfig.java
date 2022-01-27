package com.ssafy.dtod.config;

import com.ssafy.dtod.jwt.TokenProvider;
import com.ssafy.dtod.jwt.JwtAccessDeniedHandler;
import com.ssafy.dtod.jwt.JwtSecurityConfig;
import com.ssafy.dtod.jwt.JwtAuthenticationEntryPoint;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
 
    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers(
                        "/favicon.ico"
                );
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
 
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
 
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
 
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
 
                .and()
                .authorizeRequests()
                // authenticate, signup 은 Token이 없어도 호출할 수 있도록 허용
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/signup").permitAll()
                .antMatchers("/api/v2/**", "/swagger-ui.html", "/swagger/**", "/swagger-resources/**", "/webjars/**", "/v2/api-docs").permitAll()
                .anyRequest().authenticated()
 
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
 
}