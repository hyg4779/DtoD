package com.ssafy.dtod.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dtod.dto.RegistCheckOutDto;
import com.ssafy.dtod.model.CheckOut;
import com.ssafy.dtod.model.User;
import com.ssafy.dtod.service.CheckOutService;
import com.ssafy.dtod.service.UserService;

@RestController
@RequestMapping("/api/checkout")
public class CheckOutController {

	@Autowired
	private CheckOutService checkoutService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/regist")
	public ResponseEntity<CheckOut> registCheckIn(@RequestBody RegistCheckOutDto dto){
		dto.setUser(userService.getMyUserWithAuthorities().get());
		return ResponseEntity.ok(checkoutService.registCheckOut(dto));
	}
	
	@GetMapping("/mycheckout")
	public ResponseEntity<List<LocalDateTime>> myCheckIn(){
		User user = userService.getMyUserWithAuthorities().get();
		return ResponseEntity.ok(checkoutService.myCheckOut(user));
	}
	
}
