package com.ssafy.dtod.service;

import com.ssafy.dtod.dto.UserDto;
import com.ssafy.dtod.model.Authority;
import com.ssafy.dtod.model.User;
import com.ssafy.dtod.repository.UserRepository;
import com.ssafy.dtod.util.SecurityUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import java.util.Collections;
import java.util.Optional;
 
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
 
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
 
    @Transactional
    public User signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByUserEmail(userDto.getUserEmail()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
 
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();
 
        User user = User.builder()
                .userEmail(userDto.getUserEmail())
                .userPwd(passwordEncoder.encode(userDto.getUserPwd()))
                .userName(userDto.getUserName())
                .userJobs(userDto.getUserJobs())
                //배열로 받아온 데이터를 String.join을 이용해 하나씩 꺼내서 연결하여 userTechstack에 넣어준다.
                .userTechstack(String.join(",", userDto.getUserTechstack()))
                .authorities(Collections.singleton(authority))
                .userActivated(true)
                .build();
 
        return userRepository.save(user);
    }
 
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String userEmail) {
        return userRepository.findOneWithAuthoritiesByUserEmail(userEmail);
    }
 
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUserEmail);
    }
}