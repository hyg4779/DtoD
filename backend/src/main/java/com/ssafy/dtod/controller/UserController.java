package com.ssafy.dtod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dtod.model.User;
import com.ssafy.dtod.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
@Api(tags = {"회원관리 API"})
public class UserController {

    @Autowired
    private UserService userService;

    
    @PostMapping("/signup")
    @ApiOperation(value = "사용자 정보 등록", notes = "User를 Id, Pw, Name, Tech, Grade 입력을 통해 정보를 등록한다.")
    public ResponseEntity<User> signupUser(@RequestBody User user) {
    	return new ResponseEntity<User>(userService.Save(user), HttpStatus.OK);
    }
    
    
    @GetMapping("/list")
    @ApiOperation(value = "사용자 전체 조회", notes = "전체 사용자의 목록을 조회한다.")
    public ResponseEntity<List<User>> getUserList(){
    	List<User> users = userService.findAll();
    	return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{userId}")
    @ApiOperation(value = "사용자 정보 삭제", notes = "User의 Id 입력을 통해 정보를 삭제한다.")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId){
    	userService.deleteById(userId);
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @PutMapping("/update/{userId}")
    @ApiOperation(value = "사용자 정보 수정", notes = "User의 Id 입력을 통해 정보를 수정한다.")
    public ResponseEntity<User> updateUser(@PathVariable("userId") String userId, User user){
    	userService.updateById(userId, user);
    	return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
//    @PostMapping("/user/join")
//    @ApiOperation(value = "사용자 등록", notes = "이름, 비밀번호로 유저를 생성한다.")
//    public User create(@RequestBody User user) {
//        System.out.println("사용자 등록 START");
//        System.out.println(user.toString());
//        System.out.println("사용자 등록 END");
//        return userRepository.save(user);
//    }
//
//    @GetMapping("/user/{userId}")
//    @ApiOperation(value = "사용자 조회", notes = "email에 해당하는 유저정보를 조회한다.")
//    public User read(@PathVariable String userId) {
//        Optional<User> userOptional = userRepository.findByUserId(userId);
//        userOptional.ifPresent(System.out::println);
//
//        return userOptional.get();
//    }
}