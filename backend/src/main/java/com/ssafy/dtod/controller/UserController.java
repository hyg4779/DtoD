package com.ssafy.dtod.controller;

import com.ssafy.dtod.model.User;
import com.ssafy.dtod.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.persistence.criteria.Join;

@RestController
@RequestMapping("/api")
@Api(tags = {"회원관리 API"})
public class UserController {

    @Autowired
    private UserRepository userRepository;

    
}