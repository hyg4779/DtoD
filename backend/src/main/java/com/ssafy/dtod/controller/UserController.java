package com.ssafy.dtod.controller;

import com.ssafy.dtod.model.User;
import com.ssafy.dtod.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(tags = {"회원관리 API"})
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user/join")
    @ApiOperation(value = "사용자 등록", notes = "이름, 비밀번호로 유저를 생성한다.")
    public User create(@RequestBody User user) {
        System.out.println("사용자 등록 START");
        System.out.println(user.toString());
        System.out.println("사용자 등록 END");
        return userRepository.save(user);
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "사용자 조회", notes = "email에 해당하는 유저정보를 조회한다.")
    public User read(@PathVariable String userId) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        userOptional.ifPresent(System.out::println);

        return userOptional.get();
    }
}