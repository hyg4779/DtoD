package com.ssafy.dtod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dtod.dto.RegistMyRoomDto;
import com.ssafy.dtod.model.MyRoom;
import com.ssafy.dtod.model.StudyRoom;
import com.ssafy.dtod.model.User;
import com.ssafy.dtod.service.MyRoomService;
import com.ssafy.dtod.service.StudyRoomService;
import com.ssafy.dtod.service.UserService;

@RestController
@RequestMapping("/api/myroom")
public class MyRoomController {

	@Autowired
	private MyRoomService myroomService;
	@Autowired
	private UserService userService;
	@Autowired
	private StudyRoomService studyroomService;
	
	@PostMapping("regist/{roomId}/{roomPwd}")
	public ResponseEntity<MyRoom> registMyRoom(@PathVariable("roomId") Long roomId, @PathVariable("roomPwd") String roomPwd){
		RegistMyRoomDto dto = new RegistMyRoomDto();
		dto.setUser(userService.getMyUserWithAuthorities().get());
		dto.setStudyroom(studyroomService.findByRoomId(roomId));
		if(roomPwd.equals(dto.getStudyroom().getRoomPwd())) {
			return ResponseEntity.ok(myroomService.registMyRoom(dto));
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myroomService.registMyRoom(dto));
		}
		
	}
	
	@GetMapping("list")
	public ResponseEntity<List<StudyRoom>> listMyRoom(){
		User user = userService.getMyUserWithAuthorities().get();
		return ResponseEntity.ok(myroomService.listMyRoom(user));
	}
	
	@DeleteMapping("delete/{myroomId}")
	public void deleteMyRoom(@PathVariable Long myroomId) {
		myroomService.deleteRoom(myroomId);
	}
	
}
