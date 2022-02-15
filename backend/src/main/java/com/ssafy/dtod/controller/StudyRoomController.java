package com.ssafy.dtod.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dtod.dto.RegistMyRoomDto;
import com.ssafy.dtod.dto.RegistStudyRoomDto;
import com.ssafy.dtod.dto.ViewStudyRoomDto;
import com.ssafy.dtod.model.StudyRoom;
import com.ssafy.dtod.service.MyRoomService;
import com.ssafy.dtod.service.StudyRoomService;
import com.ssafy.dtod.service.UserService;

@RestController
@RequestMapping("/api/studyroom")
public class StudyRoomController {

	@Autowired
	private StudyRoomService studyroomService;
	@Autowired
	private UserService userService;
	@Autowired
	private MyRoomService myroomService;
	
	@GetMapping("/list")
	public ResponseEntity<List<StudyRoom>> listRoom(){
		return ResponseEntity.ok(studyroomService.listRoom());
	}
	
	@GetMapping("/view/{roomId}")
	public ResponseEntity<Optional<StudyRoom>> viewRoom(@PathVariable Long roomId){
		return ResponseEntity.ok(studyroomService.viewRoom(roomId));
	}
	
	@PostMapping("/regist")
	public ResponseEntity<StudyRoom> saveRoom(@RequestBody RegistStudyRoomDto dto){
		dto.setUser(userService.getMyUserWithAuthorities().get());
		StudyRoom studyroomDto = studyroomService.saveRoom(dto);
		
		RegistMyRoomDto myroomDto = new RegistMyRoomDto();
		myroomDto.setUser(userService.getMyUserWithAuthorities().get());
		myroomDto.setStudyroom(studyroomService.findByRoomId(studyroomDto.getRoomId()));
		myroomService.registMyRoom(myroomDto);
		return ResponseEntity.ok(studyroomDto);
	}
	
	@PutMapping("/update")
	public ResponseEntity<StudyRoom> updateRoom(@RequestBody ViewStudyRoomDto dto){
		dto.setUser(userService.getMyUserWithAuthorities().get());
		return ResponseEntity.ok(studyroomService.updateRoom(dto));
	}
	
	@DeleteMapping("/delete/{roomId}")
	public void deleteRoom(@PathVariable Long roomId) {
		studyroomService.deleteRoom(roomId);
	}
}
