package com.ssafy.dtod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ssafy.dtod.jwt.JwtAccessDeniedHandler;
import com.ssafy.dtod.jwt.JwtAuthenticationEntryPoint;
import com.ssafy.dtod.jwt.JwtSecurityConfig;
import com.ssafy.dtod.jwt.TokenProvider;
 
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
        web.ignoring()
                .antMatchers(
                		"/error",
                		"/webjars/**",
                		"/favicon.ico"
                );
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		// 토큰을 사용하기 때문에 csrf를 disable로 설정
                .csrf().disable()
 
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
 
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
 
                //세션을 사용하지 않기 때문에 세션 정책을 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
 
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                
                // authenticate, signup 은 Token이 없어도 호출할 수 있도록 허용
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/signup").permitAll()
                .antMatchers("/api/user/mypage").permitAll()
                .antMatchers("/api/user/update").permitAll()
                .antMatchers("/api/user/checkemail/{userEmail}").permitAll()
                .antMatchers("/api/user/checkname/{userName}").permitAll()
                .antMatchers("/api/sboard/*").permitAll()
                .antMatchers("/api/studyroom/*").permitAll()
                .antMatchers("/api/myroom/*").permitAll()
                .antMatchers("/api/checkin/*").permitAll()
                .antMatchers("/api/checkout/*").permitAll()
//                .antMatchers("/api/sboard/regist").permitAll()
//                .antMatchers("/api/sboard/list").permitAll()
//                .antMatchers("/api/sboard/update").permitAll()
//                .antMatchers("/api/sboard/delete/{sboardId}").permitAll()
//                .antMatchers("/api/sboard/view/{sboardId}").permitAll()
                .antMatchers("/api/scomment/*").permitAll()
                .antMatchers("/api/cboard/*").permitAll()
                .antMatchers("/api/ccomment/*").permitAll()
                .antMatchers("/api/v2/**", "/swagger-ui.html", "/swagger/**", "/swagger-resources/**", "/webjars/**", "/v2/api-docs").permitAll()
                
                .anyRequest().authenticated()
 
                .and()
                .cors()
                
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedOrigin("https://i6b210.p.ssafy.io");
        configuration.addAllowedOrigin("https://i6b210.p.ssafy.io:8080");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
 
}