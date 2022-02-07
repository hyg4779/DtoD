package com.ssafy.dtod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

import com.ssafy.dtod.dto.RegistStudyboardDto;
import com.ssafy.dtod.dto.ViewStudyboardDto;
import com.ssafy.dtod.model.Studyboard;
import com.ssafy.dtod.service.StudyboardService;
import com.ssafy.dtod.service.UserService;

@RestController
@RequestMapping("/api/sboard")
public class StudyboardController {
	
	@Autowired
	private StudyboardService studyboardService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Studyboard>> findAll(){
		return new ResponseEntity<List<Studyboard>>(studyboardService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/view/{sboardId}")
	public ResponseEntity<Optional<Studyboard>> ViewBoard(@PathVariable("sboardId") Long sboardId){
		
		return ResponseEntity.ok(studyboardService.ViewBoard(sboardId));
	}
	
	@PostMapping("/regist")
	public ResponseEntity<Studyboard> saveBoard(@RequestBody RegistStudyboardDto registStudyboardDto){
//		return new ResponseEntity<Studyboard>(studyboardService.saveBoard(registStudyboardDto), HttpStatus.OK);	
		
		registStudyboardDto.setUser(userService.getMyUserWithAuthorities().get());
		
		
		System.out.println(registStudyboardDto.getSboardTitle());
		return ResponseEntity.ok(studyboardService.saveBoard(registStudyboardDto));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Studyboard> updateBoard(@RequestBody ViewStudyboardDto viewstudyboardDto) throws IOException{
		
		viewstudyboardDto.setUser(userService.getMyUserWithAuthorities().get());
		System.out.println(viewstudyboardDto.getSboardId());
		
		return ResponseEntity.ok(studyboardService.updateBoard(viewstudyboardDto));
	}
	
	@DeleteMapping("/delete/{sboardId}")
	public void deleteBoard(@PathVariable Long sboardId){
		studyboardService.delete(sboardId);
	}
	
}
