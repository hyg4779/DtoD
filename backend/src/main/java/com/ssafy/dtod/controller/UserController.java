package com.ssafy.dtod.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ssafy.dtod.dto.UserDto;
import com.ssafy.dtod.dto.UserUpdateDto;
import com.ssafy.dtod.model.User;
import com.ssafy.dtod.service.UserService;
 
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
 
    public UserController(UserService userService) {
        this.userService = userService;
    }
 
    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }
 
    @GetMapping("/user/mypage")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }
 
    @GetMapping("/user/{userEmail}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String userEmail) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(userEmail).get());
    }
    
    @PutMapping("/user/update")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody UserUpdateDto userupdateDto){
    	return ResponseEntity.ok(userService.updateUser(userupdateDto));
    }
    
    @GetMapping("/user/checkemail/{userEmail}")
    public ResponseEntity<Boolean> checkEmail(@PathVariable String userEmail){
    	return ResponseEntity.ok(userService.checkEmail(userEmail));
    }
    
    @GetMapping("/user/checkname/{userName}")
    public ResponseEntity<Boolean> checkName(@PathVariable String userName){
    	return ResponseEntity.ok(userService.checkName(userName));
    }
    
}