package com.ssafy.dtod.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.dtod.dto.UserDto;
import com.ssafy.dtod.model.User;
import com.ssafy.dtod.service.UserService;
 
@RestController
@RequestMapping("/api")
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
 
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }
 
    @GetMapping("/user/{userEmail}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String userEmail) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(userEmail).get());
    }
    
    @PostMapping(value = "/file/upload/profile-img")
    @ResponseBody
    public String requestUploadFile(@RequestParam("file") MultipartFile file) {
        try {
        		User userInfo = userService.getMyUserWithAuthorities().get();
        		System.out.println(userInfo.getUserEmail());
                String path = "./profile/" + userInfo.getUserId();
                String savedFileName = file.getOriginalFilename();
                File f = new File(path);
                if (!f.exists())
                    f.mkdir();
                String savedPath = path + "/" + savedFileName;
                FileOutputStream writer = new FileOutputStream(savedPath);
                writer.write(file.getBytes());
                writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "upload fail";
        }
        return "upload success";
    }
}