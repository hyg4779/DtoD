package com.ssafy.dtod.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dtod.dto.RegistCheckInDto;
import com.ssafy.dtod.model.CheckIn;
import com.ssafy.dtod.model.User;
import com.ssafy.dtod.service.CheckInService;
import com.ssafy.dtod.service.UserService;

@RestController
@RequestMapping("/api/checkin")
public class CheckInController {
	
	@Autowired
	private CheckInService checkinService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/regist")
	public ResponseEntity<CheckIn> registCheckIn(@RequestBody RegistCheckInDto dto){
		dto.setUser(userService.getMyUserWithAuthorities().get());
		return ResponseEntity.ok(checkinService.registCheckIn(dto));
	}
	
	@GetMapping("/mycheckin")
	public ResponseEntity<List<LocalDate>> myCheckIn(){
		User user = userService.getMyUserWithAuthorities().get();
		return ResponseEntity.ok(checkinService.myCheckIn(user));
	}
	
}
